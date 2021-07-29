package me.godlycoder.dropdown.gamemap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.util.FileUtil;

import java.io.File;

public class LocalGameMap implements GameMap {
    private final File sourceWorldFolder;
    private File activeWorldFolder;

    private World world;

    public LocalGameMap(File worldFolder, String worldName, boolean loadOnInit) {
        sourceWorldFolder = new File(worldFolder, worldName);
        if (loadOnInit) load();
    }

    @Override
    public boolean load() {
        if (isLoaded()) return true;

        this.activeWorldFolder = new File(
                Bukkit.getWorldContainer().getParentFile(),
                sourceWorldFolder.getName() + "_active_" + System.currentTimeMillis()
        );
        FileUtil.copy(sourceWorldFolder, activeWorldFolder);

        this.world = Bukkit.createWorld(new WorldCreator(activeWorldFolder.getName()));
        this.world.setAutoSave(false);

        return isLoaded();
    }

    @Override
    public void unload() {
        Bukkit.unloadWorld(world, false);
        delete(activeWorldFolder);

        world = null;
        activeWorldFolder = null;
    }

    @Override
    public boolean restoreFromSource() {
        unload();
        return load();
    }

    @Override
    public boolean isLoaded() {
        return world != null;
    }

    @Override
    public World getWorld() {
        return world;
    }

    private void delete(File file) {
        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    delete(child);
                }
            }
        }

        boolean b = file.delete();
    }
}
