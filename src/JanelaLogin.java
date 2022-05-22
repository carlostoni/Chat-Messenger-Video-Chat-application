import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;


public class JanelaLogin extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField txtlogin;
	JTextField txtsenha;
	private JFrame self;
	
	
	Acao2 acao2;
	
	

	public JanelaLogin() {
		
		
	
		
		acao2 = new Acao2();
		self = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBemVindos = new JLabel("Bem Vindos");
		lblBemVindos.setForeground(Color.WHITE);
		lblBemVindos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBemVindos.setBounds(161, 33, 121, 38);
		contentPane.add(lblBemVindos);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBounds(182, 82, 62, 20);
		contentPane.add(lblLogin);
		
		txtlogin = new JTextField();
		txtlogin.setBounds(182, 107, 86, 20);
		contentPane.add(txtlogin);
		txtlogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBounds(182, 139, 62, 20);
		contentPane.add(lblSenha);
		
		txtsenha = new JTextField();
		txtsenha.setBounds(182, 164, 86, 20);
		contentPane.add(txtsenha);
		txtsenha.setColumns(10);
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String login = txtlogin.getText();
				 String senha = txtsenha.getText();
			
				BD.connect("bancoaps.db");
				ResultSet res;
				
				res = BD.query("SELECT * FROM Mensagem where login = '"+ login +"'");
				
				
				try {
					if (res.next()) {
						String s = res.getString("senha");
						if (senha.equals(s)) {
							
							acao2.execute();
							self.dispose();
							res.close();
							
						} else {
							JOptionPane.showMessageDialog(null, "Senha inválida!");
						} 
							
						} else {
							JOptionPane.showMessageDialog(null, "Usuário inválido!");
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnAcessar.setBounds(99, 214, 92, 23);
		contentPane.add(btnAcessar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsuarioCadastro c1 = new UsuarioCadastro();
				c1.setVisible(true);
				self.dispose();
			}
		});
		btnCadastrar.setBounds(267, 214, 92, 23);
		contentPane.add(btnCadastrar);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\carlo\\Documents\\5 semestre\\ApsFinal2\\Aps55\\1.jpg"));
		lblNewLabel_1.setBounds(0, 0, 434, 266);
		contentPane.add(lblNewLabel_1);
	}
	
	public static void main(String[] args) {
		
		JanelaLogin j = new JanelaLogin();
		j.setVisible(true);
		
	}
}
