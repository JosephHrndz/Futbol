/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
 import proyectofutbol.entidaddenegocio.LaLiga;
import java.util.*;
import java.sql.*;
public class LaLigaDAL {
       static String obtenerCampos()
    {
        return "r.Id, r.IdLigas, r.Posicion, r.Equipo, r.Puntos";
    }       
       private static String obtenerSelect(LaLiga pLaLiga)     
       {
           String sql;
           sql = "Select ";
           if(pLaLiga.getTop_aux()> 0 &&
                   ComunDB.TIPODB==ComunDB.TipoDB.SQLSERVER)
           {
               sql += "Top" + pLaLiga.getTop_aux()+ "";
           }
           sql += (obtenerCampos() + "From LaLiga r");
           return sql;
       }
       
       private static String agregarOrderBy(LaLiga pLaLiga)
       {
           String sql = "Order by r.Id Desc";
           if(pLaLiga.getTop_aux() > 0 &&
                   ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
           {
               sql += "Limit" + pLaLiga.getTop_aux() + "";
           }
           return sql;
       }
        public static int crear(LaLiga pLaLiga) throws Exception
        {
            int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql= "Insert Into LaLiga(IdLigas,Posicion, Equipo, Puntos) Values(?)";
                try(PreparedStatement st = 
                        ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pLaLiga.getIdLigas()); 
                    st.setInt(2, pLaLiga.getPosicion());
                    st.setString(3, pLaLiga.getEquipo());
                    st.setInt(4, pLaLiga.getPuntos());
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
        public static int modificar(LaLiga pLaLiga) throws Exception
        {
               int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                 sql= "Insert Into LaLiga(IdLigas,Posicion, Equipo, Puntos) Values(?)";
                try(PreparedStatement ps = 
                ComunDB.createPreparedStatement(conn, sql);)
                {
                    ps.setInt(1, pLaLiga.getIdLigas()); 
                    ps.setInt(2, pLaLiga.getPosicion());
                    ps.setString(3, pLaLiga.getEquipo());
                    ps.setInt(4, pLaLiga.getPuntos());
                      ps.setInt(5, pLaLiga.getId());
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
        
         public static int eliminar(LaLiga pLaLiga) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From LaLiga Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pLaLiga.getId());
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
          static int asignarDatosResultSet(LaLiga pLaLiga, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pLaLiga.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pLaLiga.setIdLigas(pResultSet.getInt(pIndex));
        pIndex++;
        pLaLiga.setPosicion(pResultSet.getInt(pIndex));
        pIndex++;
        pLaLiga.setEquipo(pResultSet.getString(pIndex));
         pIndex++;
        pLaLiga.setPuntos(pResultSet.getInt(pIndex));
         pIndex++;
       
        
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<LaLiga> pLaLiga) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                LaLiga laLiga = new LaLiga();
                asignarDatosResultSet(laLiga,resultset,0);
                pLaLiga.add(laLiga);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
    
    public static LaLiga obtenerPorId(LaLiga pLaLiga) throws Exception
    {
        LaLiga laLiga = new LaLiga();
        ArrayList<LaLiga> laLigas = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pLaLiga);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pLaLiga.getId());
                obtenerDatos(ps, laLigas);
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
        if(laLigas.size() > 0)
        {
            laLiga = laLigas.get(0);
        }
        return laLiga;
    }

    public static ArrayList<LaLiga> obtenerTodos() throws Exception
    {
        LaLiga laLiga = new LaLiga();
        ArrayList<LaLiga> laLigas = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new LaLiga());
            sql += agregarOrderBy(new LaLiga());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, laLigas);
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
        
        return laLigas;
    }
    
    static void querySelect(LaLiga pLaLiga, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pLaLiga.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLaLiga.getId());
            }
        }
        
        if(pLaLiga.getEquipo()!= null && 
           pLaLiga.getEquipo().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Equipo Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pLaLiga.getEquipo()+ "%");
            }
        }
         if(pLaLiga.getIdLigas()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.IdLigas = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLaLiga.getIdLigas());
            }
        }
          if(pLaLiga.getPosicion()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Posicion = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLaLiga.getPosicion());
            }
        }
           if(pLaLiga.getPuntos()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Puntos = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLaLiga.getPuntos());
            }
        }
    }
    
    public static ArrayList<LaLiga> buscar(LaLiga pLaLiga) throws Exception
    {
        ArrayList<LaLiga> laLigas = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pLaLiga);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pLaLiga, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pLaLiga);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pLaLiga, utilQuery);
                obtenerDatos(ps, laLigas);
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
        
        return laLigas;
    }
}
