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

	public ClientDAO() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
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
		// TODO Auto-generated method stub
		return null;
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
