package negocio.almacen;

import java.awt.EventQueue;

import ui.recogida.RevisionView;
import util.Util;
import util.pedido.PedidoUse;
import util.pedido.PedidosModel;

import util.producto.ProductosModel;
import util.recogida.IncidenciaEntity;
import util.recogida.IncidenciaModel;
import util.recogida.RecogidaController;

public class pruebaRecogida {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					PedidoUse pedido=Util.entityToUse(new PedidosModel().getPedidos()).get(0);
					
					
					
					RecogidaController controller= new RecogidaController(new ProductosModel(),new IncidenciaModel(), new RevisionView(), pedido);
					controller.initController();
					for(IncidenciaEntity incidencia: new IncidenciaModel().getIncidencias()) {
						System.out.println(incidencia.getDescripcion()+incidencia.getIdPedido());
				}
					
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
