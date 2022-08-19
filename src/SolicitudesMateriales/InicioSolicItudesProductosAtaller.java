/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolicitudesMateriales;

import BD.BD;
import BD.InsertTrabajosTransformadores;
import BD.InsertarEjemplos;
import static BD.InsertarEjemplos.InsertarEjemplos;
import BD.InsertarProductosTaller;
import BD.ProductosTaller;
import Class.ClassTrabajos;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author jluis
 */
public class InicioSolicItudesProductosAtaller extends javax.swing.JInternalFrame {

    int id;
    int productoyaagregado;
    int productoagregado;
    int productoagregadoejemplo;
    int depto;
    int estado = 0;
    int Tipo;
    DefaultTableModel temp;

    /**
     * Creates new form InicioSolicutudes
     */
    public InicioSolicItudesProductosAtaller() {
        initComponents();
        ListarTrabajos();
        selectusuario();
        ListarEjemplos();
        jButton1.setEnabled(false);
        PN.requestFocus();
    }

    private void llenacuainformacion() {
        try {

            ClassTrabajos c = InsertTrabajosTransformadores.buscarTrabajoTrans(Integer.parseInt(String.valueOf(trab.getModel().getValueAt(trab.getSelectedRow(), 0))));
            id = c.getId();
            PNINFO.setText(c.getPN());
            JOBINFO.setText(c.getJob());
            CLIENTEINFO.setText(c.getCliente());
            ESTANDARINFO.setText(c.getEstandar());
            CANTIDADINFO.setText(String.valueOf(c.getQTYPORLOTE()));
            REVISIONINFO.setText(c.getRevision());
            nolote.setText(String.valueOf(c.getNOLOTE()));
            PRIORIDAD.setText(c.getPrioridadStrin());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e);
        }
    }

    private void llenaInformacionEjemplo() {
        try {

            ClassTrabajos c = InsertarEjemplos.buscarEjemplo(Integer.parseInt(String.valueOf(ejemplos.getModel().getValueAt(ejemplos.getSelectedRow(), 0))));
            id = c.getId();
            PNINFO.setText(c.getPN());
            JOBINFO.setText(c.getJob());
            CLIENTEINFO.setText(c.getCliente());
            ESTANDARINFO.setText(c.getEstandar());
            CANTIDADINFO.setText(String.valueOf(c.getQtyproduccion()));
            REVISIONINFO.setText(c.getRevision());
            nolote.setText("EJEMPLO");
            PRIORIDAD.setText(c.getPrioridadStrin());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e);
        }
    }

    public void VerificarComponentesAgregados() {
        int compo = (Integer.parseInt(String.valueOf(PRODUCTOS.getModel().getValueAt(PRODUCTOS.getSelectedRow(), 0))));
        try {
            Connection c = BD.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select COUNT(*) from PEDIDOS_TRABAJOS where ID_LOTE = " + id + " and ID_PRODUCTO =" + compo);
            while (rs.next()) {
                int lastID = rs.getInt(1);
                productoagregado = lastID;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    public void VerificarComponentesAgregadosEjemplos() {
        int compo = (Integer.parseInt(String.valueOf(PRODUCTOS.getModel().getValueAt(PRODUCTOS.getSelectedRow(), 0))));
        try {
            Connection c = BD.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select COUNT(*) from PEDIDOS_TRABAJOS where ID = " + id + " and tipo = 2 and ID_PRODUCTO =" + compo);
            while (rs.next()) {
                int lastID = rs.getInt(1);
                productoagregadoejemplo = lastID;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    public void insertarNuevoProcuto() {
        int P; 
        
        try {
            int idproducto = (Integer.parseInt(String.valueOf(PRODUCTOS.getModel().getValueAt(PRODUCTOS.getSelectedRow(), 0))));
            ProductosTaller m = new ProductosTaller();
            m.setIdproducto(idproducto);
            m.setIdlote(id);
            SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:s");
            String fecha1 = FECHAAUTO.getText();
            Date fecha = df.parse(fecha1);
            m.setFecha(fecha);
            m.setCantidad(Integer.parseInt(CANTIDADINFO.getText()));
            m.setDepto(depto);
            m.setTipo(Tipo);
            InsertarProductosTaller.insertarNuevoProducto(m);
        } catch (SQLException e) {
            System.out.println("Error BD:" + e);
        } catch (ParseException ex) {
            Logger.getLogger(InicioSolicItudesProductosAtaller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarNuevoProcutoReproceso() {
        int P; 
        
        try {
            int idproducto = (Integer.parseInt(String.valueOf(PRODUCTOS.getModel().getValueAt(PRODUCTOS.getSelectedRow(), 0))));
            ProductosTaller m = new ProductosTaller();
            m.setIdproducto(idproducto);
            m.setIdlote(id);
            SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:s");
            String fecha1 = FECHAAUTO.getText();
            Date fecha = df.parse(fecha1);
            m.setFecha(fecha);
            m.setCantidad(Integer.parseInt(CANTIDADINFO.getText()));
            m.setDepto(depto);
            m.setTipo(Tipo);
            InsertarProductosTaller.insertarNuevoProductoReproceso(m);
        } catch (SQLException e) {
            System.out.println("Error BD:" + e);
        } catch (ParseException ex) {
            Logger.getLogger(InicioSolicItudesProductosAtaller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarNuevoProcutoEjemplo() {
        try {
            int idproducto = (Integer.parseInt(String.valueOf(PRODUCTOS.getModel().getValueAt(PRODUCTOS.getSelectedRow(), 0))));
            ProductosTaller m = new ProductosTaller();
            m.setIdproducto(idproducto);
            m.setIdlote(id);
            //SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:s");
            //String fecha1 = FECHAAUTO.getText();
            //Date fecha=df.parse(fecha1);
            //m.setFecha(fecha);
            m.setCantidad(Integer.parseInt(CANTIDADINFO.getText()));
            m.setDepto(depto);
            m.setTipo(Tipo);
            InsertarProductosTaller.insertarNuevoProductoEjemplo(m);
        } catch (SQLException e) {
            System.out.println("Error BD:" + e);
        }
    }
    
    public void insertarNuevoProcutoEjemploReproceso() {
        try {
            int idproducto = (Integer.parseInt(String.valueOf(PRODUCTOS.getModel().getValueAt(PRODUCTOS.getSelectedRow(), 0))));
            ProductosTaller m = new ProductosTaller();
            m.setIdproducto(idproducto);
            m.setIdlote(id);
            //SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:s");
            //String fecha1 = FECHAAUTO.getText();
            //Date fecha=df.parse(fecha1);
            //m.setFecha(fecha);
            m.setCantidad(Integer.parseInt(CANTIDADINFO.getText()));
            m.setDepto(depto);
            m.setTipo(Tipo);
            InsertarProductosTaller.insertarNuevoProductoEjemploreproceso(m);
        } catch (SQLException e) {
            System.out.println("Error BD:" + e);
        }
    }

    public void remover() {
        try {
            int idproducto = (Integer.parseInt(String.valueOf(YasolicitadosE.getModel().getValueAt(YasolicitadosE.getSelectedRow(), 0))));
            ProductosTaller m = new ProductosTaller();
            m.setIdlote(id);
            m.setDepto(depto);
            m.setIdproducto(idproducto);
            InsertarProductosTaller.deleteProducto(m);
        } catch (Exception e) {
            System.out.println("Error BD:" + e.getMessage());
        }
    }

    public void removerEjemplo() {
        try {
            int idproducto = (Integer.parseInt(String.valueOf(YasolicitadosE.getModel().getValueAt(YasolicitadosE.getSelectedRow(), 0))));
            ProductosTaller m = new ProductosTaller();
            m.setIdlote(id);
            m.setDepto(depto);
            m.setIdproducto(idproducto);
            InsertarProductosTaller.deleteProductoEjemplo(m);
        } catch (Exception e) {
            System.out.println("Error BD:" + e.getMessage());
        }
    }

    public void removerPorCancelacion() {
        try {
            //int idproducto = (Integer.parseInt(String.valueOf(Yasolicitados.getModel().getValueAt(Yasolicitados.getSelectedRow(), 0))));
            ProductosTaller m = new ProductosTaller();
            m.setIdlote(id);
            m.setDepto(depto);
            InsertarProductosTaller.deletePorCancelacion(m);
        } catch (Exception e) {
            System.out.println("Error BD:" + e.getMessage());
        }
    }

    public void removerPorCancelacionEjemplo() {
        try {
            //int idproducto = (Integer.parseInt(String.valueOf(Yasolicitados.getModel().getValueAt(Yasolicitados.getSelectedRow(), 0))));
            ProductosTaller m = new ProductosTaller();
            m.setIdlote(id);
            m.setDepto(depto);
            InsertarProductosTaller.deletePorCancelacionEjemplo(m);
        } catch (Exception e) {
            System.out.println("Error BD:" + e.getMessage());
        }
    }

    public void confirmar() {

        if (id != 0 && PNINFO.getText().compareTo("") != 0) {
            try {

                Connection cnn = BD.getConnection();
                PreparedStatement ps = null;
                ps = cnn.prepareStatement("UPDATE PEDIDOS_TRABAJOS set ESTADO = 1 where ID_LOTE=" + id + " and ESTADO = 0 and DEPTO =" + depto);
                //ps.setInt(1,id);
                //PS.set
                //int rowsUpdated = 
                ps.executeUpdate();
                cnn.close();
                ps.close();
                JOptionPane.showMessageDialog(null, "PEDIDO CONFIRMADO CORECTAMENTE");
                limpiar();
            } catch (Exception e) {
                System.out.println("Error BD:" + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Nada Que Agregar");
        }
    }

    public void confirmarEjemplo() {

        if (id != 0 && PNINFO.getText().compareTo("") != 0) {
            try {

                Connection cnn = BD.getConnection();
                PreparedStatement ps = null;
                ps = cnn.prepareStatement("UPDATE PEDIDOS_TRABAJOS set ESTADO = 1 where ID=" + id + " and ESTADO = 0 and DEPTO =" + depto);
                //ps.setInt(1,id);
                //PS.set
                //int rowsUpdated = 
                ps.executeUpdate();
                cnn.close();
                ps.close();
                JOptionPane.showMessageDialog(null, "PEDIDO CONFIRMADO CORECTAMENTE");
                limpiar();
            } catch (Exception e) {
                System.out.println("Error BD:" + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tiene Nada Que Agregar");
        }
    }

    public void selectusuario() {
        String a = System.getProperty("user.name");//usar usuario de windows
        if (a.equals("jluis")) {
            depto = 10;  //cambio en HistorialStatus,InicioSolicItudesProductosAtaller,Status
        } //INFORMATICA
        else if (a.equals("ehernandez")) {
            depto = 1;
        } //TRANSFORMADORES
        else if (a.equals("ingenieria2")) {
            depto = 2;
        } //INGENIERIA
        else if (a.equals("potting")) {
            depto = 3;
        } //STRIP & POTTING
        else if (a.equals("Inspeccion")) {
            depto = 4;
        }// INSPECCION
        else if (a.equals("testing")) {
            depto = 5;
        } // TESTING
        else if (a.equals("calidad")) {
            depto = 6;
        } // CALIDAD 
        else if (a.equals("oecheverria")) {
            depto = 7;
        }//GERENTE OPERACIONES
        else if (a.equals("bodega")) {
            depto = 8;
        }//BODEGA 
        else if (a.equals("ingenieria")) {
            depto = 9;
        }//RELACION CON EL CLIENTE 
        else if (a.equals("taller")) {
            depto = 10;
        }//TALLER
        else if (a.equals("Sotano")) {
            depto = 10;
        }//TALLER
        else if (a.equals("apacheco")) {
            depto = 11;
        } else if (a.equals("deptochips")) {
            depto = 12;
        } else if (a.equals("molding")) {
            depto = 13;
        }
    }

    public void Cfecha() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        FECHAAUTO.setText(dateFormat.format(cal.getTime()));
    }

    private void limpiar() {
        PNINFO.setText("");
        JOBINFO.setText("");
        CLIENTEINFO.setText("");
        ESTANDARINFO.setText("");
        REVISIONINFO.setText("");
        nolote.setText("");
        CANTIDADINFO.setText("");
        PRIORIDAD.setText("");
        FECHAAUTO.setText("");
        clearProductos();
        clearProductosAgregados();
        trab.setEnabled(true);
        ejemplos.setEnabled(true);
        PN.setEnabled(true);
        PN1.setEnabled(true);
        estado = 0;
        jButton1.setEnabled(false);
        PN.requestFocus();
        pestañas.setEnabled(true);
        PN.setEnabled(true);
        PN1.setEnabled(true);
        
    }

    public void clearProductos() {
        try {
            temp = (DefaultTableModel) PRODUCTOS.getModel();
            int a = temp.getRowCount();
            for (int i = 0; i < a; i++) {
                temp.removeRow(i);
                i--;
            }
        } catch (Exception e) {
        }
    }

    public void clearProductosAgregados() {
        try {
            temp = (DefaultTableModel) YasolicitadosE.getModel();
            int a = temp.getRowCount();
            for (int i = 0; i < a; i++) {
                temp.removeRow(i);
                i--;
            }
        } catch (Exception e) {
        }
    }

    /*public void VerificarComponentesantesderemover() {
        int soli = (Integer.parseInt(String.valueOf(Yasolicitados.getModel().getValueAt(Yasolicitados.getSelectedRow(), 0))));
        try {
            Connection c = BD.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select COUNT(*) from PEDIDOS_TRABAJOS where ID_LOTE = " + id + " and depto = " + depto + " and ID_PRODUCTO =" + soli + " and estado = 1");
            while (rs.next()) {
                int ID = rs.getInt(1);
                productoyaagregado = ID;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void ListarTrabajos() {
        ArrayList<ClassTrabajos> result = InsertTrabajosTransformadores.ListarTrabajosSolicitudesMat(PN.getText());
        RecargarTabla(result);
    }

    private void RecargarTabla(ArrayList<ClassTrabajos> list) {

        Object[][] datos = new Object[list.size()][4];
        int i = 0;
        for (ClassTrabajos t : list) {
            datos[i][0] = t.getId();
            datos[i][1] = t.getPN();
            datos[i][2] = t.getJob();
            datos[i][3] = t.getNOLOTE();
            i++;
        }
        trab.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    " ", "P/N", "JOB", "NO. LOTE"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        TableColumn columna1 = trab.getColumn(" ");
        columna1.setPreferredWidth(-20);
        TableColumn columna2 = trab.getColumn("P/N");
        columna2.setPreferredWidth(75);
        TableColumn columna3 = trab.getColumn("JOB");
        columna3.setPreferredWidth(75);
        TableColumn columna4 = trab.getColumn("NO. LOTE");
        columna4.setPreferredWidth(75);
    }

    private void ListarEjemplos() {
        ArrayList<ClassTrabajos> result = InsertarProductosTaller.ListarEjemplos(PN1.getText());
        RecargarTablaEjemplo(result);
    }

    private void RecargarTablaEjemplo(ArrayList<ClassTrabajos> list) {

        Object[][] datos = new Object[list.size()][4];
        int i = 0;
        for (ClassTrabajos t : list) {
            datos[i][0] = t.getId();
            datos[i][1] = t.getPN();
            datos[i][2] = t.getJob();
            datos[i][3] = t.getCliente();
            i++;
        }
        ejemplos.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    " ", "P/N", "JOB", "CLIENTE"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        TableColumn columna1 = ejemplos.getColumn(" ");
        columna1.setPreferredWidth(-20);
        TableColumn columna2 = ejemplos.getColumn("P/N");
        columna2.setPreferredWidth(75);
        TableColumn columna3 = ejemplos.getColumn("JOB");
        columna3.setPreferredWidth(75);
        TableColumn columna4 = ejemplos.getColumn("CLIENTE");
        columna4.setPreferredWidth(75);
    }

    private void ListarProductos() {
        ArrayList<ClassTrabajos> result = InsertTrabajosTransformadores.ListarProductos(depto);
        RecargarTablaProductos(result);
    }

    private void RecargarTablaProductos(ArrayList<ClassTrabajos> list) {

        Object[][] datos = new Object[list.size()][4];
        int i = 0;
        for (ClassTrabajos t : list) {
            datos[i][0] = t.getId();
            datos[i][1] = t.getDescripcion();

            i++;
        }
        PRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "ID", "DESCRIPICION"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        TableColumn columna1 = PRODUCTOS.getColumn("ID");
        columna1.setPreferredWidth(-20);
        TableColumn columna2 = PRODUCTOS.getColumn("DESCRIPICION");
        columna2.setPreferredWidth(275);/*
             TableColumn columna3 = trab.getColumn("JOB");
             columna3.setPreferredWidth(75);
             TableColumn columna4 = trab.getColumn("NO. LOTE");
             columna4.setPreferredWidth(75);*/
    }

    private void ListarProductosYaSolicitados() {
        ArrayList<ClassTrabajos> result = InsertTrabajosTransformadores.ListarProductosYaSolicitados(id, depto);
        ProductosSolicitados(result);
    }

    private void ProductosSolicitados(ArrayList<ClassTrabajos> list) {

        Object[][] datos = new Object[list.size()][4];
        int i = 0;
        for (ClassTrabajos t : list) {
            datos[i][0] = t.getId();
            datos[i][1] = t.getDescripcion();
            datos[i][2] = t.getFecha();
            datos[i][3] = t.getCantidad();
            i++;
        }
        YasolicitadosE.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "ID", "DESCRIPCION", "FECHA SOLICITADO","CANTIDAD"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        TableColumn columna1 = YasolicitadosE.getColumn("ID");
        columna1.setPreferredWidth(0);
        TableColumn columna2 = YasolicitadosE.getColumn("DESCRIPCION");
        columna2.setPreferredWidth(200);
        TableColumn columna3 = YasolicitadosE.getColumn("FECHA SOLICITADO");
        columna3.setPreferredWidth(200);
        TableColumn columna4 = YasolicitadosE.getColumn("CANTIDAD");
        columna4.setPreferredWidth(200);

    }
    
    
    private void ListarProductosYaSolicitadosEje() {
        ArrayList<ClassTrabajos> result = InsertTrabajosTransformadores.ListarProductosYaSolicitadosEjemplo(id, depto);
        ProductosSolicitadosEje(result);
    }

    private void ProductosSolicitadosEje(ArrayList<ClassTrabajos> list) {

        Object[][] datos = new Object[list.size()][4];
        int i = 0;
        for (ClassTrabajos t : list) {
            datos[i][0] = t.getId();
            datos[i][1] = t.getDescripcion();
            datos[i][2] = t.getFecha();
            datos[i][3] = t.getCantidad();
            i++;
        }
        YasolicitadosE.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "ID", "DESCRIPCION", "FECHA SOLICITADO","CANTIDAD"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        TableColumn columna1 = YasolicitadosE.getColumn("ID");
        columna1.setPreferredWidth(0);
        TableColumn columna2 = YasolicitadosE.getColumn("DESCRIPCION");
        columna2.setPreferredWidth(200);
        TableColumn columna3 = YasolicitadosE.getColumn("FECHA SOLICITADO");
        columna3.setPreferredWidth(200);
        TableColumn columna4 = YasolicitadosE.getColumn("CANTIDAD");
        columna4.setPreferredWidth(200);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PNINFO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JOBINFO = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CLIENTEINFO = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ESTANDARINFO = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        REVISIONINFO = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nolote = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        CANTIDADINFO = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PRIORIDAD = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        FECHAAUTO = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PRODUCTOS = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        YasolicitadosE = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        pestañas = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        trab = new javax.swing.JTable();
        PN = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ejemplos = new javax.swing.JTable();
        PN1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFORMACION DE SOLICITUD DE PRODUCTOS ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(51, 204, 255));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setForeground(new java.awt.Color(102, 255, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("P/N");

        PNINFO.setEditable(false);
        PNINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PNINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("JOB");

        JOBINFO.setEditable(false);
        JOBINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JOBINFO.setForeground(new java.awt.Color(0, 102, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JOBINFO)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 127, Short.MAX_VALUE))
                    .addComponent(PNINFO))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PNINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JOBINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("CLIENTE");

        CLIENTEINFO.setEditable(false);
        CLIENTEINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CLIENTEINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("ESTANDAR");

        ESTANDARINFO.setEditable(false);
        ESTANDARINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ESTANDARINFO.setForeground(new java.awt.Color(0, 102, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ESTANDARINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(CLIENTEINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CLIENTEINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ESTANDARINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("REVISION");

        REVISIONINFO.setEditable(false);
        REVISIONINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        REVISIONINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("#LOTE");

        nolote.setEditable(false);
        nolote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nolote.setForeground(new java.awt.Color(0, 102, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(REVISIONINFO)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 91, Short.MAX_VALUE))
                    .addComponent(nolote))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(REVISIONINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nolote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("CANTIDAD");

        CANTIDADINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CANTIDADINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("PRIORIDAD #");

        PRIORIDAD.setEditable(false);
        PRIORIDAD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PRIORIDAD.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CANTIDADINFO)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addComponent(PRIORIDAD))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CANTIDADINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PRIORIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("FECHA");

        FECHAAUTO.setEditable(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 90, Short.MAX_VALUE))
                    .addComponent(FECHAAUTO))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHAAUTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("AGREGAR SOLICITUD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PARTES  PARA P/N ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        PRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "DESCRIPCION"
            }
        ));
        PRODUCTOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PRODUCTOSMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(PRODUCTOS);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("PARTES SOLICITADAS"));

        YasolicitadosE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "DESCRIPCION", "CANTIDAD"
            }
        ));
        YasolicitadosE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                YasolicitadosEMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(YasolicitadosE);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(12, 12, 12))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("SOLICITUD DE PRODUCTOS A TALLER");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jButton3.setText("CERRAR");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pestañas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pestañasMouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR P/N", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel5.setForeground(new java.awt.Color(153, 153, 153));

        trab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P/N", "LOTE", "JOB"
            }
        ));
        trab.setToolTipText("");
        trab.setFocusable(false);
        trab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(trab);

        PN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PNKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pestañas.addTab("TRABAJOS", jPanel5);

        jPanel12.setBackground(new java.awt.Color(51, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR P/N EJEMPLOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel12.setForeground(new java.awt.Color(153, 153, 153));

        ejemplos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P/N", "LOTE", "JOB"
            }
        ));
        ejemplos.setToolTipText("");
        ejemplos.setFocusable(false);
        ejemplos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ejemplosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(ejemplos);

        PN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PN1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pestañas.addTab("EJEMPLOS", jPanel12);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(252, 252, 252)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pestañas))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PNKeyReleased
        ListarTrabajos();
    }//GEN-LAST:event_PNKeyReleased
    private void trabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trabMouseClicked
        Tipo = 1;
        if (estado == 0) {
            llenacuainformacion();
            ListarProductos();
            ListarProductosYaSolicitados();//error
            Cfecha();
        }
    }//GEN-LAST:event_trabMouseClicked

    private void PRODUCTOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRODUCTOSMouseClicked
        
        
        //SOLICITAR MATERIALES
        
        if (evt.getClickCount() > 1) {

            if (Tipo == 1) {
                VerificarComponentesAgregados();
                Cfecha();
                if (productoagregado == 0) {
                    trab.setEnabled(false);
                    PN.setEnabled(false);
                    pestañas.setEnabled(false);
                    estado = 1;
                    trab.clearSelection();
                    jButton1.setEnabled(true);
                    insertarNuevoProcuto();
                    ListarProductosYaSolicitados();
                } else {
                    
                    trab.setEnabled(false);
                    PN.setEnabled(false);
                    pestañas.setEnabled(false);
                    estado = 1;
                    trab.clearSelection();
                    jButton1.setEnabled(true);
                    insertarNuevoProcutoReproceso();
                    ListarProductosYaSolicitados();
                    JOptionPane.showMessageDialog(null, "PRODUCTO INSERTADO COMO REPROCESO");
                }
                
            } else if (Tipo == 2) {
                VerificarComponentesAgregadosEjemplos();
                if (productoagregadoejemplo == 0) {
                    ejemplos.setEnabled(false);
                    PN1.setEnabled(false);
                    pestañas.setEnabled(false);
                    estado = 1;
                    ejemplos.clearSelection();
                    jButton1.setEnabled(true);
                    insertarNuevoProcutoEjemplo();
                    ListarProductosYaSolicitadosEje();
                } else {
                    ejemplos.setEnabled(false);
                    PN1.setEnabled(false);
                    pestañas.setEnabled(false);
                    estado = 1;
                    ejemplos.clearSelection();
                    jButton1.setEnabled(true);
                    insertarNuevoProcutoEjemploReproceso();
                    ListarProductosYaSolicitadosEje();
                    JOptionPane.showMessageDialog(null, "PRODUCTO INSERTADO COMO REPROCESO");
                }
            }
        }
    }//GEN-LAST:event_PRODUCTOSMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (Tipo == 1) {
            confirmar();
        } else if (Tipo == 2) {
            confirmarEjemplo();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void YasolicitadosEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_YasolicitadosEMouseClicked
        if (evt.getClickCount() > 1) {
            if (Tipo == 1) {
                    remover();
                    ListarProductosYaSolicitados();
                } else if (Tipo == 2) {
                    removerEjemplo();
                    ListarProductosYaSolicitados();
                }
            }
    }//GEN-LAST:event_YasolicitadosEMouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

        int resp = JOptionPane.showConfirmDialog(null, "DESEA CANCELAR LA SOLICITUD...");
        if (JOptionPane.OK_OPTION == resp) {
            removerPorCancelacion();
        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (estado == 1) {

            if (Tipo == 1) {
                int resp = JOptionPane.showConfirmDialog(null, "DESEA CANCELAR LA SOLICITUD...", "CANCELAR", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.OK_OPTION == resp) {
                    removerPorCancelacion();
                    limpiar();
                } else {
                }
            } else if (Tipo == 2) {
                int resp = JOptionPane.showConfirmDialog(null, "DESEA CANCELAR LA SOLICITUD...", "CANCELAR", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.OK_OPTION == resp) {
                    removerPorCancelacionEjemplo();
                    limpiar();
                } else {
                }

            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (estado == 1) {
            int resp = JOptionPane.showConfirmDialog(null, "DESEA CANCELAR LA SOLICITUD...",
                    "CANCELAR", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (JOptionPane.OK_OPTION == resp) {
                removerPorCancelacion();
                this.dispose();
            } else {
            }
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ejemplosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ejemplosMouseClicked
        Tipo = 2;
        if (estado == 0) {
            llenaInformacionEjemplo();
            ListarProductos();
            ListarProductosYaSolicitadosEje();
            Cfecha();
        }
    }//GEN-LAST:event_ejemplosMouseClicked

    private void PN1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PN1KeyReleased
        ListarEjemplos();
    }//GEN-LAST:event_PN1KeyReleased

    private void pestañasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pestañasMouseClicked

    }//GEN-LAST:event_pestañasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioSolicItudesProductosAtaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSolicItudesProductosAtaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSolicItudesProductosAtaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSolicItudesProductosAtaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSolicItudesProductosAtaller().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CANTIDADINFO;
    private javax.swing.JTextField CLIENTEINFO;
    private javax.swing.JTextField ESTANDARINFO;
    private javax.swing.JTextField FECHAAUTO;
    private javax.swing.JTextField JOBINFO;
    private javax.swing.JTextField PN;
    private javax.swing.JTextField PN1;
    private javax.swing.JTextField PNINFO;
    private javax.swing.JTextField PRIORIDAD;
    private javax.swing.JTable PRODUCTOS;
    private javax.swing.JTextField REVISIONINFO;
    private javax.swing.JTable YasolicitadosE;
    private javax.swing.JTable ejemplos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField nolote;
    private javax.swing.JTabbedPane pestañas;
    private javax.swing.JTable trab;
    // End of variables declaration//GEN-END:variables
}
