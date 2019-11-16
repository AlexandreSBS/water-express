 package br.com.waterexpress.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Brand;





public class BrandDAO implements Operacoes<Brand> {
	
	@SuppressWarnings("unused")
	private List<Brand> client = new ArrayList<Brand>();
	private SessionFactory sessionFactory;
	
	public BrandDAO() {
		sessionFactory = new Configuration()
				.configure().buildSessionFactory();
	}
	
	@Override
	public void insert(Brand register) {
		Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(register);
		  		session.getTransaction().commit();
		  		session.close();		
	}

	

	public Brand getById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();		
		Brand brand = session.get(Brand.class, id);		
		session.getTransaction().commit();
		session.close();
		return brand;

	}


	@Override
	public List<Brand> listAll() {
		List<Brand> result = new ArrayList<Brand>();
		Session session = sessionFactory.openSession();
		result = session.createQuery("from Aluno").list();
		session.close();
		return result;
		
	}


	@Override
	public void update(Brand register) {
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
		Brand brand = session.get(Brand.class, id);
		session.delete(brand);
		session.getTransaction().commit();
		session.close();
		
	}


	
}
