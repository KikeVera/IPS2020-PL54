package logica.paquete;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import logica.Controller;
import logica.pedido.AlmacenController;
import logica.pedido.PedidoUse;
import logica.producto.ProductoOT;
import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.paquete.EstadoEntity;
import persistencia.paquete.EstadoModel;
import persistencia.paquete.PaqueteModel;
import persistencia.pedido.PedidoEntity;
import persistencia.pedido.PedidosModel;
import persistencia.pedido.TrozoEntity;
import persistencia.pedido.TrozosModel;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductosModel;
import persistencia.usuario.UsuarioEntity;
import persistencia.usuario.UsuarioModel;
import ui.almacen.AlmacenView;
import ui.paquete.PaqueteView;
import util.Util;
import util.swingTables.SwingUtil;

/**
 * Controlador de los paquetes 
 *
 */
public class PaqueteController implements Controller {
	
	private ProductosModel model; 
	
	private PaqueteView view;
	private OTEntity ot;
	private Empaquetado empaquetado;
	
	private PedidosModel pem;
	
	private OTModel otm;
	private EstadoModel em;
	private PaqueteModel pam;
	private TrozosModel tm;

	
	/**
	 * Constructor 
	 * @param m 
	 * @param pem
	 * @param otm
	 * @param pam
	 * @param v
	 * @param ot
	 */
	public PaqueteController(ProductosModel m,PedidosModel pem,TrozosModel tm,OTModel otm,PaqueteModel pam,EstadoModel em, PaqueteView v, OTEntity ot) {
		this.ot=ot;
		this.model = m; 
		this.view = v;  
		this.em=em;
		this.pem=pem;
		this.otm=otm;
		this.pam=pam;
		this.tm=tm;
		List<Integer> idPedidos=new ArrayList<>();
		
		List<PedidoUse> Pedidos=new ArrayList<>();
		
		
		
		List<ProductoEntity> catalogo=model.getListaProductos();
		
		if(ot.getIdPedido().endsWith("F")) {
			Pedidos.add(Util.trozoToPedidoUse(this.tm.getTrozo(ot.getIdPedido())));
		}
		
		else {
		
			String [] pedidos=ot.getIdPedido().split("-");
			for(int i=0;i<pedidos.length;i++) {
				idPedidos.add(Integer.parseInt(pedidos[i]));
			}
			for(Integer id: idPedidos) {
				Pedidos.add(Util.entityToUse(this.pem.getPedidoID(id)));
			}
		}
	
		empaquetado= new Empaquetado(Pedidos,catalogo);
		
		if(!em.getEstadoFromOT(ot.getIdOt()).isEmpty()) {
			cargarEstado();
		}
		this.initView();
	}
	
	/**
	 * Inicializa la vista 
	 */
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
				//borrarPaquetes();
				actualizarEstado();
				view.getFrame().dispose();
				AlmacenController controller = new AlmacenController(new ProductosModel(), new AlmacenView(),new PedidosModel(),new OTModel(),new TrozosModel());
				controller.initController();
				
				
				
			}
		});
		
		this.view.getBtEscanear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				escanearProducto();
				
				
			}
		});
		
		this.view.getBtEmpaquetar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				generarPaquete();
				
			}
		});
		
		this.view.getBtTerminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ot.getIdPedido().endsWith("F")) {
					actualizarEstado();
					if(pedidoTroceadoTerminado()) {
						terminarPedidos();
					}
				}
					
				
				else {
					actualizarEstado();
					terminarPedidos();
				}
				
				otm.updateStatus(ot.getIdOt(), "TERMINADO");
				
				view.getFrame().dispose();
				AlmacenController controller = new AlmacenController(new ProductosModel(), new AlmacenView(),new PedidosModel(),new OTModel(),new TrozosModel());
				controller.initController();
				
				
				
				
			}
		});
		
		
		
		this.view.getTablePedidos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateDetail();
			}
		});
		
	
		
	}
	
	//private void borrarPaquetes() {
		//for(String idPaquete: empaquetado.idPaquetes) {
			//pam.borrarPaquete(idPaquete);
			//File etiqueta = new File ("files","etiqueta" + idPaquete + ".txt");
			//etiqueta.delete();
		//}
		
	//}
	
	private void actualizarEstado() {
		
		
		String terminado=Util.booleanArraytoPersistString(empaquetado.terminado);
		String posibleEmpaquetado=Util.booleanArraytoPersistString(empaquetado.posibleEmpaquetado);
		List<HashMap<Integer, Integer>> maps=new ArrayList<>();
		for(PedidoUse p:empaquetado.getPedidos()) {
			maps.add(p.getProductos());
		}
		
		if(em.getEstadoFromOT(ot.getIdOt()).isEmpty()) {
			
			
			em.createEstado(ot.getIdOt(),terminado , posibleEmpaquetado,Util.ListProductostoString(maps));
		}
		
		else {
			em.updateEstado(ot.getIdOt(), terminado, posibleEmpaquetado,Util.ListProductostoString(maps));
		}
		
		
	}
	
	private void cargarEstado() {
		EstadoEntity estado=em.getEstadoFromOT(ot.getIdOt()).get(0);
		boolean [] terminadoEst=Util.persistStringToBooleanArray(estado.getTerminado());
		boolean [] posibleEmpaquetadoEst=Util.persistStringToBooleanArray(estado.getPosibleEmpaquetado());
		empaquetado.terminado=terminadoEst;
		empaquetado.posibleEmpaquetado=posibleEmpaquetadoEst;
		List<HashMap<Integer, Integer>> maps=Util.StringToProductosList(estado.getMaps());
		int i=0;
		for(PedidoUse p: empaquetado.getPedidos()) {
			p.setProductos(maps.get(i));
			i++;
		}
	}
	
	private void generarPaquete() {
		int selected=view.getTablePedidos().getSelectedRow();
		if(selected==-1) {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Ningún pedido seleccionado","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		PedidoUse pedido= empaquetado.getPedidos().get(selected);
		
		int idPedido=pedido.getId();
		PedidoEntity pedidoEntity = this.pem.getPedido(idPedido); 
		String idPaquete = UUID.randomUUID().toString().substring(0, 5);
		String fecha=Util.dateToIsoString(new Date());
		
		File etiqueta = new File ("files","etiqueta" + idPaquete + ".txt");
		generarEtiqueta(etiqueta,pedidoEntity,idPaquete,fecha);
		pam.createPaquete(idPedido,idPaquete,fecha);
		if(empaquetado.isVacio(selected)) {
			empaquetado.terminado[selected]=true;
		}
		
		if(empaquetado.isTerminado()) {
			view.getBtTerminar().setEnabled(true);
		}
		
		empaquetado.posibleEmpaquetado[selected]=false;
		view.getBtEmpaquetar().setEnabled(false);
	
		
	}
	
	
	
	private void terminarPedidos() {
		for(PedidoUse pedido: empaquetado.getPedidos()) {
			
			String fecha=Util.dateToIsoString(new Date());
			
			File albaran = new File ("files","albaran" + pedido.getId() + ".txt");
			generarAlbaran(albaran,this.pem.getPedido(pedido.getId()),fecha);
		}
	}
	
	private boolean pedidoTroceadoTerminado() {
		for(TrozoEntity trozo:tm.getTrozos()) {
			if(trozo.getId().startsWith(ot.getIdPedido().substring(0, 2))) {
				for(Integer value:Util.stringToProductos(trozo.getProductos()).values()){
					if(value!=0) {
						return false;
					}
				}
				
			}
		}
		
		return true;
		
	}
	
	
	
	
	/**
	 * Genera la etiqueta de un pedido 
	 * @param etiqueta Fichero donde se almacenara la etiqueta.
	 * @param pedido Pedido del que se realizará la etiqueta 
	 * @param idPaquete ID del paquete a generar 
	 * @param fecha Fecha de empaquetado (No confundir con la de pedido)
	 */
	private void generarEtiqueta(File etiqueta,PedidoEntity pedido, String idPaquete, String fecha) {
		FileWriter fw = null;
		BufferedWriter bw = null;  
				
        try
        {
        	fw = new FileWriter(etiqueta); 
            bw = new BufferedWriter(fw); 
            
            UsuarioEntity usuario = new UsuarioModel().getUsuario(pedido.getIdUsuario()); 
            
            bw.write("----Etiqueta----\n");
            bw.write("Id paquete: " + idPaquete + "\n");
            bw.write("Id pedido: " + pedido.getId() + "\n");
            if(usuario != null) {
            	bw.write("Usuario: " + usuario.getIdUsuario() + "\n");
                bw.write("Tipo de usuario: " + usuario.getTipo() + "\n");
                bw.write("Dirección de envío: " + usuario.getDireccion() + "\n");
            }

            bw.write("Fecha de envio: " + fecha + "\n");

        } catch (IOException e) {
            System.out.println("Error al crear la etiqueta del pedido: " + pedido.getId());
        } finally {
           try {
           if (bw != null)
              bw.close();
           if (fw != null)
               fw.close();
           } catch (IOException e) {
        	   System.out.println("Error al cerrar la etiqueta del pedido: " + pedido.getId());
           }
        }
	}
	
	/**
	 * Genera el albaran de un pedido.
	 * @param albaran Fichero donde se almacenara el albaran 
	 * @param pedido Pedido que se va a empaquetar 
	 * @param fecha Fecha de empaquetado (No confundir con la de pedido)  
	 */
	private void generarAlbaran(File albaran, PedidoEntity pedido, String fecha) {
		FileWriter fw = null;
		BufferedWriter bw = null; 
				
        try
        {
        	fw = new FileWriter(albaran); 
            bw = new BufferedWriter(fw);
            PedidosModel pm = new PedidosModel(); 
            
            PedidoEntity pedidoOrig = pm.getPedido(pedido.getId()); 
            UsuarioEntity usuario = new UsuarioModel().getUsuario(pedidoOrig.getIdUsuario());
            
            bw.write("----Albaran----\n");
           
            bw.write("Id pedido: " + pedidoOrig.getId() + "\n");
            if(usuario != null) {
            	bw.write("Usuario: " + usuario.getIdUsuario() + "\n");
                bw.write("Tipo de usuario: " + usuario.getTipo() + "\n");
                bw.write("Dirección de envío: " + usuario.getDireccion() + "\n");
            }
    
            bw.write("Fecha de envio: " + fecha + "\n");
            bw.write("Tamaño total: " + pedidoOrig.getTamaño() + "\n");
            bw.write("Lista productos: " + generarCadenaPedido(pedidoOrig) + "\n");

        } catch (IOException e) {
            System.out.println("Error al crear el albaran del pedido: " + pedido.getId());
        } finally {
           try {
           if (bw != null)
              bw.close();
           if (fw != null)
               fw.close();
           } catch (IOException e) {
        	   System.out.println("Error al cerrar el albaran del pedido: " + pedido.getId());
           }
        }
	}
	
	/**
	 * Crea una lista de los productos de un pedido a partir de este 
	 * @param pedido Pedido origen 
	 * @return Cadena que lo representa 
	 */
	private String generarCadenaPedido(PedidoEntity pedido) {
		StringBuilder sb = new StringBuilder();
		ProductosModel pm = new ProductosModel();
		
		String[] aux = pedido.getProductos().split("/");
		List<ProductoEntity> productos = new ArrayList<ProductoEntity>();
		List<Integer> uds = new ArrayList<Integer>(); 
		for(int i = 0 ; i < aux.length ;i++) {
			String[] par = aux[i].split("-"); 
			productos.add(pm.findProductById(Integer.parseInt(par[0])).get(0));
			uds.add(Integer.parseInt(par[1])); 
		}
		for(int i = 0;i<productos.size();i++) {
			ProductoEntity p = productos.get(i); 
			int n = uds.get(i); 
			sb.append("[id = " + p.getId() +" ,nombre = " + p.getNombre() + " ,número unidades = " + n + " uds]\n");
		}
		return sb.toString(); 
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
		
		
		if(view.getTablePedidos().getSelectedRow()==-1){
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: Ningún pedido seleccionado","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
			
		}
		int idPedido=empaquetado.getPedidos().get(view.getTablePedidos().getSelectedRow()).getId();
		
		
		
		
		int idProducto=Integer.parseInt(idProductoTx);
		
		
		
		int codeResultado=empaquetado.empaquetarProducto(idPedido, idProducto,(int)view.getSpUnidades().getValue());
			
			
		
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
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR: El producto no corresponde a esta OT ","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			JOptionPane.showMessageDialog(view.getFrame(), "ERROR desconocido","Advertencia escaner", JOptionPane.WARNING_MESSAGE);
		}
		
		if(codeResultado==0) {
			empaquetado.posibleEmpaquetado[view.getTablePedidos().getSelectedRow()]=true;
			view.getBtEmpaquetar().setEnabled(true);
		}
		
		view.getSpUnidades().setValue(1);
		
	}
	private void updateDetail() {
		
		//Actualizamos la tabla correspondiente al pedido 
		if(view.getTablePedidos().getSelectedRow()!=-1) {
			PedidoUse pedido=empaquetado.getPedidos().get(view.getTablePedidos().getSelectedRow());
			
			
			List<ProductoOT> productos=Util.hashMapToProductsList(pedido.getProductos(), empaquetado.getCatalogo());
			TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","unidades"});
			
			this.view.getTableProductos().setModel(tmodel);
			
			if(empaquetado.posibleEmpaquetado[view.getTablePedidos().getSelectedRow()])
				view.getBtEmpaquetar().setEnabled(true);
			else
				view.getBtEmpaquetar().setEnabled(false);
		
		}
	
		
		//Actualizamos el precio
		
	}
	
	
	
	
	
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	
	
	private void inicializarTabla() {
		
		TableModel tmodel= SwingUtil.getTableModelFromPojos(empaquetado.getPedidos(),new String[] {"id","fecha","tamaño"});
		
		this.view.getTablePedidos().setModel(tmodel);
		view.getBtTerminar().setEnabled(false);
		view.getBtEmpaquetar().setEnabled(false);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}

