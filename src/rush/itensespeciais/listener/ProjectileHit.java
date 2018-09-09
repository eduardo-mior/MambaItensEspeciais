package rush.itensespeciais.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import rush.itensespeciais.Main;
import rush.itensespeciais.config.Config;

public class ProjectileHit extends Config implements Listener {
	
	@EventHandler
	public void aoAcertarProjetil(ProjectileHitEvent e) {
		if (e.getEntityType() == EntityType.SNOWBALL) {
			if (e.getEntity().isCustomNameVisible()) {
				executeTrap(e.getEntity().getLocation());
			}
		}
	}
	
	private void executeTrap(Location l) {
		for (double x = l.getX() - 1; x <= l.getX() + 1; x++) {
			for (double z = l.getZ() - 1; z <= l.getZ() + 1; z++) {
				Block b = new Location(l.getWorld(), x, l.getY(), z).getBlock();
				if (b.getType() == Material.AIR) {
					b.setType(Material.WEB);
				}
			}
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				for (double x = l.getX() - 1; x <= l.getX() + 1; x++) {
					for (double z = l.getZ() - 1; z <= l.getZ() + 1; z++) {
						Block b = new Location(l.getWorld(), x, l.getY(), z).getBlock();
						if (b.getType() == Material.WEB) {
							b.setType(Material.AIR);
						}
					}
				}
			}
		}.runTaskLater(Main.get(), (20L * COOLDOWN_TEIA));
	}
}
