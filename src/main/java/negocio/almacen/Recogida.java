package negocio.almacen;

import java.util.ArrayList;
import java.util.List;


import util.producto.ProductoEntity;

public class Recogida {
	
	List <ProductoEntity> pedido;
	List <Incidencia> incidencias;
	
	public Recogida(List <ProductoEntity> pedido) {
		this.pedido=pedido;
		incidencias= new ArrayList<Incidencia>();
		
	}
	
	public void setIncidencia(Incidencia incidencia) {
		incidencias.add(incidencia);
	}
	
	
	public List <Incidencia> getIncidencias() {
		return incidencias;
	}
	
	public List<ProductoEntity> getPedido(){
		return pedido;
		
	}
	
}
