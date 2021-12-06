package Model;

public class Profesionales {

    private String id;
    private String nombre;
    private String salario;
    private String profesion;

    public Profesionales() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }


    //metodo string para mostrar el objeto en la lista
    @Override
    public String toString() {
        return nombre;
    }
}
