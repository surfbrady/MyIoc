package main.com.myioc.web.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * use for web application which
 * 
 */
public class ContextLoaderListener extends ContextLoader implements ServletContextListener{

	private ContextLoader contextLoader;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		this.contextLoader = this;
		this.contextLoader.initWebApplicationContext(arg0.getServletContext());
	}

}
