package BDOO;

import com.db4o.*;
import com.db4o.query.Query;

public class MostrarAlumnos26 {
	
	public static void mostrarResultado(ObjectSet res){
		System.out.println("Recuperados "+res.size()+ " Objetos");
		while(res.hasNext())
		System.out.println(res.next());
		}
	
	public static void muestraAlumnos(ObjectContainer bd){
		Alumno a=new Alumno(null, 26, 0);
		ObjectSet res=bd.queryByExample(a);
		mostrarResultado(res);
		}
	
	public static void consultaSODAAlum23(ObjectContainer bd){
		Query query= bd.query( );
		query.constrain(Alumno.class);
		query.descend("edad").constrain(23);
		ObjectSet result= query.execute();
		mostrarResultado(result);
		}
	
	public static void main(String[] args) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "alumnos.db4o");
		try{
			muestraAlumnos(bd);
			consultaSODAAlum23(bd);
		}finally {
			bd.close();
		}
	}

}
