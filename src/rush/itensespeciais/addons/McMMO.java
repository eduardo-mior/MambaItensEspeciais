package rush.itensespeciais.addons;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.nossr50.datatypes.skills.AbilityType;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityActivateEvent;

import rush.itensespeciais.itens.Itens;

public class McMMO implements Listener {
	
	@EventHandler
	public void aoAtivarSkill(McMMOPlayerAbilityActivateEvent e) {
		if (e.getAbility() == AbilityType.SUPER_BREAKER) {
			if (e.getPlayer().getItemInHand().isSimilar(Itens.PICARETA)) {
				e.setCancelled(true);
			}
		}
	}
	
}