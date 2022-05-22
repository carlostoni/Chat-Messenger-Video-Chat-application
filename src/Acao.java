import javax.swing.SwingWorker;

public class Acao extends SwingWorker<Object, Object> {
	protected Object doInBackground() throws Exception {
		
		
		WebcamServer cam = new WebcamServer();
		
		cam.start();
		
		return null;
		
	}}
	
