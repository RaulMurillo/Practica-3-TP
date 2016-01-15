package tp.pr3.control;

/**
 * Clase que implementa el comando PASO.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Paso implements Comando {
	//Identificador del comando
	public final String PASO = "PASO";

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.daUnPaso();
	}

	@Override
	public String textoAyuda() {
		return ("PASO: Realiza un paso en la simulacion");
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando.length != 1)
			return null;
		else if (cadenaComando[0].equals(PASO))
			return this;
		else
			return null;
	}
}
