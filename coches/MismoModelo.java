package coches;

public class MismoModelo implements Criterio {

	private String modelo;

	public MismoModelo(String modelo) throws CochesException {
		if (modelo == null || modelo == "") {
			throw new CochesException("Invalid car model");
		}
		this.modelo = modelo;
	}

	@Override
	public boolean cumpleCondicion(Coche coche) {
		return modelo.equalsIgnoreCase(coche.getModelo());
	}

}
