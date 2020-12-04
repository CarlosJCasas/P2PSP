package ejercicio1;

public class Main {

	public static void main(String[] args) {
		ClassA claseA = new ClassA();
		ClassB classB = new ClassB(claseA);
		Thread hilo;
		for (int i=0 ; i<=5 ; i++) {
			hilo = new Thread(classB);
			hilo.start();
		}
	}

}
