import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {	
	
	private int count = 0;
	private Lock lock = new ReentrantLock();  
	private Condition cond = lock.newCondition();
	
	private void increment(){
		for(int i=0;i<1000;i++){
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		lock.lock();
		
		System.out.println("Waiting");
		cond.await();	
		System.out.println("Woken Up");		
		
		try{
			increment();
		}finally{
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException{
		
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Please Enter the return key");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
;		System.out.println("Got Return Key");
		
		cond.signal();
		try{
			increment();
		}finally{
			lock.unlock();
		}
			
		lock.unlock();
	}
	
	public void finished(){
		System.out.println("Count is : "+count);
	}
}
