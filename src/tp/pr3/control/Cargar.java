package tp.pr3.control;

/**
 * Clase que implementa el comando Cargar.
 * 
 * @version 1.0, 18/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Cargar implements Comando {
	public final String CARGAR = "CARGAR";
	private String nombreFichero; // Nombre del fichero a cargar.

	public Cargar(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	@Override
	public void ejecuta(Controlador controlador) throws PalabraIncorrecta, ArchivoIncorrecto, ArchivoNoEncontrado {
		controlador.carga(this.nombreFichero);

	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando Cargar si el array de strings se corresponde con este,
	 *         null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 2)
			return null;
		else if (cadenaComando[0].equals(CARGAR))
			return new Cargar(cadenaComando[1]);
		else
			return null;
	}

	/**
	 * Genera el codigo de ayuda referente a Cargar.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("CARGAR NOMFIC: Carga como juego actual el almacenado" + " en el fichero de texto NOMFICH");
	}

}
