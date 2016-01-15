package tp.pr3.control;

/**
 * Clase que implementa el comando CARGAR.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Cargar implements Comando {
	// Identificador del comando
	public final String CARGAR = "CARGAR";
	// Nombre del fichero a cargar.
	private String nombreFichero;

	/**
	 * Constructor de la clase. Asigna el nombre del fichero desde el que se
	 * cargara la informacion de la partida.
	 * 
	 * @param nombreFichero
	 *            Fichero desde el que se cargara la informacion de la partida.
	 */
	public Cargar(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	@Override
	public void ejecuta(Controlador controlador) throws PalabraIncorrecta, ArchivoIncorrecto, ArchivoNoEncontrado {
		controlador.carga(this.nombreFichero);

	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 2)
			return null;
		else if (cadenaComando[0].equals(CARGAR))
			return new Cargar(cadenaComando[1]);
		else
			return null;
	}

	@Override
	public String textoAyuda() {
		return ("CARGAR NOMFIC: Carga como juego actual el almacenado" + " en el fichero de texto NOMFICH");
	}

}
