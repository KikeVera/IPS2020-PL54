package logica.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import logica.Controller;
import logica.producto.ProductosController;
import persistencia.pedido.PedidosModel;
import persistencia.producto.ProductosModel;
import persistencia.usuario.UsuarioEntity;
import persistencia.usuario.UsuarioModel;
import ui.producto.ProductosView;
import ui.usuario.UsuarioView;

public class UsuarioController implements Controller{

	
	private UsuarioModel model; //Acceso a la base de datos en el ambito de los productos 
	private UsuarioView view; //Interfaz usuario 
	
 
	/**
	 * Constructor 
	 * @param m
	 * @param v
	 */
	public UsuarioController(UsuarioModel m, UsuarioView v) {
		this.model = m; 
		this.view = v; 
		this.initView();
	}
	
	
	@Override
	public void initController() {
		view.getFrame().setVisible(true);
	}

	@Override
	public void initView() {
		this.view.getBtnInicio().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inicioSesion();
			}

		});
		
		this.view.getRdbNoAnonimo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableNoAnonimo(); 
			}
		});
		
		this.view.getRdbAnonimo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disableNoAnonimo(); 
			}

		});
		
	}
	
	/**
	 * Inicia sesion si es posible. Controla que exista el codigo de usuario y que 
	 * se seleecione un tipo de usuario.
	 */
	private void inicioSesion() {
		if(this.view.getRdbAnonimo().isSelected()) {
			UsuarioEntity usuario = new UsuarioEntity();
			createTienda(usuario);
		}
		else if(this.view.getRdbNoAnonimo().isSelected()) {
			UsuarioEntity usuario = this.model.getUsuario(this.view.getTxtCodigo().getText());
			if(usuario != null) {
				createTienda(usuario);
			}
			else {
				JOptionPane.showMessageDialog(this.view.getFrame(),"El codigo de usuario es erroneo",
						"Inicio sesión: Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(this.view.getFrame(),"Debe indicar un tipo de usuario",
					"Inicio sesión: Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Despliega la tienda 
	 * @param user Usuario que ha iniciado sesion 
	 */
	private void createTienda(UsuarioEntity user) {
		ProductosController controller = new ProductosController(new ProductosModel(), new ProductosView(),new PedidosModel(),user);
		controller.initController();
		this.view.getFrame().dispose();
	}


	/**
	 * Activa la region de inicio para usuarios no anonimos
	 */
	private void enableNoAnonimo() {
		this.view.getTxtCodigo().setEnabled(true);
		this.view.getTxtCodigo().setEditable(true);	
	}
	
	/**
	 * Desactiva la region de inicio para usuarios no anonimos 
	 */
	private void disableNoAnonimo() {
		this.view.getTxtCodigo().setEnabled(false);
		this.view.getTxtCodigo().setEditable(false);	
		this.view.getTxtCodigo().setText("");
	}
}
