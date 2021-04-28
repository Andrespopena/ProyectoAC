package objetos;

import java.util.ArrayList;

import bbdd.conexionBD;

public class contacto {
	private int id;
	private String nombre;
	private ArrayList<String> telefonos = new ArrayList<String>();
	private ArrayList<aficion> aficiones = new ArrayList<aficion>();

	public contacto() {

	}

	public contacto(int id, String nombre, ArrayList<String> telefonos, ArrayList<aficion> aficiones) {
		this.aficiones = aficiones;
		this.id = id;
		this.nombre = nombre;
		this.telefonos = telefonos;
	}

	@Override
	public String toString() {
		return "Nombre = " + nombre + ", Id = " + id + ", Telefonos = " + telefonos.toString() 
			+ ", Aficiones = " + aficiones.toString();
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(ArrayList<String> telefonos) {
		this.telefonos = telefonos;
	}

	public ArrayList<aficion> getAficiones() {
		return aficiones;
	}

	public void setAficiones(ArrayList<aficion> aficiones) {
		this.aficiones = aficiones;
	}
}
