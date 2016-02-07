
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.jibble.pircbot.*;

/*
 * Esta Clase es el bot una vez iniciada siempre esta activada, se encarga de leer el chat y decidir que hacer
 * en funcion del comando que envien por el chat.
 */

public class TwitchBot extends PircBot{
	private static String canal;
	private static int i=0;
	private static String pregunta;
	private static String respuesta;
	
	
	//Para que cambien los !comandos solo hace falta que esos comandos no llegen a estar definidos.
	
	public TwitchBot(String canal){
		this.setName(canal);
		this.canal=canal;
		pregunta="";
		respuesta="";
	}
	
	public void cambiarPreguntas(String preg,String resp){
		pregunta=preg;
		respuesta=resp;
	}

	public TwitchBot(String nombre, String adminis){
		this.setName(nombre);
		this.canal=nombre;
	}
	
	public static String getCanal(){
		return canal;
	}
	
	public void onMessage(String channel,String sender, String login, String hostname, String message) {
		
		if(message.equalsIgnoreCase("!prueba")){
			Bot.mandarMensaje(canal, "Estoy conectado");
		}else if(message.contains("!ans")){
			Bot.validarPregunta(message.toLowerCase(),sender);
		}
			
	}

	public void setPregunta(String pregunta,String respuesta) {
		TwitchBot.pregunta = pregunta;
		TwitchBot.respuesta = respuesta;
	}
	
}
