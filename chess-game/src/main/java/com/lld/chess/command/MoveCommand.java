package com.lld.chess.command;

public interface MoveCommand {
    boolean validate();
    void execute();
}
