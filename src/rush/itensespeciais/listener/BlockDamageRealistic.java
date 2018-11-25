package rush.itensespeciais.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
		final Block b = e.getBlock();
		if (b.getType() == Material.BEDROCK) {
			if (b.getY() > CAMADA_MINIMA) {
				if (e.getItemInHand().isSimilar(Itens.PICARETA)) {
					e.setCancelled(true);
					BlockBreakEvent event = new BlockBreakEvent(b, e.getPlayer());
					Bukkit.getPluginManager().callEvent(event);
					if (!event.isCancelled()) {
						new BukkitRunnable() {
							@Override
							public void run() {
								b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 1);
								b.breakNaturally();
							}
						}.runTaskLater(Main.get(), 7L);
					}
				}
			}
		}
	}
}
