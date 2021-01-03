package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Enemy extends GameObject {

	public Enemy() {
		super(new Circle(15, 15, 15 , Color.DARKRED));
	}
	
}
