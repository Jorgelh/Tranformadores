/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import BD.BD;
import BD.InsertTrabajosTransformadores;
import Class.*;
import Class.CerrararTra;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
 

/**
 *
 * @author jluis
 */
public class IniciarTrabajoTrans extends javax.swing.JInternalFrame {
    int id;
    int id_proceso;
    int n;
    DefaultTableModel temp;
    
    

    /**
     * Creates new form Recoridos
     */
    public IniciarTrabajoTrans() {
        initComponents();
        ListarTrabajosTrans();
    }
    
    public void id(){
        
        try {
             Connection cn = BD.getConnection();
             Statement ps = cn.createStatement();
             ResultSet rs = ps.executeQuery("select max(ID_PROCESO) from PROCESO");
             rs.next();
             n = (rs.getInt("max(ID_PROCESO)"));
             ps.close();
             rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
          id_proceso = n+1;
          
    
    }
    
 /*   public void actualizarTrabajo(){   
        try {
             Connection cn = BD.getConnection();
             Statement ps = cn.createStatement();
             ResultSet rs = ps.executeQuery("UPDATE TRABAJO SET ESTADO = 2, FECHAFIN ='"+fechafin.getDate()+"' WHERE ID="+no.getText());
             ps.close();
             rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        ListarTrabajos();
        limpiar();
        LimpirTablaPro();
    }
    
    public void Cfecha() {                                      
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
         Calendar cal = Calendar.getInstance();
}   */   
    
       
    public void limpiar(){
    
        no.setText("");
        PNINFO.setText("");
        CLIENTEINFO.setText("");
        REVISIONINFO.setText("");
        JOBINFO.setText("");
        ESTANDARINFO.setText("");
        nolote.setText("");
        CANTIDADINFO.setText("");
        fechafin.setDate(null);
        PRIORIDAD.setText("");
    
    
    }
    
    /*public void LimpirTablaPro() {

        try {
            temp = (DefaultTableModel) TablaPro.getModel();
            int a = temp.getRowCount();
            for (int i = 0; i < a; i++) {
                temp.removeRow(i);
                i--;
            }
        } catch (Exception e) {

        }

    }*/
   
    public void IniciarTrabajo(){
    
        
         if(no.getText().compareTo("")!=0 && fechafin.getDate()!=null){
            
             int resp=JOptionPane.showConfirmDialog(null,"Desea Iniciar el Trabajo P/N = "+PNINFO.getText()+" Lote = "+nolote.getText());
          if (JOptionPane.OK_OPTION == resp){
          
        CerrararTra m = new CerrararTra();
        try {
            
           m.setNo(Integer.parseInt(no.getText()));
           m.setFecha(fechafin.getDate());
           InsertTrabajosTransformadores.IniciarTra(m);
           JOptionPane.showMessageDialog(null, "Trabajo Iniciado Correctamente");
           limpiar();
          // LimpirTablaPro();
           ListarTrabajosTrans();
           
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "MENSAJE"+e);
        }
        }
        }else{JOptionPane.showMessageDialog(null, "Ingrese Una Fecha o Seleccione Un Trabajo");}
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
        jScrollPane1 = new javax.swing.JScrollPane();
        trab = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PN = new javax.swing.JTextField();
        JOB = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PNINFO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JOBINFO = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CANTIDADINFO = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        REVISIONINFO = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CLIENTEINFO = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ESTANDARINFO = new javax.swing.JTextField();
        nolote = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        no = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        PRIORIDAD = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        fechafin = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setTitle("TRABAJOS");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        trab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        trab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "P/N", "JOB", "CLIENTE", "ESTANDAR", "CANTIDAD ", "REV", "No. LOTE"
            }
        ));
        trab.setGridColor(new java.awt.Color(0, 0, 0));
        trab.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                trabAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        trab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(trab);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("P/N");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("JOB");

        PN.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        PN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNActionPerformed(evt);
            }
        });
        PN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PNKeyReleased(evt);
            }
        });

        JOB.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOBActionPerformed(evt);
            }
        });
        JOB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JOBKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("P/N");

        PNINFO.setEditable(false);
        PNINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PNINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("JOB");

        JOBINFO.setEditable(false);
        JOBINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JOBINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("CANTIDAD");

        CANTIDADINFO.setEditable(false);
        CANTIDADINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CANTIDADINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("REVISION");

        REVISIONINFO.setEditable(false);
        REVISIONINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        REVISIONINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CLIENTE");

        CLIENTEINFO.setEditable(false);
        CLIENTEINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CLIENTEINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("ESTANDAR");

        ESTANDARINFO.setEditable(false);
        ESTANDARINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ESTANDARINFO.setForeground(new java.awt.Color(0, 102, 255));

        nolote.setEditable(false);
        nolote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nolote.setForeground(new java.awt.Color(0, 102, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("#LOTE");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("No.");

        no.setEditable(false);
        no.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        no.setForeground(new java.awt.Color(0, 102, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("PRIORIDAD");

        PRIORIDAD.setEditable(false);
        PRIORIDAD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PRIORIDAD.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10))
                                .addGap(102, 102, 102))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(no)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(PNINFO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CLIENTEINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(95, 95, 95)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(REVISIONINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7)))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(JOBINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ESTANDARINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(nolote)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CANTIDADINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(PRIORIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PNINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CLIENTEINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(REVISIONINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JOBINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ESTANDARINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nolote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CANTIDADINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PRIORIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1481834876_tick_16.png"))); // NOI18N
        jButton1.setText("INICIAR  TRABAJO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("FECHA DE INICIO DE PRODUCCION");

        fechafin.setDateFormatString("d/MM/yy");
        fechafin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(231, 231, 231)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNActionPerformed
      JOB.requestFocus();
    }//GEN-LAST:event_PNActionPerformed

    private void PNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PNKeyReleased
           ListarTrabajosTrans();
    }//GEN-LAST:event_PNKeyReleased

    private void JOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JOBActionPerformed
           ListarTrabajosTrans();

    }//GEN-LAST:event_JOBActionPerformed

    private void JOBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JOBKeyReleased
           ListarTrabajosTrans();
    }//GEN-LAST:event_JOBKeyReleased

    private void trabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trabMouseClicked

        llenacuainformacion();
    }//GEN-LAST:event_trabMouseClicked

    private void trabAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_trabAncestorMoved
    }//GEN-LAST:event_trabAncestorMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       IniciarTrabajo();
        
    }//GEN-LAST:event_jButton1ActionPerformed
     
    private void ListarTrabajosTrans(){
        ArrayList<ClassTrabajos> result = InsertTrabajosTransformadores.ListarTrabajoIniciar(PN.getText(),JOB.getText());
        RecargarTabla(result);  
    }
     private void RecargarTabla(ArrayList<ClassTrabajos> list) {
         
              Object[][] datos = new Object[list.size()][8];
              int i = 0;
              for(ClassTrabajos t : list)
              {
                  datos[i][0] = t.getId();
                  datos[i][1] = t.getNOLOTE();
                  datos[i][2] = t.getPN();
                  datos[i][3] = t.getJob();
                  datos[i][4] = t.getCliente();
                  datos[i][5] = t.getEstandar();
                  datos[i][6] = t.getRevision();
                  datos[i][7] = t.getQTYPORLOTE();
                  
                  i++;
              }    
             trab.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                " ","NO. LOTE","P/N","JOB","CLIENTE","ESTANDAR","REVISION","CANTIDAD"

             })
             {  
                 @Override
             public boolean isCellEditable(int row, int column){
                 return false;
             }
             });
             TableColumn columna1 = trab.getColumn(" ");
             columna1.setPreferredWidth(-10);
             TableColumn columna2 = trab.getColumn("NO. LOTE");
             columna2.setPreferredWidth(50);
             TableColumn columna3 = trab.getColumn("P/N");
             columna3.setPreferredWidth(50);
             TableColumn columna4 = trab.getColumn("JOB");
             columna4.setPreferredWidth(50);
             TableColumn columna5 = trab.getColumn("CLIENTE");
             columna5.setPreferredWidth(50);
             TableColumn columna6 = trab.getColumn("ESTANDAR");
             columna6.setPreferredWidth(60);
             TableColumn columna7 = trab.getColumn("REVISION");
             columna7.setPreferredWidth(50);
             TableColumn columna8 = trab.getColumn("CANTIDAD");
             columna8.setPreferredWidth(50);
             
}
     
   /* private void ListarProcesos(){
        ArrayList<ClassTrabajos> result1 = InsertTrabajos.ListarProceso(id);
        Listar(result1);  
    }
     private void Listar(ArrayList<ClassTrabajos> list1) {
         
              Object[][] datos = new Object[list1.size()][4];
              int i = 0;
              for(ClassTrabajos t : list1)
              {
                  datos[i][0] = t.getProceso();
                  datos[i][1] = t.getFecha();
                  datos[i][2] = t.getCantidad();
                  datos[i][3] = t.getComentarios();
                  i++;
              }    
             TablaPro.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                "PROCESO","FECHA","CANTIDAD","COMENTARIO"
             })
             {  
                 @Override
             public boolean isCellEditable(int row, int column){
                 return false;
             }
             });
             TableColumn columna1 = TablaPro.getColumn("CANTIDAD");
             columna1.setPreferredWidth(0);
             TableColumn columna2 = TablaPro.getColumn("FECHA");
             columna2.setPreferredWidth(10);}*/
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
            java.util.logging.Logger.getLogger(IniciarTrabajoTrans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IniciarTrabajoTrans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IniciarTrabajoTrans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IniciarTrabajoTrans.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new IniciarTrabajoTrans().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CANTIDADINFO;
    private javax.swing.JTextField CLIENTEINFO;
    private javax.swing.JTextField ESTANDARINFO;
    private javax.swing.JTextField JOB;
    private javax.swing.JTextField JOBINFO;
    private javax.swing.JTextField PN;
    private javax.swing.JTextField PNINFO;
    private javax.swing.JTextField PRIORIDAD;
    private javax.swing.JTextField REVISIONINFO;
    private com.toedter.calendar.JDateChooser fechafin;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField no;
    private javax.swing.JTextField nolote;
    private javax.swing.JTable trab;
    // End of variables declaration//GEN-END:variables

    private void llenacuainformacion() {
       try {
            //id();
            ClassTrabajos c = InsertTrabajosTransformadores.buscarTrabajoTrans(Integer.parseInt(String.valueOf(trab.getModel().getValueAt(trab.getSelectedRow(), 0))));
            //id = c.getId();
            no.setText(String.valueOf(c.getId()));
            PNINFO.setText(c.getPN());
            JOBINFO.setText(c.getJob());
            CLIENTEINFO.setText(c.getCliente());
            ESTANDARINFO.setText(c.getEstandar());
            CANTIDADINFO.setText(String.valueOf(c.getQTYPORLOTE()));
            REVISIONINFO.setText(c.getRevision());
            nolote.setText(String.valueOf(c.getNOLOTE()));
            PRIORIDAD.setText(c.getPrioridadStrin());
           // ListarProcesos();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e);
        }
    }
}
