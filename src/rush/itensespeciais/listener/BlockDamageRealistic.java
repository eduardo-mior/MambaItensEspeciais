package rush.itensespeciais.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import rush.itensespeciais.Main;
import rush.itensespeciais.config.Config;
import rush.itensespeciais.itens.Itens;

public class BlockDamageRealistic extends Config implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void aoQuebrar(BlockDamageEvent e) {
		if (e.getBlock().getType() == Material.BEDROCK) {
			if (e.getBlock().getY() > CAMADA_MINIMA) {
				if (e.getItemInHand().isSimilar(Itens.PICARETA)) {
					e.setCancelled(true);
					BlockBreakEvent event = new BlockBreakEvent(e.getBlock(), e.getPlayer());
					Bukkit.getPluginManager().callEvent(event);
					if (!event.isCancelled()) {
						new BukkitRunnable() {
							@Override
							public void run() {
								e.getBlock().getWorld().playEffect(e.getBlock().getLocation(), Effect.STEP_SOUND, 1);
								e.getBlock().breakNaturally();
							}
						}.runTaskLater(Main.get(), 7L);
					}
				}
			}
		}
	}
}
