package modelo;

public class Usuarios {

    // Atributos
    private int id;                   // ID único del alumno
    private String nombre;            // Nombre del alumno
    private String apellido;          // Apellido del alumno
    private int edad;                 // Edad del alumno
    private String curso;             // Curso en el que está inscrito el alumno

    // Constructor
    public Usuarios(int id, String nombre, String apellido, int edad, String curso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.curso = curso;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    // Método toString para representar el objeto en formato String
    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", curso='" + curso + '\'' +
                '}';
    }

    // Otros métodos opcionales
    // Método para mostrar la información del alumno
    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Edad: " + edad);
        System.out.println("Curso: " + curso);
    }
}