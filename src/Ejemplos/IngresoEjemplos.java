/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import Formularios.*;
import Class.ClassTrabajos;
import BD.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jluis
 */
public class IngresoEjemplos extends javax.swing.JInternalFrame {
     int estandar;
    /**
     * Creates new form Trabajos
     */
    
    int n1 = 0;
    public IngresoEjemplos() {
         try {
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
             
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        initComponents();
        PN.requestFocus();
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
    
    public void ingresoTrabajo(){
    if(PN.getText().compareTo("")!=0 && 
       JOB.getText().compareTo("")!=0 && 
       CLIENTE.getText().compareTo("")!=0 && 
       QTYEJEMPLO.getText().compareTo("")!=0 && 
       !ESTANDAR.getSelectedItem().toString().equalsIgnoreCase("SELECCIONAR...")&& 
       REVISION.getText().compareTo("")!=0 &&
       FECHAIN.getDate()!=null){
        //for(int i=0; Integer.parseInt(QTYDELOTES.getText())>i;i++){
        estandar();
        try {
            ClassTrabajos t = new ClassTrabajos();
            //t.setId(Integer.parseInt(Id.getText()));
            t.setPN(PN.getText().toUpperCase());
            t.setJob(JOB.getText().toUpperCase());
            t.setCliente(CLIENTE.getText().toUpperCase());
            t.setQtycliente(Integer.parseInt(QTYCLIENTE.getText()));
            t.setQtyproduccion(Integer.parseInt(QTYEJEMPLO.getText()));
            t.setEstandarint(estandar);
            t.setRevision(REVISION.getText().toUpperCase());
            t.setFechain(FECHAIN.getDate());
             if (URGENTE.isSelected()) {t.setPrioridad(1);}else{t.setPrioridad(0);}
            //t.setComentarios(COMENTARIO.getText());
            InsertarEjemplos.InsertarEjemplos(t);       
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "error"+e);
        }
        }
            JOptionPane.showMessageDialog(null, "Trabajo Guardado");
            limpiar();
   // } else {JOptionPane.showMessageDialog(null,"Ingrese Todos los Datos");}
    }
    
    
    
    /*public void ingresoTrabajoSinCantidad(){
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
    }
    */
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
        QTYEJEMPLO.setText("");
        ESTANDAR.setSelectedItem("SELECCIONAR...");
        REVISION.setText("");
        FECHAIN.setDate(null);
        //COMENTARIO.setText("");
        PN.requestFocus();
        QTYCLIENTE.setText("");
        estandar = 0;
    
    }
   /* public void lote(){
    
        int a = Integer.parseInt(QTYPRODUCCION.getText())/Integer.parseInt(QTYDELOTES.getText());
         QTYPORLOTE.setText(String.valueOf(a));
    }*/
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
        PN = new javax.swing.JTextField();
        JOB = new javax.swing.JTextField();
        CLIENTE = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        QTYEJEMPLO = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        REVISION = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        FECHAIN = new com.toedter.calendar.JDateChooser();
        QTYCLIENTE = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ESTANDAR = new javax.swing.JComboBox<>();
        URGENTE = new javax.swing.JCheckBox();
        GUARDAR = new javax.swing.JButton();

        setClosable(true);
        setTitle("NUEVO EJEMPLO");
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("P/N");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("JOB");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CLIENTE");

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CANTIDAD");

        QTYEJEMPLO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QTYEJEMPLO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYEJEMPLOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CLIENTE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JOB, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(QTYEJEMPLO)
                    .addComponent(PN)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 134, Short.MAX_VALUE)))
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
                .addComponent(QTYEJEMPLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

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

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("FECHA INGRESO");

        FECHAIN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        QTYCLIENTE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QTYCLIENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYCLIENTEActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("CANTIDAD ORDEN");

        ESTANDAR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ESTANDAR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR...", "FUJI", "INGENIERIA", "MIL-PRF-27", "MIL-STD-981", "MIL-STD-981 PRE-CAP", "MIL-STD-981 URGENTE", "MIL-STD-981 X-RAY" }));
        ESTANDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ESTANDARActionPerformed(evt);
            }
        });

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
                    .addComponent(REVISION)
                    .addComponent(FECHAIN, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(QTYCLIENTE)
                    .addComponent(ESTANDAR, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(URGENTE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ESTANDAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(REVISION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QTYCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHAIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(URGENTE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QTYEJEMPLOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTYEJEMPLOActionPerformed
       ESTANDAR.requestFocus();
    }//GEN-LAST:event_QTYEJEMPLOActionPerformed

    private void PNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNActionPerformed
        JOB.requestFocus();
    }//GEN-LAST:event_PNActionPerformed

    private void JOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JOBActionPerformed
        CLIENTE.requestFocus();
    }//GEN-LAST:event_JOBActionPerformed

    private void CLIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLIENTEActionPerformed
        QTYEJEMPLO.requestFocus();
    }//GEN-LAST:event_CLIENTEActionPerformed

    private void REVISIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REVISIONActionPerformed
        QTYCLIENTE.requestFocus();
    }//GEN-LAST:event_REVISIONActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
       ingresoTrabajo();
    }//GEN-LAST:event_GUARDARActionPerformed

    private void QTYCLIENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTYCLIENTEActionPerformed
        FECHAIN.requestFocus();
    }//GEN-LAST:event_QTYCLIENTEActionPerformed

    private void ESTANDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ESTANDARActionPerformed
        REVISION.requestFocus();
    }//GEN-LAST:event_ESTANDARActionPerformed

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
            java.util.logging.Logger.getLogger(IngresoEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoEjemplos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CLIENTE;
    private javax.swing.JComboBox<String> ESTANDAR;
    private com.toedter.calendar.JDateChooser FECHAIN;
    private javax.swing.JButton GUARDAR;
    private javax.swing.JTextField JOB;
    private javax.swing.JTextField PN;
    private javax.swing.JTextField QTYCLIENTE;
    private javax.swing.JTextField QTYEJEMPLO;
    private javax.swing.JTextField REVISION;
    private javax.swing.JCheckBox URGENTE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
