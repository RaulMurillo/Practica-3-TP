package tp.pr3.control;

/**
 * Clase que implementa el comando SALIR.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Salir implements Comando {
	// Identificador del comando
	public final String SALIR = "SALIR";

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.sal();
	}

	@Override
	public String textoAyuda() {
		return ("SALIR: Cierra la aplicacion");
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(SALIR))
			return this;
		else
			return null;
	}
}
