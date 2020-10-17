package logica.paquete;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.pedido.PedidoUse;
import logica.producto.ProductoOT;
import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.paquete.PaqueteModel;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;

import ui.paquete.PaqueteView;

import util.Util;
import util.swingTables.SwingUtil;

public class PaqueteController {
	
	private ProductosModel model; 
	
	private PaqueteView view;
	private OTEntity ot;
	private Empaquetado empaquetado;
	
	private PedidosModel pem;
	
	private OTModel otm;
	
	private PaqueteModel pam;

	
	
	
	
	
	public PaqueteController(ProductosModel m,PedidosModel pem,OTModel otm,PaqueteModel pam, PaqueteView v, OTEntity ot) {
		this.ot=ot;
		this.model = m; 
		this.view = v;  
		
		this.pem=pem;
		this.otm=otm;
		this.pam=pam;
		List<Integer> idPedidos=new ArrayList<>();
		
		List<PedidoUse> Pedidos=new ArrayList<>();
		
		List<ProductoEntity> catalogo=model.getListaProductos();
		
		idPedidos.add(ot.getIdPedido());
		
		for(Integer id: idPedidos) {
			Pedidos.add(Util.entityToUse(this.pem.getPedidoID(id)));
		}
		
	
		empaquetado= new Empaquetado(Pedidos,catalogo);
		this.initView();
	}
	
	public void initView() {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		
		
		//Inicializamos la tabla que representara al pedido 
		inicializarTabla();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);
		
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador.
	 */
	public void initController() {
		
		
		
		
		
		this.view.getBtCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				view.getFrame().dispose();
				
				
				
			}
		});
		
		this.view.getBtEmpaquetar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				escanearProducto();
				
				
			}
		});
		
		this.view.getBtTerminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				empaquetarPedidos();
				otm.updateStatus(ot.getIdOt(), "TERMINADO");
				view.getFrame().dispose();
				
				
				
				
			}
		});
		
		
		
		this.view.getTablePedidos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateDetail();
			}
		});
		
	
		
	}
	
	private void empaquetarPedidos() {
		for(PedidoUse pedido: empaquetado.getPedidos()) {
			pam.createPaquete(pedido.getId());
		}
	}
	
	
	private void escanearProducto() {
		
		String idProductoTx=view.getTxIDProducto().getText();
		if(idProductoTx.length()==0) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: No se ha detectado ningñun producto","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		for(Character ch: idProductoTx.toCharArray()) {
			if(!Character.isDigit(ch)) {
				JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Código de producto no válido","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		String idPedidoTx=view.getTxIDPedido().getText();
		
		if(idPedidoTx.length()==0) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Pedido no seleccionado","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		for(Character ch: idPedidoTx.toCharArray()) {
			if(!Character.isDigit(ch)) {
				JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Pedido no válido","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		int idProducto=Integer.parseInt(idProductoTx);
		int idPedido=Integer.parseInt(idPedidoTx);
		
		
		int codeResultado=empaquetado.empaquetarProducto(idPedido, idProducto);
			
			
		
		if(codeResultado==0) {
			updateDetail();
		}
		
		else if(codeResultado==1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: No se puede escanear este código","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(codeResultado==2) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: El pedido seleccionado no existe en la orden de trabajo","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(codeResultado==3) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: No se deben recoger mas unidades de este artículo","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(codeResultado==4) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: El producto corresponde a otro pedido de esta OT","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(codeResultado==5) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: El producto no corresponde a esta OT ","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR desconocido","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	private void updateDetail() {
		
		//Actualizamos la tabla correspondiente al pedido 
		if(view.getTablePedidos().getSelectedRow()!=-1) {
			PedidoUse pedido=empaquetado.getPedidos().get(view.getTablePedidos().getSelectedRow());
			
			
			List<ProductoOT> productos=Util.hashMapToProductsList(pedido.getProductos(), empaquetado.getCatalogo());
			TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"Id","Nombre","Unidades"});
			
			this.view.getTableProductos().setModel(tmodel);
		
		}
		
		if(empaquetado.isVacio()) {
			view.getBtTerminar().setEnabled(true);
		}
		
		
		//Actualizamos el precio
		
	}
	
	
	
	
	
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTabla() {
		
		TableModel tmodel= SwingUtil.getTableModelFromPojos(empaquetado.getPedidos(),new String[] {"Id","Fecha","Tamaño"});
		
		this.view.getTablePedidos().setModel(tmodel);
		view.getBtTerminar().setEnabled(false);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}

