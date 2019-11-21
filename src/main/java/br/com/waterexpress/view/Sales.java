package br.com.waterexpress.view;

import java.awt.FontFormatException;
import java.util.List;
import java.util.Scanner;

import br.com.waterexpress.controller.Facade;
import br.com.waterexpress.controller.SaleController;
import br.com.waterexpress.controller.Validator;
import br.com.waterexpress.enums.PaymentMethod;
import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Client;
import br.com.waterexpress.model.Product;
import br.com.waterexpress.model.Sale;

public class Sales {
	private Validator validator = Validator.getValidator();
	private SaleController saleCtrl;
	private Facade facade;
	Scanner reader = new Scanner(System.in);

	public Sales() {

		facade = Facade.getFacade();

		section();
	}

	public void section() {

		printHome();

		System.out.println("Sess�o encerrada");
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
				System.out.print("Op��o: ");
				try {

					homeOptions = reader.nextInt();

				} catch (Exception e) {

					System.out.println("++++++++++++++++++++++++++");
					System.out.println("|          ERRO!!!       |");
					System.out.println("| Insira Um N�mero 1 - 6 |");
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
					System.out.println("| Insira Um N�mero 1 - 6 |");
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

		int id = 0;

		System.out.println("*********************************");
		System.out.println("********    Edit Sale    ********");
		System.out.println("*********************************");
		System.out.println();

		List<Sale> noPosted = facade.saleListProcessingSales();

		Sales.listar(noPosted);

		if (noPosted != null) {
			System.out.println("Selecione a Venda (ID):");

			try {
				id = reader.nextInt();
			} catch (Exception e) {

				getIntMessageError();
			}

			reader.nextLine();

			facade.saleUpdate(facade.saleGetById(id));

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

		List<Sale> sales = facade.saleListProcessingSales();

		if (sales != null) {

			Sales.listar(sales);

			System.out.print("Selecione venda (ID):");
			int id = reader.nextInt();

			try {

				facade.saleCancel(facade.saleGetById(id));

			} catch (SaleException e) {

				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("N�o h� vendas registradas");
		}

		reader.nextLine();
	}

	public void salesList() throws SaleException {

		System.out.println("*********** Lista de venda ***********");
		System.out.println(" 1: Sem Filtro                       |");
		System.out.println(" 2: Filtrar por m�todo de pagamento  |");
		System.out.println(" 3: Filtrar por marca                |");
		System.out.println("**************************************");

		int option = reader.nextInt();

		switch (option) {
		case 1:
			try {
				
				listar(facade.saleListAll());
				
			} catch (Exception e) {

				System.out.println(e.getMessage());
			}
			break;
		case 2:
			PaymentMethod pm = pmRegister();
			listar(facade.saleListByPaymentMethod(pm));
			break;
		case 3:
			// TODO t� quebrado (por enquanto kkk)
			break;
		default:
			System.out.println("Op��o Invalida");
		}
	}

	public void printCompleteOrders() {

		List<Sale> ProcessingSales = facade.saleListProcessingSales();

		if (ProcessingSales != null) {

			System.out.println("Colocar para entregar (S ou N)?");

			String resp = reader.next().toUpperCase();

			switch (resp) {

			case "S":

				facade.saleChangeToPosted(ProcessingSales);

				System.out.println("******************************");
				System.out.println("| A��o realizada com sucesso |");
				System.out.println("******************************");
				System.out.println();

				break;
			case "N":

				break;
			default:
				System.out.println("Op��o Inv�lida");
				break;
			}
		} else {

			System.out.println("Sem vendas com entrega pendente");
		}
	}

	public Sale sale() {

		Client client = clientResgister();
		facade.clientInsert(client);
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
		// System.out.println("Valor Da Compra: R$" + Sale.Value(product.getPrice(),
		// quant));
		System.out.println("*********************************");

		PaymentMethod pm;
		try {

			pm = pmRegister();

		} catch (SaleException e) {

			e.printStackTrace();
		}

		// Sale sale = new Sale(client, product, quant, pm);

		return null;

	}

	public Client clientResgister() {
		String nome;
		String telefone;
		String endereco;
		boolean n = true;
		
		do {
		System.out.print("Nome: ");
		nome = reader.nextLine();
		n = validator.validarNome(nome);
		}while(n==false);
		
		do {
		System.out.print("Telefone: ");
		telefone = reader.next();
		n = validator.validarnumero(telefone);
		}while(n==false);
		
		do {
		System.out.print("Endere�o: ");
		reader.nextLine();
		endereco = reader.nextLine();
		n = validator.validarEndereco(endereco);
		}while(n==false);
		
		Client client = new Client(nome, endereco, telefone);

		return client;
	}

	public Product productResgister() throws SaleException {

		System.out.println();
		System.out.println("***** Selecione Um Produto *****");
		System.out.println();

		Sales.listar(facade.productListAll());

		System.out.print("Selecione o produto (ID): ");

		int id = reader.nextInt();

		Product product = facade.productGetById(id);

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

	public PaymentMethod pmRegister() throws SaleException {
		int pm = 0;

		System.out.println("*********************************");
		System.out.println("*      M�TODO DE PAGAMENTO      *");
		System.out.println("* 1 - Dinheiro                  *");
		System.out.println("* 2 - Cart�o                    *");
		System.out.println("*********************************");
		try {

			pm = reader.nextInt();

		} catch (Exception e) {

			getIntMessageError();
		}

		switch (pm) {
		case 1:
			return PaymentMethod.DINHEIRO;
		case 2:
			return PaymentMethod.CARTAO;
		default:
			throw new SaleException("A op��o " + pm + " n�o existe!");
		}

	}

	public Brand brandRegister() throws SaleException {

		System.out.println("********** Marcas **********");

		Sales.listar(facade.brandListAll());

		System.out.println();
		System.out.print("Selecione a marca (ID): ");
		int id = reader.nextInt();

		return facade.brandGetById(id);
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

	public static <T> void listar(List<T> list) {

		for (T item : list) {
			System.out.println(item + "\n");
		}
	}

	public void getIntMessageError() {

		System.out.println("++++++++++++++++++++++++++");
		System.out.println("|         ERRO!!!        |");
		System.out.println("|    Insira Um N�mero    |");
		System.out.println("++++++++++++++++++++++++++");
		System.out.println();
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
