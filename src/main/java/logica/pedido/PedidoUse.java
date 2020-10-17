package logica.pedido;


import java.util.HashMap;



public class PedidoUse {
	private int id;
	private String fecha;
	private int tamaño ;
	private String idUsuario;
	


	private HashMap<Integer,Integer> productos;
	
	
	public  PedidoUse( int id,String fecha,int tamaño,String idUsuario,HashMap<Integer,Integer> productos) {
		this.id=id;
		this.fecha=fecha;
		this.tamaño=tamaño;
		this.productos=productos;
		this.idUsuario=idUsuario;
		
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


	public HashMap<Integer,Integer> getProductos() {
		return productos;
	}


	public void setProductos(HashMap<Integer,Integer> productos) {
		this.productos = productos;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}
