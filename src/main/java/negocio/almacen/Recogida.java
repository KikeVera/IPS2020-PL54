package negocio.almacen;

import java.util.ArrayList;
import java.util.List;



import util.producto.ProductoPedido;

public class Recogida {
	
	List <ProductoPedido> pedido;
	List <Incidencia> incidencias;
	
	public Recogida(List <ProductoPedido> pedido) {
		this.pedido=pedido;
		incidencias= new ArrayList<Incidencia>();
		
	}
	
	public void setIncidencia(Incidencia incidencia) {
		incidencias.add(incidencia);
	}
	
	
	public List <Incidencia> getIncidencias() {
		return incidencias;
	}
	
	public List<ProductoPedido> getPedido(){
		return pedido;
		
	}
	
}
