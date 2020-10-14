package logica.recogida;

import java.awt.EventQueue;

import logica.pedido.PedidoUse;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductosModel;
import persistencia.recogida.IncidenciaEntity;
import persistencia.recogida.IncidenciaModel;
import ui.recogida.RevisionView;
import util.Util;

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
