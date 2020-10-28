package util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import logica.pedido.PedidoUse;
import logica.producto.ProductoOT;
import persistencia.pedido.PedidoEntity;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductoInfo;
import util.exception.ApplicationException;



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
	
	public static List<HashMap<Integer,Integer>> dividePedido(HashMap<Integer,Integer> mapa, int maxCarrito){
		List<HashMap<Integer,Integer>> lista= new ArrayList<>();
		HashMap<Integer,Integer> trozo=new HashMap<>();
		int hueco=maxCarrito;
		
		for(Integer ID: mapa.keySet()) {
			if(mapa.get(ID)<=hueco) {
				trozo.put(ID, mapa.get(ID));
				mapa.put(ID, 0);
				hueco=hueco-mapa.get(ID);
			}
			
			else {
				while(mapa.get(ID)>hueco) {
					trozo.put(ID, hueco);
					mapa.put(ID, mapa.get(ID)-hueco);
					lista.add(trozo);
					hueco=maxCarrito;
					trozo=new HashMap<>();
					
					
				}
				
			}
			
			if(hueco==0) {
				lista.add(trozo);
				hueco=maxCarrito;
				trozo=new HashMap<>();
			}
			
			
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

	
	public static List<PedidoUse> entityToUseList(List<PedidoEntity> entity) {
		
		List<PedidoUse> lista= new ArrayList<PedidoUse>();
		
		for(PedidoEntity e:entity) {
		lista.add( new PedidoUse(e.getId(), e.getFecha(), e.getTamaño(),e.getIdUsuario(), stringToProductos(e.getProductos())));
		}
		
		return lista;
	}
	
	public static PedidoUse entityToUse(PedidoEntity e) {
		
		
		
	return new PedidoUse(e.getId(), e.getFecha(), e.getTamaño(),e.getIdUsuario(), stringToProductos(e.getProductos()));
		
		
	}
	
	public static List<PedidoEntity> useToEntityList(List<PedidoUse> use) {
		
		List<PedidoEntity> lista= new ArrayList<PedidoEntity>();
		for(PedidoUse u:use) {
		lista.add( new PedidoEntity(u.getId(),u.getFecha(),u.getTamaño(),u.getIdUsuario(),productosToString(u.getProductos())));
		
		}
		
		return lista;
	}
	
	public static List<ProductoOT> hashMapToProductsList(HashMap<Integer,Integer> mapa, List<ProductoEntity> catalogo){
		List<ProductoOT> lista= new ArrayList<ProductoOT>();
		Integer [] keys=mapa.keySet ().toArray(new Integer [mapa.size()]);
		
		for(int i=0;i<mapa.size();i++) {
			for(ProductoEntity producto: catalogo) {
				if(producto.getId()==keys[i]) {
					lista.add(new ProductoOT(producto.getId(),producto.getNombre(),mapa.get(keys[i]),
							producto.getPasillo(),producto.getEstanteria(),producto.getAltura()));
				}
			}
			
			
		}
		
		return lista;
		
	}
	public static List<ProductoInfo> hashMapToProductEntityList(HashMap<Integer,Integer> mapa, List<ProductoEntity> catalogo){
		List<ProductoInfo> lista= new ArrayList<ProductoInfo>();
		Integer [] keys=mapa.keySet ().toArray(new Integer [mapa.size()]);
		
		for(int i=0;i<mapa.size();i++) {
			for(ProductoEntity producto: catalogo) {
				if(producto.getId()==keys[i]) {
					lista.add(new ProductoInfo(producto.getNombre(),producto.getPasillo(),producto.getEstanteria()));
				}
			}		
		}
		
		return lista;
		
	}
		
}
