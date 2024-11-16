
package Logic;

import DataAccesObject.UsuarioDao;
import Entity.UsuarioEntity;
import java.util.List;

public class UsuarioBL implements IBaseBL<UsuarioEntity>{
    
    
    //listar 
    @Override
    public List<UsuarioEntity> listar( ){
        
        UsuarioDao  usuarioDao = new UsuarioDao();
        
        
        return usuarioDao.readAll();
        
        
        
        
    }
    
    //aliminar
    
    @Override
    public boolean  eliminar( String id){
        
        return new UsuarioDao().delete(id);
    }
        
    //buscar por id
    @Override
    public UsuarioEntity  bucarPorId( String id  ) {

      

        return new UsuarioDao().readById(id);

    }
    
    //insertar
    @Override
    public boolean insertar( UsuarioEntity usuario ){
        
        return new UsuarioDao().insert(usuario);
        
    }

        //actualizar
    @Override
    public boolean actualizar( UsuarioEntity usuario ){
        
        return new UsuarioDao().update(usuario);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
