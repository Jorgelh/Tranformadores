/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Class.CerrararTra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Class.ClassTrabajos;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jluis
 */
public class InsertTrabajosTransformadores {
    
   
    
    
    /*public static void InsertarTrabajo(ClassTrabajos t) throws SQLException{
        Connection con = BD.getConnection();
        PreparedStatement ps = null;
        {     
        ps = con.prepareStatement("insert into trabajo values(idtrabajo.nextval,?,?,?,?,?,?,?,?,?,1,sysdate,null)");
        //ps.setInt(1,t.getId());
        ps.setString(1, t.getPN());
        ps.setString(2, t.getJob());
        ps.setString(3, t.getCliente());
        ps.setInt(4, t.getQtyproduccion());
        ps.setInt(5, t.getQtycliente());
        ps.setInt(6, t.getEstandarint());
        ps.setString(7, t.getRevision());
        ps.setDate(8, new java.sql.Date(t.getFechain().getTime()));
        ps.setString(9, t.getComentarios());
       
        ps.executeUpdate();
        }
        con.close();
        ps.close(); 
    }*/
    public static void InsertarTrabajoTrans(ClassTrabajos t) throws SQLException{
        Connection con = BD.getConnection();
        PreparedStatement ps = null;
        {     
        ps = con.prepareStatement("insert into trabajo values(?,?,?,?,?,?,?,?,?,?,?,1,sysdate,?)");
        ps.setInt(1,t.getId());
        ps.setString(2, t.getPN());
        ps.setString(3, t.getJob());
        ps.setString(4, t.getCliente());
        ps.setInt(5, t.getQtyproduccion());
        ps.setInt(6, t.getQtycliente());
        ps.setInt(7, t.getEstandarint());
        ps.setString(8, t.getRevision());
        ps.setInt(9, t.getQTYDELOTES());
        ps.setDate(10, new java.sql.Date(t.getFechain().getTime()));
        ps.setString(11, t.getComentarios());
        ps.setInt(12, t.getPrioridad());
        ps.executeUpdate();
        }
        con.close();
        ps.close(); 
    }

    public static ArrayList<ClassTrabajos> ListarHistorial(String a) {
        return SQL("select LOTES.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,TRABAJO.NOLOTES,TRABAJO.QTYPRODUCCION,LOTES.CANTIDAD as QTYPORLOTE,LOTES.NOLOTE FROM TRABAJO INNER JOIN LOTES ON TRABAJO.ID = LOTES.ID WHERE UPPER(TRABAJO.PN) LIKE UPPER('"+a+"%') and LOTES.ESTADO = 2 and LOTES.FECHAINICIO is not null order by TRABAJO.ID" );
    }
    
    private static ArrayList<ClassTrabajos> SQL(String sql){
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
                 t.setLotes(rs.getInt("NOLOTES"));
                 t.setQtyproduccion(rs.getInt("QTYPRODUCCION"));
                 t.setQTYPORLOTE(rs.getInt("QTYPORLOTE"));
                 t.setNOLOTE(rs.getInt("NOLOTE"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta"+e);
            return null;
        } 
        return list;
}
    

    public static ArrayList<ClassTrabajos> ListarTra(String a , String b) {
        return SQL2("select ID,PN,JOB,CLIENTE,ESTANDAR,REVISION,NOLOTES,QTYPRODUCCION,QTYPORLOTE,NOLOTE FROM TRABAJO WHERE UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') and estado = 1 and fechainicio IS null order by id" );
    }
    
    public static ArrayList<ClassTrabajos> ListarTraIniciadosTras(String a , String b) {
        return SQL2("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,trabajo.nolotes,trabajo.qtyproduccion,lotes.cantidad as QTYPORLOTE,lotes.nolote FROM TRABAJO inner join lotes on trabajo.id = lotes.id WHERE UPPER(TRABAJO.PN) LIKE UPPER('"+a+"%') and UPPER(TRABAJO.JOB) LIKE UPPER('"+b+"%') and LOTES.estado = 1 and LOTES.fechainicio IS NOT null order by TRABAJO.PN,lotes.id_lote,LOTES.NOLOTE" );
    }
    
    public static ArrayList<ClassTrabajos> ListarTraIniciadosTrasEdit(String a , String b) {
        return SQL2("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,trabajo.nolotes,trabajo.qtyproduccion,lotes.cantidad as QTYPORLOTE,lotes.nolote FROM TRABAJO inner join lotes on trabajo.id = lotes.id WHERE UPPER(TRABAJO.PN) LIKE UPPER('"+a+"%') and UPPER(TRABAJO.JOB) LIKE UPPER('"+b+"%') and LOTES.estado = 1 and LOTES.fechainicio IS null order by TRABAJO.PN,LOTES.NOLOTE" );
    }
    private static ArrayList<ClassTrabajos> SQL2(String sql){
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
                 t.setLotes(rs.getInt("NOLOTES"));
                 t.setQtyproduccion(rs.getInt("QTYPRODUCCION"));
                 t.setQTYPORLOTE(rs.getInt("QTYPORLOTE"));
                 t.setNOLOTE(rs.getInt("NOLOTE"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
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
            ps = cn.prepareStatement("select lotes.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,lotes.nolote,lotes.cantidad,decode(LOTES.PRIORIDAD,0,'',1,'URGENTE') as PRIORIDAD from trabajo inner join lotes on trabajo.id = lotes.id where lotes.ID_LOTE ="+a);
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
            }
            cn.close();
            ps.close();
            return c;
}
    
    
     public static ClassTrabajos buscarTrabajoEditar(int a) throws SQLException{
        return buscarTrabajoEditar(a ,null);
    }
    
    public static ClassTrabajos buscarTrabajoEditar(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("SELECT LOTES.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,TRABAJO.QTYCLIENTE,TRABAJO.QTYPRODUCCION,LOTES.CANTIDAD,TRABAJO.REVISION,DECODE(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,LOTES.NOLOTE,TRABAJO.FECHAIN,LOTES.PRIORIDAD,LOTES.NOTA FROM TRABAJO INNER JOIN LOTES ON TRABAJO.ID = LOTES.ID WHERE LOTES.ID_LOTE ="+a);
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
               c.setQtycliente(rs.getInt("QTYCLIENTE"));
               c.setQtyproduccion(rs.getInt("QTYPRODUCCION"));
               c.setQTYPORLOTE(rs.getInt("CANTIDAD"));
               c.setRevision(rs.getString("REVISION"));
               c.setEstandar(rs.getString("ESTANDAR"));
               c.setNOLOTE(rs.getInt("NOLOTE"));
               c.setFecha(rs.getString("FECHAIN"));
               c.setPrioridad(rs.getInt("PRIORIDAD"));
               c.setComentarios(rs.getString("NOTA"));
            }
            cn.close();
            ps.close();
            return c;
}
    
    public static void InsertarProceso(ClassTrabajos t) throws SQLException{
        Connection con = BD.getConnection();
        PreparedStatement ps = null;
        ps = con.prepareStatement("insert into procesos(id_proceso,id_lote,proceso,comentarios,fechaauto,fecha,cantidad,fechasys,depto) values(ID_PROCESO.NEXTVAL,?,?,?,?,?,?,sysdate,?)");
        //ps.setInt(1,t.getId_proceso());
        ps.setInt(1, t.getId());
        ps.setString(2, t.getProceso());
        ps.setString(3, t.getComentarios());
        ps.setString(4, t.getFecha());
        ps.setDate(5,new java.sql.Date(t.getFecha1().getTime()));
        ps.setInt(6, t.getCantidad());
        ps.setInt(7, t.getDepartamento());
        ps.executeUpdate();
        con.close();
        ps.close(); 
    }

    public static ArrayList<ClassTrabajos> ListarProceso(int a) {
                   
        return SQL1("select proceso,fechaauto,cantidad,comentarios,decode(depto,0,'INFORMATICA',1,'TRANSFORMADORES',2,'INGENIERIA',3,'STRIP Y POTTING',4,'INSPECCION',5,'TESTING',6,'CALIDAD',7,'GERENTE OPERACIONES',8,'BODEGA',9,'RELACION CON EL CLIENTE',10,'TALLER',11,'GERENCIA',12,'CHIPS') AS DEPTO from procesos where id_lote="+a+" order by id_proceso");
    }
    private static ArrayList<ClassTrabajos> SQL1(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos b;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 b = new ClassTrabajos();
                 b.setProceso(rs.getString("proceso"));
                 b.setFecha(rs.getString("fechaauto"));
                 b.setCantidad(rs.getInt("cantidad"));
                 b.setComentarios(rs.getString("comentarios"));
                 b.setDepto(rs.getString("depto"));
                 list.add(b);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } 
        return list;
}
    
    public static boolean CerrarTra(CerrararTra t) throws SQLException{
        
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE LOTES SET ESTADO = 2, FECHAFIN = ? WHERE ID_LOTE ="+t.getNo());
        ps.setDate(1,new java.sql.Date(t.getFecha().getTime()));    
        int rowsUpdated = ps.executeUpdate();
        cn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }    
        public static boolean IniciarTra(CerrararTra t) throws SQLException{
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE LOTES SET FECHAINICIO = ? WHERE ID_LOTE ="+t.getNo());
        ps.setDate(1,new java.sql.Date(t.getFecha().getTime()));    
        int rowsUpdated = ps.executeUpdate();
        cn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
        
        
        public static ArrayList<ClassTrabajos> ListarTrabajoIniciar(String a , String b) {
        return SQLListas("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,lotes.cantidad as QTYPORLOTE,lotes.nolote FROM TRABAJO inner join lotes on trabajo.id = lotes.id\n" +
"WHERE UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') and LOTES.estado = 1 and lotes.fechainicio IS null order by LOTES.id_LOTE" );
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
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
}    
        
    
    public static boolean AprobarEjemplo(CerrararTra t) throws SQLException{
        
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE EJEMPLOS_TRABAJO SET ESTADO = 2, FECHAFIN = ? WHERE ID ="+t.getNo());
        ps.setDate(1,new java.sql.Date(t.getFecha().getTime()));    
        int rowsUpdated = ps.executeUpdate();
        cn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }    
    
    
    public static ArrayList<ClassTrabajos> ListarLotesEditar(String a , String b) {
        return SQ("select ID,PN,JOB,CLIENTE,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR ,REVISION,QTYPRODUCCION FROM EJEMPLOS_TRABAJO WHERE UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') and estado = 1 order by id" );
    }
    private static ArrayList<ClassTrabajos> SQ(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setId(rs.getInt("ID"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setCliente(rs.getString("CLIENTE"));
                 t.setEstandar(rs.getString("ESTANDAR"));
                 t.setRevision(rs.getString("REVISION"));
                 t.setQtyproduccion(rs.getInt("QTYPRODUCCION"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
    
    }  
    
    public static boolean EditarNodeLote(ClassTrabajos t) throws SQLException{
        
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE LOTES SET CANTIDAD = ?,  PRIORIDAD = ?, NOTA = ? WHERE ID_LOTE ="+t.getId());
        //ps.setDate(1,new java.sql.Date(t.getFecha().getTime())); 
        ps.setInt(1, t.getQTYPORLOTE());
        ps.setInt(2, t.getPrioridad());
        ps.setString(3, t.getComentarios());
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
