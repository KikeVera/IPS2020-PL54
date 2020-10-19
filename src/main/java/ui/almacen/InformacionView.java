package ui.almacen;

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

public class InformacionView  {

	/**
	 * 
	 */

	private JFrame frmInformacion;
	private JPanel contentPane;
	
	private Recogida recogida;
	private List<ProductoEntity> almacen= new ProductosModel().getListaProductos();
	private JPanel pnBotones;
	private JPanel pnTitulo;
	private JLabel lbTitulo;
	private JScrollPane scrProductos;
	private JTable tableProductos;
	private JPanel pnCentral;
	private JPanel pnProductos;
	private JLabel lbProductos;
	private JButton btTerminar;

	

	/**
	 * Create the frame.
	 */
	public InformacionView() {
	
		initialize();		
	}
	
	private void initialize() {
		frmInformacion= new JFrame();
		frmInformacion.setBackground(Color.WHITE);
		frmInformacion.setTitle("Informacion");
		frmInformacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInformacion.setBounds(100, 100, 575, 512);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		frmInformacion.setContentPane(contentPane);
		
		pnBotones = new JPanel();
		pnBotones.setLayout(new GridLayout(2, 3, 0, 0));
		
		
		
		lbTitulo = new JLabel("Informacion del pedido");
		lbTitulo.setFont(new Font("Arial", Font.PLAIN, 24));
		
		pnTitulo = new JPanel();
		pnTitulo.add(lbTitulo);
		tableProductos = new JTable();
		tableProductos.setFont(new Font("Arial", Font.PLAIN, 15));
		tableProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		
		scrProductos = new JScrollPane();
		scrProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrProductos.setViewportView(tableProductos);
		
		
		contentPane.add(pnBotones, BorderLayout.SOUTH);
		
		btTerminar = new JButton("Aceptar");
		btTerminar.setFont(new Font("Arial", Font.PLAIN, 13));
		pnBotones.add(btTerminar);
		contentPane.add(pnTitulo, BorderLayout.NORTH);
		
		
		pnCentral = new JPanel();
		contentPane.add(pnCentral, BorderLayout.CENTER);
		pnCentral.setLayout(new GridLayout(1, 2, 0, 0));
		
		
		pnProductos = new JPanel();
		pnCentral.add(pnProductos);
		pnProductos.setLayout(new BorderLayout(0, 0));
		pnProductos.add(scrProductos, BorderLayout.CENTER);
		
		lbProductos = new JLabel("Productos");
		lbProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lbProductos.setFont(new Font("Arial", Font.PLAIN, 18));
		pnProductos.add(lbProductos, BorderLayout.NORTH);
	}

	public JFrame getFrame() {
		return frmInformacion;
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
		return pnBotones;
	}
	
	public JButton getBtTerminar() {
		return btTerminar;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
