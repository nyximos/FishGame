package gameObject;


public class Snail extends Point {

	private int direction;
	private double speed;

	public Snail() {
		super(0.0, 0.0);	// X랑 Y
		speed = 0.5;
		direction = 1;
	}

	public Snail(Point p) {
		super(p.getX() / 2, p.getY());
		speed = 0.5;
		direction = 1;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void move(Point p, Bool eat) {
		double distance = Math.abs(p.getX() - getX());   // 절대값 반환
		if (direction == 1 && p.getX() < getX()) {
			direction = 0;
		} else if (direction == 0 && p.getX() > getX()) {
			direction = 1;
		}

		if (distance <= speed) {
			setX(p.getX());
		} else if (direction == 1) {
			setX(speed + getX());
		} else {
			setX(getX() - speed);
		}
		if (this.equals(p)) {
			eat.setValue(true);
		} else {
			eat.setValue(false);
		}
	}

	public int takeCoin(LinkedList<Coin> listCoin, Coin coin) {
			    int value = coin.getValue();
			    listCoin.remove(coin);
			    return value;
	  }
}
