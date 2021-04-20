package buscador;

import java.util.ArrayList;

import bbdd.conexionBD;
import contactos.contacto;

public class herramientasContactos {
	
	public static void buscarContacto(String nombre) {
		conexionBD con = new conexionBD();
        con.connect();
        ArrayList<contacto> contactos = con.buscarContacto(nombre);
        
        for (contacto Contacto : contactos) {
        	System.out.print("ID: ");
			System.out.println(Contacto.getId());

			System.out.print("Nombre: ");
			System.out.println(Contacto.getNombre());

			System.out.print("Telefono: ");
			System.out.println(Contacto.getTelefono());
			
			System.out.print("Aficiones: ");
			System.out.println(Contacto.getAficiones());

			System.out.println("=======================");
		}
        contactos.clear();
	}
	
//	public static contacto obtenerContacto() {
//		
//	}
	
	public static void mostrarContactos() {
		conexionBD con = new conexionBD();
        con.connect();
        con.mostrarContactos();
        con.close();
	}
	
	public static void selectCont(int i ) {
		conexionBD con = new conexionBD();
        con.connect();
        contacto cont = con.mostrarContactoSing(i);
        System.out.print("ID: ");
		System.out.println(cont.getId());

		System.out.print("Nombre: ");
		System.out.println(cont.getNombre());

		System.out.print("Telefono: ");
		System.out.println(cont.getTelefono());
		
		System.out.print("Aficiones: ");
		System.out.println(cont.getAficiones());
        con.close();
	}
	
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
	
	public static void cambiarTelefono(int i,String nombre) {
		conexionBD con = new conexionBD();
        con.connect();
        con.actualizarTelefono(i, nombre);
        con.close();
	}
	
	public static void cambiarAficion(int i,String nombre) {
		conexionBD con = new conexionBD();
        con.connect();
        con.actualizarAficiones(i, nombre);
        con.close();
	}
}
