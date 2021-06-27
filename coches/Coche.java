package coches;

public class Coche implements Comparable<Coche>
{
	private final String modelo, NIV;
	private final int ano, mes, dia;
	
	public Coche(String modelo, String NIV, int mes, int dia, int ano) throws CochesException
	{
		if (modelo == null || modelo.isEmpty())
		{
			throw new CochesException("El modelo no puede estar vacío");
		}
		
		this.modelo = modelo;
		
		if (NIV.toUpperCase().matches(".*[OIQ]+.*") || NIV.length() != 17)
		{
			throw new CochesException("El niv no puede incluir las letras I/O/Q");
		}
		
		this.NIV = NIV.toUpperCase();
		this.ano = ano;
		
		if (dia < 1 || dia > 31)
		{
			throw new CochesException("Días invalidos, debe estar entre 1 y 31");	
		}
		
		this.dia = dia;
		
		if (mes < 1 || mes > 12)
		{
			throw new CochesException("Mes invalido, debe estar entre 1 y 12");
		}
		
		this.mes = mes;
	}
	public String getModelo()
	{
		return modelo;
	}
	public String getNIV()
	{
		return NIV;
	}
	public int getAno()
	{
		return ano;
	}
	public int getMes()
	{
		return mes;
	}
	public int getDia()
	{
		return dia;
	}
	@Override
	public int hashCode()
	{
		return NIV.hashCode() + dia + mes + ano;
	}
	@Override
	public boolean equals(Object o)
	{
		return (o instanceof Coche) && ((Coche) o).NIV.equals(NIV)
				&& ((Coche) o).ano == ano && ((Coche) o).dia == dia &&
				((Coche) o).mes == mes;
	}
	@Override
	public int compareTo(Coche o)
	{
		int res = o.dia + o.mes + o.ano,
			res2 = dia + mes + ano,
			
			resultado = Integer.compare(res, res2);
		
		if (resultado == 0)
		{
			resultado = modelo.compareToIgnoreCase(o.modelo);
		}
		
		return resultado;
	}
	@Override
	public String toString()
	{
		// "[5GZCZ43D13S812715, 5/27/2020]
		return "[" + NIV + ", " + mes + "/" + dia + "/" + ano + "]";
	}

}
