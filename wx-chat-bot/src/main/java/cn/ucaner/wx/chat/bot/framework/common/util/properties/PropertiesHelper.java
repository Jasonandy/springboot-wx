package cn.ucaner.wx.chat.bot.framework.common.util.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * @ClassName：PropertiesHelper
 * @Description： <p> PropertiesHelper </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:57
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class PropertiesHelper {

    public static Logger logger = LoggerFactory.getLogger(PropertiesHelper.class);

    /**
     * PropertiesUtil
     */
	private static PropertiesHelper propertiesHelper = null;

    /**
     * ResourceLoader
     */
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    /**
     * Properties
     */
    private final Properties properties;

    /**
     * PropertiesUtil 加载指定的配置文件
     * @param resourcesPaths
     */
    public PropertiesHelper(String... resourcesPaths) {
        properties = loadProperties(resourcesPaths);
    }

    public Properties getProperties() {
        return properties;
    }


    /**
     * getInstance 单例
     * @return
     */
	public static PropertiesHelper getInstance() {
		if (propertiesHelper == null) {
            propertiesHelper = new PropertiesHelper();
		}
		return propertiesHelper;
	}

    /**
     * 取出Property.
     * @param key
     * @return
     * @Author Jason
     */
    private String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        return properties.getProperty(key);
    }

    /**
     * 取出String类型的Property 如果为NULL抛出异常
     * @param key key
     * @return String
     * @Author Jason
     */
    public String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }


    /**
     * 取出String类型的Property.如果Null則返回Default值.
     * @param key
     * @param defaultValue
     * @return
     * @Author Jason
     */
    public String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }


    /**
     * 取出Integer类型的Property.如果Null或内容错误则抛出异常.
     * @param key
     * @return
     * @Author Jason
     */
    public Integer getInteger(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }


    /**
     * 取出Integer类型的Property.如果Null則返回Default值，如果内容错误则抛出异常
     * @param key
     * @param defaultValue
     * @return
     * @Author Jason
     */
    public Integer getInteger(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }


    /**
     * 取出Double类型的Property.如果Null或内容错误则抛出异常.
     * @param key
     * @return
     * @Author Jason
     */
    public Double getDouble(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Double.valueOf(value);
    }


    /**
     * 取出Double类型的Property.如果Null則返回Default值，如果内容错误则抛出异常
     * @param key
     * @param defaultValue
     * @return
     * @Author Jason
     */
    public Double getDouble(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Double.valueOf(value) : defaultValue;
    }


    /**
     * 取出Boolean类型的Property.如果Null抛出异常,如果内容不是true/false则返回false.
     * @param key
     * @return
     * @Author Jason
     */
    public Boolean getBoolean(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Boolean.valueOf(value);
    }


    /**
     * 取出Boolean类型的Propert.如果Null則返回Default值,如果内容不为true/false则返回false.
     * @param key
     * @param defaultValue
     * @return
     * @Author Jason
     */
    public Boolean getBoolean(String key, boolean defaultValue) {
        String value = getValue(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }


    /**
     * 载入多个文件, 文件路径使用Spring Resource格式.
     * @param resourcesPaths
     * @return
     * @Author Jason
     */
    private Properties loadProperties(String... resourcesPaths) {
        Properties props = new Properties();
        for (String location : resourcesPaths) {
            logger.debug(">>> Loading properties file from path:[{}] ...", location);
            InputStream is = null;
            try {
                Resource resource = resourceLoader.getResource(location);
                is = resource.getInputStream();
                props.load(is);
            } catch (IOException ex) {
                logger.warn("<<<<< 加载不到指定配置文件 -- Could not load properties from path:{}, {} ", location, ex.getMessage());
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        logger.error("文件读取流关闭失败! :{}",e.getMessage());
                    }
                }
            }
        }
        return props;
    }

    /**
     * 根据资源路径加载配置到Map中
     * @param resourcesPaths
     * @return
     */
    public Map<String, String> loadPropertiesMap(String... resourcesPaths){
        HashMap<String, String> propsMap = new HashMap<>(15);
        Properties props = loadProperties(resourcesPaths);
        Enumeration<?> enumeration = props.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value =props.getProperty(key);
            propsMap.put(key,value);
        }
        return propsMap;
    }



    /**
     * just for test
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 加载指定位置的配置文件  .properties
         */
        PropertiesHelper propertiesHelper = new PropertiesHelper("config/kafka/kafka.properties");
        System.out.println(propertiesHelper.getProperties());
    }

}
