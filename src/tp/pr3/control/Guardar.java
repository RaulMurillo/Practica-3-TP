package tp.pr3.control;

/**
 * Clase que implementa el comando Guardar.
 * 
 * @version 1.0, 18/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Guardar extends Comando {
	public final String GUARDAR = "GUARDAR";
	private String nombreFichero; // Nombre del fichero a guardar.

	@Override
	public void ejecuta(Controlador controlador) {
		// TODO Auto-generated method stub

	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Genera el codigo de ayuda referente a Paso.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("GUARDAR NOMFICH: Permite almacenar en un fichero de texto, de nombre NOMBFICH, la configuracion del juego actual");
	}

}
