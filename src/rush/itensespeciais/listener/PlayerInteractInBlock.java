package rush.itensespeciais.listener;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import rush.itensespeciais.config.Config;
import rush.itensespeciais.itens.Itens;

public class PlayerInteractInBlock extends Config implements Listener {
	
	@EventHandler
	public void aoClicar(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (e.getItem() == null) return;
			if (e.getClickedBlock() == null) return;
			
			if(e.getItem().isSimilar(Itens.SUPER_CREEPER)) {
				Location spawn = getCorrectLocation(e.getClickedBlock().getLocation(), e.getBlockFace());
				Creeper cc = (Creeper) spawn.getWorld().spawnEntity(spawn, EntityType.CREEPER);
				cc.setPowered(true);
				
				removeItem(e.getPlayer());
				e.setCancelled(true);
				return;
			}
			
			if (e.getItem().isSimilar(Itens.DETECTOR_DE_SLIMECHUNK)) {
				Location loc = e.getClickedBlock().getLocation();
				long worldSeed = loc.getWorld().getSeed();
				Chunk playerChunk;
				int xChunk;
				int zChunk;
				Random random = new Random(worldSeed + (xChunk = (playerChunk = loc.getWorld().getChunkAt(loc)).getX()) * xChunk * 4987142 + xChunk * 5947611 + (zChunk = playerChunk.getZ()) * zChunk * 4392871L + zChunk * 389711 ^ 0x3AD8025F);
				if (random.nextInt(10) == 0) {
					e.getPlayer().sendMessage(DETECTOR_ESTA);
					return;
				} else {
					e.getPlayer().sendMessage(DETECTOR_NAOESTA);
					return;
				}
			}
		}
	}
	
	private Location getCorrectLocation(Location l, BlockFace face) {
		switch (face) {
			case UP: return l.add(0.5, 1.0, 0.5);
			case DOWN: return l.add(0.5, -1.0, 0.5);
			case EAST: return l.add(1.5, 0.0, 0.5);
			case WEST: return l.add(-0.5, 0.0, 0.5);
			case SOUTH:	return l.add(0.5, 0.0, 1.5);
			case NORTH:	return l.add(0.5, 0.0, -0.5);
			default: return l;
		}
	}

	private void removeItem(Player p) {
		if (p.getItemInHand().getAmount() < 2) {
			p.setItemInHand(new ItemStack(Material.AIR));
		} else {
			ItemStack item = p.getItemInHand();
			item.setAmount(item.getAmount() - 1);
		}
	}
}
