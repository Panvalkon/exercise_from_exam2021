package coches;

public class MismoModelo implements Criterio
{
	private String modelo;
	
	public MismoModelo(String modelo) throws CochesException
	{
		if (modelo == null || modelo.equals(""))
		{
			throw new CochesException("El modelo no puede estar vacío");
		}
		
		this.modelo = modelo;
	}

	@Override
	public boolean cumpleCondicion(Coche c)
	{
		return c.getModelo().equalsIgnoreCase(modelo);
	}
}
