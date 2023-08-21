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
public class ResultadoLaLigaDAL {
    
      static String obtenerCampos() {
        return "r.Id, r.Equipo1, r.ResultadoDeportes, r.Equipo2";
    }

    private static String obtenerSelect(ResultadosLaLiga pResultadosLaLiga) {
        String sql;
        sql = "Select ";
        if (pResultadosLaLiga.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "Top " + pResultadosLaLiga.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From ResultadosLaLiga r");
        return sql;
    }

    private static String agregarOrderBy(ResultadosLaLiga pResultadosLaLiga) {
        String sql = " Order by r.Id Desc";
        if (pResultadosLaLiga.getTop_aux() > 0
                && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += "Limit " + pResultadosLaLiga.getTop_aux() + " ";
        }
        return sql;
    }

    public static int crear(ResultadosLaLiga pResultadosLaLiga) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Insert Into ResultadosBundes(Equipo1, ResultadoDeportes, Equipo2) Values(?)";
            try (PreparedStatement st
                    = ComunDB.createPreparedStatement(conn, sql);) {
                st.setString(1, pResultadosLaLiga.getEquipo1());
                st.setString(2, pResultadosLaLiga.getResultadoDeportes());
                st.setString(3, pResultadosLaLiga.getEquipo2());
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

    public static int modificar(ResultadosLaLiga pResultadosLaLiga) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Update ResultadosLaLiga Set Equipo1, ResultadoDeporte, Equipo2 = ? Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pResultadosLaLiga.getEquipo1());
                ps.setString(2, pResultadosLaLiga.getResultadoDeportes());
                ps.setString(3, pResultadosLaLiga.getEquipo2());
                ps.setInt(4, pResultadosLaLiga.getId());
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

    public static int eliminar(ResultadosLaLiga pResultadosLaLiga) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Delete From ResultadosBundes Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pResultadosLaLiga.getId());
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

    static int asignarDatosResultSet(ResultadosLaLiga pResultadosLaLiga, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pResultadosLaLiga.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pResultadosLaLiga.setEquipo1(pResultSet.getString(pIndex));
        pResultadosLaLiga.setResultadoDeportes(pResultSet.getString(pIndex));
        pResultadosLaLiga.setEquipo2(pResultSet.getString(pIndex));
        return pIndex;
    }

    private static void obtenerDatos(PreparedStatement pPS, ArrayList<ResultadosLaLiga> pResultadosLaLiga) throws Exception {
        try (ResultSet resultset = ComunDB.obtenerResulSet(pPS);) {
            while (resultset.next()) {
                ResultadosLaLiga resultadoslaliga = new ResultadosLaLiga();
                asignarDatosResultSet(resultadoslaliga, resultset, 0);
                pResultadosLaLiga.add(resultadoslaliga);
            }
            resultset.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public static ResultadosLaLiga obtenerPorId(ResultadosLaLiga pResultadosLaLiga) throws Exception {
        ResultadosLaLiga resultadoslaliga = new ResultadosLaLiga();
        ArrayList<ResultadosLaLiga> resultadolaliga = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pResultadosLaLiga);
            sql += " Where Id = ?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pResultadosLaLiga.getId());
                obtenerDatos(ps, resultadolaliga);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        if (resultadolaliga.size() > 0) {
            resultadoslaliga = resultadolaliga.get(0);
        }
        return resultadoslaliga;
    }

    public static ArrayList<ResultadosLaLiga> obtenerTodos() throws Exception {
        ResultadosLaLiga resultadoslaliga = new ResultadosLaLiga();
        ArrayList<ResultadosLaLiga> resultadolaliga = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new ResultadosLaLiga());
            sql += agregarOrderBy(new ResultadosLaLiga());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, resultadolaliga);
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return resultadolaliga;
    }



    
  /*  public static ArrayList<ResultadosLaLiga> buscar(ResultadosLaLiga pResultadosLaLiga) throws Exception
    {
        ArrayList<ResultadosLaLiga> resultadoslaliga = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pResultadosLaLiga);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pResultadosLaLiga, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pResultadosLaLiga);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pResultadosLaLiga, utilQuery);
                obtenerDatos(ps, resultadoslaliga);
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
        
        return resultadoslaliga  ;
    }*/
}

    

