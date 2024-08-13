package sweeper.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import sweeper.Handler;
import sweeper.gfx.Assets;
import sweeper.gfx.Text;

public class LoseState extends State {

	public LoseState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		if (handler.getKeyManager().getKeyStatus(KeyEvent.VK_R)) {
			((GameState)(handler.getGame().gameState)).reset();
			
			State.setState(handler.getGame().gameState);
		}
	}

	@Override
	public void render(Graphics g) {
		handler.getField().render(g);
		Text.drawString(g, "You Lose", handler.getWidth() / 2, handler.getHeight() / 2, true, Color.RED, Assets.font);
	}

}
