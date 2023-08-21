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
public class Liga1DAL {
    static String obtenerCampos()
    {
        return "l.Id, l.IdLigas, l.Posicion, l.Equipo, l.Puntos"; 
    }
     private static String obtenerSelect(Ligue1 pLigue1)
    {
        String sql;
        sql = "Select ";
        if(pLigue1.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pLigue1.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Ligue1 l");
        return sql;
    }
        private static String agregarOrderBy(Ligue1 pLigue1)
    {
        String sql = " Order by r.Id Desc";
        if(pLigue1.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pLigue1.getTop_aux() + " ";
        }
        return sql;
    }
            public static int crear(Ligue1 pLigue1) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Ligue1(IdLigas, Posicion, Equipo, Puntos) Values(?,?,?,?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setInt(1, pLigue1.getIdLigas());
                st.setInt(2, pLigue1.getPosicion());
                st.setString(3, pLigue1.getEquipo());
                st.setInt(4,pLigue1.getPuntos());
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
    public static int modificar(Ligue1 pLigue1) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Ligue1 Set IdLigas = ? Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pLigue1.getIdLigas());
                ps.setInt(2, pLigue1.getPosicion());
                ps.setString(3,pLigue1.getEquipo());
                ps.setInt(4, pLigue1.getPuntos());
                ps.setInt(5, pLigue1.getId());
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
        public static int eliminar(Ligue1 pLigue1) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Ligue1 Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pLigue1.getId());
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
         static int asignarDatosResultSet(Ligue1 pLigue1, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pLigue1.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pLigue1.setIdLigas(pResultSet.getInt(pIndex));
        pIndex++;
        pLigue1.setPosicion(pResultSet.getInt(pIndex));
        pIndex++;
        pLigue1.setEquipo(pResultSet.getString(pIndex));
        pIndex++;
        pLigue1.setPuntos(pResultSet.getInt(pIndex));
        return pIndex;
    }
          private static void obtenerDatos(PreparedStatement pPS, ArrayList<Ligue1> pLigues1) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Ligue1 ligue1 = new Ligue1();
                asignarDatosResultSet(ligue1,resultset,0);
                pLigues1.add(ligue1);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
          public static Ligue1 obtenerPorId(Ligue1 pLigue1) throws Exception
    {
        Ligue1 ligue1 = new Ligue1();
        ArrayList<Ligue1> ligues1 = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pLigue1);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pLigue1.getId());
                obtenerDatos(ps, ligues1);
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
        if(ligues1.size() > 0)
        {
            ligue1 = ligues1.get(0);
        }
        return ligue1;
    }
    
    public static ArrayList<Ligue1> obtenerTodos() throws Exception
    {
        Ligue1 ligue1 = new Ligue1();
        ArrayList<Ligue1> ligues1 = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Ligue1());
            sql += agregarOrderBy(new Ligue1());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, ligues1);
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
        
        return ligues1;
    }
    
static void querySelect(Ligue1 pLigue1, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pLigue1.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLigue1.getId());
            }
        }
         if(pLigue1.getIdLigas() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.IdLigas = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLigue1.getIdLigas());
            }
        }
          if(pLigue1.getPosicion() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.Posicion = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLigue1.getPosicion());
            }
        }
        
        if(pLigue1.getEquipo()!= null && 
           pLigue1.getEquipo().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" l.Equipo Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pLigue1.getEquipo() + "%");
            }
        }
         if(pLigue1.getPuntos() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.Puntos = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLigue1.getPuntos());
            }
        }
    }
           public static ArrayList<Ligue1> buscar(Ligue1 pLigue1) throws Exception
    {
        ArrayList<Ligue1> ligues1 = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pLigue1);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pLigue1, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pLigue1);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pLigue1, utilQuery);
                obtenerDatos(ps, ligues1);
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
        
        return ligues1;
    }
           
}
   
    
    
    
  
