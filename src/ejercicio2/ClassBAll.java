package ejercicio2;

import java.util.LinkedList;

public class ClassBAll implements Runnable {
	
	private ClassAAll claseAll;
	private LinkedList<ClassBAll> listaCb;
	private boolean lock = true;

	public ClassBAll(ClassAAll classAll) {
		this.claseAll = classAll;
	}

	@Override
	public void run() {
		while (lock) {
			synchronized (listaCb) {
				if(!(listaCb.size() == 1)) {
					try {
						listaCb.wait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
				
				if (claseAll.isFinished()) {
					lock = false;
				} else {
					claseAll.enterAndWait();
				}
				listaCb.notifyAll();
			}
		}
	}

	public LinkedList<ClassBAll> getListaCb() {
		return listaCb;
	}

	public void setListaCb(LinkedList<ClassBAll> listaCb) {
		this.listaCb = listaCb;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public ClassAAll getClaseAll() {
		return claseAll;
	}

	public void setClaseAll(ClassAAll claseAll) {
		this.claseAll = claseAll;
	}

}
