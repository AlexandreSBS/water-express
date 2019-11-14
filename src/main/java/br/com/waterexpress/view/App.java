package br.com.waterexpress.view;

import java.awt.FontFormatException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
	
	public static void main(String[] args) throws FontFormatException, Exception {
		
		
		SessionFactory factory = new Configuration()
		.configure().buildSessionFactory();
		
		
		Sales sales = new Sales();
	}

}
