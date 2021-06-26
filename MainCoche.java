import coches.Coche;
import coches.CochesException;

public class MainCoche {

	public static void main(String[] args) {
		try {
			Coche c1 = new Coche("León",  "WP0ZZZ99ZTS392124", 5, 27, 2020);
			Coche c2 = new Coche("Arona", "WP0ZZZ99ZtS392124", 5, 27, 2020);
			Coche c3 = new Coche("Ateca", "WP1ZZZ99ZTS392124", 1, 30, 2019);
			System.out.printf("%s %ses igual a %s\n", c1, c1.equals(c2) ? "" : "no ", c2);
			System.out.printf("%s %ses igual a %s\n", c1, c1.equals(c3) ? "" : "no ", c3);
			new Coche("Ateca", "WPOZZZ99ZTS392124", 1, 31, 2019);
		} catch (CochesException e) {
			e.printStackTrace();
		}
	}

}

/* 
Expected output:

[WP0ZZZ99ZTS392124, 5/27/2020] es igual a [WP0ZZZ99ZTS392124, 5/27/2020]
[WP0ZZZ99ZTS392124, 5/27/2020] no es igual a [WP1ZZZ99ZTS392124, 1/30/2019]
coches.CochesException: NIV erroneo
	at coches.Coche.<init>(Coche.java:29)
	at MainCoche.main(MainCoche.java:13)
*/