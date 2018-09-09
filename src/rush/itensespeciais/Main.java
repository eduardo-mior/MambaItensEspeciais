package rush.itensespeciais;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush.itensespeciais.commands.CommandEspeciais;
import rush.itensespeciais.config.Config;
import rush.itensespeciais.itens.Itens;
import rush.itensespeciais.listener.ProjectileLaunch;
import rush.itensespeciais.listener.BlockDamageNotRealistic;
import rush.itensespeciais.listener.BlockDamageRealistic;
import rush.itensespeciais.listener.EntityDamage;
import rush.itensespeciais.listener.EntityExplode;
import rush.itensespeciais.listener.PlayerInteract;
import rush.itensespeciais.listener.PlayerInteractInBlock;
import rush.itensespeciais.listener.PlayerKick;
import rush.itensespeciais.listener.PotionSplash;
import rush.itensespeciais.listener.ProjectileHit;
import rush.itensespeciais.listener.PlayerDeath;
import rush.itensespeciais.utils.ConfigManager;
import rush.itensespeciais.utils.TitleAPI;

public class Main extends JavaPlugin implements Listener {

	private static Main aqui;

	@Override
	public void onEnable() {
		aqui = this;
		gerarConfigs();
		carregarConfigs();
		registrarEventos();
		registrarComandos();
		new TitleAPI();
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll((Listener) this);
	}

	private void gerarConfigs() {
		ConfigManager.createNewConfig("ajuda");
		ConfigManager.createNewConfig("config");
		ConfigManager.createNewConfig("mensagens");
		ConfigManager.createNewConfig("crie-novos-itens");
	}
	
	private void carregarConfigs() {
		Itens.loadItens();
		Config.loadConfig();
	}

	private void registrarComandos() {
		getCommand("especial").setExecutor(new CommandEspeciais());
	}

	private void registrarEventos() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new EntityExplode(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new PlayerInteractInBlock(), this);
		pm.registerEvents(new PlayerKick(), this);
		pm.registerEvents(new PotionSplash(), this);
		pm.registerEvents(new ProjectileHit(), this);
		pm.registerEvents(new ProjectileLaunch(), this);
		
		if (Config.OBSIDIAN_BREAKER) pm.registerEvents(new EntityDamage(), this);
		
		if (Config.REALISTIC) pm.registerEvents(new BlockDamageRealistic(), this);
		else pm.registerEvents(new BlockDamageNotRealistic(), this);
	}

	public static Main get() {
		return aqui;
	}

}
