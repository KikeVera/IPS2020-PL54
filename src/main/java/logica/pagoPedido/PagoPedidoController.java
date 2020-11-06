package logica.pagoPedido;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logica.Controller;
import ui.pagoPedido.PagoPedidoView;

public class PagoPedidoController implements Controller{

	private PagoPedidoView view; 	
 
	public PagoPedidoController(PagoPedidoView v) {
		this.view = v; 
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
				cerrar();
			}
		});
		this.view.getBtnSiguienteTarjeta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Comprobar 
				cerrar();
			}
		});
		this.view.getBtnAtrasTarjeta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back(); 		
			}
		});
		this.view.getBtnSiguienteTransaccion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		this.view.getBtnAtrasTransaccion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		
	}


	@Override
	public void initView() {
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
			cerrar();
		}
	}
	
	private void back() {
		CardLayout c = (CardLayout) this.view.getFrame().getContentPane().getLayout(); 
		c.show(this.view.getFrame().getContentPane(), "Inicio");
	}
	
	private void cerrar() {
		this.view.getFrame().dispose();
	}
	
}
