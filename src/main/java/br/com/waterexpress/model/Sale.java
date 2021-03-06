package br.com.waterexpress.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.waterexpress.enums.PaymentMethod;
import br.com.waterexpress.enums.SaleStatus;

@Entity
@Table(name = "Sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_sale")
	private int id;

	@ManyToOne
	private Client client;

	@OneToMany(fetch = FetchType.EAGER)
	private List<OrderItem> items = new ArrayList<OrderItem>();

	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	private LocalDateTime date;

	@Enumerated(EnumType.STRING)
	private SaleStatus status;

	public Sale() {

		date = LocalDateTime.now();
		status = SaleStatus.PROCESSING;

	}

	public Sale(Client client, List<OrderItem> itens, PaymentMethod paymentMethods) {

		this.client = client;
		this.items = itens;
		this.paymentMethod = paymentMethods;
		date = LocalDateTime.now();
		status = SaleStatus.PROCESSING;
	}

	public double totalValue() {

		double total = 0;

		for (OrderItem item : items) {
			total += item.getProduct().getPrice() * item.getQuantity();
		}

		return total;
	}
	
	public void indexItem() {
		int index = 0;
		for(OrderItem item: items) {
			System.out.println("#"+index + ", " +item );
			index++;
		}
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("******************************************************************\n");
		sb.append("Id: " + id);
		sb.append("\nCliente: " + client);
		sb.append("\nITENS:");
		for (OrderItem item : items) {
			sb.append("\n------------------------------------------------------------------\n");
			sb.append(item);
		}
		sb.append("\n------------------------------------------------------------------");
		sb.append("\nTotal: R$ " + String.format("$%,.2f", totalValue()));
		sb.append(" | " + paymentMethod);
		sb.append(" | " + status);
		sb.append("\n******************************************************************");

		return sb.toString();
	}

	// PaymentMethods's get/set
	public PaymentMethod getPaymentMethods() {

		return paymentMethod;
	}

	public void setPaymentMethods(PaymentMethod paymentMethods) {

		this.paymentMethod = paymentMethods;
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

	// Client's get/set
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	// Date's get/set
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}