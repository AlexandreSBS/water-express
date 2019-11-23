package br.com.waterexpress.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactorySession {
	
	private static FactorySession instance;
	private SessionFactory sessionFactory;
	
	private FactorySession() {
		sessionFactory =new Configuration()
				.configure().buildSessionFactory();
	}
	
	public static FactorySession getFactorySession() {
		if (instance == null) {
			instance = new FactorySession();
		}
		return instance;		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
