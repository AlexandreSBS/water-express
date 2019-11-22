package br.com.waterexpress.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.waterexpress.controller.Facade;
import br.com.waterexpress.enums.PaymentMethod;
import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Client;
import br.com.waterexpress.model.OrderItem;
import br.com.waterexpress.model.Product;
import br.com.waterexpress.model.Sale;
import br.com.waterexpress.util.Print;
import br.com.waterexpress.util.Validator;

public class Sales {

	private Validator validator = Validator.getValidator();
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
				System.out.println("********  WATER EXPRESS  ********");
				System.out.println("| 1: Executar venda             |");
				System.out.println("| 2: Editar venda               |");
				System.out.println("| 3: Cancelar venda             |");
				System.out.println("| 4: Registro das vendas        |");
				System.out.println("| 5: Finalizar os pedidos       |");
				System.out.println("| 6: Fechar o Programa          |");
				System.out.println("*********************************");
				System.out.println();
				System.out.print("Opção: ");
				try {

					homeOptions = reader.nextInt();

				} catch (InputMismatchException e) {

					Print.getIntMessageError(1, 6);

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

					Print.getIntMessageError(1, 6);
					break;
				}

			} catch (Exception e) {

				System.out.println(e.getMessage());
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

		Print.list(noPosted);

		if (noPosted != null) {
			System.out.println("Selecione a Venda (ID):");

			try {
				id = reader.nextInt();
			} catch (Exception e) {

				Print.getIntMessageError();
			}

			reader.nextLine();

			facade.saleUpdate(facade.saleGetById(id));

		} else {
			System.out.println("Não Há vendas com entrega pendente");
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

			Print.list(sales);

			System.out.print("Selecione venda (ID):");
			int id = reader.nextInt();

			try {

				facade.saleCancel(facade.saleGetById(id));

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
			try {

				Print.list(facade.saleListAll());

			} catch (Exception e) {

				System.out.println(e.getMessage());
			}
			break;
		case 2:
			PaymentMethod pm = registerPaymentMethod();
			Print.list(facade.saleListByPaymentMethod(pm));
			break;
		case 3:
			// TODO tá quebrado (por enquanto kkk)
			break;
		default:
			System.out.println("Opção Invalida");
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

		Client client = resgisterClient();
		List<OrderItem> itens = registerItens();
		PaymentMethod method = null;

		try {

			method = registerPaymentMethod();

		} catch (SaleException e) {

			System.out.println(e.getMessage());
		}

		Sale sale = new Sale(client, itens, method);

		System.out.println("*********************************");
		System.out.println("Valor ToTal: " + sale.getTotalValue());
		System.out.println("*********************************");

		return sale;

	}

	private List<OrderItem> registerItens() {

		List<OrderItem> itens = new ArrayList<OrderItem>();
		String option = null;

		do {
			itens.add(registerOrderItem());

			System.out.print("Inserir mais um produto (S ou N)?");
			option = reader.nextLine();

		} while (option.equalsIgnoreCase("S"));

		
		
		return itens;
	}

	private OrderItem registerOrderItem() {
		
		int quantity = 0;
		Product product = resgisterProduct();
		
		try {
			quantity = registerQuantity();
			
		} catch (SaleException e) {
			
			System.out.println(e.getMessage());
		}
		
		OrderItem orderItem = new OrderItem(quantity, product);
		facade.OrderItemInsert(orderItem);
		
		return orderItem;
	}

	public Client resgisterClient() {

		String nome;
		String telefone;
		String endereco;

		do {
			System.out.print("Nome: ");

			nome = reader.nextLine();

		} while (!validator.validarNome(nome));

		do {
			System.out.print("Telefone: ");

			telefone = reader.next();

		} while (!validator.validarnumero(telefone));

		do {
			System.out.print("Endereço: ");
			reader.nextLine();

			endereco = reader.nextLine();

		} while (!validator.validarEndereco(endereco));

		Client client = new Client(nome, endereco, telefone);

		facade.clientInsert(client);

		return client;
	}

	public Product resgisterProduct() {

		System.out.println();
		System.out.println("*********************************");
		System.out.println("****** Selecione o Produto ******");
		System.out.println("*********************************");
		System.out.println();

		Print.list(facade.productListAll());

		System.out.print("Selecione o produto (ID): ");

		int id = reader.nextInt();

		Product product = facade.productGetById(id);

		return product;
	}

	public int registerQuantity() throws SaleException {
		int quantity = 0;
		
		System.out.print("Quantidade: ");
		try {
			quantity = reader.nextInt();
			
		}catch (InputMismatchException e) {
			
			Print.getIntMessageError();
		}
		
		if (quantity > 0) {

			return quantity;

		} else {

			throw new SaleException("VALOR NEGATIVO!");
		}
	}

	public PaymentMethod registerPaymentMethod() throws SaleException {
		int option = 0;

		System.out.println("*********************************");
		System.out.println("*      MÉTODO DE PAGAMENTO      *");
		System.out.println("* 1 - Dinheiro                  *");
		System.out.println("* 2 - Cartão                    *");
		System.out.println("*********************************");

		try {
			option = reader.nextInt();

		} catch (Exception e) {

			Print.getIntMessageError();
		}

		switch (option) {
		case 1:
			return PaymentMethod.DINHEIRO;
		case 2:
			return PaymentMethod.CARTAO;
		default:
			throw new SaleException("A opção " + option + " não existe!");
		}

	}

	public Brand brandRegister() throws SaleException {

		System.out.println("*********************************");
		System.out.println("***********  Marcas  ************");
		System.out.println("*********************************");

		Print.list(facade.brandListAll());

		System.out.println();

		System.out.print("Selecione a marca (ID): ");
		int id = reader.nextInt();
		System.out.println();

		return facade.brandGetById(id);
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
