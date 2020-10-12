package ui.recogida;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import negocio.almacen.Recogida;
import util.producto.ProductoEntity;

import util.producto.ProductosModel;


import java.awt.Color;


import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.JButton;


import java.util.List;

import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

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
		frame.setBounds(100, 100, 714, 449);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		
		
		
		btComprobar = new JButton("Comprobar");
		btComprobar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		btIncidencia = new JButton("A\u00F1adir Incidencia");
		btIncidencia.setFont(new Font("Arial", Font.PLAIN, 14));
		
		btSalir = new JButton("Salir");
		btSalir.setFont(new Font("Arial", Font.PLAIN, 14));
		
		pnBotones = new JPanel();
		pnBotones.setLayout(new GridLayout(0, 4, 0, 0));
		pnBotones.add(btComprobar);
		pnBotones.add(btIncidencia);
		
		btGuardarIncidencias = new JButton("Guardar Incidencias");
		btGuardarIncidencias.setFont(new Font("Arial", Font.PLAIN, 14));
		pnBotones.add(btGuardarIncidencias);
		pnBotones.add(btSalir);
		
		
		
		lbTitulo = new JLabel("Recogida");
		lbTitulo.setFont(new Font("Arial", Font.PLAIN, 24));
		
		pnTitulo = new JPanel();
		pnTitulo.add(lbTitulo);
		tableProductos = new JTable();
		tableProductos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		scrProductos = new JScrollPane();
		scrProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrProductos.setViewportView(tableProductos);
		
		
		contentPane.add(pnBotones, BorderLayout.SOUTH);
		contentPane.add(pnTitulo, BorderLayout.NORTH);
		contentPane.add(scrProductos, BorderLayout.CENTER);
		
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
