package logica.recogida;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.pedido.PedidoUse;
import logica.producto.ProductoPedido;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import persistencia.recogida.IncidenciaEntity;
import persistencia.recogida.IncidenciaModel;
import ui.recogida.ComparacionView;
import ui.recogida.IncidenciaView;
import ui.recogida.RevisionView;
import util.Util;
import util.swingTables.SwingUtil;

public class RecogidaController {
	
	private ProductosModel model; 
	private IncidenciaModel incidenciaModel;
	private RevisionView view;
	private PedidoUse pedido;
	private Recogida recogida;
	private IncidenciaView incidencia;
	private ComparacionView compara;
	
	
	
	
	public RecogidaController(ProductosModel m,IncidenciaModel im, RevisionView v, PedidoUse pedido) {
		this.pedido=pedido;
		this.model = m; 
		this.view = v;  
		this.incidenciaModel=im;
		
		recogida= new Recogida(Util.hashMapToProductsList(this.pedido.getProductos(), model.getListaProductos()));
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
		
		this.view.getBtComprobar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTableProductos().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(view.getFrame(),"Debe seleccionar un producto para comparar "
							,"Recogida: Advertencia",JOptionPane.WARNING_MESSAGE);
				}	
				
				else {
			    compara= new ComparacionView(getSelectedProduct(), getAlmacenProduct());
				compara.setLocationRelativeTo(view.getFrame());
				compara.setModal(true);
				compara.setVisible(true);
				}
			}
		});
		
		this.view.getBtIncidencia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			    incidencia= new IncidenciaView();
			    incidencia.getBtAñadir_1().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						añadirIncidencia(incidencia);
						
					}
				});
				incidencia.setLocationRelativeTo(view.getFrame());
				incidencia.setModal(true);
				incidencia.setVisible(true);
				
				
			}
		});
		
		this.view.getBtSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				view.getFrame().dispose();
				
			}
		});
		
		this.view.getBtGuardarIncidencias().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int resp=JOptionPane.showConfirmDialog(view.getFrame(), "¿Está seguro de añadir esta incidencia?","Confirmar incidencia",JOptionPane.YES_NO_OPTION);
				if(resp==JOptionPane.YES_OPTION) {
				guardarIncidencias();
				view.getFrame().dispose();
				}
			}
		});
		
		
		this.view.getBtBorrarIncidencia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTableIncidencias().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(view.getFrame(),"Debe seleccionar una incidencia para borrar "
							,"Recogida: Advertencia",JOptionPane.WARNING_MESSAGE);
				}	
				else {
				borrarIncidencia();
				}
			}
		});
		
		
	}
	
	private void borrarIncidencia() {
		recogida.getIncidencias().remove(view.getTableIncidencias().getSelectedRow());
		updateDetail();
		
	}
	private void añadirIncidencia(IncidenciaView iw) {
		
		if(iw.getTxIncidencia().getText().trim().length()!=0) {
		int resp=JOptionPane.showConfirmDialog(iw, "¿Está seguro de añadir esta incidencia?","Confirmar incidencia",JOptionPane.YES_NO_OPTION);
		if(resp==JOptionPane.YES_OPTION)
		recogida.setIncidencia(new Incidencia(iw.getTxIncidencia().getText()));
		updateDetail();
			
		
			
		}
		
	}
	private void updateDetail() {
		
		//Actualizamos la tabla correspondiente al pedido 
		String[] properties = new String[] {"Descripcion"};
		TableModel tm = SwingUtil.getTableModelFromPojos(recogida.getIncidencias(),properties);
		this.view.getTableIncidencias().setModel(tm);
		
		//Actualizamos el precio
		
	}
	
	private void guardarIncidencias() {
		List<IncidenciaEntity> lista= new ArrayList<IncidenciaEntity>();
		for(Incidencia incidencia: recogida.getIncidencias()) {
			lista.add(new IncidenciaEntity(pedido.getId(), incidencia.getDescripcion()));
		
		}
		
		incidenciaModel.setIncidencias(lista);
	}
	
	private ProductoPedido getSelectedProduct() {
		return recogida.getPedido().get(view.getTableProductos().getSelectedRow());
		
	}
	
	private ProductoEntity getAlmacenProduct() {
		List<ProductoEntity> catalogo= model.getListaProductos();
		ProductoPedido buscado= getSelectedProduct();
		for(ProductoEntity entity:catalogo) {
			if (entity.getId()==buscado.getId()) {
				return entity;
			}
		}
		return new ProductoEntity(-1,"Producto no encontrado","",0,0,0);
	}
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTabla() {
		List<ProductoPedido> productos=recogida.getPedido();
		TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"Id","Nombre","Descripcion","Precio","Unidades"});
		
		this.view.getTableProductos().setModel(tmodel);
		
		String[] properties = new String[] {"Descripcion"};
		TableModel tm = SwingUtil.getTableModelFromPojos(recogida.getIncidencias(),properties);
		this.view.getTableIncidencias().setModel(tm);
		
	}
	
	
	
	
	
	
	
	
	
	
}

