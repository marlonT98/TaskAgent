package DataAccesObject;

import Entity.ClienteEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends ConexionMySQL {

    // Insertar un nuevo cliente
    public boolean insertar(ClienteEntity cliente) {
        String sql = "INSERT INTO clientes (nombre, telefono, direccion, correo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getTelefono());
            pst.setString(3, cliente.getDireccion());
            pst.setString(4, cliente.getCorreo());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los clientes
    public List<ClienteEntity> obtenerTodos() {
        List<ClienteEntity> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ClienteEntity cliente = new ClienteEntity();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Actualizar un cliente
    public boolean actualizar(ClienteEntity cliente) {
        String sql = "UPDATE clientes SET nombre = ?, telefono = ?, direccion = ?, correo = ? WHERE id_cliente = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getTelefono());
            pst.setString(3, cliente.getDireccion());
            pst.setString(4, cliente.getCorreo());
            pst.setInt(5, cliente.getIdCliente());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un cliente
    public boolean eliminar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, idCliente);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
