package ui.almacen;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.almacen.Incidencia;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RevisionRecogida extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbPedido;
	private JComboBox comboBox;
	private JButton btComprobar;
	private JButton btSalir;
	private JButton btIncidencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RevisionRecogida frame = new RevisionRecogida();
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
	public RevisionRecogida() {
		setBackground(Color.WHITE);
		setTitle("Revision");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 449);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbPedido());
		contentPane.add(getBtComprobar());
		contentPane.add(getBtIncidencia());
		contentPane.add(getComboBox());
		contentPane.add(getBtSalir());
	}
	private JLabel getLbPedido() {
		if (lbPedido == null) {
			lbPedido = new JLabel("Pedido:");
			lbPedido.setBounds(10, 44, 55, 19);
			lbPedido.setFont(new Font("Arial", Font.PLAIN, 16));
		}
		return lbPedido;
	}
	private JComboBox  getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(new Rectangle(10, 75, 170, 21));
			comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return comboBox;
	}
	private JButton getBtComprobar() {
		if (btComprobar == null) {
			btComprobar = new JButton("Comprobar");
			btComprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Comparacion c = new Comparacion();
					c.setModal(true);
					c.setVisible(true);
				}
			});
			btComprobar.setBounds(190, 74, 143, 23);
			btComprobar.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return btComprobar;
	}
	private JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton("Salir");
			btSalir.setBounds(575, 376, 113, 23);
			btSalir.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return btSalir;
	}
	private JButton getBtIncidencia() {
		if (btIncidencia == null) {
			btIncidencia = new JButton("Incidencia");
			btIncidencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IncidenciaUI i = new IncidenciaUI();
					i.setModal(true);
					i.setVisible(true);
				}
			});
			btIncidencia.setBounds(343, 74, 131, 23);
			btIncidencia.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return btIncidencia;
	}
}
