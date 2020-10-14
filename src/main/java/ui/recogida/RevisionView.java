package ui.recogida;




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

public class RevisionView  {

	/**
	 * 
	 */

	private JFrame frame;
	private JPanel contentPane;
	
	private Recogida recogida;
	private List<ProductoEntity> almacen= new ProductosModel().getListaProductos();
	private JPanel pnBotones;
	private JButton btComprobar;
	private JButton btIncidencia;
	private JButton btSalir;
	private JPanel pnTitulo;
	private JLabel lbTitulo;
	private JScrollPane scrProductos;
	private JTable tableProductos;
	private JButton btGuardarIncidencias;
	private JButton btBorrarIncidencia;
	private JPanel pnCentral;
	private JPanel pnProductos;
	private JPanel pnIncidencias;
	private JLabel lbProductos;
	private JScrollPane scrIncidencias;
	private JLabel lbIncidencias;
	private JTable tableIncidencias;

	

	/**
	 * Create the frame.
	 */
	public RevisionView() {
	
		initialize();
		
		
		
	}
	
	private void initialize() {
		frame= new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setTitle("Revision");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 833, 512);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		
		
		
		btComprobar = new JButton("Comprobar producto");
		btComprobar.setFont(new Font("Arial", Font.PLAIN, 13));
		
		btIncidencia = new JButton("A\u00F1adir Incidencia");
		btIncidencia.setFont(new Font("Arial", Font.PLAIN, 13));
		
		btSalir = new JButton("Salir");
		btSalir.setFont(new Font("Arial", Font.PLAIN, 13));
		
		pnBotones = new JPanel();
		pnBotones.setLayout(new GridLayout(0, 5, 0, 0));
		pnBotones.add(btComprobar);
		pnBotones.add(btIncidencia);
		
		btBorrarIncidencia = new JButton("Borrar incidencia");
		btBorrarIncidencia.setFont(new Font("Arial", Font.PLAIN, 13));
		pnBotones.add(btBorrarIncidencia);
		
		btGuardarIncidencias = new JButton("Guardar Incidencias");
		btGuardarIncidencias.setFont(new Font("Arial", Font.PLAIN, 13));
		pnBotones.add(btGuardarIncidencias);
		pnBotones.add(btSalir);
		
		
		
		lbTitulo = new JLabel("Recogida");
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
		contentPane.add(pnTitulo, BorderLayout.NORTH);
		
		
		pnCentral = new JPanel();
		contentPane.add(pnCentral, BorderLayout.CENTER);
		pnCentral.setLayout(new GridLayout(1, 2, 0, 0));
		
		
		pnProductos = new JPanel();
		pnCentral.add(pnProductos);
		
		pnIncidencias = new JPanel();
		pnCentral.add(pnIncidencias);
		pnIncidencias.setLayout(new BorderLayout(0, 0));
		
		scrIncidencias = new JScrollPane();
		scrIncidencias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnIncidencias.add(scrIncidencias);
		
		tableIncidencias = new JTable();
		tableIncidencias.setFont(new Font("Arial", Font.PLAIN, 15));
		tableIncidencias.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		scrIncidencias.setViewportView(tableIncidencias);
		
		lbIncidencias = new JLabel("Incidencias");
		lbIncidencias.setHorizontalAlignment(SwingConstants.CENTER);
		lbIncidencias.setFont(new Font("Arial", Font.PLAIN, 18));
		pnIncidencias.add(lbIncidencias, BorderLayout.NORTH);
		pnProductos.setLayout(new BorderLayout(0, 0));
		pnProductos.add(scrProductos, BorderLayout.CENTER);
		
		lbProductos = new JLabel("Productos");
		lbProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lbProductos.setFont(new Font("Arial", Font.PLAIN, 18));
		pnProductos.add(lbProductos, BorderLayout.NORTH);
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
		return pnBotones;
	}

	public JButton getBtComprobar() {
		return btComprobar;
	}

	public JButton getBtIncidencia() {
		return btIncidencia;
	}

	public JButton getBtSalir() {
		return btSalir;
	}
	
	public JButton getBtGuardarIncidencias() {
		return btGuardarIncidencias;
	}
	
	public JButton getBtBorrarIncidencia() {
		return btBorrarIncidencia;
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
	
	public JTable getTableIncidencias() {
		return tableIncidencias;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
