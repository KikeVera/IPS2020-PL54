package ui.almacen;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Comparacion extends JDialog {

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
	private JTextField textField;
	private JLabel lbNombre_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lbDescripcion_1;
	private JLabel lblPrecio_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comparacion frame = new Comparacion();
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
	public Comparacion() {
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
			pnAlmacen.add(getTextField());
			pnAlmacen.add(getLbNombre_1());
			pnAlmacen.add(getTextField_1());
			pnAlmacen.add(getTextField_2());
			pnAlmacen.add(getLbDescripcion_1());
			pnAlmacen.add(getLblPrecio_1());
			pnAlmacen.add(getTextField_3());
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
		}
		return txId;
	}
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setColumns(10);
			txNombre.setBounds(129, 87, 86, 20);
		}
		return txNombre;
	}
	private JTextField getTxDescripcion() {
		if (txDescripcion == null) {
			txDescripcion = new JTextField();
			txDescripcion.setColumns(10);
			txDescripcion.setBounds(129, 127, 86, 20);
		}
		return txDescripcion;
	}
	private JTextField getTxprecio() {
		if (txprecio == null) {
			txprecio = new JTextField();
			txprecio.setColumns(10);
			txprecio.setBounds(129, 174, 86, 20);
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
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(129, 50, 86, 20);
		}
		return textField;
	}
	private JLabel getLbNombre_1() {
		if (lbNombre_1 == null) {
			lbNombre_1 = new JLabel("Nombre:");
			lbNombre_1.setFont(new Font("Arial", Font.PLAIN, 13));
			lbNombre_1.setBounds(10, 89, 59, 14);
		}
		return lbNombre_1;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(129, 87, 86, 20);
		}
		return textField_1;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(129, 127, 86, 20);
		}
		return textField_2;
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
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(129, 174, 86, 20);
		}
		return textField_3;
	}
}
