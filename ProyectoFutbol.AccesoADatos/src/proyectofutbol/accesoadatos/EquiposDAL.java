/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
import  proyectofutbol.entidaddenegocio.Equipos;
import java.util.*;
import java.sql.*;
public class EquiposDAL {
   static String obtenerCampos()
    {
        return "r.IdEquipos, r.IdLigas, r.NombreEquipo, r.PaisEquipo";
    }       
       private static String obtenerSelect(Equipos pEquipos)     
       {
           String sql;
           sql = "Select ";
           if(pEquipos.getTop_aux()> 0 &&
                   ComunDB.TIPODB==ComunDB.TipoDB.SQLSERVER)
           {
               sql += "Top" + pEquipos.getTop_aux()+ "";
           }
           sql += (obtenerCampos() + "From Equipos r");
           return sql;
       }
       
       private static String agregarOrderBy(Equipos pEquipos)
       {
           String sql = "Order by r.IdEquipos Desc";
           if(pEquipos.getTop_aux() > 0 &&
                   ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
           {
               sql += "Limit" + pEquipos.getTop_aux() + "";
           }
           return sql;
       }
        public static int crear(Equipos pEquipos) throws Exception
        {
            int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql= "Insert Into BundesLiga(IdLigas, NombreEquipo, PaisEquipo) Values(?)";
                try(PreparedStatement st = 
                        ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pEquipos.getIdLigas()); 
                    st.setString(2, pEquipos.getNombreEquipo());
                    st.setString(3, pEquipos.getPaisEquipo());
                    
                    result = st.executeUpdate();
                    st.close();
                 }
                catch(SQLException ex)
                {
                    throw ex;
                }
              
            }
              catch (SQLException ex)
                {
                    throw ex;
                }
            return result;         
        }
        public static int modificar(Equipos pEquipos) throws Exception
        {
               int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                 sql= "Insert Into Equipos(IdLigas, NombreEquipo, PaisEquipo) Values(?)";
                try(PreparedStatement ps = 
                ComunDB.createPreparedStatement(conn, sql);)
                {
                    ps.setInt(1, pEquipos.getIdLigas()); 
                    ps.setString(2, pEquipos.getNombreEquipo());
                    ps.setString(3, pEquipos.getPaisEquipo());
                  
                      ps.setInt(5, pEquipos.getIdEquipos());
                    result = ps.executeUpdate();
                    ps.close();
                    
                    
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
              
            
            return result;
        }
        
         public static int eliminar(Equipos pEquipos) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Equipos Where IdEquipos = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pEquipos.getIdEquipos());
                result = ps.executeUpdate();
                ps.close();
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
        return result;
    }
          static int asignarDatosResultSet(Equipos pEquipos, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pEquipos.setIdEquipos(pResultSet.getInt(pIndex));
        pIndex++;
        pEquipos.setIdLigas(pResultSet.getInt(pIndex));
        pIndex++;
        pEquipos.setNombreEquipo(pResultSet.getString(pIndex));
        pIndex++;
        pEquipos.setPaisEquipo(pResultSet.getString(pIndex));
         pIndex++;
        
       
        
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Equipos> pEquipos) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Equipos equipos = new Equipos();
                asignarDatosResultSet(equipos,resultset,0);
                pEquipos.add(equipos);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
    
    public static Equipos obtenerPorId(Equipos pEquipos) throws Exception
    {
        Equipos equipos = new Equipos();
        ArrayList<Equipos> equipo = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pEquipos);
            sql += " Where IdEquipos = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pEquipos.getIdEquipos());
                obtenerDatos(ps, equipo);
                ps.close();
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        if(equipo.size() > 0)
        {
            equipos = equipo.get(0);
        }
        return equipos;
    }


    public static ArrayList<Equipos> obtenerTodos() throws Exception
    {
        Equipos equipos = new Equipos();
        ArrayList<Equipos> equipo = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Equipos());
            sql += agregarOrderBy(new Equipos());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, equipo);
                ps.close();
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        
        return equipo;
    }
    
    static void querySelect(Equipos pEquipos, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pEquipos.getIdEquipos() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.IdEquipos = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pEquipos.getIdEquipos());
            }
        }
        
        if(pEquipos.getNombreEquipo()!= null && 
           pEquipos.getNombreEquipo().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.NombreEquipo Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pEquipos.getNombreEquipo()+ "%");
            }
        }
         if(pEquipos.getPaisEquipo()!= null && 
           pEquipos.getPaisEquipo().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.PaisEquipo Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pEquipos.getNombreEquipo()+ "%");
            }
        }
         if(pEquipos.getIdLigas()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.IdLigas = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pEquipos.getIdLigas());
            }
        }

    }
    
    public static ArrayList<Equipos> buscar(Equipos pEquipos) throws Exception
    {
        ArrayList<Equipos> equipo = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pEquipos);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pEquipos, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pEquipos);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pEquipos, utilQuery);
                obtenerDatos(ps, equipo);
                ps.close();
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        
        return equipo;
    }  

}