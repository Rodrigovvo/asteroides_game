package application;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class GameObject {
	//
	private Node view;
	private Point2D velocity = new Point2D(0.1,0);
	
	private boolean alive = true;
	
	public GameObject(Node view) {
		this.view = view;
	}
	
	public void update() {
		view.setTranslateX(view.getTranslateX() + velocity.getX());
		view.setTranslateY(view.getTranslateY() + velocity.getY());
		}
	
	public Point2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Point2D velocity) {
		this.velocity = velocity;
	}
	
	
	public Node getView() {
		return view;
	}

	public boolean isAlive() {
		return alive;

	}
	
	public boolean isDead() {
		return !alive;

	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Double getRotate() {
		return view.getRotate();
		
	}	
	
	public void rotateRight() {
        view.setRotate(view.getRotate() + 10);
        setVelocity(new Point2D((Math.cos(Math.toRadians(getRotate())) / 10), 
        		(Math.sin(Math.toRadians(getRotate()))) /10));
		
	}
	
	public void rotateLeft() {
        view.setRotate(view.getRotate() - 10);
        setVelocity(new Point2D((Math.cos(Math.toRadians(getRotate())) /10),
        		( Math.sin(Math.toRadians(getRotate()))) /10));
		
	}
	
	public boolean isColiding( GameObject other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
	}
	

}
