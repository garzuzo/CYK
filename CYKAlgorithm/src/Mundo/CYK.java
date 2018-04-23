package Mundo;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * @author garzuzo
 *
 */
public class CYK {

	private HashSet<String> variables;
	private ArrayList<Production> gramatica;

	private ArrayList<String>[][] matriz;

	private String ultimoSubconjunto;

	public String getUltimoSubconjunto() {
		return ultimoSubconjunto;
	}

	public void setUltimoSubconjunto(String ultimoSubconjunto) {
		this.ultimoSubconjunto = ultimoSubconjunto;
	}

	public CYK() {
		variables = new HashSet<String>();
		gramatica = new ArrayList<Production>();
	}

	/**
	 * Se verifica que la cadena pueda ser generada o no por la gramatica dada
	 * 
	 * @param w
	 *            cadena a verificar
	 * @return true si el w es generada por la gramatica
	 */
	public boolean cykAlgorithm(String w) {
		ultimoSubconjunto = "";
		int n = w.length();
		matriz = new ArrayList[n + 1][n + 1];

		inicializar(w);
		dp();
		return verificarCYK(matriz[1][n]);
	}

	/**
	 * Se verifica si S pertenece al conjunto final (1,n)
	 * 
	 * @param arr
	 *            lista de la posicion 1,n de la matriz
	 * 
	 * @return true si w es generada por la gramatica
	 */
	public boolean verificarCYK(ArrayList<String> arr) {

		boolean ret = false;
		ultimoSubconjunto += "{";
		for (int i = 0; i < arr.size(); i++) {
			String varAct = arr.get(i);

			ultimoSubconjunto += varAct;
			if (i + 1 != arr.size())
				ultimoSubconjunto += ",";
			else
				ultimoSubconjunto += "}";
			if (varAct.equalsIgnoreCase("S"))
				ret = true;
		}
		System.out.println("----------------------------------");
		for (int i = 1; i < matriz.length; i++) {
			for (int j = 1; j < matriz.length; j++) {
				if(matriz[i][j]!=null)
				System.out.println(matriz[i][j].toString());
			}
		}
		System.out.println("----------------------------------");
		return ret;
	}

	/**
	 * 
	 * Se recorre desde la segunda columna a la ultima por cada iteracion se recorre
	 * hasta la fila n-j, siendo j la columna actual y se recorre k veces (j-1),
	 * siendo el numero de subconjuntos X(i,k)X(i-k,j+k)
	 * 
	 * @param w
	 */
	public void dp() {

		for (int j = 2; j < matriz.length; j++) {
			for (int i = 1; i <= matriz.length - j; i++) {
				HashSet<String> xAct = new HashSet<String>();
				for (int k = 1; k <= j - 1; k++) {

					ArrayList<String> comb = combinarSubconjuntos(matriz[i][k], matriz[i + k][j - k]);

					for (int l = 0; l < comb.size(); l++)
						xAct.add(comb.get(l));

				}
				agregarEnMatriz(xAct, i, j);

			}
		}

	}

	/**
	 * Se agrega una variable en una posicion de la matriz dada si algun cuerpo de
	 * dicha variable contiene alguna combinacion
	 */
	public void agregarEnMatriz(HashSet<String> x, int i, int j) {

		ArrayList<String> rets = new ArrayList<String>();
		HashSet<String> variables = new HashSet<String>();
		matriz[i][j] = new ArrayList<String>();
		for (String act : x)
			verificarCuerpo(act, rets);
		for (int k = 0; k < rets.size(); k++) {
			variables.add(rets.get(k));
		}
		for (String act : variables)
			matriz[i][j].add(act);
	}

	/**
	 * Se combinan los subconjuntos c1 y c2
	 * 
	 * @param c1
	 *            X(i,k)
	 * @param c2
	 *            X(i+k,j-k)
	 * @return subconjunto luego de combinar c1 y c2
	 */
	public ArrayList<String> combinarSubconjuntos(ArrayList<String> c1, ArrayList<String> c2) {

		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < c1.size(); i++) {

			for (int j = 0; j < c2.size(); j++) {

				if (!c1.get(i).equals("") && !c2.get(j).equals("")) {
					String comb = c1.get(i) + c2.get(j);
					ret.add(comb);
				}
			}
		}
		return ret;
	}

	/**
	 * 
	 * Se hace el primer paso que es agregar la primera columna j = 1. Para cada i,
	 * 1 ≤ i ≤ n, Xij = Xi1 := conjunto de variables A tales que A → ai
	 * 
	 * @param w
	 *            cadena a verificar
	 */
	public void inicializar(String w) {

		/**
		 * se recorre la primera columna
		 */

		for (int i = 1; i < matriz[1].length; i++) {

			/**
			 * conjunto que tendra las variables que contienen al caracter actual
			 */
			ArrayList<String> act = new ArrayList<String>();

			matriz[i][1] = act;

			// terminal actual
			String termAct = w.charAt(i - 1) + "";

			/**
			 * se recorre cada produccion y se verifica si contienen a cadAct en algun
			 * cuerpo de esa produccion, si es asi: se agrega la terminal a act.
			 */
			verificarCuerpo(termAct, act);

		}
	}

	/**
	 * Verifica si el cuerpo de una produccion contiene una terminal o un cuerpo
	 * dado
	 */
	public void verificarCuerpo(String cuerpo, ArrayList<String> act) {

		for (int k = 0; k < gramatica.size(); k++) {

			ArrayList<String> cuerpoAct = gramatica.get(k).getCuerpo();

			for (int j = 0; j < cuerpoAct.size(); j++) {
				if (cuerpoAct.get(j).equals(cuerpo)) {
					act.add(gramatica.get(k).getVariable());
				}
			}

		}
	}

	/**
	 * Se crea una nueva produccion y se agrega a la gramatica
	 * 
	 * @param variable
	 * @param prods
	 */
	public void crearProduccion(String variable, ArrayList<String> cuerpo) {

		Production prodActual = new Production(variable, cuerpo);
		variables.add(variable);
		gramatica.add(prodActual);
		System.out.println(variable + cuerpo.toString());
	}

}
