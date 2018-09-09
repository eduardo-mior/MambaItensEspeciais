package rush.itensespeciais.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKick implements Listener {
	
	@EventHandler
	public void aoKickar(PlayerKickEvent e) {
		if (EntityDamage.PROTECTEDS.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
}
