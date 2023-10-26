package edu.project1;

public sealed interface GuessResult permits Win, Defeat, SuccessfulGuess, WrongGuess {
    String state();
}
