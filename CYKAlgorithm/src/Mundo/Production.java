package Mundo;

import java.util.ArrayList;

public class Production {

	private String variable;
	private ArrayList<String> cuerpo;
	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public ArrayList<String> getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(ArrayList<String> cuerpo) {
		this.cuerpo = cuerpo;
	}



	public Production(String variable, ArrayList<String> cuerpo) {
		this.variable = variable;
		this.cuerpo = cuerpo;
	}
	

}
