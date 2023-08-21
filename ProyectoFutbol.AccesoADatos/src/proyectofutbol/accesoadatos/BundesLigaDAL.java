/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
import  proyectofutbol.entidaddenegocio.BundesLiga;
import java.util.*;
import java.sql.*;
public class BundesLigaDAL {
    static String obtenerCampos()
    {
        return "r.Id, r.IdLigas, r.Posicion, r.Equipo, r.Puntos";
    }       
       private static String obtenerSelect(BundesLiga pBundesLiga)     
       {
           String sql;
           sql = "Select ";
           if(pBundesLiga.getTop_aux()> 0 &&
                   ComunDB.TIPODB==ComunDB.TipoDB.SQLSERVER)
           {
               sql += "Top" + pBundesLiga.getTop_aux()+ "";
           }
           sql += (obtenerCampos() + "From BundesLiga r");
           return sql;
       }
       
       private static String agregarOrderBy(BundesLiga pBundesLiga)
       {
           String sql = "Order by r.Id Desc";
           if(pBundesLiga.getTop_aux() > 0 &&
                   ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
           {
               sql += "Limit" + pBundesLiga.getTop_aux() + "";
           }
           return sql;
       }
        public static int crear(BundesLiga pBundesLiga) throws Exception
        {
            int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql= "Insert Into BundesLiga(IdLigas,Posicion, Equipo, Puntos) Values(?)";
                try(PreparedStatement st = 
                        ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pBundesLiga.getIdLigas()); 
                    st.setInt(2, pBundesLiga.getPosicion());
                    st.setString(3, pBundesLiga.getEquipo());
                    st.setInt(4, pBundesLiga.getPuntos());
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
        public static int modificar(BundesLiga pBundesLiga) throws Exception
        {
               int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                 sql= "Insert Into BundesLiga(IdLigas,Posicion, Equipo, Puntos) Values(?)";
                try(PreparedStatement ps = 
                ComunDB.createPreparedStatement(conn, sql);)
                {
                    ps.setInt(1, pBundesLiga.getIdLigas()); 
                    ps.setInt(2, pBundesLiga.getPosicion());
                    ps.setString(3, pBundesLiga.getEquipo());
                    ps.setInt(4, pBundesLiga.getPuntos());
                      ps.setInt(5, pBundesLiga.getId());
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
        
         public static int eliminar(BundesLiga pBundesLiga) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From BundesLiga Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pBundesLiga.getId());
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
          static int asignarDatosResultSet(BundesLiga pBundesLiga, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pBundesLiga.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pBundesLiga.setIdLigas(pResultSet.getInt(pIndex));
        pIndex++;
        pBundesLiga.setPosicion(pResultSet.getInt(pIndex));
        pIndex++;
        pBundesLiga.setEquipo(pResultSet.getString(pIndex));
         pIndex++;
        pBundesLiga.setPuntos(pResultSet.getInt(pIndex));
         pIndex++;
       
        
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<BundesLiga> pBundesLiga) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                BundesLiga bundesLiga = new BundesLiga();
                asignarDatosResultSet(bundesLiga,resultset,0);
                pBundesLiga.add(bundesLiga);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
    
    public static BundesLiga obtenerPorId(BundesLiga pBundesLiga) throws Exception
    {
        BundesLiga bundesLiga = new BundesLiga();
        ArrayList<BundesLiga> bundesLigas = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pBundesLiga);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pBundesLiga.getId());
                obtenerDatos(ps, bundesLigas);
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
        if(bundesLigas.size() > 0)
        {
            bundesLiga = bundesLigas.get(0);
        }
        return bundesLiga;
    }

    public static ArrayList<BundesLiga> obtenerTodos() throws Exception
    {
        BundesLiga bundesLiga = new BundesLiga();
        ArrayList<BundesLiga> bundesLigas = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new BundesLiga());
            sql += agregarOrderBy(new BundesLiga());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, bundesLigas);
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
        
        return bundesLigas;
    }
    
    static void querySelect(BundesLiga pBundesLiga, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pBundesLiga.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pBundesLiga.getId());
            }
        }
        
        if(pBundesLiga.getEquipo()!= null && 
           pBundesLiga.getEquipo().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Equipo Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pBundesLiga.getEquipo()+ "%");
            }
        }
         if(pBundesLiga.getIdLigas()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.IdLigas = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pBundesLiga.getIdLigas());
            }
        }
          if(pBundesLiga.getPosicion()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Posicion = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pBundesLiga.getPosicion());
            }
        }
           if(pBundesLiga.getPuntos()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Puntos = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pBundesLiga.getPuntos());
            }
        }
    }
    
    public static ArrayList<BundesLiga> buscar(BundesLiga pBundesLiga) throws Exception
    {
        ArrayList<BundesLiga> bundesLigas = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pBundesLiga);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pBundesLiga, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pBundesLiga);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pBundesLiga, utilQuery);
                obtenerDatos(ps, bundesLigas);
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
        
        return bundesLigas;
    }
    
}

