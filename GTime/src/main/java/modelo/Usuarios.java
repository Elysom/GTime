package modelo;

public class Usuarios {

    // Atributos
    private int id;                   // ID único del alumno
    private String mail;              // Correo del alumno
    private String nombreReal;        // Nombre real del alumno
    private int apellidos;            // Apellidos (aparentemente este debe ser una cadena)
    private String contasenia;        // Contraseña del alumno
    private String curso;             // Curso en el que está inscrito el alumno
    private String nombreUsuario;     // Nombre de usuario

    // Constructor vacío
    public Usuarios() {
        super();
    }

    // Constructor con parámetros
    public Usuarios(int id, String mail, String nombreReal, int apellidos, String contasenia, String curso,
                    String nombreUsuario) {
        super();
        this.id = id;
        this.mail = mail;
        this.nombreReal = nombreReal;
        this.apellidos = apellidos;
        this.contasenia = contasenia;
        this.curso = curso;
        this.nombreUsuario = nombreUsuario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public int getApellidos() {
        return apellidos;
    }

    public void setApellidos(int apellidos) {
        this.apellidos = apellidos;
    }

    public String getContasenia() {
        return contasenia;
    }

    public void setContasenia(String contasenia) {
        this.contasenia = contasenia;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
