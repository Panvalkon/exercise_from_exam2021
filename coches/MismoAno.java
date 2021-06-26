package coches;

public class MismoAno implements Criterio {
	
	private int ano;

	public MismoAno(int ano) {
		this.ano = ano;
	}

	@Override
	public boolean cumpleCondicion(Coche coche) {
		
		return coche.getAno() == this.ano;
	}

}
