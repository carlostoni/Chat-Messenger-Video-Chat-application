import javax.swing.SwingWorker;

public class Acao2 extends SwingWorker<Object, Object> {
	protected Object doInBackground() throws Exception {
				
		
		Cliente app = new Cliente();
		app.setVisible(true);
		app.conectar();
		app.escutar();
		
		return null;
		
	}
	

}
