package me.godlycoder.dropdown.gameplayer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.godlycoder.dropdown.DropDown;
import me.godlycoder.dropdown.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.github.paperspigot.Title;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class GamePlayer {
    private final DropDown plugin;
    @Getter final Game game;
    @Getter private final Player player;

    int lives = 3;
    @Getter @Setter GamePlayerState gamePlayerState;

    public void giveKit() {
        player.sendMessage("Kit has been successfully given");
    }

    public void resurrect() {
        if (lives >= 1) {
            lives -= 1;
            player.playNote(player.getLocation(), Instrument.PIANO, new Note(0, Note.Tone.A , true));
            AtomicInteger countdown = new AtomicInteger(5);

            BukkitTask task;
            task = Bukkit.getScheduler().runTaskTimer(
                    plugin,
                    () -> {
                        player.sendTitle(new Title("You died", "Resurrecting in $countdown"));
                        countdown.getAndDecrement();
                    },
                    0, 20
            );

            if (countdown.get() == 0) {
                task.cancel();
            }

        }else {
            eliminate();
        }
    }

    public void respawn() {
        // teleport to random spot at highest block
    }

    public void eliminate() {
        gamePlayerState = GamePlayerState.SPECTATOR;
        player.spigot().setCollidesWithEntities(false);
        // hide player
    }
}
