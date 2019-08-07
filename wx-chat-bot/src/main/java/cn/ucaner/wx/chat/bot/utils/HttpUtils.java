package cn.ucaner.wx.chat.bot.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName：HttpUtils
 * @Description： <p> HttpUtils </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:41
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Component
public class HttpUtils {

    public static final String SUCCESS_CODE = "200";

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    public static final String ENCODE = "UTF-8";

    public static CloseableHttpClient httpClient = null;
    public static HttpClientContext context = null;
    public static CookieStore cookieStore = null;
    public static RequestConfig requestConfig = null;

    static {
        init();
    }

    private static void init() {
        context = HttpClientContext.create();
        cookieStore = new BasicCookieStore();
        // 配置超时时间（连接服务端超时1秒，请求数据返回超时2秒）
        requestConfig = RequestConfig.custom().setConnectTimeout(6000).setSocketTimeout(6000)
                .setConnectionRequestTimeout(6000).build();
        // 设置默认跳转以及存储cookie
        httpClient = HttpClientBuilder.create().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore).build();
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return response
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String get(String url) {
        return get(url, false, null,false);
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return response
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String downloadImage(String url,String filePath) {
        return get(url, true, filePath,false);
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return response
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String downloadVideo(String url,String filePath) {
        return get(url, true, filePath,true);
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return response
     * @throws ClientProtocolException
     * @throws IOException
     */
    private static String get(String url, boolean download, String filePath,boolean video) {
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            httpget.setHeader("Referer", "https://wx.qq.com/");
            if(video){
                httpget.setHeader("Range", "bytes=0-");
            }
            //设定请求的参数
            if (download) {
                response = httpClient.execute(httpget);
                //下载
                return download(response, filePath);
            } else {
                response = httpClient.execute(httpget, context);
                //普通http请求
                return copyResponse2Str(response);
            }
        } catch (Exception e) {
            LOGGER.debug("请求失败\t" + url);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将返回的Response转化成String对象
     *
     * @param response 返回的Response
     * @return
     */
    private static String copyResponse2Str(CloseableHttpResponse response) {
        try {
            int code = response.getStatusLine().getStatusCode();
            //当请求的code返回值不是400的情况
            if ((code == HttpStatus.SC_MOVED_TEMPORARILY)
                    || (code == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (code == HttpStatus.SC_SEE_OTHER)
                    || (code == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                return null;
            } else {
                return copyInputStream2Str(response.getEntity().getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将InputStream转化为String类型的数据
     *
     * @param in
     * @return
     */
    private static String copyInputStream2Str(InputStream in) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, ENCODE));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            LOGGER.debug("获取字符串失败");
        }
        return null;
    }

    private static String download(CloseableHttpResponse response, String filePath) {

        // 文件已存在，跳过下载
        File imageFile = new File(filePath);
        if (imageFile.exists()) {
            return filePath;
        }
        String fileDirPath = filePath.substring(0, filePath.lastIndexOf("\\"));
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }

        HttpEntity entity = response.getEntity();
        try {
            InputStream instream = entity.getContent();
            byte[] data = readInputStream(instream);

            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
            return filePath;
        } catch (Exception e) {
            LOGGER.error("download file error,", e);
        }
        return null;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 发从post 请求
     *
     * @param url
     * @param parameters
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> parameters, boolean raw) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        CloseableHttpResponse response = null;
        try {
            //设定请求的参数
            setRequestParamter(parameters, httpPost, raw);
            //发送请求
            response = httpClient.execute(httpPost, context);
            return copyResponse2Str(response);
        } catch (Exception e) {
            LOGGER.debug("请求失败\t" + url);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发从post 请求
     *
     * @param url
     * @param parameters
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String post(String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        CloseableHttpResponse response = null;
        try {
            //发送请求
            response = httpClient.execute(httpPost, context);
            return copyResponse2Str(response);
        } catch (Exception e) {
            LOGGER.debug("请求失败\t" + url);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 设定POST请求的参数
     *
     * @param parameters
     * @param httpPost
     * @throws UnsupportedEncodingException
     */
    private static void setRequestParamter(Map<String, Object> parameters, HttpPost httpPost, boolean raw)
            throws UnsupportedEncodingException {
        if (raw) {
            String body = JSON.toJSONString(parameters);
            httpPost.setEntity(new StringEntity(body));
            return;
        }
        List<NameValuePair> nvps;
        //添加参数
        if (parameters != null && parameters.size() > 0) {
            nvps = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> map : parameters.entrySet()) {
                NameValuePair param = new BasicNameValuePair(map.getKey(), map.getValue().toString());
                nvps.add(param);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE));
        }
    }

    /**
     * 将 http://www.yellowcong.com?age=7&name=8
     * 这种age=7&name=8  转化为map数据
     *
     * @param parameters
     * @return
     */
    @SuppressWarnings("unused")
    private static List<NameValuePair> toNameValuePairList(String parameters) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String[] paramList = parameters.split("&");
        for (String parm : paramList) {
            int index = -1;
            for (int i = 0; i < parm.length(); i++) {
                index = parm.indexOf("=");
                break;
            }
            String key = parm.substring(0, index);
            String value = parm.substring(++index, parm.length());
            nvps.add(new BasicNameValuePair(key, value));
        }
        System.out.println(nvps.toString());
        return nvps;
    }

    /**
     * 手动增加cookie
     *
     * @param name
     * @param value
     * @param domain
     * @param path
     */
    public void addCookie(String name, String value, String domain, String path) {
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookieStore.addCookie(cookie);
    }

    /**
     * 把结果console出来
     *
     * @param httpResponse
     * @throws ParseException
     * @throws IOException
     */
    public static void printResponse(HttpResponse httpResponse) throws ParseException, IOException {
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        System.out.println("status:" + httpResponse.getStatusLine());
        System.out.println("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
    }

    /**
     * 把当前cookie从控制台输出出来
     */
    public static void printCookies() {
        cookieStore = context.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("key:" + cookie.getName() + "  value:" + cookie.getValue());
        }
    }

    /**
     * 检查cookie的键值是否包含传参
     *
     * @param key
     * @return
     */
    public static boolean checkCookie(String key) {
        cookieStore = context.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        boolean res = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * 直接把Response内的Entity内容转换成String
     *
     * @param httpResponse
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String toString(CloseableHttpResponse httpResponse) throws ParseException, IOException {
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            return EntityUtils.toString(entity);
        } else {
            return null;

        }
    }
}
