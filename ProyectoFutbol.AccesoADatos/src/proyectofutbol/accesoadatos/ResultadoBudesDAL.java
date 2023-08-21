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

public class ResultadoBudesDAL {
    
    
  static String obtenerCampos() {
        return "r.Id, r.Equipo1, r.ResultadoDeportes, r.Equipo2";
    }

    private static String obtenerSelect(ResultadosBundes pResultadosBundes) {
        String sql;
        sql = "Select ";
        if (pResultadosBundes.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "Top " + pResultadosBundes.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From ResultadosBundes r");
        return sql;
    }

    private static String agregarOrderBy(ResultadosBundes pResultadosBundes) {
        String sql = " Order by r.Id Desc";
        if (pResultadosBundes.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += "Limit " + pResultadosBundes.getTop_aux() + " ";
        }
        return sql;
    }

    public static int crear(ResultadosBundes pResultadosBundes) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Insert Into ResultadosBundes(Equipo1, ResultadoDeportes, Equipo2) Values(?)";
            try (PreparedStatement st
                    = ComunDB.createPreparedStatement(conn, sql);) {
                st.setString(1, pResultadosBundes.getEquipo1());
                st.setString(2, pResultadosBundes.getResultadoDeportes());
                st.setString(3, pResultadosBundes.getEquipo2());
                result = st.executeUpdate();
                st.close();
            } catch (SQLException ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static int modificar(ResultadosBundes pResultadosBundes) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Update ResultadosBundes Set Equipo1, ResultadoDeporte, Equipo2 = ? Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pResultadosBundes.getEquipo1());
                ps.setString(2, pResultadosBundes.getResultadoDeportes());
                ps.setString(3, pResultadosBundes.getEquipo2());
                ps.setInt(4, pResultadosBundes.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static int eliminar(ResultadosBundes pResultadosBundes) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Delete From ResultadosBundes Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pResultadosBundes.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    static int asignarDatosResultSet(ResultadosBundes pResultadosBundes, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pResultadosBundes.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pResultadosBundes.setEquipo1(pResultSet.getString(pIndex));
        pResultadosBundes.setResultadoDeportes(pResultSet.getString(pIndex));
        pResultadosBundes.setEquipo2(pResultSet.getString(pIndex));
        return pIndex;
    }

    private static void obtenerDatos(PreparedStatement pPS, ArrayList<ResultadosBundes> pResultadoBundes) throws Exception {
        try (ResultSet resultset = ComunDB.obtenerResulSet(pPS);) {
            while (resultset.next()) {
                ResultadosBundes resultadosbundes = new ResultadosBundes();
                asignarDatosResultSet(resultadosbundes, resultset, 0);
                pResultadoBundes.add(resultadosbundes);
            }
            resultset.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public static ResultadosBundes obtenerPorId(ResultadosBundes pResultadosBundes) throws Exception {
        ResultadosBundes resultadosbundes = new ResultadosBundes();
        ArrayList<ResultadosBundes> resultadobundes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pResultadosBundes);
            sql += " Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pResultadosBundes.getId());
                obtenerDatos(ps, resultadobundes);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        if (resultadobundes.size() > 0) {
            resultadosbundes = resultadobundes.get(0);
        }
        return resultadosbundes;
    }

    public static ArrayList<ResultadosBundes> obtenerTodos() throws Exception {
        ResultadosBundes resultadosbundes = new ResultadosBundes();
        ArrayList<ResultadosBundes> resultadobundes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new ResultadosBundes());
            sql += agregarOrderBy(new ResultadosBundes());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, resultadobundes);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return resultadobundes;
    }
    
         static void querySelect(ResultadosBundes pResultadosBundes, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pResultadosBundes.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pResultadosBundes.getId());
            }
        }
        
        if(pResultadosBundes.getEquipo1() != null && 
           pResultadosBundes.getEquipo1().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Equipo1 Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pResultadosBundes.getEquipo1() + "%");
            }
        }
     if(pResultadosBundes.getResultadoDeportes() != null && 
           pResultadosBundes.getResultadoDeportes().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.ResultadoDeportes Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                  "%" + pResultadosBundes.getResultadoDeportes() + "%");
            }
        }
      if(pResultadosBundes.getEquipo2() != null && 
           pResultadosBundes.getEquipo2().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Equipo2 Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                  "%" + pResultadosBundes.getEquipo2() + "%");
            }
        }
    }
    
    public static ArrayList<ResultadosBundes> buscar(ResultadosBundes pResultadosBundes) throws Exception
    {
        ArrayList<ResultadosBundes> resultadobundes = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pResultadosBundes);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pResultadosBundes, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pResultadosBundes);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pResultadosBundes, utilQuery);
                obtenerDatos(ps, resultadobundes);
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
        
        return resultadobundes; 
    }
}
