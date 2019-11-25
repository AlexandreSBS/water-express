package br.com.waterexpress.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.waterexpress.enums.PaymentMethod;
import br.com.waterexpress.interfaces.OperacoesBase;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Sale;

public class SaleDAO implements OperacoesBase<Sale> {

	private SessionFactory sessionFactory;
	private static SaleDAO instance;
	private EntityManager entityManager; 
	private SaleDAO() {
		sessionFactory = FactorySession.getFactorySession().getSessionFactory();
	}

	public static SaleDAO getSaleDAO() {
		if (instance == null)
			instance = new SaleDAO();

		return instance;
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
	public List<Sale> listAll() {

		List<Sale> result = new ArrayList<Sale>();
		Session session = sessionFactory.openSession();

		result = session.createQuery("from Sale").list();

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

	@Override
	public Sale getById(int id) {

		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Sale sale = session.get(Sale.class, id);

		session.getTransaction().commit();
		session.close();

		return sale;
	}

	public List<Sale> listProcessingSales() {

		List<Sale> result = new ArrayList<Sale>();
		Session session = sessionFactory.openSession();

		result = session.createQuery("from Sale where status = 'PROCESSING'").list();

		session.close();

		return result;

	}

	
	public List<Sale> listByBrand(Brand brand) {

		List<Sale> result = new ArrayList<Sale>();
		Session session = sessionFactory.openSession();

		String queryString = "SELECT distinct(Sale.pk_sale)\r\n" + 
				"		,Sale.date\r\n" + 
				"		,Sale.paymentMethod\r\n" + 
				"		,Sale.status\r\n" + 
				"		,Sale.client_pk_client\r\n" + 
				"FROM	Sale\r\n" + 
				"INNER	JOIN Sale_OrderItem \r\n" + 
				"ON		Sale.pk_sale = Sale_OrderItem.Sale_pk_sale\r\n" + 
				"INNER	JOIN OrderItem \r\n" + 
				"ON		Sale_OrderItem.items_pk_order_item = OrderItem.pk_order_item\r\n" + 
				"INNER	JOIN Product \r\n" + 
				"ON		OrderItem.product_pk_productS = Product.pk_productS\r\n" + 
				"where	Product.brand_pk_brand =" + brand.getId();
		
		result = session.createNativeQuery(queryString, Sale.class).list();
		
		session.close();

		return result;
	}

	public List<Sale> listByPaymentMethod(PaymentMethod pm) {
		
		List<Sale> result = new ArrayList<Sale>();
		Session session = sessionFactory.openSession();

		String querystring = "from Sale where PaymentMethod = '" + pm.name() + "'";
		
		result = session.createQuery(querystring).list();

		session.close();

		return result;
		
	}

}
