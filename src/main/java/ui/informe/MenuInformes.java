package ui.informe;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.informes.InformeImporteVentaController;
import logica.informes.InformeImporteVentaController.InformeVenta;
import logica.informes.InformeOTsController;
import logica.informes.InformePaquetesController;
import logica.informes.InformePaquetesListosController;
import logica.informes.InformeProductosController;
import persistencia.almacenero.OTModel;
import persistencia.paquete.PaqueteModel;
import persistencia.producto.ProductosModel;
import persistencia.producto.VentasModel;

public class MenuInformes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6725140802751372778L;
	
	private JFrame frmInformes;
	private JPanel panelPrincipal;
	private JButton btnLanzarInformeImporteTipoPago;
	private JButton btnLanzarInformeImporteTipoUsuario;
	private JButton btnLanzarInformeImporteEmpresa;
	private JButton btnLanzarInformeOTs;
	private JButton btnLanzarInformeProductos;
	private JButton btnLanzarInformePaquetes;
	private JButton btnLanzarInformePaquetesListos;

	public MenuInformes() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		frmInformes = new JFrame();
		
		frmInformes.setBounds(100, 100, 683, 473);
		frmInformes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInformes.setLocationRelativeTo(null);
		frmInformes.getContentPane().setLayout(new BorderLayout(0, 0));
		frmInformes.setTitle("Informes");
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(4, 2, 0, 0));
		panelPrincipal.add(getBtnInformeImporteVentaTipoPago());
		panelPrincipal.add(getBtnInformeImporteVentaTipoUsuario());
		panelPrincipal.add(getBtnInformeImporteVentaEmpresa());
		panelPrincipal.add(getBtnInformePaquetes());
		panelPrincipal.add(getBtnInformePaquetesListos());
		panelPrincipal.add(getBtnInformeOTs());
		panelPrincipal.add(getBtnInformeProductosStock());
		
		frmInformes.getContentPane().add(panelPrincipal);
	
	}

	private JButton getBtnInformeImporteVentaTipoPago() {
		if (btnLanzarInformeImporteTipoPago == null) {
			btnLanzarInformeImporteTipoPago = new JButton("Informe importe venta tipo de pago");
			btnLanzarInformeImporteTipoPago.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InformeImporteVentaController controller = new InformeImporteVentaController(new VentasModel(), new InformeImporteVentaView(),InformeVenta.tipoPago);
					controller.initController();
				}
			});
			btnLanzarInformeImporteTipoPago.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnLanzarInformeImporteTipoPago;
	}
	private JButton getBtnInformeImporteVentaTipoUsuario() {
		if (btnLanzarInformeImporteTipoUsuario == null) {
			btnLanzarInformeImporteTipoUsuario = new JButton("Informe importe venta tipo de usuario");
			btnLanzarInformeImporteTipoUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InformeImporteVentaController controller = new InformeImporteVentaController(new VentasModel(), new InformeImporteVentaView(),InformeVenta.tipoUsuario);
					controller.initController();
				}
			});
			btnLanzarInformeImporteTipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnLanzarInformeImporteTipoUsuario;
	}
	
	private JButton getBtnInformeImporteVentaEmpresa() {
		if (btnLanzarInformeImporteEmpresa == null) {
			btnLanzarInformeImporteEmpresa = new JButton("Informe importe venta empresa");
			btnLanzarInformeImporteEmpresa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InformeImporteVentaController controller = new InformeImporteVentaController(new VentasModel(), new InformeImporteVentaView(),InformeVenta.empresa);
					controller.initController();
				}
			});
			btnLanzarInformeImporteEmpresa.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnLanzarInformeImporteEmpresa;
	}
	
	private JButton getBtnInformePaquetes() {
		if (btnLanzarInformePaquetes == null) {
			btnLanzarInformePaquetes = new JButton("Informe paquetes");
			btnLanzarInformePaquetes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InformePaquetesController controller= new InformePaquetesController(new PaqueteModel(), new InformePaquetesView());
					controller.initController();
				}
			});
			btnLanzarInformePaquetes.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnLanzarInformePaquetes;
	}
	
	private JButton getBtnInformePaquetesListos() {
		if (btnLanzarInformePaquetesListos == null) {
			btnLanzarInformePaquetesListos = new JButton("Informe paquetes listos");
			btnLanzarInformePaquetesListos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InformePaquetesListosController controller= new InformePaquetesListosController(new PaqueteModel(), new InformePaquetesListosView());
					controller.initController();
				}
			});
			btnLanzarInformePaquetesListos.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnLanzarInformePaquetesListos;
	}
	
	private JButton getBtnInformeOTs() {
		if (btnLanzarInformeOTs == null) {
			btnLanzarInformeOTs = new JButton("Informe OTs ");
			btnLanzarInformeOTs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InformeOTsController controller= new InformeOTsController(new OTModel(), new InformeOTsView());
					controller.initController();
				}
			});
			btnLanzarInformeOTs.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnLanzarInformeOTs;
	}
	
	private JButton getBtnInformeProductosStock() {
		if (btnLanzarInformeProductos == null) {
			btnLanzarInformeProductos = new JButton("Informe productos stock ");
			btnLanzarInformeProductos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InformeProductosController controller= new InformeProductosController(new ProductosModel(), new InformeProductosView());
					controller.initController();
				}
			});
			btnLanzarInformeProductos.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnLanzarInformeProductos;
	}
	
	public JFrame getFrame() { return this.frmInformes; }
	
	public JPanel getPanel() {
		return this.panelPrincipal;
	}
}
