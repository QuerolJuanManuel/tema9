package ejercicio_4;

public class Pregunta {

	public String pregunta;
	public String respuesta1;
	public String respuesta2;
	public String respuesta3;
	public String respuesta4;
	public int respuestaCorrecta;
	
	public Pregunta(){
		this.pregunta=null;
		this.respuesta1=null;
		this.respuesta2=null;
		this.respuesta3=null;
		this.respuesta4=null;
		this.respuestaCorrecta=0;
	}
	public Pregunta (String pregunta, String respuesta1, String respuesta2, String respuesta3, String respuesta4, int respuestaCorrecta){
		this.pregunta=pregunta;
		this.respuesta1=respuesta1;
		this.respuesta2=respuesta2;
		this.respuesta3=respuesta3;
		this.respuesta4=respuesta4;
		this.respuestaCorrecta=respuestaCorrecta;
	}
	
	public String getPregunta(){
		return pregunta;
	}
	
	public String getRespuesta1(){
		return respuesta1;
	}
	
	public String getRespuesta2(){
		return respuesta2;
	}
	
	public String getRespuesta3(){
		return respuesta3;
	}
	
	public String getRespuesta4(){
		return respuesta4;
	}
	
	public int getRespuestaCorrecta(){
		return respuestaCorrecta;
	}
	
	public void setPregunta(String pregunta){
		this.pregunta=pregunta;
	}
	
	public void setRespuesta1(String respuesta1){
		this.respuesta1=respuesta1;
	}
	
	public void setRespuesta2(String respuesta2){
		this.respuesta2=respuesta2;
	}
	
	public void setRespuesta3(String respuesta3){
		this.respuesta3=respuesta3;
	}
	
	public void setRespuesta4(String respuesta4){
		this.respuesta4=respuesta4;
	}
	
	public void setRespuestaCorrecta(int respuestaCorrecta){
		this.respuestaCorrecta=respuestaCorrecta;
	}
	
	public String toString(){
		return this.pregunta +"\n" + this.respuesta1 +"\n" + this.respuesta2 +"\n"
				+ this.respuesta3 +"\n" + this.respuesta4 +"\n" + this.respuestaCorrecta +"\n";
	}
	public String preguntar(){
		return this.pregunta +"\n" + this.respuesta1 +"\n" + this.respuesta2 +"\n"
				+ this.respuesta3 +"\n" + this.respuesta4 +"\n";
	}
	public boolean comprobarRespuesta(int respuesta){
		return respuesta == this.respuestaCorrecta;
	}
}
