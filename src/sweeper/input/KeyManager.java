package sweeper.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick() {
		for (int i=0; i<keys.length; i++) {
			if (keys[i])
				keys[i] = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public boolean getKeyStatus(int keyCode) {
		if (keyCode < 0 || keyCode >= keys.length)
			return false;
		return keys[keyCode];
	}

}
