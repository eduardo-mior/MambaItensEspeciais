package rush.itensespeciais.config;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import rush.itensespeciais.utils.ConfigManager;

public abstract class Config {

	public static int 
	                  MAX_STACK_POTION, LIMITE_PODER, LIMITE_MEMBROS, COOLDOWN_TEIA, 
					  CAMADA_MINIMA, CREEPER_POWER, BOLA_POWER;
	
	public static String 
					  DETECTOR_ESTA, DETECTOR_NAOESTA, PODER_INSTANTANEO_ERRO, PODER_MAXIMO_ERRO, 
					  MEMBROS_MAXIMO_ERRO, PODER_INSTANTANEO_USADO, PODER_MAXIMO_USADO, ZONA_LIVRE_ERRO,
					  MEMBROS_MAXIMO_USADO, RESETKDR_ERRO, RESETKDR_SUCESSO, NUMERO_INVALIDO,
					  PLAYER_INVALIDO, ESPECIAL_INCORRETO, ITEM_DESCONHECIDO, ITEM_ENVIADO, 
					  PURIFICADOR_TITLE, PURIFICADOR_SUBTITLE, INCINERADOR_TITLE, INCIDERADOR_SUBTITLE;
	
	public static Sound RAIO, BIGORNA;
	public static boolean REALISTIC, OBSIDIAN_BREAKER, MCMMO;

	public static void loadConfig() {
		FileConfiguration config = ConfigManager.getConfig("config");
		FileConfiguration mensagens = ConfigManager.getConfig("mensagens");
		REALISTIC = config.getBoolean("Picareta.PicaretaRealista");
		MCMMO = config.getBoolean("McMMO");
		OBSIDIAN_BREAKER = config.getBoolean("ObsidianBreaker");
		MAX_STACK_POTION = config.getInt("Agrupador_De_Pocoes.LimiteDePocoesQuePodemSeAgrupar");
		LIMITE_PODER = config.getInt("Poder_Maximo.LimiteMaximoDePoder");
		LIMITE_MEMBROS = config.getInt("Membros_Maximo.LimiteMaximoDeMembros");
		COOLDOWN_TEIA = config.getInt("Armadilha.DuracaoDasTeias");
		CAMADA_MINIMA = config.getInt("Picareta.CamadaMinima");
		CREEPER_POWER = config.getInt("Super_Creeper.DanoDaExplosaoPorBloco");
		BOLA_POWER = config.getInt("Bola_de_Fogo.DanoDaExplosaoPorBloco");
		DETECTOR_ESTA = mensagens.getString("Detector_De_SlimeChunk.Esta").replace('&', '§');
		DETECTOR_NAOESTA = mensagens.getString("Detector_De_SlimeChunk.NaoEsta").replace('&', '§');
		PODER_INSTANTANEO_ERRO = mensagens.getString("Poder_Instantaneo.LimiteAtingido").replace('&', '§');
		PODER_MAXIMO_ERRO = mensagens.getString("Poder_Maximo.LimiteAtingido").replace('&', '§');
		MEMBROS_MAXIMO_ERRO = mensagens.getString("Membros_Maximo.LimiteAtingido").replace('&', '§');
		PODER_INSTANTANEO_USADO = mensagens.getString("Poder_Instantaneo.UsadoComSucesso").replace('&', '§');
		PODER_MAXIMO_USADO = mensagens.getString("Poder_Maximo.UsadoComSucesso").replace('&', '§');
		MEMBROS_MAXIMO_USADO = mensagens.getString("Membros_Maximo.UsadoComSucesso").replace('&', '§');
		ZONA_LIVRE_ERRO = mensagens.getString("Membros_Maximo.ZonaLivreErro").replace('&', '§');
		RESETKDR_ERRO = mensagens.getString("Reset_KDR.JaEstaResetado").replace('&', '§');
		RESETKDR_SUCESSO = mensagens.getString("Reset_KDR.ResetadoComSucesso").replace('&', '§');
		INCINERADOR_TITLE = mensagens.getString("Incinerador.Title").replace('&', '§');
		INCIDERADOR_SUBTITLE = mensagens.getString("Incinerador.Subtitle").replace('&', '§');
		PURIFICADOR_TITLE = mensagens.getString("Purificador.Title").replace('&', '§');
		PURIFICADOR_SUBTITLE = mensagens.getString("Purificador.Subtitle").replace('&', '§');
		NUMERO_INVALIDO = mensagens.getString("Comando.Numero-Invalido").replace('&', '§');
		PLAYER_INVALIDO = mensagens.getString("Comando.Player-Invalido").replace('&', '§');
		ESPECIAL_INCORRETO = mensagens.getString("Comando.ComandoIncorreto").replace('&', '§');
		ITEM_DESCONHECIDO = mensagens.getString("Comando.ItemDesconhecido").replace('&', '§');
		ITEM_ENVIADO = mensagens.getString("Comando.ItemEnviado").replace('&', '§');
		try {
			RAIO = Sound.valueOf("AMBIENCE_THUNDER");
			BIGORNA = Sound.valueOf("ANVIL_USE");
		} catch (IllegalArgumentException ex) {
			RAIO = Sound.valueOf("ENTITY_LIGHTNING_THUNDER");
			BIGORNA = Sound.valueOf("BLOCK_ANVIL_USE");
		}
	}

}
