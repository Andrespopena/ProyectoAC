package contactos;

import bbdd.conexionBD;

public class contacto {
	private int id;
	private String nombre;
	private String telefono;
	private String aficiones;
	
	
	public contacto() {
		
	}
	public contacto(String nombre, String telefono, String aficiones) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.aficiones = aficiones;
	}
	
	public contacto(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.aficiones = null;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getAficiones() {
		return aficiones;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setAficiones(String aficiones) {
		this.aficiones = aficiones;
	}
	
	public void save(){
        conexionBD con = new conexionBD();
        con.connect();
        con.guardarContacto(this);
        con.mostrarContactos();
        con.close();
    }
	
	public void eliminar() {
		conexionBD con = new conexionBD();
        con.connect();
        con.borrarContacto(this);
        con.mostrarContactos();
        con.close();
	}
	
//	public void actualizarNombre(String nombre) {
//		conexionBD con = new conexionBD();
//        con.connect();
//        con.actualizarNombre(this, nombre);
//	}
//	
//	public void actualizarTelefono(String telefono) {
//		conexionBD con = new conexionBD();
//        con.connect();
//        con.actualizarTelefono(this, telefono);
//	}
//	
//	public void actualizarAficiones(String aficion) {
//		conexionBD con = new conexionBD();
//        con.connect();
//        con.actualizarAficiones(this, aficion);
//	}
}
