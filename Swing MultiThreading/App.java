import javax.swing.SwingUtilities;

public class App {
	public static void main(String arsgs[]){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new MainFrame("SwingWorker Demo");
			}
		});
	}
}
