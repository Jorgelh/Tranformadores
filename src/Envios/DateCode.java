/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Envios;

import BD.BD;
import ClassEnvios.BDEnvios;
import ClassEnvios.ClassEnv;
import Formularios.Inicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author jluis
 */
public class DateCode extends javax.swing.JInternalFrame {
    /** Creates new form EditarEnvio */
    public DateCode() {
        initComponents();
        ListarEnvios();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    private void ListarEnvios() {
        ArrayList<ClassEnv> result = BDEnvios.ListarEnvios();
        recargarTable(result);
    }
    
    public void recargarTable(ArrayList<ClassEnv> list) {
        Object[][] datos = new Object[list.size()][2];
        int i = 0;
        for (ClassEnv m : list)
        {
            datos[i][0] = m.getNo_envio();
            datos[i][1] = m.getFecha();
            i++;
        }
        envios.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "No.","FECHA"
                }) {
                     @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        TableColumn columna1 = envios.getColumn("No.");
             columna1.setPreferredWidth(0);
             TableColumn columna2 = envios.getColumn("FECHA");
             columna2.setPreferredWidth(140);
        
    }
    

      
      
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
      
      private void ListarTrabajosdeEnvio(){
        ArrayList<ClassEnv> result = BDEnvios.ListarEnviosData(Integer.parseInt(NO.getText()));
        TablaEnvi(result);  
    }
     private void TablaEnvi(ArrayList<ClassEnv> list) {
         
              Object[][] datos = new Object[list.size()][8];
              int i = 0;
              for(ClassEnv t : list)
              {
                  datos[i][0] = t.getNo_envio();
                  datos[i][1] = t.getPN();
                  datos[i][2] = t.getJob();
                  datos[i][3] = t.getLoteS();
                  datos[i][4] = t.getCantidad();
                  datos[i][5] = t.getProcesosActual();
                  datos[i][6] = t.getDatecode();
                  datos[i][7] = t.getFechadata();
                  i++;
              }    
             ENVIO.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "No.","P/N","JOB","LOTE","CANTIDAD","PROCESO ACTUAL","DATE CODE","FECHA PARA DATA"
             }
             
             )
             {  
                 @Override
             public boolean isCellEditable(int row, int column){
                 return false;
             }
             });
             
             TableColumn columna1 = ENVIO.getColumn("No.");
             columna1.setPreferredWidth(0);
             TableColumn columna2 = ENVIO.getColumn("P/N");
             columna2.setPreferredWidth(50);
             TableColumn columna3 = ENVIO.getColumn("JOB");
             columna3.setPreferredWidth(50);
             TableColumn columna4 = ENVIO.getColumn("LOTE");
             columna4.setPreferredWidth(50);
             TableColumn columna6 = ENVIO.getColumn("CANTIDAD");
             columna6.setPreferredWidth(50);
             TableColumn columna7 = ENVIO.getColumn("PROCESO ACTUAL");
             columna7.setPreferredWidth(150);
             TableColumn columna8 = ENVIO.getColumn("DATE CODE");
             columna8.setPreferredWidth(50);
             TableColumn columna9 = ENVIO.getColumn("FECHA PARA DATA");
             columna9.setPreferredWidth(200);
             
}    
      
     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     
     
     public void buscarEnvios(){
     
     try {
            ClassEnv E = BDEnvios.BuscarDateCode(Integer.parseInt(String.valueOf(ENVIO.getModel().getValueAt(ENVIO.getSelectedRow(),0))));
            if(E.getDatecode()==0){}else{
            datecode.setText(String.valueOf(E.getDatecode()));}
            if(E.getFechadata()==null){}else{
            fechadata.setText(String.valueOf(E.getFechadata()));}
            
        } catch (Exception e) {
            System.out.println("Erro de Seleccion:"+e);
        }     
     }
     
     
     
     
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
     
    private void actualizardatecode() {

        int idlote = (Integer.parseInt(String.valueOf(ENVIO.getModel().getValueAt(ENVIO.getSelectedRow(), 0))));

        if (datecode.getText().compareTo("") != 0) {

            try {
                Connection cnn = BD.getConnection();
                PreparedStatement ps = null;
                ps = cnn.prepareStatement("update lotesenvios set datecode = " + datecode.getText() + ",fechadata = '" + fechadata.getText() + "'  where id_lote =" + idlote);
                ps.executeUpdate();
                cnn.close();
                ps.close();
                JOptionPane.showMessageDialog(null, "Guardado...");
                ListarTrabajosdeEnvio();
            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                Connection cnn = BD.getConnection();
                PreparedStatement ps = null;
                ps = cnn.prepareStatement("update lotesenvios set datecode = null ,fechadata = '" + fechadata.getText() + "'  where id_lote =" + idlote);
                ps.executeUpdate();
                cnn.close();
                ps.close();
                JOptionPane.showMessageDialog(null, "Guardado...");
                ListarTrabajosdeEnvio();
            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     
     private void limpiar(){
     PN.setText("");
     LOTE.setText("");
     JOB.setText("");
     datecode.setText("");
     fechadata.setText("");
     }    
     
     
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        envios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ENVIO = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NO = new javax.swing.JTextField();
        FECHA = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        PN = new javax.swing.JTextField();
        JOB = new javax.swing.JTextField();
        datecode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fechadata = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        LOTE = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("DATE CODE -- FECHA PARA DATA");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        envios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        envios.setForeground(new java.awt.Color(255, 51, 51));
        envios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO.", "FECHA"
            }
        ));
        envios.setAlignmentX(3.5F);
        envios.setAlignmentY(1.5F);
        envios.setIntercellSpacing(new java.awt.Dimension(15, 1));
        envios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enviosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(envios);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ENVIOS");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        ENVIO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ENVIO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "P/N", "JOB", "LOTE", "CANTIDAD", "PROCESO ACTUAL", "DATE CODE"
            }
        ));
        ENVIO.setAlignmentX(3.5F);
        ENVIO.setAlignmentY(1.5F);
        ENVIO.setRowHeight(25);
        ENVIO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ENVIOMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ENVIO);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("TRABAJOS EN EL ENVIO NO ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("FECHA");

        NO.setEditable(false);
        NO.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NO.setForeground(new java.awt.Color(51, 51, 255));

        FECHA.setEditable(false);
        FECHA.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        FECHA.setForeground(new java.awt.Color(51, 51, 255));

        PN.setEditable(false);
        PN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PN.setForeground(new java.awt.Color(255, 51, 51));

        JOB.setEditable(false);
        JOB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JOB.setForeground(new java.awt.Color(255, 51, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("P/N");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("JOB");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("DATE CODE");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("FECHA PARA DATA ");

        fechadata.setColumns(20);
        fechadata.setRows(5);
        jScrollPane2.setViewportView(fechadata);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("LOTE");

        LOTE.setEditable(false);
        LOTE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LOTE.setForeground(new java.awt.Color(255, 51, 51));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save2.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(227, 227, 227)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LOTE, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(datecode, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71)
                                        .addComponent(jButton1)))))
                        .addGap(0, 169, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(LOTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datecode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NO, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(NO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1)))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviosMouseClicked
       NO.setText((String.valueOf(envios.getModel().getValueAt(envios.getSelectedRow(), 0))));
       FECHA.setText((String.valueOf(envios.getModel().getValueAt(envios.getSelectedRow(), 1))));
       ListarTrabajosdeEnvio();
    }//GEN-LAST:event_enviosMouseClicked

    private void ENVIOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ENVIOMouseClicked
       PN.setText((String.valueOf(ENVIO.getModel().getValueAt(ENVIO.getSelectedRow(), 1))));
       JOB.setText((String.valueOf(ENVIO.getModel().getValueAt(ENVIO.getSelectedRow(), 2))));
       LOTE.setText((String.valueOf(ENVIO.getModel().getValueAt(ENVIO.getSelectedRow(), 3))));
       datecode.setText("");
       fechadata.setText("");
       buscarEnvios();
    }//GEN-LAST:event_ENVIOMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       int cuentaFilasSeleccionadas = ENVIO.getSelectedRowCount(); 
        if(cuentaFilasSeleccionadas>0){    
       actualizardatecode();
       limpiar();
        }else{JOptionPane.showMessageDialog(null, "SELECCIONE UN LOTE...");}
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ENVIO;
    private javax.swing.JTextField FECHA;
    private javax.swing.JTextField JOB;
    private javax.swing.JTextField LOTE;
    private javax.swing.JTextField NO;
    private javax.swing.JTextField PN;
    private javax.swing.JTextField datecode;
    private javax.swing.JTable envios;
    private javax.swing.JTextArea fechadata;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
