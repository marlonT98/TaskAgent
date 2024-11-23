package DataAccesObject;

import Entity.TrabajadorEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO extends ConexionMySQL {

    // Insertar un nuevo trabajador
    public boolean insertar(TrabajadorEntity trabajador) {
        String sql = "INSERT INTO trabajadores (nombre, cargo, telefono, correo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, trabajador.getNombre());
            pst.setString(2, trabajador.getCargo());
            pst.setString(3, trabajador.getTelefono());
            pst.setString(4, trabajador.getCorreo());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar trabajador: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los trabajadores
    public List<TrabajadorEntity> obtenerTodos() {
        List<TrabajadorEntity> trabajadores = new ArrayList<>();
        String sql = "SELECT * FROM trabajadores";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TrabajadorEntity trabajador = new TrabajadorEntity();
                trabajador.setIdTrabajador(rs.getInt("id_trabajador"));
                trabajador.setNombre(rs.getString("nombre"));
                trabajador.setCargo(rs.getString("cargo"));
                trabajador.setTelefono(rs.getString("telefono"));
                trabajador.setCorreo(rs.getString("correo"));
                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar trabajadores: " + e.getMessage());
        }
        return trabajadores;
    }

    // Actualizar un trabajador
    public boolean actualizar(TrabajadorEntity trabajador) {
        String sql = "UPDATE trabajadores SET nombre = ?, cargo = ?, telefono = ?, correo = ? WHERE id_trabajador = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, trabajador.getNombre());
            pst.setString(2, trabajador.getCargo());
            pst.setString(3, trabajador.getTelefono());
            pst.setString(4, trabajador.getCorreo());
            pst.setInt(5, trabajador.getIdTrabajador());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar trabajador: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un trabajador
    public boolean eliminar(int idTrabajador) {
        String sql = "DELETE FROM trabajadores WHERE id_trabajador = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setInt(1, idTrabajador);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar trabajador: " + e.getMessage());
            return false;
        }
    }
}
