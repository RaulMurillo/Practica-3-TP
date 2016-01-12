package tp.pr3.control;

import java.io.*;

import tp.pr3.logica.*;

/**
 * Clase abstracta de la que heredan todos los comandos de la practica.
 * 
 * @version 2.0, 11/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
interface Comando {
	/**
	 * Ejecuta el comando correspondiente sobre el mundo.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecuta el comando.
	 */
	public abstract void ejecuta(Controlador controlador);

	/**
	 * Recibe un array de String, que debe procesar devolviendo el comando que
	 * representa el string.
	 * 
	 * @param cadenaComando
	 *            Array de Strings con los comandos a parsear.
	 * @return Comando correspondiente.
	 * @throws ErrorDeInicializacion
	 * @throws NumberFormatException
	 */
	public abstract Comando parsea(String[] cadenaComando) throws NumberFormatException, ErrorDeInicializacion,
			FileNotFoundException, IOException, IndicesFueraDeRango, PalabraIncorrecta;

	/**
	 * Genera un String con la informacion de ayuda que se quiera mostrar sobre
	 * el comando.
	 * 
	 * @return String con la informacion de ayuda.
	 */
	public abstract String textoAyuda();
}
