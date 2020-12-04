package ejercicio1;

public class ClassA {
	
	private long threadId;
	
	public ClassA() {
		super();
	}



	public synchronized void enterAndWait() {
		//Imprime un mensaje con el hilo que se ejecuta
		threadId = Thread.currentThread().getId();
		System.out.println("El hilo " + threadId +" est� ejecutando el m�todo enterAndWait()");
		
		//Detener durante unos segundos
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Imprime un mensaje con que hilo est� terminando de ejecutarse
		System.out.println("EL hilo " + threadId + " acaba de ejecutar el m�todo enterAndWait()");
		
	}
}
