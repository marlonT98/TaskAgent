
package Logic;

import DataAccesObject.UsuarioDao;
import Entity.UsuarioEntity;
import java.util.List;

public class UsuarioBL {
    
    
    //listar 
    public List<UsuarioEntity> listar( ){
        
        UsuarioDao  usuarioDao = new UsuarioDao();
        
        
        return usuarioDao.readAll();
        
        
        
        
    }
    
    //aliminar
    
    public boolean  eliminar( String id){
        
        return new UsuarioDao().delete(id);
    }
        
    //buscar por id
    public UsuarioEntity  bucarPorId( String id  ) {

      

        return new UsuarioDao().readByCodigo(id);

    }
    
    //insertar
    public boolean insertar( UsuarioEntity usuario ){
        
        return new UsuarioDao().insert(usuario);
        
    }

        //actualizar
    public boolean actualizar( UsuarioEntity usuario ){
        
        return new UsuarioDao().update(usuario);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
