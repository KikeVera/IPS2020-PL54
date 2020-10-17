package logica.paquete;


import java.util.List;

import logica.pedido.PedidoUse;
import logica.producto.ProductoOT;
import persistencia.producto.ProductoEntity;
import util.Util;

public class Empaquetado {
	List<PedidoUse> pedidos;
	List<ProductoEntity> catalogo;
	
	
	
	
	public Empaquetado(List<PedidoUse> pedidos, List<ProductoEntity> catalogo) {
		this.pedidos=pedidos;
		this.catalogo=catalogo;
		
		
	}
	
	public List<ProductoEntity> getCatalogo(){
		return catalogo;
		
	}
	
	
	public List<PedidoUse> getPedidos(){
		return pedidos;
	}
	
	
	
	
	
	public int empaquetarProducto(int idPedido,int idProducto) {
		
		if(!encontrado(idProducto))
			return 1;
		
		PedidoUse selected=selectPedido(idPedido);
		
		if(selected==null) {
			return 2;
		}
		
		
		ProductoOT producto= selectProducto(idProducto, selected);
		
		if(producto!=null) {
			return compruebaUnidades(producto, selected);
		}
		
			
		for(PedidoUse pedido: pedidos) {
			ProductoOT pr= selectProducto(idProducto, pedido);
			if(pr!=null) {
				return 4;
			}
		}
		
		return 5;
		
	}
	
	private boolean encontrado(int idProducto) {
		for (ProductoEntity producto: catalogo) {
			if(producto.getId()==idProducto) {
				return true;
			}
			
		}
		
		return false;
	}
	
	private PedidoUse selectPedido(int idPedido) {
		for(PedidoUse pedido: pedidos) {
			if(pedido.getId()==idPedido) {
				return pedido;
			}
		}
		
		return null;
	}
	
	private ProductoOT selectProducto(int idProducto,PedidoUse selected) {
		List<ProductoOT> lista= Util.hashMapToProductsList(selected.getProductos(), catalogo);
	
		for(ProductoOT producto: lista) {
			if(producto.getId()==idProducto) {
				return producto;
			}
			
		}
		return null;
	}
	
	private int compruebaUnidades(ProductoOT producto, PedidoUse selected) {
		if(producto.getUnidades()>0) {
			
			selected.getProductos().put(producto.getId(), producto.getUnidades()-1);
				return 0;
			}
				
			else {
				return 3;
			}		
	}
	
	public boolean isVacio() {
		List<ProductoOT> lista;
		for(PedidoUse pedido: pedidos) {
			lista= Util.hashMapToProductsList(pedido.getProductos(), catalogo);
			for(ProductoOT p: lista) {
				if(p.getUnidades()!=0) {
					return false;
				}
			}
		}
		return true;
	}

}
