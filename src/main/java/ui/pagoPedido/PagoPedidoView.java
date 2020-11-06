package ui.pagoPedido;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class PagoPedidoView extends JDialog{
	

	private static final long serialVersionUID = 1L;
	
	private JDialog frmPago;
	private JPanel pnInicio;
	private JPanel pnMetodosPago;
	private JRadioButton rdbTarjeta;
	private JRadioButton rdbContrareembolso;
	private JRadioButton rdbTransferencia;
	private JPanel pnBotonesInicio;
	private JButton btnAtrasInicio;
	private JButton btnSiguienteInicio;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel pnTarjeta;
	private JPanel pnTransaccion;
	private JPanel pnInfoTarjeta;
	private JLabel lblNumero;
	private JTextField textField;
	private JLabel lblCVV;
	private JTextField textField_1;
	private JPanel pnBotonesTarjeta;
	private JButton btnAtrasTarjeta;
	private JButton btnSiguienteTarjeta;
	private JPanel pnInfoTransaccion;
	private JLabel lblInformacinParaRealizar;
	private JTextField textField_2;
	private JPanel pnBotonesTransaccion;
	private JButton btnAtrasTransaccion;
	private JButton btnSiguienteTransaccion;

	
	public PagoPedidoView() {
		initialize();		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {

		//Inicializa la ventana principal 
		
		
		pnInicio = new JPanel();
		pnInicio.setLayout(new MigLayout("", "[591px]", "[115px][99px]"));
		
		pnMetodosPago = new JPanel();
		pnMetodosPago.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccion un método de pago", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new Color(0, 0, 0)));
		pnInicio.add(pnMetodosPago, "cell 0 0,grow");
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
		
		pnBotonesInicio = new JPanel();
		pnInicio.add(pnBotonesInicio, "cell 0 1,grow");
		pnBotonesInicio.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 35));
		
		btnAtrasInicio = new JButton("Atr\u00E1s");
		btnAtrasInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtrasInicio.setBackground(Color.RED);
		pnBotonesInicio.add(btnAtrasInicio);
		
		btnSiguienteInicio = new JButton("Siguiente");
		btnSiguienteInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiguienteInicio.setBackground(new Color(50, 205, 50));
		pnBotonesInicio.add(btnSiguienteInicio);
		
		frmPago = new JDialog();
		frmPago.setTitle("Tienda online: pago de pedido");
		frmPago.setName("Tienda online: pago de pedido");
		frmPago.setBounds(0, 0, 590, 264);
		frmPago.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmPago.setLocationRelativeTo(null);
		frmPago.getContentPane().setLayout(new CardLayout(0, 0));
		frmPago.setModal(true);
		frmPago.getContentPane().add(pnInicio,"Inicio"); 
		
		pnTarjeta = new JPanel();
		frmPago.getContentPane().add(pnTarjeta, "Tarjeta");
		pnTarjeta.setLayout(new MigLayout("", "[576px]", "[204px][99px]"));
		
		pnInfoTarjeta = new JPanel();
		pnTarjeta.add(pnInfoTarjeta, "cell 0 0,grow");
		pnInfoTarjeta.setLayout(new MigLayout("", "[576px]", "[40px][40px][40px][40px]"));
		
		lblNumero = new JLabel("N\u00FAmero tarjeta:");
		lblNumero.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInfoTarjeta.add(lblNumero, "cell 0 0,grow");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setColumns(10);
		pnInfoTarjeta.add(textField, "cell 0 1,grow");
		
		lblCVV = new JLabel("CVV:");
		lblCVV.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCVV.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInfoTarjeta.add(lblCVV, "cell 0 2,grow");
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_1.setColumns(10);
		pnInfoTarjeta.add(textField_1, "cell 0 3,grow");
		
		pnBotonesTarjeta = new JPanel();
		pnTarjeta.add(pnBotonesTarjeta, "cell 0 1,grow");
		pnBotonesTarjeta.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 35));
		
		btnAtrasTarjeta = new JButton("Atr\u00E1s");
		btnAtrasTarjeta.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtrasTarjeta.setBackground(Color.RED);
		pnBotonesTarjeta.add(btnAtrasTarjeta);
		
		btnSiguienteTarjeta = new JButton("Siguiente");
		btnSiguienteTarjeta.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiguienteTarjeta.setBackground(new Color(50, 205, 50));
		pnBotonesTarjeta.add(btnSiguienteTarjeta);
		
		pnTransaccion = new JPanel();
		frmPago.getContentPane().add(pnTransaccion, "Transaccion");
		pnTransaccion.setLayout(new MigLayout("", "[562px]", "[204px][99px]"));
		
		pnInfoTransaccion = new JPanel();
		pnTransaccion.add(pnInfoTransaccion, "cell 0 0,alignx center,aligny center");
		pnInfoTransaccion.setLayout(new MigLayout("", "[576px]", "[81px][81px]"));
		
		lblInformacinParaRealizar = new JLabel("Informaci\u00F3n para realizar la transacci\u00F3n:");
		lblInformacinParaRealizar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblInformacinParaRealizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInfoTransaccion.add(lblInformacinParaRealizar, "cell 0 0,alignx left,aligny bottom");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		pnInfoTransaccion.add(textField_2, "cell 0 1,grow");
		
		pnBotonesTransaccion = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnBotonesTransaccion.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(35);
		flowLayout.setHgap(10);
		pnTransaccion.add(pnBotonesTransaccion, "cell 0 1,grow");
		
		btnAtrasTransaccion = new JButton("Atr\u00E1s");
		btnAtrasTransaccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtrasTransaccion.setBackground(Color.RED);
		pnBotonesTransaccion.add(btnAtrasTransaccion);
		
		btnSiguienteTransaccion = new JButton("Siguiente");
		btnSiguienteTransaccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiguienteTransaccion.setBackground(new Color(50, 205, 50));
		pnBotonesTransaccion.add(btnSiguienteTransaccion);
	
	}
	
	
	public JPanel getPnMetodosPago() {return pnMetodosPago;}
	public JRadioButton getRdbTransferencia() {return this.rdbTransferencia;}
	public JRadioButton getRdbContrareembolso() {return this.rdbContrareembolso;}
	public JRadioButton getRdbTarjeta() { return this.rdbTarjeta;}
	public JDialog getFrame() { return this.frmPago;}
	public JButton getBtnAtrasInicio() { return this.btnAtrasInicio;}
	public JButton getBtnSiguienteInicio() { return this.btnSiguienteInicio;}
	public JButton getBtnAtrasTarjeta() { return this.btnAtrasTarjeta;}
	public JButton getBtnSiguienteTarjeta() { return this.btnSiguienteTarjeta;}
	public JButton getBtnAtrasTransaccion() { return this.btnAtrasTransaccion;}
	public JButton getBtnSiguienteTransaccion() { return this.btnSiguienteTransaccion;}
}
