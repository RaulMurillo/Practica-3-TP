package tp.pr3.control;

/**
 * Interfaz especialzada por cada uno de los comandos de la practica.
 * 
 * @version 3.0, 11/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
interface Comando {

	/**
	 * Ejecuta la accion del comando correspondiente.
	 * 
	 * @param controlador
	 *            Controlador sobre el que se ejecuta la simulación.
	 * @throws PosicionNoValida
	 * @throws SeleccionNoValida
	 * @throws ArchivoIncorrecto
	 * @throws ArchivoNoEncontrado
	 * @throws PalabraIncorrecta
	 */
	public abstract void ejecuta(Controlador controlador)
			throws PosicionNoValida, SeleccionNoValida, ArchivoIncorrecto, ArchivoNoEncontrado, PalabraIncorrecta, IndicesFueraDeRango;

	/**
	 * Genera un String con la informacion de ayuda que se quiera mostrar sobre
	 * el comando.
	 * 
	 * @return String con la informacion de ayuda.
	 */
	public abstract String textoAyuda();

	/**
	 * Recibe un array de Strings, que debe procesar devolviendo el comando que
	 * representa el string en caso de que se trate de dicho comando, o null en
	 * caso contrario.
	 * 
	 * @param cadenaComando
	 *            Array de Strings con los comandos a parsear.
	 * @return Comando correspondiente, en caso de coincidir, null en caso
	 *         contrario.
	 * @throws ErrorDeInicializacion
	 * @throws NumberFormatException
	 */
	public abstract Comando parsea(String[] cadenaComando)
			throws FormatoNumericoIncorrecto, ErrorDeInicializacion, PalabraIncorrecta;

}
