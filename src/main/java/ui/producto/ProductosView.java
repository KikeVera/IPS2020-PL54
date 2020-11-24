package ui.producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import ui.util.MiRenderer;

/**
 * Clase encargada de la interfaz de usuario de la tienda online.
 * @author Moises
 *
 */
public class ProductosView {
	
	private JFrame frmTiendaOnline;
	private JTable tabProductos;
	private JScrollPane scrollPedido;
	private JPanel pnSuperiorIzquierdo;
	private JLabel lblTabla;
	private JPanel pnSuperiorDerecho;
	private JLabel lblPedido;
	private JTable tabPedido;
	private JPanel pnInfo;
	private JPanel pnNorte;
	private JLabel lblUsuario;
	private JPanel pnPrecio;
	private JLabel lblPrecio;
	private JTextField textField;
	private JPanel pnNavegacion;
	private JButton btnAtras;
	private JButton btnSiguiente;
	private JPanel pnUnidades;
	private JLabel lblUnidades;
	private JSpinner spUnidades;
	private JButton btnAnadir;
	private JButton btnEliminar;
	private JPanel pnBotonesPedido;
	private JPanel pnAuxiliar;
	private JButton btnPagarPedido;
	private JButton btnFinalizarPedido;
	private JPanel pnDireccion;
	private JLabel lblDireccion;
	private JTextField txtDireccionEnvio;
	
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
		frmTiendaOnline.setBounds(0, 0, 1042, 637);
		frmTiendaOnline.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTiendaOnline.setLocationRelativeTo(null);
		
		//Inicializa el panel superior izquierdo 
		pnSuperiorIzquierdo = new JPanel();
		pnSuperiorIzquierdo.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnSuperiorIzquierdo.setLayout(new BorderLayout(0, 0));
		
		lblTabla = new JLabel("Productos disponibles:");
		lblTabla.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnSuperiorIzquierdo.add(lblTabla, BorderLayout.NORTH);
		
		tabProductos = new JTable();
		tabProductos.setName("tabProductos");
		tabProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabProductos.setDefaultEditor(Object.class, null);
		tabProductos.setDefaultRenderer(Object.class, new MiRenderer());
		JScrollPane tablePanel = new JScrollPane(tabProductos);
		pnSuperiorIzquierdo.add(tablePanel);

		//Inicializa el panel superior derecho 
		pnSuperiorDerecho = new JPanel();
		pnSuperiorDerecho.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		pnSuperiorDerecho.setLayout(new BorderLayout(0, 0));
		
		lblPedido = new JLabel("Estado actual del pedido:");
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnSuperiorDerecho.add(lblPedido, BorderLayout.NORTH);
		
		pnPrecio = new JPanel();
		pnPrecio.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnSuperiorDerecho.add(pnPrecio, BorderLayout.SOUTH);
		pnPrecio.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblPrecio = new JLabel("Precio actual:");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnPrecio.add(lblPrecio);
		
		textField = new JTextField();
		textField.setText("0 \u20AC");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		pnPrecio.add(textField);
		
		scrollPedido = new JScrollPane((Component) null);
		pnSuperiorDerecho.add(scrollPedido);
		
		tabPedido = new JTable();
		tabPedido.setDefaultEditor(Object.class, null);
		tabPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPedido.setViewportView(tabPedido);
		frmTiendaOnline.getContentPane().setLayout(new MigLayout("", "[759px,grow]", "[][300px,grow][][bottom]"));
		
		pnInfo = new JPanel();
		frmTiendaOnline.getContentPane().add(pnInfo);
		
		pnNorte = new JPanel();
		frmTiendaOnline.getContentPane().add(pnNorte);
		
		pnUnidades = new JPanel();
		pnUnidades.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		frmTiendaOnline.getContentPane().add(pnUnidades, "cell 0 2,growx,aligny top");
		pnUnidades.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[55px,grow,center]"));
		
		lblUnidades = new JLabel("Unidades:");
		lblUnidades.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnUnidades.add(lblUnidades, "cell 0 0,alignx right,aligny center");
		
		spUnidades = new JSpinner();
		spUnidades.setModel(new SpinnerNumberModel(1, 1, null,1));
		spUnidades.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnUnidades.add(spUnidades, "cell 1 0,growx,alignx center,aligny center");
		
		btnAnadir = new JButton("A\u00F1adir al carrito");
		btnAnadir.setToolTipText("Para a\u00F1adir un producto al carrito en especifico, debe seleccionarlo en la tabla de productos disponibles, establecer  las unidades deseadas y darle a este bot\u00F3n.");
		btnAnadir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAnadir.setEnabled(false);
		btnAnadir.setBackground(new Color(50, 205, 50));
		pnUnidades.add(btnAnadir, "cell 2 0,alignx center,aligny center");
		
		btnEliminar = new JButton("Eliminar del carrito");
		btnEliminar.setToolTipText("Para eliminar un producto del carrito en especifico, debe seleccionarlo en el carrito, establecer  las unidades que desea eliminar de su pedido y darle a este bot\u00F3n.");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setEnabled(false);
		btnEliminar.setBackground(Color.RED);
		pnUnidades.add(btnEliminar, "cell 3 0,alignx center,aligny center");
		
		
		txtDireccionEnvio = new JTextField();
		txtDireccionEnvio.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtDireccionEnvio.setColumns(10);
		
		lblDireccion = new JLabel("Direcci\u00F3n de env\u00EDo:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		pnDireccion = new JPanel();
		pnDireccion.setLayout(new BorderLayout(0, 0));
		pnDireccion.add(getLblDireccion(), BorderLayout.NORTH);
		pnDireccion.add(txtDireccionEnvio, BorderLayout.CENTER);	
		
		pnBotonesPedido = new JPanel();
		pnBotonesPedido.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		frmTiendaOnline.getContentPane().add(pnBotonesPedido, "cell 0 3,grow");
		pnBotonesPedido.setLayout(new MigLayout("", "[100px,grow][right]", "[55px,grow]"));
		pnBotonesPedido.add(pnDireccion); 
		
		
		pnBotonesPedido.add(getPnDireccion(), "cell 0 0,grow");
		pnNorte.setLayout(new MigLayout("", "[600px,grow][460px,grow]", "[390px,grow]"));
		
		
		//Añadimos todo al panel principal 
		pnNorte.add(pnSuperiorDerecho, "cell 1 0,alignx center,grow");
		pnNorte.add(pnSuperiorIzquierdo, "cell 0 0,alignx center,grow");
		
		pnNavegacion = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnNavegacion.getLayout();
		flowLayout.setHgap(50);
		pnSuperiorIzquierdo.add(pnNavegacion, BorderLayout.SOUTH);
		
		btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setEnabled(false);
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnNavegacion.add(btnAtras);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		pnNavegacion.add(btnSiguiente);
		frmTiendaOnline.getContentPane().add(pnInfo,"cell 0 0,grow");
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInfo.add(lblUsuario);
		frmTiendaOnline.getContentPane().add(pnNorte,"cell 0 1,grow");
		
		pnAuxiliar = new JPanel();
		FlowLayout flowLayout1 = (FlowLayout) pnAuxiliar.getLayout();
		flowLayout1.setHgap(40);
		
		btnPagarPedido = new JButton("Pagar pedido");
		btnPagarPedido.setEnabled(false);
		btnPagarPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnAuxiliar.add(btnPagarPedido);
		
		btnFinalizarPedido = new JButton("Finalizar Pedido");
		btnFinalizarPedido.setToolTipText("Una vez tenga claro su pedido, para finalizar pulse este bot\u00F3n.");
		btnFinalizarPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizarPedido.setBackground(new Color(50, 205, 50));
		pnAuxiliar.add(btnFinalizarPedido);
		pnBotonesPedido.add(pnAuxiliar, "cell 1 0,alignx center,aligny center");
		

		
	}
	
	//Getters y Setters anyadidos para acceso desde el controlador 
	public JFrame getFrame() { return this.frmTiendaOnline; }
	public JTable getTabProductos() { return this.tabProductos; }
	public JSpinner getSpUnidades() {return this.spUnidades; }
	public JButton getBtnAnadir() {return this.btnAnadir; }
	public JButton getBtnEliminar() {return this.btnEliminar; }
	public JButton getBtnFinalizarPedido() {return this.btnFinalizarPedido; }
	public JButton getBtnSiguiente() {return this.btnSiguiente; }
	public JButton getBtnAtras() {return this.btnAtras; }
	public JTable getTabPedido() { return this.tabPedido;}
	public JScrollPane getScrollPedido() {return this.scrollPedido;}
	public JTextField getTextPrecio() {return this.textField;}
	public JPanel getPnSuperiorDerecho() { return this.pnSuperiorDerecho;}
	public JPanel getPnSuperiorIzquierdo() { return this.pnSuperiorIzquierdo;}
	public JLabel getLblPedido() {return this.lblPedido;}
	public JLabel getLblUsuario() {return this.lblUsuario;}
	public JButton getBtnPagarPedido() {return this.btnPagarPedido;}
	public JTextField getTextDireccionEnvio() {return this.txtDireccionEnvio;}
	public JPanel getPnDireccion() {return pnDireccion;}
	public JLabel getLblDireccion() { return lblDireccion;}	
	public JPanel getPnAuxiliar() {return pnAuxiliar;}


}