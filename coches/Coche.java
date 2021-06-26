package coches;

public class Coche implements Comparable<Coche> {
	private final String modelo;
	private final String niv;
	private final int ano;
	private final int mes;
	private final int dia;

	public Coche(String modelo, String niv, int mes, int dia, int ano) throws CochesException {
		if (niv == null || niv.length() != 17) {
			throw new CochesException("Invalid niv");
		}
		String newNiv = niv.toUpperCase();
		if (newNiv.contains("O") || newNiv.contains("I") || newNiv.contains("Q")) {
			throw new CochesException("Used illegal characters in niv number");
		}
		this.niv = newNiv;
		if (modelo == null || modelo == "") {
			throw new CochesException("Invalid modelo");
		}
		this.modelo = modelo;
		this.ano = ano;
		if (mes < 1 || mes > 12) {
			throw new CochesException("Mes out of range");
		}
		this.mes = mes;
		if (dia < 1 || dia > 31) {
			throw new CochesException("Dia out of range");
		}
		this.dia = dia;
	}

	public String getModelo() {
		return modelo;
	}

	public String getNiv() {
		return niv;
	}

	public int getAno() {
		return ano;
	}

	public int getMes() {
		return mes;
	}

	public int getDia() {
		return dia;
	}

	@Override
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Coche) {
			Coche c = (Coche) o;
			res = this.niv.equals(c.niv) && this.ano == c.ano && this.mes == c.mes && this.dia == c.dia;
		}
		return res;
	}

	@Override
	public int hashCode() {
		int res = 0;
		res = this.niv.hashCode() + this.ano * 3 + this.mes * 5 + this.dia * 7;
		return res;
	}

	@Override
	public int compareTo(Coche o) {
		int res = 0;
		res = Integer.compare(this.ano, o.ano);
		if (res == 0) {
			res = Integer.compare(this.mes, o.mes);
			if (res == 0) {
				res = Integer.compare(this.dia, o.dia);
			}
		}
		return res;
	}

	@Override
	public String toString() {
		return String.format("[%s, %d/%d/%d]", this.niv, this.mes, this.dia, this.ano);
	}
}
