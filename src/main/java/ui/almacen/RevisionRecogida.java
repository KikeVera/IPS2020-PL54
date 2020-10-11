package ui.almacen;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import negocio.almacen.Recogida;
import util.producto.ProductoEntity;
import util.producto.ProductosModel;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

import javax.swing.JButton;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class RevisionRecogida extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbPedido;
	private JComboBox <ProductoEntity> cbPedido;
	private JButton btComprobar;
	private JButton btSalir;
	private JButton btIncidencia;
	private Recogida recogida;
	private List<ProductoEntity> almacen= new ProductosModel().getListaProductos();

	

	/**
	 * Create the frame.
	 */
	public RevisionRecogida(Recogida recogida) {
		this.recogida=recogida;
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
		contentPane.add(getCbPedido());
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
	private JComboBox <ProductoEntity>  getCbPedido() {
		if (cbPedido == null) {
			cbPedido = new JComboBox <ProductoEntity>();
			cbPedido.setModel(new DefaultComboBoxModel <ProductoEntity>(getPedido()));
			cbPedido.setBounds(new Rectangle(10, 75, 170, 21));
			cbPedido.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return cbPedido;
	}
	
	public ProductoEntity[] getPedido(){
		List<ProductoEntity> listaProductos= recogida.getPedido();
		ProductoEntity[] arrayProductos= new ProductoEntity[listaProductos.size()];
		return listaProductos.toArray(arrayProductos);
		
	}
	
	private JButton getBtComprobar() {
		if (btComprobar == null) {
			btComprobar = new JButton("Comprobar");
			btComprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Comparacion c = new Comparacion(getPPedido(),getPAlmacen());
					c.setModal(true);
					c.setVisible(true);
				}
			});
			btComprobar.setBounds(190, 74, 143, 23);
			btComprobar.setFont(new Font("Arial", Font.PLAIN, 13));
		}
		return btComprobar;
	}
	
	private ProductoEntity getPPedido() {
		return (ProductoEntity) cbPedido.getSelectedItem();
		
	}
	
	private ProductoEntity getPAlmacen() {
		for(ProductoEntity producto: almacen) {
			if(producto.getId()==getPPedido().getId()) {
				return producto;
			}
		}
		return new ProductoEntity(0,"Producto no encontrado","",0);
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
					IncidenciaUI i = new IncidenciaUI(recogida);
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
