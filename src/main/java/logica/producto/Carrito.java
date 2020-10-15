package logica.producto;

import java.util.HashMap;
import java.util.List;

import persistencia.producto.ProductoEntity;
import persistencia.usuario.UsuarioEntity;

/**
 * Esta clase representa el carrito de la compra en la pagina web.
 * @author Moises
 *
 */
public class Carrito {

	//Representa el pedido donde la clave es el id del producto y el valor el numero de uds pedidas.
	private HashMap<Integer, Integer> pedido = new HashMap<Integer,Integer>(); 
	
	//Copia de la lista de productos disponibles.
	private List<ProductoEntity> catalogo; 
	
	//Representa al usuario que esta realizando la compra 
	private UsuarioEntity usuario;
	
	/**
	 * Constructor 
	 * @param catalogo Lista de productos disponibles 
	 */
	public Carrito(List<ProductoEntity> catalogo) {
		this.catalogo = catalogo;
		this.usuario = new UsuarioEntity(); 
	}
	
	/**
	 * Añade un producto al pedido. Se le pasa el id del producto a anadir junto con
	 * el numero de uds deseadas.
	 */
	public void addProduct(int id, int ud) {
		if (!this.pedido.containsKey(id)) {
			this.pedido.put(id, ud);
		}
		else {
			int actualUd = this.pedido.get(id); 
			this.pedido.put(id, actualUd + ud);
		}
	}
	
	/**
	 * Elimina un producto del pedido. Se le pasa el id del producto a eliminar junto con
	 * el numero de uds deseadas.
	 */
	public void removeProduct(int id, int ud) {
		if(this.pedido.containsKey(id)) {
			int actualUd = this.pedido.get(id); 
			if(actualUd - ud <= 0) {
				this.pedido.remove(id);
			}
			else {
				this.pedido.put(id, actualUd - ud); 
			}
		}
	}
	
	
	
	/**
	 * Representa el pedido actual con una cadena.
	 */
	@Override
	public String toString() {
		String cadena = "";
		for (int id : this.pedido.keySet()) {
			ProductoEntity producto = searchProductById(id); 
			cadena += producto + " UD:" + this.pedido.get(id); 
		}
		return cadena; 
	}
	
	/**
	 * Busca un producto por id.
	 * @param id para identificar el producto a buscar 
	 * @return Producto encontrado o null si no lo encuentra.
	 */
	public ProductoEntity searchProductById(int id) {
		for(ProductoEntity producto : this.catalogo) {
			if(producto.getId() == id) {
				return producto; 
			}
		}
		return null; 
	}
	
	/**
	 * Calcula el precio actual de nuestro pedido.
	 * @return Precio actual 
	 */
	public double calcPrecio() {
		double precio = 0;
		for (int id : this.pedido.keySet()) {
			ProductoEntity producto = searchProductById(id); 
			precio += producto.getPrecio()*this.pedido.get(id);  
		}
		return precio; 
	}
	
	/**
	 * Devuelve el pedido.
	 * @return Pedido actual
	 */
	public HashMap<Integer, Integer> getPedido() {
		return this.pedido; 
	}
	
	/**
	 * Devuelve el usuario que esta realizando el pedido
	 * @return Usuario actual
	 */
	public UsuarioEntity getUsuario() {
		return this.usuario; 
	}
}
