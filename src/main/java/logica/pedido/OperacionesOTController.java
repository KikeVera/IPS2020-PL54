package logica.pedido;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.paquete.PaqueteController;
import logica.recogida.RecogidaController;
import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.paquete.PaqueteModel;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductosModel;
import persistencia.recogida.IncidenciaModel;
import ui.almacen.AlmacenView;
import ui.almacen.InformacionView;
import ui.almacen.OperacionesOTView;
import ui.paquete.PaqueteView;
import ui.recogida.RevisionView;
import util.swingTables.SwingUtil;

public class OperacionesOTController implements Controller {
	
	
	private OperacionesOTView view;
	private Controller selectedController;
	
	private OTModel otmodel;
	private List<OTEntity> recoger;
	private List<OTEntity> empaquetar;
	
	
	public OperacionesOTController( OperacionesOTView v,OTModel otm) {
		
		this.view = v;  
		this.otmodel=otm;
		recoger= otmodel.getOTsByStatus("ASIGNADO");
		empaquetar= otmodel.getOTsByStatus("RECOGIDO");
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
		
		
		this.view.getbtSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Salir();
				AlmacenController controller = new AlmacenController(new ProductosModel(), new AlmacenView(),new PedidosModel(),new OTModel());
				controller.initController();
			}
			
		});
		
		this.view.getCbOperacion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateDetail();
			}
			
			
		});
		
		
		this.view.getbtComenzar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarController();
			}
			
		});
		
		this.view.getbtInformacion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				informacion();
			}
			
		});
		
		
	}

	private void Salir() {
		view.getFrame().dispose();
	}
	
	/**
	 * Muestra la informacion para la recogida de los productos de esa orden de trabajo
	 * Informacion -> El pasillo, la estanteria y el nombre del producto
	 */
	private void informacion() {
		int index=view.getTabOrdenes().getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Orden no seleccionada","Advertencia operacion", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		InformacionView ventana= new InformacionView();
		ventana.getFrame().setLocationRelativeTo(view.getFrame());
		
		selectedController=new InformacionController(recoger.get(index),ventana,new ProductosModel(),new PedidosModel());
		selectedController.initController();
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	private void seleccionarController() {
		
		String selected=(String)view.getCbOperacion().getSelectedItem();
		int index=view.getTabOrdenes().getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Orden no seleccionada","Advertencia operacion", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		
		if(selected.equals("Recogida")) {
			RevisionView ventana= new RevisionView();
			ventana.getFrame().setLocationRelativeTo(view.getFrame());
					
			selectedController=new RecogidaController(new ProductosModel(), new IncidenciaModel(), new PedidosModel(), new OTModel(),ventana,recoger.get(index));
			selectedController.initController();
			
			
			
		}
		
		if(selected.equals("Empaquetado")) {
		PaqueteView ventana= new PaqueteView();
		ventana.getFrame().setLocationRelativeTo(view.getFrame());
		 selectedController= new PaqueteController(new ProductosModel(), new PedidosModel(), new OTModel(), new PaqueteModel(), ventana, empaquetar.get(index));
		 selectedController.initController();
		
		}
		
		Salir();
		
	}
	
	private void inicializarTabla() {
	
		TableModel tmodel= SwingUtil.getTableModelFromPojos(recoger,new String[] {"idOt","idAlmacenero","idPedido"});
		view.getTabOrdenes().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabOrdenes());
	
		
	}
	
	private void updateDetail() {
		String selected=(String)view.getCbOperacion().getSelectedItem();
		List<OTEntity> ots=null;
		
		if(selected.equals("Recogida")) {
			 ots= recoger;
			 this.view.getbtInformacion().setEnabled(true);
			 view.getbtComenzar().setText("Recoger");
		}
		
		if(selected.equals("Empaquetado")) {
		 ots= empaquetar;
		 this.view.getbtInformacion().setEnabled(false);
		 view.getbtComenzar().setText("Empaquetar");
		}
		
		TableModel tmodel= SwingUtil.getTableModelFromPojos(ots,new String[] {"idOt","idAlmacenero","idPedido"});
		view.getTabOrdenes().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTabOrdenes());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

