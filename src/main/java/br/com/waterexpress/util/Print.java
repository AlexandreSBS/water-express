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


}
