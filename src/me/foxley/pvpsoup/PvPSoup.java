package me.foxley.pvpsoup;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PvPSoup extends JavaPlugin implements Listener {

    private int heal;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        heal = this.getConfig().getInt("heal");

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) { // Right Click

            if ((event.getItem() != null) && (event.getItem().getType() == Material.MUSHROOM_SOUP)) {

                Player p = event.getPlayer();

                p.setHealth(Math.min(p.getMaxHealth(), p.getHealth() + heal));

                event.getItem().setType(Material.BOWL);
            }
        }
    }

}
