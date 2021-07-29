package me.godlycoder.dropdown.game;

import lombok.Getter;
import me.godlycoder.dropdown.DropDown;
import me.godlycoder.dropdown.gameplayer.GamePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameManager {
    @Getter private List<Game> games;

    private final DropDown plugin;

    public GameManager(DropDown plugin) {
        this.plugin = plugin;
        games = new ArrayList<>();
    }

    public Game createGame() {
        final Game game = new Game(plugin);
        games.add(game);
        return game;
    }

    public Game getGame(Player player) {
        return games.stream()
                .filter( it -> it.getGamePlayers().stream().map(GamePlayer::getPlayer).collect(Collectors.toList()).contains(player) )
                .findFirst()
                .orElse(null);
    }
}
