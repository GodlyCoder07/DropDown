package me.godlycoder.dropdown.game.gamestates;

import me.godlycoder.dropdown.DropDown;
import me.godlycoder.dropdown.game.Game;
import me.godlycoder.dropdown.game.GameState;
import me.godlycoder.dropdown.game.State;
import me.godlycoder.dropdown.gameplayer.GamePlayerState;

public class Ongoing extends GameState {

    public Ongoing(Game game) {
        super(game);
    }

    @Override
    public State getStateValue() {
        return State.ONGOING;
    }

    @Override
    public void onEnable(DropDown plugin) {
        game.getGamePlayers().forEach( it ->
            it.setGamePlayerState(GamePlayerState.ALIVE)
        );

        // check winner runnable
    }

    @Override
    public void onDisable() {

    }
}
