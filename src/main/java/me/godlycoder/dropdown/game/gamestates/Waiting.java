package me.godlycoder.dropdown.game.gamestates;

import me.godlycoder.dropdown.DropDown;
import me.godlycoder.dropdown.game.Game;
import me.godlycoder.dropdown.game.GameState;
import me.godlycoder.dropdown.game.State;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class Waiting extends GameState {

    public Waiting(Game game) {
        super(game);
    }

    @Override
    public State getStateValue() {
        return State.WAITING;
    }

    private BukkitTask startCheck;

    @Override
    public void onEnable(DropDown plugin) {
        startCheck = Bukkit.getScheduler().runTaskTimer(
                plugin,
                () -> {
                    if (game.canStart()) {
                        game.setGameState(new Starting(game));
                    }
                },
                0, 0
        );
    }

    @Override
    public void onDisable() {
        startCheck.cancel();
    }
}
