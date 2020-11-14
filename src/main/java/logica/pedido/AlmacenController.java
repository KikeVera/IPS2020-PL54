package logica.pedido;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.producto.ProductoOT;
import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.pedido.PedidosModel;
import persistencia.pedido.TrozoEntity;
import persistencia.pedido.TrozosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import ui.SwingMain;
import ui.almacen.AlmacenView;
import ui.almacen.OperacionesOTView;
import util.Util;
import util.swingTables.SwingUtil;

public class AlmacenController implements Controller {
	
	private ProductosModel model; 
	private AlmacenView view;
	
	private PedidosModel pedidoModel;
	private OTModel otmodel;
	private TrozosModel trozmodel;
	private List<PedidoUse> pedidos;
	
	private List<String> idpedidos = new ArrayList<String>();	
	private List<String> idpedconOT = new ArrayList<String>();	

	private final int size=15;
	
	public AlmacenController(ProductosModel m, AlmacenView v, PedidosModel pem,OTModel otm,TrozosModel troz) {
		this.pedidoModel=pem;
		this.model = m; 
		this.view = v;  
		this.otmodel=otm;
		this.trozmodel=troz;
		this.initView();
	}
	
	public void initView() {

		//Inicializamos el carrito pasandole el catalogo de productos disponibles. Inicializamos 
		//tambien la tabla de productos.
		
		
		//Inicializamos la tabla que representara al pedido 
		inicializarTablaPedido();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);
		
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador.
	 */
	public void initController() {
		this.view.getbtVerPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verPedido();
			}
		});
		
		this.view.getbtAsignar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				asignarOT();
			}
		});
		
		this.view.getbtSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getFrame().dispose();
				SwingMain frame = new SwingMain();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		
		this.view.getbtOperacionesOT().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OperacionesOTController controller=new OperacionesOTController(new OperacionesOTView(), new OTModel());
				controller.initController();
				view.getFrame().dispose();
			}
		});
		
		
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTablaPedido() {
		pedidos=new ArrayList<>();
		
		
		
		for(PedidoUse pedido:Util.entityToUseList(pedidoModel.getPedidos())) {
			boolean mostrar=true;
			for(OTEntity ot: otmodel.getOTs()) {
				String idPedido=Integer.toString(pedido.getId());
				String idPedOT=ot.getIdPedido();
				if(idPedOT.equals(idPedido)||idPedOT.contains("-"+idPedido+"-")||idPedOT.startsWith(idPedido+"-")||idPedOT.endsWith("-"+idPedido)){
					mostrar=false;
					
				}
			}
			
			if(mostrar) {
				pedidos.add(pedido);
			}
		}
			
		TableModel tmodel= SwingUtil.getTableModelFromPojos(pedidos,new String[] {"id","fecha","tamaño"});
		view.getTabPedidos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabPedidos());
	}
	
	private void verPedido() {
		
		
		int index=view.getTabPedidos().getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Pedido no seleccionado","Advertencia operacion", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		List<ProductoEntity> catalogo=model.getListaProductos();
		PedidoUse pedido=Util.entityToUseList(pedidoModel.getPedidos()).get(index);
		List<ProductoOT> productos=Util.hashMapToProductsList(pedido.getProductos(), catalogo);
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","unidades"});
		
		this.view.getTabProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabProductos());
	}
	
	/**
	 * Guardamos la orden de trabajo en la base de datos
	 */
	private void asignarOT() {
		int index=view.getTabPedidos().getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Orden no seleccionada","Advertencia operacion", JOptionPane.WARNING_MESSAGE);
			return;
		}
		PedidoUse pedido=pedidos.get(index);

		idpedidos.add(Integer.toString(pedido.getId()));
		if(sumaTamPedidos()<=this.size) {
			if(idpedidos.size()==1)
				otmodel.setOT(idpedidos, sumaTamPedidos());
			for(PedidoUse ped:pedidos) {
				if(!idpedidos.contains(Integer.toString(ped.getId())))
					if(ped.getTamaño()+sumaTamPedidos()<=this.size && !this.idpedconOT.contains(Integer.toString(ped.getId())))
						idpedidos.add(Integer.toString(ped.getId()));
			}
			String idPedido=idpedidos.get(0);	//Para que sea en la misma ot que el anterior
			for(OTEntity ot:otmodel.getOTs()) {
				String idPedOT=ot.getIdPedido();
				if(idPedOT.equals(idPedido)||idPedOT.contains("-"+idPedido+"-")||idPedOT.startsWith(idPedido+"-")||idPedOT.endsWith("-"+idPedido)){
					otmodel.updateOT(ot.getIdOt(), sumaTamPedidos(), idpedidos);
					for(String idped:idpedidos)
						this.idpedconOT.add(idped);
					this.idpedidos.clear();
				}
			}	
			
		}else if(sumaTamPedidos()>this.size) {
				List<HashMap<Integer, Integer>> procesaTrozos=Util.dividePedido(pedido.getProductos(),size);	
				for(int i=0;i<procesaTrozos.size();i++) {		
					this.trozmodel.setFragmentoPedido(procesaTrozos.get(i), pedido.getId()+"-"+(i+1)+"-F");
				}
				List<TrozoEntity> trozos=trozmodel.getTrozos();
				
				for(TrozoEntity tr:trozos) {
					otmodel.setTrozoOT(tr.getId(),tr.getTamaño());
				}
				idpedidos.clear(); //Hacemos esto porque no se puede meter un fragmento de un pedido y otro pedido distinto
		}
		inicializarTablaPedido();
			
	}
	/**
	 *  Suma el tamaño de los pedidos de una lista pasada como parametro
	 * @param lista
	 * @return total
	 */
	private int sumaTamPedidos() {
		int total=0;
		for(String s:idpedidos) {
			total+=this.pedidoModel.getPedido(Integer.parseInt(s)).getTamaño();
		}
		return total;
	}
}

