package persistencia.pedido;

/** Clase que representa un pedido
 */

//PENDIENTE DE HACER BIEN
public class PedidoEntity {
	private int id;
	private String fecha;
	private int tama�o ;
	private String productos;
	
	public  PedidoEntity() {}
	public  PedidoEntity( int id,String fecha,int tama�o,String productos) {
		this.id=id;
		this.fecha=fecha;
		this.tama�o=tama�o;
		this.productos=productos;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getTama�o() {
		return tama�o;
	}
	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}
	public String getProductos() {
		return productos;
	}
	public void setProductos(String productos) {
		this.productos = productos;
	}
	

	

	
	
}
