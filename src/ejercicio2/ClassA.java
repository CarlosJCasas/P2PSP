package ejercicio2;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassA {

	private int counter;
	private Set<Long> setIds = new LinkedHashSet<Long>();
	private long threadID;

	public ClassA(int contador) {
		this.counter = contador;
	}

	public synchronized void enterAndWait() {
		
		threadID = Thread.currentThread().getId();
		// Imprime un mensaje con el hilo que entra
		System.out.println("El hilo " + threadID + " está ejecutando el método enterAndWait()");
		setIds.add(threadID);
		counter--;
		waiting();
		// Imprime un mensaje con el hilo que sale del programa
		System.out.println("EL hilo " + threadID + " acaba de ejecutar el método enterAndWait()\n");

	}

	public boolean isFinished() {
		// Devuelve true si counter es 0
		boolean retorno = false;
		if (counter <=0) {
			retorno = true;
		}
		return retorno;
	}

	private void waiting() {
		try {
			wait(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}

	public Set<Long> getSetIds() {
		return setIds;
	}
}
