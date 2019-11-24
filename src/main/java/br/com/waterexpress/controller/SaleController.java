 package br.com.waterexpress.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.waterexpress.dao.SaleDAO;
import br.com.waterexpress.enums.PaymentMethod;
import br.com.waterexpress.enums.SaleStatus;
import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Sale;

public class SaleController implements Operacoes<Sale> {

	private static SaleController instance;
	public ProductController productCtrl;
	private SaleDAO dao;
	private static Duration timeLimit = Duration.ofMinutes(3);

	private SaleController() {

		this.productCtrl = ProductController.getProductController();
		dao = SaleDAO.getSaleDAO();
	}

	public static SaleController getSaleController() {

		if (instance == null)

			instance = new SaleController();

		return instance;
	}

	public void insert(Sale register) {

		dao.insert(register);
	}

	public Sale getById(int id) {

		return dao.getById(id);
	}

	public List<Sale> listAll() throws Exception {

		List<Sale> sales = dao.listAll();
		
		if (!sales.isEmpty()) {
			return sales;
		}
		else {
			throw new Exception("Não existe compras registradas");
		}
		
	}

	public List<Sale> listByPaymentMethod(PaymentMethod pm) {

		return dao.listByPaymentMethod(pm);
	}

	public List<Sale> listByBrand(Brand brand) {

		return dao.listByBrand(brand);
	}

	public List<Sale> listProcessingSales() {

		return dao.listProcessingSales();
	}

	public void changeToPosted(List<Sale> noPosteds) {

		for (Sale sale : noPosteds) {

			sale.setStatus(SaleStatus.POSTED);

			dao.update(sale);
		}

	}

	public void cancel(Sale sale) throws SaleException {

		if (onLimitTime(sale)) {

			sale.setStatus(SaleStatus.CANCELLED);

			dao.update(sale);
		} else {

			throw new SaleException("O tempo para cancelar a venda expirou");
		}
	}

	public void update(Sale register) {

		if (register.getStatus() == SaleStatus.PROCESSING) {

			dao.update(register);
		}
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public boolean onLimitTime(Sale sale) {

		Duration duration = Duration.between(sale.getDate(), LocalDateTime.now());

		return (duration.toMillis() < timeLimit.toMillis()) ? true : false;
	}

}
