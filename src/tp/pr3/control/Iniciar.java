package tp.pr3.control;

import tp.pr3.logica.Mundo;

/**
 * Clase que implementa el comando iniciar
 * 
 * @version 1.0, 07/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Iniciar extends Comando {
	public final String INICIAR = "INICIAR";

	/**
	 * Reinicia el mundo.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Mundo mundo) {
		mundo.inicializaMundo();
	}

	/**
	 * Genera el codigo de ayuda referente a Iniciar.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("INICIAR: Inicia una nueva simulacion");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando Iniciar si el array de strings se corresponde con este,
	 *         null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(INICIAR))
			return this;
		else
			return null;
	}
}
