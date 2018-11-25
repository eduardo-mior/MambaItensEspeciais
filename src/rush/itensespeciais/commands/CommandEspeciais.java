package rush.itensespeciais.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rush.itensespeciais.config.Config;
import rush.itensespeciais.itens.Itens;

@SuppressWarnings("all")
public class CommandEspeciais extends Config implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {				
		
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length > 3 || args.length < 1) {
			s.sendMessage(ESPECIAL_INCORRETO);
			return true;
		}

		// Pegando o nome do item especial e verificando se é valido
		String nameItem = args[0].toUpperCase();
		if (!Itens.NAME_ITENS.containsKey(nameItem)) {
			s.sendMessage(ITEM_DESCONHECIDO.replace("%lista%", Itens.NAME_ITENS.keySet().toString()));
			return true;
		}

		// Pegando o item especial
		ItemStack item = Itens.NAME_ITENS.get(nameItem).clone();

		// Pegando a quantia
		int quantia;
		if (args.length < 2) {
			quantia = 1;
		} else {
			try {
				quantia = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				s.sendMessage(NUMERO_INVALIDO.replace("%numero%", args[1]));
				return true;
			}
		}

		// Pegando o player
		Player p;
		if (args.length < 3) {
			if (!(s instanceof Player)) {
				s.sendMessage(ESPECIAL_INCORRETO);
				return true;
			}
			p = (Player) s;
		} else {
			p = Bukkit.getPlayer(args[2]);
			if (p == null) {
				s.sendMessage(PLAYER_INVALIDO.replace("%player%", args[2]));
				return true;
			}
		}

		// Setando a quantia especificada e enviando o item
		item.setAmount(quantia);
		p.getInventory().addItem(item);
		s.sendMessage(ITEM_ENVIADO.replace("%item%", nameItem).replace("%player%", p.getName()));
		return true;
	}
}
