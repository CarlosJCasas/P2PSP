package ejercicio2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) {
		int CONTADOR = 10;
		int NHILOS = 10;
		String metodo;
		String respuesta;
		boolean control = false;
		boolean condicion = true;
		do {
			LinkedList<ClassB> listaCb = new LinkedList<ClassB>();
			LinkedList<ClassBAll> listaCbAll = new LinkedList<ClassBAll>();
			ArrayList<Thread> threads = new ArrayList<Thread>();
			Scanner sc = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			do {
				System.out.println("Introduce cuantos hilos quieres que se ejecuten");
				try {
					NHILOS = sc.nextInt();
					control = false;
				} catch (InputMismatchException e1) {
					System.out.println("Debe introducir un número entero");
					sleepy();
					control = true;
					sc.nextLine();
				}
			} while (control);
			do {
				System.out.println("Introduce cuantos accesos totales");
				try {
					CONTADOR = sc.nextInt();
					control = false;
				} catch (InputMismatchException e) {
					System.out.println("Debe introducir un número entero");
					sleepy();
					control = true;
					sc.nextLine();
				}
			} while (control);
			do {
				System.out.println("Indica que método quieres utilizar: 1. Notify o 2. NotifyAll");
				metodo = sc2.nextLine();
				control = false;
				
//				if(!metodo.equalsIgnoreCase("1") || !metodo.equalsIgnoreCase("notify") || 
//						!metodo.equalsIgnoreCase("2") || !metodo.equalsIgnoreCase("notifyall")) {
//					
//					System.out.println("Debe introducir 1 o notify para la primera opción o 2 o notifyall para la segunda opción");
//					control = true;
//					sc.nextLine();
//				}
			} while (control);
			
			ClassA claseA = new ClassA(CONTADOR);
			AtomicInteger aCONTADOR = new AtomicInteger(CONTADOR);
			ClassAAll claseAll = new ClassAAll(aCONTADOR);

			if (metodo.equalsIgnoreCase("1") || metodo.equalsIgnoreCase("notify")) {

				for (int i = 0; i < NHILOS; i++) {
					ClassB claseB = new ClassB(claseA);
					listaCb.add(claseB);

				}

				for (int i = 0; i < listaCb.size(); i++) {
					if (!(listaCb.size() == i + 1)) {
						listaCb.get(i).setNext(listaCb.get(i + 1));
					} else {
						listaCb.get(listaCb.size() - 1).setNext(listaCb.get(0));
					}
				}

				for (ClassB cb : listaCb) {
					Thread hilo = new Thread(cb);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
					hilo.start();
					threads.add(hilo);
				}

				synchronized (listaCb.getFirst()) {
					listaCb.getFirst().notify();
				}

				for (Thread thread : threads) {
					try {
						thread.join();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			} else if (metodo.equalsIgnoreCase("2") || metodo.equalsIgnoreCase("notifyall")) {

				for (int i = 0; i < NHILOS; i++) {
					ClassBAll claseBAll = new ClassBAll(claseAll);
					listaCbAll.add(claseBAll);
				}

				for (ClassBAll cba : listaCbAll) {
					cba.setListaCb(listaCbAll);
				}

				for (ClassBAll cba : listaCbAll) {
					Thread hilo = new Thread(cba);
					hilo.start();
					threads.add(hilo);
				}

				synchronized (listaCbAll) {
					try {
						listaCbAll.wait(500);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
					listaCbAll.notifyAll();
				}

				for (Thread thread : threads) {
					try {
						thread.join();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			}

			System.out.println("¿Qué quieres hacer?");
			System.out.println("1. Continuar o 2. Salir");
			respuesta = sc.next();
			if (respuesta.equalsIgnoreCase("1") || respuesta.equalsIgnoreCase("continuar")) {
				condicion = true;
			} else if (respuesta.equalsIgnoreCase("2") || respuesta.equalsIgnoreCase("salir")) {
				condicion = false;
			}
		} while (condicion);

	}
	private static void sleepy() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
}
