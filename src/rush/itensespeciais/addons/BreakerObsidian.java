package rush.itensespeciais.addons;

import java.lang.reflect.InvocationTargetException;

import com.creeperevents.oggehej.obsidianbreaker.ObsidianBreaker;
import com.creeperevents.oggehej.obsidianbreaker.StorageHandler;

public class BreakerObsidian {

	private static final Class<?> CLAZZ = ObsidianBreaker.class;
	private static final ObsidianBreaker PLUGIN = ObsidianBreaker.getPlugin(ObsidianBreaker.class);
	
	public static StorageHandler getStorage() {
		try {
			return (StorageHandler) CLAZZ.getDeclaredMethod("getStorage").invoke(PLUGIN);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
}
