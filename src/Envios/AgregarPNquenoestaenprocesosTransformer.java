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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

/**
 *
 * @author jluis
 */
public class AgregarPNquenoestaenprocesosTransformer extends javax.swing.JInternalFrame {
     int NO;
     int id;
     int id_lote;
     String accion = "";
    /**
     * Creates new form AgregarPNquenoestaenprocesos
     */
    public AgregarPNquenoestaenprocesosTransformer() {
        initComponents();
        ListarEnvios();
        CONTROL.setDefaultRenderer (Object.class, new FormatoTablaEnvios());
    }
    
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
        ENVIOS.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "No.","FECHA"
                }) {
                     @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        TableColumn columna1 = ENVIOS.getColumn("No.");
             columna1.setPreferredWidth(0);
             TableColumn columna2 = ENVIOS.getColumn("FECHA");
             columna2.setPreferredWidth(85);
    }
    
    
    private void ListarTrabajosdeEnvio(){
       
        ArrayList<ClassEnv> result = BDEnvios.ListadoParaagregarPN(NO);
        TablaEnvi(result);
    }

    
     private void TablaEnvi(ArrayList<ClassEnv> list) {
              Object[][] datos = new Object[list.size()][7];
              int i = 0;
              for(ClassEnv t : list)
              {
                  datos[i][0] = t.getIdlote();
                  datos[i][1] = t.getJob();
                  datos[i][2] = t.getPN();
                  datos[i][3] = t.getLoteS();
                  datos[i][4] = t.getProcesosActual();
                  datos[i][5] = t.getCantidad();
                  datos[i][6] = t.getFecha();
                  i++;
              }    
             CONTROL.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "ID","JOB","P/N","LOTE","PROCESO ACTUAL","QTY","FECHA"
             })
             {  
                 @Override
             public boolean isCellEditable(int row, int column){
                 return false;
             }
             });
             
             TableColumn columna1 = CONTROL.getColumn("JOB");
             columna1.setPreferredWidth(0);
             TableColumn columna2 = CONTROL.getColumn("P/N");
             columna2.setPreferredWidth(0);
             TableColumn columna3 = CONTROL.getColumn("LOTE");
             columna3.setPreferredWidth(0);
             TableColumn columna4 = CONTROL.getColumn("PROCESO ACTUAL");
             columna4.setPreferredWidth(200);
             TableColumn columna6 = CONTROL.getColumn("QTY");
             columna6.setPreferredWidth(0);
             TableColumn columna7 = CONTROL.getColumn("FECHA");
             columna7.setPreferredWidth(100);
             TableColumn columna8 = CONTROL.getColumn("ID");
             columna8.setPreferredWidth(0);
             
} 
     
     
     public void limpiar(){
     
         JOB.setText("");
         PN.setText("");
         LOTE.setText("");
         PROCESO.setText("");
         QTY.setText("");
     
     }
     
     public void obtenerUltimoId() {
        try {
            Connection con = BD.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(nvl(id_lote,0)) from ENVIOFUERADEPROGRAMA");
            while (rs.next()) {
                int lastID = rs.getInt(1);
                id_lote = lastID+1;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException error) {
            System.out.print(error);
        }
    }
     
     
      public void insertarLoteaEnvio() {
        
        try {
            ClassEnv m = new ClassEnv();
            m.setIdlote(id_lote);
            m.setNoenvio(Integer.parseInt(NO1.getText()));
            m.setCantidad(Integer.parseInt(QTY.getText()));
            BDEnvios.insertarLoteEnvio(m);
        } catch (SQLException e) {
            System.out.println("Error BD:" + e);
        }
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
        jScrollPane3 = new javax.swing.JScrollPane();
        ENVIOS = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        CONTROL = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        JOB = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        PN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        LOTE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PROCESO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        QTY = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        GUARDAR = new javax.swing.JButton();
        EDITAR = new javax.swing.JButton();
        NUEVO = new javax.swing.JButton();
        CANCELAR = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        NO1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        FECHA1 = new javax.swing.JTextField();

        setClosable(true);
        setTitle("AGREGAR A ENVIO");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        ENVIOS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ENVIOS.setForeground(new java.awt.Color(255, 51, 0));
        ENVIOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA DE ENVIO"
            }
        ));
        ENVIOS.setIntercellSpacing(new java.awt.Dimension(15, 1));
        ENVIOS.setRowHeight(25);
        ENVIOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ENVIOSMouseClicked(evt);
            }
        });
        ENVIOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ENVIOSKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(ENVIOS);

        CONTROL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CONTROL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "JOB", "P/N", "LOTE", "PROCESO ACTUAL", "QTY", "FECHA"
            }
        ));
        CONTROL.setRowHeight(25);
        CONTROL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CONTROLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(CONTROL);

        JOB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JOB.setEnabled(false);
        JOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("JOB");

        PN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PN.setEnabled(false);
        PN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("P/N");

        LOTE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LOTE.setEnabled(false);
        LOTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOTEActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("LOTE");

        PROCESO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PROCESO.setEnabled(false);
        PROCESO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PROCESOActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("PROCESO ACTUAL");

        QTY.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        QTY.setEnabled(false);
        QTY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("QTY");

        GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save2.png"))); // NOI18N
        GUARDAR.setText("GUARDAR");
        GUARDAR.setEnabled(false);
        GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARActionPerformed(evt);
            }
        });

        EDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit2.png"))); // NOI18N
        EDITAR.setText("EDITAR");
        EDITAR.setEnabled(false);
        EDITAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDITARActionPerformed(evt);
            }
        });

        NUEVO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        NUEVO.setText("NUEVO");
        NUEVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NUEVOActionPerformed(evt);
            }
        });

        CANCELAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        CANCELAR.setText("CANCELAR");
        CANCELAR.setEnabled(false);
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(NUEVO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(GUARDAR)
                        .addGap(26, 26, 26)
                        .addComponent(EDITAR, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(CANCELAR))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LOTE, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel3)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel4)
                                .addGap(128, 128, 128)
                                .addComponent(jLabel5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PROCESO, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(QTY, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LOTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PROCESO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(QTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EDITAR, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NUEVO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CANCELAR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("TRABAJOS EN EL ENVIO NO ");

        NO1.setEditable(false);
        NO1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NO1.setForeground(new java.awt.Color(51, 51, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("FECHA");

        FECHA1.setEditable(false);
        FECHA1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        FECHA1.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NO1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FECHA1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(NO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FECHA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ENVIOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ENVIOSMouseClicked
        NO = (int) (ENVIOS.getModel().getValueAt(ENVIOS.getSelectedRow(),0));
        NO1.setText(String.valueOf(ENVIOS.getModel().getValueAt(ENVIOS.getSelectedRow(),0)));
        FECHA1.setText(String.valueOf(ENVIOS.getModel().getValueAt(ENVIOS.getSelectedRow(),1)));
        ListarTrabajosdeEnvio();
    }//GEN-LAST:event_ENVIOSMouseClicked

    private void ENVIOSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ENVIOSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ENVIOSKeyPressed

    private void CONTROLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CONTROLMouseClicked
         id = (int) (CONTROL.getModel().getValueAt(CONTROL.getSelectedRow(),0));
         JOB.setText((String.valueOf(CONTROL.getModel().getValueAt(CONTROL.getSelectedRow(),1))));
         PN.setText((String.valueOf(CONTROL.getModel().getValueAt(CONTROL.getSelectedRow(),2))));
         LOTE.setText((String.valueOf(CONTROL.getModel().getValueAt(CONTROL.getSelectedRow(),3))));
         PROCESO.setText((String.valueOf(CONTROL.getModel().getValueAt(CONTROL.getSelectedRow(),4))));
         QTY.setText((String.valueOf(CONTROL.getModel().getValueAt(CONTROL.getSelectedRow(),5))));
         EDITAR.setEnabled(true);
         JOB.requestFocus();
    }//GEN-LAST:event_CONTROLMouseClicked

    private void NUEVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NUEVOActionPerformed
    if(NO1.getText().compareTo("")!=0){      
         accion = "Guardar";
         JOB.setEnabled(true);
         PN.setEnabled(true);
         LOTE.setEnabled(true);
         PROCESO.setEnabled(true);
         QTY.setEnabled(true);
         NUEVO.setEnabled(false);
         EDITAR.setEnabled(false);
         GUARDAR.setEnabled(true);
         CANCELAR.setEnabled(true);
         limpiar();
         CONTROL.clearSelection();
         CONTROL.setEnabled(false);
         JOB.requestFocus();
    }else{ JOptionPane.showMessageDialog(null, "SELECCIONE UNA FECHA DE ENVIO....");}
    }//GEN-LAST:event_NUEVOActionPerformed

    private void CANCELARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELARActionPerformed
         JOB.setEnabled(false);
         PN.setEnabled(false);
         LOTE.setEnabled(false);
         PROCESO.setEnabled(false);
         QTY.setEnabled(false);
         NUEVO.setEnabled(true);
         GUARDAR.setEnabled(false);
         EDITAR.setEnabled(false);
         CONTROL.setEnabled(true);
         CONTROL.clearSelection();
         limpiar();
    }//GEN-LAST:event_CANCELARActionPerformed

    private void EDITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDITARActionPerformed
        accion = "Actualizar";
        CONTROL.setEnabled(false);
        JOB.setEnabled(true);
         PN.setEnabled(true);
         LOTE.setEnabled(true);
         PROCESO.setEnabled(true);
         QTY.setEnabled(false);
        NUEVO.setEnabled(false);
        GUARDAR.setEnabled(true);
        ENVIOS.setEnabled(false);
        
        
    }//GEN-LAST:event_EDITARActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
        
        if (JOB.getText().compareTo("")!=0 && PN.getText().compareTo("")!=0 && LOTE.getText().compareTo("")!=0 && PROCESO.getText().compareTo("")!=0  ){
        
        obtenerUltimoId();
        JOB.setEnabled(false);
         PN.setEnabled(false);
         LOTE.setEnabled(false);
         PROCESO.setEnabled(false);
         QTY.setEnabled(false);
        CONTROL.clearSelection();
        NUEVO.setEnabled(true);
        GUARDAR.setEnabled(false);
        EDITAR.setEnabled(false);
        ENVIOS.setEnabled(false);
        CONTROL.setEnabled(true);
        ENVIOS.setEnabled(true);
        NUEVO.setEnabled(true);
         EDITAR.setEnabled(false);
         GUARDAR.setEnabled(false);
         CANCELAR.setEnabled(true);
         guardaractualizar();
        limpiar();
        }else {
       JOptionPane.showMessageDialog(null,"Llene Todos Los Campos...");
       }   
    }//GEN-LAST:event_GUARDARActionPerformed

    private void JOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JOBActionPerformed
        PN.requestFocus();
    }//GEN-LAST:event_JOBActionPerformed

    private void PNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNActionPerformed
        LOTE.requestFocus();
    }//GEN-LAST:event_PNActionPerformed

    private void LOTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOTEActionPerformed
       PROCESO.requestFocus();
    }//GEN-LAST:event_LOTEActionPerformed

    private void PROCESOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PROCESOActionPerformed
        QTY.requestFocus();
    }//GEN-LAST:event_PROCESOActionPerformed

    private void QTYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTYActionPerformed
       GUARDAR.requestFocus();
    }//GEN-LAST:event_QTYActionPerformed

     public void guardaractualizar()    
 {
 
 if(accion.equalsIgnoreCase("Guardar")){
       
       
           try {
              
               ClassEnv m = new ClassEnv();
               m.setIdlote(id_lote);
               m.setJob(JOB.getText());
               m.setPN(PN.getText());
               m.setLoteS(LOTE.getText());
               m.setProcesosActual(PROCESO.getText());
               m.setCantidad(Integer.parseInt(QTY.getText()));
               BDEnvios.InsertarProcesoFuera(m);
               JOptionPane.showMessageDialog(null,"Agregado...");
               insertarLoteaEnvio();
               ListarTrabajosdeEnvio();
           } catch (Exception e) {
               System.out.println("Error BD:"+e.getMessage());
           }
        }   
 else if (accion.equalsIgnoreCase("Actualizar")) {
      System.out.println("actualiza");
            ClassEnv p;
            try {
                p= BDEnvios.BuscarEnvio(Integer.parseInt(NO1.getText()));
                 p.setIdlote(id);
                 p.setJob(JOB.getText());
                 p.setPN(PN.getText());
                 p.setLoteS(LOTE.getText());
                 p.setProcesosActual(PROCESO.getText());
                 p.setCantidad(Integer.parseInt(QTY.getText()));        
                 BDEnvios.actualizarEnvioFuera(p);
                JOptionPane.showMessageDialog(null, " [ Datos Actualizados ]");
                ListarTrabajosdeEnvio();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error BD: " + e.getMessage());
            }
        }
 
 
 }
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CANCELAR;
    private javax.swing.JTable CONTROL;
    private javax.swing.JButton EDITAR;
    private javax.swing.JTable ENVIOS;
    private javax.swing.JTextField FECHA1;
    private javax.swing.JButton GUARDAR;
    private javax.swing.JTextField JOB;
    private javax.swing.JTextField LOTE;
    private javax.swing.JTextField NO1;
    private javax.swing.JButton NUEVO;
    private javax.swing.JTextField PN;
    private javax.swing.JTextField PROCESO;
    private javax.swing.JTextField QTY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
