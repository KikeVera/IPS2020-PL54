package ui.pagoPedido;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
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
	private JPanel pnDireccion;
	private JTextField txtDireccion;
	private JPanel pnBotones;
	private JButton btnAtrasInicio;
	private JButton btnSiguienteInicio;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblDireccion;
	private JPanel pnTarjeta;
	private JPanel pnTransaccion;

	
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
		pnInicio.setLayout(new MigLayout("", "[591px]", "[115px][89px][99px]"));
		
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
		
		pnDireccion = new JPanel();
		pnInicio.add(pnDireccion, "cell 0 1,grow");
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
		pnInicio.add(pnBotones, "cell 0 2,grow");
		
		btnAtrasInicio = new JButton("Atr\u00E1s");
		btnAtrasInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtrasInicio.setBackground(Color.RED);
		pnBotones.add(btnAtrasInicio);
		
		btnSiguienteInicio = new JButton("Siguiente");
		btnSiguienteInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiguienteInicio.setBackground(new Color(50, 205, 50));
		pnBotones.add(btnSiguienteInicio);
		
		frmPago = new JDialog();
		frmPago.setTitle("Tienda online: pago de pedido");
		frmPago.setName("Tienda online: pago de pedido");
		frmPago.setBounds(0, 0, 590, 361);
		frmPago.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmPago.setLocationRelativeTo(null);
		frmPago.getContentPane().setLayout(new CardLayout(0, 0));
		frmPago.setModal(true);	
		frmPago.getContentPane().add(pnInicio,"Inicio"); 
		
		pnTarjeta = new JPanel();
		frmPago.getContentPane().add(pnTarjeta, "Tarjeta");
		
		pnTransaccion = new JPanel();
		frmPago.getContentPane().add(pnTransaccion, "Transaccion");
	
	}
	
	
	public JPanel getPnMetodosPago() {return pnMetodosPago;}
	public JRadioButton getRdbTransferencia() {return this.rdbTransferencia;}
	public JRadioButton getRdbContrareembolso() {return this.rdbContrareembolso;}
	public JRadioButton getRdbTarjeta() { return this.rdbTarjeta;}
	public JDialog getFrame() { return this.frmPago;}
	public JTextField getTxtDireccion() { return this.txtDireccion;}
	public JButton getBtnAtrasInicio() { return this.btnAtrasInicio;}
	public JButton getBtnSiguienteInicio() { return this.btnSiguienteInicio;}
}
