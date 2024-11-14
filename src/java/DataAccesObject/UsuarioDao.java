package DataAccesObject;

import Entity.UsuarioEntity;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends ConexionMySQL {

    public UsuarioDao() {
    }

    // Método para listar todos los usuarios
    public List<UsuarioEntity> readAll() {
        List<UsuarioEntity> lstUsuarios = new ArrayList<>();

        String SQL = "SELECT * FROM usuarios";
        try (Statement st = getConexion().createStatement(); ResultSet rs = st.executeQuery(SQL)) {

            while (rs.next()) {
                UsuarioEntity item = new UsuarioEntity();

                item.setId_usuario(rs.getString("id_usuario"));
                item.setNombre(rs.getString("nombre"));
                item.setEmail(rs.getString("email"));
                item.setPassword(rs.getString("password"));

                // Convertir a LocalDate desde Date
                item.setFecha_creacion(rs.getDate("fecha_creacion").toLocalDate());
                item.setFecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso").toLocalDate());

                lstUsuarios.add(item);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los usuarios: " + e.getMessage());
        }

        return lstUsuarios;
    }

    // Método para eliminar un usuario por ID
    public boolean delete(String id) {
        boolean result = false;

        String sql = "DELETE FROM usuarios WHERE id_usuario =?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, id);

            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }

        return result;
    }

    // Actualizar el usuario
    public boolean update(UsuarioEntity item) {
        boolean result = false;

        String sql = "UPDATE usuarios SET nombre=?, email=?, password=?, fecha_creacion=?, fecha_ultimo_acceso=? WHERE id_usuario=?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, item.getNombre());
            pst.setString(2, item.getEmail());
            pst.setString(3, item.getPassword());
            // Convertir LocalDate a java.sql.Date para la base de datos
            pst.setDate(4, Date.valueOf(item.getFecha_creacion()));
            pst.setDate(5, Date.valueOf(item.getFecha_ultimo_acceso()));

            pst.setString(6, item.getId_usuario());

            // Ejecuta la actualización y verifica si se actualizó al menos una fila
            result = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }

        return result;
    }

    //metodo para registrar o crear
    public boolean insert(UsuarioEntity item) {

        boolean result = false;

        try {
            String sql = "INSERT INTO usuarios (id_usuario, nombre, email, password, fecha_creacion, fecha_ultimo_acceso) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setString(2, item.getNombre());
            pst.setString(3, item.getEmail());
            pst.setString(4, item.getPassword());
            // Convertir LocalDate a java.sql.Date para la base de datos
            pst.setDate(5, Date.valueOf(item.getFecha_creacion()));
            pst.setDate(6, Date.valueOf(item.getFecha_ultimo_acceso()));

            // Usamos executeUpdate() para las operaciones de INSERT y verificamos si afecta al menos una fila
            result = pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());

        }

        return result;

    }

    // Método para listar un usuario por código
    public UsuarioEntity readByCodigo(String c) {
        UsuarioEntity item = null; // Inicia como null para manejar cuando no se encuentre un usuario

        String sql = "SELECT * FROM usuarios WHERE id_usuario= ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, c);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    item = new UsuarioEntity();
                    item.setId_usuario(rs.getString("id_usuario"));
                    item.setNombre(rs.getString("nombre"));
                    item.setEmail(rs.getString("email"));
                    item.setPassword(rs.getString("password"));

                    // Convertir a LocalDate desde Date
                    item.setFecha_creacion(rs.getDate("fecha_creacion").toLocalDate());
                    item.setFecha_ultimo_acceso(rs.getDate("fecha_ultimo_acceso").toLocalDate());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar el usuario: " + e.getMessage());
        }

        return item; // Devuelve null si no se encuentra el usuario
    }


}
