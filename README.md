# Dungeons and Dragons Text-based Game

Welcome to the Dungeons and Dragons Text-based Game! This program allows you to play a simplified version of the popular tabletop role-playing game, Dungeons and Dragons, right in your terminal. Embark on an epic adventure, battle fearsome monsters, and make crucial decisions that shape your character's destiny.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [How to Play](#how-to-play)
  - [Starting the Game](#starting-the-game)
  - [Commands](#commands)
  - [Exploration](#exploration)
  - [Combat](#combat)
  - [Game Flow](#game-flow)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- An IDE or text editor (e.g., IntelliJ IDEA, Eclipse, VS Code).
- A terminal or command prompt.

### Installation

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/shakedmatityahu/Dungeons-and-Dragons.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Dungeons-and-Dragons
   ```

3. Compile the project:

   ```bash
   javac -d bin src/**/*.java
   ```

4. Run the game:

   ```bash
   java -cp bin Dungeons_and_Dragons.GameController <path-to-levels>
   ```

   Replace `<path-to-levels>` with the directory path containing your level files.

## How to Play

### Starting the Game

1. Start the game by running the `GameController` with the path to your level files.
2. Select your character from the available options.

### Commands

- Use the following commands to navigate through the levels:
  - `w`: Move up
  - `s`: Move down
  - `a`: Move left
  - `d`: Move right
  - `e`: Cast ability
  - `K`: Special command (Burn them all)

### Exploration

- Navigate through the game by moving between locations using the commands.
- Explore different areas to find items, quests, and encounter NPCs.

### Combat

- Combat occurs when you encounter monsters or enemies.
- During combat encounters, you have options to attack, defend, use items, or try to escape.

### Game Flow

- The game initializes by loading level files and setting up the game boards and enemy lists.
- The player selects a character from the available options.
- Each level is played in turns, where the player makes a move, followed by enemy movements.
- The game continues until all levels are cleared or the player dies.

## Contributing

Contributions to this project are welcome! If you find any bugs or have ideas for improvements, feel free to open an issue or submit a pull request.

1. Fork the repository.
2. Create a new branch for your contribution.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Open a pull request, describing the changes you've made.

---

Enjoy your journey in the Dungeons and Dragons Text-based Game! May your adventures be full of excitement and triumph. If you have any questions or need assistance, feel free to reach out in the Issues section of the repository. Have fun!
