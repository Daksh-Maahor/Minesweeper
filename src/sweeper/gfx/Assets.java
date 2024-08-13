package sweeper.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

import sweeper.field.tiles.Tile;

public class Assets {
	
	private static SpriteSheet sheet;
	
	public static BufferedImage mine_unselected, mine_selected, mine_unselected_flagged, mine_selected_flagged;
	
	public static BufferedImage[] turnedTiles;
	
	public static Font font;
	
	public static void init() {
		sheet = new SpriteSheet(ImageLoader.loadImage("/images/sheet_final.png"));
		
		turnedTiles = new BufferedImage[64];
		
		turnedTiles[Tile.BASE_TILE_ID] = sheet.crop(0, 1);
		turnedTiles[Tile.BOMB_TILE_ID] = sheet.crop(1, 1);
		
		mine_selected = sheet.crop(0, 0);
		mine_unselected = sheet.crop(1, 0);
		
		mine_selected_flagged = sheet.crop(0, 2);
		mine_unselected_flagged = sheet.crop(1, 2);
		
		font = FontLoader.loadFont("res/fonts/slkscr.ttf", 20);
	}

}
