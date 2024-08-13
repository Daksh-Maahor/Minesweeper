package sweeper.states;

import java.awt.Graphics;

import sweeper.Handler;
import sweeper.field.Field;

public class GameState extends State {
	
	private Field field;

	public GameState(Handler handler) {
		super(handler);
		
		reset();
	}
	
	public void reset() {
		field = new Field(handler);
		handler.setField(field);
	}

	@Override
	public void tick() {
		field.tick();
	}

	@Override
	public void render(Graphics g) {
		field.render(g);
	}

}
