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

}
