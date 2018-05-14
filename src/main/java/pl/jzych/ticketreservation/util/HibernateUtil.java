package pl.jzych.ticketreservation.util;

import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactory() {
		if(Objects.isNull(sessionFactory)) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}


}
