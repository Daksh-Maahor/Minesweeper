package sweeper.field.tiles;

public enum TileType {
	
	BASE_TILE {
		@Override
		public int getID() {
			return Tile.BASE_TILE_ID;
		}
	},
	BOMB_TILE {
		@Override
		public int getID() {
			return Tile.BOMB_TILE_ID;
		}
	};
	
	public abstract int getID();

}
