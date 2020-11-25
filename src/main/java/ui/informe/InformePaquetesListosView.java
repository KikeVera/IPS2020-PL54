package ui.informe;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import javax.swing.SwingConstants;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Clase encargada de la interfaz de usuario de la tienda online.
 * @author Moises
 *
 */
public class InformePaquetesListosView {
	
	private JFrame frmTiendaOnline;
	private JPanel pnContenido;
	private JLabel lbTitulo;
	private JPanel pnTabla;
	
	private JScrollPane scTabla;
	private JTable tablePaquetes;
	
	/**
	 * Create the application.
	 */
	public InformePaquetesListosView() {
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
		frmTiendaOnline.setBounds(0,0,752,493);
		frmTiendaOnline.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTiendaOnline.setLocationRelativeTo(null);
		frmTiendaOnline.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		pnContenido = new JPanel();
		
		pnContenido.setBackground(Color.WHITE);
		
		frmTiendaOnline.getContentPane().add(pnContenido);
		pnContenido.setLayout(new BorderLayout(0, 0));
		
		lbTitulo = new JLabel("Cajas listas para expedici\u00F3n");
	
		
	
		
	
		lbTitulo.setForeground(Color.BLACK);
		
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Arial Black", Font.PLAIN, 16));
		pnContenido.add(lbTitulo, BorderLayout.NORTH);
		
		pnTabla = new JPanel();
		pnTabla.setBackground(Color.WHITE);
		
		
		pnTabla.setLayout(new GridLayout(1, 0, 0, 0));
		
		scTabla = new JScrollPane();
		
		
		pnContenido.add(scTabla, BorderLayout.CENTER);
		
		tablePaquetes = new JTable();
		scTabla.setViewportView(tablePaquetes);
		
		
	
		
		
		

		
	}
	
	//Getters y Setters anyadidos para acceso desde el controlador 
	public JFrame getFrame() { return this.frmTiendaOnline; }
	
	public JTable getTable() {
		return this.tablePaquetes;
	}
	
}

