package dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MenuDTO {

    private String nombre;
    private String descripcion;
    private Map<String, BigDecimal> preciosPorTamano;
    private List<ExtraDTO> extras;

    // Getters y Setters
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

    public Map<String, BigDecimal> getPreciosPorTamano() {
        return preciosPorTamano;
    }

    public void setPreciosPorTamano(Map<String, BigDecimal> preciosPorTamano) {
        this.preciosPorTamano = preciosPorTamano;
    }

    public List<ExtraDTO> getExtras() {
        return extras;
    }

    public void setExtras(List<ExtraDTO> extras) {
        this.extras = extras;
    }
}