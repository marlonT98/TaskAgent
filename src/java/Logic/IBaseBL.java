/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logic;



import java.util.List;


public interface IBaseBL<T> {
    
    List<T> listar( );
    boolean  eliminar( String id);
    T bucarPorId( String id  );
    boolean insertar( T usuario );
    boolean actualizar( T usuario );

}
