package ui.pedidos;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesPedidoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnVolver;
	private JButton btnAsignar;
	JTextPane textDetalles;

	/**
	 * Create the frame.
	 */
	public DetallesPedidoView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnVolver());
		contentPane.add(getBtnAsignar());
		contentPane.add(getTextDetalles());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Detalles");
			lblNewLabel.setBounds(33, 11, 46, 14);
		}
		return lblNewLabel;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListaView listav=new ListaView();
					setVisible(false);
					listav.setVisible(true);
				}
			});
			btnVolver.setBounds(21, 154, 89, 23);
		}
		return btnVolver;
	}
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.setBounds(143, 154, 89, 23);
		}
		return btnAsignar;
	}
	private JTextPane getTextDetalles() {
		if (textDetalles == null) {
			textDetalles = new JTextPane();
			textDetalles.setEditable(false);
			textDetalles.setBounds(21, 36, 211, 107);
		}
		return textDetalles;
	}
}
