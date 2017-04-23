package Owjek;

import java.util.*;
public abstract class Owjek{

	protected int startX;
	protected int startY;
	protected int targetX;
	protected int targetY;
	private Map map = new Map();
	private ArrayDeque<Owjek.Pair> queue = new ArrayDeque<Owjek.Pair>();

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

	public Owjek(String start, String target){
		startX = x(start);
		startY = y(start);
		targetX = x(target);
		targetY = y(target);
		map.set('S', startX, startY);
		map.set('F', targetX, targetY);
		for(int[] ha : minimap)
			Arrays.fill(ha, 99);
		minimap[startX][startY] = 0;
	}

	private final iir
		nt [] dirX = {0, -1, 0, 1};
	private final iint [] dirY = {-1, 0, 1, 0};

	public int shortestPath(int x, int y, int prevX, int prevY){
		for (int i = 0; i < 4; i++){
			int nextX = x + dirX[i];
			int nextY = y + dirY[i];
			if (map.get(nextX, nextY) == 'F'){
				map.set('.', x, y);
				map.set('.', prevX, prevY);
				return 1;
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
			if (map.get(nextX, nextY) == '^')
				map.set(' ', nextX, nextY);
		}

		if (map.get(x, y) == '.' && map.get(prevX, prevY) != 'S'){
			map.set('.', prevX, prevY);
			return tmp + 1;
		} else if (map.get(prevX, prevY) != '.'){
			map.set(' ', prevX, prevY);
			return tmp;
		} else {
			return tmp;
		}
	}

	public abstract int getCost();
	public abstract int getPromo();
	public abstract int getDistance();
}
