package cn.ucaner.wx.app.service.common.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.github.pagehelper.PageHelper;

/**
* @Package：cn.ucaner.wx.app.service.common.config   
* @ClassName：MybatisConfig   
* @Description：   <p> MybatisConfig </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:31:58   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
//@Component
//指定扫描的mapper接口所在的包
//@MapperScan("cn.ucaner.wx.app.service.*.dao")
public class MybatisConfig {

	
   // private static final String TYPE_ALIASES_PACKAGE = "cn.ucaner.wx.app.service";
    //private static final String MAPPER_LOCATION = "classpath:cn.ucaner.wx.app.service";

    //@Bean
   // @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        // 添加插件
        // PageHelper是Interceptor接口的实现类
        // 基于接口实现的多态
        // 指向接口的引用（pageHelper）必须是实现了该接口的一个类的实例程序（implements Interceptor），在运行时，根据对象引用的实际类型来执行对应的方法。
        // 也就是说，可以根据这个特性，对SpringBoot定制特殊功能。
       // sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

        //testMybatis.typeAliasesPackage：指定domain类的基包，即指定其在*Mapper.xml文件中可以使用简名来代替全类名（看后边的UserMapper.xml介绍）
        //sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));

        return sqlSessionFactoryBean.getObject();
    }
    
    
}
