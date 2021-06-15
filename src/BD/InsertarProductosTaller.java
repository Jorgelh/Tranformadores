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
public class InsertarProductosTaller {
    
     public static void insertarNuevoProducto(ProductosTaller d) throws SQLException{
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("insert into PEDIDOS_TRABAJOS(id_pedido,id_producto,id_lote,fecha,cantidad,status,estado,depto,tipo) Values(ID_PEDIDO.nextval,?,?,sysdate,?,1,0,?,?)");
    ps.setInt(1, d.getIdproducto());
    ps.setInt(2, d.getIdlote());
   /* DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm:ss");
    DateFormat e = dateFormat.format(d.getFecha());
    ps.setDate(3, d.getFecha());*/
    //ps.setDate(3,new java.sql.Date(d.getFecha().getTime()));
    ps.setInt(3, d.getCantidad());
    ps.setInt(4, d.getDepto());
    ps.setInt(5, d.getTipo());
    ps.executeUpdate();
    cnn.close();
    ps.close();   
    } 
    public static void insertarNuevoProductoEjemplo(ProductosTaller d) throws SQLException{
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("insert into PEDIDOS_TRABAJOS(id_pedido,id_producto,id,fecha,cantidad,status,estado,depto,tipo) Values(ID_PEDIDO.nextval,?,?,sysdate,?,1,0,?,?)");
    ps.setInt(1, d.getIdproducto());
    ps.setInt(2, d.getIdlote());
    //ps.setDate(3, (Date) d.getFecha());
    //ps.setDate(3,new java.sql.Date(d.getFecha().getTime()));
    ps.setInt(3, d.getCantidad());
    ps.setInt(4, d.getDepto());
    ps.setInt(5, d.getTipo());
    ps.executeUpdate();
    cnn.close();
    ps.close();   
    }  
     
    public static void deleteProducto(ProductosTaller d) throws SQLException{
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("delete from pedidos_trabajos where id_lote = ? and depto = ? and id_producto = ? and estado = 0");
    ps.setInt(1, d.getIdlote());
    ps.setInt(2, d.getDepto());
    ps.setInt(3, d.getIdproducto());
    ps.executeUpdate();
    cnn.close();
    ps.close();
    } 
 
    public static void deleteProductoEjemplo(ProductosTaller d) throws SQLException{
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("delete from pedidos_trabajos where id = ? and depto = ? and id_producto = ? and estado = 0");
    ps.setInt(1, d.getIdlote());
    ps.setInt(2, d.getDepto());
    ps.setInt(3, d.getIdproducto());
    ps.executeUpdate();
    cnn.close();
    ps.close();
    }      
    
    
    
    public static void deletePorCancelacion(ProductosTaller d) throws SQLException{
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("delete from pedidos_trabajos where id_lote = ? and depto = ? and estado = 0");
    ps.setInt(1, d.getIdlote());
    ps.setInt(2, d.getDepto());
    ps.executeUpdate();
    cnn.close();
    ps.close();
    
    }

     public static void deletePorCancelacionEjemplo(ProductosTaller d) throws SQLException{
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("delete from pedidos_trabajos where  id = ? and depto = ? and estado = 0");
    ps.setInt(1, d.getIdlote());
    ps.setInt(2, d.getDepto());
    ps.executeUpdate();
    cnn.close();
    ps.close();
    
    }       

    public static ArrayList<ProductosTaller> ListarProductosPendientes(String a, int b) {
        return SQLProd("select l.id_lote,t.pn,t.job,l.NOLOTE,'EJEMPLO' as NOLOTE1,l.cantidad,decode(l.prioridad,1,'URGENTE',2,'') as prioridad from trabajo t inner join lotes l on t.id = l.id where id_lote in(select id_lote from pedidos_trabajos where estado = 1 and depto ="+b+") and UPPER(t.PN) LIKE UPPER('"+a+"%')");
    }
    /* public static ArrayList<ProductosTaller> ListarProductosPendientesMasterActualizar(String a) {
        return SQLProd("select l.id_lote,t.pn,t.job,l.NOLOTE,'EJEMPLO' as NOLOTE1,l.cantidad from trabajo t inner join lotes l on t.id = l.id where id_lote in(select id_lote from pedidos_trabajos where estado = 1 ) and UPPER(t.PN) LIKE UPPER('"+a+"%')");
    }*/
    
    public static ArrayList<ProductosTaller> ListarProductosPendientesConsultaMaster(String a) {
        return SQLProd("select l.id_lote,t.pn,t.job,l.NOLOTE,'EJEMPLO' as NOLOTE1,l.cantidad,decode(l.prioridad,1,'URGENTE',2,'') as prioridad from trabajo t inner join lotes l on t.id = l.id where id_lote in(select id_lote from pedidos_trabajos where estado = 1) and UPPER(t.PN) LIKE UPPER('"+a+"%')");
    }
    
    
    
    public static ArrayList<ProductosTaller> ListarProductosTerminados(String a) {
        return SQLProd("select l.id_lote,t.pn,t.job,l.NOLOTE,'EJEMPLO' as NOLOTE1,l.cantidad,decode(l.prioridad,1,'URGENTE',2,'') as prioridad from trabajo t inner join lotes l on t.id = l.id where id_lote in(select id_lote from pedidos_trabajos where estado = 2 ) and UPPER(t.PN) LIKE UPPER('"+a+"%')");
    }
    
    public static ArrayList<ProductosTaller> ListarProductosPendientesEjemplo(String a,int b) {
        return SQLProd("select t.id as ID_LOTE,e.pn,e.job,'EJEMPLO' as NOLOTE1,'1' as NOLOTE,t.cantidad,decode(e.prioridad,1,'URGENTE',2,'') as prioridad from pedidos_trabajos t inner join ejemplos_trabajo e on t.id = e.id where t.id in(select id from pedidos_trabajos where estado = 1 and depto = "+b+" GROUP BY id) and UPPER(e.PN) LIKE UPPER('"+a+"%') GROUP BY t.id,e.pn,e.job,t.cantidad,e.prioridad");
    }
    
    /*public static ArrayList<ProductosTaller> ListarTrabajosEjemploMaster(String a) {
        return SQLProd("select t.id as ID_LOTE,e.pn,e.job,'EJEMPLO' as NOLOTE1,'1' as NOLOTE,t.cantidad from pedidos_trabajos t inner join ejemplos_trabajo e on t.id = e.id where t.id in(select id from pedidos_trabajos where estado = 1  GROUP BY id) and UPPER(e.PN) LIKE UPPER('"+a+"%') GROUP BY t.id,e.pn,e.job,t.cantidad");
    }*/
    
    public static ArrayList<ProductosTaller> ListarProductosPendientesEjemploMaster(String a) {
        return SQLProd("select t.id as ID_LOTE,e.pn,e.job,'EJEMPLO' as NOLOTE1,'1' as NOLOTE,t.cantidad,decode(e.prioridad,1,'URGENTE',2,'') as prioridad from pedidos_trabajos t inner join ejemplos_trabajo e on t.id = e.id where t.id in(select id from pedidos_trabajos where estado = 1 GROUP BY id) and UPPER(e.PN) LIKE UPPER('"+a+"%') GROUP BY t.id,e.pn,e.job,t.cantidad,e.prioridad");
    }
    
    public static ArrayList<ProductosTaller> ListarProductosTerminadoEjemplo(String a) {
        return SQLProd("select t.id as ID_LOTE,e.pn,e.job,'EJEMPLO' as NOLOTE1,'1' as NOLOTE,t.cantidad,decode(e.prioridad,1,'URGENTE',2,'') AS prioridad  from pedidos_trabajos t inner join ejemplos_trabajo e on t.id = e.id where t.id in(select id from pedidos_trabajos where estado = 2 GROUP BY id) and UPPER(e.PN) LIKE UPPER('"+a+"%') GROUP BY t.id,e.pn,e.job,t.cantidad,e.prioridad");
    }
    
    private static ArrayList<ProductosTaller> SQLProd(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setIdlote(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJOB(rs.getString("JOB"));
                 t.setEstatus(rs.getString("NOLOTE1"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setNolote(rs.getInt("NOLOTE"));
                 t.setPrioridad(rs.getString("prioridad"));
                 list.add(t);
            }
            cn.close();
        }catch (Exception e) {
            System.out.println("error consulta shit"+e);
            return null;
        } 
         return list;
    }   
  
    
    
    public static ArrayList<ProductosTaller> ListarProductosPendientesMasterActualizar(String a) {
        return SQLProdMaster("select l.id_lote,t.pn,t.job,l.NOLOTE,'EJEMPLO' as NOLOTE1,l.cantidad,decode(l.prioridad,1,'URGENTE',2,'') AS PRIORIDAD from trabajo t inner join lotes l on t.id = l.id where id_lote in(select id_lote from pedidos_trabajos where estado = 1 ) and UPPER(t.PN) LIKE UPPER('"+a+"%')");
    }
    private static ArrayList<ProductosTaller> SQLProdMaster(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setIdlote(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJOB(rs.getString("JOB"));
                 t.setEstatus(rs.getString("NOLOTE1"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setNolote(rs.getInt("NOLOTE"));
                 t.setPrioridad(rs.getString("PRIORIDAD"));
                 list.add(t);
            }
            cn.close();
        }catch (Exception e) {
            System.out.println("error consulta shit"+e);
            return null;
        } 
        return list;
    }
    
    
    
    
    
    public static ArrayList<ProductosTaller> ListarTrabajosEjemploMaster(String a) {
        return SQLProdMaster2("select t.id as ID_LOTE,e.pn,e.job,'EJEMPLO' as NOLOTE1,'1' as NOLOTE,t.cantidad,decode(e.prioridad,1,'URGENTE',2,'') as prioridad from pedidos_trabajos t inner join ejemplos_trabajo e on t.id = e.id where t.id in(select id from pedidos_trabajos where estado = 1  GROUP BY id) and UPPER(e.PN) LIKE UPPER('"+a+"%') GROUP BY t.id,e.pn,e.job,t.cantidad,e.prioridad");
    }
   private static ArrayList<ProductosTaller> SQLProdMaster2(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setIdlote(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJOB(rs.getString("JOB"));
                 t.setEstatus(rs.getString("NOLOTE1"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setNolote(rs.getInt("NOLOTE"));
                 t.setPrioridad(rs.getString("prioridad"));
                 list.add(t);
            }
            cn.close();
        }catch (Exception e) {
            System.out.println("error consulta shit"+e);
            return null;
        } 
        return list;
}
  
    public static ArrayList<ProductosTaller> ListarProductosPendientesMaster(String a) {
        return SQLP("select l.id_lote,t.pn,t.job,l.NOLOTE,l.cantidad from trabajo t inner join lotes l on t.id = l.id where id_lote in(select id_lote from pedidos_trabajos where estado = 1 ) and UPPER(t.PN) LIKE UPPER('"+a+"%')");
    }
    
    private static ArrayList<ProductosTaller> SQLP(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setIdlote(rs.getInt("ID_LOTE"));
                 t.setPN(rs.getString("PN"));
                 t.setJOB(rs.getString("JOB"));
                 t.setEstatus(rs.getString("NOLOTE"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 list.add(t);
            }
            cn.close();
        }catch (Exception e) {
            System.out.println("error consulta shit"+e);
            return null;
        } 
        return list;
    }
        public static ArrayList<ProductosTaller> ListarProductosTrabajo(int a,int b) {
        return Prod("select p.id_pedido,d.descripcion,p.cantidad,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS, p.nota,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') AS FECHA from pedidos_trabajos p inner join lotes l on p.id_lote = l.id_lote join trabajo t on t.id = l.id join productos_taller d on d.id_producto = p.id_producto WHERE  p.depto="+b+" and p.estado = 1 and l.ID_LOTE = "+a);
    }
         public static ArrayList<ProductosTaller> ListarProductosTrabajoMasterActualizar(int a) {
        return Prod("select p.id_pedido,d.descripcion,p.cantidad,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS, p.nota ,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') AS FECHA from pedidos_trabajos p inner join lotes l on p.id_lote = l.id_lote join trabajo t on t.id = l.id join productos_taller d on d.id_producto = p.id_producto WHERE  p.estado = 1 and l.ID_LOTE = "+a);
    }
       /* public static ArrayList<ProductosTaller> ListarProductosTrabajoMaster(int a) {//master listado de pedidos
        return Prod("select p.id_pedido,d.descripcion,p.cantidad,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS, p.nota from pedidos_trabajos p inner join lotes l on p.id_lote = l.id_lote join trabajo t on t.id = l.id join productos_taller d on d.id_producto = p.id_producto WHERE  p.estado = 1 and l.ID_LOTE = "+a);
    }*/ 
         public static ArrayList<ProductosTaller> ListarProductosTrabajoTerminados(int a) {
        return Prod("select p.id_pedido,d.descripcion,p.cantidad,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS, p.nota ,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') AS FECHA from pedidos_trabajos p inner join lotes l on p.id_lote = l.id_lote join trabajo t on t.id = l.id join productos_taller d on d.id_producto = p.id_producto WHERE p.estado = 2 and l.ID_LOTE = "+a);
    }  
         public static ArrayList<ProductosTaller> ListarProductosTrabajoEjemplo(int a,int b) {
        return Prod("select p.id_pedido,d.descripcion,p.cantidad,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS, p.nota ,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') AS FECHA from pedidos_trabajos p inner join ejemplos_trabajo t on t.id = p.id  join productos_taller d on d.id_producto = p.id_producto WHERE  p.estado = 1 and p.depto="+b+" and t.ID = "+a);
    }
      public static ArrayList<ProductosTaller> ListarProductosTrabajoEjemploMasterActualizar(int a) {
        return Prod("select p.id_pedido,d.descripcion,p.cantidad,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS, p.nota ,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') AS FECHA from pedidos_trabajos p inner join ejemplos_trabajo t on t.id = p.id  join productos_taller d on d.id_producto = p.id_producto WHERE  p.estado = 1 and t.ID = "+a);
    }  
         
       /*  public static ArrayList<ProductosTaller> ListarProductosTrabajoEjemploMaster(int a) {//productos ejemplo master
        return Prod("select p.id_pedido,d.descripcion,p.cantidad,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS, p.nota from pedidos_trabajos p inner join ejemplos_trabajo t on t.id = p.id  join productos_taller d on d.id_producto = p.id_producto WHERE  p.estado = 1 and t.ID = "+a);
    }*/
    private static ArrayList<ProductosTaller> Prod(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setIdpedido(rs.getInt("id_pedido"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setEstatus(rs.getString("STATUS"));
                 t.setNota(rs.getString("NOTA"));
                 t.setFechaStrin(rs.getString("FECHA"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
}
    public static ArrayList<ProductosTaller> ListarProductosTrabajoEjemploMaster(int a) {//productos ejemplo master
        return ProdMas("select p.id_pedido,d.descripcion,p.cantidad,decode(p.depto,0,'INFORMATICA',1,'TRANSFORMADORES',2,'INGENIERIA',3,'STRIP Y POTTING',4,'INSPECCION',5,'TESTING',6,'CALIDAD',7,'GERENTE OPERACIONES',8,'BODEGA',9,'RELACION CON EL CLIENTE',10,'TALLER',11,'GERENCIA',12,'CHIPS',13,'MOLDING') AS DEPTO,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') AS FECHA,p.nota from pedidos_trabajos p inner join ejemplos_trabajo t on t.id = p.id  join productos_taller d on d.id_producto = p.id_producto WHERE  p.estado = 1 and t.ID = "+a+" order by p.id_pedido");
    }
    private static ArrayList<ProductosTaller> ProdMas(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setIdpedido(rs.getInt("id_pedido"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setEstatus(rs.getString("STATUS"));
                 t.setDepartamento(rs.getString("DEPTO"));
                 t.setFechaStrin(rs.getString("FECHA"));
                 t.setNota(rs.getString("NOTA"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
}
    public static ArrayList<ProductosTaller> ListarProductosTrabajoMaster(int a) {//master listado de pedidos
        return ProdMAster2("select p.id_pedido,p.id_producto,d.descripcion,p.cantidad,decode(p.depto,0,'INFORMATICA',1,'TRANSFORMADORES',2,'INGENIERIA',3,'STRIP Y POTTING',4,'INSPECCION',5,'TESTING',6,'CALIDAD',7,'GERENTE OPERACIONES',8,'BODEGA',9,'RELACION CON EL CLIENTE',10,'TALLER',11,'GERENCIA',12,'CHIPS',13,'MOLDING') AS DEPTO,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') AS FECHA,p.nota from pedidos_trabajos p inner join lotes l on p.id_lote = l.id_lote join trabajo t on t.id = l.id join productos_taller d on d.id_producto = p.id_producto WHERE  p.estado = 1 and l.ID_LOTE = "+a+" order by p.id_pedido");
    }
    private static ArrayList<ProductosTaller> ProdMAster2(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setIdpedido(rs.getInt("id_pedido"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setEstatus(rs.getString("STATUS"));
                 t.setNota(rs.getString("NOTA"));
                 t.setDepartamento(rs.getString("depto"));
                 t.setFechaStrin(rs.getString("FECHA"));
                 t.setIdproducto(rs.getInt("id_producto"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
}
    //BUSCAR HISTORIAL
     public static ArrayList<ProductosTaller> ListarProductosTrabajoTerminadosAceptado(int a) {
        return ProdAcep("select p.id_pedido,decode(e.poinsert,null,'N/A',e.poinsert) as poinsert,decode(e.popotrod,null,'N/A',e.popotrod)as popotrod,d.descripcion,p.cantidad,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') as FECHA_SOLICITADO,to_char(e.fecha,'dd/mm/yyyy HH:MI:SS') as FECHA_ENTREGA,e.cantidad as CANTIDAD_ENTREGADA,e.po,e.realizado,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS,e.nota from pedidos_trabajos p inner join lotes l on p.id_lote = l.id_lote join trabajo t on t.id = l.id join productos_taller d on d.id_producto = p.id_producto join entregas_productos e on p.id_pedido = e.id_pedido WHERE p.estado = 2 and l.ID_LOTE ="+a+"order by d.id_producto");
    }
     
    public static ArrayList<ProductosTaller> ListarProductosTrabajoTerminadosAceptadoEjemplo(int a) {
        return ProdAcep("select p.id_pedido,decode(e.poinsert,null,'N/A',e.poinsert) as poinsert,decode(e.popotrod,null,'N/A',e.popotrod)as popotrod,d.descripcion,p.cantidad,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') as FECHA_SOLICITADO,to_char(e.fecha,'dd/mm/yyyy HH:MI:SS') as FECHA_ENTREGA,e.cantidad as CANTIDAD_ENTREGADA,e.po,e.realizado,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS,e.nota from pedidos_trabajos p inner join ejemplos_trabajo l on p.id = l.id join productos_taller d on d.id_producto = p.id_producto join entregas_productos e on p.id_pedido = e.id_pedido WHERE p.estado = 2 and l.ID ="+a+"order by d.id_producto");
    }
     
    public static ArrayList<ProductosTaller> ListarProductosTrabajoTerminadosAceptadoE(int a) {
        return ProdAcep("select p.id_pedido,decode(e.poinsert,null,'N/A',e.poinsert) as poinsert,decode(e.popotrod,null,'N/A',e.popotrod)as popotrod,d.descripcion,p.cantidad,to_char(p.fecha,'dd/mm/yyyy hh:mm:ss') as FECHA_SOLICITADO,to_char(e.fecha,'dd/mm/yyyy HH:MI:SS') as FECHA_ENTREGA,e.cantidad as CANTIDAD_ENTREGADA,e.po,e.realizado,decode(p.status,1,'EN PROCESO',2,'SOLICITANDO MATERIAL',3,'ENTREGADO',4,'ACEPTADO') AS STATUS,e.nota from pedidos_trabajos p inner join lotes l on p.id_lote = l.id_lote join trabajo t on t.id = l.id join productos_taller d on d.id_producto = p.id_producto join entregas_productos e on p.id_pedido = e.id_pedido WHERE p.estado = 2 and l.ID ="+a+"order by d.id_producto");
    } 
        
    private static ArrayList<ProductosTaller> ProdAcep(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setIdpedido(rs.getInt("id_pedido"));
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setCantidad(rs.getInt("CANTIDAD"));
                 t.setFechaSolicitado(rs.getString("FECHA_SOLICITADO"));
                 t.setFechaStrin(rs.getString("FECHA_ENTREGA"));
                 t.setCantidadEntre(rs.getInt("CANTIDAD_ENTREGADA"));
                 t.setPO(rs.getString("PO"));
                 t.setBy(rs.getInt("REALIZADO"));
                 t.setEstatus(rs.getString("STATUS"));
                 t.setNota(rs.getString("NOTA"));
                 t.setPOInsert(rs.getString("poinsert"));
                 t.setPOPotRod(rs.getString("popotrod"));
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
}

public static ArrayList<ClassTrabajos> ListarEjemplos(String a){
        return SQL2("select id,pn,job,cliente from ejemplos_trabajo where UPPER(PN) LIKE UPPER('"+a+"%')");
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
                 list.add(t);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("error consulta "+e);
            return null;
        } 
        return list;
}  

    public static ProductosTaller buscarTrabajo(int a) throws SQLException{
        return buscarTrabajoT(a ,null);
    }
    
    public static ProductosTaller buscarTrabajoT(int a, ProductosTaller c) throws SQLException {
             
            Connection cn = BD.getConnection();
            PreparedStatement ps = null;
            ps = cn.prepareStatement("select  t.id_pedido, p.descripcion,to_char(t.fecha,'dd/mm/yyyy hh:mm:ss') as fecha  from pedidos_trabajos t inner join productos_taller p on t.id_producto = p.id_producto where t.id_pedido="+a);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
               if (c==null)
               {c = new ProductosTaller(){
               };
               }
               c.setIdpedido(rs.getInt("id_pedido"));
               c.setDescripcion(rs.getString("Descripcion"));
               c.setFechaStrin(rs.getString("fecha"));
            }
            cn.close();
            ps.close();
            return c;
}
    
    public static void InsertarEntrega(ProductosTaller t) throws SQLException{
        Connection con = BD.getConnection();
        PreparedStatement ps = null;
        ps = con.prepareStatement("insert into ENTREGAS_PRODUCTOS(id_entregas,id_pedido,cantidad,fecha,nota,PO,realizado,poinsert,popotrod) values(EntregaProducto.NEXTVAL,?,?,sysdate,?,?,?,?,?)");
        //ps.setInt(1,t.getId_proceso());
        ps.setInt(1, t.getIdpedido());
        ps.setInt(2, t.getCantidad());
        ps.setString(3, t.getNota());
        ps.setString(4, t.getPO());
        ps.setInt(5, t.getBy());
        ps.setString(6, t.getPOInsert());
        ps.setString(7, t.getPOPotRod());
        ps.executeUpdate();
        con.close();
        ps.close(); 
    }

public static ArrayList<ProductosTaller> ListarProductosTaller() {
        return Produ("SELECT DESCRIPCION,decode(depto,0,'INFORMATICA',1,'TRANSFORMADORES',2,'INGENIERIA',3,'STRIP Y POTTING',4,'INSPECCION',5,'TESTING',6,'CALIDAD',7,'GERENTE OPERACIONES',8,'BODEGA',9,'RELACION CON EL CLIENTE',10,'TALLER',11,'GERENCIA',12,'CHIPS',13,'MOLDING') AS DEPTO FROM PRODUCTOS_TALLER");
    }
    private static ArrayList<ProductosTaller> Produ(String sql){
    ArrayList<ProductosTaller> list = new ArrayList<ProductosTaller>();
    Connection cn = BD.getConnection();
        try {
            ProductosTaller t;
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                 t = new ProductosTaller();
                 t.setDescripcion(rs.getString("DESCRIPCION"));
                 t.setDepartamento(rs.getString("DEPTO"));
                 list.add(t);
            }
            cn.close();
        }catch (Exception e) {
            System.out.println("error consulta shit"+e);
            return null;
        } 
        return list;
    }    
}   
     
