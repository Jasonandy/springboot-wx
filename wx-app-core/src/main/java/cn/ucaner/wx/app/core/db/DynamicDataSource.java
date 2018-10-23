package cn.ucaner.wx.app.core.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* @Package：cn.ucaner.wx.app.core.db   
* @ClassName：DynamicDataSource   
* @Description：   <p>  动态数据源（数据源切换） </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:31:26   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * 以后这里统一使用Slf4j  方便以后对接为log4j/logback
	 * exclude  commoingLog 
	 */
    private final static Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = getDataSource();
        LOGGER.info("当前操作使用的数据源：{}", dataSource);
        return dataSource;
    }

    /**
     * 设置数据源
     * @param dataSource 
     */
    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource() {
        String dataSource = CONTEXT_HOLDER.get();
        // 如果没有指定数据源，使用默认数据源
        if (null == dataSource) {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getDefault());
        }
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }

}
