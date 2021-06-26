package coches;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class Fabricante {

	protected Map<String, Set<Coche>> coches;
	private String name;

	public Fabricante(String name) throws CochesException {
		if (name == null || name == "") {
			throw new CochesException("Nobre de fabricante vacío");
		}
		this.name = name;
		this.coches = new HashMap<String,Set<Coche>>();
	}
	
	public void anadeModelo(String modelo) throws CochesException {
		if (modelo == null || modelo == "") {
			throw new CochesException("Invalid modelo");
		}
		if (this.coches.get(modelo) != null) {
			throw new CochesException("Dicho modelo ya exite");
		}
		this.coches.put(modelo, new TreeSet<Coche>());
	}
	
	public void anadeCoche(Coche coche) throws CochesException {
		if (coche == null) {
			throw new CochesException("Coche no válido");
		}
		for (Set<Coche> mod : coches.values()) {
			for (Coche c : mod) {
				if (c.getNiv().equals(coche.getNiv())) {
					throw new CochesException("Coche con este Niv ya está en la base de datos");
				}
			}
		}
		if (! this.coches.keySet().contains(coche.getModelo())) {
			this.anadeModelo(coche.getModelo());
		}
		this.coches.get(coche.getModelo()).add(coche);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Set<Coche>> mod : this.coches.entrySet()) {
			StringJoiner sj = new StringJoiner(", ", "<", ">");
			for (Coche c : mod.getValue()) {
				sj.add(c.toString());
			}
			sb.append(mod.getKey() + ": " + sj.toString());
		}
		return this.name + ": <" + sb + ">";
	}
	
	public void  leeCoches(String filename) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File (filename))){
			while (sc.hasNextLine()) {
				leeCoches(sc);
			}
		}
	}

	private void leeCoches(Scanner sc) {		
		String line = sc.nextLine();
		String cars[] = line.split("[;]");
		for (String car : cars) {
			try (Scanner coche = new Scanner(car)){
				//coche.useLocale(Locale.ENGLISH);
				coche.useDelimiter("\\s*[ ,]\\s*");
				String mod = coche.next();
				String niv = coche.next();
				int mes = Integer.parseInt(coche.next());
				int dia = Integer.parseInt(coche.next());
				int ano = Integer.parseInt(coche.next());
				Coche c = new Coche(mod, niv, mes, dia, ano);
				this.anadeCoche(c);
			} catch(NoSuchElementException | NumberFormatException | CochesException e) {
				// Skip in case of errors;
			}
		}
	}
	
	 public void escribeCoches(String filename) throws FileNotFoundException {
		 try (PrintWriter pw = new PrintWriter(filename)){
			 escribeCoches(pw);
		 }
	 }

	private void escribeCoches(PrintWriter pw) {
		for (Set<Coche> c : this.coches.values()) {
			for(Coche coche : c) {
				pw.append(coche.toString() + "\n");
			}
		}
	}
	
	public Coche[] llama(Criterio crit) {
		Coche[] res = new Coche[10];
		int pos = 0;
		for(Set<Coche> c : this.coches.values()) {
			for(Coche coche : c) {
				if (crit.cumpleCondicion(coche)) {
					if (res.length == pos) {
						res = Arrays.copyOf(res, res.length + 10);
					}
					res[pos] = coche;
					pos++;
				}
			}
		}
		res = Arrays.copyOf(res, pos);
		return res;
	}
}
