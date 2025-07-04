
package Modelo;

import Modelo.EnumEstadoHabitacion;
import java.time.LocalDateTime;
import Modelo.EnumTipoHabitacion;

import java.util.List;


public class Habitacion {
    private int idHabitacion;
    private int numHabitacion;
    private EnumEstadoHabitacion estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private EnumTipoHabitacion tipoHabitacion;

    
     public int getIdHabitacion() {
        return idHabitacion;
    };
    
     public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
     
     public int getNumHabitacion(){
         return numHabitacion;
     }
     
     public void setNumHabitacion(int numHabitacion){
         this.numHabitacion = numHabitacion;
     }
     
     public EnumEstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoHabitacion estado) {
        this.estado = estado;
    }
    
      public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    } 
    
     public EnumTipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(EnumTipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
    
    

}



