package ui.almacen;





import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.almacen.Incidencia;
import negocio.almacen.Recogida;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JButton btCacelar;
	private Recogida recogida;

	

	/**
	 * Create the frame.
	 */
	public IncidenciaUI(Recogida recogida) {
		this.recogida=recogida;
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
		contentPane.add(getBtCacelar());
	}
	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir Incidencia");
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirIncidencia();
				}
			});
			btAñadir.setFont(new Font("Arial", Font.PLAIN, 13));
			btAñadir.setBounds(470, 340, 131, 23);
		}
		return btAñadir;
	}
	
	private void añadirIncidencia() {
		if(!txIncidencia.getText().trim().equals("")) {
			recogida.setIncidencia(new Incidencia(txIncidencia.getText()));
			
		}
		
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
	private JButton getBtCacelar() {
		if (btCacelar == null) {
			btCacelar = new JButton("Cancelar");
			btCacelar.setFont(new Font("Arial", Font.PLAIN, 13));
			btCacelar.setBounds(328, 340, 131, 23);
		}
		return btCacelar;
	}
}
