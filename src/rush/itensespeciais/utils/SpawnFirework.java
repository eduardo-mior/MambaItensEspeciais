package rush.itensespeciais.utils;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class SpawnFirework {

	public static void empty(Player p) {
		p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
	}
	
	public static void small(Player p) {
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		FireworkMeta fm = fw.getFireworkMeta();
		fm.addEffect(FireworkEffect.builder().with(Type.BALL).withColor(random()).build());
		fw.setFireworkMeta(fm);
	}
	
	public static void medium(Player p) {
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		FireworkMeta fm = fw.getFireworkMeta();
		fm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(random(), random()).build());
		fm.setPower(1);
		fw.setFireworkMeta(fm);
	}
	
	public static void big(Player p) {
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		FireworkMeta fm = fw.getFireworkMeta();
		fm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(random(), random(), random(), random()).build());
		fm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(random(), random(), random(), random()).build());
		fm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(random(), random(), random(), random()).build());
		fm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(random(), random(), random(), random()).build());
		fm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(random(), random(), random(), random()).build());
		fm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(random(), random(), random(), random()).build());
		fm.addEffect(FireworkEffect.builder().with(Type.BALL_LARGE).withColor(random(), random(), random(), random()).build());
		fm.setPower(1);
		fw.setFireworkMeta(fm);
	}
	
	private static Color random() {
		Random rnd = new Random();
		int r = rnd.nextInt(255);
		int g = rnd.nextInt(255);
		int b = rnd.nextInt(255);
		return Color.fromRGB(r,g,b);
	}
}