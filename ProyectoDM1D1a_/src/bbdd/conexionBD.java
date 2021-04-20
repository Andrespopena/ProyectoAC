package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import contactos.contacto;

public class conexionBD {
	Connection connect;

	public void connect() {
		try {
			connect = DriverManager.getConnection("jdbc:sqlite:Contactos");
			java.sql.Statement enunciado;
			enunciado = connect.createStatement();
			enunciado.execute("CREATE TABLE IF NOT EXISTS contacto (\n"
					+ "id integer primary key,\n"
					+ "nombre text NOT NULL,\n"
					+ "aficiones text\n"
					+ ");");
			enunciado.execute("CREATE TABLE IF NOT EXISTS telefono (\n"
					+ "id integer primary key,\n"
					+ "telefono text NOT NULL,\n"
					+ "contacto integer, \n"
					+ "FOREIGN KEY(contacto) REFERENCES contacto(id) \n"
					+ ");");
			enunciado.execute("CREATE TABLE IF NOT EXISTS aficion (\n"
					+ "id integer primary key,\n"
					+ "nombre text NOT NULL,\n"
					+ "contacto integer, \n"
					+ "FOREIGN KEY(contacto) REFERENCES contacto(id) \n"
					+ ");");
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

	public void guardarContacto(contacto Contacto) {
		try {
			PreparedStatement st = connect.prepareStatement(
					"insert into contacto (nombre, telefono, aficiones) values (?,?,?);");
			st.setString(1, Contacto.getNombre());
			st.setString(2, Contacto.getTelefono());
			st.setString(3, Contacto.getAficiones());
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void borrarContacto(contacto Contacto) {
		try {
			PreparedStatement st = connect.prepareStatement(
					"DELETE FROM contacto where ID = '?'");
			st.setInt(1, Contacto.getId());
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public ArrayList<contacto> buscarContacto(String nombre) {
		ArrayList<contacto> contactos = new ArrayList<contacto>();
		ResultSet result = null;
		try {
			PreparedStatement st = connect.prepareStatement("SELECT * FROM contacto WHERE nombre LIKE '%"+nombre+"%'");
			result = st.executeQuery();
			while (result.next()) {
				contacto Contacto = new contacto();
				Contacto.setId(result.getInt("id"));
				Contacto.setNombre(result.getString("nombre"));
				Contacto.setTelefono(result.getString("telefono"));
				Contacto.setAficiones(result.getString("aficiones"));
				
				contactos.add(Contacto);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return contactos;
	}
	
	public contacto mostrarContactoSing(int i) {
		contacto cont = new contacto();
		ResultSet result = null;
		try {
			PreparedStatement st = connect.prepareStatement("SELECT * FROM contacto WHERE id LIKE ?");
			st.setInt(1, i);
			result = st.executeQuery();
			cont.setId(result.getInt("id"));
			cont.setNombre(result.getString("nombre"));
			cont.setTelefono(result.getString("telefono"));
			cont.setAficiones(result.getString("aficiones"));
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return cont;
	}

	public void mostrarContactos() {
		ResultSet result = null;
		try {
			PreparedStatement st = connect.prepareStatement("select * from contacto");
			result = st.executeQuery();
			while (result.next()) {
				System.out.print("ID: ");
				System.out.println(result.getInt("id"));

				System.out.print("Nombre: ");
				System.out.println(result.getString("nombre"));

				System.out.print("Telefono: ");
				System.out.println(result.getString("telefono"));
				
				System.out.print("Aficiones: ");
				System.out.println(result.getString("aficiones"));

				System.out.println("=======================");
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
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
	
	public void actualizarTelefono(int i, String nombre) {
		try {
			PreparedStatement st = connect.prepareStatement("UPDATE contacto SET nombre = '"+nombre+"'  WHERE id = "+i);
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void actualizarAficiones(int i, String nombre) {
		try {
			PreparedStatement st = connect.prepareStatement("UPDATE contacto SET nombre = '"+nombre+"'  WHERE id = "+i);
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void deleteCont(int i) {
		try {
			PreparedStatement st = connect.prepareStatement(
					"DELETE FROM contacto where ID = "+i );
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
