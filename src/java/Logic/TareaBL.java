package Logic;

import DataAccesObject.TareaDao;
import Entity.TareaEntity;
import java.util.List;

public class TareaBL {

  private final TareaDao tareaDao = new TareaDao();

    public TareaEntity bucarPorId(String id) {
        return tareaDao.readById(id);
    }

    public boolean insertar(TareaEntity tarea) {
        return tareaDao.insert(tarea);
    }

    public boolean actualizar(TareaEntity tarea) {
        return tareaDao.update(tarea);
    }

    public List<TareaEntity> listar() {
        return tareaDao.readAll();
    }
    
    public boolean  eliminar( String id ){
        return tareaDao.delete(id);
    }

}