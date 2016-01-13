package tp.pr3.control;

public class ArchivoIncorrecto extends Exception {
	public ArchivoIncorrecto() {
		super("No se pudo abrir el archivo.");
	}
}
