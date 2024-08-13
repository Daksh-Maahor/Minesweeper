package sweeper.field;

import java.awt.Graphics;

import sweeper.Handler;
import sweeper.field.tiles.Tile;
import sweeper.field.tiles.TileType;
import sweeper.states.State;

public class Field {
	
	private Handler handler;
	
	private int width, height;
	private Tile[][] tiles;
	private boolean[][] tiles_show_num;
	
	private int currentTileX, currentTileY;
	
	//private long i = 0;

	public Field(Handler handler) {
		this.handler = handler;
		
		width = handler.getWidth() / Tile.TILE_SIZE;
		height = handler.getHeight() / Tile.TILE_SIZE;
		
		tiles = new Tile[width][height];
		tiles_show_num = new boolean[width][height];
		
		init();
	}
	
	private void init() {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				int i = (int) Math.floor(Math.random() * 10);
				
				if (i == 9 || i == 8)
					tiles[x][y] = new Tile(TileType.BOMB_TILE);
				else
					tiles[x][y] = new Tile(TileType.BASE_TILE);
				
				i = (int) Math.floor(Math.random() * 10);
				
				if (i >= 7)
					tiles_show_num[x][y] = false;
				else
					tiles_show_num[x][y] = true;
			}
		}
	}
	
	public void tick() {
//		if (i == 0) {
//			for (int x=0; x<width; x++) {
//				for (int y=0; y<height; y++) {
//					tiles[x][y].turn(handler, x, y);
//				}
//			}
//		}
//		
//		i++;
		
		currentTileX = handler.getMouseManager().getMouseX() / Tile.TILE_SIZE;
		currentTileY = handler.getMouseManager().getMouseY() / Tile.TILE_SIZE;
		
		if (handler.getMouseManager().isLeftReleased())
			if (currentTileX < width && currentTileY < height)
				tiles[currentTileX][currentTileY].turn(handler, currentTileX, currentTileY);
		
		if (handler.getMouseManager().isRightReleased())
			if (currentTileX < width && currentTileY < height)
				tiles[currentTileX][currentTileY].flag(handler, currentTileX, currentTileY);
	}
	
	public void render(Graphics g) {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				tiles[x][y].render(g, x, y, (currentTileX == x && currentTileY == y), tiles_show_num[x][y]);
			}
		}
	}
	
	public void gameOver() {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (tiles[x][y].getTileType() == TileType.BOMB_TILE)
					tiles[x][y].setTurned(true);
			}
		}
		
		State.setState(handler.getGame().loseState);
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

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public boolean getTiles_show_num(int x, int y) {
		return tiles_show_num[x][y];
	}

}
