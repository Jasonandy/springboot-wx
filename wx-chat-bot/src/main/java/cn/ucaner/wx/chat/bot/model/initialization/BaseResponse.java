package cn.ucaner.wx.chat.bot.model.initialization;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName：BaseResponse
 * @Description： <p> BaseResponse </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:52
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class BaseResponse {

    @JSONField(name = "Ret")
    private Integer ret;

    @JSONField(name = "ErrMsg")
    private String errMsg;

    public BaseResponse() {
    }

    public BaseResponse(Integer ret, String errMsg) {
        this.ret = ret;
        this.errMsg = errMsg;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean verify(){
        return this.ret == 0;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
