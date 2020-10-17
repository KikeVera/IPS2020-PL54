package logica.recogida;

import java.awt.EventQueue;


import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductosModel;
import persistencia.recogida.IncidenciaEntity;
import persistencia.recogida.IncidenciaModel;
import ui.recogida.RevisionView;


public class pruebaRecogida {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					OTEntity ot=new OTModel().getOTs().get(0);
					
					
					
					RecogidaController controller= new RecogidaController(new ProductosModel(),new IncidenciaModel(),new PedidosModel(),new OTModel(), new RevisionView(), ot);
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
