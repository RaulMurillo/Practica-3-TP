package tp.pr3.control;

public class ArchivoNoEncontrado extends Exception {
	public ArchivoNoEncontrado() {
		super("No se encontro el archivo.");
	}
}
