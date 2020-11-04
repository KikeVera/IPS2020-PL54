package logica.paquete;

import java.awt.EventQueue;


import persistencia.almacenero.OTEntity;
import persistencia.almacenero.OTModel;
import persistencia.paquete.PaqueteEntity;
import persistencia.paquete.PaqueteModel;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductosModel;

import ui.paquete.PaqueteView;



public class pruebaPaquete {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					OTEntity ot=new OTEntity();
					ot.setIdAlmacenero(2);
					ot.setIdOt(3);
					ot.setEstado("RECOGIDO");
					ot.setIdPedido("1-2-3");
					
					
					
					PaqueteController controller= new PaqueteController(new ProductosModel(),new PedidosModel(),new OTModel(),new PaqueteModel(), new PaqueteView(), ot);
					controller.initController();
					for(PaqueteEntity paquete: new PaqueteModel().getPaquetes()) {
						System.out.println(paquete.getIdPaquete()+" "+paquete.getIdPedido()+" "+paquete.getFecha());
				}
					
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
