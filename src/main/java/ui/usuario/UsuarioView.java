package ui.usuario;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.ButtonGroup;

public class UsuarioView {

	private JFrame frmUsuarios;
	private JPanel pnNoAnonimo;
	private JRadioButton rdbNoAnonimo;
	private JPanel pnCodigo;
	private JPanel pnAnonimo;
	private JRadioButton rdbAnonimo;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JPanel pnBotones;
	private JButton btnInicio;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	
	public UsuarioView() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {

		//Inicializa la ventana principal 
		frmUsuarios = new JFrame();
		frmUsuarios.setTitle("Tienda online: inicio de sesión");
		frmUsuarios.setName("Tienda online: inicio de sesión");
		frmUsuarios.setBounds(0, 0, 619, 319);
		frmUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUsuarios.setLocationRelativeTo(null);
		frmUsuarios.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));

		
		pnNoAnonimo = new JPanel();
		pnNoAnonimo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Usuario no anónimo", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new Color(0, 0, 0)));
		pnNoAnonimo.setLayout(new MigLayout("", "[100px,grow]", "[1px][40px][26px]"));

		rdbNoAnonimo = new JRadioButton("No an\u00F3nimo");
		buttonGroup.add(rdbNoAnonimo);
		rdbNoAnonimo.setHorizontalAlignment(SwingConstants.CENTER);
		rdbNoAnonimo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		pnCodigo = new JPanel();
		pnCodigo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));

		
		pnAnonimo = new JPanel();
		pnAnonimo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Usuario anónimo", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Tahoma", java.awt.Font.BOLD,16), new Color(0, 0, 0)));
		pnAnonimo.setLayout(new MigLayout("", "[100px,grow]", "[100px,grow]"));

		
		rdbAnonimo = new JRadioButton("An\u00F3nimo");
		buttonGroup.add(rdbAnonimo);
		rdbAnonimo.setHorizontalAlignment(SwingConstants.CENTER);
		rdbAnonimo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setEnabled(false);
		txtCodigo.setBackground(Color.WHITE);
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCodigo.setColumns(10);
		
		frmUsuarios.getContentPane().add(pnAnonimo); 
		frmUsuarios.getContentPane().add(pnNoAnonimo);
		
		pnBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
		flowLayout.setVgap(30);
		frmUsuarios.getContentPane().add(pnBotones);
		
		btnInicio = new JButton("Iniciar sesi\u00F3n");
		btnInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInicio.setBackground(new Color(50, 205, 50));
		
		
		pnAnonimo.add(rdbAnonimo, "cell 0 0,alignx center,grow"); 
		pnNoAnonimo.add(rdbNoAnonimo, "cell 0 0,alignx center,grow"); 
		pnNoAnonimo.add(lblCodigo, "cell 0 1,alignx center,grow");
		pnNoAnonimo.add(txtCodigo, "cell 0 1,alignx center,grow");
		pnBotones.add(btnInicio);
		
	}
	
	//Getters y Setters anyadidos para acceso desde el controlador 
	public JFrame getFrame() { return this.frmUsuarios; }
	public JPanel getPnTipoNoAnonimo() {return pnNoAnonimo;}
	public JRadioButton getRdbNoAnonimo() {return rdbNoAnonimo;}
	public JPanel getPnCodigo() {return pnCodigo;}
	public JButton getBtnInicio() {return btnInicio;}
	public JPanel getPnAnonimo() {return pnAnonimo;}
	public JRadioButton getRdbAnonimo() {return rdbAnonimo;}
	public JLabel getLblCodigo() {return lblCodigo;}
	public JTextField getTxtCodigo() {return txtCodigo;}
}
