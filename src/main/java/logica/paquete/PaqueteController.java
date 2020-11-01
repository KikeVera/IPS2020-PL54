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
import persistencia.paquete.PaqueteModel;
import persistencia.pedido.PedidoEntity;
import persistencia.pedido.PedidosModel;
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
	
	private PaqueteModel pam;

	
	/**
	 * Constructor 
	 * @param m 
	 * @param pem
	 * @param otm
	 * @param pam
	 * @param v
	 * @param ot
	 */
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
				
				view.getFrame().dispose();
				AlmacenController controller = new AlmacenController(new ProductosModel(), new AlmacenView(),new PedidosModel(),new OTModel());
				controller.initController();
				
				
				
			}
		});
		
		this.view.getBtEscanear().addActionListener(new ActionListener() {
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
				AlmacenController controller = new AlmacenController(new ProductosModel(), new AlmacenView(),new PedidosModel(),new OTModel());
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
	
	private void empaquetarPedidos() {
		for(PedidoUse pedido: empaquetado.getPedidos()) {
			String idPaquete = UUID.randomUUID().toString().substring(0, 5);
			String fecha=Util.dateToIsoString(new Date());
			pam.createPaquete(pedido.getId(),idPaquete,fecha);
			generarDocumentacion(pedido.getId(),idPaquete,fecha);
		}
	}
	
	/**
	 * Genera documentacion de un paquete. Tanto la etiqueta como el albaran
	 * @param idPedido Pedido del que se genera la documentacion
	 * @param idPaquete ID del paquete a generar 
	 * @param fecha Fecha de empaquetado (No confundir con la de pedido)
	 */
	public void generarDocumentacion(int idPedido, String idPaquete, String fecha) {
		PedidoEntity pedido = this.pem.getPedido(idPedido); 
		File etiqueta = new File ("files","etiqueta" + idPaquete + ".txt");
		File albaran = new File ("files","albaran" + idPaquete + ".txt");
		
		generarEtiqueta(etiqueta,pedido,idPaquete,fecha);
		generarAlbaran(albaran,pedido,idPaquete,fecha);
		
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
            else {
            	bw.write("Usuario: " + pedido.getIdUsuario() + "\n");
                bw.write("Tipo de usuario: Anónimo\n");
                //bw.write("Dirección de envío: " + usuario.getDireccion() + "\n");
            }
    
            bw.write("Fecha de envio: " + fecha + "\n");

        } catch (IOException e) {
            System.out.println("Error al crear la etiqueta del paquete: " + idPaquete);
        } finally {
           try {
           if (bw != null)
              bw.close();
           if (fw != null)
               fw.close();
           } catch (IOException e) {
        	   System.out.println("Error al cerrar la etiqueta del paquete: " + idPaquete);
           }
        }
	}
	
	/**
	 * Genera el albaran de un paquete.
	 * @param albaran Fichero donde se almacenara el albaran 
	 * @param pedido Pedido que se va a empaquetar 
	 * @param idPaquete ID del paquete a generar
	 * @param fecha Fecha de empaquetado (No confundir con la de pedido)  
	 */
	private void generarAlbaran(File albaran, PedidoEntity pedido, String idPaquete, String fecha) {
		FileWriter fw = null;
		BufferedWriter bw = null;  
				
        try
        {
        	fw = new FileWriter(albaran); 
            bw = new BufferedWriter(fw);
            
            UsuarioEntity usuario = new UsuarioModel().getUsuario(pedido.getIdUsuario()); 
            
            bw.write("----Albaran----\n");
            bw.write("Id paquete: " + idPaquete + "\n");
            bw.write("Id pedido: " + pedido.getId() + "\n");
            if(usuario != null) {
            	bw.write("Usuario: " + usuario.getIdUsuario() + "\n");
                bw.write("Tipo de usuario: " + usuario.getTipo() + "\n");
                bw.write("Dirección de envío: " + usuario.getDireccion() + "\n");
            }
            else {
            	bw.write("Usuario: " + pedido.getIdUsuario() + "\n");
                bw.write("Tipo de usuario: Anónimo\n");
                //bw.write("Dirección de envío: " + usuario.getDireccion() + "\n");
            }
    
            bw.write("Fecha de envio: " + fecha + "\n");
            bw.write("Tamaño total: " + pedido.getTamaño() + "\n");
            bw.write("Lista productos: " + pedido.getProductos() + "\n");

        } catch (IOException e) {
            System.out.println("Error al crear el albaran del paquete: " + idPaquete);
        } finally {
           try {
           if (bw != null)
              bw.close();
           if (fw != null)
               fw.close();
           } catch (IOException e) {
        	   System.out.println("Error al cerrar el albaran del paquete: " + idPaquete);
           }
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
		
		view.getSpUnidades().setValue(1);
		
	}
	private void updateDetail() {
		
		//Actualizamos la tabla correspondiente al pedido 
		if(view.getTablePedidos().getSelectedRow()!=-1) {
			PedidoUse pedido=empaquetado.getPedidos().get(view.getTablePedidos().getSelectedRow());
			
			
			List<ProductoOT> productos=Util.hashMapToProductsList(pedido.getProductos(), empaquetado.getCatalogo());
			TableModel tmodel= SwingUtil.getTableModelFromPojos(productos,new String[] {"id","nombre","unidades"});
			
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
		
		TableModel tmodel= SwingUtil.getTableModelFromPojos(empaquetado.getPedidos(),new String[] {"id","fecha","tamaño"});
		
		this.view.getTablePedidos().setModel(tmodel);
		view.getBtTerminar().setEnabled(false);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}

