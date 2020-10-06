package util.producto;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class ProductosView {

	private JFrame frame;
	private JTable tabDetalle;
	private JTable tabProductos;
	
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
		
		frame = new JFrame();
		frame.setTitle("Carreras");
		frame.setName("Carreras");
		frame.setBounds(0, 0, 492, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());
		
		//Incluyo la tabla en un JScrollPane y anyado este en vez de la tabla para poder ver los headers de la tabla
		tabProductos = new JTable();
		tabProductos.setName("tabProductos");
		tabProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabProductos.setDefaultEditor(Object.class, null); 
		JScrollPane tablePanel = new JScrollPane(tabProductos);
		frame.getContentPane().add(tablePanel, "cell 0 5,grow");

	}
	
	//Getters y Setters anyadidos para acceso desde el controlador (repersentacion compacta)
	public JFrame getFrame() { return this.frame; }
	public JTable getDetalleCarrera() { return this.tabDetalle; }
	public JTable getTablaProductos() { return this.tabProductos; }

}
