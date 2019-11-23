package br.com.waterexpress.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Client;

public class ClientDAO implements Operacoes<Client> {

	private List<Client> client = new ArrayList<Client>();
	private SessionFactory sessionFactory;
	private static ClientDAO instance;
	
	private ClientDAO() {
		sessionFactory = FactorySession.getFactorySession().getSessionFactory();
	}
	
	public static ClientDAO getClient() {
		if(instance == null)
			instance = new ClientDAO();
		
		return instance;
	}

	@Override
	public void insert(Client register) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(register);
		session.getTransaction().commit();
		session.close();

	}
	
	public Client getById(int id) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();		
		
		Client client = session.get(Client.class, id);		
		
		session.getTransaction().commit();
		session.close();
	
		return client;
				
	}

	public List<Client> listAll() {

		List<Client> result = new ArrayList<Client>();
		Session session = sessionFactory.openSession();	
		
		session.beginTransaction();		
		
		result = session.createQuery("from Client").list();
		
		session.close();
		
		return result;
	}

	public void update(Client register) {

		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.saveOrUpdate(register);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(int id) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		Client client = session.get(Client.class, id);
		
		session.delete(client);
		session.getTransaction().commit();
		session.close();

	}

}

