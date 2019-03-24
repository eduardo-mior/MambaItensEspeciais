package rush.itensespeciais.itens;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rush.itensespeciais.utils.ConfigManager;
import rush.itensespeciais.utils.ReflectionUtils;

@SuppressWarnings("all")
public abstract class Itens {
	
	private static final FileConfiguration config = ConfigManager.getConfig("config");

	public static ItemStack SUPER_CREEPER;
	public static ItemStack BOLA_DE_FOGO;
	public static ItemStack PURIFICADOR;
	public static ItemStack INCINERADOR;
	public static ItemStack RESET_KDR;
	public static ItemStack PODER_MAXIMO;
	public static ItemStack PODER_INSTANTANEO;
	public static ItemStack MEMBROS_MAXIMO;
	public static ItemStack LANCADOR;
	public static ItemStack REPARADOR;
	public static ItemStack ARMADILHA;
	public static ItemStack RAIO_MESTRE;
	public static ItemStack PICARETA;
	public static ItemStack TOTEM_DA_MORTE;
	public static ItemStack AGRUPADOR;
	public static ItemStack DETECTOR_DE_SLIMECHUNK;
	public static HashMap<ItemStack, List<String>> NEW_ITENS = new HashMap<>();
	public static HashMap<String, ItemStack> NAME_ITENS = new HashMap<>();

	public static void loadItens() {
		buildSuperCreeper();
		buildBolaDeFogo();
		buildPurificador();
		buildIncinerador();
		buildResetKDR();
		buildPoderMaximo();
		buildPoderInstantaneo();
		buildMembrosMaximo();
		buildLancador();
		buildReparadorInstantaneo();
		buildArmadilha();
		buildRaioMestre();
		buildPicaretaQuebraBedRock();
		buildTotemDaMorte();
		buildAgrupadorDePocoes();
		buildDetectorDeSlimeChunk();
		buildNewItens();
	}
	
	
	// Construtor do ItemStack do SuperCreeper
	private static void buildSuperCreeper() {	
		// Pegando as configs do item
		int idSuperCreeper = config.getInt("Super_Creeper.Id");
		int dataSuperCreeper = config.getInt("Super_Creeper.Data");
		String nomeSuperCreeper = config.getString("Super_Creeper.Nome");
		List<String> loreSuperCreeper = config.getStringList("Super_Creeper.Lore");
		boolean glow = config.getBoolean("Super_Creeper.Glow");
		boolean flags = config.getBoolean("Super_Creeper.Flags");
		
		// Criando o ItemStack
		ItemStack SuperCreeper = new ItemStack(idSuperCreeper);
		ItemMeta SuperCreeperMeta = SuperCreeper.getItemMeta();
		SuperCreeperMeta.setDisplayName(coloredName(nomeSuperCreeper));
		SuperCreeperMeta.setLore(coloredLore(loreSuperCreeper));
		if (glow) {
			SuperCreeperMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			SuperCreeperMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				SuperCreeperMeta.addItemFlags(flag);
			}
		}
		SuperCreeper.setDurability((short) dataSuperCreeper);
		SuperCreeper.setItemMeta(SuperCreeperMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("CREEPER", SuperCreeper);
		SUPER_CREEPER = SuperCreeper;
	}
	
	
	// Construtor do ItemStack da bola de fogo
	private static void buildBolaDeFogo() {	
		// Pegando as configs do item
		int idBolaDeFogo = config.getInt("Bola_de_Fogo.Id");
		int dataBolaDeFogo = config.getInt("Bola_de_Fogo.Data");
		String nomeBolaDeFogo = config.getString("Bola_de_Fogo.Nome");
		List<String> loreBolaDeFogo = config.getStringList("Bola_de_Fogo.Lore");
		boolean glow = config.getBoolean("Bola_de_Fogo.Glow");
		boolean flags = config.getBoolean("Bola_de_Fogo.Flags");
		
		// Criando o ItemStack
		ItemStack BolaDeFogo = new ItemStack(idBolaDeFogo);
		ItemMeta BolaDeFogoMeta = BolaDeFogo.getItemMeta();
		BolaDeFogoMeta.setDisplayName(coloredName(nomeBolaDeFogo));
		BolaDeFogoMeta.setLore(coloredLore(loreBolaDeFogo));
		if (glow) {
			BolaDeFogoMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			BolaDeFogoMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				BolaDeFogoMeta.addItemFlags(flag);
			}
		}
		BolaDeFogo.setDurability((short) dataBolaDeFogo);
		BolaDeFogo.setItemMeta(BolaDeFogoMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("BOLA_DE_FOGO", BolaDeFogo);
		BOLA_DE_FOGO = BolaDeFogo;
	}
	
	
	// Construtor do ItemStack do Purificador
	private static void buildPurificador() {		
		// Pegando as configs do item
		int idPurificador = config.getInt("Purificador.Id"); // ID da poção
		int dataPurificador = config.getInt("Purificador.Data");
		String nomePurificador = config.getString("Purificador.Nome");
		List<String> lorePurificador = config.getStringList("Purificador.Lore");
		boolean flags = config.getBoolean("Purificador.Flags");
		
		// Criando o ItemStack
		ItemStack Purificador = new ItemStack(idPurificador);
		PotionMeta PurificadorMeta = (PotionMeta) Purificador.getItemMeta();
		PurificadorMeta.setDisplayName(coloredName(nomePurificador));
		PurificadorMeta.setLore(coloredLore(lorePurificador));
		PurificadorMeta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 1, 1), true);
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				PurificadorMeta.addItemFlags(flag);
			}
		}
		Purificador.setDurability((short) dataPurificador);
		Purificador.setItemMeta(PurificadorMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("PURIFICADOR", Purificador);
		PURIFICADOR = Purificador;
	}
	
	
	// Construtor do ItemStack do Incinerador
	private static void buildIncinerador() {		
		// Pegando as configs do item
		int idIncinerador = config.getInt("Incinerador.Id"); // ID da poção
		int dataIncinerador = config.getInt("Incinerador.Data");
		String nomeIncinerador = config.getString("Incinerador.Nome");
		List<String> loreIncinerador = config.getStringList("Incinerador.Lore");
		boolean flags = config.getBoolean("Incinerador.Flags");
		
		// Criando o ItemStack
		ItemStack Incinerador = new ItemStack(idIncinerador);
		PotionMeta IncineradorMeta = (PotionMeta) Incinerador.getItemMeta();
		IncineradorMeta.setDisplayName(coloredName(nomeIncinerador));
		IncineradorMeta.setLore(coloredLore(loreIncinerador));
		IncineradorMeta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 1, 1), true);
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				IncineradorMeta.addItemFlags(flag);
			}
		}
		Incinerador.setDurability((short) dataIncinerador);
		Incinerador.setItemMeta(IncineradorMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("INCINERADOR", Incinerador);
		INCINERADOR = Incinerador;
	}
	
	
	// Construtor do ItemStack do ResetKDR
	private static void buildResetKDR() {		
		// Pegando as configs do item
		int idResetKDR = config.getInt("Reset_KDR.Id");
		int dataResetKDR = config.getInt("Reset_KDR.Data");
		String nomeResetKDR = config.getString("Reset_KDR.Nome");
		List<String> loreResetKDR = config.getStringList("Reset_KDR.Lore");
		boolean glow = config.getBoolean("Reset_KDR.Glow");
		boolean flags = config.getBoolean("Reset_KDR.Flags");
		
		// Criando o ItemStack
		ItemStack ResetKDR = new ItemStack(idResetKDR);
		ItemMeta ResetKDRMeta = ResetKDR.getItemMeta();
		ResetKDRMeta.setDisplayName(coloredName(nomeResetKDR));
		ResetKDRMeta.setLore(coloredLore(loreResetKDR));
		if (glow) {
			ResetKDRMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			ResetKDRMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				ResetKDRMeta.addItemFlags(flag);
			}
		}
		ResetKDR.setDurability((short) dataResetKDR);
		ResetKDR.setItemMeta(ResetKDRMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("RESET_KDR", ResetKDR);
		RESET_KDR = ResetKDR;
	}
	
	
	// Construtor do ItemStack do PoderMaximo
	private static void buildPoderMaximo() {		
		// Pegando as configs do item
		int idPoderMaximo = config.getInt("Poder_Maximo.Id");
		int dataPoderMaximo = config.getInt("Poder_Maximo.Data");
		String nomePoderMaximo = config.getString("Poder_Maximo.Nome");
		List<String> lorePoderMaximo = config.getStringList("Poder_Maximo.Lore");
		boolean glow = config.getBoolean("Poder_Maximo.Glow");
		boolean flags = config.getBoolean("Poder_Maximo.Flags");
		
		// Criando o ItemStack
		ItemStack PoderMaximo = new ItemStack(idPoderMaximo);
		ItemMeta PoderMaximoMeta = PoderMaximo.getItemMeta();
		PoderMaximoMeta.setDisplayName(coloredName(nomePoderMaximo));
		PoderMaximoMeta.setLore(coloredLore(lorePoderMaximo));
		if (glow) {
			PoderMaximoMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			PoderMaximoMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				PoderMaximoMeta.addItemFlags(flag);
			}
		}
		PoderMaximo.setDurability((short) dataPoderMaximo);
		PoderMaximo.setItemMeta(PoderMaximoMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("PODER_MAXIMO", PoderMaximo);
		PODER_MAXIMO = PoderMaximo;
	}
	
	
	// Construtor do ItemStack do PoderInstantaneo
	private static void buildPoderInstantaneo() {		
		// Pegando as configs do item
		int idPoderInstantaneo = config.getInt("Poder_Instantaneo.Id");
		int dataPoderInstantaneo = config.getInt("Poder_Instantaneo.Data");
		String nomePoderInstantaneo = config.getString("Poder_Instantaneo.Nome");
		List<String> lorePoderInstantaneo = config.getStringList("Poder_Instantaneo.Lore");
		boolean glow = config.getBoolean("Poder_Instantaneo.Glow");
		boolean flags = config.getBoolean("Poder_Instantaneo.Flags");

		// Criando o ItemStack
		ItemStack PoderInstantaneo = new ItemStack(idPoderInstantaneo);
		ItemMeta PoderInstantaneoMeta = PoderInstantaneo.getItemMeta();
		PoderInstantaneoMeta.setDisplayName(coloredName(nomePoderInstantaneo));
		PoderInstantaneoMeta.setLore(coloredLore(lorePoderInstantaneo));
		if (glow) {
			PoderInstantaneoMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			PoderInstantaneoMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				PoderInstantaneoMeta.addItemFlags(flag);
			}
		}
		PoderInstantaneo.setDurability((short) dataPoderInstantaneo);
		PoderInstantaneo.setItemMeta(PoderInstantaneoMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("PODER_INSTANTANEO", PoderInstantaneo);
		PODER_INSTANTANEO = PoderInstantaneo;
	}
	
	
	// Construtor do ItemStack do MembrosMaximo
	private static void buildMembrosMaximo() {		
		// Pegando as configs do item
		int idMembrosMaximo = config.getInt("Membros_Maximo.Id");
		int dataMembrosMaximo = config.getInt("Membros_Maximo.Data");
		String nomeMembrosMaximo = config.getString("Membros_Maximo.Nome");
		List<String> loreMembrosMaximo = config.getStringList("Membros_Maximo.Lore");
		boolean glow = config.getBoolean("Membros_Maximo.Glow");
		boolean flags = config.getBoolean("Membros_Maximo.Flags");
		
		// Criando o ItemStack
		ItemStack MembrosMaximo = new ItemStack(idMembrosMaximo);
		ItemMeta MembrosMaximoMeta = MembrosMaximo.getItemMeta();
		MembrosMaximoMeta.setDisplayName(coloredName(nomeMembrosMaximo));
		MembrosMaximoMeta.setLore(coloredLore(loreMembrosMaximo));
		if (glow) {
			MembrosMaximoMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			MembrosMaximoMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				MembrosMaximoMeta.addItemFlags(flag);
			}
		}
		MembrosMaximo.setDurability((short) dataMembrosMaximo);
		MembrosMaximo.setItemMeta(MembrosMaximoMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("MEMBROS_MAXIMO", MembrosMaximo);
		MEMBROS_MAXIMO = MembrosMaximo;
	}
	
	
	// Construtor do ItemStack do Lancador
	private static void buildLancador() {		
		// Pegando as configs do item
		int idLancador = config.getInt("Lancador.Id");
		int dataLancador = config.getInt("Lancador.Data");
		String nomeLancador = config.getString("Lancador.Nome");
		List<String> loreLancador = config.getStringList("Lancador.Lore");
		boolean glow = config.getBoolean("Lancador.Glow");
		boolean flags = config.getBoolean("Lancador.Flags");

		// Criando o ItemStack
		ItemStack Lancador = new ItemStack(idLancador);
		ItemMeta LancadorMeta = Lancador.getItemMeta();
		LancadorMeta.setDisplayName(coloredName(nomeLancador));
		LancadorMeta.setLore(coloredLore(loreLancador));
		if (glow) {
			LancadorMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			LancadorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				LancadorMeta.addItemFlags(flag);
			}
		}
		Lancador.setDurability((short) dataLancador);
		Lancador.setItemMeta(LancadorMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("LANCADOR", Lancador);
		LANCADOR = Lancador;
	}
	
	
	// Construtor do ItemStack do ReparadorInstantaneo
	private static void buildReparadorInstantaneo() {		
		// Pegando as configs do item
		int idReparador = config.getInt("Reparador.Id");
		int dataReparador = config.getInt("Reparador.Data");
		String nomeReparador = config.getString("Reparador.Nome");
		List<String> loreReparador = config.getStringList("Reparador.Lore");
		boolean glow = config.getBoolean("Reparador.Glow");
		boolean flags = config.getBoolean("Reparador.Flags");
		
		// Criando o ItemStack
		ItemStack Reparador = new ItemStack(idReparador);
		ItemMeta ReparadorMeta = Reparador.getItemMeta();
		ReparadorMeta.setDisplayName(coloredName(nomeReparador));
		ReparadorMeta.setLore(coloredLore(loreReparador));
		if (glow) {
			ReparadorMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			ReparadorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				ReparadorMeta.addItemFlags(flag);
			}
		}
		Reparador.setDurability((short) dataReparador);
		Reparador.setItemMeta(ReparadorMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("REPARADOR", Reparador);
		REPARADOR = Reparador;
	}
	
	
	// Construtor do ItemStack do Armadilha
	private static void buildArmadilha() {		
		// Pegando as configs do item
		int idArmadilha = 332; // ID da bola de neve
		String nomeArmadilha = config.getString("Armadilha.Nome");
		List<String> loreArmadilha = config.getStringList("Armadilha.Lore");
		boolean glow = config.getBoolean("Armadilha.Glow");
		
		// Criando o ItemStack
		ItemStack Armadilha = new ItemStack(idArmadilha);
		ItemMeta ArmadilhaMeta = Armadilha.getItemMeta();
		ArmadilhaMeta.setDisplayName(coloredName(nomeArmadilha));
		ArmadilhaMeta.setLore(coloredLore(loreArmadilha));
		if (glow) {
			ArmadilhaMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			ArmadilhaMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		Armadilha.setItemMeta(ArmadilhaMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("ARMADILHA", Armadilha);
		ARMADILHA = Armadilha;
	}
	
	
	// Construtor do ItemStack do RaioMestre
	private static void buildRaioMestre() {		
		// Pegando as configs do item
		int idRaioMestre = config.getInt("Raio_Mestre.Id");
		int dataRaioMestre = config.getInt("Raio_Mestre.Data");
		String nomeRaioMestre = config.getString("Raio_Mestre.Nome");
		List<String> loreRaioMestre = config.getStringList("Raio_Mestre.Lore");
		boolean glow = config.getBoolean("Raio_Mestre.Glow");
		boolean flags = config.getBoolean("Raio_Mestre.Flags");
		
		// Criando o ItemStack
		ItemStack RaioMestre = new ItemStack(idRaioMestre);
		ItemMeta RaioMestreMeta = RaioMestre.getItemMeta();
		RaioMestreMeta.setDisplayName(coloredName(nomeRaioMestre));
		RaioMestreMeta.setLore(coloredLore(loreRaioMestre));
		if (glow) {
			RaioMestreMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			RaioMestreMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				RaioMestreMeta.addItemFlags(flag);
			}
		}
		RaioMestre.setDurability((short) dataRaioMestre);
		RaioMestre.setItemMeta(RaioMestreMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("RAIO_MESTRE", RaioMestre);
		RAIO_MESTRE = RaioMestre;
	}
	
	
	// Construtor do ItemStack do PicaretaQuebraBedRock
	private static void buildPicaretaQuebraBedRock() {		
		// Pegando as configs do item
		int idPicareta = 278; // ID da picareta de dima
		String nomePicareta = config.getString("Picareta.Nome");
		boolean flags = config.getBoolean("Picareta.Flags");
		List<String> lorePicareta = config.getStringList("Picareta.Lore");
		
		// Criando o ItemStack
		ItemStack Picareta = new ItemStack(idPicareta);
		try
		{
			Class<?> CraftItemStackClass = ReflectionUtils.getOBClass("inventory.CraftItemStack");
			Class<?> NBTTagCompoundClass = ReflectionUtils.getNMSClass("NBTTagCompound");
			Class<?> ItemStackClass = ReflectionUtils.getNMSClass("ItemStack");
			Method asNMSCopy = CraftItemStackClass.getDeclaredMethod("asNMSCopy", Picareta.getClass());
			Method asBukkitCopy = CraftItemStackClass.getDeclaredMethod("asBukkitCopy", ItemStackClass);
			Method setBoolean = NBTTagCompoundClass.getDeclaredMethod("setBoolean", String.class, boolean.class);
			Method setTag = ItemStackClass.getDeclaredMethod("setTag", NBTTagCompoundClass);
			Method getItemMeta = CraftItemStackClass.getDeclaredMethod("getItemMeta", ItemStackClass);
			Method setItemMeta = CraftItemStackClass.getDeclaredMethod("setItemMeta", ItemStackClass, ItemMeta.class);
			Object NBTTagCompound = NBTTagCompoundClass.newInstance();
			Object CraftItemStack = asNMSCopy.invoke(null, Picareta);
			setBoolean.invoke(NBTTagCompound, "Unbreakable", true);
			setTag.invoke(CraftItemStack, NBTTagCompound);
			ItemMeta PicaretaMeta = (ItemMeta) getItemMeta.invoke(null, CraftItemStack);
			if (flags) {for (ItemFlag flag : ItemFlag.values()) {PicaretaMeta.addItemFlags(flag);}}
			PicaretaMeta.setDisplayName(coloredName(nomePicareta));
			PicaretaMeta.setLore(coloredLore(lorePicareta));
			PicaretaMeta.addEnchant(Enchantment.SILK_TOUCH, 2, true);
			setItemMeta.invoke(null, CraftItemStack, PicaretaMeta);
			Picareta = (ItemStack) asBukkitCopy.invoke(null, CraftItemStack);
		} 
		catch (NullPointerException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) 
		{
			e.printStackTrace();
		}
		
		// Setando o ItemStack
		NAME_ITENS.put("PICARETA", Picareta);
		PICARETA = Picareta;
	}
	
	
	// Construtor do ItemStack do TotemDaMorte
	private static void buildTotemDaMorte() {		
		// Pegando as configs do item
		int idTotemDaMorte = config.getInt("Totem_Da_Morte.Id");
		int dataTotemDaMorte = config.getInt("Totem_Da_Morte.Data");
		String nomeTotemDaMorte = config.getString("Totem_Da_Morte.Nome");
		List<String> loreTotemDaMorte = config.getStringList("Totem_Da_Morte.Lore");
		boolean glow = config.getBoolean("Totem_Da_Morte.Glow");
		boolean flags = config.getBoolean("Totem_Da_Morte.Flags");
		
		// Criando o ItemStack
		ItemStack TotemDaMorte = new ItemStack(idTotemDaMorte);
		ItemMeta TotemDaMorteMeta = TotemDaMorte.getItemMeta();
		TotemDaMorteMeta.setDisplayName(coloredName(nomeTotemDaMorte));
		TotemDaMorteMeta.setLore(coloredLore(loreTotemDaMorte));
		if (glow) {
			TotemDaMorteMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			TotemDaMorteMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				TotemDaMorteMeta.addItemFlags(flag);
			}
		}
		TotemDaMorte.setDurability((short) dataTotemDaMorte);
		TotemDaMorte.setItemMeta(TotemDaMorteMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("TOTEM", TotemDaMorte);
		TOTEM_DA_MORTE = TotemDaMorte;
	}
	
	
	// Construtor do ItemStack do AgrupadorDePocoes
	private static void buildAgrupadorDePocoes() {		
		// Pegando as configs do item
		int idAgrupadorDePocoes = config.getInt("Agrupador_De_Pocoes.Id");
		int dataAgrupadorDePocoes = config.getInt("Agrupador_De_Pocoes.Data");
		String nomeAgrupadorDePocoes = config.getString("Agrupador_De_Pocoes.Nome");
		List<String> loreAgrupadorDePocoes = config.getStringList("Agrupador_De_Pocoes.Lore");
		boolean glow = config.getBoolean("Agrupador_De_Pocoes.Glow");
		boolean flags = config.getBoolean("Agrupador_De_Pocoes.Flags");
		
		// Criando o ItemStack
		ItemStack AgrupadorDePocoes = new ItemStack(idAgrupadorDePocoes);
		ItemMeta AgrupadorDePocoesMeta = AgrupadorDePocoes.getItemMeta();
		AgrupadorDePocoesMeta.setDisplayName(coloredName(nomeAgrupadorDePocoes));
		AgrupadorDePocoesMeta.setLore(coloredLore(loreAgrupadorDePocoes));
		if (glow) {
			AgrupadorDePocoesMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			AgrupadorDePocoesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				AgrupadorDePocoesMeta.addItemFlags(flag);
			}
		}
		AgrupadorDePocoes.setDurability((short) dataAgrupadorDePocoes);
		AgrupadorDePocoes.setItemMeta(AgrupadorDePocoesMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("AGRUPADOR", AgrupadorDePocoes);
		AGRUPADOR = AgrupadorDePocoes;
	}
	
	
	// Construtor do ItemStack do DetectorDeSlimeChunk
	private static void buildDetectorDeSlimeChunk() {		
		// Pegando as configs do item
		int idDetectorDeSlimeChunk = config.getInt("Detector_De_SlimeChunk.Id");
		int dataDetectorDeSlimeChunk = config.getInt("Detector_De_SlimeChunk.Data");
		String nomeDetectorDeSlimeChunk = config.getString("Detector_De_SlimeChunk.Nome");
		List<String> loreDetectorDeSlimeChunk = config.getStringList("Detector_De_SlimeChunk.Lore");
		boolean glow = config.getBoolean("Detector_De_SlimeChunk.Glow");
		boolean flags = config.getBoolean("Detector_De_SlimeChunk.Flags");
		
		// Criando o ItemStack
		ItemStack DetectorDeSlimeChunk = new ItemStack(idDetectorDeSlimeChunk);
		ItemMeta DetectorDeSlimeChunkMeta = DetectorDeSlimeChunk.getItemMeta();
		DetectorDeSlimeChunkMeta.setDisplayName(coloredName(nomeDetectorDeSlimeChunk));
		DetectorDeSlimeChunkMeta.setLore(coloredLore(loreDetectorDeSlimeChunk));
		if (glow) {
			DetectorDeSlimeChunkMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			DetectorDeSlimeChunkMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		if (flags) {
			for (ItemFlag flag : ItemFlag.values()) {
				DetectorDeSlimeChunkMeta.addItemFlags(flag);
			}
		}
		DetectorDeSlimeChunk.setDurability((short) dataDetectorDeSlimeChunk);
		DetectorDeSlimeChunk.setItemMeta(DetectorDeSlimeChunkMeta);
		
		// Setando o ItemStack
		NAME_ITENS.put("DETECTOR", DetectorDeSlimeChunk);
		DETECTOR_DE_SLIMECHUNK = DetectorDeSlimeChunk;
	}
	
	
	private static void buildNewItens() {
		FileConfiguration nConfig = ConfigManager.getConfig("crie-novos-itens");
		Set<String> itens = nConfig.getConfigurationSection("Itens").getKeys(false);
		
		for (String item : itens) {
			// Pegando as configs do item
			int idItem = nConfig.getInt("Itens." + item + ".Id");
			int dataItem = nConfig.getInt("Itens." + item + ".Data");
			String nomeItem = nConfig.getString("Itens." + item + ".Nome");
			List<String> loreItem = nConfig.getStringList("Itens." + item + ".Lore");
			boolean glow = nConfig.getBoolean("Itens." + item + ".Glow");
			boolean flags = nConfig.getBoolean("Itens." + item + ".Flags");
			List<String> executa = nConfig.getStringList("Itens." + item + ".Executa");
			
			// Construindo ItemStack
			ItemStack ItemEspecial = new ItemStack(idItem);
			ItemMeta ItemEspecialMeta = ItemEspecial.getItemMeta();
			ItemEspecialMeta.setDisplayName(coloredName(nomeItem));
			ItemEspecialMeta.setLore(coloredLore(loreItem));
			if (glow) {
				ItemEspecialMeta.addEnchant(Enchantment.DURABILITY, 1, true);
				ItemEspecialMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			}
			if (flags) {
				for (ItemFlag flag : ItemFlag.values()) {
					ItemEspecialMeta.addItemFlags(flag);
				}
			}
			ItemEspecial.setDurability((short) dataItem);
			ItemEspecial.setItemMeta(ItemEspecialMeta);
			
			// Adicionando o item na HashMap
			NEW_ITENS.put(ItemEspecial, executa);
			NAME_ITENS.put(item.toUpperCase(), ItemEspecial);
		}
	}
	
	private static List<String> coloredLore(List<String> oldLore) {
		List<String> coloredLore = new ArrayList<>();
		for (String s : oldLore) {
			coloredLore.add(s.replace('&', '§'));
		}
		return coloredLore;
	}
	
	private static String coloredName(String oldName) {
		return oldName.replace('&', '§');
	}
}