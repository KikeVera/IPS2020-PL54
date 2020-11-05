package logica.pagoPedido;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logica.Controller;
import persistencia.usuario.UsuarioEntity;
import ui.pagoPedido.PagoPedidoView;

public class PagoPedidoController implements Controller{

	private PagoPedidoView view; 
	private UsuarioEntity usuario; 
	
 
	public PagoPedidoController(PagoPedidoView v,UsuarioEntity usuario) {
		this.view = v; 
		this.usuario = usuario; 
		this.initController();
		this.initView();	
	}


	@Override
	public void initController() {
		this.view.getBtnSiguienteInicio().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		this.view.getBtnAtrasInicio().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		
	}


	@Override
	public void initView() {
		if(!usuario.getTipo().equals("Anónimo")) {
			this.view.getTxtDireccion().setText(usuario.getDireccion());
		}
		this.view.getFrame().setVisible(true);
		
	}
	
	private void next() {
		
		CardLayout c = (CardLayout) this.view.getFrame().getContentPane().getLayout(); 
		
		if(this.view.getRdbTarjeta().isSelected()) {
			c.show(this.view.getFrame().getContentPane(), "Tarjeta");
		}
		else if (this.view.getRdbTransferencia().isSelected()) {
			c.show(this.view.getFrame().getContentPane(), "Transaccion");
		}
		else if (this.view.getRdbContrareembolso().isSelected()) {
			this.view.dispose();
		}
	}
	
	private void back() {
		this.view.getFrame().dispose();
	}
}
