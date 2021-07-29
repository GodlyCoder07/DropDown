package me.godlycoder.dropdown.game.gamestates;

import me.godlycoder.dropdown.DropDown;
import me.godlycoder.dropdown.game.Game;
import me.godlycoder.dropdown.game.GameState;
import me.godlycoder.dropdown.game.State;
import me.godlycoder.dropdown.gameplayer.GamePlayer;
import me.godlycoder.dropdown.util.Text;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitTask;
import org.github.paperspigot.Title;

import java.util.concurrent.atomic.AtomicInteger;

public class Starting extends GameState {

    public Starting(Game game) {
        super(game);
    }

    @Override
    public State getStateValue() {
        return State.STARTING;
    }

    private BukkitTask startConfirmation;
    private BukkitTask countdownTask;

    @Override
    public void onEnable(DropDown plugin) {
        AtomicInteger countdown = new AtomicInteger(30);

        startConfirmation = Bukkit.getScheduler().runTaskTimer(
                plugin,
                () -> {
                    if (!game.canStart()) {
                        game.setGameState(new Waiting(game));
                    }
                },
                0, 0
        );

        countdownTask = Bukkit.getScheduler().runTaskTimer(
                plugin,
                () -> {
                    for (GamePlayer gamePlayer : game.getGamePlayers()) {
                        if (countdown.get() <= 10) {
                            gamePlayer.getPlayer().setLevel(countdown.get());
                            if (countdown.get() <= 5) {
                                gamePlayer.getPlayer().playSound(gamePlayer.getPlayer().getLocation(), Sound.ANVIL_LAND, 1, 1);
                                gamePlayer.getPlayer().sendTitle(new Title("Starting in " + countdown));
                            }
                            if (countdown.get() <= 0) {
                                game.setGameState(new Ongoing(game));
                                gamePlayer.getPlayer().sendTitle(new Title(Text.color("§2Welcome to Floor Is Lava"), Text.color("§eGood Luck!"), 1, 2, 1));
                                gamePlayer.getPlayer().sendMessage("Message: Objectives");
                            }
                        }
                        gamePlayer.getPlayer().sendMessage(Text.color("§2Game starting in $ecountDown"));

                    }
                    countdown.getAndDecrement();
                },
                0, 20
        );
    }

    @Override
    public void onDisable() {
        startConfirmation.cancel();
        countdownTask.cancel();
        game.getGamePlayers().forEach(GamePlayer::giveKit);
    }
}