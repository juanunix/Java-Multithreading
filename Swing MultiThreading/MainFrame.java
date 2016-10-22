import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class MainFrame extends JFrame {
	 private JLabel countLabel1 = new JLabel("0");
	 private JLabel statusLabel = new JLabel("Task not Completed.");
	 private JButton startButton = new JButton("Start");
	 
	 public MainFrame(String title){
		 super(title);
		 
		 setLayout(new GridBagLayout());
		 
		 GridBagConstraints gc = new GridBagConstraints();
		 
		 gc.fill = GridBagConstraints.NONE;
		 
		 gc.gridx = 0;
		 gc.gridy = 0;
		 gc.weightx = 1;
		 gc.weighty = 1;
		 add(countLabel1, gc);
		 
		 gc.gridx = 0;
		 gc.gridy = 1;
		 gc.weightx =1;
		 gc.weighty =1;
		 add(statusLabel, gc);
		 
		 gc.gridx = 0;
		 gc.gridy=2;
		 gc.weightx=1;
		 gc.weighty=1;
		 add(startButton,gc);
		 
		 startButton.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		 	
		setSize(200,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	 }
	 
	 private void start(){
		 SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>(){

			@Override
			protected Boolean doInBackground() throws Exception {
				
				for(int i=0;i<30;i++){
					Thread.sleep(100);
					System.out.println("Hello " +  i);		
				
					publish(i);
				}
				
				return true;
			}
			
			protected void process(List<Integer> chunks){
				int value = chunks.get(chunks.size()-1);
				
				countLabel1.setText("Current value: " + value); 
			}
			protected void done(){
				
				try {
					Boolean status = get();
					statusLabel.setText("Completed with status: " + status);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		 };
		 worker.execute();
	 }
}
