package menuPrincipal;

import java.util.Scanner;

import ValidacionDatos.VD;
import bbdd.conexionBD;
import buscador.herramientasContactos;
import objetos.contacto;

public class recursosMenu {
	static boolean bucle = true;
	static int menuPrincial() {
		int aux = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("+=========== Menú ============+");
		System.out.println("1. Ver contactos.");
		System.out.println("2. Añadir contacto.");
		System.out.println("3. Eliminar Contacto.");
		System.out.println("4. Modificar Contacto");
		System.out.println("5. Salir.");
		System.out.println("+=========== Menú ============+");
		try {
			System.out.println("Seleccione una opcion: ");
			aux = sc.nextInt();
		} catch (Exception e) {
			System.err.println("Dato erroneo.");
		}
		return aux;
	}
	
	static void verContactos() {
		System.out.println();
		System.out.println("+=========== Ver contactos ============+");
		herramientasContactos.verContactos();
		System.out.println();
	}
	
	static void anadirContacto() {
		Scanner sc = new Scanner(System.in);
		System.out.println("+=========== Añadir contacto ============+");
		herramientasContactos.crearContacto();
		System.out.println("+=========== =============== ============+");
	}
	
	static void eliminarContacto() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("+=========== Eliminar contacto ============+");
		System.out.println("Buscar contacto que quiere eliminar: ");
		String nombre = sc.next();
		System.out.println("======= Resultados =======");
		herramientasContactos.buscarContactos(nombre);
		System.out.println("Seleciona ID: ");
		int i = sc.nextInt();
		System.out.println("Ha seleccionado: ");
		herramientasContactos.buscarContactosId(i);
		System.out.println("¿Desea eliminar? S/N");
		char c = sc.next().charAt(0);
		if (c == 'S' || c == 's') {
			herramientasContactos.deleteCont(i);
		}
	}
	
	static void modificarContacto() {
		conexionBD con = new conexionBD();
        con.connect();
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("+=========== Actualizar contacto ============+");
		System.out.println("Buscar contacto que quiere actualizar: ");
		String nombre = sc.next();
		System.out.println("======= Resultados =======");
		herramientasContactos.buscarContactos(nombre);
		System.out.println("Seleciona ID: ");
		int i = sc.nextInt();
		System.out.println("Ha seleccionado: ");
		herramientasContactos.buscarContactosId(i);
		System.out.println("¿Qué quiere actualizar?\n"
				+"1. Nombre\n"
				+"2. Telefono\n"
				+"3. Aficiones"
				+"4. Ninguna");
		int optionMod = sc.nextInt();
		switch (optionMod) {
		case 1: {
			System.out.println("Nombre: ");
			String nueNombre = sc.next();
			herramientasContactos.cambiarNombre(i, nueNombre);
			break;
		}
		case 2: {
			System.out.println("¿Añadir. modificar o eliminar? 1.2.3");
			int op = sc.nextInt();
			switch (op) {
			case 1: {
				System.out.println("Nuevo telefono: ");
				String nueTelefono = sc.next();
				con.saveTelefono(1, nueTelefono);
				break;
			}
			case 2: {
				
				break;
			}
			case 3: {
				break;
			}
			}
			break;
		}
		case 3: {
			System.out.println("Aficion: ");
			
			break;
		}
		case 4: {
			break;
		}
		}
		con.close();
	}
}
