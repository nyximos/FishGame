package gameObject;

import java.io.Serializable;


public class Point implements Serializable {
	private double absis;
	private double ordinat;

	public Point() {
		absis = 0.0;   // ���� abscissa
		ordinat = 0.0; // ���� ordinate
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

	public double findDistance(Point p) {                  // � �� ��ġ �޾Ƽ� �Ÿ����ϱ�
		double tempX = p.getX() - absis;                   // ��X - ��X
		double tempY = p.getY() - ordinat;                 // ��Y - ��Y
		return Math.sqrt(tempX * tempX + tempY * tempY);  // ������ ���
	}

	public boolean equals(Point p) {
		return absis == p.getX() && ordinat == p.getY();
	}
}
