package br.com.waterexpress.model;

import org.joda.time.DateTime;

import br.com.waterexpress.enums.PaymentMethods;
import br.com.waterexpress.enums.SaleStatus;

public class Sale {

	private int id;
	private Client client;
	private Product product;
	private int quantity;
	private PaymentMethods paymentMethods;
	private double totalValue;
	private DateTime date;
	private SaleStatus status;

	public Sale() {
		super();
		date = DateTime.now();
		status = SaleStatus.Processing;
		
	}

	public Sale(Client client, Product products, int quantity, PaymentMethods paymentMethods) {
		
		this.client = client;
		this.product = products;
		this.quantity = quantity;
		this.paymentMethods = paymentMethods;
		this.totalValue = totalValue();
		date = DateTime.now();
		status = SaleStatus.Processing;

	}

	public double totalValue() {
		
		return product.getPrice() * quantity;
	}
	public static double totalValue( double price, int quantity) {
		
		return price * quantity;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("------------------------------------------------------------------\n");
		sb.append("Id: " + id);
		sb.append("\nCliente: " + client);
		sb.append("\nProduct: " + product);
		sb.append(" | Quant: " + quantity);
		sb.append("\nTotal: R$ " + totalValue);
		sb.append(" | " + paymentMethods);
		sb.append(" | " + status);
		sb.append("\n------------------------------------------------------------------");
		
		return sb.toString();
	}

	// PaymentMethods's get/set
	public PaymentMethods getPaymentMethods() {
		
		return paymentMethods;
	}

	public void setPaymentMethods(PaymentMethods paymentMethods) {
		
		this.paymentMethods = paymentMethods;
	}

	// Product's get/set
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// Status's get/set
	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

	// Id's get/set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// TotalValue's get/set
	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	// Client's get/set
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	// Quantity's get/set
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// Date's get/set
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

}