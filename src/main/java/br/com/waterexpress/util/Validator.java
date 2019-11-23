package br.com.waterexpress.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.waterexpress.dao.BrandDAO;

public class Validator {
	private static Validator instance;

	private Validator() {
	}

	public static Validator getValidator() {

		if (instance == null)
			instance = new Validator();

		return instance;
	}

	public boolean validarNome(String nome) {
		String padrao = "^[A-Za-zÀ-Åà-åÈ-Ëè-ëÌ-Ïì-ïÒ-Öò-öÙ-Üù-üÇçÑñİı\\s]{3,100}$";
		Pattern regex = Pattern.compile(padrao);

		Matcher resultado = regex.matcher(nome);
		if (!(resultado.matches())) {
			System.out.println("O nome " + "'" + nome + "'" + " não é válido");
			System.out.println("+++++++++++++++++++++++++++++++");
			return false;
		} else {
			return true;
		}
	}

	public boolean validarEndereco(String endereco) {
		String padrao = "^[0-9A-Za-zÀ-Åà-åÈ-Ëè-ëÌ-Ïì-ïÒ-Öò-öÙ-Üù-üÇçÑñİı\\s.,-]{3,}+$";
		Pattern regex = Pattern.compile(padrao);

		Matcher resultado = regex.matcher(endereco);
		
		if (!(resultado.matches())) {
			System.out.println("O endereco " + endereco + " não é válido");
			System.out.println("+++++++++++++++++++++++++++++++");
			return false;
		} else {
			return true;
		}
	}

	public boolean validarnumero(String numero) {
		String padrao = "^[0-9()-]{9,}$";
		Pattern regex = Pattern.compile(padrao);
		
		Matcher resultado = regex.matcher(numero);
		if(!(resultado.matches())) {
			System.out.println("O telefone "+ numero + " não é válido");
			System.out.println("+++++++++++++++++++++++++++++++");
			return false;
		}else {
			return true;
		}
	}
}
