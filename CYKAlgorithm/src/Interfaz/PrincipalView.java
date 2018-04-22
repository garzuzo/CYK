package Interfaz;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Mundo.CYK;
/**
 * 
 * @author garzuzo
 *
 */

public class PrincipalView extends JFrame implements ActionListener {

	private TextArea taGramatica;

	private JButton butAgregarProduccion;

	private JButton butVerificarCadena;

	private JTextField txtCadena;

	private CYK cyk;

	public static final String VERIFICAR = "verificar";
	public static final String AGREGAR = "agregar";

	/**
	 * listener agregar produccion jdialog
	 */
	public static final String ADD = "addProd";

	public PrincipalView() {
		cyk = new CYK();
		taGramatica = new TextArea("Aqui va su gramatica\n");
		taGramatica.setEditable(false);
		butAgregarProduccion = new JButton("Agregar produccion");

		butAgregarProduccion.setActionCommand(AGREGAR);
		butAgregarProduccion.addActionListener(this);
		JPanel pButAgregarProduccion = new JPanel();
		pButAgregarProduccion.setLayout(new FlowLayout());
		pButAgregarProduccion.add(butAgregarProduccion);
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout());

		txtCadena = new JTextField(8);
		butVerificarCadena = new JButton("Verificar cadena");
		butVerificarCadena.setActionCommand(VERIFICAR);
		butVerificarCadena.addActionListener(this);

		panelAux.add(new JLabel("w="));
		panelAux.add(txtCadena);
		panelAux.add(butVerificarCadena);
		add(taGramatica, BorderLayout.NORTH);
		add(pButAgregarProduccion, BorderLayout.CENTER);
		add(panelAux, BorderLayout.SOUTH);

		pack();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PrincipalView pv = new PrincipalView();
		pv.setVisible(true);

		pv.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pv.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String evento = ae.getActionCommand();

		if (evento.equals(VERIFICAR)) {

			String w = txtCadena.getText();
			cyk.cykAlgorithm(w);

		} else if (evento.equals(AGREGAR)) {

			String numProds = JOptionPane.showInputDialog("Indique cuantas producciones va a tener su variable");
			agregarProduccion(Integer.parseInt(numProds));
		} else if (evento.equals(ADD)) {
			String variable = tVariable.getText();

			ArrayList<String> producciones = new ArrayList<String>();
			for (int i = 0; i < campos.size(); i++) {
				producciones.add(campos.get(i).getText());
			}
			crearProduccion(variable, producciones);
			refrescarGramatica(variable, producciones);
			jd.dispose();
		}
	}

	private JTextField tVariable;
	private JDialog jd;
	private ArrayList<JTextField> campos;
	private JButton butAgregar;

	public void agregarProduccion(int numProducciones) {

		jd = new JDialog();
		jd.setTitle("Agrega una nueva produccion");
		jd.setLayout(new FlowLayout());
		tVariable = new JTextField(4);
		jd.add(tVariable);
		jd.add(new JLabel("->"));
		campos = new ArrayList<JTextField>();
		for (int i = 0; i < numProducciones; i++) {

			JTextField tfAct = new JTextField(6);

			jd.add(tfAct);
			campos.add(tfAct);
			if (i + 1 != numProducciones)
				jd.add(new JLabel("|"));
		}
		butAgregar = new JButton("Agregar produccion");
		butAgregar.setActionCommand(ADD);
		butAgregar.addActionListener(this);
		jd.add(butAgregar);
		jd.setLocationRelativeTo(null);
		jd.pack();
		jd.setVisible(true);
	}

	public void refrescarGramatica(String var, ArrayList<String> prods) {

		StringBuilder sb = new StringBuilder();
		sb.append(var + "->");
		for (int i = 0; i < prods.size(); i++) {
			sb.append(prods.get(i));
			if (i + 1 != prods.size())
				sb.append("|");

		}
		taGramatica.append(sb.toString() + "\n");
	}

	public void crearProduccion(String variable, ArrayList<String> prods) {

		cyk.crearProduccion(variable, prods);

	}

}
