package util.pedido;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase encargada de la interfaz de usuario de la tienda online.
 * @author Moises
 *
 */
public class AlmacenView {
	
	private JFrame frmTiendaOnline;
	private JTable tabPedidos;
	private JScrollPane scrollProductos;
	private JPanel pnSuperiorIzquirdo;
	private JLabel lblTabla;
	private JPanel pnSuperiorDerecho;
	private JLabel lblPedido;
	private JTable tabProductos;
	private JButton btVerPedido;
	private JButton btAsignar;
	
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
		frmTiendaOnline.setTitle("Tienda online");
		frmTiendaOnline.setName("Tienda online");
		frmTiendaOnline.setBounds(0, 0, 766, 732);
		frmTiendaOnline.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTiendaOnline.setLocationRelativeTo(null);
		
		//Inicializa el panel superior izquierdo 
		pnSuperiorIzquirdo = new JPanel();
		pnSuperiorIzquirdo.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnSuperiorIzquirdo.setLayout(new BorderLayout(0, 0));
		
		lblTabla = new JLabel("Pedidos realizados:");
		lblTabla.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnSuperiorIzquirdo.add(lblTabla, BorderLayout.NORTH);
		
		tabPedidos = new JTable();
		tabPedidos.setName("tabProductos");
		tabPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabPedidos.setDefaultEditor(Object.class, null);
		JScrollPane scrollPedidos = new JScrollPane(tabPedidos);
		pnSuperiorIzquirdo.add(scrollPedidos);

		//Inicializa el panel superior derecho 
		pnSuperiorDerecho = new JPanel();
		pnSuperiorDerecho.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnSuperiorDerecho.setLayout(new BorderLayout(0, 0));
		
		scrollProductos = new JScrollPane((Component) null);
		pnSuperiorDerecho.add(scrollProductos);
		
		tabProductos = new JTable();
		scrollProductos.setViewportView(tabProductos);
		
		lblPedido = new JLabel("Pedido seleccionado:");
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnSuperiorDerecho.add(lblPedido, BorderLayout.NORTH);
		frmTiendaOnline.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		
		//Añadimos todo al panel principal 
		frmTiendaOnline.getContentPane().add(pnSuperiorIzquirdo);
		
		btVerPedido = new JButton("Ver Pedido");
		btVerPedido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnSuperiorIzquirdo.add(btVerPedido, BorderLayout.SOUTH);
		frmTiendaOnline.getContentPane().add(pnSuperiorDerecho);
		
		btAsignar = new JButton("Asignar");	
		btAsignar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnSuperiorDerecho.add(btAsignar, BorderLayout.SOUTH);

		
	}
	
	//Getters y Setters anyadidos para acceso desde el controlador 
	public JFrame getFrame() { return this.frmTiendaOnline; }
	public JTable getTabPedidos() { return this.tabPedidos; }
	public JButton getbtVerPedido() {return this.btVerPedido;}
	public JButton getbtAsignar() {return this.btAsignar;}
	
	public JTable getTabProductos() { return this.tabProductos;}
	public JScrollPane getScrollProductos() {return this.scrollProductos;}

	public JPanel getPnSuperiorDerecho() { return this.pnSuperiorDerecho;}
	public JPanel getPnSuperiorIzquierdo() { return this.pnSuperiorIzquirdo;}
	
	
}

