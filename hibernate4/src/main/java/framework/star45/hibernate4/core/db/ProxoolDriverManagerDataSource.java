package framework.star45.hibernate4.core.db;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.xml.sax.InputSource;


/**
 * Proxool连接池的datasource</br> 
 * 类名: ProxoolDriverManagerDataSource </br> 
 * 日期: 2013-5-14 下午05:30:09 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class ProxoolDriverManagerDataSource extends DriverManagerDataSource implements DisposableBean {
	
    private boolean autoShutdown = true;
    private static final Logger logger = Logger.getLogger(ProxoolDriverManagerDataSource.class);
    
    
    /**
     * 设置是否能自动关闭连接池</br>
     * 日期:2013-5-14 下午05:30:26
     * @param autoShutdown true 是 ，false 否
     */
	public void setAutoShutdown(boolean autoShutdown) {
		this.autoShutdown = autoShutdown;
	}

	/**
	 * 加载连接池xml配置文件</br>
	 * 日期:2013-5-14 下午05:31:46
	 * @param xmlFile xml配置文件资源
	 */
	public void setXmlFile(Resource xmlFile) {
		try {
			JAXPConfigurator.configure(new InputSource(xmlFile.getInputStream()), false);
		} catch (Exception e) {
			this.logger.error("Problem configuring " + xmlFile, e);
		}
	}

	/**
	 * 加载连接池属性配置文件</br>
	 * 日期:2013-5-14 下午05:32:26
	 * @param propertyFile 属性配置文件资源
	 */
	public void setPropertyFile(Resource propertyFile) {
		 try {

			Properties prop = new Properties();
			prop.load(propertyFile.getInputStream());
			prop.put("jdbc-0.proxool.driver-url", DBAdapter.getInstance().getURL());
			prop.put("jdbc-0.proxool.driver-class",DBAdapter.getInstance().getDriverClass());
			prop.put("jdbc-0.user", DBAdapter.getInstance().getUserName());
			prop.put("jdbc-0.password", DBAdapter.getInstance().getPassword());
			PropertyConfigurator.configure(prop);
		} catch (Exception e) {
			this.logger.error("Problem configuring " + propertyFile, e);
		}
	}
	/**
	 * spring容器关闭时调用，会根据设置决定是否关闭连接池</br> 
	 * 日期:2013-5-14 下午05:32:56 
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	public void destroy() {
        if (autoShutdown) {
            ProxoolFacade.shutdown(0);
        }
    }
}
