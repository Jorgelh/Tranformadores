/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

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
public class InsertarEjemplos {
    
    public static void InsertarEjemplos(ClassTrabajos t) throws SQLException{
        Connection con = BD.getConnection();
        PreparedStatement ps = null;
        {     
        ps = con.prepareStatement("insert into EJEMPLOS_TRABAJO values(idejemplo.nextval,?,?,?,?,?,?,?,?,null,1,sysdate,null,?)");
        //ps.setInt(1,t.getId());
        ps.setString(1, t.getPN());
        ps.setString(2, t.getJob());
        ps.setString(3, t.getCliente());
        ps.setInt(4, t.getQtyproduccion());
        ps.setInt(5, t.getQtycliente());
        ps.setInt(6, t.getEstandarint());
        ps.setString(7, t.getRevision());
        ps.setDate(8, new java.sql.Date(t.getFechain().getTime()));
        ps.setInt(9, t.getPrioridad());
        //ps.setString(9, t.getComentarios());
        ps.executeUpdate();
        }
        con.close();
        ps.close(); 
    }
    
    
    public static void InsertarProcesoEjemplo(ClassTrabajos t) throws SQLException{
        Connection con = BD.getConnection();
        PreparedStatement ps = null;
        ps = con.prepareStatement("insert into ejemplos_procesos(id_proceso,id,proceso,comentarios,fechaauto,fecha,cantidad,fechasys,depto,nota,realizadopor) values(ID_PROCESO_EJEMPLO.nextval,?,?,?,?,?,?,sysdate,?,?,?)");
        //ps.setInt(1,t.getId_proceso());
        ps.setInt(1, t.getId());
        ps.setString(2, t.getProceso());
        ps.setString(3, t.getComentarios());
        ps.setString(4, t.getFecha());
        ps.setDate(5,new java.sql.Date(t.getFecha1().getTime()));
        ps.setInt(6, t.getCantidad());
        ps.setInt(7, t.getDepartamento());
        ps.setString(8, t.getNota());
        ps.setInt(9, t.getTrabajadopor());
        ps.executeUpdate();
        con.close();
        ps.close(); 
    }

    
    
    public static ArrayList<ClassTrabajos> ListarEjemplos(String a , String b) {
        return SQL2("select ID,PN,JOB,CLIENTE,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR ,REVISION,QTYPRODUCCION FROM EJEMPLOS_TRABAJO WHERE UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') and estado in (1,3,4)  order by id" );
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
    
    
    
public static ArrayList<ClassTrabajos> ListarProcesoEjemplos(int a) {
                   
        return SQL1("select proceso,fechaauto,cantidad,comentarios,decode(depto,0,'INFORMATICA',1,'TRANSFORMADORES',2,'INGENIERIA',3,'STRIP Y POTTING',4,'INSPECCION',5,'TESTING',6,'CALIDAD',7,'GERENTE OPERACIONES',8,'BODEGA',9,'RELACION CON EL CLIENTE',10,'TALLER',11,'GERENCIA',12,'CHIPS',13,'MOLDING') AS DEPTO from EJEMPLOS_PROCESOS where id="+a+" order by id_proceso");
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
    
    public static ClassTrabajos buscarEjemplo(int a) throws SQLException{
        return buscarEjemplo(a ,null);
    }
    
    public static ClassTrabajos buscarEjemplo(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select id,pn,job,cliente,decode(estandar,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as estandar,qtyproduccion,revision,decode(PRIORIDAD,0,' ',1,'URGENTE') AS PRIORIDAD,estado,QTYCLIENTE from EJEMPLOS_TRABAJO where id ="+a);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new ClassTrabajos(){
               };
            }
               c.setId(rs.getInt("id"));
               c.setPN(rs.getString("pn"));
               c.setJob(rs.getString("job"));
               c.setCliente(rs.getString("cliente"));
               c.setEstandar(rs.getString("estandar"));
               c.setQtyproduccion(rs.getInt("qtyproduccion"));
               c.setRevision(rs.getString("revision"));
               c.setPrioridadStrin(rs.getString("PRIORIDAD"));
               c.setEstadoeje(rs.getInt("estado"));
               c.setQtycliente(rs.getInt("QTYCLIENTE"));
            }
            cn.close();
            ps.close();
            return c;
}
    
public static ClassTrabajos buscarEjemploTrabajo(int a) throws SQLException{
        return buscarEjemploTrabajo(a ,null);
    }
    
    public static ClassTrabajos buscarEjemploTrabajo(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select id,pn,job,cliente,decode(estandar,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as estandar,qtycliente,revision,fechain from EJEMPLOS_TRABAJO where id ="+a);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new ClassTrabajos(){
               };
            }
               c.setId(rs.getInt("id"));
               c.setPN(rs.getString("pn"));
               c.setJob(rs.getString("job"));
               c.setCliente(rs.getString("cliente"));
               c.setEstandar(rs.getString("estandar"));
               c.setQtyproduccion(rs.getInt("qtycliente"));
               c.setRevision(rs.getString("revision"));
               c.setFecha(rs.getString("fechain"));
            }
            cn.close();
            ps.close();
            return c;
}    
    
    
    
    public static ArrayList<ClassTrabajos> ListarProcesoEjemplo(int a) {
                   
        return Ejemplo("select proceso,fechaauto,cantidad,comentarios,decode(depto,0,'INFORMATICA',1,'TRANSFORMADORES',2,'INGENIERIA',3,'STRIP Y POTTING',4,'INSPECCION',5,'TESTING',6,'CALIDAD',7,'GERENTE OPERACIONES',8,'BODEGA',9,'RELACION CON EL CLIENTE',10,'TALLER',11,'GERENCIA',12,'CHIPS',13,'MOLDING') AS DEPTO,nota,realizadopor,co.nombres as realizado from EJEMPLOS_PROCESOS ep left join RRHH.alistaempleados co on ep.realizadopor = co.codigo where id="+a+" order by id_proceso");
    }
    private static ArrayList<ClassTrabajos> Ejemplo(String sql){
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
                 b.setNota(rs.getString("nota"));
                 b.setTrabajadopor(rs.getInt("realizadopor"));
                 b.setRealizadopor(rs.getString("realizado"));
                 list.add(b);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } 
        return list;
        }

        

public static ArrayList<ClassTrabajos> ListarHistorialEjemplos(String a) {
        return SQL("select ID,PN,JOB,CLIENTE,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR ,"
                + "REVISION,QTYPRODUCCION,COMENTARIOS,FECHAFIN,decode(ESTADO,2,'',4,'TRABAJO CREADO') as ESTADO FROM EJEMPLOS_TRABAJO WHERE UPPER(PN) LIKE UPPER('"+a+"%') and estado in (4,2) order by id" );
    }

public static ArrayList<ClassTrabajos> ListarHistorialEjemplosCerrados(String a) {
        return SQL("select ID,PN,JOB,CLIENTE,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR ,"
                + "REVISION,QTYPRODUCCION,COMENTARIOS,FECHAFIN,decode(ESTADO,2,'',4,'TRABAJO CREADO') as ESTADO FROM EJEMPLOS_TRABAJO WHERE UPPER(PN) LIKE UPPER('"+a+"%') and estado in (5) order by id" );
    }
    
public static ArrayList<ClassTrabajos> ListarHistorialEjemplos2(String a) {
        return SQL("select ID,PN,JOB,CLIENTE,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR ,"
                + "REVISION,QTYPRODUCCION,COMENTARIOS,FECHAFIN,decode(ESTADO,2,'EJEMPLO TERMINADO',3,'EJEMPLO INCOMPLETO') as ESTADO FROM EJEMPLOS_TRABAJO WHERE UPPER(PN) LIKE UPPER('"+a+"%') and estado in (2,3) order by id" );
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
                 t.setId(rs.getInt("ID"));
                 t.setPN(rs.getString("PN"));
                 t.setJob(rs.getString("JOB"));
                 t.setCliente(rs.getString("CLIENTE"));
                 t.setEstandar(rs.getString("ESTANDAR"));
                 t.setRevision(rs.getString("REVISION"));
                 t.setQtyproduccion(rs.getInt("QTYPRODUCCION"));
                 t.setComentarios(rs.getString("COMENTARIOS"));
                 t.setFecha(rs.getString("FECHAFIN"));
                 t.setEstado(rs.getString("ESTADO"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta"+e);
            return null;
        } 
        return list;
}
    

     public static ArrayList<ClassTrabajos> ListarTrabajosPrioridadEjemplo(String a , String b) {
        return SQL2E("select ID,'' as NOLOTES,PN,JOB,CLIENTE,decode(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,REVISION,qtyproduccion,qtyproduccion as QTYPORLOTE,'1' as NOLOTE FROM EJEMPLOS_TRABAJO WHERE  estado = 1 and UPPER(PN) LIKE UPPER('"+a+"%') and UPPER(JOB) LIKE UPPER('"+b+"%') order by id");
    }
    private static ArrayList<ClassTrabajos> SQL2E(String sql){
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
    

public static ClassTrabajos buscarTrabajoEditarEjemplo(int a) throws SQLException{
        return buscarTrabajoEditarEJE(a ,null);
    }
    
    public static ClassTrabajos buscarTrabajoEditarEJE(int a, ClassTrabajos c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("SELECT ID,PN,JOB,CLIENTE,QTYCLIENTE,QTYPRODUCCION,REVISION,DECODE(ESTANDAR,1,'FUJI',2,'INGENIERIA',3,'MIL-PRF-27',4,'MIL-STD-981',5,'MIL-STD-981 PRE-CAP',6,'MIL-STD-981 URGENTE',7,'MIL-STD-981 X-RAY') as ESTANDAR,'' as NOLOTE,FECHAIN,PRIORIDAD,comentarios as nota FROM ejemplos_trabajo WHERE ID ="+a);
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
               c.setQtycliente(rs.getInt("QTYCLIENTE"));
               c.setQtyproduccion(rs.getInt("QTYPRODUCCION"));
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
    
        public static boolean EditarNodeEjemplo(ClassTrabajos t) throws SQLException{
        
        Connection cn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cn.prepareStatement("UPDATE EJEMPLOS_TRABAJO SET PRIORIDAD = ? WHERE ID ="+t.getId());
        //ps.setDate(1,new java.sql.Date(t.getFecha().getTime())); 
        ps.setInt(1, t.getPrioridad());
        int rowsUpdated = ps.executeUpdate();
        cn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }  
 
        
        
        
 public static ClassTrabajos  buscarEmpleado(int id) throws SQLException {
        return buscarEmple(id, null);
    }    
 public static ClassTrabajos buscarEmple(int id,ClassTrabajos p) throws SQLException {
        Connection cnn = BD_RECURSOS.getConnection();
        
        try {
            PreparedStatement ps = null;
            ps = cnn.prepareStatement("select ID_LISTAEMPLEADOS,nombres from alistaempleados where codigo = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (p == null) {
                    p = new ClassTrabajos(){
                    };
                }
                p.setId_listaEmpleados(rs.getInt("ID_LISTAEMPLEADOS"));
                p.setNombres(rs.getString("NOMBRES"));
                cnn.close();
                ps.close();
                return p;
            }

        } catch (Exception e) {
               System.err.println("errroooooorrr"+e);    
        }
        
        return null;

    }          
        
        
        
        
        
        
        
}