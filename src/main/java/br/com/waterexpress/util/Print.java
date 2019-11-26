package br.com.waterexpress.util;

import java.util.List;

public class Print {

	public static <T> void list(List<T> list) {

		for (T item : list) {
			System.out.println(item + "\n");
		}
	}

	public static void getIntMessageError() {
		
		System.out.println("++++++++++++++++++++++++++");
		System.out.println("|         ERRO!!!        |");
		System.out.println("|    Insira Um Número    |");
		System.out.println("++++++++++++++++++++++++++");
		System.out.println();
	}

	public static void getIntMessageError(int minRange, int maxRange) {

		System.out.println("++++++++++++++++++++++++++");
		System.out.println("|          ERRO!!!       |");
		System.out.println("| Insira Um Número " + minRange + " - " + maxRange + " |");
		System.out.println("++++++++++++++++++++++++++");
		System.out.println();
	}

	public static void withoutRegisters() {

		System.out.println("****  Não existem registros  ****");
	}

	public static void getNumberMessageError() {

		System.out.println("++++++++++++++++++++++++++");
		System.out.println("|         ERRO!!!         |");
		System.out.println("| Insira Um Número Válido |");
		System.out.println("++++++++++++++++++++++++++");
		System.out.println();
	}

	public static void getIdValidMessege() {
		System.out.println();
		System.out.println("***********************");
		System.out.println("**Insira Um Id Válido** ");
		System.out.println("***********************");
	}

	public static void getQuantInvalidMessege() {
		System.out.println();
		System.out.println("** Quantidade inválida **");
		System.out.println();
	}
	
	public static void getMenuinvalidOptionMessege(int answer) {
		System.out.println();
		System.out.println("**A Opção " + answer + " Não Existe**");
		System.out.println("**Insira Outra opção**");
		System.out.println();
	}
	
	public static void getCanceledSaleMessege() {
		System.out.println("***********************");
		System.out.println("** Venda Cancelada!! ** ");
		System.out.println("***********************");
	}

}
