package br.com.waterexpress.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Sale;

public class SaleDAO implements Operacoes<Sale>{
	
	private List<Sale> product = new ArrayList<Sale>();
	private SessionFactory sessionFactory;
	
	public SaleDAO() {
		sessionFactory = new Configuration()
				.configure().buildSessionFactory();
	}
	
	
	@Override
	public void insert(Sale register) {
		Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  session.save(register);
		  session.getTransaction().commit();
		  session.close();
		
	}

	@Override
	public List<Sale> allList() {
		
			List<Sale> result = new ArrayList<Sale>();		
			Session  session = sessionFactory.openSession();
			//Consulta de uma query, listando o nome "NomeSale"
			//result = session.createQuery("from Client where Nome LIKE '%NomeSale%'").list();
			session.close();
			return result;
	}

	@Override
	public void update(Sale register) {
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
		Sale sale = session.get(Sale.class, id);
		session.delete(sale);
		session.getTransaction().commit();
		session.close();
	}

}
