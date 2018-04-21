package Interfaz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Mundo.Production;

public class ViewAgregarProduccion extends JFrame implements ActionListener {

	public static final String ADD = "agregar";
	private String variable;
	private ArrayList<String> producciones;
	private PrincipalView pv;
	private ArrayList<JTextField> campos;

	private JTextField tVariable;
	private JButton butAgregar;

	public ViewAgregarProduccion(int numProducciones, PrincipalView pv) {
		this.pv = pv;
		setLayout(new FlowLayout());
		tVariable = new JTextField(4);
		add(tVariable);
		for (int i = 0; i < numProducciones; i++) {

			JTextField tfAct = new JTextField(6);

			add(tfAct);
			campos.add(tfAct);
		}
		butAgregar = new JButton("Agregar produccion");
		butAgregar.setActionCommand(ADD);
		butAgregar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String comando = ae.getActionCommand();

		if (comando.equals(ADD)) {
			variable = tVariable.getText();

			for (int i = 0; i < campos.size(); i++) {
				producciones.add(campos.get(i).getText());
			}
			
			
		}
	}

}
