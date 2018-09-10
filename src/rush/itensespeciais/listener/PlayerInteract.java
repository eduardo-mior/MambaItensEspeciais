package rush.itensespeciais.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import rush.itensespeciais.Main;
import rush.itensespeciais.addons.MassiveFactions;
import rush.itensespeciais.config.Config;
import rush.itensespeciais.itens.Itens;
import rush.itensespeciais.utils.SpawnFirework;

@SuppressWarnings("all")
public class PlayerInteract extends Config implements Listener  {

	@EventHandler
	public void aoClicar(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (e.getItem() == null) return;
			ItemStack item = e.getItem();
			
			for (ItemStack i : Itens.NEW_ITENS.keySet()) {
				if (item.isSimilar(i)) {
					dispatchCommands(e.getPlayer(), Itens.NEW_ITENS.get(i));
					removeItem(e.getPlayer());
					e.setCancelled(true);
					return;
				}
			}

			if (item.isSimilar(Itens.REPARADOR)) {
				repairArmor(e.getPlayer());
				removeItem(e.getPlayer());
				e.setCancelled(true);
				return;
			}

			if (item.isSimilar(Itens.AGRUPADOR)) {
				agroupPotions(e.getPlayer());
				removeItem(e.getPlayer());
				e.setCancelled(true);
				return;
			}

			if (item.isSimilar(Itens.LANCADOR)) {
				if (e.getPlayer().getLocation().getY() < 275) {
					executeLauncher(e.getPlayer());
					removeItem(e.getPlayer());
					e.setCancelled(true);
					return;
				}
			}

			if (item.isSimilar(Itens.RAIO_MESTRE)) {
				World w;
				Location l;
				if (e.getClickedBlock() != null) {
					w = e.getClickedBlock().getWorld();
					l = e.getClickedBlock().getLocation();
				} else {
					w = e.getPlayer().getWorld();
					l = e.getPlayer().getTargetBlock((Set<Material>) null, 15).getLocation();
				}
				playLightning(w, l);
				removeItem(e.getPlayer());
				e.setCancelled(true);
				return;
			}
			
			if (item.isSimilar(Itens.BOLA_DE_FOGO)) {
				throwFireball(e.getPlayer());
				removeItem(e.getPlayer());
				e.setCancelled(true);
				return;	
			}

			if (item.isSimilar(Itens.PODER_INSTANTANEO)) {
				if (MassiveFactions.upPower(e.getPlayer())) {
					SpawnFirework.medium(e.getPlayer());
					removeItem(e.getPlayer());
				}
				e.setCancelled(true);
				return;	
			}

			if (item.isSimilar(Itens.PODER_MAXIMO)) {
				if (MassiveFactions.upMaxPower(e.getPlayer())) {
					SpawnFirework.big(e.getPlayer());
					removeItem(e.getPlayer());
				}
				e.setCancelled(true);
				return;		
			}

			if (item.isSimilar(Itens.RESET_KDR)) {
				if (MassiveFactions.resetKDR(e.getPlayer())) {
					SpawnFirework.small(e.getPlayer());
					removeItem(e.getPlayer());
				}
				e.setCancelled(true);
				return;
			}
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

	private void repairArmor(Player p) {
		ItemStack[] armadura = p.getInventory().getArmorContents();
		for (ItemStack i : armadura) {
			if (i != null && i.getType().getMaxDurability() != 0) {
				i.setDurability((short) 0);
			}
		}
		p.updateInventory();
		p.playSound(p.getLocation(), BIGORNA, 1, 1);
	}

	private void agroupPotions(Player p) {
		PlayerInventory inv = p.getInventory();
		HashMap<ItemStack, Integer> potions = new HashMap<>();

		for (ItemStack item : inv.getContents()) {
			if (item != null && item.getType() == Material.POTION) {
				int amount = item.getAmount();
				item.setAmount(1);
				if (potions.containsKey(item)) {
					amount += potions.get(item);
					potions.replace(item, amount);
				} else {
					potions.put(item, amount);
				}
				inv.removeItem(item);
			}
		}

		for (ItemStack potion : potions.keySet()) {
			int amount = potions.get(potion);
			while (amount > MAX_STACK_POTION) {
				potion.setAmount(MAX_STACK_POTION);
				inv.addItem(potion);
				amount -= MAX_STACK_POTION;
			}
			potion.setAmount(amount);
			inv.addItem(potion);
		}
		p.playEffect(p.getLocation(), Effect.POTION_BREAK, 0);
	}

	private void playLightning(World w, Location l) {
		w.strikeLightning(l);
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.playSound(p.getLocation(), RAIO, 1, 1);
		}
	}
	
	private void executeLauncher(Player p) {
		Vector vel = p.getVelocity().setY(4.0);
		SpawnFirework.empty(p);
		p.setVelocity(vel);
		BukkitTask newTask = new BukkitRunnable() {
			@Override
			public void run() {
				EntityDamage.PROTECTEDS.remove(p);
			}
		}.runTaskLater(Main.get(), 400L);
		if (EntityDamage.PROTECTEDS.containsKey(p)) {
			int oldTask = EntityDamage.PROTECTEDS.get(p);
			Bukkit.getScheduler().cancelTask(oldTask);
		}
		EntityDamage.PROTECTEDS.put(p, newTask.getTaskId());
	}

	private void throwFireball(Player p) {
		Vector vec = p.getEyeLocation().getDirection().multiply(0.5);
		Location location = p.getLocation().add(0.0, 1.0, 0.0);
		Fireball fireball = (Fireball) p.getWorld().spawnEntity(location, EntityType.FIREBALL);
		fireball.setYield(4f);
		fireball.setShooter(p);
		fireball.setVelocity(vec);
		fireball.setIsIncendiary(false);
		fireball.setCustomNameVisible(true);
		location.getWorld().playEffect(location, Effect.GHAST_SHOOT, 1);
	}
	
	private void dispatchCommands(Player p, List<String> commands) {
		for (String command : commands) {

			if (command.startsWith("console: ")) {
				String line = command.split("console: ")[1].replace('&', '§').replace("{player}", p.getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), line);
				continue;
			}

			if (command.startsWith("player: ")) {
				String line = command.split("player: ")[1].replace('&', '§').replace("{player}", p.getName());
				p.chat("/" + line);
				continue;
			}

			if (command.startsWith("mensagem: ")) {
				String msg = command.split("mensagem: ")[1].replace('&', '§').replace("{player}", p.getName());
				p.sendMessage(msg);
				continue;
			}

			if (command.startsWith("broadcast: ")) {
				String msg = command.split("broadcast: ")[1].replace('&', '§').replace("{player}", p.getName());
				Bukkit.broadcastMessage(msg);
				continue;
			}
		}
	}
}
