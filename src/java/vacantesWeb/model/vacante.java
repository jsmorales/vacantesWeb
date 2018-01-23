
package vacantesWeb.model;

import java.time.LocalDate;

/**
 *
 * @author jsmorales
 * modelo de la tabla vacante
 */
public class vacante {
   /*
    Variables que mapean a los campos de la tabla
    */ 
    private int id;
    private LocalDate fechaPublicacion;
    private String nombre;
    private String descripcion;
    private String detalle;
    
    /*Contructor con solo el id*/
    
    public vacante(int id) {
        this.fechaPublicacion = LocalDate.now(); 
        this.id = id;
    }
    
    /*Getters y setters para cada campo mapeado*/
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    /*Se sobreescribe el método toString para ver el status de cada vairable
    del objeto.
    Retorna todos los valores del objeto.*/

    @Override
    public String toString() {
        return "vacante{" + "id=" + id + ", fechaPublicacion=" + fechaPublicacion + ", nombre=" + nombre + ", descripcion=" + descripcion + ", detalle=" + detalle + '}';
    }
    
}
