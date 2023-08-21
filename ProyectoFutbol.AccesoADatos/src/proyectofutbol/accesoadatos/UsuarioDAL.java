/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import proyectofutbol.entidaddenegocio.Usuario;
import proyectofutbol.entidaddenegocio.Usuario.EstatusUsuario;

public class UsuarioDAL{
    
    // Metodo para poder encriptar en MD5 la contraseña del usuario
public static String encriptarMD5(String txt) throws Exception 
{
 try {
      StringBuffer sb;
      java.security.MessageDigest md = java.security.MessageDigest
            .getInstance("MD5");
      byte[] array = md.digest(txt.getBytes());
      sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) 
      {
      sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
      .substring(1, 3));
     }
     return sb.toString();
    } catch (java.security.NoSuchAlgorithmException ex) {
    throw ex;
    }
}
    
    static String obtenerCampos()
    {
        return "u.Id, u.IdRol, u.Nombre, u.Apellido, u.Login"
                + ", u.Estatus, u.FechaRegistro";
    }
    
    private static String obtenerSelect(Usuario pUsuario)
    {
        String sql;
        sql = "Select ";
        if(pUsuario.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pUsuario.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Usuario u");
        return sql;
    }
    
    private static String agregarOrderBy(Usuario pUsuario)
    {
        String sql = " Order by r.Id Desc";
        if(pUsuario.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pUsuario.getTop_aux() + " ";
        }
        return sql;
    }
    
    private static boolean existeLogin(Usuario pUsuario) throws Exception {
        boolean existe = false;
        ArrayList<Usuario> usuarios = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pUsuario);  // Obtener la consulta SELECT de la tabla Usuario
            // Concatenar a la consulta SELECT de la tabla Usuario el WHERE y el filtro para saber si existe el login
            sql += " WHERE u.Id<>? AND u.Login=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pUsuario.getId());  // Agregar el parametros a la consulta donde estan el simbolo ? #1 
                ps.setString(2, pUsuario.getLogin());  // Agregar el parametros a la consulta donde estan el simbolo ? #2 
                obtenerDatos(ps, usuarios); // Llenar el ArrayList de USuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement el en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (usuarios.size() > 0) { // Verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            Usuario usuario;
             // Se solucciono tenia valor de 1 cuando debe de ser cero
            usuario = usuarios.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero 
            if (usuario.getId() > 0 && usuario.getLogin().equals(pUsuario.getLogin())) {
                // Si el Id de Usuario es mayor a cero y el Login que se busco en la tabla de Usuario es igual al que solicitamos
                // en los parametros significa que el login ya existe en la base de datos y devolvemos true en la variable "existe"
                existe = true;
            }
        }
        return existe; //Devolver la variable "existe" con el valor true o false si existe o no el Login en la tabla de Usuario de la base de datos

    }
    
    public static int crear(Usuario pUsuario) throws Exception
    {
        int result;
        String sql;
        boolean existe = existeLogin(pUsuario);
        if(existe == false)
        {
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Usuario(IdRol, Nombre, Apellido, Login"
                        + ", Password, Estatus, FechaRegistro) Values"
                        + "(?,?,?,?,?,?,?)";
                try(PreparedStatement st = 
                    ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pUsuario.getId());
                    st.setString(2, pUsuario.getNombre());
                    st.setString(3, pUsuario.getApellido());
                    st.setString(4, pUsuario.getLogin());
                    st.setString(5, encriptarMD5(pUsuario.getPassword()));
                    st.setByte(6, pUsuario.getEstatus());
                    st.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
                    result = st.executeUpdate();
                    st.close();
                }
                catch(SQLException ex)
                {
                    throw ex;
                }
            }
            catch(SQLException ex)
            {
                throw ex;
            }
        }
        else
        {
            result=0;
            throw new RuntimeException("Login ya Existe");
        }
        return result;
    }
    
    static int asignarDatosResultSet(Usuario pUsuario, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT u.Id(indice 1), u.IdRol(indice 2), u.Nombre(indice 3), u.Apellido(indice 4), u.Login(indice 5), 
        // u.Estatus(indice 6), u.FechaRegistro(indice 7) * FROM Usuario
        pIndex++;
        pUsuario.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pUsuario.setIdRol(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pUsuario.setNombre(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pUsuario.setApellido(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pUsuario.setLogin(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        pUsuario.setEstatus(pResultSet.getByte(pIndex)); // index 6
        pIndex++;
        pUsuario.setFechaRegistro(pResultSet.getDate(pIndex).toLocalDate()); // index 7
        return pIndex;
    }

     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Usuario> pUsuarios) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Usuario usuario = new Usuario();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(usuario, resultSet, 0);
                pUsuarios.add(usuario); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
     
    
    public static Usuario login(Usuario pUsuario) throws Exception
    {
        Usuario usuario = new Usuario();
        ArrayList<Usuario> usuarios = new ArrayList();
        String password = encriptarMD5(pUsuario.getPassword());
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pUsuario);
            sql += " Where u.Login = ? And u.Password = ? And "
                    + "u.Estatus = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, pUsuario.getLogin());
                ps.setString(2, password);
                ps.setByte(3, Byte.parseByte("1"));
                obtenerDatos(ps, usuarios);
                ps.close();
            }
            catch(SQLException ex)
            {
                throw ex;
            }
            conn.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        if(usuarios.size() > 0)
        {
            usuario = usuarios.get(0);
        }
        return usuario;
    }
}
