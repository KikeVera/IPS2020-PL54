package persistencia.pedido;

/**
 * 
 * @author daniel
 * Clase para tratar los trozos del pedido fragmentado en la base de datos
 */
public class TrozoEntity {
	private String id;
	private int tama�o;
	private String productos;
	
	
	public  TrozoEntity( String id,String fecha,int tama�o,String idUsuario,String productos) {
		this.id=id;
		this.tama�o=tama�o;
		this.productos=productos;
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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