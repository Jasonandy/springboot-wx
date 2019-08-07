package cn.ucaner.wx.chat.bot.model.scan;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：ScanCode
 * @Description： <p> ScanCode </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:50
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class ScanCode {

    private String redirectUri;

    private String baseUri;

    public ScanCode() {
    }

    public ScanCode(String redirectUri, String baseUri) {
        this.redirectUri = redirectUri;
        this.baseUri = baseUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
