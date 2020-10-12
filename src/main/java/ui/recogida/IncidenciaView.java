package ui.recogida;





import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.almacen.Incidencia;
import negocio.almacen.Recogida;


import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class IncidenciaView extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbIncidencia;
	private Recogida recogida;
	private JTextArea txIncidencia;
	private JPanel pnBotones;
	private JButton btSalir;
	private JButton btAñadir;

	

	/**
	 * Create the frame.
	 */
	public IncidenciaView(Recogida recogida) {
		this.recogida=recogida;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 413);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLbIncidencia(), BorderLayout.NORTH);
		contentPane.add(getTxIncidencia());
		contentPane.add(getPnBotones(), BorderLayout.SOUTH);
	}
	private JLabel getLbIncidencia() {
		if (lbIncidencia == null) {
			lbIncidencia = new JLabel("Describa la incidencia:");
			lbIncidencia.setFont(new Font("Arial Black", Font.PLAIN, 16));
		}
		return lbIncidencia;
	}
	private JTextArea getTxIncidencia() {
		if (txIncidencia == null) {
			txIncidencia = new JTextArea();
			txIncidencia.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return txIncidencia;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setLayout(new GridLayout(1, 0, 0, 0));
			pnBotones.add(getBtCacelar_1());
			pnBotones.add(getBtAñadir_1());
		}
		return pnBotones;
	}
	private JButton getBtCacelar_1() {
		if (btSalir == null) {
			btSalir = new JButton("Salir");
			btSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btSalir.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return btSalir;
	}
	private JButton getBtAñadir_1() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir Incidencia");
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirIncidencia();
				}
			});
			btAñadir.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		return btAñadir;
	}
	
	private void añadirIncidencia() {
		if(txIncidencia.getText().trim().length()!=0) {
			int resp=JOptionPane.showConfirmDialog(this, "¿Está seguro de añadir esta incidencia?","Confirmar incidencia",JOptionPane.YES_NO_OPTION);
			if(resp==JOptionPane.YES_OPTION)
				recogida.setIncidencia(new Incidencia(txIncidencia.getText()));
		
		}
		
	}
}
