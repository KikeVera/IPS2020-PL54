package ui.producto;

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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

/**
 * Clase encargada de la interfaz de usuario de la tienda online.
 * @author Moises
 *
 */
public class ProductosView {
	
	private JFrame frmTiendaOnline;
	private JTable tabProductos;
	private JScrollPane scrollPedido;
	private JSpinner spUnidades;
	private JButton btnAnadir;
	private JPanel pnSuperiorIzquirdo;
	private JLabel lblTabla;
	private JPanel pnSuperiorDerecho;
	private JLabel lblPedido;
	private JPanel pnInferiorIzquierdo;
	private JPanel pnUnidades;
	private JLabel lblUnidades;
	private JPanel pnInferiorDerecho;
	private JTable tabPedido;
	private JPanel pnInfo;
	private JPanel pnNorte;
	private JPanel pnSur;
	private JLabel lblUsuario;
	private JPanel pnBotonesInferoriDerecho;
	private JButton btnEliminar;
	private JButton btnFinalizarPedido;
	private JPanel pnPrecio;
	private JLabel lblPrecio;
	private JTextField textField;
	
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
		frmTiendaOnline.setBounds(0, 0, 809, 732);
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
		pnInferiorDerecho.setLayout(new GridLayout(0, 1, 0, 0));
		
		pnPrecio = new JPanel();
		pnPrecio.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnInferiorDerecho.add(pnPrecio);
		pnPrecio.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblPrecio = new JLabel("Precio actual:");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnPrecio.add(lblPrecio);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("0 \u20AC");
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		pnPrecio.add(textField);
		
		pnBotonesInferoriDerecho = new JPanel();
		pnInferiorDerecho.add(pnBotonesInferoriDerecho);
		pnBotonesInferoriDerecho.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
		
		btnEliminar = new JButton("Eliminar del carrito");
		btnEliminar.setToolTipText("Para eliminar un producto del carrito en especifico, debe seleccionarlo en el carrito, establecer  las unidades que desea eliminar de su pedido y darle a este bot\u00F3n.");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBackground(Color.RED);
		pnBotonesInferoriDerecho.add(btnEliminar);
		
		btnFinalizarPedido = new JButton("Finalizar Pedido");
		btnFinalizarPedido.setToolTipText("Una vez tenga claro su pedido, para finalizar pulse este bot\u00F3n.");
		btnFinalizarPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizarPedido.setBackground(new Color(50, 205, 50));
		pnBotonesInferoriDerecho.add(btnFinalizarPedido);
		
		//Inicializa el panel inferior izquierdo 
		pnInferiorIzquierdo = new JPanel();
		pnInferiorIzquierdo.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnInferiorIzquierdo.setLayout(new MigLayout("", "[374px]", "[46px][29px]"));
		
		pnUnidades = new JPanel();
		pnInferiorIzquierdo.add(pnUnidades, "cell 0 0,growx,aligny top");
		pnUnidades.setLayout(new BorderLayout(0, 0));
		
		lblUnidades = new JLabel("Unidades:");
		lblUnidades.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnUnidades.add(lblUnidades, BorderLayout.NORTH);
		
		spUnidades = new JSpinner();
		spUnidades.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnUnidades.add(spUnidades);
		spUnidades.setModel(new SpinnerNumberModel(1, 1, null, 1));
		btnAnadir = new JButton("A\u00F1adir al carrito");
		btnAnadir.setToolTipText("Para a\u00F1adir un producto al carrito en especifico, debe seleccionarlo en la tabla de productos disponibles, establecer  las unidades deseadas y darle a este bot\u00F3n.");
		btnAnadir.setBackground(new Color(50, 205, 50));
		btnAnadir.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInferiorIzquierdo.add(btnAnadir, "cell 0 1,growx,aligny top");

		//Inicializa el panel superior derecho 
		pnSuperiorDerecho = new JPanel();
		pnSuperiorDerecho.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnSuperiorDerecho.setLayout(new BorderLayout(0, 0));
		
		scrollPedido = new JScrollPane((Component) null);
		pnSuperiorDerecho.add(scrollPedido);
		
		tabPedido = new JTable();
		tabPedido.setDefaultEditor(Object.class, null);
		tabPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPedido.setViewportView(tabPedido);
		
		lblPedido = new JLabel("Estado actual del pedido:");
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnSuperiorDerecho.add(lblPedido, BorderLayout.NORTH);
		frmTiendaOnline.getContentPane().setLayout(new MigLayout("", "[752px]", "[][231px,grow][130px,grow]"));
		
		pnInfo = new JPanel();
		frmTiendaOnline.getContentPane().add(pnInfo);
		
		pnNorte = new JPanel();
		frmTiendaOnline.getContentPane().add(pnNorte);
		
		pnSur = new JPanel();
		frmTiendaOnline.getContentPane().add(pnSur);
		pnNorte.setLayout(new BorderLayout(0, 0));
		
		
		//Añadimos todo al panel principal 
		pnNorte.add(pnSuperiorIzquirdo, BorderLayout.CENTER);
		pnNorte.add(pnSuperiorDerecho, BorderLayout.WEST);
		pnSur.setLayout(new GridLayout(0, 2, 0, 0));
		pnSur.add(pnInferiorIzquierdo);
		pnSur.add(pnInferiorDerecho);
		frmTiendaOnline.getContentPane().add(pnInfo,"cell 0 0,grow");
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInfo.add(lblUsuario);
		frmTiendaOnline.getContentPane().add(pnNorte,"cell 0 1,grow");
		frmTiendaOnline.getContentPane().add(pnSur,"cell 0 2,grow");
		
	}
	
	//Getters y Setters anyadidos para acceso desde el controlador 
	public JFrame getFrame() { return this.frmTiendaOnline; }
	public JTable getTabProductos() { return this.tabProductos; }
	public JSpinner getSpUnidades() {return this.spUnidades; }
	public JButton getBtnAnadir() {return this.btnAnadir; }
	public JButton getBtnEliminar() {return this.btnEliminar; }
	public JButton getBtnFinalizarPedido() {return this.btnFinalizarPedido; }
	public JTable getTabPedido() { return this.tabPedido;}
	public JScrollPane getScrollPedido() {return this.scrollPedido;}
	public JTextField getTextPrecio() {return this.textField;}
	public JPanel getPnSuperiorDerecho() { return this.pnSuperiorDerecho;}
	public JPanel getPnSuperiorIzquierdo() { return this.pnSuperiorIzquirdo;}
	public JPanel getPnInferiorDerecho() { return this.pnInferiorDerecho;}
	public JPanel getPnInferiorIzquierdo() { return this.pnInferiorIzquierdo;}
	public JLabel getLblPedido() {return this.lblPedido;}
	public JLabel getLblUsuario() {return this.lblUsuario;}
	
}
