package cn.ucaner.wx.chat.bot.dao.impl;


import cn.ucaner.wx.chat.bot.dao.WechatDao;
import cn.ucaner.wx.chat.bot.model.group.GroupInfo;
import cn.ucaner.wx.chat.bot.model.initialization.UserModel;
import cn.ucaner.wx.chat.bot.model.login.LoginModel;
import cn.ucaner.wx.chat.bot.model.scan.ScanCode;
import cn.ucaner.wx.chat.bot.utils.HttpUtils;
import cn.ucaner.wx.chat.bot.utils.TimeUtils;
import cn.ucaner.wx.chat.bot.utils.WechatApiPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName：WechatDaoImpl
 * @Description： <p> WechatDaoImpl </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:56
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
@Repository
public class WechatDaoImpl implements WechatDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatDaoImpl.class);

    /**
     {'appid': 'wx782c26e4c19acffb', 'fun': 'new', 'lang': 'zh_CN', '_': 1540975411}

     * @return
     */
    @Override
    public String getUUID() {
        Map<String,Object> params = new HashMap<>();
        params.put("appid", WechatApiPool.APPID);
        params.put("fun",WechatApiPool.FUN);
        params.put("lang",WechatApiPool.LANG);
        params.put("_", TimeUtils.getTime());
        String result = HttpUtils.post(WechatApiPool.POST_JSLOGIN, params,false);
        return result;
    }

    /**
     * 判断是否扫码
     * @param uuid
     * @param tip
     * @return
     */
    @Override
    public String waitForLogin(String uuid,Integer tip) {
        String url = String.format(WechatApiPool.GET_WAITFORLOGIN, tip, uuid, TimeUtils.getTime());
        String result = HttpUtils.get(url);
        return result;
    }

    /**
     * 登陆
     * @param scanCode
     */
    @Override
    public String login(ScanCode scanCode) {
        String result = HttpUtils.get(scanCode.getRedirectUri());
        return result;
    }

    @Override
    public String initialization(ScanCode scanCode, LoginModel loginModel) {
        //pass_ticket,skey,_
        int time = TimeUtils.getTime();
        String initializationUrl = scanCode.getBaseUri()+WechatApiPool.POST_INIT;
        String url = String.format(initializationUrl,loginModel.getPass_ticket(),loginModel.getSkey(),time);
        Map<String,Object> params = new HashMap<>();
        params.put("BaseRequest",loginModel.toBaseRequest());
        String result = HttpUtils.post(url, params,true);
        return result;
    }

    @Override
    public String statusNotify(ScanCode scanCode, LoginModel loginModel, UserModel toUser) {
        //pass_ticket
        String statusNotifyUrl = scanCode.getBaseUri()+WechatApiPool.POST_STATUS_NOTIFY;
        String url = String.format(statusNotifyUrl,loginModel.getPass_ticket());
        Map<String,Object> params = new HashMap<>();
        params.put("BaseRequest",loginModel.toBaseRequest());
        params.put("Code",3);
        params.put("FromUserName",toUser.getUserName());
        params.put("ToUserName",toUser.getUserName());
        params.put("ClientMsgId",TimeUtils.getTime());
        String result = HttpUtils.post(url, params, true);
        return result;
    }

    @Override
    public String getContactList(ScanCode scanCode, LoginModel loginModel) {
        int time = TimeUtils.getTime();
        //pass_ticket,skey,r
        String contactListUrl = scanCode.getBaseUri()+WechatApiPool.POST_GET_CONTACT_LIST;
        String url = String.format(contactListUrl,loginModel.getPass_ticket(),loginModel.getSkey(),time);
        String result = HttpUtils.post(url);
        return result;
    }

    @Override
    public String getGroupInfo(ScanCode scanCode, LoginModel loginModel, List<GroupInfo> groupInfoList) {
        String groupInfoUrl = scanCode.getBaseUri()+WechatApiPool.POST_GROUP_INFO;
        int time = TimeUtils.getTime();
        //r,pass_ticket
        String url = String.format(groupInfoUrl,time,loginModel.getPass_ticket());
        Map<String,Object> params = new HashMap<>();
        params.put("BaseRequest",loginModel.toBaseRequest());
        params.put("Count",groupInfoList.size());
        params.put("List",groupInfoList);
        String result = HttpUtils.post(url, params, true);
        return result;
    }
}
