package cn.ucaner.wx.chat.bot.model.group;

import cn.ucaner.wx.chat.bot.model.contact.ContactMemberModel;
import cn.ucaner.wx.chat.bot.model.initialization.BaseResponse;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * @ClassName：GroupModel
 * @Description： <p> GroupModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:54
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class GroupModel {

    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "Count")
    private Integer count;

    @JSONField(name = "ContactList")
    private List<ContactMemberModel> contactList;

    public GroupModel() {
    }

    public GroupModel(BaseResponse baseResponse, Integer count, List<ContactMemberModel> contactList) {
        this.baseResponse = baseResponse;
        this.count = count;
        this.contactList = contactList;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ContactMemberModel> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactMemberModel> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
