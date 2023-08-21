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
public class ResultdoPremierDAL {
    
      static String obtenerCampos() {
        return "r.Id, r.Equipo1, r.ResultadoDeportes, r.Equipo2";
    }

    private static String obtenerSelect(ResultadosPremier pResultadosPremier) {
        String sql;
        sql = "Select ";
        if (pResultadosPremier.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "Top " + pResultadosPremier.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From ResultadosPremier r");
        return sql;
    }

    private static String agregarOrderBy(ResultadosPremier pResultadosPremier) {
        String sql = " Order by r.Id Desc";
        if (pResultadosPremier.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += "Limit " + pResultadosPremier.getTop_aux() + " ";
        }
        return sql;
    }

    public static int crear(ResultadosPremier pResultadosPremier) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Insert Into ResultadosPremier(Equipo1, ResultadoDeportes, Equipo2) Values(?)";
            try (PreparedStatement st
                    = ComunDB.createPreparedStatement(conn, sql);) {
                st.setString(1, pResultadosPremier.getEquipo1());
                st.setString(2, pResultadosPremier.getResultadoDeportes());
                st.setString(3, pResultadosPremier.getEquipo2());
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

    public static int modificar(ResultadosPremier pResultadosPremier) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Update ResultadosPremier Set Equipo1, ResultadoDeporte, Equipo2 = ? Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pResultadosPremier.getEquipo1());
                ps.setString(2, pResultadosPremier.getResultadoDeportes());
                ps.setString(3, pResultadosPremier.getEquipo2());
                ps.setInt(4, pResultadosPremier.getId());
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

    public static int eliminar(ResultadosPremier pResultadosPremier) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Delete From ResultadosPremier Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pResultadosPremier.getId());
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

    static int asignarDatosResultSet(ResultadosPremier pResultadosPremier, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pResultadosPremier.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pResultadosPremier.setEquipo1(pResultSet.getString(pIndex));
        pResultadosPremier.setResultadoDeportes(pResultSet.getString(pIndex));
        pResultadosPremier.setEquipo2(pResultSet.getString(pIndex));
        return pIndex;
    }

    private static void obtenerDatos(PreparedStatement pPS, ArrayList<ResultadosPremier> pResultadosPremier) throws Exception {
        try (ResultSet resultset = ComunDB.obtenerResulSet(pPS);) {
            while (resultset.next()) {
                ResultadosPremier resultadospremier = new ResultadosPremier();
                asignarDatosResultSet(resultadospremier, resultset, 0);
                pResultadosPremier.add(resultadospremier);
            }
            resultset.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public static ResultadosPremier obtenerPorId(ResultadosPremier pResultadosPremier) throws Exception {
        ResultadosPremier resultadospremier = new ResultadosPremier();
        ArrayList<ResultadosPremier> resultadopremier = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pResultadosPremier);
            sql += " Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pResultadosPremier.getId());
                obtenerDatos(ps, resultadopremier);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        if (resultadopremier.size() > 0) {
            resultadospremier = resultadopremier.get(0);
        }
        return resultadospremier;
    }

    public static ArrayList<ResultadosPremier> obtenerTodos() throws Exception {
        ResultadosPremier resultadospremier = new ResultadosPremier();
        ArrayList<ResultadosPremier> resultadopremier = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new ResultadosPremier());
            sql += agregarOrderBy(new ResultadosPremier());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, resultadopremier);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return resultadopremier;
    }

    /*      static void querySelect(ResultadosPremier pResultadosPremier, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pResultadosPremier.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" r.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pResultadosPremier.getId());
            }
        }
        
        if(pResultadosPremier.getNombre() != null && 
           pResultadosPremier.getNombre().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Nombre Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pResultadosPremier.getNombre() + "%");
            }
        }
    }

    
    public static ArrayList<ResultadosPremier> buscar(ResultadosPremier pResultadosPremier) throws Exception
    {
        ArrayList<ResultadosPremier> resultadospremier = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pResultadosPremier);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pResultadosPremier, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pResultadosPremier);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pResultadosPremier, utilQuery);
                obtenerDatos(ps, resultadospremier);
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
        
        return resultadospremier  ;
    }*/
}

    

