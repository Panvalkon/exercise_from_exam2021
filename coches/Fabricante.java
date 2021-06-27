package coches;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Fabricante
{
	protected Map<String, Set<Coche>> coches;
	private String name;

	public Fabricante(String nombre) throws CochesException
	{
		if (nombre == null || nombre.isEmpty())
		{
			throw new CochesException("El nombre del fabricante no puede estar vacío");
		}

		this.name = nombre;
		coches = new HashMap<>();
	}

	public void anadeModelo(String modelo) throws CochesException
	{
		if (modelo == null || modelo.isEmpty())
		{
			throw new CochesException("El modelo no puede estar vacío");
		}

		if (coches.containsKey(modelo))
		{
			throw new CochesException("El modelo ya existe en la colección.");
		}

		coches.put(modelo, new TreeSet<>());
	}

	public void anadeCoche(Coche coche) throws CochesException
	{
		if (coche == null)
		{
			throw new CochesException("El coche pasado como argumento no puede ser nulo");
		}

		Iterator<String> iter = coches.keySet().iterator();
		boolean encontrada = false;

		while (!encontrada && iter.hasNext())
		{
			if (iter.next().equals(coche.getNIV()))
			{
				encontrada = true;
			}
		}

		if (encontrada)
		{
			throw new CochesException("El coche ya está en la lista");
		}

		Set<Coche> lista = coches.getOrDefault(coche.getModelo(), new TreeSet<>());
		lista.add(coche);

		if (!coches.containsKey(coche.getModelo()))
		{
			coches.put(coche.getModelo(), lista);
		}

	}

	public void leeCoches(String fichero) throws FileNotFoundException, CochesException
	{
		try (Scanner sc = new Scanner(new File(fichero)))
		{
			while (sc.hasNextLine())
			{
				leeCoches(sc);
			}
		}
	}

	public void leeCoches(Scanner sca) throws CochesException
	{
		String line = sca.nextLine();
		String cars[] = line.split("[;]");

		for (String car : cars)
		{
			try (Scanner sc = new Scanner(car))
			{
				sc.useDelimiter("[ ,\\r \\n]+");
				anadeCoche(new Coche(sc.next(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
			}
		}

	}

	@Override
	public String toString()
	{
		StringJoiner modelos = new StringJoiner(", ", "<", ">");

		for (Map.Entry<String, Set<Coche>> modelo : coches.entrySet())
		{
			StringJoiner coches = new StringJoiner(", ", "<", ">");
			for (Coche coche : modelo.getValue())
			{
				coches.add(coche.toString());
			}
			modelos.add(String.format("%1$s: %2$s", modelo.getKey(), coches));
		}
		return String.format("%1$s: %2$s", name, modelos);
	}

	public void escribeCoches(String fichero) throws FileNotFoundException
	{
		try (PrintWriter pw = new PrintWriter(fichero))
		{
			escribeCoches(pw);
		}
	}

	private void escribeCoches(PrintWriter pw)
	{
		for (Set<Coche> c : coches.values())
		{
			for (Coche coche : c)
			{
				pw.append(coche.toString() + "\n");
			}
		}
	}

	public Coche[] llama(Criterio c)
	{
		Coche[] arrCoches = new Coche[coches.size()];
		int pos = 0;

		for (Set<Coche> lista : coches.values())
		{
			for (Coche coche : lista)
			{
				if (c.cumpleCondicion(coche))
				{
					if (arrCoches.length == pos)
					{
						arrCoches = Arrays.copyOf(arrCoches, 2 * arrCoches.length);
					}
					
					arrCoches[pos] = coche;
					pos++;
				}
			}
		}
		return Arrays.copyOf(arrCoches, pos);
	}

}
