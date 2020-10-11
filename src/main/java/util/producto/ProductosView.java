package util.producto;

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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

/**
 * Clase encargada de la interfaz de usuario de la tienda online.
 * @author Moises
 *
 */
public class ProductosView {
	
	private JFrame frmTiendaOnline;
	private JTable tabDetalle;
	private JTable tabProductos;
	private JScrollPane scrollPedido;
	private JSpinner spUnidades;
	private JButton btnAnadir;
	private JButton btnEliminar;
	private JButton btnFinalizarPedido;
	private JTextArea textPedido;
	private JPanel pnSuperiorIzquirdo;
	private JLabel lblTabla;
	private JPanel pnSuperiorDerecho;
	private JLabel lblPedido;
	private JPanel pnInferiorIzquierdo;
	private JPanel pnUnidades;
	private JLabel lblUnidades;
	private JPanel pnInferiorDerecho;
	private JPanel pnPrecio;
	private JLabel lblPrecio;
	private JTextField textPrecio;
	
	/**
	 * Create the application.
	 */
	public ProductosView() {
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
		
		lblTabla = new JLabel("Productos disponibles:");
		lblTabla.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnSuperiorIzquirdo.add(lblTabla, BorderLayout.NORTH);
		
		tabProductos = new JTable();
		tabProductos.setName("tabProductos");
		tabProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabProductos.setDefaultEditor(Object.class, null);
		JScrollPane tablePanel = new JScrollPane(tabProductos);
		pnSuperiorIzquirdo.add(tablePanel);
		
		//Inicializa el panel inferior derecho
		pnInferiorDerecho = new JPanel();
		pnInferiorDerecho.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnInferiorDerecho.setLayout(new GridLayout(0, 2, 0, 0));
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		textPrecio.setText("0 \u20AC");
		textPrecio.setEditable(false);
		textPrecio.setColumns(10);
		
		pnPrecio = new JPanel();
		pnInferiorDerecho.add(pnPrecio);
		pnPrecio.setLayout(new BorderLayout(0, 0));
		
		lblPrecio = new JLabel("Precio actual:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnPrecio.add(lblPrecio, BorderLayout.NORTH);
		pnPrecio.add(getTextPrecio());
		btnFinalizarPedido = new JButton("Finalizar Pedido");
		btnFinalizarPedido.setBackground(new Color(50, 205, 50));
		btnFinalizarPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInferiorDerecho.add(btnFinalizarPedido);
		
		//Inicializa el panel inferior izquierdo 
		pnInferiorIzquierdo = new JPanel();
		pnInferiorIzquierdo.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnInferiorIzquierdo.setLayout(new GridLayout(0, 3, 0, 0));
		
		pnUnidades = new JPanel();
		pnInferiorIzquierdo.add(pnUnidades);
		pnUnidades.setLayout(new BorderLayout(0, 0));
		
		lblUnidades = new JLabel("Unidades:");
		lblUnidades.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnUnidades.add(lblUnidades, BorderLayout.NORTH);
		
		spUnidades = new JSpinner();
		spUnidades.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnUnidades.add(spUnidades);
		spUnidades.setModel(new SpinnerNumberModel(1, 1, null, 1));
		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setBackground(new Color(50, 205, 50));
		btnAnadir.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInferiorIzquierdo.add(btnAnadir);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(255, 0, 0));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInferiorIzquierdo.add(btnEliminar);

		//Inicializa el panel superior derecho 
		pnSuperiorDerecho = new JPanel();
		pnSuperiorDerecho.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnSuperiorDerecho.setLayout(new BorderLayout(0, 0));
		
		scrollPedido = new JScrollPane((Component) null);
		pnSuperiorDerecho.add(scrollPedido);
		
		textPedido = new JTextArea();
		textPedido.setEditable(false);
		scrollPedido.setViewportView(textPedido);
		
		lblPedido = new JLabel("Estado actual del pedido:");
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnSuperiorDerecho.add(lblPedido, BorderLayout.NORTH);
		frmTiendaOnline.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		
		//Añadimos todo al panel principal 
		frmTiendaOnline.getContentPane().add(pnSuperiorIzquirdo);
		frmTiendaOnline.getContentPane().add(pnSuperiorDerecho);
		frmTiendaOnline.getContentPane().add(pnInferiorIzquierdo);
		frmTiendaOnline.getContentPane().add(pnInferiorDerecho);
		
	}
	
	//Getters y Setters anyadidos para acceso desde el controlador 
	public JFrame getFrame() { return this.frmTiendaOnline; }
	public JTable getDetalleCarrera() { return this.tabDetalle; }
	public JTable getTablaProductos() { return this.tabProductos; }
	public JSpinner getSpUnidades() {return this.spUnidades; }
	public JButton getBtnAnadir() {return this.btnAnadir; }
	public JButton getBtnEliminar() {return this.btnEliminar; }
	public JButton getBtnFinalizarPedido() {return this.btnFinalizarPedido; }
	public JTextArea getTextPedido() { return this.textPedido;}
	public JScrollPane getScrollPedido() {return this.scrollPedido;}
	public JTextField getTextPrecio() { return this.textPrecio;}
	public JPanel getPnSuperiorDerecho() { return this.pnSuperiorDerecho;}
	public JPanel getPnSuperiorIzquierdo() { return this.pnSuperiorIzquirdo;}
	public JPanel getPnInferiorDerecho() { return this.pnInferiorDerecho;}
	public JPanel getPnInferiorIzquierdo() { return this.pnInferiorIzquierdo;}
	
}

