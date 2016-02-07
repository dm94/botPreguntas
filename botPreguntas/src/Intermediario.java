
import java.util.ArrayList;


public class Intermediario {
	private static ArrayList<PreguntaResp> preguntas;
	private static ArrayList<Usuario> usuarios;
	private static String fichero="";
	private static String canalAConectarse="";
	private static int puntos=0;
	private static GestionFicheros gf;
	private static boolean botTeamspeakEncendido=false;
	private static boolean botTwitchEncendido=false;
	private static BotTeamspeak botTS3;
	
	public static void encenderBotTeamspeak(String fich,int canal){
		if(!botTeamspeakEncendido && !botTwitchEncendido){
			fichero=fich;
			puntos=Config.getPuntos();
			iniciarTodo();
			botTeamspeakEncendido=true;
			
			try {
				botTS3=new BotTeamspeak(canal,preguntas);
				botTS3.start();
			} catch (Exception e) {
				aniadirError(e.getMessage());
			}
		}
		
	}
	
	public static void encenderBotTwitch(String fich){
		if(!botTeamspeakEncendido && !botTwitchEncendido){
			fichero=fich;
			canalAConectarse=Config.getCanalTwitch();
			puntos=Config.getPuntos();
			iniciarTodo();
			botTwitchEncendido=true;
			try {
				Bot.crearBot(canalAConectarse,Config.getoAuth());
			} catch (Exception e) {
				aniadirError(e.getMessage());
			}
		}
	}
	
	public static void iniciar(String fich){
		fichero=fich;
		preguntas=new ArrayList<PreguntaResp>();
		usuarios=new ArrayList<Usuario>();
		gf=new GestionFicheros();
		preguntas=gf.leerPreguntasRespuestas(fichero);
		
		actualizarListas();
	}
	
	private static void iniciarTodo(){
		preguntas=gf.leerPreguntasRespuestas(fichero);
		
		actualizarListas();
		gf.guardarConfig();
	}
	
	public static void apagarBot(){
		gf.guardarPreguntasRespuestas(fichero, preguntas);
		gf.guardarConfig();
		
		if(botTwitchEncendido){
			Bot.pararBot();
		}else if(botTeamspeakEncendido){
			botTS3.stop();
		}
	}
	
	private static void actualizarListas(){
		Principal.actualizarListas(usuarios, preguntas);
	}
	
	public static void aniadirError(String error){
		Principal.actualizarListaErrores(error);
	}
	
	public static void aniadirPreguntaResp(String pregunta, String resp){
		preguntas.add(new PreguntaResp(pregunta,resp));
		gf.guardarPreguntasRespuestas(fichero, preguntas);
		actualizarListas();
	}
	
	public static void aniadirPuntosAUsuario(String usuario){
		if(estaUsuario(usuario)){
			for(int i=0;i<usuarios.size();i++){
				if(usuarios.get(i).getNombreUsuario().equalsIgnoreCase(usuario)){
					usuarios.get(i).addPuntos(puntos);
				}
			}
		}else{
			usuarios.add(new Usuario(usuario.trim(),puntos));
		}
		actualizarListas();
	}
	private static boolean estaUsuario(String usuario){
		boolean estaUsuario=false;
		
		for(int i=0;i<usuarios.size();i++){
			if(usuarios.get(i).getNombreUsuario().equalsIgnoreCase(usuario)){
				estaUsuario=true;
			}
		}
		
		return estaUsuario;
	}

	public static ArrayList<PreguntaResp> getPreguntas() {
		return preguntas;
	}
	
	
	
}
