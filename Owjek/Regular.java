package Owjek;
public class Regular extends Owjek {

	/**
	 * @param start
	 * @param target
	 */
	public Regular(String start, String target) {
		super(start, target);
		costPerKm = 3000;
		firstNKmCost = 20000;
		N = 5;
		protectionCost = 10;
		promo = 60;
		promoKm = 8;
	}

	@Override
	public String getType() {
		return "Regular";
	}
}
