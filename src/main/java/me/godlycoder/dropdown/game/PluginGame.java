package me.godlycoder.dropdown.game;

import me.godlycoder.dropdown.gameplayer.GamePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

abstract public class PluginGame {
    abstract public boolean canStart();

    abstract public GameState getGameState();
    abstract public void setGameState(GameState gameState);

    abstract public Set<GamePlayer> getGamePlayers();
    abstract public void addGamePlayer(Player player); // refactor to EntityPlayer when changing to NMS
    abstract public GamePlayer getGamePlayer(Player player);
    abstract public void removeGamePlayer(Player player);

    abstract public Set<GamePlayer> getAliveGamePlayers();
    abstract public Set<GamePlayer> getSpectatingGamePlayers();
}
