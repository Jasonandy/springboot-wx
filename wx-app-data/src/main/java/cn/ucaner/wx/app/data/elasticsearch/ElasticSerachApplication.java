package cn.ucaner.wx.app.data.elasticsearch;

import cn.ucaner.wx.app.data.elasticsearch.config.ElasticSearchConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Jason
 */
@SpringBootApplication
public class ElasticSerachApplication {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);

    public static void main(String[] args) {
        SpringApplication.run(ElasticSerachApplication.class, args);
        logger.info(" application running...");
    }


}
