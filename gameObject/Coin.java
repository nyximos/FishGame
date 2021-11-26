package gameObject;

import static gameObject.Constants.COIN_BASE_VALUE;

public class Coin extends Item {

	private int baseVal;
	private int value;

	public Coin() {
		super(-1, -1);
		baseVal = COIN_BASE_VALUE;
		value = 0;
	}
	
	public Coin(Point p, int level) {
		super(p.getX(), p.getY());
		baseVal = COIN_BASE_VALUE;
		value = baseVal * level;
	}

	public void setValue(int val) {
		value = val;
	}

	public int getValue() {
		return value;
	}

	public int getBaseVal() {
		return baseVal;
	}
}
