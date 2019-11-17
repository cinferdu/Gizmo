package util;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static ServiceRegistry serviceRegistry;
	private static final SessionFactory sessionFactory;
	private static final String HIBERNATE_CFG = "hibernate.cfg.xml";
	
	static {
		try {
			Configuration configuration = new Configuration();
		    configuration.configure(HIBERNATE_CFG);
		    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}