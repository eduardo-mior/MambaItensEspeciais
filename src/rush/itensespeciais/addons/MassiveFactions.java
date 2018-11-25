package rush.itensespeciais.addons;

import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import rush.itensespeciais.config.Config;

public class MassiveFactions extends Config {

	public static boolean resetKDR(Player p) {
		MPlayer mp = MPlayer.get(p);
		if (mp.getKills() > 0 || mp.getDeaths() > 0) {
			mp.setKills(0);
			mp.setDeaths(0);
			p.sendMessage(RESETKDR_SUCESSO);
			return true;
		} else {
			p.sendMessage(RESETKDR_ERRO);
			return false;
		}
	}

	public static boolean upPower(Player p) {
		MPlayer mp = MPlayer.get(p);
		int poderatual = mp.getPowerRounded();
		if (poderatual >= mp.getPowerMax()) {
			p.sendMessage(translatedMessage(mp, PODER_INSTANTANEO_ERRO));
			return false;
		} else {
			mp.setPower(poderatual + 1.0);
			p.sendMessage(translatedMessage(mp, PODER_INSTANTANEO_USADO));
			return true;
		}
	}

	public static boolean upMaxPower(Player p) {
		MPlayer mp = MPlayer.get(p);
		int maximo = mp.getPowerMaxRounded();
		if (maximo >= LIMITE_PODER) {
			p.sendMessage(translatedMessage(mp, PODER_MAXIMO_ERRO));
			return false;
		} else {
			mp.setPowerBoost(mp.getPowerBoost() + 1.0);
			p.sendMessage(translatedMessage(mp, PODER_MAXIMO_USADO));
			return true;
		}
	}

	public static boolean upMaxMembers(Player p) {
		MPlayer mp = MPlayer.get(p);
		if (!mp.hasFaction()) {
			p.sendMessage(ZONA_LIVRE_ERRO);
			return false;
		}
		Faction f = mp.getFaction();
		int maximo = f.getMembersLimit();
		if (maximo >= LIMITE_MEMBROS) {
			p.sendMessage(translatedMessage(f, MEMBROS_MAXIMO_ERRO));
			return false;
		} else {
			f.setMemberBoost(f.getMemberBoost() + 1);
			p.sendMessage(translatedMessage(f, MEMBROS_MAXIMO_USADO));
			return true;
		}
	}
	
	private static String translatedMessage(MPlayer mp, String txt) {
		return txt.replace("%poder%", String.valueOf(mp.getPowerRounded()))
				  .replace("%maxpoder%", String.valueOf(mp.getPowerMaxRounded()))
				  .replace("%maximoPoder%", String.valueOf(LIMITE_PODER));
	}
	
	private static String translatedMessage(Faction f, String txt) {
		return txt.replace("%membros%", String.valueOf(f.getMembersCount()))
				  .replace("%maxmembros%", String.valueOf(f.getMembersLimit()))
				  .replace("%maximoMembros%", String.valueOf(LIMITE_MEMBROS));
	}

}
