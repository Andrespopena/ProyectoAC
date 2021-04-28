package buscador;

import java.util.ArrayList;
import java.util.Scanner;

import bbdd.conexionBD;
import objetos.aficion;
import objetos.contacto;

public class herramientasContactos {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void deleteCont(int i) {
		conexionBD con = new conexionBD();
        con.connect();
        con.deleteCont(i);
        con.close();
	}
	
	public static void cambiarNombre(int i,String nombre) {
		conexionBD con = new conexionBD();
        con.connect();
        con.actualizarNombre(i, nombre);
        con.close();
	}
	
	//Nuevos metodos ..............................................................................................................................
	
	public static void crearContacto() {
		boolean vuelta = true;
		conexionBD con = new conexionBD();
        con.connect();
        //Primer paso crear contacto con nombre en la tabla contacto y nos quedamos con el id de contacto añadido
		System.out.println("Introduzca un nombre:");
		String nombre = sc.nextLine();
		int id_contacto = con.saveContacto(nombre);
		//Segundo paso añadir tantos telefonos como quiera el usuario minimo 1 en la tabla telefono con el id del usuario.
		while (vuelta) {
			System.out.println("Introduzca un telefono: ");
			nombre = sc.nextLine();
			System.out.println("Ha introducido "+nombre+" ¿Quiere guardar? S/N");
			char c = sc.next().charAt(0);
			sc.nextLine();
			if (c == 's' || c == 'S') {
				con.saveTelefono(id_contacto, nombre);
				sc.nextLine();
				System.out.println("¿Quiere agregar otro telefono? S/N");
				c = sc.next().charAt(0);
				sc.nextLine();
				if (c == 'n' || c == 'N') {
					vuelta = false;
				}
			}
		}
		System.out.println("¿Quiere añadir aficiones? S/N");
		char c = sc.next().charAt(0);
		sc.nextLine();
		if (c == 's' || c == 'S') {
			ArrayList<aficion> aficiones = con.verAficiones();
			boolean crearaficion = true;
			if (aficiones.size() == 0) {
				System.out.println("No se han encontrado aficiones debe crear una aficion.");
				while (crearaficion) {
					System.out.println("Introduzca Aficion:");
					con.crearAficion(sc.next());
					sc.nextLine();
					System.out.println("¿Quiere añadir más aficiones? S/N");
					c = sc.next().charAt(0);
					sc.nextLine();
					if ( c == 'n' || c == 'N')
						crearaficion = false;
				}
				aficiones = con.verAficiones();
			}
			for (aficion aficion : aficiones) {
				System.out.print("ID: ");
				System.out.println(aficion.getId());
				System.out.print("Nombre: ");
				System.out.println(aficion.getNombre());
			}
			System.out.println("¿Quiere añadir más aficiones? S/N");
			c = sc.next().charAt(0);
			sc.nextLine();
			if (c == 's' || c == 'S') {
				while (crearaficion) {
					System.out.println("Introduzca Aficion:");
					con.crearAficion(sc.next());
					sc.nextLine();
					System.out.println("¿Quiere añadir más aficiones? S/N");
					c = sc.next().charAt(0);
					sc.nextLine();
					if ( c == 'n' || c == 'N')
						crearaficion = false;
				}
			}
			System.out.println("Introduzca todos los ids que quiere añadir: ");
			String[] ids = sc.nextLine().split(" ");
			for (String id : ids) {
				con.saveAficionContacto(id_contacto, Integer.parseInt(id));
			}
		}
		ArrayList<contacto> nuevoContacto = con.buscarContactoId(id_contacto);
		for (contacto contacto : nuevoContacto) {
			System.out.println(contacto.toString());
		}
		System.out.println("¿Quiere guardar este contacto? S/N");
		c = sc.next().charAt(0);
		sc.nextLine();
		if ( c == 'n' || c == 'N')
			con.deleteCont(id_contacto);
		con.close();
	}
	
	public static void buscarContactos(String nombre) {
		conexionBD con = new conexionBD();
        con.connect();
        ArrayList<contacto> contactos= con.buscarContacto(nombre);
        
        for (contacto contacto : contactos) {
			System.out.println(contacto.toString());
		}
        
        con.close();
	}
	
	public static void buscarContactosId(int i) {
		conexionBD con = new conexionBD();
        con.connect();
        ArrayList<contacto> contactos = con.buscarContactoId(i);
        for (contacto contacto : contactos) {
			System.out.println(contacto.toString());
		}
        con.close();
	}
	
	public static void verContactos() {
		String str = "";
		conexionBD con = new conexionBD();
        con.connect();
        ArrayList<contacto> contactos = con.buscarContacto(str);
        for (contacto contacto : contactos) {
			System.out.println(contacto.toString());
		}
        con.close();
	}
}
