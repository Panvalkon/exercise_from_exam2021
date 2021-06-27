package coches;

import java.util.*;

public class FabricantePlus extends Fabricante
{
	public FabricantePlus(String nombre) throws CochesException
	{
		super(nombre);
	}

	public Map<Integer, SortedSet<Coche>> cochesPorAno()
	{
		Map<Integer, SortedSet<Coche>> res = new HashMap<>();

		for (Set<Coche> coche : super.coches.values())
		{
			for (Coche c : coche)
			{
				res.putIfAbsent(c.getAno(), new TreeSet<Coche>());
				res.get(c.getAno()).add(c);
			}
		}

		return res;
	}

	public Map<Integer, Integer> numeroDeCochesPorAno()
	{
		Map<Integer, Integer> res = new TreeMap<>();
		for (Set<Coche> coche : super.coches.values())
		{
			for (Coche c : coche)
			{
				int times = res.getOrDefault(c.getAno(), 0);
				res.put(c.getAno(), times + 1);
			}
		}
		return res;
	}

}
