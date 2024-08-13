package sweeper.field.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import sweeper.Handler;
import sweeper.gfx.Assets;
import sweeper.gfx.Text;

public class Tile {

	// Tiles

	public static final int BASE_TILE_ID = 0, BOMB_TILE_ID = 1;

	// class

	public static final int TILE_SIZE = 32;

	private boolean turned, flagged;
	private BufferedImage imageTurned;

	private TileType tileType;

	private int tileNum;

	private static final int[] offsets = { -1, 1 };

	public Tile(TileType type) {
		tileType = type;
		turned = false;
		flagged = false;
		this.imageTurned = Assets.turnedTiles[type.getID()];

		tileNum = 0;
	}

	public void render(Graphics g, int x, int y, boolean isSelected, boolean showNumber) {
		if (turned) {
			g.drawImage(imageTurned, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
			if (tileNum != 0 && showNumber)
				Text.drawString(g, "" + tileNum + "", TILE_SIZE * x + TILE_SIZE / 2, TILE_SIZE * y + TILE_SIZE / 2,
						true, Color.BLACK, Assets.font);
		} else {
			if (isSelected)
				g.drawImage(flagged ? Assets.mine_selected_flagged : Assets.mine_selected, x * TILE_SIZE, y * TILE_SIZE,
						TILE_SIZE, TILE_SIZE, null);
			else
				g.drawImage(flagged ? Assets.mine_unselected_flagged : Assets.mine_unselected, x * TILE_SIZE,
						y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
		}
	}

	public boolean isTurned() {
		return turned;
	}

	public void onlyturn(Handler handler, int x, int y) {
		if (flagged)
			return;

		if (turned) {
			return;
		}

		this.turned = true;

		if (tileType == TileType.BOMB_TILE) {
			handler.getField().gameOver();
		} else {
			int numBombs = 0;

			for (int dx = -1; dx <= 1; dx++) {
				for (int dy = -1; dy <= 1; dy++) {
					if (x + dx < 0 || x + dx >= handler.getField().getWidth())
						continue;

					if (y + dy < 0 || y + dy >= handler.getField().getHeight())
						continue;

					if (handler.getField().getTiles()[x + dx][y + dy].tileType == TileType.BOMB_TILE)
						numBombs++;
				}
			}

			tileNum = numBombs;
		}
	}

	public void turn(Handler handler, int x, int y) {
		if (flagged)
			return;

		if (!turned) {
			this.turned = true;

			if (tileType == TileType.BOMB_TILE) {
				handler.getField().gameOver();
			} else {
				int numBombs = 0;
				for (int dx : offsets) {
					if (x + dx < 0 || x + dx >= handler.getField().getWidth())
						continue;

					if (!(handler.getField().getTiles()[x + dx][y].tileType == TileType.BOMB_TILE)) {
						int c = (int) Math.floor(Math.random() * 100);

						if (c <= 35) {
							if (handler.getField().getTiles()[x + dx][y].tileType != TileType.BOMB_TILE
									&& !handler.getField().getTiles()[x + dx][y].turned)
								handler.getField().getTiles()[x + dx][y].turn(handler, x + dx, y);
						}
					}
				}

				for (int dy : offsets) {
					if (y + dy < 0 || y + dy >= handler.getField().getHeight())
						continue;

					if (!(handler.getField().getTiles()[x][y + dy].tileType == TileType.BOMB_TILE)) {
						int c = (int) Math.floor(Math.random() * 2);

						if (c == 1) {
							if (handler.getField().getTiles()[x][y + dy].tileType != TileType.BOMB_TILE
									&& !handler.getField().getTiles()[x][y + dy].turned)
								handler.getField().getTiles()[x][y + dy].turn(handler, x, y + dy);
						}
					}
				}

				for (int dx = -1; dx <= 1; dx++) {
					for (int dy = -1; dy <= 1; dy++) {
						if (x + dx < 0 || x + dx >= handler.getField().getWidth())
							continue;

						if (y + dy < 0 || y + dy >= handler.getField().getHeight())
							continue;

						if (handler.getField().getTiles()[x + dx][y + dy].tileType == TileType.BOMB_TILE)
							numBombs++;
					}
				}

				tileNum = numBombs;
			}
		} else {
			if (!(handler.getField().getTiles_show_num(x, y))) {
				return;
			} else {

				for (int dx = -1; dx <= 1; dx++) {
					for (int dy = -1; dy <= 1; dy++) {
						if (x + dx < 0 || x + dx >= handler.getField().getWidth())
							continue;

						if (y + dy < 0 || y + dy >= handler.getField().getHeight())
							continue;

						handler.getField().getTiles()[x + dx][y + dy].onlyturn(handler, x + dx, y + dy);
					}
				}
			}
		}
	}

	public void flag(Handler handler, int x, int y) {
		if (!turned)
			flagged = !flagged;
	}

	public void setTurned(boolean turned) {
		this.turned = turned;
	}

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}

}
