
public class PreguntaResp {
	private String pregunta;
	private String respuesta;
	
	public PreguntaResp(String preg,String respu){
		this.pregunta=preg;
		this.respuesta=respu;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
