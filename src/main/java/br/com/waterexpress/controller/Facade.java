package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.enums.PaymentMethod;
import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Client;
import br.com.waterexpress.model.Item;
import br.com.waterexpress.model.Product;
import br.com.waterexpress.model.Sale;

public class Facade {

	private static Facade instance;
	private SaleController saleController;
	private ClientController clientController;
	private ProductController productController;
	private BrandController brandController;
	private ItemController itemController;

	private Facade() {

		saleController = SaleController.getSaleController();
		clientController = ClientController.getClientController();
		productController = ProductController.getProductController();
		brandController = BrandController.getBrandDAO();
		itemController = ItemController.getItemController();
	}

	public static Facade getFacade() {
		if (instance == null)
			instance = new Facade();

		return instance;
	}

	public void saleInsert(Sale register) {

		saleController.insert(register);
	}

	public Sale saleGetById(int id) {

		return saleController.getById(id);
	}

	public List<Sale> saleListAll() throws Exception {

		return saleController.listAll();
	}

	public List<Sale> saleListByPaymentMethod(PaymentMethod pm) {

		return saleController.listByPaymentMethod(pm);
	}

	public List<Sale> saleListByBrand(Brand brand) {

		return saleController.listByBrand(brand);
	}

	public List<Sale> saleListProcessingSales() {

		return saleController.listProcessingSales();
	}

	public void saleChangeToPosted(List<Sale> noPosteds) {

		saleController.changeToPosted(noPosteds);
	}

	public void saleCancel(Sale sale) throws SaleException {

		saleController.cancel(sale);
	}

	public void saleUpdate(Sale register) {

		saleController.update(register);
	}

	public void saleDelete(int id) {

		saleController.delete(id);
	}

	public void clientInsert(Client register) {

		clientController.insert(register);
	}

	public Client clientGetById(int id) {

		return clientController.getById(id);
	}

	public List<Client> clientListAll() {

		return clientController.listAll();
	}

	public void clientUpdate(Client register) {

		clientController.update(register);
	}

	public void clientDelete(int id) {

		clientController.delete(id);
	}

	public void productInsert(Product register) {
		
		productController.insert(register);
	}

	public Product productGetById(int id) {
		
		return productController.getById(id);
	}	
	
	public List<Product> productListAll() {
		
		return productController.listAll();
	}
	
	public void productUpdate(Product register) {
		
		productController.update(register);
	}

	public void productDelete(int id) {
		
		productController.delete(id);
	}

	public void brandInsert(Brand register) {
		
		brandController.insert(register);
	}

	public Brand brandGetById(int id) {
		
		return brandController.getById(id);
	}

	public List<Brand> brandListAll() {
		
		return brandController.listAll();
	}

	public void brandUpdate(Brand register) {
		
		brandController.update(register);
	}

	public void brandDelete(int id) {
		
		brandController.delete(id);
	}
	
	public void itemInsert(Item register) {
		
		itemController.insert(register);
	}

	public Item itemGetById(int id) {
		
		return itemController.getById(id);
	}

	public List<Item> itemListAll() {
		
		return itemController.listAll();
	}

	public void itemUpdate(Item register) {
		
		itemController.update(register);
	}

	public void itemDelete(int id) {
		
		itemController.delete(id);
	}
}
