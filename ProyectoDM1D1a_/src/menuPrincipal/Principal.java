package menuPrincipal;
/**
 * Clase principal del proyecto donde se hace la llamada a los principales metodos.
 * 
 * @author andres
 * @version 1.0
 */

public class Principal {
	public static void main(String[] args) {
		int opcion = 0;
		/**
		 * Aqui empieza el programa cargando el menú.
		 */
		while (opcion != 5) {
			/**
			 * El metodo menuPrincipal imprime las diferentes opcines lee la opcion que introduce el usuario y la devuelve para cargarla en el switch.
			 */
			opcion = recursosMenu.menuPrincial();
			switch (opcion) {
			case 1:
				/**
				 * Esta opción es para ver los contactos existentes
				 */
				recursosMenu.verContactos();
				break;
			case 2:
				/**
				 * Esta opción es para añadir/Crear un nuevo usuario.
				 */
				recursosMenu.anadirContacto();
				break;
			case 3:
				/**
				 * Opción para eliminar contacto.
				 */
				recursosMenu.eliminarContacto();
				break;
			case 4:
				/**
				 * opcion para modificar contacto ya existente
				 */
				recursosMenu.modificarContacto();
				break;
			}
		}
	}
}
