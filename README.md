# Battleship
An implementation of the class Milton Bradley Battleship board game.

# Prepare for Battle
Secretly place your fleet of five ships on your ocean grid.  Your opponent does the same.

Rules for placing ships:
* Place each ship in any horizontal or vertical position, but not diagonally.
* Do not place a ship so that any part of it overlaps letters, numbers, the edge of the grid, or another ship.
* Do not change the position of any ship once the game has begun.

# How to Play
Decide who will go first.  You and your opponent will alternate turns, calling out one shot per turn to try and
hit each other's ships.

## Call Your Shot!
On your turn, pick a target hole on your target grid and call out its location by letter and number.
Each target hole has a letter-number coordinate that corresponds with the same coordinate on your opponent's
ocean grid.  To determine coordinate, find its corresponding letter on the left side of the target grid and
its number on the top of the target grid.

For example, D4.

When you call a shot, your opponent must tell you whether your shot is a hit or a miss.

# It's a Hit!
If you call out a shot location that is occupied by a ship on your opponent's ocean grid, your shot is a hit!
Your opponent tells you which ship you have hit (e.g., cruiser, submarine, etc.).  Record your hit by placing a
red peg in the corresponding target hole on your target grid.  Your opponent places a red peg in the corresponding
hole in the ship you have hit on his/her ocean grid.

## Example
You call: "D4."
<BR>
Opponent answers: "Hit. Cruiser."

You place a red peg in coordinate D4 of your target grid.  Your opponent places a red peg in his/her cruiser directly
over D4 in their ocean grid.

# It's a Miss!
If you call a shot location not occupied by a ship on your opponent's ocean grid, it's a miss.  Record your miss by
placing a white peg in the corresponding target hole on your target grid so you won't call this shot again.  It's not
necessary for players to record each other's misses with white pegs on their ocean grids.

After a hit or miss, your turn is over.

## Example
Your opponent calls: "F4."
<BR>
You answer: "Miss."

Your opponent places a white peg in coordinate F4 of their target grid.

Play continues in this manner, with you and your opponent calling one shot per turn.

# Sinking a Ship
Once all the holes in any one ship are filled with red pegs, it has been sunk.  The owner of the ship must announce
which ship has been sunk.  Keep track of how many of your opponent's ships you sink by placing a red peg for each
ship sunk in one of the five holes at the top of your game unit.

# Winning the Game
If you're the first player to sink your opponent's entire fleet of five ships, you win the game!

# Salvo
Future to do?