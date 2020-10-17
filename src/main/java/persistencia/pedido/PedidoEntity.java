package persistencia.pedido;

/** Clase que representa un pedido
 */

//PENDIENTE DE HACER BIEN
public class PedidoEntity {
	private int id;
	private String fecha;
	private int tamaño ;
	private String productos;
	
	public  PedidoEntity() {}
	public  PedidoEntity( int id,String fecha,int tamaño,String productos) {
		this.id=id;
		this.fecha=fecha;
		this.tamaño=tamaño;
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
	public int getTamaño() {
		return tamaño;
	}
	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}
	public String getProductos() {
		return productos;
	}
	public void setProductos(String productos) {
		this.productos = productos;
	}
	

	

	
	
}
