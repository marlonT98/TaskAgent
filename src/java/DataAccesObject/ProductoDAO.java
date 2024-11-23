package DataAccesObject;

import Entity.ProductoEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends ConexionMySQL {

    // Insertar un nuevo producto
    public boolean insertar(ProductoEntity producto) {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, producto.getNombre());
            pst.setDouble(2, producto.getPrecio());
            pst.setInt(3, producto.getStock());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los productos
    public List<ProductoEntity> obtenerTodos() {
        List<ProductoEntity> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ProductoEntity producto = new ProductoEntity();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return productos;
    }

    // Actualizar un producto
    public boolean actualizar(ProductoEntity producto) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id_producto = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, producto.getNombre());
            pst.setDouble(2, producto.getPrecio());
            pst.setInt(3, producto.getStock());
            pst.setInt(4, producto.getIdProducto());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un producto
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, idProducto);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}
