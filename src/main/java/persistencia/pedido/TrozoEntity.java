package persistencia.pedido;

/**
 * 
 * @author daniel
 * Clase para tratar los trozos del pedido fragmentado en la base de datos
 */
public class TrozoEntity {
	private String id;
	private int tamaño;
	private String productos;
	
	
	public  TrozoEntity( String id,String fecha,int tamaño,String idUsuario,String productos) {
		this.id=id;
		this.tamaño=tamaño;
		this.productos=productos;
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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