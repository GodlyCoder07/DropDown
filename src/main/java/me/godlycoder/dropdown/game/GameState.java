package me.godlycoder.dropdown.game;

import me.godlycoder.dropdown.DropDown;

abstract public class GameState {
    protected Game game;

    public GameState(Game game) {
        this.game = game;
    }

    public abstract State getStateValue();

    public abstract void onEnable(DropDown plugin);
    public abstract void onDisable();

    // abstract StateListenerProvider listenerProvider();
}
