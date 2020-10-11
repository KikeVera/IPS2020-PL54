package util.pedido;

import java.util.Date;
import java.util.List;

import util.producto.ProductoEntity;

public class PedidoUse {
	private int id;
	private Date fecha;
	private int tama�o ;
	private List<ProductoEntity> productos;
	
	
	public  PedidoUse( int id,Date fecha,int tama�o,List<ProductoEntity> productos) {
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


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public int getTama�o() {
		return tama�o;
	}


	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}


	public List<ProductoEntity> getProductos() {
		return productos;
	}


	public void setProductos(List<ProductoEntity> productos) {
		this.productos = productos;
	}
	
	
	
}
