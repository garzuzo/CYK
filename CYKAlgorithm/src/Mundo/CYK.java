package Mundo;

import java.util.ArrayList;
import java.util.HashSet;

public class CYK {

	private HashSet<String> variables;
	private ArrayList<Production> gramatica;

	public CYK() {
		variables = new HashSet<String>();
		gramatica = new ArrayList<Production>();
	}

	public boolean cykAlgorithm(String w) {

		return true;
	}

	public void crearProduccion(String variable, ArrayList<String> prods) {

		Production prodActual = new Production(variable, prods);
		variables.add(variable);
		gramatica.add(prodActual);
		System.out.println(variable + prods.toString());
	}
}
