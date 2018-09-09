package rush.itensespeciais.listener;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener {

	public static ArrayList<Player> PROTECTEDS = new ArrayList<>();

	@EventHandler(ignoreCancelled = true)
	public void aoTomarDando(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.FALL) {
			if (e.getEntity() instanceof Player) {
				if (PROTECTEDS.contains(e.getEntity())) {
					PROTECTEDS.remove(e.getEntity());
					e.setCancelled(true);
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
