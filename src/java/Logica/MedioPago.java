package Logica;

public class MedioPago {

    
    
// ---------  ATRIBUTOS    

    private int codigo_medio_pago;
    private String nombre;
    private boolean habilitado;
    
    

//<editor-fold defaultstate="collapsed" desc="CONTRUCTORES">
    
    public MedioPago() {
    }
    
    public MedioPago(int codigo_medio_pago, String nombre, boolean habilitado) {
        this.codigo_medio_pago = codigo_medio_pago;
        this.nombre = nombre;
        this.habilitado = habilitado;
    }
    
//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="SETTERS Y GETTERS">
    
    public int getCodigo_medio_pago() {
        return codigo_medio_pago;
    }
    
    public void setCodigo_medio_pago(int codigo_medio_pago) {
        this.codigo_medio_pago = codigo_medio_pago;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean isHabilitado() {
        return habilitado;
    }
    
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
//</editor-fold>
    
    
    
    
}
