package gameObject;

import static gameObject.Constants.*;


public class Piranha extends Fish {

	  private int price;

	  public Piranha(double time, double x, double y) {
	    super(time, x, y);
	    setSpeed(getSpeed() + 0.000005);
	    price = PIRANHA_PRICE;
	  }

	  @Override
	  public void moveRandom(double time, Point max) {
	    double moveX;
	    double moveY;
	    double newRad;

	    if (isTimeToChangeDirection(time)) {
	      newRad = createDirection(getX(), getY(), max);
	      setDirectionRad(newRad);

	      lastChangeDir = time;
	      moveX = speed * Math.cos(newRad);
	      moveY = speed * Math.sin(newRad);
	      if (moveX > 0) {
	        setDirection(1);
	      } else {
	        setDirection(0);
	      }

	    } else {
	      moveX = speed * Math.cos(directionRad);
	      moveY = speed * Math.sin(directionRad);
	      if (!isInsideAquarium((getX() + moveX), (getY() + moveY), max)) {
	        newRad = createDirection(getX(), getY(), max);
	        setDirectionRad(newRad);

	        lastChangeDir = time;
	        moveX = speed * Math.cos(newRad);
	        moveY = speed * Math.sin(newRad);
	        if (moveX > 0) {
	          setDirection(1);
	        } else {
	          setDirection(0);
	        }
	      }
	    }

	    setX(getX() + moveX);
	    setY(getY() + moveY);
	  }

	  public void moveToEat(LinkedList<Guppy> listguppy, Guppy guppy, double time, Bool eat) {
	    if (guppy.findDistance(new Point(getX(), getY())) <= speed) {
	      setX(guppy.getX());
	      setY(guppy.getY());
	      lastEaten = time;
	      listguppy.remove(guppy);
	      eat.setValue(true);
	    } else {
	      double rad = Math.atan2((guppy.getY() - getY()), (guppy.getX() - getX()));
	      double moveX = speed * Math.cos(rad);
	      double moveY = speed * Math.sin(rad);

	      setX(getX() + moveX);
	      setY(getY() + moveY);
	      lastChangeDir = time;
	      directionRad = rad;
	      if (moveX > 0) {
	        direction = 1;
	      } else {
	        direction = 0;
	      }
	    }
	  }

	}