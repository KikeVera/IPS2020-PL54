package logica.recogida;

import java.util.ArrayList;
import java.util.List;

import logica.producto.ProductoOT;

public class Recogida {
	
	List <ProductoOT> pedido;
	List <Incidencia> incidencias;
	
	public Recogida(List <ProductoOT> pedido) {
		this.pedido=pedido;
		incidencias= new ArrayList<Incidencia>();
		
	}
	
	public void setIncidencia(Incidencia incidencia) {
		incidencias.add(incidencia);
	}
	
	
	public List <Incidencia> getIncidencias() {
		return incidencias;
	}
	
	public List<ProductoOT> getPedido(){
		return pedido;
		
	}
	public boolean isVacia() {
		for(ProductoOT producto: pedido) {
			if(producto.getUnidades()!=0) {
				return false;
			}
		}
		
		
		return true;
		
		
	}
	
	public int escanear(int id) {
		for(ProductoOT producto: pedido) {
			if(producto.getId()==id) {
				if(producto.getUnidades()>0) {
					producto.setUnidades(producto.getUnidades()-1);
					return 0;
				}
				
				else {
					return 3;
				}
			}
			
		}
		return 2;
	}
	
	
	
}
