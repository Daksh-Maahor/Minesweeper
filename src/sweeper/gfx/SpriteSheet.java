package sweeper.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private static final int SIZE = 32;
	
	private BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y) {
		return sheet.getSubimage(x*SIZE, y*SIZE, SIZE, SIZE);
	}

}
