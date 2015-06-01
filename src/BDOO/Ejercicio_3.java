package BDOO;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

public class Ejercicio_3 {

	public static void mostrarResultado(ObjectSet res){
		System.out.println("Recuperados "+res.size()+ " Objetos");
		while(res.hasNext())
		System.out.println(res.next());
		}

	public static void consultaSODAEjercicio3(ObjectContainer bd){
		Query query= bd.query( );
		query.constrain(Alumno.class);
		Constraint constr=query.descend("nota").constrain(9).smaller();
		query.descend("nota").constrain(7).greater().and(constr);
		ObjectSet result= query.execute();
		mostrarResultado(result);
	}
	
	public static void main(String[] args) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "alumnos.db4o");
		try{
			consultaSODAEjercicio3(bd);
		}finally {
			bd.close();
		}
	}
}
