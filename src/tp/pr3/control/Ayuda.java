package tp.pr3.control;

/**
 * Clase que implementa el comando AYUDA.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Ayuda implements Comando {
	// Identificador del comando
	public final String AYUDA = "AYUDA";

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.muestraAyuda();
	}

	@Override
	public String textoAyuda() {
		return ("AYUDA: Muestra esta ayuda");
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(AYUDA))
			return this;
		else
			return null;
	}
}
