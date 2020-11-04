package ui.paquete;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.recogida.Recogida;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;

import java.awt.Color;


import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.JButton;


import java.util.List;

import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class PaqueteView  {

	/**
	 * 
	 */

	private JFrame frame;
	private JPanel contentPane;
	
	private Recogida recogida;
	private List<ProductoEntity> almacen= new ProductosModel().getListaProductos();
	private JPanel pnInferior;
	private JButton btCancelar;
	private JPanel pnTitulo;
	private JLabel lbTitulo;
	private JScrollPane scrProductos;
	private JTable tableProductos;
	private JPanel pnCentral;
	private JPanel pnProductos;
	private JLabel lbProductos;
	private JLabel lbIDProducto;
	private JTextField txIDProducto;
	private JButton btTerminar;
	private JPanel pnPedidos;
	private JScrollPane scrPedidos;
	private JLabel lbPedidos;
	private JTable tablePedidos;
	private JPanel pnBotones;
	private JPanel pnEscaner;
	private JButton btEscanear;
	private JSpinner spUnidades;
	private JPanel pnID;
	private JButton btEmpaquetar;

	

	/**
	 * Create the frame.
	 */
	public PaqueteView() {
	
		initialize();
		
		
		
	}
	
	private void initialize() {
		frame= new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setTitle("Revision");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 334, 528);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setFont(new Font("Arial", Font.PLAIN, 13));
		
		pnInferior = new JPanel();
		pnInferior.setLayout(new GridLayout(2, 3, 0, 0));
		
		lbIDProducto = new JLabel("IDProducto: ");
		lbIDProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lbIDProducto.setFont(new Font("Arial", Font.PLAIN, 13));
		
		
		txIDProducto = new JTextField();
		txIDProducto.setFont(new Font("Arial", Font.PLAIN, 13));
		
		txIDProducto.setColumns(10);
		
		
		
		lbTitulo = new JLabel("Empaquetado");
		lbTitulo.setFont(new Font("Arial", Font.PLAIN, 24));
		
		pnTitulo = new JPanel();
		pnTitulo.add(lbTitulo);
		tableProductos = new JTable();
		tableProductos.setDefaultEditor(Object.class, null);
		tableProductos.setFont(new Font("Arial", Font.PLAIN, 15));
		tableProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		
		scrProductos = new JScrollPane();
		scrProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrProductos.setViewportView(tableProductos);
		
		
		contentPane.add(pnInferior, BorderLayout.SOUTH);
		
		btTerminar = new JButton("Terminar");
		btTerminar.setFont(new Font("Arial", Font.PLAIN, 13));
		
		pnEscaner = new JPanel();
		
		pnEscaner.setLayout(new GridLayout(0, 3, 0, 0));
		
		pnEscaner.add(lbIDProducto);
		
		pnID = new JPanel();
		pnEscaner.add(pnID);
		pnID.setLayout(new GridLayout(0, 2, 0, 0));
		pnID.add(txIDProducto);
		
		
		pnBotones = new JPanel();
		pnInferior.add(pnBotones);
		pnInferior.add(pnEscaner);
		
		spUnidades = new JSpinner();
		spUnidades.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		pnID.add(spUnidades);
		
		btEscanear = new JButton("Escanear");
		btEscanear.setFont(new Font("Arial", Font.PLAIN, 13));
		pnEscaner.add(btEscanear);
		pnBotones.setLayout(new GridLayout(0, 3, 0, 0));
		btEmpaquetar = new JButton("Empaquetar");
		btEmpaquetar.setFont(new Font("Arial", Font.PLAIN, 13));
		pnBotones.add(btEmpaquetar);
		pnBotones.add(btCancelar);
		
		
		
		pnBotones.add(btTerminar);
		
		contentPane.add(pnTitulo, BorderLayout.NORTH);
		
		pnCentral = new JPanel();
		contentPane.add(pnCentral, BorderLayout.CENTER);
		pnCentral.setLayout(new GridLayout(2, 1, 0, 0));
		
		pnPedidos = new JPanel();
		pnCentral.add(pnPedidos);
		pnPedidos.setLayout(new BorderLayout(0, 0));
		
		scrPedidos = new JScrollPane();
		scrPedidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnPedidos.add(scrPedidos);
		
		tablePedidos = new JTable();
		
		tablePedidos.setDefaultEditor(Object.class, null);
		tablePedidos.setFont(new Font("Arial", Font.PLAIN, 15));
		tablePedidos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
	    scrPedidos.setViewportView(tablePedidos);
		
		lbPedidos = new JLabel("Pedidos");
		lbPedidos.setHorizontalAlignment(SwingConstants.CENTER);
		lbPedidos.setFont(new Font("Arial", Font.PLAIN, 18));
		pnPedidos.add(lbPedidos, BorderLayout.NORTH);
	
		pnProductos = new JPanel();
		pnCentral.add(pnProductos);
		pnProductos.setLayout(new BorderLayout(0, 0));
		pnProductos.add(scrProductos, BorderLayout.CENTER);
		
		lbProductos = new JLabel("Productos");
		lbProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lbProductos.setFont(new Font("Arial", Font.PLAIN, 18));
		pnProductos.add(lbProductos, BorderLayout.NORTH);
	}

	public JTextField getTxIDProducto() {
		return txIDProducto;
	}
	

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public Recogida getRecogida() {
		return recogida;
	}

	public List<ProductoEntity> getAlmacen() {
		return almacen;
	}

	public JPanel getPnBotones() {
		return pnInferior;
	}


	

	public JButton getBtCancelar() {
		return btCancelar;
	}
	
	public JButton getBtEscanear() {
		return btEscanear;
	}
	
	public JButton getBtTerminar() {
		return btTerminar;
	}
	
	public JButton getBtEmpaquetar() {
		return btEmpaquetar;
	}

	public JSpinner getSpUnidades() {
		return spUnidades;
		
	}
	public JPanel getPnTitulo() {
		return pnTitulo;
	}

	public JLabel getLbTitulo() {
		return lbTitulo;
	}

	public JScrollPane getScrProductos() {
		return scrProductos;
	}

	public JTable getTableProductos() {
		return tableProductos;
	}
	
	public JTable getTablePedidos() {
		return tablePedidos;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
