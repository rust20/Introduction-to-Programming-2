package Owjek;

import java.util.*;
public abstract class Owjek{

	protected int distance = -1;
	protected int costPerKm;
	protected int firstNKmCost;
	protected int N;
	protected int protectionCost;
	protected int promo;
	protected int promoKm;

	private int startX;
	private int startY;
	private int targetX;
	private int targetY;
	private Map map = new Map();
	private ArrayDeque<Owjek.Pair> queue = new ArrayDeque<Owjek.Pair>();

	public Owjek(String start, String target){
		startX = x(start);
		startY = y(start);
		targetX = x(target);
		targetY = y(target);
		map.set('*', startX, startY);
		map.set('F', targetX, targetY);
	}
	public static int x(String s){ return (int) (s.charAt(0) - 'A') * 10 + (int) s.charAt(1) - 48; }
	public static int y(String s){ return (int) (s.charAt(2) - 'Q') * 10 + (int) s.charAt(3) - 48; }

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	class Pair{
		public int x, y, a, b;
		public Pair(int x, int y, int a, int b) {
			this.x = x;
			this.y = y;
			this.a = a;
			this.b = b;
		}
	}

	private final int [] dirX = {0, -1, 0, 1};
	private final int [] dirY = {-1, 0, 1, 0};

	private final int blockDistance = 100;
	public int shortestPath(int x, int y, int prevX, int prevY){
		for (int i = 0; i < 4; i++){
			int nextX = x + dirX[i];
			int nextY = y + dirY[i];
			if (map.get(nextX, nextY) == 'F'){
				map.set('.', x, y);
				map.set('.', prevX, prevY);
				return blockDistance;
			}
			if (map.get(nextX, nextY) == ' '){
				queue.add(new Pair(nextX, nextY, x, y));
				map.set('^', nextX, nextY);
			}
		}

		int tmp = 0;
		Pair p = queue.poll();
		tmp = shortestPath(p.x, p.y, p.a, p.b);

		for (int i = 0; i < 4; i++) {
			int nextX = x + dirX[i], nextY = y + dirY[i];
			if (map.get(nextX, nextY) == '^') {
				map.set(' ', nextX, nextY);
			}
		}

		if (map.get(prevX, prevY) == 'S' || map.get(x, y) == 'S'){
			map.set('S', prevX, prevY);
			map.set('S', x, y);
			return tmp + blockDistance;
		} else if (map.get(x, y) == '.' && map.get(prevX, prevY) != '*'){
			map.set('.', prevX, prevY);
			return tmp + blockDistance;
		} else if (map.get(prevX, prevY) != '.'){
			map.set(' ', prevX, prevY);
			return tmp;
		} else {
			return tmp;
		}
	}

	private final int padx = 6;
	private final int pady = 4;
	public int shortestPathWithPathFinding(int x, int y, int prevX, int prevY){
		//System.out.println("\033[2J");
		System.out.println("\033[3;1H");
		//map.print();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 4; i++){
			int nextX = x + dirX[i];
			int nextY = y + dirY[i];
			if (map.get(nextX, nextY) == 'F'){
				map.set('.', x, y);
				System.out.print("\033[" + (x+padx) + ";" + (y+pady) + "H.");
				map.set('.', prevX, prevY);
				System.out.print("\033[" + ( padx + prevX) + ";" +  (pady + prevY) + "H.");
				return blockDistance;
			}
			if (map.get(nextX, nextY) == ' '){
				queue.add(new Pair(nextX, nextY, x, y));
				map.set('^', nextX, nextY);
				System.out.print("\033[" + (padx + nextX) + ";" + (pady + nextY) + "H^");
			}
		}

		int tmp = 0;
		Pair p = queue.poll();
		tmp = shortestPathWithPathFinding(p.x, p.y, p.a, p.b);

		for (int i = 0; i < 4; i++) {
			int nextX = x + dirX[i], nextY = y + dirY[i];
			if (map.get(nextX, nextY) == '^') {
				map.set(' ', nextX, nextY);
				System.out.print("\033[" + (padx + nextX) + ";" + (pady + nextY) + "H ");
			}
		}

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (map.get(prevX, prevY) == 'S' || map.get(x, y) == 'S'){
			map.set('S', prevX, prevY);
			map.set('S', x, y);
			//System.out.print("\033[" + (padx + prevX) + ";" + (pady + prevY) + "H.");
			return tmp + blockDistance;
		} else if (map.get(x, y) == '.' && map.get(prevX, prevY) != '*'){
			map.set('.', prevX, prevY);
			System.out.print("\033[" + (padx + prevX) + ";" + (pady + prevY) + "H.");
			return tmp + blockDistance;
		} else if (map.get(prevX, prevY) != '.'){
			map.set(' ', prevX, prevY);
			System.out.print("\033[" + (padx + prevX) + ";" + (pady + prevY) + "H ");
			return tmp;
		} else {
			return tmp;
		}
	}

	/**
	 * @return the distance
	 */
	//public abstract int getDistance();
	public int getDistance() {
		if (distance == -1)
			distance = shortestPathWithPathFinding(startX, startY, startX, startY);
		return distance ;
	}

	//public abstract int getKMPertama();
	public int getKMPertama(){
		return firstNKmCost;
	}

	//public abstract int getKMSelanjutnya();
	public int getKMSelanjutnya(){
		return (getDistance() - N * 1000) * costPerKm / 1000;
	}

	//public abstract int getCost();
	public int getTotal(){
		if (getDistance() < N*1000){
			return firstNKmCost;
		} else {
			return firstNKmCost + (getDistance() - N * 1000 ) * costPerKm / 1000;
		}
	}

	//public abstract int getPromo();
	public int getPromo() {
		if (getDistance() <= N*1000) {
			return 0;
		} else if (getDistance() > (promoKm + N) * 1000){
			return promoKm * costPerKm * promo / 100;
		} else {
			return getTotal() * promo / 100;
		}
	}

	//public abstract int getProteksi();
	public int getProteksi(){
		return (getTotal() - getPromo()) * protectionCost / 100;
	}

	public int getFinalCost(){
		System.out.println(getTotal());
		System.out.println(getPromo());
		System.out.println(getProteksi());

		return getTotal() - getPromo() + getProteksi();
	}

	public abstract String getType();
}
