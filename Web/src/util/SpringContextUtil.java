package util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware{

	protected static ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		 context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
    
    public static Object getBean(String name)
    {
    	return getContext().getBean(name);
    }

    
 
}

