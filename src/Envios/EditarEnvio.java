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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author jluis
 */
public class EditarEnvio extends javax.swing.JInternalFrame {
    /** Creates new form EditarEnvio */
    public EditarEnvio() {
        initComponents();
        ListarEnvios();
        ListarTrabajosTrans();
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
    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    
private void ListarTrabajosTrans(){
        ArrayList<ClassEnv> result = BDEnvios.ListarTrabajosEnvios(PNINF.getText());
        RecargarTabla(result);  
    }
     private void RecargarTabla(ArrayList<ClassEnv> list) {
         
              Object[][] datos = new Object[list.size()][6];
              int i = 0;
              for(ClassEnv t : list)
              {
                  datos[i][0] = t.getNo_envio();
                  datos[i][1] = t.getPN();
                  datos[i][2] = t.getJob();
                  datos[i][3] = t.getLote();
                  datos[i][4] = t.getCantidad();
                  datos[i][5] = t.getPrioridad();
                  i++;
              }    
             TRABAJOS.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "No.","P/N","JOB","LOTE","CANTIDAD","PRIORIDAD"
             })
             {  
                 @Override
             public boolean isCellEditable(int row, int column){
                 return false;
             }
             });
             
             TableColumn columna1 = TRABAJOS.getColumn("No.");
             columna1.setPreferredWidth(0);
             TableColumn columna2 = TRABAJOS.getColumn("P/N");
             columna2.setPreferredWidth(50);
             TableColumn columna3 = TRABAJOS.getColumn("JOB");
             columna3.setPreferredWidth(50);
             TableColumn columna4 = TRABAJOS.getColumn("LOTE");
             columna4.setPreferredWidth(50);
             TableColumn columna6 = TRABAJOS.getColumn("CANTIDAD");
             columna6.setPreferredWidth(50);
             TableColumn columna7 = TRABAJOS.getColumn("PRIORIDAD");
             columna7.setPreferredWidth(150);
             
}    
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
     
     public void insertarLoteaEnvio() {
        
        try {
            int idlote = (Integer.parseInt(String.valueOf(TRABAJOS.getModel().getValueAt(TRABAJOS.getSelectedRow(), 0))));
            int cantidad = (Integer.parseInt(String.valueOf(TRABAJOS.getModel().getValueAt(TRABAJOS.getSelectedRow(), 4))));
            ClassEnv m = new ClassEnv();
            m.setIdlote(idlote);
            m.setNoenvio(Integer.parseInt(NO.getText()));
            m.setCantidad(cantidad);
            BDEnvios.insertarLoteEnvio(m);
            actualizarLote();
        } catch (SQLException e) {
            System.out.println("Error BD:" + e);
        }
    }
     
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

     
      private void actualizarLote(){
     int idlote = (Integer.parseInt(String.valueOf(TRABAJOS.getModel().getValueAt(TRABAJOS.getSelectedRow(), 0))));
       try {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("update lotes set envio = 1  where id_lote ="+idlote);
    ps.executeUpdate();
    cnn.close();
    ps.close();
      } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }   
          
    }
     
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
      
      private void actualizarLote2(){
     int idlote = (Integer.parseInt(String.valueOf(ENVIO.getModel().getValueAt(ENVIO.getSelectedRow(), 0))));
       try {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("update lotes set envio = 0  where id_lote ="+idlote);
    ps.executeUpdate();
    cnn.close();
    ps.close();
      } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }   
          
    }
      
      
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
      
      private void ListarTrabajosdeEnvio(){
        ArrayList<ClassEnv> result = BDEnvios.ListarEnvios(Integer.parseInt(NO.getText()));
        TablaEnvi(result);  
    }
     private void TablaEnvi(ArrayList<ClassEnv> list) {
         
              Object[][] datos = new Object[list.size()][6];
              int i = 0;
              for(ClassEnv t : list)
              {
                  datos[i][0] = t.getNo_envio();
                  datos[i][1] = t.getPN();
                  datos[i][2] = t.getJob();
                  datos[i][3] = t.getLote();
                  datos[i][4] = t.getCantidad();
                  datos[i][5] = t.getProcesosActual();
                  i++;
              }    
             ENVIO.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "No.","P/N","JOB","LOTE","CANTIDAD","PROCESO ACTUAL"
             })
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
             
}    
      
      
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
     
     private void EliminarLote(){
     int idlote = (Integer.parseInt(String.valueOf(ENVIO.getModel().getValueAt(ENVIO.getSelectedRow(), 0))));
       try {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("delete from lotesenvios where id_lote ="+idlote);
    ps.executeUpdate();
    cnn.close();
    ps.close();
    actualizarLote2();
      } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }   
          
    }
     
     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
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
        jScrollPane2 = new javax.swing.JScrollPane();
        TRABAJOS = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PNINF = new javax.swing.JTextField();
        JOBINF = new javax.swing.JTextField();

        setClosable(true);

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
                "NO", "P/N", "JOB", "LOTE", "CANTIDAD", "PROCESO ACTUAL"
            }
        ));
        ENVIO.setAlignmentX(3.5F);
        ENVIO.setAlignmentY(1.5F);
        ENVIO.setIntercellSpacing(new java.awt.Dimension(15, 1));
        ENVIO.setRowHeight(20);
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
        NO.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        NO.setForeground(new java.awt.Color(51, 51, 255));

        FECHA.setEditable(false);
        FECHA.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        FECHA.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NO, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("TRABAJOS"));

        TRABAJOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TRABAJOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "PN", "JOB", "LOTE", "CANTIDAD", "PRIORIDAD"
            }
        ));
        TRABAJOS.setAlignmentX(3.5F);
        TRABAJOS.setAlignmentY(1.5F);
        TRABAJOS.setIntercellSpacing(new java.awt.Dimension(15, 1));
        TRABAJOS.setRowHeight(20);
        TRABAJOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TRABAJOSMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TRABAJOS);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("P/N");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("JOB");

        PNINF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PNINF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PNINFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PNINFKeyTyped(evt);
            }
        });

        JOBINF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PNINF, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JOBINF, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PNINF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(JOBINF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void PNINFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PNINFKeyTyped
      
    }//GEN-LAST:event_PNINFKeyTyped

    private void PNINFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PNINFKeyReleased
            ListarTrabajosTrans();
    }//GEN-LAST:event_PNINFKeyReleased

    private void enviosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviosMouseClicked
       NO.setText((String.valueOf(envios.getModel().getValueAt(envios.getSelectedRow(), 0))));
       FECHA.setText((String.valueOf(envios.getModel().getValueAt(envios.getSelectedRow(), 1))));
       ListarTrabajosdeEnvio();
    }//GEN-LAST:event_enviosMouseClicked

    private void TRABAJOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TRABAJOSMouseClicked
        if (evt.getClickCount() > 1) {
            if(NO.getText().compareTo("")!=0 && FECHA.getText().compareTo("")!=0)
            {
            insertarLoteaEnvio();
            
            ListarTrabajosTrans();
            ListarTrabajosdeEnvio();
            }else{JOptionPane.showMessageDialog(null, "SELECCIONE UNA FECHA DE ENVIO");}
        }
    }//GEN-LAST:event_TRABAJOSMouseClicked

    private void ENVIOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ENVIOMouseClicked
       if (evt.getClickCount() > 1) {
            EliminarLote();
            
            ListarTrabajosTrans();
            ListarTrabajosdeEnvio();
        }
    }//GEN-LAST:event_ENVIOMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ENVIO;
    private javax.swing.JTextField FECHA;
    private javax.swing.JTextField JOBINF;
    private javax.swing.JTextField NO;
    private javax.swing.JTextField PNINF;
    private javax.swing.JTable TRABAJOS;
    private javax.swing.JTable envios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

}
