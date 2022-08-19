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
    
    public static ArrayList<ClassTrabajos> ListarTrabajosSolicitudesMat(String a) {
        return SQL2("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,trabajo.nolotes,trabajo.qtyproduccion,lotes.cantidad as QTYPORLOTE,lotes.nolote FROM TRABAJO inner join lotes on trabajo.id = lotes.id WHERE UPPER(TRABAJO.PN) LIKE UPPER('"+a+"%') and LOTES.estado = 1 order by TRABAJO.PN,lotes.id_lote,LOTES.NOLOTE" );
    }
    
    public static ArrayList<ClassTrabajos> ListarTraIniciadosTrasEdit(String a , String b) {
        return SQL2("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,trabajo.nolotes,trabajo.qtyproduccion,lotes.cantidad as QTYPORLOTE,lotes.nolote FROM TRABAJO inner join lotes on trabajo.id = lotes.id WHERE UPPER(TRABAJO.PN) LIKE UPPER('"+a+"%') and UPPER(TRABAJO.JOB) LIKE UPPER('"+b+"%') and LOTES.estado = 1 and LOTES.fechainicio IS null order by TRABAJO.PN,LOTES.NOLOTE" );
    }
    public static ArrayList<ClassTrabajos> ListarTrabajosPrioridad(String a , String b) {
        return SQL2("select loteS.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,trabajo.nolotes,trabajo.qtyproduccion,lotes.cantidad as QTYPORLOTE,lotes.nolote FROM TRABAJO inner join lotes on trabajo.id = lotes.id WHERE UPPER(TRABAJO.PN) LIKE UPPER('"+a+"%') and UPPER(TRABAJO.JOB) LIKE UPPER('"+b+"%') and LOTES.estado = 1 and LOTES.fechainicio IS not null order by TRABAJO.PN,LOTES.NOLOTE" );
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
            ps = cn.prepareStatement("select trabajo.ID,lotes.ID_LOTE,TRABAJO.PN,TRABAJO.JOB,TRABAJO.CLIENTE,decode(TRABAJO.ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,TRABAJO.REVISION,lotes.nolote,lotes.cantidad,decode(LOTES.PRIORIDAD,0,'',1,'URGENTE') as PRIORIDAD, trabajo.nolotes from trabajo inner join lotes on trabajo.id = lotes.id where lotes.ID_LOTE ="+a);
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
               c.setId_trabajo(rs.getInt("ID"));
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
                   
        return SQL1("select proceso,fechaauto,cantidad,comentarios,decode(depto,0,'INFORMATICA',1,'TRANSFORMADORES',2,'INGENIERIA',3,'STRIP Y POTTING',4,'INSPECCION',5,'TESTING',6,'CALIDAD',7,'GERENTE OPERACIONES',8,'BODEGA',9,'RELACION CON EL CLIENTE',10,'TALLER',11,'GERENCIA',12,'CHIPS',13,'MOLDING') AS DEPTO from procesos where id_lote="+a+" order by id_proceso");
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
        ps = cn.prepareStatement("UPDATE EJEMPLOS_TRABAJO SET ESTADO = ?, FECHAFIN = ?, COMENTARIOS = ? WHERE ID ="+t.getNo());
        ps.setInt(1, t.getEstado());
        ps.setDate(2,new java.sql.Date(t.getFecha().getTime())); 
        ps.setString(3, t.getNota());
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
        ps = cn.prepareStatement("UPDATE LOTES SET PRIORIDAD = ?, NOTA = ? WHERE ID_LOTE ="+t.getId());
        //ps.setDate(1,new java.sql.Date(t.getFecha().getTime())); 
        ps.setInt(1, t.getPrioridad());
        ps.setString(2, t.getComentarios());
        int rowsUpdated = ps.executeUpdate();
        cn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }  
    
    
    public static boolean EditarNodeLoteO(ClassTrabajos t) throws SQLException{
        
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE LOTES SET PRIORIDAD = ?,CANTIDAD = ? , NOTA = ? WHERE ID_LOTE ="+t.getId());
        //ps.setDate(1,new java.sql.Date(t.getFecha().getTime())); 
        ps.setInt(1, t.getPrioridad());
        ps.setInt(2, t.getQTYPORLOTE());
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
    
    
    
    
     public static ArrayList<ClassTrabajos> ListarProductos(int a) {
        return SQpro("select ID_PRODUCTO,DESCRIPCION from productos_taller where depto ="+a );
    }
    private static ArrayList<ClassTrabajos> SQpro(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setId(rs.getInt("ID_PRODUCTO"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
    }  
    public static ArrayList<ClassTrabajos> ListarProductosYaSolicitados(int a,int b) {
        return SQpedidos("SELECT p.id_producto as ID_PRODUCTO ,t.descripcion as DESCRIPCION,to_char(p.fecha,'dd/mm/yyyy hh:mi:ss') as fecha, p.cantidad FROM PEDIDOS_TRABAJOS P INNER JOIN productos_taller T ON p.id_producto = t.id_producto WHERE P.ID_LOTE ="+a+" and estado in(0,1)  and p.depto ="+b); 
    }
    private static ArrayList<ClassTrabajos> SQpedidos(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setId(rs.getInt("ID_PRODUCTO"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setFecha(rs.getString("fecha"));
                 t.setCantidad(rs.getInt("cantidad"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
    }  
    
public static ArrayList<ClassTrabajos> ListarProductosYaSolicitadosEjemplo(int a,int b) {
        return SQpedidosE("SELECT p.id_producto as ID_PRODUCTO ,t.descripcion as DESCRIPCION,to_char(p.fecha,'dd/mm/yyyy hh:mi:ss') as fecha,p.cantidad FROM PEDIDOS_TRABAJOS P INNER JOIN productos_taller T ON p.id_producto = t.id_producto WHERE  p.id ="+a+"  and p.depto ="+b); 
    }
    private static ArrayList<ClassTrabajos> SQpedidosE(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setId(rs.getInt("ID_PRODUCTO"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setFecha(rs.getString("fecha"));
                 t.setCantidad(rs.getInt("cantidad"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
    } 
    
    
    
    
    public static ArrayList<ClassTrabajos> ListarTrabajosporDepto(String a) {
        return SQLDepto("select p.id_lote,upper(p.proceso)as proceso,p.fechaauto,l.nolote,l.cantidad,t.pn,t.job,decode(l.prioridad,1,'URGENTE') as prioridad from procesos p inner join lotes l on p.id_lote = l.id_lote join trabajo t on l.id = t.id\n" +
"where p.id_proceso in(select max(p.id_proceso) as id_proceso from lotes l INNER join procesos p on l.id_lote = p.id_lote join trabajo t on l.id = t.id where l.estado = 1  GROUP BY (l.id_lote)) and\n" +
"upper (p.proceso) like upper('"+a+"%') order by t.pn,l.nolote");
    }
    
    private static ArrayList<ClassTrabajos> SQLDepto(String sql){
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
                 t.setProceso(rs.getString("PROCESO"));
                 t.setNOLOTE(rs.getInt("NOLOTE"));
                 t.setFecha(rs.getString("FECHAAUTO"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setPrioridadStrin(rs.getString("PRIORIDAD"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta TEAS "+e);
            return null;
        } 
        return list;
}   
    
    
    


public static ClassTrabajos buscarTrabajo(int a) throws SQLException{
        return buscarTrabajoCambios(a ,null);
    }
    
    public static ClassTrabajos buscarTrabajoCambios(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select ID,PN,JOB,CLIENTE,REVISION from trabajo where ID ="+a);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new ClassTrabajos(){
               };
               }
               c.setId(rs.getInt("ID"));
               c.setPN(rs.getString("PN"));
               c.setJob(rs.getString("JOB"));
               c.setCliente(rs.getString("CLIENTE"));
               c.setRevision(rs.getString("REVISION"));
            }
            cn.close();
            ps.close();
            return c;
}
    
public static ClassTrabajos buscarTrabajoHistorial(int a) throws SQLException{
        return buscarTrabajoCambiosHistorial(a ,null);
    }
    
    public static ClassTrabajos buscarTrabajoCambiosHistorial(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select ID,PN,JOB,CLIENTE,REVISION,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR, fechain as FECHA  from trabajo where ID ="+a);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new ClassTrabajos(){
               };
               }
               c.setId(rs.getInt("ID"));
               c.setPN(rs.getString("PN"));
               c.setJob(rs.getString("JOB"));
               c.setCliente(rs.getString("CLIENTE"));
               c.setRevision(rs.getString("REVISION"));
               c.setEstandar(rs.getString("ESTANDAR"));
               c.setFecha(rs.getString("FECHA"));
            }
            cn.close();
            ps.close();
            return c;
}    
    

public static ClassTrabajos buscarTrabajoControlHistorial(int a) throws SQLException{
        return buscarTrabajoControlCambiosHistorial(a ,null);
    }
    
    public static ClassTrabajos buscarTrabajoControlCambiosHistorial(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select ID,PN,JOB,CLIENTE,REVISION,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR, fechain as FECHA  from trabajo where ID ="+a);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new ClassTrabajos(){
               };
               }
               c.setId(rs.getInt("ID"));
               c.setPN(rs.getString("PN"));
               c.setJob(rs.getString("JOB"));
               c.setCliente(rs.getString("CLIENTE"));
               c.setRevision(rs.getString("REVISION"));
               c.setEstandar(rs.getString("ESTANDAR"));
               c.setFecha(rs.getString("FECHA"));
            }
            cn.close();
            ps.close();
            return c;
}



        public static void InsertarCambio(ClassTrabajos t) throws SQLException{
        Connection con = BD.getConnection();
        PreparedStatement ps = null;
        ps = con.prepareStatement("insert into controlcambios(id,proposito,consecuencia,integridad,r_foto,r_materia,r_impresion,solicita,cambio,aprueba,fecha,observaciones,id_control) values(?,?,?,?,?,?,?,?,?,?,?,?,controlcamb.NEXTVAL)");
        //ps.setInt(1,t.getId_proceso());
        ps.setInt(1, t.getId());
        ps.setInt(2, t.getProposito());
        ps.setInt(3, t.getConsecuencia());
        ps.setString(4, t.getIntegridad());
        ps.setInt(5, t.getR_foto());
        ps.setInt(6, t.getR_materia());
        ps.setInt(7, t.getR_imprimir());
        ps.setInt(8, t.getSolicita());
        ps.setString(9, t.getCambio());
        ps.setInt(10, t.getAprueba());
        ps.setDate(11,new java.sql.Date(t.getFecha1().getTime()));
        ps.setString(12, t.getObservaciones());
        ps.executeUpdate();
        con.close();
        ps.close(); 
    }
    
    

 public static ArrayList<ClassTrabajos> ListarCambiosRealizados(int a) {
        return P("SELECT INTEGRIDAD,CAMBIO,APRUEBA,TO_CHAR(FECHA,'DD/MM/YY') AS FECHA,OBSERVACIONES FROM CONTROLCAMBIOS where id = "+a);
    }
     
    private static ArrayList<ClassTrabajos> P(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setIntegridad(rs.getString("INTEGRIDAD"));
                 t.setCambio(rs.getString("CAMBIO"));
                 t.setAprueba(rs.getInt("APRUEBA"));
                 t.setFecha(rs.getString("FECHA"));
                 t.setObservaciones(rs.getString("OBSERVACIONES"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
} 
       //test
 /*public static ArrayList<ClassTrabajos> ListarCambiosRealizadosHistorial(int a) throws SQLException{
        return PH("SELECT proposito,consecuencia,integridad,solicita,cambio,aprueba,to_char(fecha,'DD/MM/YY') AS fecha,observaciones FROM CONTROLCAMBIOS where id_control = "+a);
    }
     
    private static ArrayList<ClassTrabajos> PH(String sql){
    ArrayList<ClassTrabajos> list1 = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setPropositoS(rs.getString("proposito"));
                 t.setConsecuenciaS(rs.getString("consecuencias"));
                 t.setIntegridadS(rs.getString("integridad"));
                 t.setSolicitaS(rs.getString("solicita"));
                 t.setAprueba(rs.getInt("aprueba"));
                 t.setCambio(rs.getString("cambio"));
                 t.setFecha(rs.getString("fecha"));
                 t.setObservaciones(rs.getString("observaciones"));
                 list1.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list1;
}*/
    
    
    
public static ArrayList<ClassTrabajos> ListarCambiosRealizadosHistorial2(int a) {
        return PH2("SELECT id_control,integridad,cambio,aprueba,to_char(fecha,'DD/MM/YY') AS FECHA,OBSERVACIONES FROM CONTROLCAMBIOS where id = "+a);
    }
     
    private static ArrayList<ClassTrabajos> PH2(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setId(rs.getInt("id_control"));
                 t.setIntegridadS(rs.getString("integridad"));
                 t.setAprueba(rs.getInt("aprueba"));
                 t.setCambio(rs.getString("cambio"));
                 t.setFecha(rs.getString("fecha"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
}       
    
   
    
public static ClassTrabajos buscarTrabajoHistorialFinal(int a) throws SQLException{
        return buscarTrabajoCambiosHistorialF(a ,null);
    }
    
    public static ClassTrabajos buscarTrabajoCambiosHistorialF(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            Connection cn2 = BD_RECURSOS.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("SELECT decode(proposito,1,'MODIFICAR',2,'ELIMINAR') as proposito,decode(consecuencia,1,'ACTUALIZACION DE LA INFORMACION',2,'DEJAR SOLO INFORMACION VIGENTE') as consecuencia,integridad,"
                    + "decode(solicita,1,'INSPECCION',2,'TESTING',3,'CHIPS',4,'STRIP Y POTTING',5,'TRANSFORMADORES',6,'TALLER',7,'BODEGA',8,'INGENIERIA',9,'GERENCIA') as solicita,cambio,"
                    + "aprueba,to_char(fecha,'DD/MM/YY') AS fecha,observaciones, "
                    + "nvl(decode(r_foto,1,'FOTOGRAFIAS'),' ') AS FOTO,nvl(decode(r_materia,1,'MATERIA PRIMA'),' ') AS MATERIA,nvl(decode(r_impresion,1,'PAPEL PARA IMPRESION'),' ') AS impresion FROM CONTROLCAMBIOS  where id_control ="+a);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new ClassTrabajos(){
               };
               }
                 c.setPropositoS(rs.getString("proposito"));
                 c.setConsecuenciaS(rs.getString("consecuencia"));
                 c.setIntegridadS(rs.getString("integridad"));
                 c.setSolicitaS(rs.getString("solicita"));
                 c.setAprueba(rs.getInt("aprueba"));
                 c.setCambio(rs.getString("cambio"));
                 c.setFecha(rs.getString("fecha"));
                 c.setObservaciones(rs.getString("observaciones"));
                 c.setR_fotoS(rs.getString("foto"));
                 c.setR_materiaS(rs.getString("materia"));
                 c.setR_imprimirS(rs.getString("impresion"));
            }
            cn.close();
            ps.close();
            return c;
}        
    

public static ArrayList<ClassTrabajos> ListarTrabajosVencidos(String a , String b) {
        return Vencido("select l.id,t.pn,t.job,t.cliente,l.fechainicio,t.fechain,(SELECT COUNT(*) FROM lotes WHERE ID IN(l.id) AND ESTADO = 1 GROUP BY id)as LOTES,\n" +
"round(MONTHS_BETWEEN(TO_DATE(SYSDATE,'DD/MM/YYYY'),TO_DATE(l.fechainicio,'DD/MM/YYYY')),0) as meses,\n" +
"case WHEN round(MONTHS_BETWEEN(TO_DATE(SYSDATE,'DD/MM/YYYY'),TO_DATE(l.fechainicio,'DD/MM/YYYY')),0) > 3.9  AND  round(MONTHS_BETWEEN(TO_DATE(SYSDATE,'DD/MM/YYYY'),TO_DATE(l.fechainicio,'DD/MM/YYYY')),1) <= 6 THEN 'TRABAJO PROXIMO A VENCER'\n" +
"WHEN\n" +
"round(MONTHS_BETWEEN(TO_DATE(SYSDATE,'DD/MM/YYYY'),TO_DATE(l.fechainicio,'DD/MM/YYYY')),0) >= 5 THEN 'TRABAJO VENCIDO' \n" +
"WHEN\n" +
"round(MONTHS_BETWEEN(TO_DATE(SYSDATE,'DD/MM/YYYY'),TO_DATE(l.fechainicio,'DD/MM/YYYY')),0) <= 3 THEN 'TRABAJO A TIEMPO' \n" +
"END as TIPO\n" +
"from lotes l inner join trabajo t on l.id = t.id where l.id_lote in(\n" +
"select min(l.id_lote) as ID_LOTE from lotes l inner join trabajo t on l.id = t.id  where t.id in(select id from trabajo where estado = 1) and l.fechainicio is not null group by  t.id,t.pn,t.job,t.cliente\n" +
") and UPPER(t.PN) LIKE UPPER('"+a+"%') and UPPER(t.JOB) LIKE UPPER('"+b+"%') order by  to_date(l.fechainicio,'dd/mm/yy')" );
    }
    private static ArrayList<ClassTrabajos> Vencido(String sql){
    ArrayList<ClassTrabajos> list = new ArrayList<ClassTrabajos>();
    Connection cn = BD.getConnection();
        try {
            ClassTrabajos t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ClassTrabajos();
                 t.setId(rs.getInt("id"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setCliente(rs.getString("CLIENTE"));
                 t.setFecha(rs.getString("fechain"));
                 t.setFecha2(rs.getString("fechainicio"));
                 t.setLotes(rs.getInt("LOTES"));
                 t.setMeses(rs.getInt("meses"));
                 t.setEstado(rs.getString("TIPO"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
}    
    
    
    
    
    
    
    
   
}
