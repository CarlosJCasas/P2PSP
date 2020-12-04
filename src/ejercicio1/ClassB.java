package ejercicio1;

public class ClassB implements Runnable{
	
	private ClassA claseA = new ClassA();
	
	
	public ClassB(ClassA claseA) {
		this.claseA = claseA;
	}


	@Override
	public void run() {
		
		claseA.enterAndWait();
		
	}
}
