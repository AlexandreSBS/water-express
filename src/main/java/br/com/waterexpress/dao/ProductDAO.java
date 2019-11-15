package br.com.waterexpress.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Product;

public class ProductDAO implements Operacoes<Product> {

	private List<Product> product = new ArrayList<Product>();
	private SessionFactory sessionFactory;

	public ProductDAO() {
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@Override
	public void insert(Product register) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(register);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Product> allList() {
		List<Product> result = new ArrayList<Product>();
		Session session = sessionFactory.openSession();
		// Consulta de uma query, listando o nome "NomeProduct"
		// result = session.createQuery("from Client where Nome LIKE
		// '%NomeProduct%'").list();
		session.close();
		return result;

	}

	@Override
	public void update(Product register) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.saveOrUpdate(register);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Product product = session.get(Product.class, id);
		session.delete(product);
		session.getTransaction().commit();
		session.close();

	}

}
