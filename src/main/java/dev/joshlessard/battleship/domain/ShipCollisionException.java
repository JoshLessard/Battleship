package dev.joshlessard.battleship.domain;

// todo: Trace all paths that can throw exceptions and make sure they're handled properly in UI
//  May need to add more information to the exception (e.g., the two colliding ships here)
class ShipCollisionException extends RuntimeException {
}
