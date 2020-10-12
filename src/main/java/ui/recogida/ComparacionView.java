package ui.recogida;


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
import util.producto.ProductoPedido;
import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComparacionView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	ProductoPedido pPedido; 
	ProductoEntity pAlmacen;
	private JPanel pnProductos;
	private JPanel pnAlmacen;
	private JLabel lbIDAlmacen;
	private JTextField txIDAlmacen;
	private JLabel lbNombreAlmacen;
	private JTextField txNombreAlmacen;
	private JTextField texDescripcionAlmacen;
	private JLabel lbDescripcion_1;
	private JLabel lblPrecio_1;
	private JTextField txPrecioAlmacen;
	private JPanel pnPedido;
	private JLabel lbID;
	private JLabel lbNombre;
	private JLabel lbDescripcion;
	private JLabel lblPrecio;
	private JTextField textID;
	private JTextField txNombre;
	private JTextField txDecripcion;
	private JTextField txPrecio;
	
	

	
	/**
	 * Create the frame.
	 */
	public ComparacionView(ProductoPedido pPedido ,ProductoEntity pAlmacen) {
		this.pPedido=pPedido;
		this.pAlmacen=pAlmacen;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getBtnNewButton(), BorderLayout.SOUTH);
		contentPane.add(getPnProductos());
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Aceptar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnNewButton;
	}
	private JPanel getPnProductos() {
		if (pnProductos == null) {
			pnProductos = new JPanel();
			pnProductos.setLayout(new GridLayout(0, 2, 0, 0));
			pnProductos.add(getPnAlmacen_1());
			pnProductos.add(getPnPedido_1());
		}
		return pnProductos;
	}
	private JPanel getPnAlmacen_1() {
		if (pnAlmacen == null) {
			pnAlmacen = new JPanel();
			pnAlmacen.setBorder(new TitledBorder(null, "En almacen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnAlmacen.setLayout(new GridLayout(0, 2, 0, 0));
			pnAlmacen.add(getLbIDAlmacen());
			pnAlmacen.add(getTxIDAlmacen());
			pnAlmacen.add(getLbNombre_1_1());
			pnAlmacen.add(getTxNombreAlmacen());
			pnAlmacen.add(getLbDescripcion_1_1());
			pnAlmacen.add(getTexDescripcionAlmacen());
			pnAlmacen.add(getLblPrecio_1_1());
			pnAlmacen.add(getTxPrecioAlmacen());
		}
		return pnAlmacen;
	}
	private JLabel getLbIDAlmacen() {
		if (lbIDAlmacen == null) {
			lbIDAlmacen = new JLabel("Id:");
			lbIDAlmacen.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return lbIDAlmacen;
	}
	private JTextField getTxIDAlmacen() {
		if (txIDAlmacen == null) {
			txIDAlmacen = new JTextField();
			txIDAlmacen.setFont(new Font("Arial", Font.PLAIN, 13));
			txIDAlmacen.setText("0");
			txIDAlmacen.setColumns(10);
			txIDAlmacen.setText(Integer.toString(pAlmacen.getId()));
		}
		return txIDAlmacen;
	}
	private JLabel getLbNombre_1_1() {
		if (lbNombreAlmacen == null) {
			lbNombreAlmacen = new JLabel("Nombre:");
			lbNombreAlmacen.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return lbNombreAlmacen;
	}
	private JTextField getTxNombreAlmacen() {
		if (txNombreAlmacen == null) {
			txNombreAlmacen = new JTextField();
			txNombreAlmacen.setFont(new Font("Arial", Font.PLAIN, 13));
			txNombreAlmacen.setText((String) null);
			txNombreAlmacen.setColumns(10);
			txNombreAlmacen.setText(pAlmacen.getNombre());
		}
		return txNombreAlmacen;
	}
	private JTextField getTexDescripcionAlmacen() {
		if (texDescripcionAlmacen == null) {
			texDescripcionAlmacen = new JTextField();
			texDescripcionAlmacen.setFont(new Font("Arial", Font.PLAIN, 13));
			texDescripcionAlmacen.setText((String) null);
			texDescripcionAlmacen.setColumns(10);
			texDescripcionAlmacen.setText(pAlmacen.getDescripcion());
		}
		return texDescripcionAlmacen;
	}
	private JLabel getLbDescripcion_1_1() {
		if (lbDescripcion_1 == null) {
			lbDescripcion_1 = new JLabel("Descripcion: ");
			lbDescripcion_1.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return lbDescripcion_1;
	}
	private JLabel getLblPrecio_1_1() {
		if (lblPrecio_1 == null) {
			lblPrecio_1 = new JLabel("Precio: ");
			lblPrecio_1.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return lblPrecio_1;
	}
	private JTextField getTxPrecioAlmacen() {
		if (txPrecioAlmacen == null) {
			txPrecioAlmacen = new JTextField();
			txPrecioAlmacen.setFont(new Font("Arial", Font.PLAIN, 13));
			txPrecioAlmacen.setText("0.0");
			txPrecioAlmacen.setColumns(10);
			txPrecioAlmacen.setText(Double.toString(pAlmacen.getPrecio()));
		}
		return txPrecioAlmacen;
	}
	private JPanel getPnPedido_1() {
		if (pnPedido == null) {
			pnPedido = new JPanel();
			pnPedido.setBorder(new TitledBorder(null, "En pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPedido.setLayout(new GridLayout(0, 2, 0, 0));
			pnPedido.add(getLbID_1());
			pnPedido.add(getTextID());
			pnPedido.add(getLbNombre_1());
			pnPedido.add(getTxNombre());
			pnPedido.add(getLbDescripcion_1());
			pnPedido.add(getTxDecripcion());
			pnPedido.add(getLblPrecio_1());
			pnPedido.add(getTxPrecio());
		}
		return pnPedido;
	}
	private JLabel getLbID_1() {
		if (lbID == null) {
			lbID = new JLabel("Id:");
			lbID.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return lbID;
	}
	private JLabel getLbNombre_1() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre:");
			lbNombre.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return lbNombre;
	}
	private JLabel getLbDescripcion_1() {
		if (lbDescripcion == null) {
			lbDescripcion = new JLabel("Descripcion: ");
			lbDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return lbDescripcion;
	}
	private JLabel getLblPrecio_1() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio: ");
			lblPrecio.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return lblPrecio;
	}
	private JTextField getTextID() {
		if (textID == null) {
			textID = new JTextField();
			textID.setText("0");
			textID.setColumns(10);
			textID.setText(Integer.toString(pPedido.getId()));
		}
		return textID;
	}
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setText((String) null);
			txNombre.setColumns(10);
			txNombre.setText(pPedido.getNombre());
		}
		return txNombre;
	}
	private JTextField getTxDecripcion() {
		if (txDecripcion == null) {
			txDecripcion = new JTextField();
			txDecripcion.setText((String) null);
			txDecripcion.setColumns(10);
			txDecripcion.setText(pPedido.getDescripcion());
		}
		return txDecripcion;
	}
	private JTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JTextField();
			txPrecio.setText("0.0");
			txPrecio.setColumns(10);
			txPrecio.setText(Double.toString(pPedido.getPrecio()));
		}
		return txPrecio;
	}
}
