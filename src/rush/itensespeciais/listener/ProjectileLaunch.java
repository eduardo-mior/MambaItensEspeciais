package rush.itensespeciais.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import rush.itensespeciais.itens.Itens;

public class ProjectileLaunch implements Listener {

	@EventHandler
	public void aoLancar(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {	
			Player p = (Player) e.getEntity().getShooter();

			if (e.getEntityType() == EntityType.SNOWBALL) {
				if (p.getItemInHand().isSimilar(Itens.ARMADILHA)) {
					e.getEntity().setCustomName("§bARMADILHA");
					e.getEntity().setCustomNameVisible(true);
					return;
				}
			}
			
			if (e.getEntityType() == EntityType.SPLASH_POTION) {

				if (p.getItemInHand().isSimilar(Itens.PURIFICADOR)) {
					e.getEntity().setCustomName("§dPURIFICADOR");
					e.getEntity().setCustomNameVisible(true);
					return;
				}
			
				if (p.getItemInHand().isSimilar(Itens.INCINERADOR)) {
					e.getEntity().setCustomName("§6INCINERADOR");
					e.getEntity().setCustomNameVisible(true);
					return;
				}	
			}
		}
	}
}
