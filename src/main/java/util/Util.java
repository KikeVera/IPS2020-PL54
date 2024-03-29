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
import persistencia.pedido.PedidosModel;
import persistencia.pedido.TrozoEntity;
import persistencia.producto.ProductoEntity;
import persistencia.producto.ProductoInfo;
import persistencia.producto.VentaEntity;
import util.exception.ApplicationException;



public class Util {

	public static Date isoStringToDate(String isoDateString) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(isoDateString);
		} catch (ParseException e) {
			throw new ApplicationException("Formato ISO incorrecto para fecha: " + isoDateString);
		}
	}

	public static String dateToIsoString(Date javaDate) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(javaDate);
	}

	public static HashMap<Integer, Integer> stringToProductos(String cadena) {
		HashMap<Integer, Integer> lista = new HashMap<Integer, Integer>();
		String[] productos = cadena.split("/");
		for (String producto : productos) {
			String[] componentes = producto.split("-");
			lista.put(Integer.parseInt(componentes[0]), Integer.parseInt(componentes[1]));

		}

		return lista;
	}

	public static String ListProductostoString(List<HashMap<Integer, Integer>> lista) {
		StringBuffer buffer = new StringBuffer();
		for (HashMap<Integer, Integer> mapa : lista) {
			buffer.append(Util.productosToString(mapa));
			buffer.append(",");
		}
		String cadena = buffer.toString();
		return cadena.substring(0, cadena.length() - 1);

	}

	public static List<HashMap<Integer, Integer>> StringToProductosList(String cadena) {
		List<HashMap<Integer, Integer>> lista = new ArrayList<>();
		String[] subcadenas = cadena.split(",");
		for (String c : subcadenas) {
			lista.add(stringToProductos(c));
		}

		return lista;

	}

	public static List<HashMap<Integer, Integer>> dividePedido(HashMap<Integer, Integer> mapa, int maxCarrito) {
		List<HashMap<Integer, Integer>> lista = new ArrayList<>();
		HashMap<Integer, Integer> trozo = new HashMap<>();
		int hueco = maxCarrito;

		for (Integer ID : mapa.keySet()) {
			if (mapa.get(ID) <= hueco) {
				trozo.put(ID, mapa.get(ID));
				hueco = hueco - mapa.get(ID);
				mapa.put(ID, 0);

			}

			else {
				while (mapa.get(ID) > hueco) {
					trozo.put(ID, hueco);
					mapa.put(ID, mapa.get(ID) - hueco);
					lista.add(trozo);
					hueco = maxCarrito;
					trozo = new HashMap<>();

				}

				if (mapa.get(ID) != 0) {
					trozo.put(ID, mapa.get(ID));
					hueco = hueco - mapa.get(ID);
					mapa.put(ID, 0);
				}

			}

			if (hueco == 0) {
				lista.add(trozo);
				hueco = maxCarrito;
				trozo = new HashMap<>();
			}

		}
		if (!trozo.isEmpty()) {
			lista.add(trozo);
		}
		return lista;
	}

	public static String productosToString(HashMap<Integer, Integer> lista) {
		StringBuffer buffer = new StringBuffer();

		Integer[] keys = lista.keySet().toArray(new Integer[lista.size()]);
		Integer[] numbers = lista.values().toArray(new Integer[lista.size()]);

		for (int i = 0; i < lista.size(); i++) {
			buffer.append("/");
			buffer.append(keys[i]);
			buffer.append("-");
			buffer.append(numbers[i]);
		}

		String cadena = buffer.toString();
		return cadena.substring(1, cadena.length());

	}

	public static String pedidosToString(List<String> lista) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < lista.size(); i++) {
			buffer.append(lista.get(i));
			buffer.append("-");
		}
		String cadena = buffer.toString();
		return cadena.substring(0, cadena.length() - 1);

	}

	public static List<PedidoUse> entityToUseList(List<PedidoEntity> entity) {

		List<PedidoUse> lista = new ArrayList<PedidoUse>();

		for (PedidoEntity e : entity) {
			lista.add(new PedidoUse(e.getId(), e.getFecha(), e.getTama�o(), e.getIdUsuario(),
					stringToProductos(e.getProductos())));
		}

		return lista;
	}

	public static PedidoUse entityToUse(PedidoEntity e) {

		return new PedidoUse(e.getId(), e.getFecha(), e.getTama�o(), e.getIdUsuario(),
				stringToProductos(e.getProductos()));

	}

	public static List<PedidoEntity> useToEntityList(List<PedidoUse> use) {

		List<PedidoEntity> lista = new ArrayList<PedidoEntity>();
		for (PedidoUse u : use) {
			lista.add(new PedidoEntity(u.getId(), u.getFecha(), u.getTama�o(), u.getIdUsuario(),
					productosToString(u.getProductos())));

		}

		return lista;
	}

	public static PedidoUse trozoToPedidoUse(TrozoEntity trozo) {
		PedidosModel pm = new PedidosModel();
		int id = Integer.parseInt(trozo.getId().substring(0, 1));
		int tama�o = trozo.getTama�o();
		HashMap<Integer, Integer> productos = Util.stringToProductos(trozo.getProductos());
		String idUsuario = pm.getPedido(id).getIdUsuario();
		String fecha = pm.getPedido(id).getFecha();
		return new PedidoUse(id, fecha, tama�o, idUsuario, productos);

	}

	public static List<ProductoOT> hashMapToProductsList(HashMap<Integer, Integer> mapa,
			List<ProductoEntity> catalogo) {
		List<ProductoOT> lista = new ArrayList<ProductoOT>();
		Integer[] keys = mapa.keySet().toArray(new Integer[mapa.size()]);

		for (int i = 0; i < mapa.size(); i++) {
			for (ProductoEntity producto : catalogo) {
				if (producto.getId() == keys[i]) {
					lista.add(new ProductoOT(producto.getId(), producto.getNombre(), mapa.get(keys[i]),
							producto.getPasillo(), producto.getEstanteria(), producto.getAltura()));
				}
			}

		}

		return lista;

	}

	public static List<ProductoInfo> hashMapToProductEntityList(HashMap<Integer, Integer> mapa,
			List<ProductoEntity> catalogo) {
		List<ProductoInfo> lista = new ArrayList<ProductoInfo>();
		Integer[] keys = mapa.keySet().toArray(new Integer[mapa.size()]);

		for (int i = 0; i < mapa.size(); i++) {
			for (ProductoEntity producto : catalogo) {
				if (producto.getId() == keys[i]) {
					lista.add(new ProductoInfo(producto.getNombre(), producto.getPasillo(), producto.getEstanteria()));
				}
			}
		}

		return lista;

	}

	public static String booleanArraytoPersistString(boolean[] array) {

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (array[i]) {
				buffer.append("1");
			}

			else {
				buffer.append("0");
			}
		}

		return buffer.toString();

	}

	public static boolean[] persistStringToBooleanArray(String cadena) {
		boolean[] array = new boolean[cadena.length()];

		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) == '1') {
				array[i] = true;
			}

			else {
				array[i] = false;
			}
		}

		return array;

	}

	public static double sumaImportes(List<VentaEntity> ventas) {
		double total = 0;
		for (VentaEntity venta : ventas) {
			total += venta.getImporte();
		}

		return total;

	}

}
