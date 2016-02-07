
public class Config {
	private static String canalTwitch="";
	private static String oAuth="";
	private static int puntos=0;
	private static String usuarioTeamspeak="";
	private static String contraseniaTeamspeak="";
	private static String servidorTeamspeak="";
	private static int puertoTeamspeak=9987;
	
	public static String getServidorTeamspeak() {
		return servidorTeamspeak;
	}
	public static void setServidorTeamspeak(String servidorTeamspeak) {
		Config.servidorTeamspeak = servidorTeamspeak;
	}
	public static int getPuertoTeamspeak() {
		return puertoTeamspeak;
	}
	public static void setPuertoTeamspeak(int puertoTeamspeak) {
		Config.puertoTeamspeak = puertoTeamspeak;
	}
	public static String getCanalTwitch() {
		return canalTwitch;
	}
	public static void setCanalTwitch(String canalTwitch) {
		Config.canalTwitch = canalTwitch;
	}
	public static String getoAuth() {
		return oAuth;
	}
	public static void setoAuth(String oAuth) {
		Config.oAuth = oAuth;
	}
	public static int getPuntos() {
		return puntos;
	}
	public static void setPuntos(int puntos) {
		Config.puntos = puntos;
	}
	public static String getUsuarioTeamspeak() {
		return usuarioTeamspeak;
	}
	public static void setUsuarioTeamspeak(String usuarioTeamspeak) {
		Config.usuarioTeamspeak = usuarioTeamspeak;
	}
	public static String getContraseniaTeamspeak() {
		return contraseniaTeamspeak;
	}
	public static void setContraseniaTeamspeak(String contraseniaTeamspeak) {
		Config.contraseniaTeamspeak = contraseniaTeamspeak;
	}

}
