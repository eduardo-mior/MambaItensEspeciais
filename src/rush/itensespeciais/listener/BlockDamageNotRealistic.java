package rush.itensespeciais.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

import rush.itensespeciais.config.Config;
import rush.itensespeciais.itens.Itens;

public class BlockDamageNotRealistic extends Config implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void aoQuebrar(BlockDamageEvent e) {
		if (e.getBlock().getType() == Material.BEDROCK) {
			if (e.getBlock().getY() > CAMADA_MINIMA) {
				if (e.getItemInHand().isSimilar(Itens.PICARETA)) {
					e.setInstaBreak(true);
				}
			}
		}
	}
}
