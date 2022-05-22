import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
public class Cliente extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextArea texto;
	private JTextField txtMsg;
	private JButton btnSend;
	private JButton btnSair;
	private JPanel pnlContent;
	private Socket socket;
	private OutputStream ou ;
	private Writer ouw; 
	private BufferedWriter bfw;
	private JTextField txtIP;
	private JTextField txtPorta;
	private JTextField txtNome;
	private JButton btnNewButton;
	Cliente self;
	Acao acao;
	Acao1 acao1;
	public String meuNome;
	private JPanel panel;



	public Cliente() throws IOException{  
		
		acao = new Acao();
		acao1 = new Acao1();
		self = this;
		JLabel lblMessage = new JLabel("Verificar!");
		txtIP = new JTextField("127.0.0.1");
		txtPorta = new JTextField("12345");
		txtNome = new JTextField("Cliente");
		this.meuNome = JOptionPane.showInputDialog("Digite seu nome ");
		Object[] texts = {lblMessage, txtIP, txtPorta, txtNome };  
		JOptionPane.showMessageDialog(null, texts);              
		pnlContent = new JPanel();
		pnlContent.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		txtMsg = new JTextField(20);
		txtMsg.setBorder(new LineBorder(new Color(0, 128, 0)));
		txtMsg.setForeground(SystemColor.desktop);
		txtMsg.setBackground(SystemColor.text);
		txtMsg.setBounds(10, 243, 337, 69);
		
		btnSend = new JButton("Enviar");
		btnSend.setBounds(348, 243, 86, 23);
		btnSend.setToolTipText("Enviar Mensagem");
		btnSend.addActionListener(this);
		btnSend.addKeyListener(this);
		
		txtMsg.addKeyListener(this);
		pnlContent.setLayout(null);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(36, 24, 2, 2);
		pnlContent.add(scroll);
		texto = new JTextArea(2,20);
		texto.setBorder(new LineBorder(new Color(0, 128, 0)));
		texto.setForeground(Color.BLACK);
		texto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		texto.setBounds(10, 11, 424, 221);
		
		
		pnlContent.add(texto);
		texto.setEditable(false);
		texto.setBackground(Color.WHITE);
		texto.setLineWrap(true);
		pnlContent.add(txtMsg);
		pnlContent.add(btnSend);
		pnlContent.setBackground(SystemColor.menu);
		setTitle(txtNome.getText());
		setContentPane(pnlContent);

		
		btnNewButton = new JButton("Webcam");
		btnNewButton.setBounds(348, 266, 86, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					acao.execute();
					acao1.execute();
					


				}
			});
		pnlContent.add(btnNewButton);
		btnSair = new JButton("Sair");
		btnSair.setBounds(348, 289, 86, 23);
		btnSair.setToolTipText("Sair do Chat");
		btnSair.addActionListener(this);
		pnlContent.add(btnSair);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 444, 321);
		
		pnlContent.add(panel);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(450,350);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	
		/***
		 * Método usado para conectar no server socket, retorna IO Exception caso dê algum erro.
		 * @throws IOException
		 */
		public void conectar() throws IOException{

			socket = new Socket(txtIP.getText(),Integer.parseInt(txtPorta.getText()));
			ou = socket.getOutputStream();
			ouw = new OutputStreamWriter(ou);
			bfw = new BufferedWriter(ouw);
			bfw.write(txtNome.getText()+"\r\n");
			bfw.flush();
		}

		/***
		 * Método usado para enviar mensagem para o server socket
		 * @param msg do tipo String
		 * @throws IOException retorna IO Exception caso dê algum erro.
		 */
		public void enviarMensagem(String msg) throws IOException{

			if(msg.equals("Sair")){
				bfw.write("Desconectado \r\n");
				texto.append("Desconectado \r\n");
			}else{
				bfw.write(msg+"\r\n");
				texto.append( txtNome.getText() + " diz -> " + txtMsg.getText()+"\r\n");
			}
			bfw.flush();
			txtMsg.setText("");        
		}


		/**
		 * Método usado para receber mensagem do servidor
		 * @throws IOException retorna IO Exception caso dê algum erro.
		 */
		public void escutar() throws IOException{

			InputStream in = socket.getInputStream();
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader bfr = new BufferedReader(inr);
			String msg = "";

			while(!"Sair".equalsIgnoreCase(msg))

				if(bfr.ready()){
					msg = bfr.readLine();
					if(msg.equals("Sair"))
						texto.append("Servidor caiu! \r\n");
					else
						texto.append(msg+"\r\n");         
				}
		}

		/***
		 * Método usado quando o usuário clica em sair
		 * @throws IOException retorna IO Exception caso dê algum erro.
		 */
		public void sair() throws IOException{

			enviarMensagem("Sair");
			bfw.close();
			ouw.close();
			ou.close();
			socket.close();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			inserir();
			
			
			try {

				if(e.getActionCommand().equals(btnSend.getActionCommand()))
					enviarMensagem(txtMsg.getText());

				

				else
					if(e.getActionCommand().equals(btnSair.getActionCommand()))
						sair();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}                       
		} 

		@Override
		public void keyPressed(KeyEvent e) {

			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				try {
					enviarMensagem(txtMsg.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                                                          
			}                       
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub               
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub               
		}



		public void inserir () {
				
			String texto = txtMsg.getText();
			
			BD.connect("bancoaps.db");
			ResultSet res ;
			res = BD.query("SELECT * FROM Mensagem where login='"+meuNome + "'");
			try {

				if(res.next()) {
					String query = "UPDATE Mensagem SET ";
					query = query + "mensagem = '" +texto+ "' ";
					query = query + "WHERE login= '"+meuNome+"'";
					BD.execQuery(query);
					 res.close();
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
		}


		public static void main(String []args) throws IOException{

			Cliente app = new Cliente();
			app.conectar();
			app.escutar();
		}
	}
