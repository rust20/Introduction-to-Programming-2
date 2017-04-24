import java.util.*;

import Owjek.Map;
import Owjek.Owjek;
import Owjek.Regular;
import Owjek.Sporty;
import Owjek.Exclusive;

public class Main{
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		Map map = new Map();
		String [] input;
		do {
			input = scan.nextLine().split(" ");
			if (input.length == 0) continue;

			String start;
			String target;
			String type;
			Owjek ojek = null;
			String result = null;
			switch(input[0]) {
				case "print":
					map.print();
					break;
				case "go":
					start = input[2];
					target = input[4];
					type = input[7];
					System.out.println(start + " " + target);
					if (map.get(Owjek.x(start), Owjek.y(start)) == '#') {
						System.out.println("input tidak valid, " + start + " bukan jalan");
						break;
					} else if (map.get(Owjek.x(target), Owjek.y(target)) == '#') {
						System.out.println("input tidak valid, " + target + " bukan jalan");
						break;
					}
					switch(type){
						case "Regular":
							ojek = new Regular(start, target);
							result = String.format(
									"[Jarak] %.1f KM\n" +
									"[TipeO] Regular\n" +
									"[2KMpe] %s (+)\n" +
									"[KMSel] %s (+)\n" +
									"[Promo] %s (-)\n" +
									"[Total] %s \n",
									ojek.getDistance() * 0.1,
									currecyFormat(ojek.getKMPertama()),
									currecyFormat(ojek.getKMSelanjutnya()),
									currecyFormat(ojek.getPromo()),
									currecyFormat(ojek.getFinalCost()));
							break;
						case "Sporty":
							ojek = new Sporty(start, target);
							result = String.format(
									"[Jarak] %.1f KM\n" +
									"[TipeO] Sporty\n" +
									"[5KMpe] %s (+)\n" +
									"[KMSel] %s (+)\n" +
									"[Promo] %s (-)\n" +
									"[Prtks] %s (+)\n" +
									"[Total] %s\n",
									ojek.getDistance() * 0.1,
									currecyFormat(ojek.getKMPertama()),
									currecyFormat(ojek.getKMSelanjutnya()),
									currecyFormat(ojek.getPromo()),
									currecyFormat(ojek.getProteksi()),
									currecyFormat(ojek.getFinalCost()));

							break;
						case "Exclusive":
							ojek = new Exclusive(start, target);
							result = String.format(
									"[Jarak] %.1f KM\n" +
									"[TipeO] Exclusive\n" +
									"[Fixed] %s (+)\n" +
									"[KMSel] %s (+)\n" +
									"[Promo] %s (-)\n" +
									"[Prtks] %s (+)\n" +
									"[Total] %s\n",
									ojek.getDistance() * 0.001,
									currecyFormat(ojek.getKMPertama()),
									currecyFormat(ojek.getKMSelanjutnya()),
									currecyFormat(ojek.getPromo()),
									currecyFormat(ojek.getProteksi()),
									currecyFormat(ojek.getFinalCost()));
							break;
						default:
							ojek = null;
							result = "Tipe tidak tersedia";
					}
					break;
				case "exit":
				default:
					System.out.println("Input tidak valid");
			}
			if (ojek != null){
				ojek.getMap().print();
				System.out.println(result);
			}

		} while (!input[0].equals("exit"));
		scan.close();
	}

	private static String currecyFormat(int n){
		return String.format("Rp %d.%03d,00", n/1000, n%1000);
	}
}
