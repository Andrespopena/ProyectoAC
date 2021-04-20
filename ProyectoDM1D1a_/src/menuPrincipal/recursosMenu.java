package menuPrincipal;

import java.util.Scanner;

import ValidacionDatos.VD;
import buscador.herramientasContactos;
import contactos.contacto;

public class recursosMenu {
	static boolean bucle = true; 
	static Scanner sc = new Scanner(System.in);
	static int menuPrincial() {
		System.out.println("+=========== Menú ============+");
		System.out.println("1. Ver contactos.");
		System.out.println("2. Añadir contacto.");
		System.out.println("3. Eliminar Contacto.");
		System.out.println("4. Modificar Contacto");
		System.out.println("5. Salir.");
		System.out.println("+=========== Menú ============+");
		System.out.println("Seleccione una opción: ");
		return VD.VDInt();
	}
	
	static void verContactos() {
		System.out.println();
		System.out.println("+=========== Ver contactos ============+");
		herramientasContactos.mostrarContactos();
		System.out.println("para continuar pulse cualquier tecla...");
	}
	
	static boolean anadirContacto() {
		System.out.println("+=========== Añadir contacto ============+");
		System.out.println();
		System.out.println("Introduzca Nombre: ");
		String nombre = sc.next();
		System.out.println("Introduzca Telefono: ");
		String telefono = sc.next();
		System.out.println("Introduzca Aficiones: ");
		String aficiones = sc.next();
		contacto cont = new contacto(nombre, telefono, aficiones);
		System.out.print("¿Quiere guardar el siguiente contacto?:\n" + "Nombre: " + nombre + "\n"
				+ "Telefono: " + telefono + "\n" + "Aficiones: " + aficiones + "\n" + "\n" + "[S/N]");
		char c = sc.next().charAt(0);
		if (c == 'S' || c == 's') {
			cont.save();
			bucle = false;
		} else {
			System.out.println("¿Quiere salir sin guardar? S/N");
			if (sc.next().charAt(0) == 'S' || sc.next().charAt(0) == 's') {
				bucle = false;
			}
		}
		
		return bucle;
	}
	
	static void eliminarContacto() {
		System.out.println();
		System.out.println("+=========== Eliminar contacto ============+");
		System.out.println("Buscar contacto que quiere eliminar: ");
		String nombre = sc.next();
		System.out.println("======= Resultados =======");
		herramientasContactos.buscarContacto(nombre);
		System.out.println("Seleciona ID: ");
		int i = sc.nextInt();
		System.out.println("Ha seleccionado: ");
		herramientasContactos.selectCont(i);
		System.out.println("¿Desea eliminar? S/N");
		char c = sc.next().charAt(0);
		if (c == 'S' || c == 's') {
			herramientasContactos.deleteCont(i);
		}
	}
	
	static void modificarContacto() {
		System.out.println();
		System.out.println("+=========== Actualizar contacto ============+");
		System.out.println("Buscar contacto que quiere actualizar: ");
		String nombre = sc.next();
		System.out.println("======= Resultados =======");
		herramientasContactos.buscarContacto(nombre);
		System.out.println("Seleciona ID: ");
		int i = sc.nextInt();
		System.out.println("Ha seleccionado: ");
		herramientasContactos.selectCont(i);
		System.out.println("¿Qué quiere actualizar?\n"
				+"1. Nombre\n"
				+"2. Telefono\n"
				+"3. Aficiones");
		int optionMod = sc.nextInt();
		switch (optionMod) {
		case 1: {
			System.out.println("Nombre: ");
			String nueNombre = sc.next();
			herramientasContactos.cambiarNombre(i, nueNombre);
			break;
		}
		case 2: {
			System.out.println("Telefono: ");
			String nueNombre = sc.next();
			herramientasContactos.cambiarTelefono(i, nueNombre);
			break;
		}
		case 3: {
			System.out.println("Aficion: ");
			String nueNombre = sc.next();
			herramientasContactos.cambiarAficion(i, nueNombre);
			break;
		}
		}
	}
}
