package util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.pedido.PedidoEntity;
import util.pedido.PedidoUse;
import util.producto.ProductoEntity;



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
	
	
	public static List<ProductoEntity> stringToProductos(String cadena){
		List<ProductoEntity> lista = new ArrayList<ProductoEntity>();
		String[] productos=cadena.split("/");
		for(String producto: productos) {
			String[] componentes=producto.split("-");
			ProductoEntity añadir= new ProductoEntity(Integer.parseInt(componentes[0]),componentes[1],componentes[2],Double.parseDouble(componentes[3]));
			lista.add(añadir);
		}
		
		return lista;
	} 
	
	public static String productosToString(List<ProductoEntity> lista) {
		StringBuffer buffer= new StringBuffer();
		for(ProductoEntity producto:lista) {
			buffer.append("/");
			buffer.append(producto.getId());
			buffer.append("-");
			buffer.append(producto.getNombre());
			buffer.append("-");
			buffer.append(producto.getDescripcion());
			buffer.append("-");
			buffer.append(producto.getPrecio());
						
			
		}
		
		String cadena=buffer.toString();
		return cadena.substring(1, cadena.length());
		
	}

	
	public static PedidoUse entityToUse(PedidoEntity entity) {
		
		
		return new PedidoUse(entity.getId(), isoStringToDate(entity.getFecha()), entity.getTamaño(), stringToProductos(entity.getProductos()));
		
		
	}
	
public static PedidoEntity useToEntity(PedidoUse use) {
		
		
		return new PedidoEntity(use.getId(),dateToIsoString(use.getFecha()),use.getTamaño(),productosToString(use.getProductos()));
		
		
	}
	
	
	
	
}
