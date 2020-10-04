package negocio.almacen;

import java.util.ArrayList;
import java.util.List;

import util.PedidoEntity;

public class Recogida {
	
	List <PedidoEntity> pedido;
	List <Incidencia> incidencias;
	
	public Recogida(List <PedidoEntity> pedido) {
		this.pedido=pedido;
		incidencias= new ArrayList<Incidencia>();
		
	}
	
	public void setIncidencia(Incidencia incidencia) {
		incidencias.add(incidencia);
	}
	
	
	public List <Incidencia> getIncidencias() {
		return incidencias;
	}
	
	public List<PedidoEntity> getPedido(){
		return pedido;
		
	}
	
}
