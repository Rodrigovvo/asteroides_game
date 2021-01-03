package application;
	
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	
	private List<GameObject> bullets = new ArrayList<>();
	private List<GameObject> enemies = new ArrayList<>();
	
	private Player player;
	
	
	@Override
	public void start(Stage primaryStage)  {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			// Itens do Jogo
			player = new Player();
			player.setVelocity(new Point2D(0,0));
			addGameObject(root, player, 300,  300);

			// Movimentar o player
			player.action(scene);
		
			
			AnimationTimer timer = new AnimationTimer() {
				
				@Override
				public void handle(long arg0) {
					onUpdate(root);
				}
			};
			
			timer.start();
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void onUpdate(BorderPane root) {
		
		if(player.isShooting) {
			player.setShooting(false);
			addBullet(root, player.shot(), player.getView().getTranslateX(), player.getView().getTranslateY());
			
		}
		// 
		double timeRespawn = 0.01; 
		
		/*	- Elemento do Jogo - Colisão
		 * Se as balas colidirem com os enimigos no mesmo frame a situação de ambos serão
		 *  identificados como mortos 'isAlive(false)'.
		 */
		for (GameObject bullet : bullets) {
			for (GameObject enemy : enemies){
				if (bullet.isColiding(enemy)) {
					bullet.setAlive(false);
					enemy.setAlive(false);
					
                    root.getChildren().removeAll(bullet.getView(), enemy.getView());
				}
			}
		}
        bullets.removeIf(GameObject::isDead);
        enemies.removeIf(GameObject::isDead);

        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);

        player.update();
        
        if(Math.random() < timeRespawn) {
        	addEnemy(root, new Enemy(), Math.random() * root.getWidth(), Math.random() * root.getHeight());
        }
        

	}

	public void addBullet(BorderPane root, GameObject bullet, double x, double y) {
		bullets.add(bullet);
		addGameObject(root, bullet, x, y);
	}
	
	private void addEnemy(BorderPane root, GameObject enemy, double x, double y) {
		enemies.add(enemy);
		addGameObject(root, enemy, x, y);
	}
	
	private void addGameObject(BorderPane root, GameObject object, double x, double y) {
		object.getView().setTranslateX(x);
		object.getView().setTranslateY(y);
		root.getChildren().add(object.getView());
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
