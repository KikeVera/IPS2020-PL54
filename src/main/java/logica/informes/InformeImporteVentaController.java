package logica.informes;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logica.Controller;


import persistencia.producto.VentaEntity;
import persistencia.producto.VentasModel;

import ui.informe.InformeImporteVentaView;
import util.Util;



public class InformeImporteVentaController implements Controller {
	
	public enum InformeVenta{
		tipoPago,tipoUsuario,empresa
	}
	
	
	InformeVenta tipo;
	private VentasModel vm;
	private InformeImporteVentaView view;
	
	
	
	public InformeImporteVentaController(VentasModel vm, InformeImporteVentaView view,InformeVenta tipo) {
		this.vm=vm;
		this.view=view;
		this.tipo=tipo;
		this.initView();
	}
	
	public void initView() {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		
		
		//Inicializamos la tabla que representara al pedido 
		if(tipo==InformeVenta.tipoPago) {
			crearInformeTipoPago();
		}
		
		else if(tipo==InformeVenta.tipoUsuario) {
			crearInformeTipoUsuario();
		}
		
		else {
			crearInformeEmpresa();
		}
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);
		
		
		
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador.
	 */
	public void initController() {
		
		
		
	}
	
	public void crearInformeTipoPago() {
		List<VentaEntity> ventas = vm.getVentas();
		List<String> fechas = new ArrayList<>();
		List<String> tipoPago = new ArrayList<>();

		for (VentaEntity venta : ventas) {
			if (!fechas.contains(venta.getFecha())) {
				fechas.add(venta.getFecha());
			}
			if(venta.getTipoPago()!=null) {
				if (!tipoPago.contains(venta.getTipoPago())) {
					tipoPago.add(venta.getTipoPago());
				}
			
			}

		}

		Collections.sort(fechas);

		view.getPanel().setLayout(new GridLayout(fechas.size() + 1, tipoPago.size() + 1, 0, 0));

		JLabel label;

		for (int i = -1; i < fechas.size(); i++) {
			for (int j = -1; j < tipoPago.size(); j++) {
				if (i == -1) {
					if (j == -1) {
						label = new JLabel();
						label.setText("");

						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));

					} else {
						label = new JLabel();
						label.setText(tipoPago.get(j));
						label.setForeground(new Color(255, 140, 0));
						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));
						label.setFont(new Font("Arial", Font.BOLD, 18));

					}
				}

				else {
					if (j == -1) {
						label = new JLabel();
						label.setText(fechas.get(i));
						label.setForeground(Color.BLUE);
						label.setFont(new Font("Arial", Font.BOLD, 18));

						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));

					}

					else {
						label = new JLabel();
						label.setText(String.format("%.2f",
								Util.sumaImportes(vm.getVentasByTipoPagoAndFecha(fechas.get(i), tipoPago.get(j)))));
						label.setFont(new Font("Arial", Font.PLAIN, 16));

					}
				}

				label.setBorder(new LineBorder(new Color(0, 0, 0), 1));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				view.getPanel().add(label);

			}

		}
		
		view.getLbTitulo().setText("Informe por tipo de pago");

		view.getFrame().revalidate();

	}
	
	
	public void crearInformeTipoUsuario() {
		List<VentaEntity> ventas = vm.getVentas();
		List<String> fechas = new ArrayList<>();
		List<String> tipoUsuario = new ArrayList<>();

		for (VentaEntity venta : ventas) {
			if (!fechas.contains(venta.getFecha())) {
				fechas.add(venta.getFecha());
			}

			if (!tipoUsuario.contains(venta.getTipoUsuario())) {
				tipoUsuario.add(venta.getTipoUsuario());
			}

		}

		Collections.sort(fechas);

		view.getPanel().setLayout(new GridLayout(fechas.size() + 1, tipoUsuario.size() + 1, 0, 0));

		JLabel label;

		for (int i = -1; i < fechas.size(); i++) {
			for (int j = -1; j < tipoUsuario.size(); j++) {
				if (i == -1) {
					if (j == -1) {
						label = new JLabel();
						label.setText("");

						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));

					} else {
						label = new JLabel();
						label.setText(tipoUsuario.get(j));
						label.setForeground(new Color(255, 140, 0));
						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));
						label.setFont(new Font("Arial", Font.BOLD, 18));

					}
				}

				else {
					if (j == -1) {
						label = new JLabel();
						label.setText(fechas.get(i));
						label.setForeground(Color.BLUE);
						label.setFont(new Font("Arial", Font.BOLD, 18));

						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));

					}

					else {
						label = new JLabel();
						label.setText(String.format("%.2f",
								Util.sumaImportes(vm.getVentasByTipoUsuarioAndFecha(fechas.get(i), tipoUsuario.get(j)))));
						label.setFont(new Font("Arial", Font.PLAIN, 16));

					}
				}

				label.setBorder(new LineBorder(new Color(0, 0, 0), 1));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				view.getPanel().add(label);

			}

		}
		view.getLbTitulo().setText("Informe por tipo de usuario");
		view.getFrame().revalidate();

	}
	
	public void crearInformeEmpresa() {
		List<VentaEntity> ventas = vm.getVentas();
		List<String> fechas = new ArrayList<>();
		List<String> empresa = new ArrayList<>();

		for (VentaEntity venta : ventas) {
			if (!fechas.contains(venta.getFecha())) {
				fechas.add(venta.getFecha());
			}

			if(venta.getEmpresa()!=null) {
				if (!empresa.contains(venta.getEmpresa())) {
					empresa.add(venta.getEmpresa());
				}
				
			}

		}

		Collections.sort(fechas);

		view.getPanel().setLayout(new GridLayout(fechas.size() + 1, empresa.size() + 1, 0, 0));

		JLabel label;

		for (int i = -1; i < fechas.size(); i++) {
			for (int j = -1; j < empresa.size(); j++) {
				if (i == -1) {
					if (j == -1) {
						label = new JLabel();
						label.setText("");

						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));

					} else {
						label = new JLabel();
						label.setText(empresa.get(j));
						label.setForeground(new Color(255, 140, 0));
						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));
						label.setFont(new Font("Arial", Font.BOLD, 18));

					}
				}

				else {
					if (j == -1) {
						label = new JLabel();
						label.setText(fechas.get(i));
						label.setForeground(Color.BLUE);
						label.setFont(new Font("Arial", Font.BOLD, 18));

						label.setOpaque(true);
						label.setBackground(new Color(211, 211, 211));

					}

					else {
						label = new JLabel();
						label.setText(String.format("%.2f",
								Util.sumaImportes(vm.getVentasByEmpresaAndFecha(fechas.get(i), empresa.get(j)))));
						label.setFont(new Font("Arial", Font.PLAIN, 16));

					}
				}

				label.setBorder(new LineBorder(new Color(0, 0, 0), 1));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				view.getPanel().add(label);

			}

		}
		view.getLbTitulo().setText("Informe por empresa");
		view.getFrame().revalidate();

	}
	
	
	
	
}

