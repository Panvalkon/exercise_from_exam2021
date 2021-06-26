
import java.io.FileNotFoundException;
import java.util.Arrays;
import coches.CochesException;
import coches.Fabricante;
import coches.FabricantePlus;
import coches.MismoAno;
import coches.MismoModelo;

public class MainFabricantePlus {

	public static void main(String[] args) {
		try {
			FabricantePlus m = new FabricantePlus("Seat");
			m.leeCoches("input.txt");
			System.out.println(m.cochesPorAno());
			System.out.println(m.numeroDeCochesPorAno());
		} catch (CochesException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

/*
Expected output:

{2019=[[WP0ZZZ99ZTS392124, 1/30/2019]], 2020=[[5GZCZ43D13S812715, 5/27/2020], [KLATF08Y1VB363636, 4/15/2020]], 2021=[[SGZCZ43D13S812715, 7/1/2021]]}
{2019=1, 2020=2, 2021=1}
*/