import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Worker {
	//no list can use the worker object at the same time.
	// Synchronized keyword will lock the object for just one resource at a time therefore it will take longer than usual.
	// Using Object Classes Synchronized method two methods can access the worker object at the same time.
	// This program could have been solved using simple methods on lists but it's a good practice to use threads.
	// Threads also provides safety for optimizing variable value i.e it can deal with changing value unlike the other one.
	
	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1= new ArrayList<Integer>();
	private List<Integer> list2= new ArrayList<Integer>();
	
	public void stageOne(){
		
		synchronized(lock1){
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		list1.add(random.nextInt(100));
	}
	
	public void stageTwo(){
		
		synchronized(lock2){
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	
		list2.add(random.nextInt(100));
	}
	
	
	public void process(){
		for(int i=0;i<1000;i++){
			stageOne();
			stageTwo();
		}
	}
	public void main(){
		System.out.println("Starting...");
		
		long start = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				process();
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
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken: " + (end - start));
		System.out.println("List1: " + list1.size() + "; list2: " + list2.size());
	}
}
