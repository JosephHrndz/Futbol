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
public class LigasDAL {
  static String obtenerCampos()
    {
        return "l.idLigas, l.nombreLigas, l.paisLiga"; 
    }
     private static String obtenerSelect(Ligas pLigas)
    {
        String sql;
        sql = "Select ";
        if(pLigas.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pLigas.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Ligas l");
        return sql;
    } 
       private static String agregarOrderBy(Ligas pLigas)
    {
        String sql = " Order by l.IdLigas Desc";
        if(pLigas.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pLigas.getTop_aux() + " ";
        }
        return sql;
    }
            public static int crear(Ligas pLigas) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Ligas(nombreLiga, paisLiga) Values(?,?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setString(1, pLigas.getNombreLiga());
                st.setString(2, pLigas.getPaisLiga());
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
             public static int modificar(Ligas pLigas) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Ligas Set nombreLigas = ? Where IdLigas = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1,pLigas.getNombreLiga());
                ps.setString(2,pLigas.getPaisLiga());
                ps.setInt(3, pLigas.getIdLigas());
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
     public static int eliminar(Ligas pLigas) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Ligas Where IdLigas = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pLigas.getIdLigas());
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
         static int asignarDatosResultSet(Ligas pLigas, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pLigas.setIdLigas(pResultSet.getInt(pIndex));
        pIndex++;
        pLigas.setNombreLiga(pResultSet.getString(pIndex));
        pIndex++;
        pLigas.setPaisLiga(pResultSet.getString(pIndex));
        pIndex++;
        return pIndex;
    }
          private static void obtenerDatos(PreparedStatement pPS, ArrayList<Ligas> pLigass) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Ligas ligas = new Ligas();
                asignarDatosResultSet(ligas,resultset,0);
                pLigass.add(ligas);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
          public static Ligas obtenerPorId(Ligas pLigas) throws Exception
    {
        Ligas ligas = new Ligas();
        ArrayList<Ligas> ligass = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pLigas);
            sql += " Where IdLigas = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pLigas.getIdLigas());
                obtenerDatos(ps, ligass);
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
        if(ligass.size() > 0)
        {
            ligas = ligass.get(0);
        }
        return ligas;
    }
    
    public static ArrayList<Ligas> obtenerTodos() throws Exception
    {
        Ligas ligas = new Ligas();
        ArrayList<Ligas> ligass = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Ligas());
            sql += agregarOrderBy(new Ligas());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, ligass);
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
        
        return ligass;
    }
    static void querySelect(Ligas pLigas, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pLigas.getIdLigas() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" l.IdLigas = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pLigas.getIdLigas());
            }
        }
                if(pLigas.getNombreLiga()!= null && 
           pLigas.getNombreLiga().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" l.NombreLiga Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pLigas.getNombreLiga() + "%");
            }
        }
        if(pLigas.getPaisLiga()!= null && 
           pLigas.getPaisLiga().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" l.PaisLiga Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pLigas.getPaisLiga() + "%");
            }
        }
           
    
    }
           public static ArrayList<Ligas> buscar(Ligas pLigas) throws Exception
    {
        ArrayList<Ligas> ligass = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pLigas);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pLigas, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pLigas);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pLigas, utilQuery);
                obtenerDatos(ps, ligass);
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
        
        return ligass;
    }
}

    
    


