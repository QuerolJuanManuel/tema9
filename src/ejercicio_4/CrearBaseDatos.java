package ejercicio_4;

import java.io.*;

import com.db4o.*;


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
		int i =0;
		int j=0;
		int[] elegidas = new int[10];
		int size = res.size();
		int NumPregunta =0;
		if(size >= 10){
			//Control para evitar un blucle infinito
			while(i<10){
				NumPregunta = 1+(int)(Math.random() * (double)size);
				for(j=0;j<10;j++){
					if(elegidas[j] == NumPregunta)
						break;
				}
				if(j == 10){
					elegidas[i]=NumPregunta;
					i++;
				}
			}
			for(i=0;i<10;i++){
				for(j=1;j<elegidas[i];j++){
					if(res.hasNext())
						res.next();
				}
				Pregunta pt = (Pregunta) res.next();
				res.reset();
				System.out.println(pt.preguntar());
				System.out.println("Introduce la respuesta correcta 1,2,3 o 4:");
				try {
					if(pt.comprobarRespuesta(Integer.parseInt(br.readLine()))){
						System.out.println("Correcto!!\n");
						aciertos++;
					}else{
						System.out.println("Incorrecto!!\n");
					}
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Fin del juego\n" + "Aciertos: " + aciertos +" Fallos: " + (10-aciertos));
		}else{
			System.out.println("No hay suficientes preguntas en la base de datos\n");
		}
		if(br != null){
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "preguntas.db4o");
		try{
			//REALIZAR OPERACIONES O LLAMADAS A MÉTODOS
			if(bd.queryByExample(null).size() == 0){
				//Si la base de datos esta vacia significa que no se ha creado aun
				almacenarPreguntas(bd);
			}
			juegoPreguntas(bd);
		}finally {
			bd.close();
		}
	}
}
