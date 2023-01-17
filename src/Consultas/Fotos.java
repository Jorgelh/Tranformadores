/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jluis
 */
public class Fotos extends javax.swing.JInternalFrame {

    Image foto;
    Vector imagenes;
    int index = 0;
    String folder = "";
    String carpeta;
    public Fotos() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        initComponents();
        txtPN.requestFocus();
        //listarDirectorio();
        this.imagenes = new Vector();
        this.imagenes = new Vector();
       

    }

     void cargarAlbum() {
        carpeta = "CUSTOM";
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                if (name.endsWith(".jpg")) {
                    return true;
                } else {
                    
                    return false;
                }
            }
        };

        imagenes.clear();
        index = 0;
        String album = ("\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\CUSTOM\\"+txtPN.getText());
        File albumCarpeta = new File(album);
        folder = albumCarpeta.getName();
        File[] fotos = albumCarpeta.listFiles(filter);
        if (fotos != null) {
            if (fotos.length != 0) {
                adelante.requestFocus();
                for (int i = 0;
                        i < fotos.length;
                        i++) {
                    if (fotos[i].isFile()) {
                        imagenes.add(fotos[i].getName());
                    }
                }
                jLabel1.setIcon(new ImageIcon(((new ImageIcon("\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\CUSTOM\\" + folder + "\\" + (String) imagenes.elementAt(0)).getImage()).getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH))));
            } else {
               
               jLabel1.setIcon(new ImageIcon(((new ImageIcon("\\\\192.168.0.2\\Compartida Produccion\\Master Document\\No_Image_Available.jpg").getImage()).getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH))));
                return;
            }
        } else {
             cargarAlbum2();
            //jLabel1.setIcon(new ImageIcon(((new ImageIcon("\\\\192.168.0.2\\Compartida Produccion\\Master Document\\No_Image_Available.jpg").getImage()).getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH))));

        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     void cargarAlbum2() {
        carpeta = "CATALOG";
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                if (name.endsWith(".jpg")) {
                    return true;
                } else {
                    
                    return false;
                }
            }
        };

        imagenes.clear();
        index = 0;
        String album = ("\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\CATALOG\\"+txtPN.getText());
        File albumCarpeta = new File(album);
        folder = albumCarpeta.getName();
        File[] fotos = albumCarpeta.listFiles(filter);
        if (fotos != null) {
            if (fotos.length != 0) {
                adelante.requestFocus();
                for (int i = 0;
                        i < fotos.length;
                        i++) {
                    if (fotos[i].isFile()) {
                        imagenes.add(fotos[i].getName());
                    }
                }
                jLabel1.setIcon(new ImageIcon(((new ImageIcon("\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\CATALOG\\" + folder + "\\" + (String) imagenes.elementAt(0)).getImage()).getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH))));
            } else {
                jLabel1.setIcon(new ImageIcon(((new ImageIcon("\\\\192.168.0.2\\Compartida Produccion\\Master Document\\No_Image_Available.jpg").getImage()).getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH))));
                return;
            }
        } else {
            jLabel1.setIcon(new ImageIcon(((new ImageIcon("\\\\192.168.0.2\\Compartida Produccion\\Master Document\\No_Image_Available.jpg").getImage()).getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH))));

        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
     
     
     
    void fotoAdelante() {
        if ((index + 1) < imagenes.size()) {
            index++;
            ImageIcon imagen = (new ImageIcon(((new ImageIcon("\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\"+carpeta+"\\" + folder + "\\" + (String) imagenes.elementAt(index)).getImage()).getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH))));
            jLabel1.setIcon(imagen);
        } else {
            atras.requestFocus();
            JOptionPane.showMessageDialog(null, "Fin de las Fotos del Album", "Atencion", JOptionPane.OK_OPTION);
        }
    }

    void fotoAtras() {
        if ((index - 1) > -1) {
            index--;
            ImageIcon imagen = (new ImageIcon(((new ImageIcon("\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\"+carpeta+"\\" + folder + "\\" + (String) imagenes.elementAt(index)).getImage()).getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH))));
            jLabel1.setIcon(imagen);
        } else {
            adelante.requestFocus();
            JOptionPane.showMessageDialog(null, "Fin de las Fotos del Album", "Atencion", JOptionPane.OK_OPTION);
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
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Fotosboton = new javax.swing.JButton();
        atras = new javax.swing.JButton();
        adelante = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPN = new javax.swing.JTextField();

        setClosable(true);
        setTitle("CONSULTA DE FOTOS");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setToolTipText("");
        jPanel1.setName(""); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jButton1.setFont(new java.awt.Font("Eras Bold ITC", 0, 12)); // NOI18N
        jButton1.setText("NUEVO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Eras Bold ITC", 0, 12)); // NOI18N
        jButton2.setText("DRAWINGS P/N");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Fotosboton.setFont(new java.awt.Font("Eras Bold ITC", 0, 12)); // NOI18N
        Fotosboton.setText("FOTOS P/N");
        Fotosboton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FotosbotonActionPerformed(evt);
            }
        });

        atras.setText("<<");
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });
        atras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                atrasKeyPressed(evt);
            }
        });

        adelante.setText(">>");
        adelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adelanteActionPerformed(evt);
            }
        });
        adelante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adelanteKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel2.setText("P/N");

        txtPN.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        txtPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 23, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(atras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(adelante)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Fotosboton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPN, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fotosboton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adelante)
                    .addComponent(atras))
                .addContainerGap(24, Short.MAX_VALUE))
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

        jPanel1.getAccessibleContext().setAccessibleName("Consulta P/N");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        fotoAtras();
    }//GEN-LAST:event_atrasActionPerformed

    private void adelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adelanteActionPerformed
        fotoAdelante();
    }//GEN-LAST:event_adelanteActionPerformed

    private void adelanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adelanteKeyPressed
        fotoAdelante();
    }//GEN-LAST:event_adelanteKeyPressed

    private void atrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_atrasKeyPressed
        fotoAtras();
    }//GEN-LAST:event_atrasKeyPressed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
/*
        try {
            Desktop.getDesktop().open(new File("\\\\192.168.0.2\\Compartida Produccion\\DRAWINGS\\TRANSFORMERS\\DRAWINGS\\" + txtPN.getText()));
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "La Carpeta de P/N "+txtPN.getText()+" No Existe");
            if (ex != null) {
                Logger.getLogger(Fotos.class.getName()).log(Level.SEVERE, null, ex);
            } else {
                System.out.println("Forms");
            
        }*/
        try {
            Desktop.getDesktop().open(new File("\\\\192.168.0.2\\Compartida Produccion\\DRAWINGS\\TRANSFORMERS\\DRAWINGS\\" + txtPN.getText()));

        } catch (Exception ex) {
            
            try {
                Desktop.getDesktop().open(new File("\\\\192.168.0.2\\Compartida Produccion\\DRAWINGS\\FOUR CORNER\\" + txtPN.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La Carpeta de P/N "+txtPN.getText()+" no existe");
            }
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        txtPN.setText("");
        txtPN.requestFocus();
        jLabel1.setIcon(null);
        imagenes.clear();
    }//GEN-LAST:event_jButton1ActionPerformed
    private void FotosbotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FotosbotonActionPerformed
        String letra = "\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\CUSTOM\\"+ txtPN.getText();
        File Dir = new File(letra);
        File[] lista_Archivos = Dir.listFiles();
        System.out.println("carpeta" + lista_Archivos);
        try {
            if (lista_Archivos != null) {
                Desktop.getDesktop().open(new File("\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\CUSTOM\\" + txtPN.getText()));
            } else {
                Desktop.getDesktop().open(new File("\\\\192.168.0.2\\Compartida Produccion\\FOTO DE PIEZAS (desde 2019)\\CUSTOM\\"));
            }
        } catch (IOException ex) {
            Logger.getLogger(Fotos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FotosbotonActionPerformed
    private void txtPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPNActionPerformed
        
        
        cargarAlbum();
    }//GEN-LAST:event_txtPNActionPerformed

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
            java.util.logging.Logger.getLogger(Fotos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fotos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fotos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fotos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fotos().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Fotosboton;
    private javax.swing.JButton adelante;
    private javax.swing.JButton atras;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtPN;
    // End of variables declaration//GEN-END:variables

}
