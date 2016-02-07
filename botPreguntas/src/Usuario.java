
public class Usuario {
	private String nombreUsuario;
	private int puntos;
	
	public Usuario(String nombre, int puntos){
		this.nombreUsuario=nombre;
		this.puntos=puntos;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getPuntos() {
		return puntos;
	}

	public void addPuntos(int puntos) {
		this.puntos = this.puntos+puntos;
	}
	
	
	

}
