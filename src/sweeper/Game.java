package sweeper;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import sweeper.display.Display;
import sweeper.gfx.Assets;
import sweeper.input.KeyManager;
import sweeper.input.MouseManager;
import sweeper.states.GameState;
import sweeper.states.LoseState;
import sweeper.states.State;

public class Game implements Runnable {

	private String title;
	private int width, height;

	private Display display;

	private Thread thread;
	private boolean running = false;
	
	private Graphics g;
	private BufferStrategy bs;
	
	private Handler handler;
	
	public State gameState;
	public State loseState;
	
	private MouseManager mouseManager;
	private KeyManager keyManager;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	private void init() {
		display = new Display(title, width, height);
		
		Assets.init();
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		loseState = new LoseState(handler);
		
		mouseManager = new MouseManager();
		keyManager = new KeyManager();
		
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addKeyListener(keyManager);
		
		State.setState(gameState);
	}
	
	private void tick() {
		if (State.getState() != null)
			State.getState().tick();
		
		mouseManager.tick();
		keyManager.tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		
		if (State.getState() != null)
			State.getState().render(g);
		
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		//long timer = 0;
		//int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			//timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				//ticks++;
				delta--;
			}

//			if (timer >= 1000000000) {
////				System.out.println("Ticks and Frames: " + ticks);
//				ticks = 0;
//				timer = 0;
//			}
		}

		stop();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public void setMouseManager(MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public void setKeyManager(KeyManager keyManager) {
		this.keyManager = keyManager;
	}

}
