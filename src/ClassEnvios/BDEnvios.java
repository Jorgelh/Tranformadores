/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassEnvios;

import BD.BD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jluis
 */
public class BDEnvios {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public static ArrayList<ClassEnv> ListarEnvios() {

        return consultarSQL("select no_envio,to_char(fecha,'mm/dd/yyyy') as fecha from envios where estado = 1 order by no_envio");
    }
    
    public static ArrayList<ClassEnv> HistorialEnvios() {

        return consultarSQL("select no_envio,to_char(fecha,'mm/dd/yyyy') as fecha from envios where estado = 2 order by no_envio");
    }

    private static ArrayList<ClassEnv> consultarSQL(String sql) {
        ArrayList<ClassEnv> list = new ArrayList<ClassEnv>();
        Connection cn = BD.getConnection();
        try {
            ClassEnv p;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = new ClassEnv();
                p.setNo_envio(rs.getInt("no_envio"));
                p.setFecha(rs.getString("fecha"));
                list.add(p);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return list;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static ClassEnv BuscarEnvio(int id) throws SQLException {////////////////aqui
        return BuscarEnvi(id, null);
    }

    public static ClassEnv BuscarEnvi(int id, ClassEnv p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select to_char(fecha,'MM/DD/YYYY') as fecha from envios where no_envio=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new ClassEnv() {
                };
            }
            p.setNo_envio(id);
            p.setFecha(rs.getString("fecha"));
        }
        cnn.close();
        ps.close();
        return p;
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    
    public static void InsertarEnvio (ClassEnv m) throws SQLException{
    Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
    ps= cnn.prepareStatement("insert into envios (no_envio,fecha,estado,fechasys) Values(?,?,1,sysdate)");
    ps.setInt(1,m.getNo_envio());
    ps.setDate(2, new java.sql.Date(m.getFechaD().getTime()));
    ps.executeUpdate();
    cnn.close();
    ps.close();
    
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static boolean actualizarEnvio(ClassEnv p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("update envios set fecha=? where no_envio=" + p.getNo_envio());
        ps.setDate(1, new java.sql.Date(p.getFechaD().getTime()));
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }    
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static boolean cerrarEnvio(ClassEnv p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("update envios set estado=? where no_envio=" + p.getNo_envio());
        ps.setInt(1, p.getEstado());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }    
    
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static ArrayList<ClassEnv> ListarTrabajosEnvios(String a) {
        return SQLDepto("select l.id_lote,l.nolote,l.cantidad,t.pn,t.job,decode(l.prioridad,1,'URGENTE') as prioridad from  lotes l  join trabajo t on l.id = t.id where upper (t.pn) like upper('"+a+"%') and l.envio =0 and l.estado = 1 order by t.pn,l.nolote");
    }
    
    private static ArrayList<ClassEnv> SQLDepto(String sql){
    ArrayList<ClassEnv> list = new ArrayList<ClassEnv>();
    Connection cn = BD.getConnection();
        try {
            ClassEnv t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassEnv();
                 t.setNo_envio(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setLote(rs.getInt("NOLOTE"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setPrioridad(rs.getString("PRIORIDAD"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
} 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 public static void insertarLoteEnvio(ClassEnv d) throws SQLException{
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("insert into lotesenvios(no_envio,id_lote,cantidad) Values(?,?,?)");
    ps.setInt(1, d.getNoenvio());
    ps.setInt(2, d.getIdlote());
    ps.setInt(3, d.getCantidad());
    ps.executeUpdate();
    cnn.close();
    ps.close();   
    }  
 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 public static ArrayList<ClassEnv> ListarEnvios(int a) {
        return SQL("select p.id_lote,upper(p.proceso)as proceso,l.nolote,e.cantidad,t.pn,t.job \n" +
"from procesos p inner join lotes l on p.id_lote = l.id_lote \n" +
"right join trabajo t on l.id = t.id \n" +
"right join lotesenvios e on l.id_lote = e.id_lote\n" +
"where p.id_proceso in(select max(p.id_proceso) as id_proceso from lotes l INNER join procesos p on l.id_lote = p.id_lote join trabajo t on l.id = t.id \n" +
"where l.estado in(1,2)  GROUP BY (l.id_lote)) and e.no_envio = "+a+" order by t.pn,l.nolote");
    }
    
    private static ArrayList<ClassEnv> SQL(String sql){
    ArrayList<ClassEnv> list = new ArrayList<ClassEnv>();
    Connection cn = BD.getConnection();
        try {
            ClassEnv t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassEnv();
                 t.setNo_envio(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setLoteS(rs.getString("NOLOTE"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setProcesosActual(rs.getString("PROCESO"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
} 
 
 
 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
 
public static ArrayList<ClassEnv> ListadoParaelEnvio(int a) {
        return SQL1("(SELECT l.id_lote,upper(PROCESO) as proceso,NO_LOTE as nolote,l.CANTIDAD,PN,JOB,to_char(FECHA,'dd/mm/yy') as fechaauto,DATECODE,SN,SN+l.CANTIDAD-1 AS SN1,NVL(l.fechadata,0) as fechadata FROM ENVIOFUERADEPROGRAMA e INNER JOIN lotesenvios l ON l.id_lote = e.id_lote where l.no_envio= "+a+" \n" +
"union all select l.id_lote,upper(p.proceso)as proceso,to_char(l.nolote),e.cantidad,t.pn,t.job,p.fechaauto,e.datecode,e.SN,e.cantidad+e.sn-1 as SN1, NVL(e.fechadata,0) as fechadata \n" +
"from procesos p inner join lotes l on p.id_lote = l.id_lote \n" +
"right join trabajo t on l.id = t.id \n" +
"right join lotesenvios e on l.id_lote = e.id_lote\n" +
"where p.id_proceso in(select max(p.id_proceso) as id_proceso from lotes l INNER join procesos p on l.id_lote = p.id_lote join trabajo t on l.id = t.id \n" +
"where l.estado in(1,2)  GROUP BY (l.id_lote)) and e.no_envio = "+a+") order by id_lote");
    }
    
    private static ArrayList<ClassEnv> SQL1(String sql){
    ArrayList<ClassEnv> list = new ArrayList<ClassEnv>();
    Connection cn = BD.getConnection();
        try {
            ClassEnv t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassEnv();
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setLoteS(rs.getString("NOLOTE"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setProcesosActual(rs.getString("PROCESO"));
                 t.setFecha(rs.getString("fechaauto"));
                 t.setDatecode(rs.getInt("datecode"));
                 t.setSN(rs.getInt("SN"));
                 t.setSN1(rs.getInt("SN1"));
                 t.setFechadata(rs.getString("fechadata"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
}     
    
    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    public static ClassEnv BuscarDateCode(int id) throws SQLException {////////////////aqui
        return BuscarDate(id, null);
    }

    public static ClassEnv BuscarDate(int id, ClassEnv p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select datecode,fechadata from lotesenvios where id_lote=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new ClassEnv() {
                };
            }
            p.setDatecode(rs.getInt("datecode"));
            p.setFechadata(rs.getString("fechadata"));
        }
        cnn.close();
        ps.close();
        return p;
    }
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    
    public static ArrayList<ClassEnv> ListarEnviosData(int a) {
        return SQ("(SELECT l.id_lote,upper(PROCESO) as proceso,NO_LOTE AS NOLOTE,l.CANTIDAD,PN,JOB,datecode,fechadata FROM ENVIOFUERADEPROGRAMA e INNER JOIN lotesenvios l ON l.id_lote = e.id_lote where l.no_envio= "+a+" \n" +
"union all select p.id_lote,upper(p.proceso)as proceso,TO_CHAR(l.nolote),e.cantidad,t.pn,t.job,e.datecode,e.fechadata \n" +
"from procesos p inner join lotes l on p.id_lote = l.id_lote \n" +
"right join trabajo t on l.id = t.id \n" +
"right join lotesenvios e on l.id_lote = e.id_lote\n" +
"where p.id_proceso in(select max(p.id_proceso) as id_proceso from lotes l INNER join procesos p on l.id_lote = p.id_lote join trabajo t on l.id = t.id \n" +
"where l.estado in(1,2)  GROUP BY (l.id_lote)) and e.no_envio = "+a+") order by pn,nolote");
    }
    
    private static ArrayList<ClassEnv> SQ(String sql){
    ArrayList<ClassEnv> list = new ArrayList<ClassEnv>();
    Connection cn = BD.getConnection();
        try {
            ClassEnv t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassEnv();
                 t.setNo_envio(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setLoteS(rs.getString("NOLOTE"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setProcesosActual(rs.getString("PROCESO"));
                 t.setDatecode(rs.getInt("datecode"));
                 t.setFechadata(rs.getString("fechadata"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
} 
    
    
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
public static ClassEnv BuscarSN(int id) throws SQLException {
        return BuscarSN(id, null);
    }

    public static ClassEnv BuscarSN(int id, ClassEnv p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select SN from lotesenvios where id_lote=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new ClassEnv() {
                };
            }
            p.setSN(rs.getInt("SN"));
        }
        cnn.close();
        ps.close();
        return p;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static ClassEnv BuscarCantidad(int id) throws SQLException {
        return BuscarCantidad(id, null);
    }

    public static ClassEnv BuscarCantidad(int id, ClassEnv p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select cantidad from lotesenvios where id_lote=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new ClassEnv() {
                };
            }
            p.setCantidad(rs.getInt("cantidad"));
        }
        cnn.close();
        ps.close();
        return p;
    }
    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
     public static ArrayList<ClassEnv> ListarEnviosSN(int a) {
        return SN("(SELECT l.id_lote,upper(PROCESO) as proceso,NO_LOTE as nolote,l.CANTIDAD,PN,JOB,SN,SN+l.CANTIDAD-1 AS SN1 FROM ENVIOFUERADEPROGRAMA e INNER JOIN lotesenvios l ON l.id_lote = e.id_lote where l.no_envio= "+a+" \n" +
"union all select p.id_lote,upper(p.proceso)as proceso,to_char(l.nolote),e.cantidad,t.pn,t.job,e.SN,e.cantidad+e.SN-1 as SN1 \n" +
"from procesos p inner join lotes l on p.id_lote = l.id_lote \n" +
"right join trabajo t on l.id = t.id \n" +
"right join lotesenvios e on l.id_lote = e.id_lote\n" +
"where p.id_proceso in(select max(p.id_proceso) as id_proceso from lotes l INNER join procesos p on l.id_lote = p.id_lote join trabajo t on l.id = t.id \n" +
"where l.estado in(1,2)  GROUP BY (l.id_lote)) and e.no_envio = "+a+") order by pn,nolote");
    }
     
     public static ArrayList<ClassEnv> ListarEnviosCantidad(int a) {
        return SN("(SELECT l.id_lote,upper(PROCESO) as proceso,NO_LOTE as nolote,l.CANTIDAD,PN,JOB,SN,SN+l.CANTIDAD-1 AS SN1 FROM ENVIOFUERADEPROGRAMA e INNER JOIN lotesenvios l ON l.id_lote = e.id_lote where l.no_envio= "+a+" \n" +
"union all select p.id_lote,upper(p.proceso)as proceso,to_char(l.nolote),e.cantidad,t.pn,t.job,e.SN,e.cantidad+e.SN-1 as SN1 \n" +
"from procesos p inner join lotes l on p.id_lote = l.id_lote \n" +
"right join trabajo t on l.id = t.id \n" +
"right join lotesenvios e on l.id_lote = e.id_lote\n" +
"where p.id_proceso in(select max(p.id_proceso) as id_proceso from lotes l INNER join procesos p on l.id_lote = p.id_lote join trabajo t on l.id = t.id \n" +
"where l.estado in(1,2)  GROUP BY (l.id_lote)) and e.no_envio = "+a+") order by pn,nolote");
    }
    
    private static ArrayList<ClassEnv> SN(String sql){
    ArrayList<ClassEnv> list = new ArrayList<ClassEnv>();
    Connection cn = BD.getConnection();
        try {
            ClassEnv t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassEnv();
                 t.setNo_envio(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setLoteS(rs.getString("NOLOTE"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setProcesosActual(rs.getString("PROCESO"));
                 t.setSN(rs.getInt("SN"));
                 t.setSN1(rs.getInt("SN1"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
} 
    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    
 public static ArrayList<ClassEnv> ListadoParaagregarPN(int a) {
        return S("SELECT l.id_lote,PROCESO,NO_LOTE,l.CANTIDAD,PN,JOB,to_char(FECHA,'dd/mm/yy') as fechaauto "
                + "FROM ENVIOFUERADEPROGRAMA e INNER JOIN lotesenvios l ON l.id_lote = e.id_lote where l.no_envio= "+a+" ORDER BY pn,NO_LOTE");

        
}

private static ArrayList<ClassEnv> S(String sql){
    ArrayList<ClassEnv> list = new ArrayList<ClassEnv>();
    Connection cn = BD.getConnection();
        try {
            ClassEnv t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassEnv();
                 t.setIdlote(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setLoteS(rs.getString("NO_LOTE"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setProcesosActual(rs.getString("PROCESO"));
                 t.setFecha(rs.getString("fechaauto"));
                
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
}  

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 public static ArrayList<ClassEnv> ListadoEnvioConPNquenoestaenProcesos(int a) {
        return A("(SELECT l.id_lote,PROCESO,NO_LOTE as nolote,l.CANTIDAD,PN,JOB,to_char(FECHA,'dd/mm/yy') as fechaauto FROM ENVIOFUERADEPROGRAMA e INNER JOIN lotesenvios l ON l.id_lote = e.id_lote where l.no_envio= "+a+" \n" +
"union all\n" +
"select e.id_lote,upper(p.proceso)as proceso,TO_CHAR(l.no_lote),e.cantidad,t.pn,t.job,p.fecha||' '||p.hora as fechaauto \n" +
"from proceso p inner join lotes l on p.id_lote = l.id_lote \n" +
"right join trabajos t on l.id = t.id\n" +
"right join lotesenvios e on l.id_lote = e.id_lote\n" +
"where p.id_proceso in(select max(p.id_proceso) as id_proceso from lotes l INNER join proceso p on l.id_lote = p.id_lote join trabajos t on l.id = t.id \n" +
"where l.estado in (2,3)  GROUP BY (l.id_lote)) and e.no_envio = "+a+") ORDER BY pn,NO_LOTE");
        
}

private static ArrayList<ClassEnv> A(String sql){
    ArrayList<ClassEnv> list = new ArrayList<ClassEnv>();
    Connection cn = BD.getConnection();
        try {
            ClassEnv t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassEnv();
                 t.setIdlote(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setLoteS(rs.getString("NOLOTE"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setProcesosActual(rs.getString("PROCESO"));
                 t.setFecha(rs.getString("fechaauto"));
                
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
} 



    
    
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    public static void InsertarProcesoFuera (ClassEnv m) throws SQLException{
    Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
    ps= cnn.prepareStatement("insert into ENVIOFUERADEPROGRAMA (id_lote,job,pn,proceso,cantidad,fecha,no_lote) Values(?,?,?,?,?,sysdate,?)");
    ps.setInt(1,m.getIdlote());
    ps.setString(2,m.getJob());
    ps.setString(3, m.getPN());
    ps.setString(4, m.getProcesosActual());
    ps.setInt(5, m.getCantidad());
    ps.setString(6, m.getLoteS());
    
    //ps.setDate(2, new java.sql.Date(m.getFechaD().getTime()));
    ps.executeUpdate();
    cnn.close();
    ps.close();
    
    }




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static boolean actualizarEnvioFuera(ClassEnv p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("update ENVIOFUERADEPROGRAMA set job = ?, pn = ?, proceso = ?, cantidad = ? , fecha = sysdate ,NO_LOTE = ? where id_lote = " + p.getIdlote());
        //ps.setDate(1, new java.sql.Date(p.getFechaD().getTime()));
          ps.setString(1,p.getJob());
          ps.setString(2, p.getPN());
          ps.setString(3, p.getProcesosActual());
          ps.setInt(4, p.getCantidad());
          ps.setString(5, p.getLoteS());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }  

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
