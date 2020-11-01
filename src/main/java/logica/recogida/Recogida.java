package logica.recogida;

import java.util.ArrayList;
import java.util.List;

import logica.producto.ProductoOT;
import persistencia.producto.ProductoEntity;

public class Recogida {
	
	List <ProductoOT> OT;
	
	List <Incidencia> incidencias;
    List<ProductoEntity> catalogo;
	

	public Recogida(List <ProductoOT> pedido,List<ProductoEntity> catalogo) {
		this.OT=pedido;
		this.catalogo=catalogo;
		incidencias= new ArrayList<Incidencia>();
		
	}
	

	public List<ProductoEntity> getCatalogo() {
		return catalogo;
	}
	
	public void setIncidencia(Incidencia incidencia) {
		incidencias.add(incidencia);
	}
	
	
	public List <Incidencia> getIncidencias() {
		return incidencias;
	}
	
	
	public List<ProductoOT> getOT(){
		return OT;
		
	}
	public boolean isVacia() {
		for(ProductoOT producto: OT) {
			if(producto.getUnidades()!=0) {
				return false;
			}
		}
		
		
		return true;
		
		
	}
	
	public int escanear(int id,int uds) {
		for(ProductoOT producto: OT) {
			if(producto.getId()==id) {
				if(producto.getUnidades()-uds>=0) {
					producto.setUnidades(producto.getUnidades()-uds);
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
