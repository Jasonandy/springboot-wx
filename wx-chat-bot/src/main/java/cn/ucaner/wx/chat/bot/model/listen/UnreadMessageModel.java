package cn.ucaner.wx.chat.bot.model.listen;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：UnreadMessageModel
 * @Description： <p> UnreadMessageModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:51
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class UnreadMessageModel {

    private String host;

    private Integer retCode;

    private Integer selector;

    public UnreadMessageModel() {
    }

    public UnreadMessageModel(String host, Integer retCode, Integer selector) {
        this.host = host;
        this.retCode = retCode;
        this.selector = selector;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public Integer getSelector() {
        return selector;
    }

    public void setSelector(Integer selector) {
        this.selector = selector;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
