package BDOO;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class AccesoBDOO {

	public static void almacenarAlumnos(ObjectContainer bd){
		Alumno a1=new Alumno("Juan Gámez",23, 8.75);
		bd.store(a1);
		System.out.println(a1.getNombre()+ " -> Almacenado");
		Alumno a2=new Alumno("Emilio Anaya",24, 6.25);
		bd.store(a2);
		System.out.println(a2.getNombre()+ " -> Almacenado");
		Alumno a3=new Alumno("Angeles Blanco",26, 7);
		bd.store(a3);
		System.out.println(a3.getNombre()+ " -> Almacenado");
	}
	
	public static void mostrarResultado(ObjectSet res){
		System.out.println("Recuperados "+res.size()+ " Objetos");
		while(res.hasNext())
		System.out.println(res.next());
		}
	
	public static void muestraAlumnos(ObjectContainer bd){
		ObjectSet res=bd.queryByExample(new Alumno(null, 26, 0));
		mostrarResultado(res);
		}
	
	public static void actualizarNotaAlumno(ObjectContainer bd, String nom, double nota){
		ObjectSet res= bd.queryByExample(new Alumno(nom,0,0));
		Alumno a=(Alumno) res.next();
		a.setNota(nota);
		bd.store(a);
		muestraAlumnos(bd);
		}

	public static void main(String[] args) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "alumnos.db4o");
		try{
			almacenarAlumnos(bd);
			muestraAlumnos(bd);
			actualizarNotaAlumno(bd,"Emilio Anaya", 9.5);
		}finally {
			bd.close();
		}
	}
}
