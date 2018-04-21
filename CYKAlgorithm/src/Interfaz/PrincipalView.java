package Interfaz;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PrincipalView extends JFrame implements ActionListener {

	private TextArea taGramatica;

	private JButton butAgregarProduccion;

	private JButton butVerificarCadena;

	public static final String VERIFICAR = "verificar";
	public static final String AGREGAR = "agregar";

	public PrincipalView() {

		taGramatica = new TextArea("Aqui va su gramatica");
		taGramatica.setEditable(false);
		butAgregarProduccion = new JButton("Agregar produccion");
		butAgregarProduccion.setActionCommand(AGREGAR);
		butAgregarProduccion.addActionListener(this);
		butVerificarCadena = new JButton("Verificar cadena");
		butAgregarProduccion.setActionCommand(VERIFICAR);
		butAgregarProduccion.addActionListener(this);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PrincipalView pv = new PrincipalView();
		pv.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String evento = ae.getActionCommand();

		if (ae.equals(VERIFICAR)) {

			
			
		} else if (ae.equals(AGREGAR)) {

			
			String numProds=JOptionPane.showInputDialog("Indique cuantas producciones va a tener su variable");
			
			
		}
	}

}
