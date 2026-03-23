🗡️ 2D Java Tile-Based Game(WIP)

A simple 2D tile-based game built using Java and Swing, featuring player movement, collision detection, and a custom tile engine.

📸 Overview

This project is a small game framework created for a YouTube video/series, showcasing how to build a 2D game from scratch in Java.

It is also fully open source, so you can explore, modify, and expand it however you want.

In this project:

The player can move in 4 directions (with diagonal support)
The world is built from a tile map loaded from a file
Collision is handled per tile with custom hitboxes
Camera follows the player smoothly
Basic animation system is implemented
🎨 Assets

All tiles used in this project are hand-drawn.

This includes:

Terrain (grass, mud, straw, etc.)
Walls (brick, 3D walls, curved walls)
Special biome tiles (e.g. hell biome, basic biome) (WIP)

Player sprites are also custom-loaded and animated.

⚙️ Features
🧱 Tile System
Tile-based rendering
Tile flipping (horizontal & vertical)
Custom collision boxes per tile
Efficient rendering (only visible tiles drawn)
🗺️ Map System
Maps are loaded from .txt files
Each number corresponds to a tile ID
Supports large worlds (80x50 tiles)
🧍 Player
Smooth movement (including diagonal normalization)
Direction-based animation
Idle animation system
Collision-aware movement
🎥 Camera
Player-centered camera
Only renders visible part of the world (optimization)
💥 Collision System
Tile-based collision detection
Custom hitboxes for both tiles and player
Axis-separated collision (X and Y handled independently → smoother movement)
🧠 Controls
Key	Action
W	Move Up
S	Move Down
A	Move Left
D	Move Right
🚀 How to Run
Clone the repository:
git clone https://github.com/yourusername/your-repo.git
Open in your IDE (e.g. IntelliJ)
Make sure resources are correctly marked as Resources Root
Run:
Main.java
📂 Project Structure
main/
  ├── Main.java
  ├── GamePanel.java
  ├── KeyHandler.java
  ├── CollisionChecker.java

entity/
  ├── Entity.java 
  └── Player.java

tile/
  ├── Tile.java
  └── TileManager.java

res/
  ├── tiles/
  ├── player/
  ├── objects/
  └── maps/

object/
  └── SuperObject/

  
🛠️ Tech Stack
Java
Swing (Graphics2D)
BufferedImage
Custom game loop (60 FPS)
📈 Future Improvements
Enemies / AI
Combat system
Inventory system
Better animation blending
Sound effects & music
GUI
💡 Notes
This project was created for YouTube content, so the code is structured to be understandable and educational
Rendering uses manual optimization by only drawing tiles within camera range
Tile flipping is handled programmatically (no need for duplicate assets)
Movement uses normalized diagonal speed (0.707) to prevent faster diagonal movement
📜 License

This project is open source – feel free to use, modify, and build upon it.

👤 Author

Created by Roleks
