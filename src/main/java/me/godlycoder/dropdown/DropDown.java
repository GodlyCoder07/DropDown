package me.godlycoder.dropdown;

import lombok.Getter;
import me.godlycoder.dropdown.game.Game;
import me.godlycoder.dropdown.game.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author GodlyCoder
 */
public class DropDown extends JavaPlugin implements Listener {
    @Getter private File gameMapsFolder;
    @Getter private GameManager gameManager;

    Game game; // TEST

    @Override
    public void onEnable() {
        gameMapsFolder = new File(getDataFolder(), "maps");
        
        if (!gameMapsFolder.exists()) {
            boolean b = gameMapsFolder.mkdirs();
        }
        
        gameManager = new GameManager(this);
        this.game = gameManager.createGame(); // TEST
    }

    @Override
    public void onDisable() {
        
    }

    // TEST
    @EventHandler
    public void test(PlayerJoinEvent event) {
        game.addGamePlayer(event.getPlayer());
    }
}
