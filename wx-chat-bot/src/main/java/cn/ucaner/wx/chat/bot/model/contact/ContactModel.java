package cn.ucaner.wx.chat.bot.model.contact;

import cn.ucaner.wx.chat.bot.model.initialization.BaseResponse;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @ClassName：ContactModel
 * @Description： <p> ContactModel </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:01
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class ContactModel {

    @JSONField(name = "BaseResponse")
    private BaseResponse baseResponse;

    @JSONField(name = "MemberCount")
    private Integer memberCount;

    @JSONField(name = "MemberList")
    private List<ContactMemberModel> memberList;

    @JSONField(name = "Seq")
    private Integer seq;

    public ContactModel() {
    }

    public ContactModel(BaseResponse baseResponse, Integer memberCount, List<ContactMemberModel> memberList, Integer seq) {
        this.baseResponse = baseResponse;
        this.memberCount = memberCount;
        this.memberList = memberList;
        this.seq = seq;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public List<ContactMemberModel> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<ContactMemberModel> memberList) {
        this.memberList = memberList;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
