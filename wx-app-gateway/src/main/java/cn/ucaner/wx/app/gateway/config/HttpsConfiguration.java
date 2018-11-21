/**
 * <html>
 * <body>
 *  <P> Copyright JasonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason  https://github.com/Jasonandy/springboot-wx</p>
 *  </body>
 * </html>
 */
//package cn.ucaner.wx.app.gateway.config;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.Ssl;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//
///**
//* @Package：cn.ucaner.wx.app.gateway.config   
//* @ClassName：HttpsConfiguration   
//* @Description：   <p> HttpsConfiguration 配置https ssl  if 网站需要用https的话 配合nginx使用 </p>
//* @Author： - Jason   
//* @CreatTime：2018年11月2日 上午9:27:58   
//* @Modify By：   
//* @ModifyTime：  2018年11月2日
//* @Modify marker：   
//* @version    V1.0
// */
////@Configuration
////havingValue 相同时候才加载配置文件
////@ConditionalOnProperty(name = "enable", havingValue = "true",prefix="https",matchIfMissing=false)
//public class HttpsConfiguration {
//	
//	/**
//	 * http 端口
//	 */
//	@Value("${https.httpPort}")
//    private Integer httpPort;
//
//	/**
//	 * https端口
//	 */
//	@Value("${https.httpsPort}")
//	private Integer httpsPort;
//	
//	/**
//	 * 证书密码配置
//	 */
//	@Value("${https.cerPwd}")
//	private String cerPwd;
//	
//	/**
//	 * 证书路径配置 /data/cer/server.jks
//	 */ 
//	@Value("${https.cerPath}")
//	private String cerPath;
//	
//	/**
//	 * @Description: containerCustomizer 
//	 * @return EmbeddedServletContainerCustomizer
//	 * @Autor: Jason
//	 */
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return new EmbeddedServletContainerCustomizer() {
//			@Override
//			public void customize(ConfigurableEmbeddedServletContainer container) {
//			Ssl ssl = new Ssl();
//			// Server.jks中包含服务器私钥和证书
//			ssl.setKeyStore(cerPath);
//			ssl.setKeyStorePassword(cerPwd);
//			container.setSsl(ssl);
//			container.setPort(8443);
//			}
//		};
//	}
//
//	/**
//	 * @Description: servletContainerFactory 
//	 * @return EmbeddedServletContainerFactory
//	 * @Autor: Jason
//	 */
//	@Bean
//	public EmbeddedServletContainerFactory servletContainerFactory() {
//		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {
//		  @Override
//		  protected void postProcessContext(Context context) {
//		    SecurityConstraint securityConstraint = new SecurityConstraint();
//		    securityConstraint.setUserConstraint("CONFIDENTIAL");//Confidential 保密
//		    SecurityCollection collection = new SecurityCollection();
//		    collection.addPattern("/*");
//		    securityConstraint.addCollection(collection);
//		    context.addConstraint(securityConstraint);
//		  }
//		};
//		factory.addAdditionalTomcatConnectors(createHttpConnector());
//		return factory;
//	}
//
//	/**
//	 * @Description: createHttpConnector 
//	 * @return Connector
//	 * @Autor: Jason
//	 */
//	private Connector createHttpConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setSecure(false);
//		connector.setPort(httpPort);
//		connector.setRedirectPort(httpsPort);
//		return connector;
//	}
//}
