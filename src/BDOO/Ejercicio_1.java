package BDOO;

import com.db4o.*;
import com.db4o.query.Query;

public class Ejercicio_1 {
	
	public static void mostrarResultado(ObjectSet res){
		System.out.println("Recuperados "+res.size()+ " Objetos");
		while(res.hasNext())
		System.out.println(res.next());
		}

	public static void consultaSODAFernando(ObjectContainer bd){
		Query query= bd.query( );
		query.constrain(Alumno.class);
		query.descend("nombre").orderAscending().constrain("Fernando Gil").not();
		ObjectSet result= query.execute();
		mostrarResultado(result);
	}
	
	public static void main(String[] args) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "alumnos.db4o");
		try{
			consultaSODAFernando(bd);
		}finally {
			bd.close();
		}
	}
}
