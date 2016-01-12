package tp.pr3.control;

/**
 * Clase que implementa el comando SALIR.
 * 
 * @version 1.0, 07/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Salir implements Comando {
	public final String SALIR = "SALIR";

	/**
	 * Termina la simulacion.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Controlador controlador) {
		controlador.sal();
	}

	/**
	 * Genera el codigo de ayuda referente a Salir.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("SALIR: Cierra la aplicacion");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando Salir si el array de strings se corresponde con este,
	 *         null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(SALIR))
			return this;
		else
			return null;
	}
}
