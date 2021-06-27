package coches;

public class MismoAno implements Criterio
{
	private int ano;

	public MismoAno(int ano)
	{
		this.ano = ano;
	}

	@Override
	public boolean cumpleCondicion(Coche c)
	{
		return c.getAno() == ano;	
	}
}
