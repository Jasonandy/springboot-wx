package cn.ucaner.wx.chat.bot.model.initialization;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：SyncKey
 * @Description： <p> SyncKey </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:53
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class SyncKey {

    @JSONField(name = "Key")
    private Integer key;

    @JSONField(name = "Val")
    private Integer val;

    public SyncKey() {
    }

    public SyncKey(Integer key, Integer val) {
        this.key = key;
        this.val = val;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
