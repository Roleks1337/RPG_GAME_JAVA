# 🗡️ 2D Java Tile-Based Game (WIP)

> A lightweight, custom-built 2D tile-based game engine and framework developed from scratch using Java and Swing. 

This project serves as a foundational game framework created for an educational YouTube series, showcasing how to build a 2D game without external game engines. It is fully open-source—feel free to explore, modify, and expand upon the code to build your own worlds.

## 📸 Overview

At its core, this project handles the heavy lifting of 2D game mechanics:
* **Omnidirectional Movement:** The player can move in 4 directions with normalized diagonal support.
* **Dynamic World Generation:** The world is constructed from a tile map loaded dynamically from a text file.
* **Precise Physics:** Collision is handled per tile with custom bounding boxes.
* **Cinematic Camera:** A smoothly interpolated camera follows the player.
* **Sprite Animation:** A basic, extensible animation system for characters and environments.

## 🎨 Assets

All visual assets in this project are custom, hand-drawn pixel art.
* **Terrain:** Grass, mud, straw, and more.
* **Architecture:** Standard brick walls, 3D-perspective walls, and curved boundaries.
* **Biomes:** Special environments (e.g., Hell biome, Basic biome) *(WIP)*.
* **Characters:** Custom-loaded and fully animated player sprites.

## ⚙️ Core Features

### 🧱 Tile System
* **Tile-based rendering** with programmatic horizontal & vertical flipping (saving asset space).
* **Custom collision boxes** mapped per individual tile.
* **Frustum Culling / Optimization:** Only tiles visible within the camera's viewport are drawn.

### 🗺️ Map System
* **Data-driven loading:** Maps are generated from `.txt` files where specific integers correspond to tile IDs.
* **Scalability:** Supports large-scale worlds (e.g., 80x50 tile grids).

### 🧍 Player & Movement
* **Smooth movement** with diagonal normalization (speed capped at `0.707` to prevent faster diagonal traversal).
* **State-based animation:** Directional movement and idle animation states.
* **Collision-aware pathing.**

### 💥 Collision Engine
* **Axis-Separated Detection:** X and Y axis collisions are calculated independently, allowing the player to smoothly slide along walls rather than getting stuck.
* **Custom Hitboxes:** Configurable logic for both world tiles and moving entities.

## 🎮 Controls

| Key | Action |
| :---: | :--- |
| `W` | Move Up |
| `S` | Move Down |
| `A` | Move Left |
| `D` | Move Right |

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/yourusername/your-repo.git](https://github.com/yourusername/your-repo.git)
