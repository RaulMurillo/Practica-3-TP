package tp.pr3.control;

/**
 * Clase que implementa el comando salir
 * 
 * @version 1.0, 07/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Vaciar implements Comando {
	public final String VACIAR = "VACIAR";

	/**
	 * Vacia la superficie del mundo de celulas.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public void ejecuta(Controlador controlador) {
		controlador.vacia();
	}

	/**
	 * Genera el codigo de ayuda referente a Vaciar.
	 * 
	 * @return Texto de ayuda correspondiente al comando.
	 */
	public String textoAyuda() {
		return ("VACIAR: Crea un mundo vacio");
	}

	/**
	 * Parsea un array de String para construir el comando que representa.
	 * 
	 * @param cadenaComando
	 *            Array de String a parsear.
	 * @return Comando Vaciar si el array de strings se corresponde con este,
	 *         null en otro caso.
	 */
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(VACIAR))
			return this;
		else
			return null;
	}
}
