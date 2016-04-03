package com.sisgesbib.util;

import org.springframework.web.context.WebApplicationContext;

public class SpringWebApplicationContext 
{
    private SpringWebApplicationContext()
	{
	}
	
	private static SpringWebApplicationContext unico = null;
	public static SpringWebApplicationContext getInstance() 
	{
		if (unico==null)
			unico = new SpringWebApplicationContext();
		return unico;
	}
	private WebApplicationContext webApplicationContext=null;

	public WebApplicationContext getWebApplicationContext()
	{
		return webApplicationContext;
	}

	public void setWebApplicationContext(WebApplicationContext context)
	{
		webApplicationContext = context;
	}
	public Object getBean(String beanId)
	{
		Object obj = webApplicationContext.getBean(beanId);
		return obj;
	}	
}
