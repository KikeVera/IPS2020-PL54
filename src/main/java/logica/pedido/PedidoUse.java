package logica.pedido;


import java.util.HashMap;



public class PedidoUse {
	private int id;
	private String fecha;
	private int tama�o ;
	private HashMap<Integer,Integer> productos;
	
	
	public  PedidoUse( int id,String fecha,int tama�o,HashMap<Integer,Integer> productos) {
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


	public HashMap<Integer,Integer> getProductos() {
		return productos;
	}


	public void setProductos(HashMap<Integer,Integer> productos) {
		this.productos = productos;
	}
	
	
	
}
