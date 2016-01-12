package tp.pr3.control;

/**
 * Clase que implementa el comando paso
 * 
 * @version 1.0, 07/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Paso implements Comando {
	public final String PASO = "PASO";

	/**
	 * Ejecuta un paso sobre el mundo.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Controlador controlador) {
		controlador.daUnPaso();
	}

	/**
	 * Genera el codigo de ayuda referente a Paso.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("PASO: Realiza un paso en la simulacion");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando Paso si el array de strings se corresponde con este, null
	 *         en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(PASO))
			return this;
		else
			return null;
	}
}
