/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofutbol.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
import java.util.*;
import java.sql.*;
import proyectofutbol.entidaddenegocio.*;
public class ResultadoDAL {
    static String obtenerCampos()
    {
        return "r.equipo1, r.resultadoDeportes, r.equipo2"; 
    }
     private static String obtenerSelect(Resultados pResultados)
    {
        String sql;
        sql = "Select ";
        if(pResultados.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pResultados.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Resultados r");
        return sql;
    } 
       private static String agregarOrderBy(Resultados pResultados)
    {
        String sql = " Order by r.Id Desc";
        if(pResultados.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pResultados.getTop_aux() + " ";
        }
        return sql;
    }
            public static int crear(Resultados pResultados) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Resultados(equipo1, resultadoDeportes, equipo2) Values(?,?,?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setString(1, pResultados.getEquipo1());
                st.setString(2, pResultados.getResultadoDeportes());
                st.setString(3, pResultados.getEquipo2());
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
        return result;
    }
             public static int modificar(Resultados pResultados) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Resultados Set equipo1 = ? Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, pResultados.getEquipo1());
                ps.setString(2, pResultados.getResultadoDeportes());
                ps.setString(3, pResultados.getEquipo2());
                ps.setInt(4, pResultados.getId());
                result = ps.executeUpdate();
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
        return result;
    }
     public static int eliminar(Resultados pResultados) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Resultados Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pResultados.getId());
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
         static int asignarDatosResultSet(Resultados pResultados, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pResultados.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pResultados.setEquipo1(pResultSet.getString(pIndex));
        pIndex++;
        pResultados.setResultadoDeportes(pResultSet.getString(pIndex));
        pIndex++;
        pResultados.setEquipo2(pResultSet.getString(pIndex));
        pIndex++;
        return pIndex;
    }
          private static void obtenerDatos(PreparedStatement pPS, ArrayList<Resultados> pResultadoss) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Resultados resultados = new Resultados();
                asignarDatosResultSet(resultados,resultset,0);
                pResultadoss.add(resultados);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
          public static Resultados obtenerPorId(Resultados pResultados) throws Exception
    {
        Resultados resultados = new Resultados();
        ArrayList<Resultados> Resultadoss = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pResultados);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pResultados.getId());
                obtenerDatos(ps, Resultadoss);
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
        if(Resultadoss.size() > 0)
        {
            resultados = Resultadoss.get(0);
        }
        return resultados;
    }
    
    public static ArrayList<Resultados> obtenerTodos() throws Exception
    {
        Resultados resultados = new Resultados();
        ArrayList<Resultados> Resultadoss = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Resultados());
            sql += agregarOrderBy(new Resultados());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, Resultadoss);
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
        
        return Resultadoss;
    }
    
   static void querySelect(Resultados pResultados, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pResultados.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pResultados.getId());
            }
        }
                if(pResultados.getEquipo1()!= null && 
           pResultados.getEquipo1().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Equipo1 Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pResultados.getEquipo1() + "%");
            }
        }
        if(pResultados.getResultadoDeportes()!= null && 
           pResultados.getResultadoDeportes().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.ResultadoDeportes Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pResultados.getResultadoDeportes() + "%");
            }
        }
           
    
    }
           public static ArrayList<Resultados> buscar(Resultados pResultados) throws Exception
    {
        ArrayList<Resultados> resultadoss = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pResultados);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pResultados, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pResultados);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pResultados, utilQuery);
                obtenerDatos(ps, resultadoss);
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
        
        return resultadoss;
    }
}

    
   



