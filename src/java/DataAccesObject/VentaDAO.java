package DataAccesObject;

import Entity.VentaEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO extends ConexionMySQL {

    // Insertar una nueva venta
    public boolean insertar(VentaEntity venta) {
        String sql = "INSERT INTO ventas (id_cliente, id_trabajador, total) VALUES (?, ?, ?)";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, venta.getIdCliente());
            pst.setInt(2, venta.getIdTrabajador());
            pst.setDouble(3, venta.getTotal());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar venta: " + e.getMessage());
            return false;
        }
    }

    // Obtener todas las ventas
    public List<VentaEntity> obtenerTodas() {
        List<VentaEntity> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                VentaEntity venta = new VentaEntity();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setIdCliente(rs.getInt("id_cliente"));
                venta.setIdTrabajador(rs.getInt("id_trabajador"));
                venta.setTotal(rs.getDouble("total"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        }
        return ventas;
    }

    // Actualizar una venta
    public boolean actualizar(VentaEntity venta) {
        String sql = "UPDATE ventas SET id_cliente = ?, id_trabajador = ?, total = ? WHERE id_venta = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, venta.getIdCliente());
            pst.setInt(2, venta.getIdTrabajador());
            pst.setDouble(3, venta.getTotal());
            pst.setInt(4, venta.getIdVenta());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
            return false;
        }
    }

    // Eliminar una venta
    public boolean eliminar(int idVenta) {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, idVenta);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar venta: " + e.getMessage());
            return false;
        }
    }
}
