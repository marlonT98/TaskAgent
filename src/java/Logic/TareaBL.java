package Logic;

import DataAccesObject.TareaDao;
import Entity.TareaEntity;
import java.util.List;

public class TareaBL  implements IBaseBL<TareaEntity>{

  private final TareaDao tareaDao = new TareaDao();

  @Override
    public TareaEntity bucarPorId(String id) {
        return tareaDao.readById(id);
    }

  @Override
    public boolean insertar(TareaEntity tarea) {
        return tareaDao.insert(tarea);
    }

  @Override
    public boolean actualizar(TareaEntity tarea) {
        return tareaDao.update(tarea);
    }

  @Override
    public List<TareaEntity> listar() {
        return tareaDao.readAll();
    }
    
  @Override
    public boolean  eliminar( String id ){
        return tareaDao.delete(id);
    }

}