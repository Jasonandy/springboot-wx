/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.db;

/**
* @Package：cn.ucaner.wx.app.core.db   
* @ClassName：DataSourceEnum   
* @Description：   <p>  多数据源枚举  </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:31:08   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public enum DataSourceEnum {

	// 主库
	MASTER("masterDataSource", true),
	
	// 从库
	SLAVE("slaveDataSource", false),;

	// 数据源名称
	private String name;
	
	// 是否是默认数据源
	private boolean master;

	DataSourceEnum(String name, boolean master) {
		this.name = name;
		this.master = master;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public String getDefault() {
		String defaultDataSource = "";
		for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
			if (!"".equals(defaultDataSource)) {
				break;
			}
			if (dataSourceEnum.master) {
				defaultDataSource = dataSourceEnum.getName();
			}
		}
		return defaultDataSource;
	}

}
