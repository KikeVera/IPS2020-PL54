package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.database.Database;
import util.pedido.AlmacenController;
import util.pedido.AlmacenView;
import util.pedido.PedidosModel;
import util.producto.ProductosController;
import util.producto.ProductosModel;
import util.producto.ProductosView;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6725140802751372778L;
	
	private JPanel panelPrincipal;
	private JButton btnLanzarAplicacionAlmacen;
	private JButton btnCrearBD;
	private JButton btnCargarDatosBD;
	private JButton btnLanzarPaginaWeb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingMain frame = new SwingMain();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwingMain() {
		setTitle("Pantalla inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 473);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(4, 2, 0, 0));
		panelPrincipal.add(getBtnLanzarPaginaWeb());
		panelPrincipal.add(getBtnLanzarAplicacionAlmacen());
		panelPrincipal.add(getBtnCargarDatosBD());
		panelPrincipal.add(getBtnCrearBD());
	}

	private JButton getBtnLanzarAplicacionAlmacen() {
		if (btnLanzarAplicacionAlmacen == null) {
			btnLanzarAplicacionAlmacen = new JButton("Iniciar la aplicacion del almacen");
			btnLanzarAplicacionAlmacen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AlmacenController controller = new AlmacenController(new ProductosModel(), new AlmacenView(),new PedidosModel());
					controller.initController();
				}
			});
			btnLanzarAplicacionAlmacen.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return btnLanzarAplicacionAlmacen;
	}
	private JButton getBtnCrearBD() {
		if (btnCrearBD == null) {
			btnCrearBD = new JButton("Crear la base de datos");
			btnCrearBD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Database db=new Database();
					db.createDatabase(false);
					
				}
			});
			btnCrearBD.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return btnCrearBD;
	}
	private JButton getBtnCargarDatosBD() {
		if (btnCargarDatosBD == null) {
			btnCargarDatosBD = new JButton("Cargar datos de la base de datos");
			btnCargarDatosBD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Database db=new Database();
					db.loadDatabase();
				}
			});
			btnCargarDatosBD.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return btnCargarDatosBD;
	}
	private JButton getBtnLanzarPaginaWeb() {
		if (btnLanzarPaginaWeb == null) {
			btnLanzarPaginaWeb = new JButton("Iniciar la pagina web");
			btnLanzarPaginaWeb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductosController controller = new ProductosController(new ProductosModel(), new ProductosView(),new PedidosModel());
					controller.initController();
				}
			});
			btnLanzarPaginaWeb.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return btnLanzarPaginaWeb;
	}
}
