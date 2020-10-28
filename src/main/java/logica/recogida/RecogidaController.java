package logica.recogida;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.producto.ProductoOT;
import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import persistencia.recogida.IncidenciaEntity;
import persistencia.recogida.IncidenciaModel;

import ui.recogida.IncidenciaView;
import ui.recogida.RevisionView;
import util.Util;
import util.swingTables.SwingUtil;

public class RecogidaController implements Controller {
	
	private ProductosModel model; 
	private IncidenciaModel incidenciaModel;
	private RevisionView view;
	private OTEntity ot;
	private Recogida recogida;
	private IncidenciaView incidencia;
	private PedidosModel pem;

	private OTModel otm;
	
	
	
	
	public RecogidaController(ProductosModel m,IncidenciaModel im,PedidosModel pem,OTModel otm, RevisionView v, OTEntity ot) {
		this.ot=ot;
		this.model = m; 
		this.view = v;  
		
		this.incidenciaModel=im;
		this.pem=pem;
		this.otm=otm;
		List<ProductoEntity> catalogo=model.getListaProductos();
		
		HashMap <Integer,Integer> mapa= Util.entityToUse(this.pem.getPedidoID(ot.getIdPedido())).getProductos();
		
		List<ProductoOT> lista=Util.hashMapToProductsList(mapa,catalogo);
		Collections.sort(lista);
		
		//Aquí se deberá ordenar la lista
		recogida= new Recogida(lista,catalogo);
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
		
		
		
		this.view.getBtIncidencia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			    incidencia= new IncidenciaView(recogida,view);
			  
				incidencia.setLocationRelativeTo(view.getFrame());
				incidencia.setModal(true);
				incidencia.setVisible(true);
				
				
			}
		});
		
		this.view.getBtCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				guardarIncidencias();
				view.getFrame().dispose();
				
				
			}
		});
		
		this.view.getBtescanear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				escanearProducto();
				
				
			}
		});
		
		this.view.getBtTerminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				view.getFrame().dispose();
				otm.updateStatus(ot.getIdOt(), "RECOGIDO");
				
			}
		});
		
	
		
	}
	
	
	private void escanearProducto() {
		String idProductoTx=view.getTxIDEsacaner().getText();
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
		
		int id=Integer.parseInt(idProductoTx);
		int codeResultado=1;
		
		for (ProductoEntity producto: recogida.getCatalogo()) {
			if(producto.getId()==id) {
				codeResultado=recogida.escanear(id);
			}
			
		}
		
		if(codeResultado==0) {
			updateDetail();
		}
		
		else if(codeResultado==1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: No se puede escanear este código","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(codeResultado==2) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: El artículo escaneado no se encuentra en la OT","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(codeResultado==3) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: No se deben recoger mas unidades de este artículo","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR desconocido","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	private void updateDetail() {
		
		//Actualizamos la tabla correspondiente al pedido 
		List<ProductoOT> productos=recogida.getOT();
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","unidades","pasillo","estanteria","altura"});
		
		this.view.getTableProductos().setModel(tmodel);
		if(recogida.isVacia()) {
			view.getBtTerminar().setEnabled(true);
		}
		
		
		//Actualizamos el precio
		
	}
	
	private void guardarIncidencias() {
		if(!recogida.getIncidencias().isEmpty()) {
		List<IncidenciaEntity> lista= new ArrayList<IncidenciaEntity>();
		for(Incidencia incidencia: recogida.getIncidencias()) {
			lista.add(new IncidenciaEntity(ot.getIdOt(), incidencia.getDescripcion()));
		
		}
		
		incidenciaModel.setIncidencias(lista);
		otm.updateStatus(ot.getIdOt(), "BLOQUEADO");
		}
	}
	
	
	
	
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTabla() {
		List<ProductoOT> productos=recogida.getOT();
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","unidades","pasillo","estanteria","altura"});
		
		this.view.getTableProductos().setModel(tmodel);
		view.getBtTerminar().setEnabled(false);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}

