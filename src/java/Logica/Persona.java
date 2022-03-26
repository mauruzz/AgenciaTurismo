package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Persona implements Serializable {

// ------- ATRIBUTOS
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_Persona;
    
    @Basic
    public String nombre;
    public String apellido;
    public String direccion;
    public int dni;
    public String nacionalidad;
    public String celular;
    public String email;
    
    @Temporal(TemporalType.DATE)
    public Date fecha_nacimiento;

    

    // ----------------------------------------------------- //


    //<editor-fold defaultstate="collapsed" desc="CONSTRUCTORES">
    
    public Persona() {
    }

    public Persona(int id_Persona, String nombre, String apellido, String direccion, int dni, String nacionalidad, String celular, String email, Date fecha_nacimiento) {
        this.id_Persona = id_Persona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
        this.nacionalidad = nacionalidad;
        this.celular = celular;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    //</editor-fold>
   
    
    // ----------------------------------------------------- //


    //<editor-fold defaultstate="collapsed" desc="SETTERS Y GETTERS">
    
    public int getId_Persona() {
        return id_Persona;
    }

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //


    
    
}
