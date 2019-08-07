package cn.ucaner.wx.chat.bot.model.listen;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：AddMessageAppInfo
 * @Description： <p> AddMessageAppInfo </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:51
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class AddMessageAppInfo {

    @JSONField(name = "AppID")
    private String appID;

    @JSONField(name = "Type")
    private Integer type;

    public AddMessageAppInfo() {
    }

    public AddMessageAppInfo(String appID, Integer type) {
        this.appID = appID;
        this.type = type;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
