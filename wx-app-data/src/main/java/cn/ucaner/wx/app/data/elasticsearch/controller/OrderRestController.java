package cn.ucaner.wx.app.data.elasticsearch.controller;

import cn.ucaner.wx.app.data.elasticsearch.config.ElasticSearchConfig;
import cn.ucaner.wx.app.data.elasticsearch.model.Order;
import cn.ucaner.wx.app.data.elasticsearch.service.ElasticSearchService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName：OrderRestController
 * @Description： <p> OrderRestController  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/11 - 16:43
 * @Modify By：
 * @ModifyTime： 2019/3/11
 * @Modify marker：
 * @version V1.0
*/
@RestController
public class OrderRestController {

    private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

//    @Autowired
//    private BulkProcessorService bulkProcessorService;

    @Autowired
    private ElasticSearchService elasticSearchService;

//    @Autowired
//    private QueryService queryService;


    @RequestMapping(value = "/api/order")
    public void addOrder() {
        Order order = new Order();
        order.setCategoryCode("good");
        order.setCategoryId(1);
        order.setStoreName("well");
        String jsonStr = JSON.toJSONString(order);
        logger.info("api/order{}",jsonStr);
        elasticSearchService.insertById("1","2","3",jsonStr);
    }


}
