package testeo;

import bbdd.conexionBD;

public class test {
	public static void main(String[] args) {
		conexionBD con = new conexionBD();
		con.connect();
		con.close();
	}
}
