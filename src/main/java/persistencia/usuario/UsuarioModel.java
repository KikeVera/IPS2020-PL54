package persistencia.usuario;

import persistencia.database.Database;

public class UsuarioModel {
	
	private Database db=new Database();
	
	/**
	 * Devuelve un usuario con un id en especifico
	 * @return Usuario con el id dado o null en caso contraio 
	 */
	public UsuarioEntity getUsuario(String codigo) {
		String sql = "Select idUsuario,tipo,direccion from usuario where idUsuario=?";
		UsuarioEntity usuario = db.executePojo(UsuarioEntity.class, sql, codigo); 
		return usuario; 
	}
	
	/**
	 * Inserta un usuario en la base de datos 
	 * @param id Su id 
	 * @param tipo El tipo de usuario
	 * @param direccion La direccion del usuario 
	 */
	public void setUsuario(String id, String tipo, String direccion) {
		String sql="insert into Usuario values (?,?,?)";
		db.executeUpdate(sql,id,tipo,direccion);	
	}
	
	

}
