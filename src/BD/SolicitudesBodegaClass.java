/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Class.CerrararTra;
import Class.ClassTrabajos;
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
public class SolicitudesBodegaClass {
    
    
     public static ArrayList<ClassTrabajos> ListarTrabajoSolicitudesBodega(String a , String b) {
        return SQLListas("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,lotes.cantidad as QTYPORLOTE,lotes.nolote,lotes.nolote,decode(lotes.STATUS,1,'PENDIENTE',2,'ENTREGADO',3,'ACEPTADO') as ESTATUS FROM TRABAJO inner join lotes on trabajo.id = lotes.id\n" +
"WHERE UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') and LOTES.estado = 1 and lotes.status is null order by LOTES.id_LOTE" );
    }
     
     public static ArrayList<ClassTrabajos> ListarTrabajoSolicitudesBodegaPendientes(String a , String b) {
        return SQLListas("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,lotes.cantidad as QTYPORLOTE,lotes.nolote,decode(lotes.STATUS,1,'PENDIENTE',2,'ENTREGADO',3,'ACEPTADO') as ESTATUS \n" +
"FROM TRABAJO INNER JOIN lotes on trabajo.id = lotes.id \n" +
"WHERE UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') and LOTES.estado = 1 and lotes.status in (1,2) order by LOTES.id_LOTE" );
    }
     
      public static ArrayList<ClassTrabajos> ListarHistorialTrabajoSolicitudesBodega(String a , String b) {
        return SQLListas("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,lotes.cantidad as QTYPORLOTE,lotes.nolote,lotes.nolote,decode(lotes.STATUS,1,'PENDIENTE',2,'ENTREGADO',3,'ACEPTADO') as ESTATUS \n" +
"FROM TRABAJO INNER JOIN lotes on trabajo.id = lotes.id \n" +
"WHERE UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') and lotes.status = 3 order by LOTES.id_LOTE" );
    }
    
   /* public static ArrayList<ClassTrabajos> ListarTraIniciados(String a , String b) {
        return SQLListas("select ID,PN,JOB,CLIENTE,ESTANDAR,REVISION,NOLOTES,QTYPRODUCCION,QTYPORLOTE,NOLOTE FROM TRABAJO WHERE UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') and estado = 1 and fechainicio IS NOT null order by id" );
    }*/
    private static ArrayList<ClassTrabajos> SQLListas(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setId(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setCliente(rs.getString("CLIENTE"));
                 t.setEstandar(rs.getString("ESTANDAR"));
                 t.setRevision(rs.getString("REVISION"));
                 t.setQTYPORLOTE(rs.getInt("QTYPORLOTE"));
                 t.setNOLOTE(rs.getInt("NOLOTE"));
                 t.setEstado(rs.getString("ESTATUS"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
}    
    
    
    
public static ClassTrabajos buscarTrabajoTrans(int a) throws SQLException{
        return buscarTrabajoTrans(a ,null);
    }
    
    public static ClassTrabajos buscarTrabajoTrans(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select lotes.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,to_char(lotes.fechasolicitud,'dd/mm/yy hh:mi:ss AM') as fechasolicitud,to_char(lotes.fechaentrega,'dd/mm/yy hh:mi:ss AM') as fechaentrega,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,lotes.nolote,lotes.cantidad,decode(LOTES.PRIORIDAD,0,'',1,'URGENTE') as PRIORIDAD, trabajo.nolotes from trabajo inner join lotes on trabajo.id = lotes.id where lotes.ID_LOTE ="+a);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new ClassTrabajos(){
               };
               }
               c.setId(rs.getInt("ID_LOTE"));
               c.setPN(rs.getString("PN"));
               c.setJob(rs.getString("JOB"));
               c.setCliente(rs.getString("CLIENTE"));
               c.setEstandar(rs.getString("ESTANDAR"));
               c.setRevision(rs.getString("REVISION"));
               c.setNOLOTE(rs.getInt("nolote"));
               c.setQTYPORLOTE(rs.getInt("cantidad"));
               c.setPrioridadStrin(rs.getString("PRIORIDAD"));
               c.setQTYDELOTES(rs.getInt("nolotes"));
               c.setFecha(rs.getString("fechasolicitud"));
               c.setFecha2(rs.getString("fechaentrega"));
            }
            cn.close();
            ps.close();
            return c;
}    
    
    
public static boolean IniciarTra(CerrararTra t) throws SQLException{
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE LOTES SET FECHASOLICITUD = sysdate , STATUS = ? WHERE ID_LOTE ="+t.getNo());
        ps.setInt(1,t.getEstado());
        int rowsUpdated = ps.executeUpdate();
        cn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }   

public static boolean EntregaTra(CerrararTra t) throws SQLException{
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE LOTES SET FECHAENTREGA = sysdate , STATUS = ? WHERE ID_LOTE ="+t.getNo());
        ps.setInt(1,t.getEstado());
        int rowsUpdated = ps.executeUpdate();
        cn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    } 





 public static ArrayList<ClassTrabajos> ListarHistorialCalculoIndicador( String a, String b ) {
        return SQLIndicador("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,\n" +
"TRABAJO.REVISION,lotes.cantidad as QTYPORLOTE,lotes.nolote,to_char(lotes.fechasolicitud,'dd/mm/yy  hh:mi:ss AM') as fechasolicitud ,to_char(lotes.fechaentrega,'dd/mm/yy  hh:mi:ss AM') as fechaentrega,"
                + "(TO_DATE(lotes.fechaentrega,'dd/mm/yy')-TO_DATE(lotes.fechasolicitud,'dd/mm/yy')) as DIAS FROM TRABAJO INNER JOIN lotes on trabajo.id = lotes.id \n" +
"WHERE  lotes.status = 3  and fechasolicitud BETWEEN TO_DATE('"+a+"','dd/mm/yy') and TO_DATE('"+b+"','dd/mm/yy') order by LOTES.id_LOTE" );
    }
    
  
    private static ArrayList<ClassTrabajos> SQLIndicador(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setId(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setCliente(rs.getString("CLIENTE"));
                 t.setEstandar(rs.getString("ESTANDAR"));
                 t.setRevision(rs.getString("REVISION"));
                 t.setQTYPORLOTE(rs.getInt("QTYPORLOTE"));
                 t.setNOLOTE(rs.getInt("NOLOTE"));
                 t.setFecha(rs.getString("fechasolicitud"));
                 t.setFecha2(rs.getString("fechaentrega"));
                 t.setDiasPro(rs.getInt("dias"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
}    





public static boolean AceptarEntrega(CerrararTra t) throws SQLException{
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE LOTES SET  STATUS = ? WHERE ID_LOTE ="+t.getNo());
        ps.setInt(1,t.getEstado());
        int rowsUpdated = ps.executeUpdate();
        cn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    } 




 
}
