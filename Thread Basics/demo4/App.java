package demo1;

import java.util.Scanner;

class Processor extends Thread{
	
	private volatile boolean running = true;
	//Volatile keyword to prevent thread caching when its value is  not changed.
	
	public void run(){
		while(running){
			System.out.println("Hello"); 
			
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	public void shutDown(){
		running = false;
	}
}
public class App {
	public static void main(String args[]){
		Processor pro1 = new Processor();
		pro1.start();
		
		System.out.println("Press return to stop...");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		pro1.shutDown();
	}
}
