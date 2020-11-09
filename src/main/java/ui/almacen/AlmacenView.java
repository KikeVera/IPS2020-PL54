package ui.almacen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

/**
 * Clase encargada de la interfaz de usuario de la tienda online.
 * @author Moises
 *
 */
public class AlmacenView {
	
	private JFrame frmTiendaOnline;
	private JTable tabPedidos;
	private JScrollPane scrollProductos;
	private JPanel pnSuperior;
	private JLabel lblTabla;
	private JPanel pnInferior;
	private JLabel lblPedido;
	private JTable tabProductos;
	private JButton btVerPedido;
	private JButton btAsignar;
	private JPanel pnBotones;
	private JPanel pnTablas;
	private JButton btOperacionesOT;
	private JButton btSalir;
	
	/**
	 * Create the application.
	 */
	public AlmacenView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//Inicializa la ventana principal 
		frmTiendaOnline = new JFrame();
		frmTiendaOnline.setTitle("Almac\u00E9n");
		frmTiendaOnline.setName("Almac\u00E9n");
		frmTiendaOnline.setBounds(0,0,317,483);
		frmTiendaOnline.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTiendaOnline.setLocationRelativeTo(null);
		
		//Inicializa el panel superior izquierdo 
		pnSuperior = new JPanel();
		pnSuperior.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnSuperior.setLayout(new BorderLayout(0, 0));
		
		lblTabla = new JLabel("Pedidos realizados:");
		lblTabla.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnSuperior.add(lblTabla, BorderLayout.NORTH);
		
		tabPedidos = new JTable();
		tabPedidos.setName("tabProductos");
		tabPedidos.setDefaultEditor(Object.class, null);
		tabPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabPedidos.setDefaultEditor(Object.class, null);
		JScrollPane scrollPedidos = new JScrollPane(tabPedidos);
		pnSuperior.add(scrollPedidos);

		//Inicializa el panel superior derecho 
		pnInferior = new JPanel();
		pnInferior.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnInferior.setLayout(new BorderLayout(0, 0));
		
		scrollProductos = new JScrollPane((Component) null);
		pnInferior.add(scrollProductos);
		
		tabProductos = new JTable();
		tabProductos.setDefaultEditor(Object.class, null);
		tabProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollProductos.setViewportView(tabProductos);
		
		lblPedido = new JLabel("Pedido seleccionado:");
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInferior.add(lblPedido, BorderLayout.NORTH);
		
		
		//Añadimos todo al panel principal 
		
		
		btVerPedido = new JButton("Ver Pedido");
		btVerPedido.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		
		btAsignar = new JButton("Asignar a OT");	
		btAsignar.setFont(new Font("Arial", Font.PLAIN, 14));
		frmTiendaOnline.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		pnTablas = new JPanel();
		
		frmTiendaOnline.getContentPane().add(pnTablas);
		pnTablas.setLayout(new GridLayout(2, 1, 0, 0));
		pnTablas.add(pnSuperior);
		pnTablas.add(pnInferior);
		
		pnBotones = new JPanel();
		pnBotones.setLayout(new GridLayout(0, 2, 0, 0));
		pnBotones.add(btAsignar);
		pnBotones.add(btVerPedido);
		pnBotones.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		frmTiendaOnline.getContentPane().add(pnBotones, BorderLayout.SOUTH);
		
		btOperacionesOT = new JButton("Operaciones OT");
		btOperacionesOT.setFont(new Font("Arial", Font.PLAIN, 14));
		pnBotones.add(btOperacionesOT);
		
		btSalir = new JButton("Salir");
		btSalir.setFont(new Font("Arial", Font.PLAIN, 14));
		pnBotones.add(btSalir);

		
	}
	
	//Getters y Setters anyadidos para acceso desde el controlador 
	public JFrame getFrame() { return this.frmTiendaOnline; }
	public JTable getTabPedidos() { return this.tabPedidos; }
	public JButton getbtVerPedido() {return this.btVerPedido;}
	public JButton getbtAsignar() {return this.btAsignar;}
	public JButton getbtSalir() {return this.btSalir;}
	public JButton getbtOperacionesOT() {return this.btOperacionesOT;}
	public JTable getTabProductos() { return this.tabProductos;}
	public JScrollPane getScrollProductos() {return this.scrollProductos;}

	public JPanel getPnSuperiorDerecho() { return this.pnInferior;}
	public JPanel getPnSuperiorIzquierdo() { return this.pnSuperior;}
	
	
}

