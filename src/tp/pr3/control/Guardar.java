package tp.pr3.control;

/**
 * Clase que implementa el comando GUARDAR.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Guardar implements Comando {
	// Identificador del comando
	public final String GUARDAR = "GUARDAR";
	// Nombre del fichero a guardar.
	private String nombreFichero;

	/**
	 * Constructor de la clase. Asigna el nombre del fichero donde se guardara
	 * la informacion de la partida.
	 * 
	 * @param nombreFichero
	 *            Fichero donde se guardara la informacion de la partida.
	 */
	public Guardar(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	@Override
	public void ejecuta(Controlador controlador) throws ArchivoIncorrecto {
		controlador.guarda(this.nombreFichero);
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 2)
			return null;
		else if (cadenaComando[0].equals(GUARDAR))
			return new Guardar(cadenaComando[1]);
		else
			return null;
	}

	@Override
	public String textoAyuda() {
		return ("GUARDAR NOMFICH: Permite almacenar en un fichero de texto, "
				+ "de nombre NOMBFICH, la configuracion del juego actual");
	}

}
