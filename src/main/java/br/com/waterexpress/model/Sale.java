package br.com.waterexpress.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	private int id;
	
	@ManyToOne
	private Client client;
	
	@OneToMany
	private List<Item> items = new ArrayList<Item>();
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	private double totalValue;

	private LocalDateTime date;
	
	@Enumerated(EnumType.STRING)
	private SaleStatus status;

	public Sale() {
		
		date = LocalDateTime.now();
		status = SaleStatus.PROCESSING;
		
	}

	public Sale(Client client, List<Item> itens, PaymentMethod paymentMethods) {
		
		this.client = client;
		this.items = itens;
		this.paymentMethod = paymentMethods;
		this.totalValue = totalValue();
		date = LocalDateTime.now();
		status = SaleStatus.PROCESSING;
	}

	public double totalValue() {
		
		double total = 0;
		
		for (Item item : items) {
			total += item.getProduct().getPrice()* item.getQuantity();
		}
		
		return total;
	}


	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("------------------------------------------------------------------\n");
		sb.append("Id: " + id);
		sb.append("\nCliente: " + client);
		sb.append("\nItens:");
		for (Item item : items) {
			sb.append("------------------------------------------------------------------\n");
			sb.append(item);
		}
		sb.append("------------------------------------------------------------------\n");
		sb.append("\nTotal: R$ " + totalValue);
		sb.append(" | " + paymentMethod);
		sb.append(" | " + status);
		sb.append("\n------------------------------------------------------------------");
		
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

	// Date's get/set
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}