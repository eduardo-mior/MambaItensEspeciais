package rush.itensespeciais.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import rush.itensespeciais.itens.Itens;

public class PlayerDeath implements Listener {

	@EventHandler
	public void aoMorrer(PlayerDeathEvent e) {
		for (ItemStack item : e.getDrops()) {
			if (item.isSimilar(Itens.TOTEM_DA_MORTE)) {
				e.setKeepInventory(true);
				removeItem(e.getEntity());
				return;
			}
		}
	}

	private void removeItem(Player p) {
		for (ItemStack item : p.getInventory().getContents()) {
			if (item.isSimilar(Itens.TOTEM_DA_MORTE)) {
				if (item.getAmount() == 1) {
					p.getInventory().remove(item);
				} else {
					item.setAmount(item.getAmount() - 1);
				}
				return;
			}
		}
	}
}