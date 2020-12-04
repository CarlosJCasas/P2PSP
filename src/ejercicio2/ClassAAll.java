package ejercicio2;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ClassAAll {
	private AtomicInteger count = new AtomicInteger();
	private int counter;
	private Set<Long> setIds = new LinkedHashSet<Long>();
	private long threadID;

	public ClassAAll(AtomicInteger count) {
		super();
		this.count = count;
	}

	public synchronized void enterAndWait() {
		threadID = Thread.currentThread().getId();
		System.out.println("El hilo " + threadID + " está ejecutando el método enterAndWait()");
		setIds.add(threadID);
		count.getAndDecrement();
		waiting();
		System.out.println("EL hilo " + threadID + " acaba de ejecutar el método enterAndWait()\n");
	}

	public boolean isFinished() {
		boolean retorno = false;

		if (count.get() == 0) {
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

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Set<Long> getSetIds() {
		return setIds;
	}

	public void setSetIds(Set<Long> setIds) {
		this.setIds = setIds;
	}

	public long getThreadID() {
		return threadID;
	}

	public void setThreadID(long threadID) {
		this.threadID = threadID;
	}

}
