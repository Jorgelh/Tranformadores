/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import Formularios.*;
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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;

/**
 *
 * @author jluis
 */
public class PrioridadProduccionEjemplos extends javax.swing.JInternalFrame {
     int estandar;
     int cantidadporlote;
     int n;
     int id;
    /**
     * Creates new form Trabajos
     */
    
    int n1 = 0;
    public PrioridadProduccionEjemplos(){//(int i) {
        //this.id = i;//id de ejemplo para buscar informacion y complementar el trabajo 
         try {
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
             
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        initComponents();
        //obtenerID();
        //llenacuainformacion();
        ListarTrabajos();
        
    }
    
    private void llenainformacionEdit() {
       
        id = (Integer.parseInt(String.valueOf(lotes.getModel().getValueAt(lotes.getSelectedRow(), 0))));
        try {
            ClassTrabajos c = new ClassTrabajos();
            c = InsertarEjemplos.buscarTrabajoEditarEjemplo(id);
            NO.setText(String.valueOf(c.getId()));
            PN.setText(c.getPN());
            JOB.setText(c.getJob());
            CLIENTE.setText(c.getCliente());
            QTYCLIENTE.setText(String.valueOf(c.getQtycliente()));
            QTYPRODUCCION.setText(String.valueOf(c.getQtyproduccion()));
            REVISION.setText(c.getRevision());
            ESTANDAR.setSelectedItem(c.getEstandar());
            NOLOTE.setText(String.valueOf(c.getNOLOTE()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            Date datein = sdf.parse(c.getFecha());
            FECHAIN.setDate(datein);
            int prio = c.getPrioridad();
            if(prio==1){URGENTE.setSelected(true);}else{URGENTE.setSelected(false);}
            COMENTARIO.setText(c.getComentarios());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e);
        }
    }
    
    private void ListarTrabajos(){
        ArrayList<ClassTrabajos> result = InsertarEjemplos.ListarTrabajosPrioridadEjemplo(PN1.getText(),JOB1.getText());
        RecargarTabla(result);  
    }
     private void RecargarTabla(ArrayList<ClassTrabajos> list) {
         
              Object[][] datos = new Object[list.size()][7];
              int i = 0;
              for(ClassTrabajos t : list)
              {
                  datos[i][0] = t.getId();
                  datos[i][1] = t.getPN();
                  datos[i][2] = t.getJob();
                  datos[i][3] = t.getCliente();
                  datos[i][4] = t.getEstandar();
                  datos[i][5] = t.getRevision();
                  datos[i][6] = t.getQTYPORLOTE();
                  i++;
              }    
             lotes.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                " ","P/N","JOB","CLIENTE","ESTANDAR","REVISION","CANTIDAD"
 
             })
             {  
                 @Override
             public boolean isCellEditable(int row, int column){
                 return false;
             }
             });
             TableColumn columna1 = lotes.getColumn(" ");
             columna1.setPreferredWidth(0);
             TableColumn columna3 = lotes.getColumn("P/N");
             columna3.setPreferredWidth(40);
             TableColumn columna4 = lotes.getColumn("JOB");
             columna4.setPreferredWidth(40);
             TableColumn columna5 = lotes.getColumn("CLIENTE");
             columna5.setPreferredWidth(40);
              TableColumn columna6 = lotes.getColumn("REVISION");
             columna6.setPreferredWidth(40);
             TableColumn columna7 = lotes.getColumn("ESTANDAR");
             columna7.setPreferredWidth(80);
             TableColumn columna8 = lotes.getColumn("CANTIDAD");
             columna8.setPreferredWidth(40);
             
             
}
  
    /* public void obtenerID()
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
            JOptionPane.showMessageDialog(null,"Error shit");
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
    }*/
    
  
    
    
    
    public void EditarTrabajo(){
    if(NO.getText().compareTo("")!=0) {
        try {
            ClassTrabajos t = new ClassTrabajos();
            t.setId(Integer.parseInt(NO.getText()));
            if (URGENTE.isSelected()) {t.setPrioridad(1);}else{t.setPrioridad(0);}
            InsertarEjemplos.EditarNodeEjemplo(t);
            JOptionPane.showMessageDialog(null, "LOTE ACTUALIZADO...");
            limpiar();
            ListarTrabajos();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "error"+e);
        }
        } else {JOptionPane.showMessageDialog(null,"NO SE PUEDE GUARDAR SIN CANTIDAD");}
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
        FECHAIN.setDate(null);
        COMENTARIO.setText("");
        PN.requestFocus();
        NO.setText("");
        NOLOTE.setText("");
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
        FECHAIN.setEnabled(false);
        COMENTARIO.setEnabled(false);
        GUARDAR.setEnabled(false);
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
        jLabel14 = new javax.swing.JLabel();
        NOLOTE = new javax.swing.JTextField();
        GUARDAR = new javax.swing.JButton();
        NO = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PN1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JOB1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        lotes = new javax.swing.JTable();

        setClosable(true);
        setTitle("EDITAR INFORMACION DE LOTE");
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("P/N");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("JOB");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CLIENTE");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CANTIDAD PRODUCCION");

        PN.setEditable(false);
        PN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNActionPerformed(evt);
            }
        });

        JOB.setEditable(false);
        JOB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOBActionPerformed(evt);
            }
        });

        CLIENTE.setEditable(false);
        CLIENTE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CLIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLIENTEActionPerformed(evt);
            }
        });

        QTYPRODUCCION.setEditable(false);
        QTYPRODUCCION.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QTYPRODUCCION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYPRODUCCIONActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CANTIDAD CLIENTE");

        QTYCLIENTE.setEditable(false);
        QTYCLIENTE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QTYCLIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYCLIENTEActionPerformed(evt);
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
                    .addComponent(QTYCLIENTE)
                    .addComponent(PN)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
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
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("FECHA INGRESO");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("COMENTARIO");

        COMENTARIO.setBackground(new java.awt.Color(255, 255, 153));
        COMENTARIO.setColumns(20);
        COMENTARIO.setRows(5);
        jScrollPane1.setViewportView(COMENTARIO);

        FECHAIN.setEnabled(false);
        FECHAIN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("ESTANDAR");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("REVISION");

        REVISION.setEditable(false);
        REVISION.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        REVISION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REVISIONActionPerformed(evt);
            }
        });

        ESTANDAR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ESTANDAR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR...", "FUJI", "INGENIERIA", "MIL-PRF-27", "MIL-STD-981", "MIL-STD-981 PRE-CAP", "MIL-STD-981 URGENTE", "MIL-STD-981 X-RAY" }));
        ESTANDAR.setEnabled(false);

        URGENTE.setBackground(new java.awt.Color(255, 255, 153));
        URGENTE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        URGENTE.setForeground(new java.awt.Color(255, 0, 0));
        URGENTE.setText("URGENTE");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("NO LOTE");

        NOLOTE.setEditable(false);
        NOLOTE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NOLOTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOLOTEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(URGENTE)
                                    .addComponent(jLabel12)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(FECHAIN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(NOLOTE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(REVISION)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(ESTANDAR, 0, 212, Short.MAX_VALUE))
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NOLOTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHAIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(URGENTE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        GUARDAR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save2.png"))); // NOI18N
        GUARDAR.setText("GUARDAR");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NO, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GUARDAR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PN1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PN1ActionPerformed(evt);
            }
        });
        PN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PN1KeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("P/N");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("JOB");

        JOB1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JOB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOB1ActionPerformed(evt);
            }
        });
        JOB1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JOB1KeyReleased(evt);
            }
        });

        lotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "P/N", "JOB", "CLIENTE", "ESTANDAR", "NO DE LOTE", "REV"
            }
        ));
        lotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lotesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lotes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JOB1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(JOB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3)))
                .addGap(0, 16, Short.MAX_VALUE))
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
    }//GEN-LAST:event_QTYPRODUCCIONActionPerformed

    private void REVISIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REVISIONActionPerformed
        ESTANDAR.requestFocus();
    }//GEN-LAST:event_REVISIONActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
       EditarTrabajo();
    }//GEN-LAST:event_GUARDARActionPerformed

    private void PN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PN1ActionPerformed
        JOB.requestFocus();
        
    }//GEN-LAST:event_PN1ActionPerformed

    private void PN1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PN1KeyReleased
        ListarTrabajos();
    }//GEN-LAST:event_PN1KeyReleased

    private void JOB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JOB1ActionPerformed
        ListarTrabajos();
    }//GEN-LAST:event_JOB1ActionPerformed

    private void JOB1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JOB1KeyReleased
        ListarTrabajos();
    }//GEN-LAST:event_JOB1KeyReleased

    private void NOLOTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NOLOTEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NOLOTEActionPerformed

    private void lotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lotesMouseClicked
               
       llenainformacionEdit();
       
    }//GEN-LAST:event_lotesMouseClicked

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
            java.util.logging.Logger.getLogger(PrioridadProduccionEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrioridadProduccionEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrioridadProduccionEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrioridadProduccionEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PrioridadProduccionEjemplos().setVisible(true);
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
    private javax.swing.JTextField JOB1;
    private javax.swing.JTextField NO;
    private javax.swing.JTextField NOLOTE;
    private javax.swing.JTextField PN;
    private javax.swing.JTextField PN1;
    private javax.swing.JTextField QTYCLIENTE;
    private javax.swing.JTextField QTYPRODUCCION;
    private javax.swing.JTextField REVISION;
    private javax.swing.JCheckBox URGENTE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable lotes;
    // End of variables declaration//GEN-END:variables
}
