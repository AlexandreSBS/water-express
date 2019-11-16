package br.com.waterexpress.controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import br.com.waterexpress.enums.Brands;
import br.com.waterexpress.enums.PaymentMethods;
import br.com.waterexpress.enums.SaleStatus;
import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Sale;

public class SaleController implements Operacoes<Sale>{

	private static SaleController instance;
	public ProductController productCtrl;
	private static Duration timeLimit = Duration.standardMinutes(1);
	private List<Sale> sales = new ArrayList<Sale>();
	private int id = 1;

	private SaleController() {

		this.productCtrl = ProductController.getProductController();
	}

	public static SaleController getSaleController() {

		if (instance == null)

			instance = new SaleController();

		return instance;
	}



	public boolean onLimitTime(Sale sale) {

		Duration duration = new Duration(sale.getDate(), DateTime.now());

		return (duration.getMillis() < timeLimit.getMillis()) ? true : false;
	}

	public List<Sale> list() {

		List<Sale> listAll = new ArrayList<Sale>();

		if (sales != null) {

			for (Sale sale : sales) {

				listAll.add(sale);
			}

			return listAll;

		} else {

			return listAll = null;
		}

	}

	public List<Sale> list(PaymentMethods pm) {

		List<Sale> listPayment = new ArrayList<Sale>();

		if (sales != null) {

			for (Sale sale : sales) {

				if (sale.getPaymentMethods() == pm) {

					listPayment.add(sale);
				}
			}

			return listPayment;
		} else {

			return listPayment = null;
		}
	}

	public List<Sale> list(Brand brand) {

		List<Sale> saless = new ArrayList<Sale>();

		if (sales != null) {

			for (Sale sale : sales) {

				if (sale.getProduct().getBrand() == brand) {

					saless.add(sale);
				}
			}

			return saless;
		} else {

			return saless = null;
		}
	}

	public List<Brands> listBrands() {

		List<Brands> brands = new ArrayList<Brands>(EnumSet.allOf(Brands.class));

		return brands;
	}

	public Brand getBrandByInt(int id) throws SaleException {

		List<Brands> brands = new ArrayList<Brands>(EnumSet.allOf(Brands.class));

		Brands brand0 = null;

		for (Brands brand : brands) {
			if (brand.ordinal() == (id - 1)) {
				brand0 = brand;
			}
		}

		if (brand0 != null) {
			return new Brand();
		} else {
			throw new SaleException("Id inválido!");
		}
	}

	public List<Sale> noPostedSales() {

		List<Sale> noPosteds = new ArrayList<Sale>();

		for (Sale sale : sales) {
			
			if (sale.getStatus() != SaleStatus.POSTED) {
				noPosteds.add(sale);
			}
		}

		return noPosteds; // TODO A MUDANÇA FEITA AQUI QUEBRA NA CHAMADA DA VIEW
	}

	public void changeToPosted(List<Sale> noPosteds) {
		
		for (Sale sale : noPosteds) {
			
			sale.setStatus(SaleStatus.POSTED);
		}

	}

	@Override
	public void insert(Sale register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sale getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sale> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Sale register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
