package persistencia.usuario;

import java.util.UUID;

/**
 * Representa a un usuario. DE MOMENTO solo estara compuesto por un ID que se genera autmoaticamente (aun no+
 * almacenados en BD, simplemente lo simula)
 * @author Moises
 */
public class UsuarioEntity {
	
	private String id;
	private String codigo; 
	private String tipo; 
	
	public UsuarioEntity(String rowId, String rowCodigo, String rowTipo) {
		this.id = rowId; 
		this.codigo = rowCodigo; 
		this.tipo = rowTipo; 
	}
	
	public UsuarioEntity() {
		this.id = UUID.randomUUID().toString().substring(0,5); 
		this.codigo = ""; 
		this.tipo = ""; 
	}
	
	public String getId() {return this.id;}
	public String getCodigo() {return this.codigo;}
	public String getTipo() {return this.tipo;}
	
	public void setId(String value) { this.id=value; }
	public void setCodigo(String value) { this.codigo=value; }
	public void setTipo(String value) { this.tipo=value; }


}
