package BDOO;

public class Alumno {

	public String nombre;
	public int edad;
	public double nota;
	
	public Alumno(){
		nombre=null; edad=0; nota=0;
	}
	public Alumno(String n, int e, double no){
		nombre=n; edad=e; nota=no;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public String toString() {
		return this.nombre +"( "+ this.edad + ") Nota: " + this.nota ;
	}
}
