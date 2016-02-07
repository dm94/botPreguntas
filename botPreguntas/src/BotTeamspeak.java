import java.util.ArrayList;
import java.util.logging.Level;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.TS3Query.FloodRate;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDeletedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelPasswordChangedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ServerEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;


public class BotTeamspeak extends Thread{
	private static String usuario="";
	private static String contrasenia="";
	private static String direccion="";
	private static int canalTS=0;
	private static int puerto=0;
	private static ArrayList<PreguntaResp> preguntas;
	private static String preguntaActual="";
	private static String respuestaActual="";
	private static int nPregunta=0;
	private static int nMaxPreguntas=0;
	private static TS3Api api;
	
	public BotTeamspeak(int canal,ArrayList<PreguntaResp> preg){
		this.canalTS=canal;
		this.preguntas=preg;
	}
	
	public void run(){
		usuario=Config.getUsuarioTeamspeak();
		contrasenia=Config.getContraseniaTeamspeak();
		direccion=Config.getServidorTeamspeak();
		puerto=Config.getPuertoTeamspeak();
		
		 final TS3Config config = new TS3Config(); 
		 config.setQueryPort(10011);
		 config.setHost(direccion);
	     config.setDebugLevel(Level.WARNING);
	     if(usuario.length()>0 && contrasenia.length()>0){
	    	 config.setLoginCredentials(usuario, contrasenia);
	     }
	     config.setFloodRate(FloodRate.DEFAULT);
	     	     
	     final TS3Query query = new TS3Query(config);
	     
	     query.connect();
	     
	     api = query.getApi();
	     api.selectVirtualServerByPort(puerto);
	     api.setNickname("BotPreguntas");
	     api.moveClient(canalTS);
	     
	     api.registerAllEvents();
	     api.sendChannelMessage(preguntaActual);
	     
	     api.addTS3Listeners(new TS3Listener() {

			@Override
			public void onChannelCreate(ChannelCreateEvent arg0) {
			}

			@Override
			public void onChannelDeleted(ChannelDeletedEvent arg0) {
			}

			@Override
			public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent arg0) {
			}

			@Override
			public void onChannelEdit(ChannelEditedEvent arg0) {
			}

			@Override
			public void onChannelMoved(ChannelMovedEvent arg0) {
			}

			@Override
			public void onChannelPasswordChanged(ChannelPasswordChangedEvent arg0) {
			}

			@Override
			public void onClientJoin(ClientJoinEvent arg0) {
			}

			@Override
			public void onClientLeave(ClientLeaveEvent arg0) {
			}

			@Override
			public void onClientMoved(ClientMovedEvent arg0) {
			}

			@Override
			public void onServerEdit(ServerEditedEvent arg0) {
			}

			@Override
			public void onTextMessage(TextMessageEvent arg0) {
				if(arg0.getMessage().contains("!preg")){
					api.sendChannelMessage(preguntaActual);
				}
				if(arg0.getMessage().contains("!ans")){
					validarPregunta(arg0.getMessage().toLowerCase(),arg0.getInvokerName());
				}
				if(arg0.getMessage().contains("!help")){
					api.sendChannelMessage("Tienes que poner !ans + la respuesta");
				}
				
			}
	    	 
	     });
	     
	}
	
	private static void cambiarPregunta(){
		if(nPregunta<nMaxPreguntas){
			preguntaActual=preguntas.get(nPregunta).getPregunta().trim();
			respuestaActual=preguntas.get(nPregunta).getRespuesta().trim();
			api.sendChannelMessage(preguntaActual);
			nPregunta++;
		}else{
			api.sendChannelMessage("Ya no hay más preguntas para responder");
			nPregunta=0;
		}
	}
	
	private static void validarPregunta(String resp,String usuario){
		if(resp.contains(respuestaActual)){
			api.sendChannelMessage(String.format("Felicidades %s has acertado la respuesta", usuario));
			cambiarPregunta();
			Intermediario.aniadirPuntosAUsuario(usuario);
		}
	}
}
