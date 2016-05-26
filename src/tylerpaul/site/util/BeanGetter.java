package tylerpaul.site.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanGetter {
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			"WEB-INF/beans.xml");

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}
