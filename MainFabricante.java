
import java.io.FileNotFoundException;
import java.util.Arrays;
import coches.CochesException;
import coches.Fabricante;
import coches.MismoAno;
import coches.MismoModelo;

public class MainFabricante {

	public static void main(String[] args) {
		try {
			Fabricante m = new Fabricante("Seat");
			m.leeCoches("input.txt");
			System.out.println(m);
			System.out.println("llamada 2020: " + Arrays.toString(m.llama(new MismoAno(2020))));
			System.out.println("llamada Arona: " + Arrays.toString(m.llama(new MismoModelo("Arona"))));
			m.escribeCoches("output.txt");
		} catch (CochesException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

/*
Expected output:

Seat: <León: <[5GZCZ43D13S812715, 5/27/2020]>, Arona: <[KLATF08Y1VB363636, 4/15/2020], [SGZCZ43D13S812715, 7/1/2021]>, Ateca: <[WP0ZZZ99ZTS392124, 1/30/2019]>>
llamada 2020: [[5GZCZ43D13S812715, 5/27/2020], [KLATF08Y1VB363636, 4/15/2020]]
llamada Arona: [[KLATF08Y1VB363636, 4/15/2020], [SGZCZ43D13S812715, 7/1/2021]]
*/