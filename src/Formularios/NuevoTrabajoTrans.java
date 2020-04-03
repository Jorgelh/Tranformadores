/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Class.ClassTrabajos;
import BD.*;
import Consultas.EjemplosAprobadosParaTrabajos;
import static Formularios.Inicio.Pane1;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jluis
 */
public class NuevoTrabajoTrans extends javax.swing.JInternalFrame {
     int estandar;
     int cantidadporlote;
     int prioridad;
     int n;
     int id;
    /**
     * Creates new form Trabajos
     */
    
    int n1 = 0;
    public NuevoTrabajoTrans(int i) {
        this.id = i;//id de ejemplo para buscar informacion y complementar el trabajo 
         try {
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
             
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        initComponents();
        obtenerID();
        if(id !=0){llenacuainformacion();}
        
        
    }
    
    private void llenacuainformacion() {
       try {
           
            
            ClassTrabajos c = InsertarEjemplos.buscarEjemploTrabajo(id);
            PN.setText(c.getPN());
            JOB.setText(c.getJob());
            CLIENTE.setText(c.getCliente());
            ESTANDAR.setSelectedItem(c.getEstandar());
            QTYCLIENTE.setText(String.valueOf(c.getQtyproduccion()));
            REVISION.setText(c.getRevision());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            Date datein = sdf.parse(c.getFecha());
            FECHAIN.setDate(datein);
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e);
        }
    }
    
    
    
    
    
     public void obtenerID()
    {
        
        try {
             Connection cn = BD.getConnection();
             Statement ps = cn.createStatement();
             ResultSet rs = ps.executeQuery("select max(ID) from trabajo");
             rs.next();
             n = (rs.getInt("max(ID)"));
             ps.close();
             rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error");
        }
          NO.setText(String.valueOf(n+1));
          QTYPRODUCCION.requestFocus();
       
    }
    
    public void estandar(){
       
        if (ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("FUJI")) {
            estandar = 1;
        } else if (ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("INGENIERIA")) {
            estandar = 2;
        } else if (ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("MIL-PRF-27"))
           {estandar = 3;}
        else if (ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("MIL-STD-981"))
           {estandar = 4;}
        else if (ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("MIL-STD-981 PRE-CAP"))
           {estandar = 5;}
        else if (ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("MIL-STD-981 URGENTE"))
           {estandar = 6;}
        else if (ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("MIL-STD-981 X-RAY"))
           {estandar = 7;}
    }
    
    public void crearlotes(){
    
        try {
            
            
            Connection c = BD.getConnection();
            Statement ps = c.createStatement();
            ps.executeUpdate("BEGIN CREARLOTESTRANS(Clotes=>"+QTYDELOTES.getText()+",Ncantidad=>"+QTYPORLOTE.getText()+",NNotas=>'"+COMENTARIO.getText()+"',IDTrabajo=>"+NO.getText()+",NPRIORIDAD=>"+prioridad+"); COMMIT; END;");
            c.close();
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error"+e);
        }
    }
    
    public void actualizarEstadoEjemplos(){
    try {
            
            
            Connection c = BD.getConnection();
            Statement ps = c.createStatement();
            ps.executeUpdate("UPDATE EJEMPLOS_TRABAJO SET ESTADO = 3 WHERE ID ="+id);
            c.close();
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error"+e);
        }
    }


    
    
    
    public void ingresoTrabajo(){
    if(PN.getText().compareTo("")!=0 && 
       JOB.getText().compareTo("")!=0 && 
       CLIENTE.getText().compareTo("")!=0 && 
       QTYCLIENTE.getText().compareTo("")!=0 &&
       !ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("SELECCIONAR...")&&     
       REVISION.getText().compareTo("")!=0 &&
       QTYDELOTES.getText().compareTo("")!=0 &&
       FECHAIN.getDate()!=null){
        estandar();
        try {
            ClassTrabajos t = new ClassTrabajos();
            t.setId(Integer.parseInt(NO.getText()));
            t.setPN(PN.getText().toUpperCase());
            t.setJob(JOB.getText().toUpperCase());
            t.setCliente(CLIENTE.getText().toUpperCase());
            t.setQtyproduccion(Integer.parseInt(QTYPRODUCCION.getText()));
            t.setQtycliente(Integer.parseInt(QTYCLIENTE.getText()));
            t.setEstandarint(estandar);
            t.setRevision(REVISION.getText().toUpperCase());
            t.setQTYDELOTES(Integer.parseInt(QTYDELOTES.getText()));
            t.setFechain(FECHAIN.getDate());
            t.setComentarios(COMENTARIO.getText());
            if (URGENTE.isSelected()) {prioridad = 1;}else{prioridad = 0;}
            t.setPrioridad(prioridad);
            cantidadporlote = Integer.parseInt(QTYPORLOTE.getText());//cantidad de lote
           //t.setNOLOTE(i+1);
            InsertTrabajosTransformadores.InsertarTrabajoTrans(t);
            crearlotes();
            actualizarEstadoEjemplos();
            limpiar();
            JOptionPane.showMessageDialog(null, "Trabajo Guardado");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "error"+e);
        }
        } else {JOptionPane.showMessageDialog(null,"Ingrese Todos los Datos");}
        }
    
   /* public void ingresoTrabajoSinCantidad(){
    if(PN.getText().compareTo("")!=0 && 
       JOB.getText().compareTo("")!=0 && 
       CLIENTE.getText().compareTo("")!=0 && 
       QTYCLIENTE.getText().compareTo("")!=0 && 
       ESTANDAR.getText().compareTo("")!=0 && 
       REVISION.getText().compareTo("")!=0 &&
       QTYDELOTES.getText().compareTo("")!=0 &&
       FECHAIN.getDate()!=null){
        for(int i=0; Integer.parseInt(QTYDELOTES.getText())>i;i++){
        try {
            ClassTrabajos t = new ClassTrabajos();
            //t.setId(Integer.parseInt(Id.getText()));
            t.setPN(PN.getText().toUpperCase());
            t.setJob(JOB.getText().toUpperCase());
            t.setCliente(CLIENTE.getText().toUpperCase());
            t.setQtyproduccion(Integer.parseInt(QTYPRODUCCION.getText()));
            t.setQtycliente(Integer.parseInt(QTYCLIENTE.getText()));
            t.setEstandar(ESTANDAR.getText().toUpperCase());
            t.setRevision(REVISION.getText().toUpperCase());
            t.setQTYDELOTES(Integer.parseInt(QTYDELOTES.getText()));
            t.setFechain(FECHAIN.getDate());
            t.setComentarios(COMENTARIO.getText());
            t.setNOLOTE(i+1);
            String ax = JOptionPane.showInputDialog("Ingrese cantidad Para el Lote: "+(i+1));
            t.setQTYPORLOTE(Integer.parseInt(ax));//cantidad de lote
            
            InsertTrabajos.InsertarTrabajo(t);       
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "error"+e);
        }
        }
            JOptionPane.showMessageDialog(null, "Trabajo Guardado");
            limpiar();
    } else {JOptionPane.showMessageDialog(null,"Ingrese Todos los Datos");}
    }*/
    
    /*public void id(){
        
        try {
             Connection cn = BD.getConnection();
             Statement ps = cn.createStatement();
             ResultSet rs = ps.executeQuery("select max(ID) from TRABAJO");
             rs.next();
             n1 = (rs.getInt("max(ID)"));
             ps.close();
             rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
          Id.setText(String.valueOf(n1+1));
          
    
    }*/
    
    public void limpiar(){
    
        PN.setText("");
        JOB.setText("");
        CLIENTE.setText("");
        QTYPRODUCCION.setText("");
        QTYCLIENTE.setText("");
        ESTANDAR.setSelectedItem("SELECCIONAR...");
        REVISION.setText("");
        QTYDELOTES.setText("");
        FECHAIN.setDate(null);
        COMENTARIO.setText("");
        PN.requestFocus();
        QTYPORLOTE.setText("");
        NO.setText("");
        id=0;
        bloquear();
        URGENTE.setSelected(false);
                
        
        ;
    
    }
    public void bloquear(){
        PN.setEnabled(false);
        JOB.setEnabled(false);
        CLIENTE.setEnabled(false);
        QTYPRODUCCION.setEnabled(false);
        QTYCLIENTE.setEnabled(false);
        ESTANDAR.setEnabled(false);
        REVISION.setEnabled(false);
        QTYDELOTES.setEnabled(false);
        FECHAIN.setEnabled(false);
        COMENTARIO.setEnabled(false);
        QTYPORLOTE.setEnabled(false);
        GUARDAR.setEnabled(false);
    }
    
    public void lote(){
    
        int a = Integer.parseInt(QTYPRODUCCION.getText())/Integer.parseInt(QTYDELOTES.getText());
         QTYPORLOTE.setText(String.valueOf(a));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PN = new javax.swing.JTextField();
        JOB = new javax.swing.JTextField();
        CLIENTE = new javax.swing.JTextField();
        QTYPRODUCCION = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        QTYCLIENTE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        QTYDELOTES = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        QTYPORLOTE = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        COMENTARIO = new javax.swing.JTextArea();
        FECHAIN = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        REVISION = new javax.swing.JTextField();
        ESTANDAR = new javax.swing.JComboBox<>();
        URGENTE = new javax.swing.JCheckBox();
        GUARDAR = new javax.swing.JButton();
        NO = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        NUEVO = new javax.swing.JButton();

        setClosable(true);
        setTitle("NUEVO TRABAJO");
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("P/N");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("JOB");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CLIENTE");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CANTIDAD PRODUCCION");

        PN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNActionPerformed(evt);
            }
        });

        JOB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOBActionPerformed(evt);
            }
        });

        CLIENTE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CLIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLIENTEActionPerformed(evt);
            }
        });

        QTYPRODUCCION.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QTYPRODUCCION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYPRODUCCIONActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CANTIDAD CLIENTE");

        QTYCLIENTE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QTYCLIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYCLIENTEActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("CANTIDAD DE LOTES");

        QTYDELOTES.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QTYDELOTES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYDELOTESActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("CANTIDAD POR LOTE ");

        QTYPORLOTE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QTYPORLOTE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QTYPORLOTEMouseClicked(evt);
            }
        });
        QTYPORLOTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYPORLOTEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QTYPRODUCCION, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CLIENTE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JOB, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(QTYPORLOTE)
                    .addComponent(QTYCLIENTE)
                    .addComponent(PN)
                    .addComponent(QTYDELOTES)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QTYCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QTYPRODUCCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QTYDELOTES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QTYPORLOTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("FECHA INGRESO");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("COMENTARIO");

        COMENTARIO.setColumns(20);
        COMENTARIO.setRows(5);
        jScrollPane1.setViewportView(COMENTARIO);

        FECHAIN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("ESTANDAR");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("REVISION");

        REVISION.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        REVISION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REVISIONActionPerformed(evt);
            }
        });

        ESTANDAR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ESTANDAR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR...", "FUJI", "INGENIERIA", "MIL-PRF-27", "MIL-STD-981", "MIL-STD-981 PRE-CAP", "MIL-STD-981 URGENTE", "MIL-STD-981 X-RAY" }));

        URGENTE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        URGENTE.setForeground(new java.awt.Color(255, 0, 0));
        URGENTE.setText("URGENTE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(URGENTE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(REVISION)
                        .addComponent(jLabel9)
                        .addComponent(jLabel12)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(FECHAIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ESTANDAR, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(REVISION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ESTANDAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHAIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(URGENTE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GUARDAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save2.png"))); // NOI18N
        GUARDAR.setText("GUARDAR TRABAJO");
        GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARActionPerformed(evt);
            }
        });

        NO.setEditable(false);
        NO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NO.setForeground(new java.awt.Color(255, 0, 0));
        NO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        NO.setFocusable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("ID");

        NUEVO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NUEVO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        NUEVO.setText("NUEVO TRABAJO");
        NUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUEVOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NO, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NUEVO, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NUEVO, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QTYCLIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTYCLIENTEActionPerformed
        QTYPRODUCCION.requestFocus();
    }//GEN-LAST:event_QTYCLIENTEActionPerformed

    private void PNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNActionPerformed
        JOB.requestFocus();
    }//GEN-LAST:event_PNActionPerformed

    private void JOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JOBActionPerformed
        CLIENTE.requestFocus();
    }//GEN-LAST:event_JOBActionPerformed

    private void CLIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLIENTEActionPerformed
        QTYCLIENTE.requestFocus();
    }//GEN-LAST:event_CLIENTEActionPerformed

    private void QTYPRODUCCIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTYPRODUCCIONActionPerformed
        QTYDELOTES.requestFocus();
    }//GEN-LAST:event_QTYPRODUCCIONActionPerformed

    private void REVISIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REVISIONActionPerformed
        ESTANDAR.requestFocus();
    }//GEN-LAST:event_REVISIONActionPerformed

    private void QTYDELOTESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTYDELOTESActionPerformed
       
        if(QTYDELOTES.getText().compareTo("")!=0){REVISION.requestFocus();lote();}
        else {JOptionPane.showMessageDialog(null, "INGRSE LA CANTIDAD DE LOTES...");}
    }//GEN-LAST:event_QTYDELOTESActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
       ingresoTrabajo();
    }//GEN-LAST:event_GUARDARActionPerformed

    private void QTYPORLOTEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QTYPORLOTEMouseClicked
            lote();
    }//GEN-LAST:event_QTYPORLOTEMouseClicked

    private void QTYPORLOTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTYPORLOTEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QTYPORLOTEActionPerformed

    private void NUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUEVOActionPerformed
       
        EjemplosAprobadosParaTrabajos tra = new EjemplosAprobadosParaTrabajos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        try {
               this.dispose();
            } catch (Exception e) {System.out.println("F "+e);
            }
        
    }//GEN-LAST:event_NUEVOActionPerformed

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
            java.util.logging.Logger.getLogger(NuevoTrabajoTrans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoTrabajoTrans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoTrabajoTrans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoTrabajoTrans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoTrabajoTrans(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CLIENTE;
    private javax.swing.JTextArea COMENTARIO;
    private javax.swing.JComboBox<String> ESTANDAR;
    private com.toedter.calendar.JDateChooser FECHAIN;
    private javax.swing.JButton GUARDAR;
    private javax.swing.JTextField JOB;
    private javax.swing.JTextField NO;
    private javax.swing.JButton NUEVO;
    private javax.swing.JTextField PN;
    private javax.swing.JTextField QTYCLIENTE;
    private javax.swing.JTextField QTYDELOTES;
    private javax.swing.JTextField QTYPORLOTE;
    private javax.swing.JTextField QTYPRODUCCION;
    private javax.swing.JTextField REVISION;
    private javax.swing.JCheckBox URGENTE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
