import javax.swing.SwingWorker;

public class Acao1 extends SwingWorker<Object, Object> {
	protected Object doInBackground() throws Exception {
		
		
		WebcamClient cam1 = new WebcamClient();
		
		cam1.start();
		
		
		return null;
		
	}
	

}
