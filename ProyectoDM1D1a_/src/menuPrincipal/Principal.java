package menuPrincipal;

import java.util.Scanner;
public class Principal {
	public static void main(String[] args) {
		int opcion = 0;
		while (opcion != 5) {
			boolean bucle = true;
			Scanner sc = new Scanner(System.in);
			opcion = recursosMenu.menuPrincial();

			switch (opcion) {
			case 1:
				recursosMenu.verContactos();
				sc.nextLine();
				break;
			case 2:
				while (bucle) {
					bucle = recursosMenu.anadirContacto();
				}
				break;
			case 3:
				recursosMenu.eliminarContacto();
				break;
			case 4:
				recursosMenu.modificarContacto();
				break;
			}
			sc.close();
		}
	}
}
