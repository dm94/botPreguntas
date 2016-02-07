import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class GestionFicheros {

	public void guardarPreguntasRespuestas(String fichero,ArrayList<PreguntaResp> preguntas){
		if(!(fichero.length()>0)){
			fichero="metodoguardar.txt";
		}
		File f=new File(fichero);
		try {
			if(!f.exists()){
				f.createNewFile();
			}
			
			FileWriter fw=new FileWriter(fichero);
			PrintWriter pw=new PrintWriter(fw);
			
			for(int i=0;i<preguntas.size();i++){
				pw.println(preguntas.get(i).getPregunta()+" : "+preguntas.get(i).getRespuesta());
			}
			
			fw.close();
			pw.close();

		} catch (Exception e) {
			Intermediario.aniadirError(e.getMessage());
		}
	}
	
	public ArrayList<PreguntaResp> leerPreguntasRespuestas(String fichero){
		if(!(fichero.length()>0)){
			fichero="preguntas1.txt";
		}
		ArrayList<PreguntaResp> preguntas=new ArrayList<PreguntaResp>();
		
		File f=new File(fichero);
		FileReader fr=null;
		BufferedReader br=null;
		try {
			
			if(!f.exists()){
				f.createNewFile();
				Intermediario.aniadirError("Se ha creado el fichero");
			}
			
			fr = new FileReader(fichero);
			br = new BufferedReader(fr);
			
			String cad=null;
			StringTokenizer st=null;
			
			while((cad=br.readLine())!=null){
				st=new StringTokenizer(cad, ":");
				String pregunta=st.nextToken().trim();
				String respuesta=st.nextToken().trim();
				Intermediario.aniadirError("Pregunta añadida "+pregunta+"  respuesta: "+respuesta);
				preguntas.add(new PreguntaResp(pregunta,respuesta));
				if(st.hasMoreTokens()){
					Intermediario.aniadirError("Demasiados elementos en el fichero de preguntas");
				}	
			}
		
			br.close();
			fr.close();
		} catch (Exception e) {
			Intermediario.aniadirError(e.getMessage());
		}
		
		return preguntas;
	}
	
	public static ArrayList<String> sacarFicherosDePreguntas(){
		ArrayList<String> todosLosFicheros=new ArrayList<String>();
		File dir=new File("./");
		
		File[] ficheros=dir.listFiles();
		
		if(ficheros.length==0){
			File f=new File("preguntas.txt");
			try {
				f.createNewFile();
			} catch (IOException e) {
				Intermediario.aniadirError("El fichero no se ha podido crear");
				todosLosFicheros.add("preguntas.txt");
			}
		}
		
		for(int i=0;i<ficheros.length;i++){
			todosLosFicheros.add(ficheros[i].getName());
		}
		
		return todosLosFicheros;
	}
	
	public static void guardarConfig(){
		File f=new File("config.txt");
		FileWriter fw=null;
		PrintWriter pw=null;
		try {
			
			if(!f.exists()){
				f.createNewFile();
			}
			
			fw=new FileWriter(f);
			pw=new PrintWriter(fw);
			
			pw.println("canaltwitch # "+Config.getCanalTwitch());
			pw.println("oauth # "+Config.getoAuth());
			pw.println("puntos # "+String.valueOf(Config.getPuntos()));
			pw.println("usuariots3 # "+Config.getUsuarioTeamspeak());
			pw.println("contrats3 # "+Config.getContraseniaTeamspeak());
			pw.println("servidorts3 # "+Config.getServidorTeamspeak());
			pw.println("puertots3 # "+Config.getPuertoTeamspeak());
			
			fw.close();
			
		} catch (Exception e) {
			Intermediario.aniadirError("El fichero de configuración no se ha podido crear");
		}finally{
			pw.close();
		}
		
		
	}
	
	public static void leerConfig(){
		File f=new File("config.txt");
		try {
			
			if(!f.exists()){
				f.createNewFile();
			}
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			String cad=null;
			StringTokenizer st=null;
			
			while((cad=br.readLine())!=null){
				st=new StringTokenizer(cad, "#");
				st.nextToken();
				String dato=st.nextToken().trim();
				
				if(cad.contains("canaltwitch")){
					Config.setCanalTwitch(dato);
				}else if(cad.contains("oauth")){
					Config.setoAuth(dato);
				}else if(cad.contains("puntos")){
					Config.setPuntos(Integer.parseInt(dato));
				}else if(cad.contains("usuariots3")){
					Config.setUsuarioTeamspeak(dato);
				}else if(cad.contains("contrats3")){
					Config.setContraseniaTeamspeak(dato);
				}else if(cad.contains("servidorts3")){
					Config.setServidorTeamspeak(dato);
				}else if(cad.contains("puertots3")){
					Config.setPuertoTeamspeak(Integer.parseInt(dato));
				}
				
				if(st.hasMoreTokens()){
					Intermediario.aniadirError("Demasiados elementos en el fichero de preguntas");
				}	
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			Intermediario.aniadirError("El fichero de configuración no se ha podido crear");
		}
	}
}
