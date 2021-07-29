package me.godlycoder.dropdown.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class Text {
    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('§', text);
    }

}
