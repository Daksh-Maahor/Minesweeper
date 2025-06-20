# Minesweeper

A classic Minesweeper game implemented in Java with a custom graphical interface, keyboard and mouse controls, and retro pixel-art assets.

## Features
- Classic Minesweeper gameplay
- Custom pixel-art graphics
- Keyboard and mouse controls
- Game states: play and lose
- Randomized field generation
- Flagging and revealing tiles
- Custom font for retro aesthetics

## Requirements
- Java 8 or higher
- Swing/AWT (standard in JDK)

## How to Run
1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd Minesweeper
   ```
2. **Compile the source code:**
   ```bash
   find src -name '*.java' | xargs javac -d bin
   ```
3. **Copy resources to the classpath:**
   ```bash
   cp -r res/images bin/
   cp -r res/fonts bin/
   ```
4. **Run the game:**
   ```bash
   java -cp bin sweeper.Launcher
   ```

> Ensure the `images` and `fonts` folders are inside the `bin/` directory before running, as the game loads assets from there.

## Controls
- **Left Click:** Reveal a tile
- **Right Click:** Flag or unflag a tile
- **Keyboard:** (If implemented) for additional controls

## Project Structure
```
Minesweeper/
  src/sweeper/         # Java source code
    display/           # Window and rendering
    field/             # Game field and tile logic
    field/tiles/       # Tile types and logic
    gfx/               # Graphics and asset loading
    input/             # Keyboard and mouse input
    states/            # Game and lose states
    Launcher.java      # Main entry point
    Game.java          # Main game loop
  res/
    fonts/             # Custom font and license
    images/            # Sprite sheets
  bin/                 # Compiled classes
```

## Assets & Credits
- **Font:** [Silkscreen by Jason Kottke](https://kottke.org/plus/type/silkscreen/) ([License](res/fonts/Kottke%20Silkscreen%20License.txt))
- **Graphics:** Custom sprite sheets in `res/images/`

## License
This project is for educational and personal use. Font and some assets are under their respective licenses (see `res/fonts/Kottke Silkscreen License.txt`).