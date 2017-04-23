package Owjek;
public class Regular extends Owjek {

	private int distance = -1;
	private final int costPerKm = 1000;
	private final int firstNKmCost = 3000;
	private final int N = 2;
	private final int protectionCost = 0;
	private final int promo = 60;
	private final int promoKm = 6;

	/**
	 * @param start
	 * @param target
	 */
	public Regular(String start, String target) {
		super(start, target);
		System.out.print("ini distancenya ");
		System.out.println(distance);
	}

	@Override
	public int getCost() {
		if (distance == -1)
			distance = shortestPath(startX, startY, startX, startY);
		if (distance < N*1000){
			return firstNKmCost;
		} else {
			return firstNKmCost + (distance - N * 1000 ) * costPerKm / 1000 ;
		}
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		if (distance == -1)
			distance = shortestPath(startX, startY, startX, startY);
		return distance ;
	}

	@Override
	public int getPromo() {
		if (distance <= N*1000) {
			return 0;
		} else if (distance > promoKm * 1000){
			return 2400;
		} else {
			return (distance - N * 1000) * promo / 100;
		}
	}

	public int protectionCost(){
		return firstNKmCost * protectionCost;
	}


}
