package cn.ucaner.wx.chat.bot.model.initialization;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName：SyncKeyModel
 * @Description： <p> SyncKeyModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:53
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class SyncKeyModel {

    @JSONField(name = "Count")
    private Integer count;

    @JSONField(name = "List")
    private List<SyncKey> list;

    public SyncKeyModel() {
    }

    public SyncKeyModel(Integer count, List<SyncKey> list) {
        this.count = count;
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<SyncKey> getList() {
        return list;
    }

    public void setList(List<SyncKey> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * 获取SyncKey
     * @return
     */
    public String getSyncKey(){
        List<String> syncKeyList = list.stream().map(syncKey -> String.format("%s_%s", syncKey.getKey(), syncKey.getVal())).collect(Collectors.toList());
        return StringUtils.join(syncKeyList,"|");
    }

}
