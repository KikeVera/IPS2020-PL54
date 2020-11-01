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
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RevisionView  {

	/**
	 * 
	 */

	private JFrame frame;
	private JPanel contentPane;
	private Recogida recogida;
	private List<ProductoEntity> almacen= new ProductosModel().getListaProductos();
	private JPanel pnBotones;
	private JButton btEscanear;
	private JButton btIncidencia;
	private JButton btCancelar;
	private JPanel pnTitulo;
	private JLabel lbTitulo;
	private JScrollPane scrProductos;
	private JTable tableProductos;
	private JPanel pnCentral;
	private JPanel pnProductos;
	private JLabel lbProductos;
	private JLabel lbIDEscaner;
	private JTextField txIDEsacaner;
	private JButton btTerminar;
	private JPanel pnInferior;
	private JPanel pnID;
	private JSpinner spUnidades;

	

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
		frame.setBounds(100, 100, 575, 512);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		
		
		
		btEscanear = new JButton("Escanear");
		btEscanear.setFont(new Font("Arial", Font.PLAIN, 13));
		
		btIncidencia = new JButton(" Incidencia");
		btIncidencia.setFont(new Font("Arial", Font.PLAIN, 13));
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setFont(new Font("Arial", Font.PLAIN, 13));
		
		pnBotones = new JPanel();
		pnBotones.setLayout(new GridLayout(1, 3, 0, 0));
		
		lbIDEscaner = new JLabel("ID Producto: ");
		lbIDEscaner.setFont(new Font("Arial", Font.PLAIN, 13));
		
		
		txIDEsacaner = new JTextField();
		txIDEsacaner.setFont(new Font("Arial", Font.PLAIN, 13));
		
		txIDEsacaner.setColumns(10);
		
		pnBotones.add(btIncidencia);
		pnBotones.add(btCancelar);
		
		
		
		lbTitulo = new JLabel("Recogida");
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
		
		
		
		btTerminar = new JButton("Terminar");
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
		
		pnInferior = new JPanel();
		contentPane.add(pnInferior, BorderLayout.SOUTH);
		pnInferior.setLayout(new GridLayout(2, 1, 0, 0));
		pnInferior.add(pnBotones);
		
		pnID = new JPanel();
		pnID.setLayout(new GridLayout(0, 4, 0, 0));
		pnID.add(lbIDEscaner);
		pnID.add(txIDEsacaner);
		
		spUnidades = new JSpinner();
		spUnidades.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spUnidades.setFont(new Font("Arial", Font.PLAIN, 13));
		pnID.add(spUnidades);
		pnID.add(btEscanear);
		pnInferior.add(pnID);
		
	}

	public JTextField getTxIDEsacaner() {
		return txIDEsacaner;
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


	public JButton getBtIncidencia() {
		return btIncidencia;
	}

	public JButton getBtCancelar() {
		return btCancelar;
	}
	
	public JButton getBtescanear() {
		return btEscanear;
	}
	
	public JButton getBtTerminar() {
		return btTerminar;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
