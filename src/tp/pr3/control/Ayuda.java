package tp.pr3.control;

import tp.pr3.logica.*;

/**
 * Clase que implementa el comando ayuda.
 * 
 * @version 1.1, 09/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Ayuda extends Comando {
	public final String AYUDA = "AYUDA";

	/**
	 * Metodo que ejecuta el comando ayuda mostrando las lineas de ayuda.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Mundo mundo) {
		System.out.print(ParserComandos.AyudaComandos());
	}

	/**
	 * Genera el codigo de ayuda referente a Ayuda.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("AYUDA: Muestra esta ayuda");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando Ayuda si el array de strings se corresponde con este,
	 *         null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(AYUDA))
			return this;
		else
			return null;
	}
}
