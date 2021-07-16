
package modelos;


public class Repuesto {
    
    private int id;
    private String nombreRepuesto;
    private String descripcion;
    private float precio;
    private int estado;
    private int existencia;

    public Repuesto() {
    }

    public Repuesto(int id, String nombreRepuesto, String descripcion, float precio, int estado) {
        this.id = id;
        this.nombreRepuesto = nombreRepuesto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }




    public boolean verificarCodigoR(int codigo)
    {
        return this.id == codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRepuesto() {
        return nombreRepuesto;
    }

    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Repuesto{" + "id=" + id + ", nombreRepuesto=" + nombreRepuesto + ", descripcion=" + descripcion + ", precio=" + precio + ", estado=" + estado + ", existencia=" + existencia + '}';
    }

   
 }
