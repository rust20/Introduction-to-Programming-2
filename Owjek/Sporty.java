package Owjek;
public class Sporty extends Owjek{

	/**
	 * @param start
	 * @param target
	 */
	public Sporty(String start, String target) {
		super(start, target);
		costPerKm = 1000;
		firstNKmCost = 3000;
		N = 5;
		protectionCost = 10;
		promo = 40;
		promoKm = 6;
	}

	@Override
	public String getType() {
		return "Sporty";
	}

}
