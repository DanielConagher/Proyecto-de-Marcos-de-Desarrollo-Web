package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReporteDTO {
    
    // Resumen por periodos
    private ResumenPeriodo hoy;
    private ResumenPeriodo estaSemana;
    private ResumenPeriodo esteMes;
    private ResumenPeriodo esteAnio;
    
    // Productos más vendidos
    private List<ProductoVendido> productosMasVendidos;
    
    // Ventas por método de pago
    private List<VentaMetodoPago> ventasPorMetodoPago;
    
    // Ventas por hora
    private List<VentaPorHora> ventasPorHora;

    // Getters y Setters
    public ResumenPeriodo getHoy() { return hoy; }
    public void setHoy(ResumenPeriodo hoy) { this.hoy = hoy; }

    public ResumenPeriodo getEstaSemana() { return estaSemana; }
    public void setEstaSemana(ResumenPeriodo estaSemana) { this.estaSemana = estaSemana; }

    public ResumenPeriodo getEsteMes() { return esteMes; }
    public void setEsteMes(ResumenPeriodo esteMes) { this.esteMes = esteMes; }

    public ResumenPeriodo getEsteAnio() { return esteAnio; }
    public void setEsteAnio(ResumenPeriodo esteAnio) { this.esteAnio = esteAnio; }

    public List<ProductoVendido> getProductosMasVendidos() { return productosMasVendidos; }
    public void setProductosMasVendidos(List<ProductoVendido> productosMasVendidos) { this.productosMasVendidos = productosMasVendidos; }

    public List<VentaMetodoPago> getVentasPorMetodoPago() { return ventasPorMetodoPago; }
    public void setVentasPorMetodoPago(List<VentaMetodoPago> ventasPorMetodoPago) { this.ventasPorMetodoPago = ventasPorMetodoPago; }

    public List<VentaPorHora> getVentasPorHora() { return ventasPorHora; }
    public void setVentasPorHora(List<VentaPorHora> ventasPorHora) { this.ventasPorHora = ventasPorHora; }

    // Clases internas
    public static class ResumenPeriodo {
        private BigDecimal totalVentas;
        private int cantidadOrdenes;
        private BigDecimal porcentajeCambio;

        public ResumenPeriodo() {}
        
        public ResumenPeriodo(BigDecimal totalVentas, int cantidadOrdenes, BigDecimal porcentajeCambio) {
            this.totalVentas = totalVentas;
            this.cantidadOrdenes = cantidadOrdenes;
            this.porcentajeCambio = porcentajeCambio;
        }

        public BigDecimal getTotalVentas() { return totalVentas; }
        public void setTotalVentas(BigDecimal totalVentas) { this.totalVentas = totalVentas; }

        public int getCantidadOrdenes() { return cantidadOrdenes; }
        public void setCantidadOrdenes(int cantidadOrdenes) { this.cantidadOrdenes = cantidadOrdenes; }

        public BigDecimal getPorcentajeCambio() { return porcentajeCambio; }
        public void setPorcentajeCambio(BigDecimal porcentajeCambio) { this.porcentajeCambio = porcentajeCambio; }
    }

    public static class ProductoVendido {
        private Long idProducto;
        private String nombre;
        private int cantidadVendida;
        private BigDecimal totalVentas;
        private int ranking;

        public ProductoVendido() {}
        
        public ProductoVendido(Long idProducto, String nombre, int cantidadVendida, BigDecimal totalVentas, int ranking) {
            this.idProducto = idProducto;
            this.nombre = nombre;
            this.cantidadVendida = cantidadVendida;
            this.totalVentas = totalVentas;
            this.ranking = ranking;
        }

        public Long getIdProducto() { return idProducto; }
        public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public int getCantidadVendida() { return cantidadVendida; }
        public void setCantidadVendida(int cantidadVendida) { this.cantidadVendida = cantidadVendida; }

        public BigDecimal getTotalVentas() { return totalVentas; }
        public void setTotalVentas(BigDecimal totalVentas) { this.totalVentas = totalVentas; }

        public int getRanking() { return ranking; }
        public void setRanking(int ranking) { this.ranking = ranking; }
    }

    public static class VentaMetodoPago {
        private String metodoPago;
        private int cantidadOrdenes;
        private BigDecimal totalVentas;
        private BigDecimal porcentaje;

        public VentaMetodoPago() {}
        
        public VentaMetodoPago(String metodoPago, int cantidadOrdenes, BigDecimal totalVentas, BigDecimal porcentaje) {
            this.metodoPago = metodoPago;
            this.cantidadOrdenes = cantidadOrdenes;
            this.totalVentas = totalVentas;
            this.porcentaje = porcentaje;
        }

        public String getMetodoPago() { return metodoPago; }
        public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

        public int getCantidadOrdenes() { return cantidadOrdenes; }
        public void setCantidadOrdenes(int cantidadOrdenes) { this.cantidadOrdenes = cantidadOrdenes; }

        public BigDecimal getTotalVentas() { return totalVentas; }
        public void setTotalVentas(BigDecimal totalVentas) { this.totalVentas = totalVentas; }

        public BigDecimal getPorcentaje() { return porcentaje; }
        public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    }

    public static class VentaPorHora {
        private String rangoHora;
        private int cantidadOrdenes;
        private BigDecimal totalVentas;
        private boolean esHoraPico;

        public VentaPorHora() {}
        
        public VentaPorHora(String rangoHora, int cantidadOrdenes, BigDecimal totalVentas, boolean esHoraPico) {
            this.rangoHora = rangoHora;
            this.cantidadOrdenes = cantidadOrdenes;
            this.totalVentas = totalVentas;
            this.esHoraPico = esHoraPico;
        }

        public String getRangoHora() { return rangoHora; }
        public void setRangoHora(String rangoHora) { this.rangoHora = rangoHora; }

        public int getCantidadOrdenes() { return cantidadOrdenes; }
        public void setCantidadOrdenes(int cantidadOrdenes) { this.cantidadOrdenes = cantidadOrdenes; }

        public BigDecimal getTotalVentas() { return totalVentas; }
        public void setTotalVentas(BigDecimal totalVentas) { this.totalVentas = totalVentas; }

        public boolean isEsHoraPico() { return esHoraPico; }
        public void setEsHoraPico(boolean esHoraPico) { this.esHoraPico = esHoraPico; }
    }
}
