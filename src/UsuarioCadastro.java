import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class UsuarioCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textlogin;
	private JTextField textsenha;
	private JFrame self;

	public UsuarioCadastro() {
		self = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroUsurios = new JLabel("Cadastro Usu\u00E1rios");
		lblCadastroUsurios.setForeground(SystemColor.control);
		lblCadastroUsurios.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCadastroUsurios.setBounds(142, 11, 181, 23);
		contentPane.add(lblCadastroUsurios);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(SystemColor.textHighlightText);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(10, 39, 89, 23);
		contentPane.add(lblLogin);
		
		textlogin = new JTextField();
		textlogin.setBounds(10, 64, 258, 20);
		contentPane.add(textlogin);
		textlogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(SystemColor.textHighlightText);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(10, 95, 74, 23);
		contentPane.add(lblSenha);
		
		textsenha = new JTextField();
		textsenha.setBounds(10, 120, 258, 20);
		contentPane.add(textsenha);
		textsenha.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textlogin.getText();
				String senha = textsenha.getText();
			
				
				BD.connect("bancoaps.db");
				String query = "insert into Mensagem ('login','senha') values (";
				query = query + "'" + login + "',";
				query = query + "'" + senha + "');";
				
				BD.execQuery(query);
				JOptionPane.showMessageDialog(null, "Cadastrado!");
				self.dispose();
				
			}
		});
		btnCadastrar.setBounds(10, 227, 92, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.dispose();
			}
		});
		btnCancelar.setBounds(142, 227, 92, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\carlo\\Documents\\5 semestre\\ApsFinal2\\Aps55\\3.jpg"));
		lblNewLabel.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel);
	}
}
