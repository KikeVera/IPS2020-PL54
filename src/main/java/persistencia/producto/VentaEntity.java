package persistencia.producto;

public class VentaEntity {

	private String fecha;
	private String tipoPago;
	private String tipoUsuario;
	private String empresa;
	private double importe;
	
	public VentaEntity() {}
	
	public VentaEntity(String fecha,String tipoPago,String tipoUsuario,String empresa,double importe) {
		this.fecha=fecha;
		this.tipoPago=tipoPago;
		this.tipoUsuario=tipoUsuario;
		this.empresa=empresa;
		this.importe=importe;
		
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
}
