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
public class PremierLigueDAL {
     static String obtenerCampos()
    {
        return "p.Id, p.IdLigas, p.Posicion, p.Equipo, p.Puntos"; 
    }
     private static String obtenerSelect(PremierLigue pPremierLigue)
    {
        String sql;
        sql = "Select ";
        if(pPremierLigue.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pPremierLigue.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From PremierLigue p");
        return sql;
    }
        private static String agregarOrderBy(PremierLigue pPremierLigue)
    {
        String sql = " Order by r.Id Desc";
        if(pPremierLigue.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pPremierLigue.getTop_aux() + " ";
        }
        return sql;
    }
            public static int crear(PremierLigue pPremierLigue) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into PremierLigue(IdLigas, Posicion, Equipo, Puntos) Values(?,?,?,?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setInt(1, pPremierLigue.getIdLigas());
                st.setInt(2, pPremierLigue.getPosicion());
                st.setString(3, pPremierLigue.getEquipo());
                st.setInt(4,pPremierLigue.getPuntos());
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
    public static int modificar(PremierLigue pPremierLigue) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update PremierLigue Set IdLigas = ? Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pPremierLigue.getIdLigas());
                ps.setInt(2, pPremierLigue.getPosicion());
                ps.setString(3,pPremierLigue.getEquipo());
                ps.setInt(4, pPremierLigue.getPuntos());
                ps.setInt(5, pPremierLigue.getId());
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
        public static int eliminar(PremierLigue pPremierLigue) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From PremierLigue Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pPremierLigue.getId());
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
         static int asignarDatosResultSet(PremierLigue pPremierLigue, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pPremierLigue.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pPremierLigue.setIdLigas(pResultSet.getInt(pIndex));
        pIndex++;
        pPremierLigue.setPosicion(pResultSet.getInt(pIndex));
        pIndex++;
        pPremierLigue.setEquipo(pResultSet.getString(pIndex));
        pIndex++;
        pPremierLigue.setPuntos(pResultSet.getInt(pIndex));
        return pIndex;
    }
          private static void obtenerDatos(PreparedStatement pPS, ArrayList<PremierLigue> pPremierLigues) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                PremierLigue premierligue = new PremierLigue();
                asignarDatosResultSet(premierligue,resultset,0);
                pPremierLigues.add(premierligue);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
          public static PremierLigue obtenerPorId(PremierLigue pPremierLigue) throws Exception
    {
        PremierLigue premierligue = new PremierLigue();
        ArrayList<PremierLigue> PremierLigues = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pPremierLigue);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pPremierLigue.getId());
                obtenerDatos(ps, PremierLigues);
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
        if(PremierLigues.size() > 0)
        {
            premierligue = PremierLigues.get(0);
        }
        return premierligue;
    }
    
    public static ArrayList<PremierLigue> obtenerTodos() throws Exception
    {
        PremierLigue premierligue = new PremierLigue();
        ArrayList<PremierLigue>PremierLigues = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new PremierLigue());
            sql += agregarOrderBy(new PremierLigue());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, PremierLigues);
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
        
        return PremierLigues;
    
}
    static void querySelect(PremierLigue pPremierLigue, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pPremierLigue.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pPremierLigue.getId());
            }
        }
         if(pPremierLigue.getIdLigas() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.IdLigas = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pPremierLigue.getIdLigas());
            }
        }
          if(pPremierLigue.getPosicion() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.Posicion = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pPremierLigue.getPosicion());
            }
        }
        
        if(pPremierLigue.getEquipo()!= null && 
           pPremierLigue.getEquipo().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" l.Equipo Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pPremierLigue.getEquipo() + "%");
            }
        }
         if(pPremierLigue.getPuntos() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.Puntos = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pPremierLigue.getPuntos());
            }
        }
    }
           public static ArrayList<PremierLigue> buscar(PremierLigue pPremierLigue) throws Exception
    {
        ArrayList<PremierLigue> PremierLigues = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pPremierLigue);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pPremierLigue, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pPremierLigue);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pPremierLigue, utilQuery);
                obtenerDatos(ps, PremierLigues);
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
        
        return PremierLigues;
    }
}



