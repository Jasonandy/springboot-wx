package cn.ucaner.wx.app.data.elasticsearch;

import cn.ucaner.wx.app.data.elasticsearch.config.ElasticSearchConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName：ElasticSerachApplication
 * @Description： <p> ElasticSerachApplication  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/11 - 8:54
 * @Modify By：
 * @ModifyTime： 2019/3/11
 * @Modify marker：
 * @version V1.0
*/
@SpringBootApplication
public class ElasticSerachApplication {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);

    public static void main(String[] args) {
        logger.info(" application running...");
        SpringApplication.run(ElasticSerachApplication.class, args);
    }


}
