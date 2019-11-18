package br.com.waterexpress.view;

import java.awt.FontFormatException;
import java.util.List;
import java.util.Scanner;

import br.com.waterexpress.controller.Facade;
import br.com.waterexpress.controller.SaleController;
import br.com.waterexpress.enums.PaymentMethod;
import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Client;
import br.com.waterexpress.model.Product;
import br.com.waterexpress.model.Sale;

public class Sales {

	private SaleController saleCtrl;
	private Facade facade;
	Scanner reader = new Scanner(System.in);

	public Sales() {

		facade = Facade.getFacade();

		section();
	}

	public void section() {

		printHome();

		System.out.println("Sessão encerrada");
	}

	public void printHome() {

		int homeOptions = 0;

		while (homeOptions != 6) {

			try {
				System.out.println();
				System.out.println("******  Water Express  ******");
				System.out.println("| 1: Executar venda         |");
				System.out.println("| 2: Editar venda           |");
				System.out.println("| 3: Cancelar venda         |");
				System.out.println("| 4: Registro das vendas    |");
				System.out.println("| 5: Finalizar os pedidos   |");
				System.out.println("| 6: Fechar o Programa      |");
				System.out.println("*****************************");
				System.out.println();
				System.out.print("Opção: ");
				try {

					homeOptions = reader.nextInt();

				} catch (Exception e) {

					System.out.println("++++++++++++++++++++++++++");
					System.out.println("|          ERRO!!!       |");
					System.out.println("| Insira Um Número 1 - 6 |");
					System.out.println("++++++++++++++++++++++++++");
					System.out.println();

				} finally {

					reader.nextLine();
				}

				switch (homeOptions) {

				case 1:
					newSale();
					homeOptions = 0;
					break;
				case 2:
					saleEdit();
					homeOptions = 0;
					break;
				case 3:
					saleCancellation();
					homeOptions = 0;
					break;
				case 4:
					salesList();
					homeOptions = 0;
					break;
				case 5:
					printCompleteOrders();
					homeOptions = 0;
					break;
				default:
					System.out.println("++++++++++++++++++++++++++");
					System.out.println("|          ERRO!!!       |");
					System.out.println("| Insira Um Número 1 - 6 |");
					System.out.println("++++++++++++++++++++++++++");
					System.out.println();
					break;
				}
				// TODO Clear method
			} catch (Exception e) {
				// TODO implementar a exception
			}
		}
	}

	public void newSale() {

		System.out.println("*********************************");
		System.out.println("*******    Nova Venda    ********");
		System.out.println("*********************************");
		System.out.println();

		try {
			facade.saleInsert(sale());

			System.out.println("*********************************");
			System.out.println("******  Compra Registrada  ******");
			System.out.println("*********************************");
			System.out.println();

		} catch (Exception e) {

			System.out.println();
			System.out.println("*********************************");
			System.out.println("Erro: " + e.getMessage());
			System.out.println("*********************************");
		}
	}

	public void saleEdit() {

		System.out.println("******    Edit Sale    ******");
		System.out.println();

		List<Sale> noPosted = facade.saleListProcessingSales();

		if (noPosted != null) {
			System.out.println("Selecione a Venda (ID):");
			
			try {
				int id = reader.nextInt();
			} catch (Exception e) {
				
				System.out.println("++++++++++++++++++++++++++");
				System.out.println("|         ERRO!!!        |");
				System.out.println("|     Insira Um Núme     |");
				System.out.println("++++++++++++++++++++++++++");
				System.out.println();
			}
			
			reader.nextLine();

			Sale sale = sale();

			// saleCtrl.updateSaleById(id, sale, noPosted);
		} else {
			System.out.println("Sem vendas com entrega pendente");
			System.out.println();
		}
	}

	public void saleCancellation() {

		System.out.println("*********************************");
		System.out.println("***** Cancelamento de venda *****");
		System.out.println("*********************************");
		System.out.println();

		List<Sale> sales = saleCtrl.listAll();

		if (sales != null) {
			for (Sale sale : sales) {

				System.out.println(sale);
			}
			System.out.print("Selecione venda (ID):");
			int id = reader.nextInt();

			try {

				facade.saleCancel(saleCtrl.getById(id));

			} catch (SaleException e) {

				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("Não há vendas registradas");
		}

		reader.nextLine();
	}

	public void salesList() throws SaleException {

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

		List<Sale> ProcessingSales = saleCtrl.listProcessingSales();

		if (ProcessingSales != null) {

			System.out.println("Colocar para entregar (S ou N)?");

			String resp = reader.next().toUpperCase();

			switch (resp) {

			case "S":

				saleCtrl.changeToPosted(ProcessingSales);

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

	public Sale sale() {

		Client client = clientResgister();

		Product product;

		try {

			product = productResgister();

		} catch (SaleException e) {

			System.out.println(e.getMessage());
		}

		int quant;
		try {
			quant = quantityregister();
		} catch (SaleException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		System.out.println("*********************************");
		System.out.println("Valor Da Compra: R$" + Sale.totalValue(product.getPrice(), quant));
		System.out.println("*********************************");

		PaymentMethod pm;
		try {
			pm = pmRegister();
		} catch (SaleException e) {

			e.printStackTrace();
		}

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

		// saleCtrl.productCtrl.getProducts();

		System.out.print("Selecione o produto (ID): ");
		int id = reader.nextInt();

		// Product product = saleCtrl.productCtrl.getProduct(id);

		return null;

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

	public PaymentMethod pmRegister() throws SaleException {

		System.out.println("Método de Pagamento");
		System.out.println("1 - Dinheiro");
		System.out.println("2 - Cartão");

		try {

			int pm = reader.nextInt();

			switch (pm) {
			case 1:
				return PaymentMethod.DINHEIRO;
			case 2:
				return PaymentMethod.CARTAO;
			default:
				throw new SaleException("A opção " + pm + " não existe!");
			}
		} catch (Exception e) { // TODO Colocar erro específico

			System.out.println("Erro: " + e.getMessage());
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

		List<Sale> list = saleCtrl.listAll();

		if (list != null) {

			for (Sale sales : list) {

				System.out.println(sales);
			}
		} else {
			System.out.println("Sem compras registradas kkk");
		}
	}

	public void listPayment() throws SaleException {

		List<Sale> salePayment = saleCtrl.listAll(pmRegister());

		if (salePayment != null) {

			for (Sale sales : salePayment) {

				System.out.println(sales);
			}
		} else {
			System.out.println("Sem registro de vendas.");
		}
	}

	/*
	 * public void listAllBrands() {
	 * 
	 * List<Brands> brandList = saleCtrl.listBrands();
	 * 
	 * for (Brand brand : brandList) {
	 * 
	 * System.out.println((brand.ordinal() + 1) + " " + brand); } }
	 */

	public void listBrands() throws SaleException {

		List<Sale> saleBrands = saleCtrl.listAll(brandRegister());

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
