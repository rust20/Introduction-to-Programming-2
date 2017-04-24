package Owjek;
public class Exclusive extends Owjek {

	/**
	 * @param start
	 * @param target
	 */
	public Exclusive(String start, String target) {
		super(start, target);
		costPerKm = 5000;
		firstNKmCost = 10000;
		N = 0;
		protectionCost = 5;
		promo = 50;
		promoKm = 99999999;
	}

	@Override
	public String getType() {
		return "Exclusive";
	}

}
