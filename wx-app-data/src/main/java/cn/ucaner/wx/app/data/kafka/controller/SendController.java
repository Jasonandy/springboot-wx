package cn.ucaner.wx.app.data.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ucaner.wx.app.data.kafka.producer.Producer;

/**
 * @projectName：wx-app
 * @Package：cn.ucaner.wx.app.data.kafka.controller
 * @Description： <p>  SendController </p>
 * @Author： - Jason
 * @CreatTime：2019/3/8 - 17:43
 * @Modify By：
 * @ModifyTime： 2019/3/8
 * @Modify marker：
 */
@RestController
@RequestMapping("/kafka")
public class SendController {

    /**
     * 注入producer生产者
     */
    @Autowired
    private Producer producer;

    /**
     * 发送消息
     * @return 提示信息
     */
    @RequestMapping(value = "/send")
    public String send() {
        producer.send();
        return "{\"code\":SEDN MESSAGE SUCCESS}";
    }

}


