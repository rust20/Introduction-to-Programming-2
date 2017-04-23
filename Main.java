import java.util.*;

import Owjek.Map;
import Owjek.Owjek;
import Owjek.Regular;

public class Main{
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		Map map = new Map();
		String [] input;
		do {
			input = scan.nextLine().split(" ");
			String start = "";
			String target = "";
			String type = "";
			if (input.length == 0) continue;
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
					Owjek ojek;
					switch(type){
						case "Regular":
							ojek = new Regular(start, target); break;
							/*
							 * [Jarak] 11,6 KM
							 * [TipeO] Regular
							 * [2KMPe] Rp 3.000,00 (+)
							 * [KMSel] Rp 9.600,00 (+)
							 * [Promo] Rp 2.400,00 (-)
							 * [TOTAL] Rp 10.200,00*/
						case "Sporty":
						case "Exclusive":
						default:
							ojek = new Regular("A1A1", "A1A1");
							System.out.println("Tipe tidak tersedia");
					}

					int cost = ojek.getCost();
					int promo = ojek.getPromo();
					ojek.getMap().print();

					System.out.printf();
					System.out.println(cost);
					System.out.println(promo);
					System.out.println("========================================================");
					System.out.println(ojek.getDistance());

				default:
					System.out.println("Input tidak valid");
			}
		} while (!input[0].equals("exit"));
		scan.close();
	}
}
