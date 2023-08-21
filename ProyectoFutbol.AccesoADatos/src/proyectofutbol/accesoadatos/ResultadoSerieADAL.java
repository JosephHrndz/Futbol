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
public class ResultadoSerieADAL {
    
        static String obtenerCampos() {
        return "r.Id, r.Equipo1, r.ResultadoDeportes, r.Equipo2";
    }

    private static String obtenerSelect(ResultadosSerieA pResultadosSerieA) {
        String sql;
        sql = "Select ";
        if (pResultadosSerieA.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "Top " + pResultadosSerieA.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From ResultadosSerieA r");
        return sql;
    }

    private static String agregarOrderBy(ResultadosSerieA pResultadosSerieA) {
        String sql = " Order by r.Id Desc";
        if (pResultadosSerieA.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += "Limit " + pResultadosSerieA.getTop_aux() + " ";
        }
        return sql;
    }

    public static int crear(ResultadosSerieA pResultadosSerieA) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Insert Into ResultadosSerieA(Equipo1, ResultadoDeportes, Equipo2) Values(?)";
            try (PreparedStatement st
                    = ComunDB.createPreparedStatement(conn, sql);) {
                st.setString(1, pResultadosSerieA.getEquipo1());
                st.setString(2, pResultadosSerieA.getResultadoDeportes());
                st.setString(3, pResultadosSerieA.getEquipo2());
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

    public static int modificar(ResultadosSerieA pResultadosSerieA) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Update ResultadosSerieA Set Equipo1, ResultadoDeporte, Equipo2 = ? Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pResultadosSerieA.getEquipo1());
                ps.setString(2, pResultadosSerieA.getResultadoDeportes());
                ps.setString(3, pResultadosSerieA.getEquipo2());
                ps.setInt(4, pResultadosSerieA.getId());
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

    public static int eliminar(ResultadosSerieA pResultadosSerieA) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Delete From ResultadosSerieA Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pResultadosSerieA.getId());
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

    static int asignarDatosResultSet(ResultadosSerieA pResultadosSerieA, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pResultadosSerieA.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pResultadosSerieA.setEquipo1(pResultSet.getString(pIndex));
        pResultadosSerieA.setResultadoDeportes(pResultSet.getString(pIndex));
        pResultadosSerieA.setEquipo2(pResultSet.getString(pIndex));
        return pIndex;
    }

    private static void obtenerDatos(PreparedStatement pPS, ArrayList<ResultadosSerieA> pResultadosSerieA) throws Exception {
        try (ResultSet resultset = ComunDB.obtenerResulSet(pPS);) {
            while (resultset.next()) {
                ResultadosSerieA resultadosseriea = new ResultadosSerieA();
                asignarDatosResultSet(resultadosseriea, resultset, 0);
                pResultadosSerieA.add(resultadosseriea);
            }
            resultset.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public static ResultadosSerieA obtenerPorId(ResultadosSerieA pResultadosSerieA) throws Exception {
        ResultadosSerieA resultadosseriea = new ResultadosSerieA();
        ArrayList<ResultadosSerieA> resultadoseriea = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pResultadosSerieA);
            sql += " Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pResultadosSerieA.getId());
                obtenerDatos(ps, resultadoseriea);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        if (resultadoseriea.size() > 0) {
            resultadosseriea = resultadoseriea.get(0);
        }
        return resultadosseriea;
    }

    public static ArrayList<ResultadosSerieA> obtenerTodos() throws Exception {
        ResultadosSerieA resultadosseriea = new ResultadosSerieA();
        ArrayList<ResultadosSerieA> resultadoseriea = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new ResultadosSerieA());
            sql += agregarOrderBy(new ResultadosSerieA());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, resultadoseriea);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return resultadoseriea;
    }



/* 
    public static ArrayList<ResultadosSerieA> buscar(ResultadosSerieA pResultadosSerieA) throws Exception
    {
        ArrayList<ResultadosSerieA> resultadosseriea = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pResultadosSerieA);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pResultadosSerieA, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pResultadosSerieA);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pResultadosSerieA, utilQuery);
                obtenerDatos(ps, resultadosseriea);
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
        
        return resultadosseriea  ;
    } */
}
   
    

