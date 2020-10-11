package ui.almacen;


import java.awt.Color;

import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import util.producto.ProductoEntity;

public class Comparacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JPanel pnPedido;
	private JPanel pnAlmacen;
	private JLabel lbID;
	private JLabel lbNombre;
	private JLabel lbDescripcion;
	private JLabel lblPrecio;
	private JTextField txId;
	private JTextField txNombre;
	private JTextField txDescripcion;
	private JTextField txprecio;
	private JLabel lbID_1;
	private JTextField txIdAlmacen;
	private JLabel lbNombre_1;
	private JTextField txIdNombre;
	private JTextField txIdDescripccion;
	private JLabel lbDescripcion_1;
	private JLabel lblPrecio_1;
	private JTextField txIdPrecio;
	ProductoEntity pPedido; 
	ProductoEntity pAlmacen;
	
	

	
	/**
	 * Create the frame.
	 */
	public Comparacion(ProductoEntity pPedido ,ProductoEntity pAlmacen) {
		this.pPedido=pPedido;
		this.pAlmacen=pAlmacen;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getPnPedido());
		contentPane.add(getPnAlmacen());
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Aceptar");
			btnNewButton.setBounds(460, 378, 135, 23);
		}
		return btnNewButton;
	}
	private JPanel getPnPedido() {
		if (pnPedido == null) {
			pnPedido = new JPanel();
			pnPedido.setBorder(new TitledBorder(null, "En pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPedido.setBounds(23, 44, 246, 263);
			pnPedido.setLayout(null);
			pnPedido.add(getLbID());
			pnPedido.add(getLbNombre());
			pnPedido.add(getLbDescripcion());
			pnPedido.add(getLblPrecio());
			pnPedido.add(getTxId());
			pnPedido.add(getTxNombre());
			pnPedido.add(getTxDescripcion());
			pnPedido.add(getTxprecio());
		}
		return pnPedido;
	}
	private JPanel getPnAlmacen() {
		if (pnAlmacen == null) {
			pnAlmacen = new JPanel();
			pnAlmacen.setBorder(new TitledBorder(null, "En almacen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnAlmacen.setBounds(325, 44, 251, 263);
			pnAlmacen.setLayout(null);
			pnAlmacen.add(getLbID_1());
			pnAlmacen.add(getTxIdAlmacen());
			pnAlmacen.add(getLbNombre_1());
			pnAlmacen.add(getTxIdNombre());
			pnAlmacen.add(getTxIdDescripccion());
			pnAlmacen.add(getLbDescripcion_1());
			pnAlmacen.add(getLblPrecio_1());
			pnAlmacen.add(getTxIdPrecio());
		}
		return pnAlmacen;
	}
	private JLabel getLbID() {
		if (lbID == null) {
			lbID = new JLabel("Id:");
			lbID.setFont(new Font("Arial", Font.PLAIN, 13));
			lbID.setBounds(10, 52, 46, 14);
		}
		return lbID;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre:");
			lbNombre.setFont(new Font("Arial", Font.PLAIN, 13));
			lbNombre.setBounds(10, 89, 59, 14);
		}
		return lbNombre;
	}
	private JLabel getLbDescripcion() {
		if (lbDescripcion == null) {
			lbDescripcion = new JLabel("Descripcion: ");
			lbDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
			lbDescripcion.setBounds(10, 129, 76, 14);
		}
		return lbDescripcion;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio: ");
			lblPrecio.setFont(new Font("Arial", Font.PLAIN, 13));
			lblPrecio.setBounds(10, 176, 46, 14);
		}
		return lblPrecio;
	}
	private JTextField getTxId() {
		if (txId == null) {
			txId = new JTextField();
			txId.setBounds(129, 50, 86, 20);
			txId.setColumns(10);
			txId.setText(Integer.toString(pPedido.getId()));
		}
		return txId;
	}
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setColumns(10);
			txNombre.setBounds(129, 87, 86, 20);
			txNombre.setText(pPedido.getNombre());
		}
		return txNombre;
	}
	private JTextField getTxDescripcion() {
		if (txDescripcion == null) {
			txDescripcion = new JTextField();
			txDescripcion.setColumns(10);
			txDescripcion.setBounds(129, 127, 86, 20);
			txDescripcion.setText(pPedido.getDescripcion());
		}
		return txDescripcion;
	}
	private JTextField getTxprecio() {
		if (txprecio == null) {
			txprecio = new JTextField();
			txprecio.setColumns(10);
			txprecio.setBounds(129, 174, 86, 20);
			txprecio.setText(Double.toString(pPedido.getPrecio()));
		}
		return txprecio;
	}
	private JLabel getLbID_1() {
		if (lbID_1 == null) {
			lbID_1 = new JLabel("Id:");
			lbID_1.setFont(new Font("Arial", Font.PLAIN, 13));
			lbID_1.setBounds(10, 52, 46, 14);
		}
		return lbID_1;
	}
	private JTextField getTxIdAlmacen() {
		if (txIdAlmacen == null) {
			txIdAlmacen = new JTextField();
			txIdAlmacen.setColumns(10);
			txIdAlmacen.setBounds(129, 50, 86, 20);
			txIdAlmacen.setText(Integer.toString(pAlmacen.getId()));
		}
		return txIdAlmacen;
	}
	private JLabel getLbNombre_1() {
		if (lbNombre_1 == null) {
			lbNombre_1 = new JLabel("Nombre:");
			lbNombre_1.setFont(new Font("Arial", Font.PLAIN, 13));
			lbNombre_1.setBounds(10, 89, 59, 14);
		}
		return lbNombre_1;
	}
	private JTextField getTxIdNombre() {
		if (txIdNombre == null) {
			txIdNombre = new JTextField();
			txIdNombre.setColumns(10);
			txIdNombre.setBounds(129, 87, 86, 20);
			txIdNombre.setText(pAlmacen.getNombre());
		}
		return txIdNombre;
	}
	private JTextField getTxIdDescripccion() {
		if (txIdDescripccion == null) {
			txIdDescripccion = new JTextField();
			txIdDescripccion.setColumns(10);
			txIdDescripccion.setBounds(129, 127, 86, 20);
			txIdDescripccion.setText(pAlmacen.getDescripcion());
		}
		return txIdDescripccion;
	}
	private JLabel getLbDescripcion_1() {
		if (lbDescripcion_1 == null) {
			lbDescripcion_1 = new JLabel("Descripcion: ");
			lbDescripcion_1.setFont(new Font("Arial", Font.PLAIN, 13));
			lbDescripcion_1.setBounds(10, 129, 76, 14);
		}
		return lbDescripcion_1;
	}
	private JLabel getLblPrecio_1() {
		if (lblPrecio_1 == null) {
			lblPrecio_1 = new JLabel("Precio: ");
			lblPrecio_1.setFont(new Font("Arial", Font.PLAIN, 13));
			lblPrecio_1.setBounds(10, 176, 46, 14);
		}
		return lblPrecio_1;
	}
	private JTextField getTxIdPrecio() {
		if (txIdPrecio == null) {
			txIdPrecio = new JTextField();
			txIdPrecio.setColumns(10);
			txIdPrecio.setBounds(129, 174, 86, 20);
			txIdPrecio.setText(Double.toString(pAlmacen.getPrecio()));
		}
		return txIdPrecio;
	}
}
