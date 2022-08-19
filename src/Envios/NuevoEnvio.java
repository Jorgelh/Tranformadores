/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Envios;

import BD.BD;
import ClassEnvios.BDEnvios;
import ClassEnvios.ClassEnv;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author jluis
 */
public class NuevoEnvio extends javax.swing.JInternalFrame {
int busca;
String accion = "";
    /** Creates new form NuevoEnvio */
    public NuevoEnvio() {
        initComponents();
        obtenerUltimoId();
        activarBotones(true);
        activarCajaTexto(false);
        ListarEnvios();
        GUARDAR.setEnabled(false);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public void obtenerUltimoId() {
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(no_envio) from envios");
            while (rs.next()) {
                int lastID = rs.getInt(1);
                no.setText(String.valueOf(lastID + 1));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////    
     public void limpiarCajaTexto() {
        no.setText("");
        fecha.setDate(null);
        busca=0;
    }
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////   
     
    public void activarBotones(boolean b){
        NUEVO.setEnabled(b);
        GUARDAR.setEnabled(!b);
        EDITAR.setEnabled(!b);
        CANCELAR.setEnabled(!b);
        cerrar.setEnabled(!b);
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void activarCajaTexto(boolean b) {
        no.setEnabled(b);
        fecha.setEnabled(b);
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
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
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

     public void buscarEnvios(){
     
     try {
            ClassEnv med = BDEnvios.BuscarEnvio(Integer.parseInt(String.valueOf(envios.getModel().getValueAt(envios.getSelectedRow(),0))));
            
            no.setText(String.valueOf(med.getNo_envio()));
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date datein = sdf.parse(med.getFecha());
            fecha.setDate(datein);
            
        } catch (Exception e) {
            System.out.println("Erro de Seleccion:"+e);
        }     
     }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
     
 public void guardaractualizar()    
 {
 
 if(accion.equalsIgnoreCase("Guardar")){
       
       if (no.getText().compareTo("")!=0 && fecha.getDate()!=null ){
           System.out.println("GUARDA");
           try {
              
               ClassEnv m = new ClassEnv();
               m.setNo_envio(Integer.parseInt(no.getText()));
               m.setFechaD(fecha.getDate());
               BDEnvios.InsertarEnvio(m);
               JOptionPane.showMessageDialog(null,"Envio Guardado");
               
           } catch (Exception e) {
               System.out.println("Error BD:"+e.getMessage());
           }
           limpiarCajaTexto();
           obtenerUltimoId();
           activarBotones(true);
           activarCajaTexto(false);
           ListarEnvios();
           envios.setEnabled(true);
           //cerrar.setEnabled(true);
           
       }else {
       JOptionPane.showMessageDialog(null,"Llene Todos Los Campos...");
       }
        }   
 if (accion.equalsIgnoreCase("Actualizar")) {
      System.out.println("actualiza");
            ClassEnv p;
            try {
                p= BDEnvios.BuscarEnvio(Integer.parseInt(no.getText()));
                p.setFechaD(fecha.getDate());              
                BDEnvios.actualizarEnvio(p);
                JOptionPane.showMessageDialog(null, " [ Fecha Actualizada ]");
                limpiarCajaTexto();
                obtenerUltimoId();
                activarBotones(true);
                activarCajaTexto(false);
                ListarEnvios();
                envios.setEnabled(true);
               // cerrar.setEnabled(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error BD: " + e.getMessage());
            }
        }
 
 
 }
     
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
     
     
     public void cerrarenvio(){
     
     ClassEnv p;
            try {
                p= BDEnvios.BuscarEnvio(Integer.parseInt(no.getText()));
                p.setEstado(2);              
                BDEnvios.cerrarEnvio(p);
                JOptionPane.showMessageDialog(null, "--- ENVIO CERRADO ---");
                limpiarCajaTexto();
                obtenerUltimoId();
                activarBotones(true);
                activarCajaTexto(false);
                ListarEnvios();
                envios.setEnabled(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error BD: " + e.getMessage());
            }
     
     }
     
     
     
     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        no = new javax.swing.JTextField();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        NUEVO = new javax.swing.JButton();
        GUARDAR = new javax.swing.JButton();
        EDITAR = new javax.swing.JButton();
        CANCELAR = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        envios = new javax.swing.JTable();
        cerrar = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("NO");

        no.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        fecha.setDateFormatString("MM/dd/yyyy");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("FECHA");

        NUEVO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        NUEVO.setText("NUEVO");
        NUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUEVOActionPerformed(evt);
            }
        });

        GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save2.png"))); // NOI18N
        GUARDAR.setText("GUARDAR");
        GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARActionPerformed(evt);
            }
        });

        EDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit2.png"))); // NOI18N
        EDITAR.setText("EDITAR");
        EDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDITARActionPerformed(evt);
            }
        });

        CANCELAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        CANCELAR.setText("CANCELAR");
        CANCELAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CANCELARMouseClicked(evt);
            }
        });
        CANCELAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELARActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(NUEVO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GUARDAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EDITAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CANCELAR)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NUEVO, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EDITAR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CANCELAR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        envios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        envios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "FECHA"
            }
        ));
        envios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enviosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(envios);

        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Cancel.png"))); // NOI18N
        cerrar.setText("CERRAR ENVIO");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cerrar))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void EDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDITARActionPerformed
      
      EDITAR.setEnabled(false);
      NUEVO.setEnabled(false);
      envios.clearSelection();
      envios.setEnabled(false);
      cerrar.setEnabled(false);
      GUARDAR.setEnabled(true);
      CANCELAR.setEnabled(true);
      fecha.setEnabled(true);
      busca=1;
      accion = "Actualizar";
        
        
    }//GEN-LAST:event_EDITARActionPerformed

    private void enviosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviosMouseClicked
      if(busca==0){
        buscarEnvios();
        EDITAR.setEnabled(true);
        cerrar.setEnabled(true);
      }
    }//GEN-LAST:event_enviosMouseClicked

    private void NUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUEVOActionPerformed
       accion = "Guardar";
        limpiarCajaTexto();
      obtenerUltimoId();
      EDITAR.setEnabled(false);
      NUEVO.setEnabled(false);
      envios.clearSelection();
      envios.setEnabled(false);
      cerrar.setEnabled(false);
      GUARDAR.setEnabled(true);
      CANCELAR.setEnabled(true);
      no.setEnabled(true);
      fecha.setEnabled(true);
      busca=1;
      
      
    }//GEN-LAST:event_NUEVOActionPerformed

    private void CANCELARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELARActionPerformed
      GUARDAR.setEnabled(false);
      CANCELAR.setEnabled(false);
      limpiarCajaTexto();
      obtenerUltimoId();
      NUEVO.setEnabled(true);
      envios.setEnabled(true);
      cerrar.setEnabled(false);
      busca = 0;
      activarCajaTexto(false);
    }//GEN-LAST:event_CANCELARActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
      guardaractualizar();
    }//GEN-LAST:event_GUARDARActionPerformed

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        cerrarenvio();
    }//GEN-LAST:event_cerrarActionPerformed

    private void CANCELARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CANCELARMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CANCELARMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CANCELAR;
    private javax.swing.JButton EDITAR;
    private javax.swing.JButton GUARDAR;
    private javax.swing.JButton NUEVO;
    private javax.swing.JButton cerrar;
    private javax.swing.JTable envios;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField no;
    // End of variables declaration//GEN-END:variables

}
