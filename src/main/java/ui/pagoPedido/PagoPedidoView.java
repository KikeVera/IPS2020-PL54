package ui.pagoPedido;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Representa la ventana de pago del pedido, accesible desde la tienda online
 * Tener en cuenta que esta es un JDialog
 * 
 * @author Moises
 *
 */
public class PagoPedidoView extends JDialog {

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
	private JTextField textNumeroTarjeta;
	private JLabel lblCVV;
	private JTextField textCVV;
	private JPanel pnBotonesTarjeta;
	private JButton btnAtrasTarjeta;
	private JButton btnSiguienteTarjeta;
	private JPanel pnInfoTransaccion;
	private JPanel pnBotonesTransaccion;
	private JButton btnAtrasTransaccion;
	private JButton btnSiguienteTransaccion;
	private JScrollPane scpTransaccion;
	private JLabel lblInformacinParaRealizar;
	private JTextArea textAreaTransaccion;
	private JPanel pnImporteInicio;
	private JLabel lblImporte;
	private JTextField textImporte;
	private JPanel pnImporteTarjeta;
	private JLabel lblImporteTarjeta;
	private JTextField textField_1;
	private JPanel pnImporteTransaccion;
	private JLabel lblImporteTransaccion;
	private JTextField textImporteTransaccion;

	/**
	 * Constructor
	 */
	public PagoPedidoView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {

		// Inicializa la ventana principal

		scpTransaccion = new JScrollPane();

		textAreaTransaccion = new JTextArea();
		textAreaTransaccion.setEditable(false);
		textAreaTransaccion.setText(
				"Debe realizar un ingreso con el correspondiente importe del pedido al siguiente n\u00FAmero de cuenta: \r\n\r\nXXXX-XXXX-XX-XXXXXXXXXX ");
		scpTransaccion.setViewportView(textAreaTransaccion);

		lblInformacinParaRealizar = new JLabel("Informaci\u00F3n para realizar la transacci\u00F3n:");
		lblInformacinParaRealizar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblInformacinParaRealizar.setFont(new Font("Tahoma", Font.BOLD, 16));

		pnInicio = new JPanel();
		pnInicio.setLayout(new MigLayout("", "[591px]", "[204px][99px]"));

		pnMetodosPago = new JPanel();
		pnMetodosPago.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Seleccion un método de pago", TitledBorder.LEADING, TitledBorder.TOP,
				new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16), new Color(0, 0, 0)));
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
		pnBotonesInicio.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnInicio.add(pnBotonesInicio, "cell 0 1,grow");
		pnBotonesInicio.setLayout(new MigLayout("", "[350px,center][75px][109px]", "[54px,grow]"));

		pnImporteInicio = new JPanel();
		pnImporteInicio.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnBotonesInicio.add(pnImporteInicio, "cell 0 0,alignx left,aligny center");
		pnImporteInicio.setLayout(new GridLayout(2, 1, 0, 0));

		lblImporte = new JLabel("Importe del pedido a pagar");
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnImporteInicio.add(lblImporte);

		textImporte = new JTextField();
		textImporte.setBackground(Color.LIGHT_GRAY);
		textImporte.setEditable(false);
		textImporte.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnImporteInicio.add(textImporte);
		textImporte.setColumns(10);

		btnAtrasInicio = new JButton("Atr\u00E1s");
		btnAtrasInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtrasInicio.setBackground(Color.RED);
		pnBotonesInicio.add(btnAtrasInicio, "cell 1 0,alignx left,aligny center");

		btnSiguienteInicio = new JButton("Siguiente");
		btnSiguienteInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiguienteInicio.setBackground(new Color(50, 205, 50));
		pnBotonesInicio.add(btnSiguienteInicio, "cell 2 0,alignx left,aligny center");

		frmPago = new JDialog();
		frmPago.setTitle("Tienda online: pago de pedido");
		frmPago.setName("Tienda online: pago de pedido");
		frmPago.setBounds(0, 0, 590, 264);
		frmPago.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmPago.setLocationRelativeTo(null);
		frmPago.getContentPane().setLayout(new CardLayout(0, 0));
		frmPago.setModal(true);
		frmPago.getContentPane().add(pnInicio, "Inicio");

		pnTarjeta = new JPanel();
		frmPago.getContentPane().add(pnTarjeta, "Tarjeta");
		pnTarjeta.setLayout(new MigLayout("", "[576px]", "[204px][99px]"));

		pnInfoTarjeta = new JPanel();
		pnInfoTarjeta.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTarjeta.add(pnInfoTarjeta, "cell 0 0,grow");
		pnInfoTarjeta.setLayout(new MigLayout("", "[576px]", "[40px][40px][40px][40px]"));

		lblNumero = new JLabel("N\u00FAmero tarjeta:");
		lblNumero.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInfoTarjeta.add(lblNumero, "cell 0 0,grow");

		textNumeroTarjeta = new JTextField();
		textNumeroTarjeta.setFont(new Font("Tahoma", Font.BOLD, 16));
		textNumeroTarjeta.setColumns(10);
		pnInfoTarjeta.add(textNumeroTarjeta, "cell 0 1,grow");

		lblCVV = new JLabel("CVV:");
		lblCVV.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCVV.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnInfoTarjeta.add(lblCVV, "cell 0 2,grow");

		textCVV = new JTextField();
		textCVV.setFont(new Font("Tahoma", Font.BOLD, 16));
		textCVV.setColumns(10);
		pnInfoTarjeta.add(textCVV, "cell 0 3,grow");

		pnBotonesTarjeta = new JPanel();
		pnBotonesTarjeta.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTarjeta.add(pnBotonesTarjeta, "cell 0 1,grow");
		pnBotonesTarjeta.setLayout(new MigLayout("", "[350px,center][75px][109px]", "[54px,grow]"));

		pnImporteTarjeta = new JPanel();
		pnImporteTarjeta.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnBotonesTarjeta.add(pnImporteTarjeta, "cell 0 0,alignx left,aligny top");
		pnImporteTarjeta.setLayout(new GridLayout(2, 1, 0, 0));

		lblImporteTarjeta = new JLabel("Importe del pedido a pagar");
		lblImporteTarjeta.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnImporteTarjeta.add(lblImporteTarjeta);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		pnImporteTarjeta.add(textField_1);

		btnAtrasTarjeta = new JButton("Atr\u00E1s");
		btnAtrasTarjeta.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtrasTarjeta.setBackground(Color.RED);
		pnBotonesTarjeta.add(btnAtrasTarjeta, "cell 1 0,alignx left,aligny center");

		btnSiguienteTarjeta = new JButton("Siguiente");
		btnSiguienteTarjeta.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiguienteTarjeta.setBackground(new Color(50, 205, 50));
		pnBotonesTarjeta.add(btnSiguienteTarjeta, "cell 2 0,alignx left,aligny center");

		pnTransaccion = new JPanel();
		frmPago.getContentPane().add(pnTransaccion, "Transaccion");
		pnTransaccion.setLayout(new MigLayout("", "[562px]", "[204px][99px]"));

		pnInfoTransaccion = new JPanel();
		pnInfoTransaccion.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTransaccion.add(pnInfoTransaccion, "cell 0 0,alignx center,aligny center");
		pnInfoTransaccion.setLayout(new MigLayout("", "[562px]", "[][]"));
		pnInfoTransaccion.add(getLblInformacinParaRealizar(), "cell 0 0,grow");
		pnInfoTransaccion.add(getScpTransaccion(), "cell 0 1,grow");

		pnBotonesTransaccion = new JPanel();
		pnBotonesTransaccion.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTransaccion.add(pnBotonesTransaccion, "cell 0 1,grow");
		pnBotonesTransaccion.setLayout(new MigLayout("", "[350px,center][75px][109px]", "[54px,grow]"));

		pnImporteTransaccion = new JPanel();
		pnImporteTransaccion.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnBotonesTransaccion.add(pnImporteTransaccion, "cell 0 0,alignx left,aligny top");
		pnImporteTransaccion.setLayout(new GridLayout(2, 1, 0, 0));

		lblImporteTransaccion = new JLabel("Importe del pedido a pagar");
		lblImporteTransaccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnImporteTransaccion.add(lblImporteTransaccion);

		textImporteTransaccion = new JTextField();
		textImporteTransaccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		textImporteTransaccion.setEditable(false);
		textImporteTransaccion.setColumns(10);
		textImporteTransaccion.setBackground(Color.LIGHT_GRAY);
		pnImporteTransaccion.add(textImporteTransaccion);

		btnAtrasTransaccion = new JButton("Atr\u00E1s");
		btnAtrasTransaccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAtrasTransaccion.setBackground(Color.RED);
		pnBotonesTransaccion.add(btnAtrasTransaccion, "cell 1 0,alignx left,aligny center");

		btnSiguienteTransaccion = new JButton("Siguiente");
		btnSiguienteTransaccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiguienteTransaccion.setBackground(new Color(50, 205, 50));
		pnBotonesTransaccion.add(btnSiguienteTransaccion, "cell 2 0,alignx left,aligny center");

	}

	public JPanel getPnMetodosPago() {
		return pnMetodosPago;
	}

	public JRadioButton getRdbTransferencia() {
		return this.rdbTransferencia;
	}

	public JRadioButton getRdbContrareembolso() {
		return this.rdbContrareembolso;
	}

	public JRadioButton getRdbTarjeta() {
		return this.rdbTarjeta;
	}

	public JDialog getFrame() {
		return this.frmPago;
	}

	public JButton getBtnAtrasInicio() {
		return this.btnAtrasInicio;
	}

	public JButton getBtnSiguienteInicio() {
		return this.btnSiguienteInicio;
	}

	public JButton getBtnAtrasTarjeta() {
		return this.btnAtrasTarjeta;
	}

	public JButton getBtnSiguienteTarjeta() {
		return this.btnSiguienteTarjeta;
	}

	public JButton getBtnAtrasTransaccion() {
		return this.btnAtrasTransaccion;
	}

	public JButton getBtnSiguienteTransaccion() {
		return this.btnSiguienteTransaccion;
	}

	public JTextField getTextNumeroTarjeta() {
		return this.textNumeroTarjeta;
	}

	public JTextField getTextCVV() {
		return this.textCVV;
	}

	public JScrollPane getScpTransaccion() {
		return scpTransaccion;
	}

	public JLabel getLblInformacinParaRealizar() {
		return lblInformacinParaRealizar;
	}
}
