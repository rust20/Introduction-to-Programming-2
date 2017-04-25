package Owjek;
public class Regular extends Owjek {

	/**
	 * @param start
	 * @param target
	 */
	public Regular(String start, String target) {
		super(start, target);
		costPerKm = 1000;
		firstNKmCost = 3000;
		N = 2;
		protectionCost = 0;
		promo = 40;
		promoKm = 6;
	}

	@Override
	public String getType() {
		return "Regular";
	}
}
