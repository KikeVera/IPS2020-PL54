package ui.almacen;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * Clase encargada de la interfaz de usuario de la tienda online.
 * @author Moises
 *
 */
public class OperacionesOTView {
	
	private JFrame frmTiendaOnline;
	private JTable tabOrdenes;
	private JPanel pnCentral;
	private JButton btComenzar;
	private JPanel pnBotones;
	private JPanel pnTabla;
	private JButton btSalir;
	private JLabel lbTitulo;
	private JPanel pnOperacion;
	private JLabel lbOperacion;
	private JComboBox <String> cbOperacion;
	private JButton btInformacion;
	
	/**
	 * Create the application.
	 */
	public OperacionesOTView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//Inicializa la ventana principal 
		frmTiendaOnline = new JFrame();
		frmTiendaOnline.setTitle("Tienda online");
		frmTiendaOnline.setName("Tienda online");
		frmTiendaOnline.setBounds(0,0,289,480);
		frmTiendaOnline.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTiendaOnline.setLocationRelativeTo(null);
		
		//Inicializa el panel superior izquierdo 
		pnCentral = new JPanel();
		
		
		tabOrdenes = new JTable();
		tabOrdenes.setFont(new Font("Arial", Font.PLAIN, 14));
		tabOrdenes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabOrdenes.setName("tabProductos");
		tabOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabOrdenes.setDefaultEditor(Object.class, null);
		pnCentral.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPedidos = new JScrollPane(tabOrdenes);
		pnCentral.add(scrollPedidos);
		
		
		
		btComenzar = new JButton("Comenzar");	
		btComenzar.setFont(new Font("Arial", Font.PLAIN, 14));
		frmTiendaOnline.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		pnTabla = new JPanel();
		
		frmTiendaOnline.getContentPane().add(pnTabla);
		pnTabla.setLayout(new BorderLayout(0, 0));
		pnTabla.add(pnCentral);
		
		pnOperacion = new JPanel();
		pnTabla.add(pnOperacion, BorderLayout.NORTH);
		pnOperacion.setLayout(new GridLayout(1, 2, 0, 0));
		
		lbOperacion = new JLabel("Operacion:");
		lbOperacion.setFont(new Font("Arial", Font.PLAIN, 14));
		pnOperacion.add(lbOperacion);
		
		cbOperacion = new JComboBox <String>();
		cbOperacion.setModel(new DefaultComboBoxModel <String>(new String[] {"Recogida","Empaquetado"}));
		cbOperacion.setFont(new Font("Arial", Font.PLAIN, 14));
		pnOperacion.add(cbOperacion);
		
		pnBotones = new JPanel();
		pnBotones.setLayout(new GridLayout(0, 2, 0, 0));
		pnBotones.add(btComenzar);
		
		frmTiendaOnline.getContentPane().add(pnBotones, BorderLayout.SOUTH);
		
		btInformacion = new JButton("Informacion");
		btInformacion.setFont(new Font("Arial", Font.PLAIN, 14));
		pnBotones.add(btInformacion);
		
		btSalir = new JButton("Salir");
		btSalir.setFont(new Font("Arial", Font.PLAIN, 14));
		pnBotones.add(btSalir);
		
		lbTitulo = new JLabel("Ordenes de trabajo:");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmTiendaOnline.getContentPane().add(lbTitulo, BorderLayout.NORTH);

		
	}
	
	//Getters y Setters anyadidos para acceso desde el controlador 
	public JFrame getFrame() { return this.frmTiendaOnline; }
	public JTable getTabOrdenes() { return this.tabOrdenes; }
	
	public JButton getbtComenzar() {return this.btComenzar;}
	public JButton getbtSalir() {return this.btSalir;}
	public JButton getbtInformacion() {return this.btInformacion;}

	public JComboBox<String> getCbOperacion(){
		return cbOperacion;
	}
	

	
	public JPanel getPnSuperiorIzquierdo() { return this.pnCentral;}
	
	
}

