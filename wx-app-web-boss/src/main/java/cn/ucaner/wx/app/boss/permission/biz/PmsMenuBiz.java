/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.boss.permission.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ucaner.wx.app.service.permission.service.PmsMenuService;

/**
* @Package：cn.ucaner.wx.app.boss.permission.biz   
* @ClassName：PmsMenuBiz   
* @Description：   <p> PmsMenuBiz </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:42:39   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Component("pmsMenuBiz")
public class PmsMenuBiz {

	private static final Logger logger = LoggerFactory.getLogger(PmsMenuBiz.class);

	@Autowired
	private PmsMenuService pmsMenuService;

	/**
	 * 获取用于编制菜单时的树.
	 */
	@SuppressWarnings("rawtypes")
	public String getTreeMenu(String actionUrl) {
		List treeData = pmsMenuService.getListByParent(null);
		StringBuffer strJson = new StringBuffer();
		recursionTreeMenu("0", strJson, treeData, actionUrl);
		return strJson.toString();
	}

	/**
	 * 递归输出树形菜单
	 * 
	 * @param pId
	 * @param buffer
	 */
	@SuppressWarnings("rawtypes")
	private void recursionTreeMenu(String pId, StringBuffer buffer, List list, String url) {
		if (pId.equals("0")) {
			buffer.append("<ul class=\"tree treeFolder collapse \" >");
		} else {
			buffer.append("<ul>");
		}
		List<Map> listMap = getSonMenuListByPid(pId, list);
		for (Map map : listMap) {
			String id = map.get("id").toString();// id
			String name = map.get("name").toString();// 名称
			String isLeaf = map.get("isLeaf").toString();// 是否叶子科目
			buffer.append("<li><a onclick=\"onClickMenuNode(" + id + ")\"  href=\"" + url + "?id=" + id + "\" target=\"ajax\" rel=\"jbsxBox\"  value=" + id + ">" + name + "</a>");
			if (!isLeaf.equals("1")) {
				recursionTreeMenu(id, buffer, list, url);
			}
			buffer.append("</li>");
		}
		buffer.append("</ul>");
	}

	/**
	 * 根据(pId)获取(menuList)中的所有子菜单集合.
	 * 
	 * @param pId
	 *            父菜单ID.
	 * @param menuList
	 *            菜单集合.
	 * @return sonMenuList.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getSonMenuListByPid(String pId, List menuList) {
		List sonMenuList = new ArrayList<Object>();
		logger.info("getSonMenuListByPid");
		for (Object menu : menuList) {
			Map map = (Map) menu;
			if (map != null) {
				String parentId = map.get("pId").toString();// 父id
				if (parentId.equals(pId)) {
					sonMenuList.add(map);
				}
			}
		}
		return sonMenuList;
	}

}