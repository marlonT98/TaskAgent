package DataAccesObject;

import Entity.TareaEntity;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD sobre las tareas.
 */
public class TareaDao extends ConexionMySQL implements IBaseDao<TareaEntity>{

    // Método para mapear un ResultSet a una entidad TareaEntity
  private TareaEntity mapResultSetToTarea(ResultSet rs) throws SQLException {
    TareaEntity tarea = new TareaEntity();
    tarea.setId_tarea(rs.getString("id_tarea"));
    tarea.setFk_usuario(rs.getString("fk_usuario"));
    tarea.setTitulo(rs.getString("titulo"));
    tarea.setDescripcion(rs.getString("descripcion"));
    tarea.setFecha_creacion(rs.getDate("fecha_creacion").toLocalDate());
    tarea.setFecha_vencimiento(rs.getDate("fecha_vencimiento").toLocalDate());

    // Manejar posibles errores de conversión de estado
    try {
        tarea.setEstado(TareaEntity.Estado.valueOf(rs.getString("estado").toUpperCase()));
    } catch (IllegalArgumentException e) {
        // Si el valor no es válido, asignar un valor predeterminado o loguear el error
        System.out.println("Valor inválido para el estado: " + rs.getString("estado"));
        tarea.setEstado(TareaEntity.Estado.POR_HACER);  // Valor predeterminado
    }

    // Manejar la conversión de prioridad
    try {
        tarea.setPrioridad(TareaEntity.Prioridad.valueOf(rs.getString("prioridad").toUpperCase()));
    } catch (IllegalArgumentException e) {
        System.out.println("Valor inválido para la prioridad: " + rs.getString("prioridad"));
        tarea.setPrioridad(TareaEntity.Prioridad.Media);  // Valor predeterminado
    }

    return tarea;
}


    // Obtener todas las tareas
  @Override
    public List<TareaEntity> readAll() {
        List<TareaEntity> lstTareas = new ArrayList<>();
        String SQL = "SELECT * FROM tareas";
        try (Statement st = getConexion().createStatement(); ResultSet rs = st.executeQuery(SQL)) {
            while (rs.next()) {
                lstTareas.add(mapResultSetToTarea(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las tareas: " + e.getMessage());
        }
        return lstTareas;
    }

    // Insertar una nueva tarea
  @Override
    public boolean insert(TareaEntity tarea) {
        String sql = "INSERT INTO tareas (fk_usuario, titulo, descripcion, fecha_creacion, fecha_vencimiento, estado, prioridad) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, tarea.getFk_usuario());
            pst.setString(2, tarea.getTitulo());
            pst.setString(3, tarea.getDescripcion());
            pst.setDate(4, Date.valueOf(tarea.getFecha_creacion()));
            pst.setDate(5, Date.valueOf(tarea.getFecha_vencimiento()));
            pst.setString(6, tarea.getEstado().name());
            pst.setString(7, tarea.getPrioridad().name());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar la tarea: " + e.getMessage());
            return false;
        }
    }

    // Actualizar una tarea existente
  @Override
    public boolean update(TareaEntity tarea) {
        String sql = "UPDATE tareas SET fk_usuario = ?, titulo = ?, descripcion = ?, fecha_creacion = ?, fecha_vencimiento = ?, estado = ?, prioridad = ? WHERE id_tarea = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, tarea.getFk_usuario());
            pst.setString(2, tarea.getTitulo());
            pst.setString(3, tarea.getDescripcion());
            pst.setDate(4, Date.valueOf(tarea.getFecha_creacion()));
            pst.setDate(5, Date.valueOf(tarea.getFecha_vencimiento()));
            pst.setString(6, tarea.getEstado().name());
            pst.setString(7, tarea.getPrioridad().name());
            pst.setString(8, tarea.getId_tarea());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar la tarea: " + e.getMessage());
            return false;
        }
    }

    // Eliminar una tarea
  @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM tareas WHERE id_tarea = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar la tarea: " + e.getMessage());
            return false;
        }
    }

    // Obtener una tarea por ID
    public TareaEntity readById(String id) {
        TareaEntity tarea = null;
        String sql = "SELECT * FROM tareas WHERE id_tarea = ?";
        try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    tarea = mapResultSetToTarea(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la tarea: " + e.getMessage());
        }
        return tarea;
    }
}
