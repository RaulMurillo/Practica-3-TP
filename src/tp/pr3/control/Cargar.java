package tp.pr3.control;

/**
 * Clase que implementa el comando Cargar.
 * 
 * @version 1.0, 18/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Cargar extends Comando {
	public final String CARGAR = "CARGAR";
	private String nombreFichero; // Nombre del fichero a cargar.

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
	 * Genera el codigo de ayuda referente a Cargar.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("CARGAR NOMFIC: Carga como juego actual el almacenado en el fichero de texto NOMFICH");
	}

}
