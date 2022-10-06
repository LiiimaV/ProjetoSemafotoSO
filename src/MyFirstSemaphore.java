import java.util.concurrent.*;

class FirstThread extends Thread{

	Semaphore Semaphore;
	String firstThread;

	public FirstThread(Semaphore Semaphore, String firstThread) {

		super(firstThread);
		this.Semaphore = Semaphore;
		this.firstThread = firstThread;
	
	}

	@Override
	public void run() {
		
		if(this.getName().equals("Frigideira")) {

			System.out.println("Pegando " + firstThread);
			
			try {
				
				System.out.println(firstThread + " está procurando uma boca de fogão livre.");
				Semaphore.acquire();
				System.out.println(firstThread + " encontrou uma boca de fogão livre.");

				for(int i=1; i < 4; i++) {

					System.out.println("Quantide de " + firstThread + " no fogão: " + i);
					Thread.sleep(10);
				
				}
			} catch (InterruptedException exc) {

					System.out.println(exc);
					
				}
		
				System.out.println(firstThread + " sai da boca do fogão.");
				Semaphore.release();
		}
		
		else {

			System.out.println("Pegando " + firstThread);

			try {

				System.out.println(firstThread + " está procurando uma boca de fogão livre.");
				Semaphore.acquire();
				System.out.println(firstThread + " encontrou uma boca de fogão livre.");
		
				for(int i=1; i < 4; i++) {

					System.out.println(firstThread + ": " + i);
					Thread.sleep(10);

				}
			} catch (InterruptedException exc) {

					System.out.println(exc);

				}
				
				System.out.println(firstThread + " sai da boca do fogão.");
				Semaphore.release();
				
		}
	}
}

public class MyFirstSemaphore
{
	public static void main(String args[]) throws InterruptedException
	{
		Semaphore Semaphore = new Semaphore(1);
		
		FirstThread ft1 = new FirstThread(Semaphore, "Frigideira");
		FirstThread ft2 = new FirstThread(Semaphore, "Panela de Barro");
		
		ft1.start();
		ft2.start();
		
		ft1.join();
		ft2.join();
		
	}
}