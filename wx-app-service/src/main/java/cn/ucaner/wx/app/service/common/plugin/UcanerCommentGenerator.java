/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.common.plugin;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;

/**
* @Package：cn.ucaner.wx.app.service.common.plugin   
* @ClassName：UcanerCommentGenerator   
* @Description：   <p> 生成model/entity中-字段增加注释 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:41:13   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class UcanerCommentGenerator implements CommentGenerator {

	private Properties properties;
	private Properties systemPro;
	private boolean suppressDate;
	private boolean suppressAllComments;
	
	
	/**  
	* @param properties
	* @param systemPro
	* @param suppressDate
	* @param suppressAllComments  
	*/  
	public UcanerCommentGenerator(Properties properties, Properties systemPro, boolean suppressDate,
			boolean suppressAllComments) {
		super();
		properties = new Properties();
		suppressDate = false;
		suppressAllComments = false;
		systemPro = System.getProperties();
	}

	/**
	 * addConfigurationProperties 配置属性文件
	 */
	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
		suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
		suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
		
	}
	
	/**
	 * 文件注释
	 */
	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		if (suppressAllComments) {
			return;
		}
		Calendar cal = Calendar.getInstance();
		compilationUnit.addFileCommentLine("/**");
		compilationUnit.addFileCommentLine("* <html>");
		compilationUnit.addFileCommentLine("*  <body>");
		compilationUnit.addFileCommentLine("*   <P> Copyright 2018 - XXXX  China JasonInternational co,.ltd . </p>");
		compilationUnit.addFileCommentLine("*   <p> All rights reserved.</p>");
		compilationUnit.addFileCommentLine("*   <p> Created on " + cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月"
				+ cal.get(Calendar.DAY_OF_MONTH) + "日" + "</p>");
		compilationUnit.addFileCommentLine("*   <p> Created by " + systemPro.getProperty("user.name") + "</p>");
		compilationUnit.addFileCommentLine("*  </body>");
		compilationUnit.addFileCommentLine("* </html>");
		compilationUnit.addFileCommentLine("*/");
	}
	

	/**
	 * add by wubin@wanguo.com
	 */
	@Override
	public void addModelClassComment(TopLevelClass innerClass, IntrospectedTable table) {
		if (suppressAllComments) {
			return;
		}
		Calendar cal = Calendar.getInstance();
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine("* @Project JasonInternational  ");
		innerClass.addJavaDocLine("* @Package " + innerClass.getType().getPackageName());
		innerClass.addJavaDocLine("* @ClassName " + innerClass.getType().getFullyQualifiedName());
		innerClass.addJavaDocLine("* @TableName " + table.getTableConfiguration().getTableName());
		innerClass.addJavaDocLine("* @Description" + table.getRemarks());
		innerClass.addJavaDocLine("* @JDK version used " + systemPro.getProperty("java.runtime.version"));
		innerClass.addJavaDocLine("* @Author " + systemPro.getProperty("user.name"));
		innerClass.addJavaDocLine(
				"* @Create Date " + cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DAY_OF_MONTH) + "日");
		innerClass.addJavaDocLine("* @modify By ");
		innerClass.addJavaDocLine("* @modify Date ");
		innerClass.addJavaDocLine("* @Why&What is modify ");
		innerClass.addJavaDocLine("* @Version 1.0");
		innerClass.addJavaDocLine("*/");
	}
		
	/**
	 * add by wubin@wanguo.com
	 */
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable table) {
		if (suppressAllComments) {
			return;
		}
		Calendar cal = Calendar.getInstance();
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine("* @Project: JasonInternational co,.ltd");
		innerClass.addJavaDocLine("* @Package: " + innerClass.getType().getPackageName());
		innerClass.addJavaDocLine("* @ClassName: " + innerClass.getType().getFullyQualifiedName());
		innerClass.addJavaDocLine("* @TableName:" + table.getTableConfiguration().getTableName());
		innerClass.addJavaDocLine("* @Description:" + table.getRemarks());
		innerClass.addJavaDocLine("* @JDK version used: " + systemPro.getProperty("java.runtime.version"));
		innerClass.addJavaDocLine("* @Author:" + systemPro.getProperty("user.name") +" - JasonAndy@hotmail.com");
		innerClass.addJavaDocLine("* @Create Date: " + cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DAY_OF_MONTH) + "日");
		innerClass.addJavaDocLine("* @modify By:");
		innerClass.addJavaDocLine("* @modify Date:");
		innerClass.addJavaDocLine("* @Why&What is modify:");
		innerClass.addJavaDocLine("* @Version: 1.0");
		innerClass.addJavaDocLine("*/");
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
			field.addJavaDocLine("/**");
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			// addJavadocTag(field, false);
			field.addJavaDocLine(" */");
		}
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**"); //$NON-NLS-1$
		field.addJavaDocLine(" * This field was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" * This field corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		field.addJavaDocLine(sb.toString());

		addJavadocTag(field, false);

		field.addJavaDocLine(" */"); //$NON-NLS-1$
	}
	
	

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerEnum.addJavaDocLine("/**"); //$NON-NLS-1$
		innerEnum.addJavaDocLine(" * This enum was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" * This enum corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerEnum.addJavaDocLine(sb.toString());

		addJavadocTag(innerEnum, false);

		innerEnum.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		method.addJavaDocLine("* @return " + introspectedColumn.getRemarks());
		method.addJavaDocLine("*/");
	}

	/**
	 * setter 
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		Parameter parm = method.getParameters().get(0);
		method.addJavaDocLine(" * @param " + parm.getName() + "  " + introspectedColumn.getRemarks());
		method.addJavaDocLine(" * @Author:" + systemPro.getProperty("user.name"));
		method.addJavaDocLine(" */");
	}

	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(" *"); //$NON-NLS-1$
		StringBuilder sb = new StringBuilder();
		sb.append(" * "); //$NON-NLS-1$
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge"); //$NON-NLS-1$
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}
	
	
	protected String getDateString() {
		if (suppressDate) {
			return null;
		} else {
			return new Date().toString();
		}
	}
	
	
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**"); //$NON-NLS-1$
		method.addJavaDocLine(" * Created by Jason. "); //$NON-NLS-1$
		sb.append(" * This method corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		method.addJavaDocLine(sb.toString());
		addJavadocTag(method, false);
		method.addJavaDocLine(" */"); //$NON-NLS-1$
	}


	@Override
	public void addComment(XmlElement xmlElement) {
		
	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		return;
	}

}
