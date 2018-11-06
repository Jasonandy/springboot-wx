/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.wx.app.boss.permission.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Package：cn.ucaner.wx.app.boss.permission.enums   
* @ClassName：OperatorTypeEnum   
* @Description：   <p> 操作员类型 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:48:58   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public enum OperatorTypeEnum {

	/** 普通用户 **/
	USER("普通用户"),
	/** 超级管理员 **/
	ADMIN("超级管理员");

	/** 描述 */
	private String desc;

	private OperatorTypeEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static OperatorTypeEnum getEnum(String value) {
		OperatorTypeEnum resultEnum = null;
		OperatorTypeEnum[] enumAry = OperatorTypeEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].name().equals(value)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		OperatorTypeEnum[] ary = OperatorTypeEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].name()));
			map.put("value", ary[num].name());
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		OperatorTypeEnum[] ary = OperatorTypeEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", ary[i].name());
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}

}
