package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente implements Serializable {

// ---------- ATRIBUTOS
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_cliente;
    public boolean habilitado;
    
    @OneToOne
    public Persona persona;
    
    @OneToMany
    public List<Venta> listaVentas;
    
    
// ------------- CONTRUCTORES

    public Cliente() {
    }

    public Cliente(int id_cliente, boolean habilitado, Persona persona, List<Venta> listaVentas) {
        this.id_cliente = id_cliente;
        this.habilitado = habilitado;
        this.persona = persona;
        this.listaVentas = listaVentas;
    }


// ------------ GETTERS Y SETTERS

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    
    
}
