package ejercicio_4;

import java.io.*;

import com.db4o.*;
import com.db4o.config.EmbeddedConfiguration;

public class CrearBaseDatos {

	
	public static void almacenarPreguntas(ObjectContainer bd){
		String pregunta;
		String respuesta1;
		String respuesta2;
		String respuesta3;
		String respuesta4;
		int respuestaCorrecta;
		File fe = new File("Preguntas.txt");
		Pregunta p1;
		if(fe.exists()) {
			try {
				BufferedReader br= new BufferedReader( 
						new InputStreamReader(
								new FileInputStream(fe),"UTF-8"));
				while ((pregunta=br.readLine())!=null) {
					respuesta1 = br.readLine();
					respuesta2 = br.readLine();
					respuesta3 = br.readLine();
					respuesta4 = br.readLine();
					respuestaCorrecta = Integer.parseInt(br.readLine());
					p1=new Pregunta(pregunta, respuesta1, respuesta2, respuesta3, respuesta4, respuestaCorrecta);
					bd.store(p1);
				}
				if (br != null) br.close( );
			}catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void juegoPreguntas(ObjectContainer bd){
		ObjectSet res = bd.queryByExample(new Pregunta());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int aciertos =0;
		int i =0;//Contamos las preguntas elegidas
		int j=0;//Contamos el total de preguntas consideradas
		while(i < 10 && j<res.size()){
			if(res.hasNext()){
				Pregunta pt = (Pregunta) res.next();
				j++;
				//Probabilidad del 70% de eelgir la pregunta
				if(Math.random() > 0.7 || (res.size()-j)<=(10-i)){
					i++;
					System.out.println(pt.preguntar());
					System.out.println("Introduce la respuesta correcta 1,2,3 o 4:");
					try {
						if(pt.comprobarRespuesta(Integer.parseInt(br.readLine()))){
							System.out.println("Correcto!!\n");
							aciertos++;
						}
					} catch (NumberFormatException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("Fin del juego\n" + "Aciertos: " + aciertos +" Fallos: " + (10-aciertos));
		if(br != null){
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		EmbeddedConfiguration conf =  Db4oEmbedded.newConfiguration();
		conf.common().exceptionsOnNotStorable(false);
		ObjectContainer bd=Db4oEmbedded.openFile(conf, "preguntas.db4o");
		try{
			//REALIZAR OPERACIONES O LLAMADAS A MÉTODOS
			almacenarPreguntas(bd);
			juegoPreguntas(bd);
		}finally {
			bd.close();
		}
	}
}
