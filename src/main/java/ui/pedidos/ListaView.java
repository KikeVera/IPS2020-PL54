package ui.pedidos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.pedido.PedidoEntity;
import util.pedido.PedidosModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractListModel;

public class ListaView extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblPedido;
	private JLabel lblNewLabel_1;
	private JList listPedidos;
	private JButton btnActualizar;
	private JButton btnAceptar;
	
	PedidosModel pedmodel;
	List<PedidoEntity> pedidos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaView frame = new ListaView();
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
	public ListaView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 278, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblPedido());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getListPedidos());
		contentPane.add(getBtnActualizar());
		contentPane.add(getBtnAceptar());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Fecha");
			lblNewLabel.setBounds(20, 11, 53, 25);
		}
		return lblNewLabel;
	}
	private JLabel getLblPedido() {
		if (lblPedido == null) {
			lblPedido = new JLabel("Pedido");
			lblPedido.setBounds(86, 11, 43, 25);
		}
		return lblPedido;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Cantidad");
			lblNewLabel_1.setBounds(162, 16, 74, 14);
		}
		return lblNewLabel_1;
	}
	private JList getListPedidos() {
		if (listPedidos == null) {
			listPedidos = new JList();
			listPedidos.setModel(new AbstractListModel() {
				String[] values = new String[] {};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			listPedidos.setSelectedIndex(0);
			listPedidos.setBounds(10, 47, 210, 124);
		}
	
		return listPedidos;
	}
	private JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					DefaultListModel<String> dlm =new DefaultListModel<String>();
					pedidos=pedmodel.getPedidos();

					for(int i=0;i<pedidos.size();i++) {
						dlm.addElement(pedidos.get(i).getFecha() + "  " +pedidos.get(i).getId() + " " +pedidos.get(i).getTamaño());
					}
					
					listPedidos.setModel(dlm);
				}
			});
			btnActualizar.setBounds(10, 180, 105, 23);
		}
		return btnActualizar;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {						
					Object o=listPedidos.getSelectedValue();
					DetallesPedidoView dp=new DetallesPedidoView();
					dp.textDetalles.setText("Nothing");
					dp.setVisible(true);
					setVisible(false);
				}
			});
			btnAceptar.setBounds(131, 180, 89, 23);
		}
		return btnAceptar;
	}
}
