package br.com.waterexpress.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

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
	EditSale edit = new EditSale();
	private Validator validator = Validator.getValidator();
	private Facade facade;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

					homeOptions = Integer.parseInt(reader.readLine());

				} catch (NumberFormatException e) {

					Print.getIntMessageError();
					;

				} finally {

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
				case 6:
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
		Sale editedSale = null;
		int id = 0;

		System.out.println("*********************************");
		System.out.println("********    Edit Sale    ********");
		System.out.println("*********************************");
		System.out.println();

		List<Sale> noPosted = facade.saleListProcessingSales();

		Print.list(noPosted);

		if (noPosted != null) {
			while (editedSale == null) {
				System.out.println("Selecione a Venda (ID):");

				try {
					id = Integer.parseInt(reader.readLine());
				} catch (Exception e) {

					Print.getIntMessageError();

				}
				editedSale = editSalePrint(id);
				if (!(editedSale != null)) {
					Print.getIdValidMessege();
				}
			}
			facade.saleUpdate(editedSale);

		} else {
			System.out.println("Não Há vendas com entrega pendente");
			System.out.println();
		}
	}

	public void saleCancellation() {
		int id = 0;
		Sale cancelSale;
		System.out.println("*********************************");
		System.out.println("***** Cancelamento de venda *****");
		System.out.println("*********************************");
		System.out.println();

		List<Sale> sales = facade.saleListProcessingSales();

		if (!sales.isEmpty()) {

			Print.list(sales);

			do {
				System.out.print("Selecione venda (ID):");

				try {
					id = Integer.parseInt(reader.readLine());
				} catch (NumberFormatException e1) {
					Print.getIntMessageError();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cancelSale = facade.saleGetById(id);
				if (cancelSale == null) {
					Print.getIdValidMessege();
				}
			} while (cancelSale == null);
			try {

				facade.saleCancel(cancelSale);
				Print.getCanceledSaleMessege();

			} catch (SaleException e) {

				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("Não há vendas registradas");
		}

	}

	public void salesList() throws SaleException {
		int option = 0;
		do {
			System.out.println("*********** Lista de venda ***********");
			System.out.println(" 1: Sem Filtro                       |");
			System.out.println(" 2: Filtrar por método de pagamento  |");
			System.out.println(" 3: Filtrar por marca                |");
			System.out.println(" 4: Voltar ao menu                   |");
			System.out.println("**************************************");
			System.out.println("Opção: ");

			try {
				option = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e1) {
				Print.getIntMessageError();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (option < 0 || option > 4) {
				Print.getNumberMessageError();
				Print.getMenuinvalidOptionMessege(option);
			}
		} while (option < 1 || option > 4);
		switch (option) {
		case 1:
			try {

				Print.list(facade.saleListAll());

			} catch (Exception e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println();
			}
			break;
		case 2:
			PaymentMethod pm = registerPaymentMethod();
			Print.list(facade.saleListByPaymentMethod(pm));
			break;
		case 3:
			Print.list(facade.saleListByBrand(registerBrand()));
			break;
		case 4:
			break;
		default:
			System.out.println("Opção Invalida");
		}
	}

	public void printCompleteOrders() {

		List<Sale> ProcessingSales = facade.saleListProcessingSales();

		if (ProcessingSales != null) {
			Print.list(ProcessingSales);

			System.out.println("Colocar para entregar (S ou N)?");

			String resp = null;
			try {
				resp = reader.readLine().toUpperCase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			switch (resp) {

			case "S":
				facade.saleChangeToPosted(ProcessingSales);
				System.out.println("*********************************");
				System.out.println("*  Ação realizada com sucesso   *");
				System.out.println("*********************************");
				System.out.println();

				break;
			case "N":

				break;
			default:
				System.out.println();
				System.out.println("******** Opção Inválida! ********");
				System.out.println();
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
			System.out.println();
			System.out.println(e.getMessage());
			System.out.println();
		}

		Sale sale = new Sale(client, itens, method);

		System.out.println("*********************************");
		System.out.println("Valor ToTal: " + sale.totalValue());
		System.out.println("*********************************");
		System.out.println();

		return sale;

	}

	private List<OrderItem> registerItens() {

		List<OrderItem> itens = new ArrayList<OrderItem>();
		String option = null;

		do {
			itens.add(registerOrderItem());

			System.out.print("Inserir mais um produto (S ou N)?");
			try {
				option = reader.readLine();
				System.out.println();
			} catch (IOException e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println();
			}

		} while (option.equalsIgnoreCase("S"));

		return itens;
	}

	public OrderItem registerOrderItem() {

		int quantity = 0;
		Product product = null;

		try {
			product = resgisterProduct();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		quantity = registerQuantity();

		OrderItem orderItem = new OrderItem(quantity, product);
		facade.OrderItemInsert(orderItem);

		return orderItem;
	}

	public Client resgisterClient() {

		String nome = null;
		String telefone = null;
		String endereco = null;

		do {
			System.out.print("Nome: ");

			try {
				nome = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (!validator.validarNome(nome));

		do {
			System.out.print("Telefone: ");

			try {
				telefone = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (!validator.validarnumero(telefone));

		do {
			System.out.print("Endereço: ");

			try {
				endereco = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (!validator.validarEndereco(endereco));

		Client client = new Client(nome, endereco, telefone);

		facade.clientInsert(client);

		return client;
	}

	public Product resgisterProduct() throws Exception {

		System.out.println();
		System.out.println("*********************************");
		System.out.println("****** Selecione o Produto ******");
		System.out.println("*********************************");
		System.out.println();

		try {
			Print.list(facade.productListAll());
			Product product;
			int id = 0;
			do {
				System.out.println();
				System.out.print("Selecione o produto (ID): ");
				try {
					id = Integer.parseInt(reader.readLine());
				} catch (NumberFormatException e) {
					System.out.println();
					Print.getIntMessageError();
				}
				product = facade.productGetById(id);
				if (product == null) {
					Print.getIdValidMessege();
				}
			} while (product == null);
			return product;

		} catch (Exception e) {

			System.out.println();
			System.out.println(e.getMessage());
		}

		throw new Exception();
	}

	public int registerQuantity() {

		int quantity = 0;

		while (quantity <= 0) {
			System.out.print("Quantidade: ");
			try {
				quantity = Integer.parseInt(reader.readLine());

				if (quantity <= 0) {
					Print.getQuantInvalidMessege();
				}

			} catch (NumberFormatException e) {

				System.out.println();
				Print.getIntMessageError();

			} catch (IOException e) {
				System.out.println();
				System.out.println(e.getMessage());
			}

		}
		return quantity;
	}

	public PaymentMethod registerPaymentMethod() throws SaleException {
		int option = 0;
		PaymentMethod paymentMethod = null;

		do {
			System.out.println("*********************************");
			System.out.println("*      MÉTODO DE PAGAMENTO      *");
			System.out.println("* 1 - Dinheiro                  *");
			System.out.println("* 2 - Cartão                    *");
			System.out.println("*********************************");

			System.out.print("Opção:");
			try {
				option = Integer.parseInt(reader.readLine());
				switch (option) {
				case 1:
					paymentMethod = PaymentMethod.DINHEIRO;
					break;
				case 2:
					paymentMethod = PaymentMethod.CARTAO;
					break;
				default:
					throw new SaleException("A opção " + option + " não existe!");
				}
			} catch (Exception e) {

				Print.getIntMessageError();
			}
		} while (paymentMethod == null);
		return paymentMethod;
	}

	public Brand registerBrand() throws SaleException {
		Brand brand;
		System.out.println("*********************************");
		System.out.println("***********  Marcas  ************");
		System.out.println("*********************************");

		Print.list(facade.brandListAll());

		do {
			System.out.println();
			System.out.print("Selecione a marca (ID): ");
			int id = 0;
			try {
				id = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				Print.getIntMessageError();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
			brand = facade.brandGetById(id);
			if (brand == null) {
				Print.getIdValidMessege();
			}
		} while (brand == null);
		return brand;
	}

	public Sale editSalePrint(int id) {
		Sale sale = facade.saleGetById(id);
		if (sale == null) {
			return sale;
		}
		int answer = 0;

		do {
			System.out.println("***********************************");
			System.out.println("* SELECIONE OS CAMPOS PARA EDITAR *");
			System.out.println("* 1 - Cliente                     *");
			System.out.println("* 2 - Carrinho                    *");
			System.out.println("* 3 - Método de pagamento         *");
			System.out.println("* 4 - Finalizar modificações      *");
			System.out.println("***********************************");
			System.out.println();
			System.out.print("Opção: ");

			try {
				answer = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {

				Print.getIntMessageError(1, 4);
			} catch (IOException e) {

				e.printStackTrace();
			}
			if (answer > 4) {
				Print.getMenuinvalidOptionMessege(answer);
			}

			switch (answer) {
			case 1:
				editClientField(sale);
				answer = 0;
				break;
			case 2:
				editItensField(sale);
				answer = 0;
				break;
			case 3:
				editPaymentField(sale);
				answer = 0;
				break;
			case 4:
				break;
			default:
				answer = 0;
				break;
			}
		} while (answer != 4);
		facade.saleUpdate(sale);
		return sale;
	}

	public void editClientField(Sale sale) {
		int answer = 0;
		String nome = null;
		String numero = null;
		String endereco = null;
		do {
			System.out.println();
			System.out.println("***********************************");
			System.out.println("*  SELECIONE O CAMPO DE CLIENTE   *");
			System.out.println("* 1 - Nome                        *");
			System.out.println("* 2 - Telefone                    *");
			System.out.println("* 3 - Endereço                    *");
			System.out.println("* 4 - Finalizar modificações      *");
			System.out.println("***********************************");
			System.out.println();
			System.out.print("Opção: ");
			try {
				answer = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				Print.getIntMessageError(1, 4);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (answer > 4) {
				Print.getMenuinvalidOptionMessege(answer);
			}

			switch (answer) {
			case 1:
				edit.nameClientEdit(sale, nome);
				answer = 0;
				break;

			case 2:
				edit.phoneClientEdit(sale, numero);
				answer = 0;
				break;

			case 3:
				edit.addressClientEdit(sale, endereco);
				answer = 0;
				break;

			case 4:
				break;
			default:
				answer = 0;
				break;
			}
		} while (answer != 4);
		facade.clientUpdate(sale.getClient());
	}

	public void editItensField(Sale sale) {
		String option = null;
		int answer = 0;
		do {
			System.out.println();
			System.out.println("***********************************");
			System.out.println("*      PRODUTOS NO CARRINHO       *");
			System.out.println();
			Print.list(sale.getItems());
			System.out.println();
			System.out.println("***********************************");
			System.out.println();
			System.out.println("***********************************");
			System.out.println("*     SELECIONE A MODIFICAÇÃO     *");
			System.out.println("* 1 - Adicionar Produtos          *");
			System.out.println("* 2 - Remover Produtos            *");
			System.out.println("* 3 - Editar a Quantidade         *");
			System.out.println("* 4 - Finalizar modificações      *");
			System.out.println("***********************************");
			System.out.println();
			System.out.print("Opção: ");
			try {
				answer = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				Print.getIntMessageError(1, 4);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (answer > 4) {
				Print.getMenuinvalidOptionMessege(answer);
			}

			switch (answer) {
			case 1:
				do {

					sale.getItems().add(registerOrderItem());

					System.out.print("Inserir mais um produto (S ou N)?");
					try {
						option = reader.readLine();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}

				} while (option.equalsIgnoreCase("S"));
				answer = 0;
				break;

			case 2:
				edit.removeProductCartEdit(sale, option);
				answer = 0;
				break;

			case 3:
				edit.quantProductsCartEdit(sale);
				answer = 0;
				break;

			case 4:
				break;
			default:
				answer = 0;
				break;
			}
		} while (answer != 4);
		for (OrderItem test : sale.getItems()) {
			facade.OrderItemUpdate(test);
		}

	}

	public void editPaymentField(Sale sale) {
		try {
			sale.setPaymentMethod(registerPaymentMethod());
		} catch (SaleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
