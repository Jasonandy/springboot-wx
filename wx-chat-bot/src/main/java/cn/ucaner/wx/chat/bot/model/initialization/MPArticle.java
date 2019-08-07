package cn.ucaner.wx.chat.bot.model.initialization;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：MPArticle
 * @Description： <p> MPArticle </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:53
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class MPArticle {

    /**
     * 文章标题
     */
    @JSONField(name = "Title")
    private String title;

    /**
     * 文章描述
     */
    @JSONField(name = "Digest")
    private String digest;

    /**
     * 文章图片地址
     */
    @JSONField(name = "Cover")
    private String cover;

    /**
     * 文章url
     */
    @JSONField(name = "Url")
    private String url;

    public MPArticle() {
    }

    public MPArticle(String title, String digest, String cover, String url) {
        this.title = title;
        this.digest = digest;
        this.cover = cover;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
