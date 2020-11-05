package ui.pagoPedido;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class PagoPedidoView {
	
	private JFrame frmPago;
	private JPanel pnGlobal;
	private JPanel pnMetodosPago;
	private JRadioButton rdbTarjeta;
	private JRadioButton rdbContrareembolso;
	private JRadioButton rdbTransferencia;
	private JPanel pnDireccion;
	private JTextField txtDireccion;
	private JPanel pnBotones;
	private JButton btnAtras;
	private JButton btnSiguiente;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblDireccion;

	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {

		//Inicializa la ventana principal 
		frmPago = new JFrame();
		frmPago.setTitle("Tienda online: pago de pedido");
		frmPago.setName("Tienda online: pago de pedido");
		frmPago.setBounds(0, 0, 619, 362);
		frmPago.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPago.setLocationRelativeTo(null);
		frmPago.getContentPane().setLayout(new CardLayout(0, 0));
		frmPago.getContentPane().add(getPnMetodosPago(), "Inicio");
		
		pnGlobal = new JPanel();
		frmPago.getContentPane().add(pnGlobal, "name_1154358573826900");
		pnGlobal.setLayout(new GridLayout(3, 1, 0, 10));
		
		pnMetodosPago = new JPanel();
		pnMetodosPago.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccion un método de pago", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new Color(0, 0, 0)));
		pnGlobal.add(pnMetodosPago);
		pnMetodosPago.setLayout(new GridLayout(3, 1, 0, 0));
		
		rdbTarjeta = new JRadioButton("Tarjeta");
		buttonGroup.add(rdbTarjeta);
		rdbTarjeta.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnMetodosPago.add(rdbTarjeta);
		
		rdbContrareembolso = new JRadioButton("Contra reembolso");
		buttonGroup.add(rdbContrareembolso);
		rdbContrareembolso.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnMetodosPago.add(rdbContrareembolso);
		
		rdbTransferencia = new JRadioButton("Transferencia");
		buttonGroup.add(rdbTransferencia);
		rdbTransferencia.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnMetodosPago.add(rdbTransferencia);
		
		pnDireccion = new JPanel();
		pnGlobal.add(pnDireccion);
		pnDireccion.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblDireccion = new JLabel("Direcci\u00F3n de env\u00EDo:");
		lblDireccion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDireccion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnDireccion.add(lblDireccion);
		
		txtDireccion = new JTextField();
		pnDireccion.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		pnBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(10);
		flowLayout.setVgap(35);
		pnGlobal.add(pnBotones);
		
		btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtras.setBackground(Color.RED);
		pnBotones.add(btnAtras);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiguiente.setBackground(new Color(50, 205, 50));
		pnBotones.add(btnSiguiente);
				
	
	}
	
	
	public JPanel getPnMetodosPago() {return pnMetodosPago;}
	public JRadioButton getRdbTransferencia() {return this.rdbTransferencia;}
	public JRadioButton getRdbContrareembolso() {return this.rdbContrareembolso;}
	public JRadioButton getRdbTarjeta() { return this.rdbTarjeta;}
}
