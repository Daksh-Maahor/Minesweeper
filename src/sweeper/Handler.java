package sweeper;

import sweeper.field.Field;
import sweeper.input.KeyManager;
import sweeper.input.MouseManager;

public class Handler {
	
	private Game game;
	private Field field;

	public Handler(Game game) {
		this.game = game;
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public Game getGame() {
		return game;
	}

}
