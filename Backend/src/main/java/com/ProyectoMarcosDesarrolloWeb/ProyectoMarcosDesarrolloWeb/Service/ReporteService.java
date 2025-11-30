package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.CompraRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ReporteDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ReporteDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CompraRepository compraRepository;

    public ReporteDTO obtenerReporteCompleto() {
        ReporteDTO reporte = new ReporteDTO();
        
        // Obtener resúmenes por periodo
        reporte.setHoy(obtenerResumenHoy());
        reporte.setEstaSemana(obtenerResumenSemana());
        reporte.setEsteMes(obtenerResumenMes());
        reporte.setEsteAnio(obtenerResumenAnio());
        
        // Obtener productos más vendidos
        reporte.setProductosMasVendidos(obtenerProductosMasVendidos());
        
        // Obtener ventas por método de pago
        reporte.setVentasPorMetodoPago(obtenerVentasPorMetodoPago());
        
        // Obtener ventas por hora
        reporte.setVentasPorHora(obtenerVentasPorHora());
        
        return reporte;
    }

    private ResumenPeriodo obtenerResumenHoy() {
        String sql = "SELECT COALESCE(SUM(precio_total), 0) as total, COUNT(*) as cantidad " +
                     "FROM compra WHERE CAST(fecha_compra AS DATE) = CAST(GETDATE() AS DATE)";
        
        String sqlAyer = "SELECT COALESCE(SUM(precio_total), 0) as total " +
                         "FROM compra WHERE CAST(fecha_compra AS DATE) = CAST(DATEADD(day, -1, GETDATE()) AS DATE)";
        
        return ejecutarConsultaResumen(sql, sqlAyer);
    }

    private ResumenPeriodo obtenerResumenSemana() {
        String sql = "SELECT COALESCE(SUM(precio_total), 0) as total, COUNT(*) as cantidad " +
                     "FROM compra WHERE fecha_compra >= DATEADD(day, -7, GETDATE())";
        
        String sqlAnterior = "SELECT COALESCE(SUM(precio_total), 0) as total " +
                             "FROM compra WHERE fecha_compra >= DATEADD(day, -14, GETDATE()) " +
                             "AND fecha_compra < DATEADD(day, -7, GETDATE())";
        
        return ejecutarConsultaResumen(sql, sqlAnterior);
    }

    private ResumenPeriodo obtenerResumenMes() {
        String sql = "SELECT COALESCE(SUM(precio_total), 0) as total, COUNT(*) as cantidad " +
                     "FROM compra WHERE YEAR(fecha_compra) = YEAR(GETDATE()) AND MONTH(fecha_compra) = MONTH(GETDATE())";
        
        String sqlAnterior = "SELECT COALESCE(SUM(precio_total), 0) as total " +
                             "FROM compra WHERE YEAR(fecha_compra) = YEAR(DATEADD(month, -1, GETDATE())) " +
                             "AND MONTH(fecha_compra) = MONTH(DATEADD(month, -1, GETDATE()))";
        
        return ejecutarConsultaResumen(sql, sqlAnterior);
    }

    private ResumenPeriodo obtenerResumenAnio() {
        String sql = "SELECT COALESCE(SUM(precio_total), 0) as total, COUNT(*) as cantidad " +
                     "FROM compra WHERE YEAR(fecha_compra) = YEAR(GETDATE())";
        
        String sqlAnterior = "SELECT COALESCE(SUM(precio_total), 0) as total " +
                             "FROM compra WHERE YEAR(fecha_compra) = YEAR(GETDATE()) - 1";
        
        return ejecutarConsultaResumen(sql, sqlAnterior);
    }

    private ResumenPeriodo ejecutarConsultaResumen(String sqlActual, String sqlAnterior) {
        try {
            Query queryActual = entityManager.createNativeQuery(sqlActual);
            Object[] resultActual = (Object[]) queryActual.getSingleResult();
            
            BigDecimal totalActual = resultActual[0] != null ? new BigDecimal(resultActual[0].toString()) : BigDecimal.ZERO;
            int cantidad = resultActual[1] != null ? ((Number) resultActual[1]).intValue() : 0;
            
            Query queryAnterior = entityManager.createNativeQuery(sqlAnterior);
            Object resultAnterior = queryAnterior.getSingleResult();
            BigDecimal totalAnterior = resultAnterior != null ? new BigDecimal(resultAnterior.toString()) : BigDecimal.ZERO;
            
            BigDecimal porcentaje = BigDecimal.ZERO;
            if (totalAnterior.compareTo(BigDecimal.ZERO) > 0) {
                porcentaje = totalActual.subtract(totalAnterior)
                    .divide(totalAnterior, 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"))
                    .setScale(1, RoundingMode.HALF_UP);
            } else if (totalActual.compareTo(BigDecimal.ZERO) > 0) {
                porcentaje = new BigDecimal("100");
            }
            
            return new ResumenPeriodo(totalActual, cantidad, porcentaje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResumenPeriodo(BigDecimal.ZERO, 0, BigDecimal.ZERO);
        }
    }

    public List<ProductoVendido> obtenerProductosMasVendidos() {
        List<ProductoVendido> productos = new ArrayList<>();
        
        try {
            String sql = "SELECT TOP 5 p.id_producto, p.nombre, " +
                        "SUM(cp.cantidad) as total_cantidad, " +
                        "SUM(cp.cantidad * cp.precio_venta) as total_ventas " +
                        "FROM Compra_Producto cp " +
                        "INNER JOIN dbo.producto p ON cp.id_Producto = p.id_producto " +
                        "INNER JOIN compra c ON cp.id_Compra = c.id_compra " +
                        "WHERE YEAR(c.fecha_compra) = YEAR(GETDATE()) AND MONTH(c.fecha_compra) = MONTH(GETDATE()) " +
                        "GROUP BY p.id_producto, p.nombre " +
                        "ORDER BY total_cantidad DESC";
            
            Query query = entityManager.createNativeQuery(sql);
            List<Object[]> results = query.getResultList();
            
            int ranking = 1;
            for (Object[] row : results) {
                Long idProducto = row[0] != null ? ((Number) row[0]).longValue() : 0L;
                String nombre = row[1] != null ? row[1].toString() : "";
                int cantidadVendida = row[2] != null ? ((Number) row[2]).intValue() : 0;
                BigDecimal totalVentas = row[3] != null ? new BigDecimal(row[3].toString()) : BigDecimal.ZERO;
                
                productos.add(new ProductoVendido(idProducto, nombre, cantidadVendida, totalVentas, ranking++));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return productos;
    }

    public List<VentaMetodoPago> obtenerVentasPorMetodoPago() {
        List<VentaMetodoPago> ventas = new ArrayList<>();
        
        try {
            String sql = "SELECT mp.nombre, COUNT(c.id_compra) as cantidad, " +
                        "COALESCE(SUM(c.precio_total), 0) as total " +
                        "FROM compra c " +
                        "INNER JOIN metodo_pago mp ON c.id_metodo_pago = mp.id_metodo_pago " +
                        "WHERE YEAR(c.fecha_compra) = YEAR(GETDATE()) AND MONTH(c.fecha_compra) = MONTH(GETDATE()) " +
                        "GROUP BY mp.nombre " +
                        "ORDER BY total DESC";
            
            Query query = entityManager.createNativeQuery(sql);
            List<Object[]> results = query.getResultList();
            
            // Calcular total general para porcentajes
            BigDecimal totalGeneral = BigDecimal.ZERO;
            for (Object[] row : results) {
                totalGeneral = totalGeneral.add(row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO);
            }
            
            for (Object[] row : results) {
                String metodoPago = row[0] != null ? row[0].toString() : "";
                int cantidad = row[1] != null ? ((Number) row[1]).intValue() : 0;
                BigDecimal total = row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO;
                
                BigDecimal porcentaje = BigDecimal.ZERO;
                if (totalGeneral.compareTo(BigDecimal.ZERO) > 0) {
                    porcentaje = total.divide(totalGeneral, 4, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal("100"))
                        .setScale(1, RoundingMode.HALF_UP);
                }
                
                ventas.add(new VentaMetodoPago(metodoPago, cantidad, total, porcentaje));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ventas;
    }

    public List<VentaPorHora> obtenerVentasPorHora() {
        List<VentaPorHora> ventas = new ArrayList<>();
        
        try {
            // Definir rangos de horas
            String[][] rangos = {
                {"08:00 - 12:00", "8", "12"},
                {"12:00 - 15:00", "12", "15"},
                {"15:00 - 18:00", "15", "18"},
                {"18:00 - 22:00", "18", "22"}
            };
            
            int maxOrdenes = 0;
            List<Object[]> resultados = new ArrayList<>();
            
            for (String[] rango : rangos) {
                String sql = "SELECT COUNT(c.id_compra) as cantidad, " +
                            "COALESCE(SUM(c.precio_total), 0) as total " +
                            "FROM compra c " +
                            "WHERE DATEPART(HOUR, c.fecha_compra) >= " + rango[1] + " " +
                            "AND DATEPART(HOUR, c.fecha_compra) < " + rango[2] + " " +
                            "AND YEAR(c.fecha_compra) = YEAR(GETDATE()) AND MONTH(c.fecha_compra) = MONTH(GETDATE())";
                
                Query query = entityManager.createNativeQuery(sql);
                Object[] result = (Object[]) query.getSingleResult();
                
                int cantidad = result[0] != null ? ((Number) result[0]).intValue() : 0;
                if (cantidad > maxOrdenes) maxOrdenes = cantidad;
                
                resultados.add(new Object[]{rango[0], result[0], result[1]});
            }
            
            for (Object[] row : resultados) {
                String rangoHora = row[0].toString();
                int cantidad = row[1] != null ? ((Number) row[1]).intValue() : 0;
                BigDecimal total = row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO;
                boolean esHoraPico = cantidad == maxOrdenes && cantidad > 0;
                
                ventas.add(new VentaPorHora(rangoHora, cantidad, total, esHoraPico));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ventas;
    }
}
