package rush.itensespeciais.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;

import rush.itensespeciais.config.Config;
import rush.itensespeciais.utils.TitleAPI;

public class PotionSplash extends Config implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void aoAtingir(PotionSplashEvent e) {
		if (e.getEntity().isCustomNameVisible()) {

			if (e.getEntity().getCustomName().equals("§dPURIFICADOR")) {
				for (LivingEntity le : e.getAffectedEntities()) {
					if (le instanceof Player) {
						clearEffects(le);
						TitleAPI.sendTitle((Player) le, 10, 30, 5, PURIFICADOR_TITLE, PURIFICADOR_SUBTITLE);
					}
				}
				return;
			}

			if (e.getEntity().getCustomName().equals("§6INCINERADOR")) {
				for (LivingEntity le : e.getAffectedEntities()) {
					if (le instanceof Player) {
						le.setFireTicks(Integer.MAX_VALUE);
						TitleAPI.sendTitle((Player) le, 10, 30, 5, INCINERADOR_TITLE, INCIDERADOR_SUBTITLE);
					}
				}
				return;
			}
		}
	}

	private void clearEffects(LivingEntity le) {
		for (PotionEffect pe : le.getActivePotionEffects()) {
			le.removePotionEffect(pe.getType());
		}
	}
}
