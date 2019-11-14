package br.com.waterexpress.view;

import java.awt.FontFormatException;
import java.util.List;
import java.util.Scanner;

import br.com.waterexpress.controller.SaleController;
import br.com.waterexpress.enums.Brands;
import br.com.waterexpress.enums.PaymentMethods;
import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Client;
import br.com.waterexpress.model.Product;
import br.com.waterexpress.model.Sale;

public class Sales {

	private SaleController saleCtrl;
	Scanner reader = new Scanner(System.in);

	public Sales() throws FontFormatException {

		this.saleCtrl = SaleController.getSaleController();

		section();
	}

	public void section() throws FontFormatException {

		printHome();

		System.out.println("Sessão encerrada");

	}

	public void printHome() {

		int homeOptions = 0;

		while (homeOptions != 6) {

			try {
				System.out.println("******  Water Express  ******");
				System.out.println("| 1: Executar venda         |");
				System.out.println("| 2: Editar venda           |");
				System.out.println("| 3: Cancelar venda         |");
				System.out.println("| 4: Registro das vendas    |");
				System.out.println("| 5: Finalizar os pedidos   |");
				System.out.println("| 6: Fechar o Programa      |");
				System.out.println("*****************************");

				try {

					homeOptions = reader.nextInt();

				} catch (Exception e) {

					System.out.println("++++++++++++++++++++++++++");
					System.out.println("|          ERRO!!!         |");
					System.out.println("| Insira Um Número 1 - 6 |");
					System.out.println("++++++++++++++++++++++++++");
					System.out.println();
					
				} finally {

					reader.nextLine();
				}

				switch (homeOptions) {

				case 1:
					printSale();
					homeOptions = 0;
					break;
				case 2:
					printSaleEdit();
					homeOptions = 0;
					break;
				case 3:
					printSaleCancellation();
					homeOptions = 0;
					break;
				case 4:
					PrintSalesList();
					homeOptions = 0;
					break;
				case 5:
					printCompleteOrders();
					homeOptions = 0;
					break;
				default:
					break;
				}
				// TODO Clear method
			} catch (Exception e) {
				// TODO implementar a exception
			}
		}
	}

	public void printSale() throws SaleException, Exception {

		System.out.println("******    Nova Venda    ******");

		saleCtrl.addSale(sale());

		System.out.println("******  Compra Registrada  ******");
		System.out.println();
	}

	public void printSaleEdit() throws Exception {

		System.out.println("******    Edit Sale    ******");
		System.out.println();

		try {
			List<Sale> noPosted = saleCtrl.noPostedSales();

			if (noPosted != null) {
				System.out.println("Selecione a Venda (ID):");
				int id = reader.nextInt();
				reader.nextLine();

				Sale sale = sale();

				saleCtrl.updateSaleById(id, sale, noPosted);
			} else {
				System.out.println("Sem vendas com entrega pendente");
				System.out.println();
			}
		} catch (SaleException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void printSaleCancellation() {

		System.out.println("****** Cancelamento de venda ******");
		saleCtrl.list();

		System.out.print("Selecione venda (ID):");
		int id = reader.nextInt();

		saleCtrl.removeSaleById(id);

		reader.nextLine();
	}

	public void PrintSalesList() throws SaleException {

		System.out.println("*********** Lista de venda ***********");
		System.out.println(" 1: Sem Filtro                       |");
		System.out.println(" 2: Filtrar por método de pagamento  |");
		System.out.println(" 3: Filtrar por marca                |");
		System.out.println("**************************************");

		int option = reader.nextInt();

		switch (option) {
		case 1:
			listAllPrint();
			break;
		case 2:
			listPayment();
			break;
		case 3:
			listBrands();
			break;
		default:
			System.out.println("Opção Invalida");
		}
	}

	public void printCompleteOrders() {

		List<Sale> noPosteds = saleCtrl.noPostedSales();

		if (noPosteds != null) {

			System.out.println("Colocar para entregar (S ou N)?");

			String resp = reader.next().toUpperCase();

			switch (resp) {

			case "S":

				saleCtrl.changeToPosted(noPosteds);

				System.out.println("******************************");
				System.out.println("| Ação realizada com sucesso |");
				System.out.println("******************************");
				System.out.println();

				break;
			case "N":

				break;
			default:
				System.out.println("Opção Inválida");
				break;
			}
		} else {

			System.out.println("Sem vendas com entrega pendente");
		}
	}

	public Sale sale() throws SaleException, Exception {

		Client client = clientResgister();

		Product product = productResgister();

		int quant = quantityregister();

		System.out.println("***************************");
		System.out.println("Valor Da Compra: R$" + Sale.totalValue(product.getPrice(), quant));
		System.out.println("***************************");

		PaymentMethods pm = pmRegister();

		Sale sale = new Sale(client, product, quant, pm);

		return sale;

	}

	public Client clientResgister() {

		Client client = new Client();

		System.out.print("Nome: ");
		client.setName(reader.nextLine());

		System.out.print("Telefone: ");
		client.setPhoneNumber(reader.next());

		System.out.print("Endereço: ");
		reader.nextLine();
		client.setAddress(reader.nextLine());

		return client;
	}

	public Product productResgister() throws SaleException {

		System.out.println();
		System.out.println("***** Selecione Um Produto *****");
		System.out.println();

		saleCtrl.productCtrl.getProducts();

		System.out.print("Selecione o produto (ID): ");
		int id = reader.nextInt();

		Product product = saleCtrl.productCtrl.getProduct(id);

		return product;

	}

	public int quantityregister() throws SaleException {

		System.out.print("Quantidade: ");

		int quantity = reader.nextInt();

		if (quantity > 0) {
			return quantity;
		} else {
			throw new SaleException("VALOR NEGATIVO!");
		}
	}

	public PaymentMethods pmRegister() throws SaleException {

		System.out.println("Método de Pagamento");
		System.out.println("1 - Dinheiro");
		System.out.println("2 - Cartão");

		int pm = reader.nextInt();

		switch (pm) {
		case 1:
			return PaymentMethods.DINHEIRO;
		case 2:
			return PaymentMethods.CARTAO;

		default:
			throw new SaleException("A opção " + pm + " não existe!");
		}
	}

	public Brand brandRegister() throws SaleException {

		System.out.println("********** Marcas **********");
		listAllBrands();

		System.out.println("Selecione a marca (ID): ");
		int id = reader.nextInt();

		return saleCtrl.getBrandByInt(id);
	}

	public void listAllPrint() {

		List<Sale> list = saleCtrl.list();

		if (list != null) {

			for (Sale sales : list) {

				System.out.println(sales);
			}
		} else {
			System.out.println("Sem compras registradas kkk");
		}
	}

	public void listPayment() throws SaleException {

		List<Sale> salePayment = saleCtrl.list(pmRegister());

		if (salePayment != null) {

			for (Sale sales : salePayment) {

				System.out.println(sales);
			}
		} else {
			System.out.println("Sem registro de vendas.");
		}
	}

	public void listAllBrands() {

		List<Brands> brandList = saleCtrl.listBrands();

		for (Brands brand : brandList) {

			System.out.println((brand.ordinal() + 1) + " " + brand);
		}
	}

	public void listBrands() throws SaleException {

		List<Sale> saleBrands = saleCtrl.list(brandRegister());

		if (saleBrands != null) {

			for (Sale sale : saleBrands) {

				System.out.println(sale);
			}
		} else {
			System.out.println("Sem registro de vendas.");
		}
	}

	// works only on cdm
	public static void clearConsole() {

		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}

}
