package logica.recogida;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import javax.swing.table.TableModel;

import logica.Controller;
import logica.pedido.AlmacenController;
import logica.producto.ProductoOT;
import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.pedido.PedidosModel;
import persistencia.pedido.TrozosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import persistencia.recogida.IncidenciaEntity;
import persistencia.recogida.IncidenciaModel;
import ui.almacen.AlmacenView;
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
		
		String [] pedidos=ot.getIdPedido().split("-");
		
		
		HashMap <Integer,Integer> mapaTotal= new HashMap<>();
		for(String pedido: pedidos) {
			
			HashMap <Integer,Integer> mapa= Util.entityToUse(this.pem.getPedidoID(Integer.parseInt(pedido))).getProductos();
			mapaTotal=fusionaMapas(mapa, mapaTotal);
			
		}
		
		
		
		List<ProductoOT> lista=Util.hashMapToProductsList(mapaTotal,catalogo);

		lista=ordenaProductos(lista);
		//Aquí se deberá ordenar la lista
		recogida= new Recogida(lista,catalogo);
		this.initView();
	}
	
	private HashMap<Integer,Integer> fusionaMapas(HashMap<Integer,Integer> mapa1,HashMap<Integer,Integer> mapa2){
		
		for(Integer key: mapa1.keySet()) {
			if(mapa2.containsKey(key)) {
				mapa2.put(key, mapa2.get(key)+mapa1.get(key));
			}
			
			else {
				mapa2.put(key, mapa1.get(key));
			}
		}
		
		return mapa2;
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
				AlmacenController controller = new AlmacenController(new ProductosModel(), new AlmacenView(),new PedidosModel(),new OTModel(),new TrozosModel());
				controller.initController();
				
				
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
				AlmacenController controller = new AlmacenController(new ProductosModel(), new AlmacenView(),new PedidosModel(),new OTModel(),new TrozosModel());
				controller.initController();
				
			}
		});
		
	
		
	}
	
	private List<ProductoOT> ordenaProductos(List<ProductoOT> lista){
		
		 for (int x = 0; x < lista.size(); x++) {
		        for (int i = 0; i < lista.size()-x-1; i++) {
		            if(lista.get(i).getPasillo() > lista.get(i+1).getPasillo()){
		            	ProductoOT tmp = lista.get(i+1);
		            	lista.remove(i+1);
		            	lista.add(i+1,lista.get(i));	 
		            	lista.remove(i);
		                lista.add(i,tmp);
		            }
		            if(lista.get(i).getPasillo() == lista.get(i+1).getPasillo()){
		            	if(lista.get(i).getEstanteria() > lista.get(i+1).getEstanteria()) {
		            		ProductoOT tmp = lista.get(i+1);
		            		lista.remove(i+1);
		            		lista.add(i+1,lista.get(i));	 
		            		lista.remove(i);
		            		lista.add(i,tmp);
		            	}
		            	if(lista.get(i).getEstanteria() == lista.get(i+1).getEstanteria()) {
		            		if(lista.get(i).getAltura() > lista.get(i+1).getAltura()) {
		            			ProductoOT tmp = lista.get(i+1);
			            		lista.remove(i+1);
			            		lista.add(i+1,lista.get(i));	 
			            		lista.remove(i);
			            		lista.add(i,tmp);
		            		}
		            	}
		            }
		        }
		 }
		return lista;
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
				codeResultado=recogida.escanear(id,(int)view.getSpUnidades().getValue());
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
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: No se deben recoger tantas unidades de este artículo","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR desconocido","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		view.getSpUnidades().setValue(1);
	}
	private void updateDetail() {
		
		//Actualizamos la tabla correspondiente al pedido 
		List<ProductoOT> productos=recogida.getOT();
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","unidades","pasillo","estanteria","altura"});
		
		this.view.getTableProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableProductos());
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
		SwingUtil.autoAdjustColumns(view.getTableProductos());
		view.getBtTerminar().setEnabled(false);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}

