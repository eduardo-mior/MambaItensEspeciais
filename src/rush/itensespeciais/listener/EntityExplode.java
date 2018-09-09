package rush.itensespeciais.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.creeperevents.oggehej.obsidianbreaker.StorageHandler;
import com.creeperevents.oggehej.obsidianbreaker.UnknownBlockTypeException;

import rush.itensespeciais.addons.BreakerObsidian;
import rush.itensespeciais.config.Config;

public class EntityExplode extends Config implements Listener {
		
	@EventHandler(ignoreCancelled = true)
	public void aoExplodir(EntityExplodeEvent e) {
		
		if (e.getEntityType() == EntityType.CREEPER) {
			if (((Creeper) e.getEntity()).isPowered()) {
				createFakeExplosion(e.getLocation(), CREEPER_POWER);
			}
		}
		
		if (e.getEntityType() == EntityType.FIREBALL) {
			if (e.getEntity().isCustomNameVisible()) {
				createFakeExplosion(e.getLocation(), BOLA_POWER);
			}
		}
	}
	
	private void createFakeExplosion(Location l, int power) {
		StorageHandler breaker = BreakerObsidian.getStorage();
		for (double x = l.getX() - 3; x <= l.getX() + 3; x++) {
			for (double y = l.getY() - 3; y <= l.getY() + 3; y++) {
				for (double z = l.getZ() - 3; z <= l.getZ() + 3; z++) {
					Block b = new Location(l.getWorld(), x, y, z).getBlock();
					if (y >= 1) {
						if (breaker.isValidBlock(b)) {
							try {
								if (breaker.addDamage(b, power)) {
									b.breakNaturally();
								}
							} catch (UnknownBlockTypeException e) {
								e.printStackTrace();
							}
						}
					}
				}	
			}
		}
	}
}
