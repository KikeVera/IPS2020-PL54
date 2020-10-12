package util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import util.pedido.PedidoEntity;
import util.pedido.PedidoUse;
import util.producto.ProductoEntity;
import util.producto.ProductoPedido;



public class Util {
	
	public static Date isoStringToDate(String isoDateString) {
		try {
		return new SimpleDateFormat("yyyy-MM-dd").parse(isoDateString);
		} catch (ParseException e) {
			throw new ApplicationException("Formato ISO incorrecto para fecha: "+isoDateString);
		}
	}
	
	public static String dateToIsoString(Date javaDate) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(javaDate);
	}
	
	
	public static HashMap<Integer,Integer> stringToProductos(String cadena){
		HashMap<Integer,Integer> lista = new HashMap<Integer,Integer>();
		String[] productos=cadena.split("/");
		for(String producto: productos) {
			String[] componentes=producto.split("-");
			lista.put(Integer.parseInt(componentes[0]), Integer.parseInt(componentes[1]));
			
		}
		
		return lista;
	} 
	
	public static String productosToString(HashMap<Integer,Integer> lista) {
		StringBuffer buffer= new StringBuffer();
		
		Integer [] keys=lista.keySet ().toArray(new Integer[lista.size()]);
		Integer[] numbers=lista.values().toArray(new Integer[lista.size()]);
		
		
		for(int i=0;i<lista.size();i++) {
			buffer.append("/");
			buffer.append(keys[i]);
			buffer.append("-");
			buffer.append(numbers[i]);
		}
		
		
		String cadena=buffer.toString();
		return cadena.substring(1, cadena.length());
		
	}

	
	public static List<PedidoUse> entityToUse(List<PedidoEntity> entity) {
		
		List<PedidoUse> lista= new ArrayList<PedidoUse>();
		
		for(PedidoEntity e:entity) {
		lista.add( new PedidoUse(e.getId(), e.getFecha(), e.getTamaño(), stringToProductos(e.getProductos())));
		}
		
		return lista;
	}
	
	public static List<PedidoEntity> useToEntity(List<PedidoUse> use) {
		
		List<PedidoEntity> lista= new ArrayList<PedidoEntity>();
		for(PedidoUse u:use) {
		lista.add( new PedidoEntity(u.getId(),u.getFecha(),u.getTamaño(),productosToString(u.getProductos())));
		
		}
		
		return lista;
	}
	
	public static List<ProductoPedido> hashMapToProductsList(HashMap<Integer,Integer> mapa, List<ProductoEntity> catalogo){
		List<ProductoPedido> lista= new ArrayList<ProductoPedido>();
		Integer [] keys=mapa.keySet ().toArray(new Integer [mapa.size()]);
		
		for(int i=0;i<mapa.size();i++) {
			for(ProductoEntity producto: catalogo) {
				if(producto.getId()==keys[i]) {
					lista.add(new ProductoPedido(producto.getId(),producto.getNombre(),producto.getDescripcion(),producto.getPrecio(),mapa.get(keys[i])));
				}
			}
			
			
		}
		
		return lista;
		
	}
	
	
	
	
}
