package negocio.almacen;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import ui.almacen.RevisionRecogida;
import util.producto.ProductoEntity;

public class pruebaRecogida {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<ProductoEntity> pedido= new ArrayList<ProductoEntity>();
					pedido.add(new ProductoEntity(555435,"arroz","esto es arroz",30));
					pedido.add(new ProductoEntity(555445,"tomate","esto es tomatez",40));
					pedido.add(new ProductoEntity(455435,"ternera","esto es ternera",50));
					pedido.add(new ProductoEntity(100,"Producto A","A",21.22));
					RevisionRecogida frame = new RevisionRecogida(new Recogida(pedido));
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
