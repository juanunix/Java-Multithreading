import java.util.Scanner;

public class Processor {
	public void produce() throws InterruptedException{
		synchronized (this){ // to attain mutex lock on the thread	
			System.out.println("Producer Thread Running...");
			wait(); 
			System.out.println("Resumed");
		}
	}
	
	public void consume() throws InterruptedException{
		Thread.sleep(2000);
		Scanner sc = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this){
			System.out.println("Watinng for return key : ");
			sc.nextLine();
			
			System.out.println("Return key pressed");
			notify();
		}
	}
}
