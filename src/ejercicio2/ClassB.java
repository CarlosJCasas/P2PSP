package ejercicio2;

import java.util.LinkedList;

public class ClassB implements Runnable {
	private int contador;
	private ClassA claseA = new ClassA(contador);
	private ClassB next;
	private boolean lock = true;
	private LinkedList<ClassB> listaCb;

	public ClassB(ClassA claseA) {
		this.claseA = claseA;
	}

	public ClassB(ClassA claseA, ClassB next) {
		this.claseA = claseA;
		this.next = next;
	}

	public ClassB(ClassA claseA, LinkedList<ClassB> listaCb) {
		this.claseA = claseA;
		this.listaCb = listaCb;
	}

	public ClassB() {

	}

	@Override
	public void run() {

		while (lock) {
			synchronized (this) {
				if (!this.equals(next)) {
					try {
						wait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
				if (claseA.isFinished()) {
					lock = false;
				} else {
					claseA.enterAndWait();
				}
				synchronized (next) {
					next.notify();
				}
			}
		}
	}

	public ClassB getClaseB() {
		return next;
	}

	public void setClaseB(ClassB claseB) {

		this.next = claseB;
	}

	public LinkedList<ClassB> getListaCb() {

		return listaCb;
	}

	public void setListaCb(LinkedList<ClassB> listaCb) {

		this.listaCb = listaCb;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public ClassA getClaseA() {
		return claseA;
	}

	public void setClaseA(ClassA claseA) {
		this.claseA = claseA;
	}

	public ClassB getNext() {
		return next;
	}

	public void setNext(ClassB next) {
		this.next = next;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}
}
