package logica.pagoPedido;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import logica.Controller;
import ui.pagoPedido.PagoPedidoView;
import ui.producto.ProductosView;

/**
 * Es el controlador de la ventana de pago de pedido , accesible desde la tienda
 * online
 * 
 * @author Moises
 *
 */
public class PagoPedidoController implements Controller {

	private PagoPedidoView view; // Vista de la ventana de pago pedido
	private ProductosView tienda; // Referencia a la vista de la tienda online

	/**
	 * Constructor
	 * 
	 * @param v      Vista
	 * @param tienda Referencia a la vista de la tienda online
	 */
	public PagoPedidoController(PagoPedidoView v, ProductosView tienda) {
		this.view = v;
		this.tienda = tienda;
		this.initController();
	}

	/**
	 * Inicia los controladores
	 */
	@Override
	public void initController() {
		this.view.getBtnSiguienteInicio().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		this.view.getBtnAtrasInicio().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		this.view.getBtnSiguienteTarjeta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkTarjeta();
			}
		});
		this.view.getBtnAtrasTarjeta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		this.view.getBtnSiguienteTransaccion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tienda.getBtnFinalizarPedido().setEnabled(true);
				tienda.getBtnPagarPedido().setEnabled(false);
				tienda.getBtnAnadir().setEnabled(false);
				tienda.getBtnEliminar().setEnabled(false);
				cerrar();
			}
		});
		this.view.getBtnAtrasTransaccion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});

	}

	/**
	 * Inicia la vista
	 */
	@Override
	public void initView() {
		
		//Para visualizar el precio del pedido 
		String precio = this.tienda.getTextPrecio().getText(); 
		this.view.getTextImporteInicio().setText(precio);
		this.view.getTextImporteTarjeta().setText(precio);
		this.view.getTextImporteTransaccion().setText(precio);
		
		//Iniciamos vista 
		this.view.getFrame().setVisible(true);
	}

	/**
	 * Avanza al siguiente panel seleccionado
	 */
	private void next() {

		CardLayout c = (CardLayout) this.view.getFrame().getContentPane().getLayout();

		if (this.view.getRdbTarjeta().isSelected()) {
			c.show(this.view.getFrame().getContentPane(), "Tarjeta");
		} else if (this.view.getRdbTransferencia().isSelected()) {
			c.show(this.view.getFrame().getContentPane(), "Transaccion");
		} else if (this.view.getRdbContrareembolso().isSelected()) {
			this.tienda.getBtnFinalizarPedido().setEnabled(true);
			tienda.getBtnPagarPedido().setEnabled(false);
			tienda.getBtnAnadir().setEnabled(false);
			tienda.getBtnEliminar().setEnabled(false);
			cerrar();
		} else {
			JOptionPane.showMessageDialog(this.view.getFrame(), "Debe seleccionar un metodo de pago",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}

	}

	/**
	 * Retrocede al anterior panel
	 */
	private void back() {
		CardLayout c = (CardLayout) this.view.getFrame().getContentPane().getLayout();
		c.show(this.view.getFrame().getContentPane(), "Inicio");
	}

	/**
	 * Cierra la ventana del pago de pedido actual
	 */
	private void cerrar() {
		this.view.getFrame().dispose();
	}

	/**
	 * Comprueba que los datos de la tarjeta introducidos por el usuario son
	 * correctos y en caso de que las validaciones salieran correctamente, habilita
	 * la opcion de finalizar pedido en la tienda online
	 */
	private void checkTarjeta() {

		// El numero de tarjeta no debe de ser vacio
		if (view.getTextNumeroTarjeta().getText().isBlank() || view.getTextNumeroTarjeta().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this.view.getFrame(), "No debe dejar el numero de la tarjeta vacío",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// El CVV no debe de ser vacio
		if (view.getTextCVV().getText().isBlank() || view.getTextCVV().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this.view.getFrame(), "No debe dejar el CVV de la tarjeta vacío",
					"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// El numero de tarjeta debe de ser un campo numerico
		for (int i = 0; i < view.getTextNumeroTarjeta().getText().length(); i++) {
			if (!Character.isDigit(view.getTextNumeroTarjeta().getText().charAt(i))) {
				JOptionPane.showMessageDialog(this.view.getFrame(), "El numero de la tarjeta es un campo numerico",
						"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}

		// El CVV debe de ser un campo numerico
		for (int i = 0; i < view.getTextCVV().getText().length(); i++) {
			if (!Character.isDigit(view.getTextCVV().getText().charAt(i))) {
				JOptionPane.showMessageDialog(this.view.getFrame(), "El CVV de la tarjeta es un campo numerico",
						"Tienda online: Advertencia", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}

		// El numero de tarjeta debe estar compuesto de 16 digitos
		if (view.getTextNumeroTarjeta().getText().length() != 16) {
			JOptionPane.showMessageDialog(this.view.getFrame(),
					"El número de la tarjeta debe estar compuesto por 16 dígitos", "Tienda online: Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// El CVV debe estar compuesto de 3 digitos
		if (view.getTextCVV().getText().length() != 3) {
			JOptionPane.showMessageDialog(this.view.getFrame(),
					"El CVV de la tarjeta debe estar compuesto por 3 dígitos", "Tienda online: Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Si todo se valido correctamente
		tienda.getBtnFinalizarPedido().setEnabled(true);
		tienda.getBtnPagarPedido().setEnabled(false);
		tienda.getBtnAnadir().setEnabled(false);
		tienda.getBtnEliminar().setEnabled(false);
		cerrar();
	}

}
