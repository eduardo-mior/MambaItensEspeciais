package rush.itensespeciais.listener;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener {

	public static HashMap<Player, Integer> PROTECTEDS = new HashMap<>();

	@EventHandler(ignoreCancelled = true)
	public void aoTomarDando(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.FALL) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if (PROTECTEDS.containsKey(p)) {
					e.setCancelled(true);
					Integer task = PROTECTEDS.get(p);
					Bukkit.getScheduler().cancelTask(task);
					PROTECTEDS.remove(p);
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void aoTomarDano(EntityDamageByEntityEvent e) {
		if (e.getDamager().getType() == EntityType.SNOWBALL) {
			if (e.getDamager().isCustomNameVisible()) {
				e.setCancelled(true);
			}
		}
	}
}
