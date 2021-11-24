package gameObject;

import java.io.Serializable;


public class Point implements Serializable {
	private double absis;
	private double ordinat;

	public Point() {
		absis = 0.0;   // 가로 abscissa
		ordinat = 0.0; // 세로 ordinate
	}

	public Point(double absis, double ordinat) {
		this.absis = absis;
		this.ordinat = ordinat;
	}

	public double getX() {
		return absis;
	}

	public double getY() {
		return ordinat;
	}

	public void setX(double absis) {
		this.absis = absis;
	}

	public void setY(double ordinat) {
		this.ordinat = ordinat;
	}

	public void setLocation(double absis, double ordinat) {
		this.absis = absis;
		this.ordinat = ordinat;
	}

	public double findDistance(Point p) {                  // 어떤 놈 위치 받아서 거리구하기
		double tempX = p.getX() - absis;                   // 놈X - 내X
		double tempY = p.getY() - ordinat;                 // 놈Y - 내Y
		return Math.sqrt(tempX * tempX + tempY * tempY);  // 제곱근 계산
	}

	public boolean equals(Point p) {
		return absis == p.getX() && ordinat == p.getY();
	}
}
