package coches;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class FabricantePlus extends Fabricante {

	public FabricantePlus(String name) throws CochesException {
		super(name);
	}

	public Map<Integer, SortedSet<Coche>> cochesPorAno() {
		Map<Integer, SortedSet<Coche>> result = new HashMap<>();
		for (Set<Coche> coche : super.coches.values()) {
			for (Coche c : coche) {
				result.putIfAbsent(c.getAno(), new TreeSet<Coche>(Comparator.reverseOrder()));
				result.get(c.getAno()).add(c);
			}
		}
		return result;
	}

	public Map<Integer, Integer> numeroDeCochesPorAno() {
		Map<Integer, Integer> result = new TreeMap<>();
		for (Set<Coche> coche : super.coches.values()) {
			for (Coche c : coche) {
				int times = result.getOrDefault(c.getAno(), 0);
				result.put(c.getAno(), times + 1);
			}
		}
		return result;
	}
}
