/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */import  proyectofutbol.entidaddenegocio.Futbol;
import java.util.*;
import java.sql.*;
public class FutbolDAL {
     static String obtenerCampos()
    {
        return "r.IdFutbol, r.Titulo, r.Noticias, r.Idligas, r.IdUsuario";
    }       
       private static String obtenerSelect(  Futbol pFutbol)     
       {
           String sql;
           sql = "Select ";
           if(pFutbol.getTop_aux()> 0 &&
                   ComunDB.TIPODB==ComunDB.TipoDB.SQLSERVER)
           {
               sql += "Top" + pFutbol.getTop_aux()+ "";
           }
           sql += (obtenerCampos() + "From Equipos r");
           return sql;
       }
       
       private static String agregarOrderBy(Futbol pFutbol)
       {
           String sql = "Order by r.IdFutbol Desc";
           if(pFutbol.getTop_aux() > 0 &&
                   ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
           {
               sql += "Limit" + pFutbol.getTop_aux() + "";
           }
           return sql;
       }
        public static int crear(Futbol pFutbol) throws Exception
        {
            int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql= "Insert Into Futbol( Titulo,Noticias, Idligas, IdUsuario) Values(?)";
                try(PreparedStatement st = 
                        ComunDB.createPreparedStatement(conn, sql);)
                {
                    
                    st.setString(1, pFutbol.getTitulo());
                    st.setString(2, pFutbol.getNoticias());
                    st.setInt(3, pFutbol.getIdLigas());
                    st.setInt(4, pFutbol.getIdUsuario());
                    
                    
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
        public static int modificar( Futbol pFutbol) throws Exception
        {
               int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                 sql= "Insert Into Futbol(Titulo,Noticias, Idligas, IdUsuario) Values(?)";
                try(PreparedStatement ps = 
                ComunDB.createPreparedStatement(conn, sql);)
                {
                    ps.setInt(1, pFutbol.getIdFutbol()); 
                    ps.setString(2, pFutbol.getTitulo());
                    ps.setString(3, pFutbol.getNoticias());
                    ps.setInt(4, pFutbol.getIdLigas());
                    ps.setInt(5, pFutbol.getIdUsuario());
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
        
         public static int eliminar( Futbol pFutbol) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Futbol Where IdFutbol = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pFutbol.getIdFutbol());
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
          static int asignarDatosResultSet(Futbol pFutbol, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pFutbol.setIdFutbol(pResultSet.getInt(pIndex));
        pIndex++;
        pFutbol.setIdLigas(pResultSet.getInt(pIndex));
        pIndex++;
        pFutbol.setTitulo(pResultSet.getString(pIndex));
        pIndex++;
        pFutbol.setNoticias(pResultSet.getString(pIndex));
        pIndex++;
        pFutbol.setIdUsuario(pResultSet.getInt(pIndex));
        pIndex++;
       
        
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Futbol> pFutbol) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Futbol futbol = new Futbol();
                asignarDatosResultSet(futbol,resultset,0);
                pFutbol.add(futbol);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
    
    public static Futbol obtenerPorId(Futbol pFutbol) throws Exception
    {
        Futbol futbol = new Futbol();
        ArrayList<Futbol> futbols = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pFutbol);
            sql += " Where IdFutbol = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pFutbol.getIdFutbol());
                obtenerDatos(ps, futbols);
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
        if(futbols.size() > 0)
        {
            futbol = futbols.get(0);
        }
        return futbol;
    }


    public static ArrayList<Futbol> obtenerTodos() throws Exception
    {
        Futbol futbol = new Futbol();
        ArrayList<Futbol> futbols = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Futbol());
            sql += agregarOrderBy(new Futbol());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, futbols);
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
        
        return futbols;
    }
    
    static void querySelect(Futbol pFutbol, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pFutbol.getIdFutbol() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.IdFutbol = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pFutbol.getIdFutbol());
            }
        }
        
        if(pFutbol.getTitulo()!= null && 
           pFutbol.getTitulo().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Titulo Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pFutbol.getTitulo()+ "%");
            }
        }
         if(pFutbol.getNoticias()!= null && 
           pFutbol.getNoticias().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Noticias Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pFutbol.getNoticias()+ "%");
            }
        }
         if(pFutbol.getIdLigas()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.IdLigas = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pFutbol.getIdLigas());
            }
        }
   if(pFutbol.getIdUsuario()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.IdUsuario = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pFutbol.getIdUsuario());
            }
        }

    }
    
    public static ArrayList<Futbol> buscar(Futbol pFutbol) throws Exception
    {
        ArrayList<Futbol> futbol = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pFutbol);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pFutbol, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pFutbol);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pFutbol, utilQuery);
                obtenerDatos(ps, futbol);
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
        
        return futbol;
    }  
   
}
