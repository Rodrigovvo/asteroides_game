package application;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Player extends GameObject {
	
	public boolean isShooting = false;

	public Player() {      
		super(getShape());
		super.setVelocity(new Point2D(-0.1,0));
	}
	
	
	  private static Polygon getShape() {
	        Polygon ship = new Polygon();
	        ship.getPoints().addAll(new Double[]{
	                0.0, 0.0,
	               - 20.0, 10.0,
	                -10.0, 20.0
	        });

	        ship.setFill(Color.BLUE);
	        return ship;
	    }
	
	public void action(Scene scene) {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()  {
			
			

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					Player.this.rotateLeft();
				}else if(event.getCode() == KeyCode.RIGHT) {
					Player.this.rotateRight();
				}else if(event.getCode() == KeyCode.SPACE) {
					isShooting = true;
				}
			}
		});

	}
	
	 public Bullet shot() {
		Bullet bullet = new Bullet();
		bullet.setVelocity(this.getVelocity().normalize().multiply(1.2));
		return bullet;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}
	

	 
	 


}
