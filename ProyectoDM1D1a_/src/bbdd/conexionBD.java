package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import objetos.aficion;
import objetos.contacto;

public class conexionBD {
	Connection connect;

	public void connect() {
		try {
			/**
			 * metodo para crear conexión.
			 */
			connect = DriverManager.getConnection("jdbc:sqlite:Contactos");
			/**
			 * Preparamos el enunciado para pasarlo a la bbdd.
			 */
			java.sql.Statement enunciado;
			enunciado = connect.createStatement();
			enunciado.execute("CREATE TABLE IF NOT EXISTS contacto (\n"
					+ "id integer primary key,\n"
					+ "nombre text NOT NULL\n"
					+ ");");
			enunciado.execute("CREATE TABLE IF NOT EXISTS telefono (\n"
					+ "id integer primary key,\n"
					+ "telefono text NOT NULL,\n"
					+ "contacto integer, \n"
					+ "FOREIGN KEY(contacto) REFERENCES contacto(id) \n"
					+ ");");
			enunciado.execute("CREATE TABLE IF NOT EXISTS aficion (\n"
					+ "id integer primary key,\n"
					+ "nombre text NOT NULL\n"
					+ ");");
			enunciado.execute("CREATE TABLE IF NOT EXISTS ContactoAficion (\n"
					+ "id integer primary key,\n"
					+ "id_contacto integer NOT NULL,\n"
					+ "id_aficion integer NOT NULL, \n"
					+ "FOREIGN KEY(id_contacto) REFERENCES contacto(id) \n"
					+ "FOREIGN KEY(id_aficion) REFERENCES aficiones(id) \n"
					+ ");");
			/**
			 * ejecutamos las sentencias para crear las bbdd en caso de no existir.
			 */
		} catch (SQLException ex) {
			System.err.println("No se ha podido conectar a la bd\n" + ex.getMessage());
		}
	}

	public void close() {
		try {
			connect.close();
		} catch (SQLException ex) {
			Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void actualizarNombre(int i, String nombre) {
		try {
			PreparedStatement st = connect.prepareStatement("UPDATE contacto SET nombre = '"+nombre+"'  WHERE id = "+i);
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	//Nuevos Metodos..........................................................................................................
	
	public int saveContacto(String nombre) {
		int id = 0;
		ResultSet result = null;
		try {
			PreparedStatement st = connect.prepareStatement(
					"insert into contacto (nombre) values (?);");
			st.setString(1, nombre);
			st.execute();
			st = connect.prepareStatement("select * from contacto WHERE id = (SELECT MAX(id) FROM contacto);");
			result = st.executeQuery();
			id = result.getInt("id");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return id;
	}
	
	public void saveTelefono(int id, String telefono) {
		try {
			PreparedStatement st = connect.prepareStatement(
					"insert into telefono (telefono, contacto) values (?, ?);");
			st.setString(1, telefono);
			st.setInt(2, id);
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void saveAficionContacto(int id_contacto, int id_aficion) {
		try {
			PreparedStatement st = connect.prepareStatement(
					"insert into ContactoAficion (id_contacto, id_aficion) values (?, ?);");
			st.setInt(1, id_contacto);
			st.setInt(2, id_aficion);
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void crearAficion(String aficion) {
		try {
			PreparedStatement st = connect.prepareStatement(
					"insert into aficion (nombre) values (?);");
			st.setString(1, aficion);
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public ArrayList<aficion> verAficiones() {
		ArrayList<aficion> aficiones = new ArrayList<aficion>();
		ResultSet result = null;
		try {
			PreparedStatement st = connect.prepareStatement("SELECT * FROM aficion;");
			result = st.executeQuery();
			while (result.next()) {
				aficion aficion = new aficion();
				aficion.setId(result.getInt("id"));
				aficion.setNombre(result.getString("nombre"));
				aficiones.add(aficion);
			}
			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return aficiones;
	}
	
	public ArrayList<contacto> buscarContacto(String nombre) {
		ArrayList<contacto> contactos = new ArrayList<contacto>();
		ResultSet contacto = null;
		ResultSet telefono = null;
		ResultSet aficion = null;
		try {
			PreparedStatement st = connect.prepareStatement("SELECT * FROM contacto WHERE nombre LIKE '%"+nombre+"%' ;");
			contacto = st.executeQuery();
			while (contacto.next()) {
				ArrayList<String> Telefonos = new ArrayList<String>();
				ArrayList<aficion> Aficiones = new ArrayList<aficion>();
				contacto Contacto = new contacto();
				Contacto.setId(contacto.getInt("id"));
				Contacto.setNombre(contacto.getString("nombre"));
				
				PreparedStatement telefonos = connect.prepareStatement("SELECT telefono FROM telefono WHERE contacto = "+contacto.getInt("id")+";");
				telefono = telefonos.executeQuery();
				while (((ResultSet) telefono).next()) {
					Telefonos.add(telefono.getString("telefono"));
				}
				
				PreparedStatement aficiones = connect.prepareStatement("SELECT aficion.id, aficion.nombre FROM contacto INNER JOIN ContactoAficion\n"
						+ "    on contacto.id = ContactoAficion.id_contacto INNER JOIN aficion\n"
						+ "    on ContactoAficion.id_aficion = aficion.id  WHERE contacto.id = "+contacto.getInt("id")+";");
				aficion = aficiones.executeQuery();
				while (((ResultSet) aficion).next()) {
					aficion Aficion = new aficion(aficion.getInt("id"), aficion.getString("nombre"));
					Aficiones.add(Aficion);
				}
				
				Contacto.setTelefonos(Telefonos);
				Contacto.setAficiones(Aficiones);
				contactos.add(Contacto);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return contactos;
	}
	
	public ArrayList<contacto> buscarContactoId(int i) {
		ArrayList<contacto> contactos = new ArrayList<contacto>();
		ResultSet contacto = null;
		ResultSet telefono = null;
		ResultSet aficion = null;
		try {
			PreparedStatement st = connect.prepareStatement("SELECT * FROM contacto WHERE id LIKE "+i+" ;");
			contacto = st.executeQuery();
			while (contacto.next()) {
				ArrayList<String> Telefonos = new ArrayList<String>();
				ArrayList<aficion> Aficiones = new ArrayList<aficion>();
				contacto Contacto = new contacto();
				Contacto.setId(contacto.getInt("id"));
				Contacto.setNombre(contacto.getString("nombre"));
				
				PreparedStatement telefonos = connect.prepareStatement("SELECT telefono FROM telefono WHERE contacto = "+contacto.getInt("id")+";");
				telefono = telefonos.executeQuery();
				while (((ResultSet) telefono).next()) {
					Telefonos.add(telefono.getString("telefono"));
				}
				
				PreparedStatement aficiones = connect.prepareStatement("SELECT aficion.id, aficion.nombre FROM contacto INNER JOIN ContactoAficion\n"
						+ "    on contacto.id = ContactoAficion.id_contacto INNER JOIN aficion\n"
						+ "    on ContactoAficion.id_aficion = aficion.id  WHERE contacto.id = "+contacto.getInt("id")+";");
				aficion = aficiones.executeQuery();
				while (((ResultSet) aficion).next()) {
					aficion Aficion = new aficion(aficion.getInt("id"), aficion.getString("nombre"));
					Aficiones.add(Aficion);
				}
				
				Contacto.setTelefonos(Telefonos);
				Contacto.setAficiones(Aficiones);
				contactos.add(Contacto);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return contactos;
	}
	
	public void deleteCont(int i) {
		try {
			PreparedStatement st = connect.prepareStatement(
					"DELETE FROM contacto where id = "+i+" ;" );
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void actualizarTelefono(String telefono, String nombre) {
		try {
			PreparedStatement st = connect.prepareStatement("UPDATE telefono SET telefono = '"+nombre+"'  WHERE telefono= "+telefono);
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void deleteAficiones(int idContacto, int idAficion) {
		try {
			PreparedStatement st = connect.prepareStatement("DELETE FROM ContactoAficion WHERE id_contacto = "+idContacto+" AND id_aficion = "+idAficion);
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
