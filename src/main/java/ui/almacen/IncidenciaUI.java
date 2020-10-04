package ui.almacen;


import java.awt.EventQueue;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JLabel;

public class IncidenciaUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btAñadir;
	private JTextField txIncidencia;
	private JLabel lbIncidencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncidenciaUI frame = new IncidenciaUI();
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
	public IncidenciaUI() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 413);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtAñadir());
		contentPane.add(getTxIncidencia());
		contentPane.add(getLbIncidencia());
	}
	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir Incidencia");
			btAñadir.setFont(new Font("Arial", Font.PLAIN, 13));
			btAñadir.setBounds(457, 340, 144, 23);
		}
		return btAñadir;
	}
	private JTextField getTxIncidencia() {
		if (txIncidencia == null) {
			txIncidencia = new JTextField();
			txIncidencia.setFont(new Font("Arial", Font.PLAIN, 13));
			txIncidencia.setBounds(132, 84, 329, 139);
			txIncidencia.setColumns(10);
		}
		return txIncidencia;
	}
	private JLabel getLbIncidencia() {
		if (lbIncidencia == null) {
			lbIncidencia = new JLabel("Describa la incidencia");
			lbIncidencia.setFont(new Font("Arial", Font.PLAIN, 16));
			lbIncidencia.setBounds(129, 59, 173, 14);
		}
		return lbIncidencia;
	}
}
