package br.com.waterexpress.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.waterexpress.controller.Facade;
import br.com.waterexpress.model.Sale;
import br.com.waterexpress.util.Validator;

public class EditSale {
	private Validator validator = Validator.getValidator();
	private Facade facade;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void nameClientEdit(Sale sale, String nome) {
		do {
			System.out.print("Novo Nome: ");

			try {
				nome = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (!validator.validarNome(nome));
		sale.getClient().setName(nome);
	}

	public void phoneClientEdit(Sale sale, String numero) {
		do {
			System.out.print("Novo Telefone: ");

			try {
				numero = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (!validator.validarnumero(numero));
		sale.getClient().setPhoneNumber(numero);
	}
	
	public void addressClientEdit(Sale sale, String endereco) {
		do {
			System.out.print("Novo Endere�o: ");

			try {
				endereco = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (!validator.validarEndereco(endereco));
		sale.getClient().setAddress(endereco);
	}
	
	public void removeProductCartEdit(Sale sale, String option) {
		do {
			System.out.println("Selecione o produto (#):");
			System.out.println();
			sale.indexItem();
			int item = 0;
			try {
				item = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sale.getItems().remove(item);
			System.out.println("Deseja Remover Outro Produto?(S/N)");
			try {
				option = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (option.equalsIgnoreCase("S"));
	}
	
	public void quantProductsCartEdit(Sale sale) {
		int idAnswer = 0;
		System.out.println("Selecione o produto (#):");
		System.out.println();
		sale.indexItem();
		System.out.println();
		System.out.print("Op��o:");
		try {
			idAnswer = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		int quantidade = 0;
		System.out.print("Quantidade: ");

		try {
			quantidade = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sale.getItems().get(idAnswer).setQuantity(quantidade);
		System.out.println();
	}
}
