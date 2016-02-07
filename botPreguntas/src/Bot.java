

import java.io.IOException;
import java.util.ArrayList;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

public class Bot {
	
	private static String canal="";
	private static String oAuth="";
	private static TwitchBot bot;
	private static ArrayList<PreguntaResp> preguntas;
	private static String preguntaActual="";
	private static String respuestaActual="";
	private static int nPregunta=0;
	private static int nMaxPreguntas=0;
	
	public static void crearBot(String canalHoster,String oauth) throws NickAlreadyInUseException, IOException, IrcException{
		bot= new TwitchBot(canalHoster);
		bot.setVerbose(true);
		bot.connect("irc.twitch.tv" ,6667,oauth);
		bot.joinChannel("#"+canalHoster);
		canal=canalHoster;
		preguntas=Intermediario.getPreguntas();
		nMaxPreguntas=preguntas.size();
		cambiarPregunta();
		
	}
	
	public static void mandarMensaje(String canalHoster,String mensaje){
		bot.sendMessage("#"+canalHoster,mensaje);
	}

	public static void pararBot(){
		bot.disconnect();
	}
	
	public static void cambiarPregunta(){
		if(nPregunta<nMaxPreguntas){
			preguntaActual=preguntas.get(nPregunta).getPregunta();
			respuestaActual=preguntas.get(nPregunta).getRespuesta();
			bot.setPregunta(preguntaActual, respuestaActual);
			mandarMensaje(canal, preguntaActual);
			nPregunta++;
		}else{
			mandarMensaje(canal, "Ya no hay más preguntas para responder");
			nPregunta=0;
		}
	}
	
	public static void validarPregunta(String resp,String usuario){
		if(resp.contains(respuestaActual)){
			mandarMensaje(canal, String.format("Felicidades %s has acertado la respuesta", usuario));
			cambiarPregunta();
			Intermediario.aniadirPuntosAUsuario(usuario);
		}
	}
}
