package me.godlycoder.dropdown.game.gamestates;

import me.godlycoder.dropdown.DropDown;
import me.godlycoder.dropdown.game.Game;
import me.godlycoder.dropdown.game.GameState;
import me.godlycoder.dropdown.game.State;

public class End extends GameState {

    public End(Game game) {
        super(game);
    }

    @Override
    public State getStateValue() {
        return State.END;
    }

    @Override
    public void onEnable(DropDown plugin) {

    }

    @Override
    public void onDisable() {

    }
}