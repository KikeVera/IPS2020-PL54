package negocio.almacen;

import java.awt.EventQueue;

import java.util.List;


import ui.almacen.RevisionView;
import util.Util;
import util.pedido.PedidoUse;
import util.pedido.PedidosModel;
import util.producto.ProductoEntity;
import util.producto.ProductoPedido;
import util.producto.ProductosModel;

public class pruebaRecogida {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<ProductoEntity> catalogo=new ProductosModel().getListaProductos();
					PedidoUse pedido=Util.entityToUse(new PedidosModel().getPedidos()).get(0);
					List<ProductoPedido> productos=Util.hashMapToProductsList(pedido.getProductos(), catalogo);
					
					
					RecogidaController controller= new RecogidaController(new ProductosModel(), new RevisionView(), new Recogida(productos));
					controller.initController();
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
