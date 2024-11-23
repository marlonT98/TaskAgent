package DataAccesObject;

import Entity.DetalleVentaEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO extends ConexionMySQL {

    // Insertar un nuevo detalle de venta
    public boolean insertar(DetalleVentaEntity detalle) {
        String sql = "INSERT INTO detalle_ventas (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, detalle.getIdVenta());
            pst.setInt(2, detalle.getIdProducto());
            pst.setInt(3, detalle.getCantidad());
            pst.setDouble(4, detalle.getPrecioUnitario());
            pst.setDouble(5, detalle.getSubtotal());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar detalle de venta: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los detalles de ventas
    public List<DetalleVentaEntity> obtenerTodos() {
        List<DetalleVentaEntity> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ventas";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                DetalleVentaEntity detalle = new DetalleVentaEntity();
                detalle.setIdDetalle(rs.getInt("id_detalle"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdProducto(rs.getInt("id_producto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setSubtotal(rs.getDouble("subtotal"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar detalles de ventas: " + e.getMessage());
        }
        return detalles;
    }

    // Actualizar un detalle de venta
    public boolean actualizar(DetalleVentaEntity detalle) {
        String sql = "UPDATE detalle_ventas SET id_venta = ?, id_producto = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE id_detalle = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, detalle.getIdVenta());
            pst.setInt(2, detalle.getIdProducto());
            pst.setInt(3, detalle.getCantidad());
            pst.setDouble(4, detalle.getPrecioUnitario());
            pst.setDouble(5, detalle.getSubtotal());
            pst.setInt(6, detalle.getIdDetalle());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle de venta: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un detalle de venta
    public boolean eliminar(int idDetalle) {
        String sql = "DELETE FROM detalle_ventas WHERE id_detalle = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, idDetalle);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle de venta: " + e.getMessage());
            return false;
        }
    }
}
