package demo1;

public class App {

	private int count = 0;
	
	public synchronized void increment(){
		count++;
		// using this method resources (Count) will be accessed by first thread and then second method
		//otherwise program will produce arbitrary results most of the time.
	}
	
	public void doWork(){
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<10000;i++){
					increment();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<10000;i++){
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try{
			t1.join();
			t2.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Count is : "+ count);
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}

}
