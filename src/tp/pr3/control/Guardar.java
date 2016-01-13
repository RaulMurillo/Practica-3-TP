package tp.pr3.control;

/**
 * Clase que implementa el comando Guardar.
 * 
 * @version 1.0, 18/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Guardar implements Comando {
	public final String GUARDAR = "GUARDAR";
	private String nombreFichero; // Nombre del fichero a guardar.

	public Guardar(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	@Override
	public void ejecuta(Controlador controlador) throws ArchivoIncorrecto {
		controlador.guarda(this.nombreFichero);
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando Guardar si el array de strings se corresponde con este, null
	 *         en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 2)
			return null;
		else if (cadenaComando[0].equals(GUARDAR))
			return new Guardar(cadenaComando[1]);
		else
			return null;
	}

	/**
	 * Genera el codigo de ayuda referente a Paso.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("GUARDAR NOMFICH: Permite almacenar en un fichero de texto, "
				+ "de nombre NOMBFICH, la configuracion del juego actual");
	}

}
