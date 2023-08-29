/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
 import proyectofutbol.entidaddenegocio.SerieA;
import java.util.*;
import java.sql.*;
public class SerieADAL {
    
 static String obtenerCampos()
    {
        return "r.Id, r.IdLigas, r.Posicion, r.Equipo, r.Puntos";
    }       
       private static String obtenerSelect(SerieA pSerieA)     
       {
           String sql;
           sql = "Select ";
           if(pSerieA.getTop_aux()> 0 &&
                   ComunDB.TIPODB==ComunDB.TipoDB.SQLSERVER)
           {
               sql += "Top" + pSerieA.getTop_aux()+ "";
           }
           sql += (obtenerCampos() + "From SerieA r");
           return sql;
       }
       
       private static String agregarOrderBy(SerieA pSerieA)
       {
           String sql = "Order by r.Id Desc";
           if(pSerieA.getTop_aux() > 0 &&
                   ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
           {
               sql += "Limit" + pSerieA.getTop_aux() + "";
           }
           return sql;
       }
        public static int crear(SerieA pSerieA) throws Exception
        {
            int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql= "Insert Into SerieA(IdLigas,Posicion, Equipo, Puntos) Values(?)";
                try(PreparedStatement st = 
                        ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1, pSerieA.getIdLigas()); 
                    st.setInt(2, pSerieA.getPosicion());
                    st.setString(3, pSerieA.getEquipo());
                    st.setInt(4, pSerieA.getPuntos());
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
        public static int modificar(SerieA pSerieA) throws Exception
        {
               int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                 sql= "Insert Into SerieA(IdLigas,Posicion, Equipo, Puntos) Values(?)";
                try(PreparedStatement ps = 
                ComunDB.createPreparedStatement(conn, sql);)
                {
                    ps.setInt(1, pSerieA.getIdLigas()); 
                    ps.setInt(2, pSerieA.getPosicion());
                    ps.setString(3, pSerieA.getEquipo());
                    ps.setInt(4, pSerieA.getPuntos());
                      ps.setInt(5, pSerieA.getId());
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
        
         public static int eliminar(SerieA pSerieA) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From SerieA Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pSerieA.getId());
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
          static int asignarDatosResultSet(SerieA pSerieA, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pSerieA.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pSerieA.setIdLigas(pResultSet.getInt(pIndex));
        pIndex++;
        pSerieA.setPosicion(pResultSet.getInt(pIndex));
        pIndex++;
        pSerieA.setEquipo(pResultSet.getString(pIndex));
         pIndex++;
        pSerieA.setPuntos(pResultSet.getInt(pIndex));
         pIndex++;
       
        
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<SerieA> pSerieA) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                SerieA seriea = new SerieA();
                asignarDatosResultSet(seriea,resultset,0);
                pSerieA.add(seriea);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
    
    public static SerieA obtenerPorId(SerieA pSerieA) throws Exception
    {
        SerieA seriea = new SerieA();
        ArrayList<SerieA> serieas = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pSerieA);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pSerieA.getId());
                obtenerDatos(ps, serieas);
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
        if(serieas.size() > 0)
        {
            seriea = serieas.get(0);
        }
        return seriea;
    }

    public static ArrayList<SerieA> obtenerTodos() throws Exception
    {
        SerieA seriea = new SerieA();
        ArrayList<SerieA> serieas = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new SerieA());
            sql += agregarOrderBy(new SerieA());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, serieas);
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
        
        return serieas;
    }
    
    static void querySelect(SerieA pSerieA, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pSerieA.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pSerieA.getId());
            }
        }
        
        if(pSerieA.getEquipo()!= null && 
           pSerieA.getEquipo().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Equipo Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pSerieA.getEquipo()+ "%");
            }
        }
         if(pSerieA.getIdLigas()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.IdLigas = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pSerieA.getIdLigas());
            }
        }
          if(pSerieA.getPosicion()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Posicion = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pSerieA.getPosicion());
            }
        }
           if(pSerieA.getPuntos()> 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Puntos = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pSerieA.getPuntos());
            }
        }
    }
    
    public static ArrayList<SerieA> buscar(SerieA pSerieA) throws Exception
    {
        ArrayList<SerieA> seriea = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pSerieA);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pSerieA, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pSerieA);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pSerieA, utilQuery);
                obtenerDatos(ps, seriea);
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
        
        return seriea;
    }
}

