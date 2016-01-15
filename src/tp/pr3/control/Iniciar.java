package tp.pr3.control;

/**
 * Clase que implementa el comando INICIAR.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Iniciar implements Comando {
	//Identificador del comando
	public final String INICIAR = "INICIAR";

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.inicia();
	}

	@Override
	public String textoAyuda() {
		return ("INICIAR: Inicia una nueva simulacion");
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(INICIAR))
			return this;
		else
			return null;
	}
}
