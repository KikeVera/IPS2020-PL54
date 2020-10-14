package logica.pedido;


import java.util.HashMap;



public class PedidoUse {
	private int id;
	private String fecha;
	private int tamaño ;
	private HashMap<Integer,Integer> productos;
	
	
	public  PedidoUse( int id,String fecha,int tamaño,HashMap<Integer,Integer> productos) {
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


	public HashMap<Integer,Integer> getProductos() {
		return productos;
	}


	public void setProductos(HashMap<Integer,Integer> productos) {
		this.productos = productos;
	}
	
	
	
}
