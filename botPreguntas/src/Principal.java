

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;

public class Principal {

	private JFrame frmBotpreguntasBydmdani;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private static JTextField txtPuntos;
	private static Intermediario inter;
	private static DefaultListModel listaPreguntas = new DefaultListModel();
	private static DefaultListModel listaUsuarios = new DefaultListModel();
	private static DefaultListModel listaErrores = new DefaultListModel();
	private static JList listPreguntas;
	private static JList listUsuarios;
	private static JList listErrores;
	private boolean estaEncendido=false;
	private static JTextField txtRespuesta;
	private static JTextField txtPregunta;
	private static JComboBox cbFicheroPreguntas;
	private static JComboBox cbPlataforma;
	private static JTextField txtUsuarioTwitch;
	private static JTextField txtoAuth;
	private static JPanel pTwitch;
	private static JTextField txtUsuarioTS3;
	private static JTextField txtContraTS3;
	private static JTextField txtServidorTS3;
	private static JTextField txtPuertoTS3;
	private static JTextField txtIDCanalTS3;
	private static JPanel pTeamspeak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmBotpreguntasBydmdani.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
		inter=new Intermediario();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBotpreguntasBydmdani = new JFrame();
		frmBotpreguntasBydmdani.setResizable(false);
		frmBotpreguntasBydmdani.setTitle("BotPreguntas by @Dm94Dani");
		frmBotpreguntasBydmdani.setBounds(100, 100, 543, 365);
		frmBotpreguntasBydmdani.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBotpreguntasBydmdani.getContentPane().setLayout(null);
		tabbedPane.setBounds(0, 0, 537, 336);
		frmBotpreguntasBydmdani.getContentPane().add(tabbedPane);
		
		JPanel pConfiguracion = new JPanel();
		tabbedPane.addTab("Configuracion", null, pConfiguracion, null);
		pConfiguracion.setLayout(null);
		
		JLabel lblElijeElFichero = new JLabel("Elije el fichero de preguntas a usar");
		lblElijeElFichero.setBounds(62, 253, 226, 14);
		pConfiguracion.add(lblElijeElFichero);
		
		JButton btnPararBot = new JButton("Parar Bot");
		btnPararBot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(estaEncendido){
					inter.apagarBot();
					estaEncendido=false;
				}
			}
		});
		btnPararBot.setBounds(332, 11, 139, 23);
		pConfiguracion.add(btnPararBot);
		
		JLabel lblPuntosPorAcertar = new JLabel("Puntos por acertar respuesta");
		lblPuntosPorAcertar.setBounds(62, 228, 188, 14);
		pConfiguracion.add(lblPuntosPorAcertar);
		
		txtPuntos = new JTextField();
		txtPuntos.setBounds(262, 225, 209, 20);
		pConfiguracion.add(txtPuntos);
		txtPuntos.setColumns(10);
		
		cbFicheroPreguntas = new JComboBox();
		cbFicheroPreguntas.setBounds(262, 250, 209, 20);
		pConfiguracion.add(cbFicheroPreguntas);
		
		cbPlataforma = new JComboBox();
		cbPlataforma.setBounds(332, 64, 139, 20);
		pConfiguracion.add(cbPlataforma);
		cbPlataforma.addItem("Twitch");
		cbPlataforma.addItem("Teamspeak");
		
		JLabel lblEligeLaPlataforma = new JLabel("Elige la plataforma a la que se conecta el bot:");
		lblEligeLaPlataforma.setBounds(20, 66, 293, 17);
		pConfiguracion.add(lblEligeLaPlataforma);
		
		
		pTwitch = new JPanel();
		pTwitch.setBackground(SystemColor.controlHighlight);
		pTwitch.setBounds(56, 128, 415, 86);
		pConfiguracion.add(pTwitch);
		pTwitch.setLayout(null);
		
		JLabel lblUsuarioTwitch = new JLabel("Usuario Twitch:");
		lblUsuarioTwitch.setBounds(20, 11, 182, 14);
		pTwitch.add(lblUsuarioTwitch);
		
		txtUsuarioTwitch = new JTextField();
		txtUsuarioTwitch.setBounds(202, 8, 203, 20);
		pTwitch.add(txtUsuarioTwitch);
		txtUsuarioTwitch.setColumns(10);
		
		JLabel lblOautch = new JLabel("oAuth:");
		lblOautch.setBounds(20, 34, 46, 14);
		pTwitch.add(lblOautch);
		
		JButton btnElegirPlataforma = new JButton("Elegir");
		btnElegirPlataforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbPlataforma.getSelectedItem().toString().equalsIgnoreCase("twitch")){
					pTwitch.setVisible(true);
					pTeamspeak.setVisible(false);
				}else if(cbPlataforma.getSelectedItem().toString().equalsIgnoreCase("teamspeak")){
					pTwitch.setVisible(false);
					pTeamspeak.setVisible(true);
				}
				sacarDatosGuardados();
			}
		});
		btnElegirPlataforma.setBounds(217, 94, 89, 23);
		pConfiguracion.add(btnElegirPlataforma);
		
		pTeamspeak = new JPanel();
		pTeamspeak.setBackground(SystemColor.controlHighlight);
		pTeamspeak.setBounds(20, 125, 451, 92);
		pConfiguracion.add(pTeamspeak);
		pTeamspeak.setLayout(null);
		pTeamspeak.setVisible(false);
		
		JLabel lblUsuarioTs = new JLabel("Usuario TS3: ");
		lblUsuarioTs.setBounds(10, 11, 110, 14);
		pTeamspeak.add(lblUsuarioTs);
		
		txtUsuarioTS3 = new JTextField();
		txtUsuarioTS3.setBounds(130, 8, 94, 20);
		pTeamspeak.add(txtUsuarioTS3);
		txtUsuarioTS3.setColumns(10);
		
		JLabel lblContraseaTs = new JLabel("Contrase\u00F1a TS3:");
		lblContraseaTs.setBounds(10, 36, 110, 14);
		pTeamspeak.add(lblContraseaTs);
		
		txtContraTS3 = new JTextField();
		txtContraTS3.setBounds(130, 33, 94, 20);
		pTeamspeak.add(txtContraTS3);
		txtContraTS3.setColumns(10);
		
		JLabel lblServidorTs = new JLabel("Servidor TS3:");
		lblServidorTs.setBounds(10, 61, 110, 20);
		pTeamspeak.add(lblServidorTs);
		
		txtServidorTS3 = new JTextField();
		txtServidorTS3.setBounds(130, 61, 94, 20);
		pTeamspeak.add(txtServidorTS3);
		txtServidorTS3.setColumns(10);
		
		JLabel lblPuertoTs = new JLabel("Puerto TS3:");
		lblPuertoTs.setBounds(234, 11, 85, 14);
		pTeamspeak.add(lblPuertoTs);
		
		txtPuertoTS3 = new JTextField();
		txtPuertoTS3.setBounds(329, 8, 112, 20);
		pTeamspeak.add(txtPuertoTS3);
		txtPuertoTS3.setColumns(10);
		
		JLabel lblIdCanal = new JLabel("ID Canal:");
		lblIdCanal.setBounds(234, 36, 72, 14);
		pTeamspeak.add(lblIdCanal);
		
		txtIDCanalTS3 = new JTextField();
		txtIDCanalTS3.setBounds(329, 33, 112, 20);
		pTeamspeak.add(txtIDCanalTS3);
		txtIDCanalTS3.setColumns(10);
		
		txtoAuth = new JTextField();
		txtoAuth.setBounds(202, 31, 203, 20);
		pTwitch.add(txtoAuth);
		txtoAuth.setColumns(10);
		
		JButton btnGenerarOauth = new JButton("Generar oAuth");
		btnGenerarOauth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desktop enlace=Desktop.getDesktop();
				try {
					enlace.browse(new URI("https://twitchapps.com/tmi/"));
				} catch (IOException e) {
				} catch (URISyntaxException e) {
				}
			}
		});
		btnGenerarOauth.setBounds(144, 55, 127, 20);
		pTwitch.add(btnGenerarOauth);
		
		JPanel pPreguntas = new JPanel();
		tabbedPane.addTab("Preguntas", null, pPreguntas, null);
		pPreguntas.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 532, 198);
		pPreguntas.add(scrollPane);
		
		listPreguntas = new JList();
		scrollPane.setViewportView(listPreguntas);
		
		JLabel label = new JLabel("Adici\u00F3n de nuevas preguntas");
		label.setBounds(10, 213, 209, 14);
		pPreguntas.add(label);
		
		JButton bAniadir = new JButton("A\u00F1adir");
		bAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					String pregunta=txtPregunta.getText();
					String respuesta=txtRespuesta.getText();
					
					if(pregunta.length()>0 && respuesta.length()>0){
						Intermediario.aniadirPreguntaResp(pregunta, respuesta);
						txtPregunta.setText("");
						txtRespuesta.setText("");
					}else{
						actualizarListaErrores("El campo de respuesta o pregunta esta vacio");
					}

			}
		});
		bAniadir.setBounds(429, 256, 89, 23);
		pPreguntas.add(bAniadir);
		
		JLabel label_1 = new JLabel("Respuesta");
		label_1.setBounds(263, 238, 86, 14);
		pPreguntas.add(label_1);
		
		txtRespuesta = new JTextField();
		txtRespuesta.setColumns(10);
		txtRespuesta.setBounds(263, 257, 156, 20);
		pPreguntas.add(txtRespuesta);
		
		JLabel label_2 = new JLabel("Pregunta");
		label_2.setBounds(10, 238, 86, 14);
		pPreguntas.add(label_2);
		
		txtPregunta = new JTextField();
		txtPregunta.setColumns(10);
		txtPregunta.setBounds(10, 257, 226, 20);
		pPreguntas.add(txtPregunta);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inter.iniciar(String.valueOf(cbFicheroPreguntas.getSelectedItem()));
			}
		});
		btnCargar.setBounds(429, 209, 89, 23);
		pPreguntas.add(btnCargar);
		
		JPanel pUsuarios = new JPanel();
		tabbedPane.addTab("Usuarios", null, pUsuarios, null);
		pUsuarios.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 532, 308);
		pUsuarios.add(scrollPane_1);
		
		listUsuarios = new JList();
		scrollPane_1.setViewportView(listUsuarios);
		
		JPanel pConsola = new JPanel();
		tabbedPane.addTab("Consola", null, pConsola, null);
		pConsola.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 532, 258);
		pConsola.add(scrollPane_2);
		
		listErrores = new JList();
		scrollPane_2.setViewportView(listErrores);
		
		JButton bLimpiarConsola = new JButton("Limpiar Consola");
		bLimpiarConsola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaErrores.clear();
				listErrores.setModel(listaErrores);
			}
		});
		bLimpiarConsola.setBounds(142, 269, 265, 23);
		pConsola.add(bLimpiarConsola);
		
		JButton btnIniciarBot = new JButton("Iniciar Bot");
		btnIniciarBot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!estaEncendido){
					if(txtPuntos.getText().length()>0){
						String fichero="";
						if(cbFicheroPreguntas.getItemCount()>0){
							fichero=String.valueOf(cbFicheroPreguntas.getSelectedItem());
						}
						int puntos=Integer.parseInt(txtPuntos.getText());
						
						Config.setPuntos(puntos);
						
						if(cbPlataforma.getSelectedItem().toString().equalsIgnoreCase("twitch")){
							String usuario=txtUsuarioTwitch.getText();
							String oAuth=txtoAuth.getText();
							if(usuario.length()>0 && oAuth.length()>0){
								Config.setCanalTwitch(usuario);
								Config.setoAuth(oAuth);
								if(fichero.length()<0){
									fichero="preguntasale.txt";
								}
								inter.encenderBotTwitch(fichero);
								estaEncendido=true;
							}
						}else if(cbPlataforma.getSelectedItem().toString().equalsIgnoreCase("teamspeak")){
							String usuarioTS3=txtUsuarioTS3.getText();
							String contraTS3=txtContraTS3.getText();
							String servidorTS3=txtServidorTS3.getText();
							int puertoTS3=Integer.parseInt(txtPuertoTS3.getText());
							int idCanal=Integer.parseInt(txtIDCanalTS3.getText());
							if(servidorTS3.length()>0 && puertoTS3>0 && idCanal>0){
								Config.setServidorTeamspeak(servidorTS3);
								Config.setContraseniaTeamspeak(contraTS3);
								Config.setUsuarioTeamspeak(usuarioTS3);
								Config.setPuertoTeamspeak(puertoTS3);
								
								inter.encenderBotTeamspeak(fichero, idCanal);
							}
						}
					}
				}else{
					actualizarListaErrores("El bot ya esta encendido");
				}
			}
		});
		btnIniciarBot.setBounds(62, 11, 128, 23);
		pConfiguracion.add(btnIniciarBot);
		cbFicheroPreguntas.addItem("ficheroPreguntas.txt");
		
		rellenarCB();
		inter.iniciar(String.valueOf(cbFicheroPreguntas.getSelectedItem()));
	}
	
	public static void rellenarCB(){
		ArrayList<String> ficheros=new ArrayList<String>();
		ficheros=GestionFicheros.sacarFicherosDePreguntas();
		
		for(int i=0;i<ficheros.size();i++){
			cbFicheroPreguntas.addItem(ficheros.get(i));
		}
	}
	
	public static void actualizarListas(ArrayList<Usuario> usuarios, ArrayList<PreguntaResp> preguntas){
		listaPreguntas.clear();
		listaUsuarios.clear();
		
		for(int i=0;i<usuarios.size();i++){
			listaUsuarios.addElement(String.format("%s - %s", usuarios.get(i).getNombreUsuario(),usuarios.get(i).getPuntos()));
		}
		
		for(int i=0;i<preguntas.size();i++){
			listaPreguntas.addElement(String.format("%s - %s", preguntas.get(i).getPregunta(), preguntas.get(i).getRespuesta()));
		}
		
		listPreguntas.setModel(listaPreguntas);
		listUsuarios.setModel(listaUsuarios);
	}
	
	public static void actualizarListaErrores(String error){
		listaErrores.addElement(error);
		listErrores.setModel(listaErrores);
	}
	
	private static void sacarDatosGuardados(){
		GestionFicheros.leerConfig();
		txtUsuarioTwitch.setText(Config.getCanalTwitch());
		txtoAuth.setText(Config.getoAuth());
		txtPuntos.setText(String.valueOf(Config.getPuntos()));
		txtUsuarioTS3.setText(Config.getUsuarioTeamspeak());
		txtContraTS3.setText(Config.getContraseniaTeamspeak());
		txtServidorTS3.setText(Config.getServidorTeamspeak());
		txtPuertoTS3.setText(String.valueOf(Config.getPuertoTeamspeak()));
	}
}
