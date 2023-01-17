/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import BD.BD;
import BD.BD_RECURSOS;
import BD.InsertarEjemplos;
import Class.ClassTrabajos;
import static java.awt.SystemColor.menu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.TableColumn;

/**
 *
 * @author jluis
 */
public class RecoridosEjemplos extends javax.swing.JInternalFrame {

    int id;
    int id_proceso;
    int n;
    int depto;
    String nombre;
    JPopupMenu menu;
    JPopupMenu menucontextual = new JPopupMenu();

    /**
     * Creates new form Recoridos
     */
    public RecoridosEjemplos() {
        initComponents();
        ListarTrabajos();
        PROCESO.setEnabled(false);
        FECHA.setEnabled(false);
        CANTIDAD.setEnabled(false);
        COMENTARIO.setEnabled(false);
        Bagregar.setEnabled(false);
        trabajado.setEnabled(false);

    }

    private void buscar() {
        int CODIGO = Integer.parseInt(String.valueOf(TablaPro.getModel().getValueAt(TablaPro.getSelectedRow(), 5)));
        try {
            ClassTrabajos p = InsertarEjemplos.buscarEmpleado(CODIGO);
            nombre = p.getNombres();// + ' ' + p.getApellidos());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRORRRR" + e);
        }
    }

    private void ValidarExistencia() {

        try {

            Connection con = BD_RECURSOS.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select COUNT(codigo) from alistaempleados where codigo=" + trabajado.getText());
            rs.next();
            int c = rs.getInt("count(codigo)");
            if (c == 0) {
                JOptionPane.showMessageDialog(null, "EL CODIGO EMPLEADO NO EXITE INTENTE DE NUEVO...");
                trabajado.setText("");

            } else {

                try {
                    ClassTrabajos t = new ClassTrabajos();
                    t.setId(id);
                    //t.setId_proceso(id_proceso);
                    t.setProceso(PROCESO.getText());
                    t.setCantidad(Integer.parseInt(CANTIDAD.getText()));
                    t.setFecha1(FECHA.getDate());
                    t.setFecha(fechaAuto.getText());
                    t.setComentarios(COMENTARIO.getText());
                    t.setDepartamento(depto);
                    t.setNota(nota.getText());
                    t.setTrabajadopor(Integer.parseInt(trabajado.getText()));
                    InsertarEjemplos.InsertarProcesoEjemplo(t);
                    JOptionPane.showMessageDialog(null, "Proceso agregado");
                    ListarProcesos();
                    limpiarproce();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "error" + e);
                }

            }
        } catch (Exception e) {
            System.out.println("ERROR " + e);
        }

    }

    public void id() {

        try {
            Connection cn = BD.getConnection();
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery("select max(ID_PROCESO)+1 from EJEMPLOS_PROCESOS");
            rs.next();
            n = (rs.getInt("max(ID_PROCESO)"));
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        id_proceso = n + 1;

    }

    public void FechasJdate() {
        Calendar c2 = new GregorianCalendar();
        FECHA.setCalendar(c2);
    }

    public void Cfecha() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        fechaAuto.setText(dateFormat.format(cal.getTime()));
    }

    public void limpiarproce() {

        PROCESO.setText("");
        FECHA.setDate(null);
        CANTIDAD.setText("");
        COMENTARIO.setText("");
        fechaAuto.setText("");
        PROCESO.setEnabled(false);
        FECHA.setEnabled(false);
        CANTIDAD.setEnabled(false);
        COMENTARIO.setEnabled(false);
        Bagregar.setEnabled(false);
        nota.setText("");
        trabajado.setText("");
        trabajado.setEnabled(false);
        

    }

    public void selectusuario() {
        String a = System.getProperty("user.name");//usar usuario de windows
        if (a.equals("jluis")) {
            depto = 0;
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
        }//TALLE
        else if (a.equals("Sotano")) {
            depto = 10;
        }//SOTANO */
        /*else if (a.equals("Sotano")) {
            depto = 13;
        }//MOLDING*/
        else if (a.equals("apacheco")) {
            depto = 11;
        }//RELACION CON EL CLIENTE  
        else if (a.equals("deptochips")) {
            depto = 12;
        }//RELACION CON EL CLIENTE  
        else if (a.equals("molding")) {
            depto = 13;
        }//DEPARTAMENTO DE MOLDING
        else if (a.equals("marking")) {
            depto = 2;
        }//Maquina de Marking     
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
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPro = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        PRIORIDAD = new javax.swing.JTextField();
        ejemploaprobado = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        REVISIONINFO = new javax.swing.JTextField();
        CANTIDADINFO = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CANTIDADCLIENTEINFO = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CLIENTEINFO = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ESTANDARINFO = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PNINFO = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JOBINFO = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        FECHA = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        nota = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        PROCESO = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        COMENTARIO = new javax.swing.JTextField();
        Bagregar = new javax.swing.JButton();
        fechaAuto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        CANTIDAD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        trabajado = new javax.swing.JTextField();

        setClosable(true);
        setTitle("PROCESO DE EJEMPLOS");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        trab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        trab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "P/N", "JOB", "CLIENTE", "ESTANDAR", "CANTIDAD ", "REV"
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("P/N");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("JOB");

        PN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        JOB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        TablaPro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TablaPro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PROCESO ACTUAL", "FECHA", "CANTIDAD", "PROCESO REALIZADO", "DEPARTAMENTO", "REALIZADO POR", "NOTA"
            }
        ));
        TablaPro.setGridColor(new java.awt.Color(0, 0, 0));
        TablaPro.setSelectionForeground(new java.awt.Color(255, 255, 204));
        TablaPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaPro);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("PRIORIDAD #");

        PRIORIDAD.setEditable(false);
        PRIORIDAD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PRIORIDAD.setForeground(new java.awt.Color(255, 0, 0));

        ejemploaprobado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ejemploaprobado.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PRIORIDAD)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addComponent(ejemploaprobado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PRIORIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ejemploaprobado, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("REVISION");

        REVISIONINFO.setEditable(false);
        REVISIONINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        REVISIONINFO.setForeground(new java.awt.Color(0, 102, 255));

        CANTIDADINFO.setEditable(false);
        CANTIDADINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CANTIDADINFO.setForeground(new java.awt.Color(0, 102, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setText("CANTIDAD /   QTY CLIENTE");

        CANTIDADCLIENTEINFO.setEditable(false);
        CANTIDADCLIENTEINFO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CANTIDADCLIENTEINFO.setForeground(new java.awt.Color(0, 102, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(REVISIONINFO)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(CANTIDADINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CANTIDADCLIENTEINFO))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(REVISIONINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CANTIDADINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CANTIDADCLIENTEINFO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ESTANDARINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(CLIENTEINFO, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(153, 204, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("FECHA");

        FECHA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FECHAMouseClicked(evt);
            }
        });

        nota.setColumns(20);
        nota.setRows(5);
        jScrollPane3.setViewportView(nota);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("NOTA");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(39, 39, 39))
        );

        jPanel10.setBackground(new java.awt.Color(153, 204, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("SIGUIENTE PROCESO");

        PROCESO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PROCESO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PROCESOActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("PROCESO REALIZADO");

        COMENTARIO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        COMENTARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMENTARIOActionPerformed(evt);
            }
        });

        Bagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save2.png"))); // NOI18N
        Bagregar.setText("AGREGAR");
        Bagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BagregarActionPerformed(evt);
            }
        });

        fechaAuto.setEditable(false);
        fechaAuto.setBackground(new java.awt.Color(153, 204, 255));
        fechaAuto.setBorder(null);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("CANTIDAD");

        CANTIDAD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CANTIDAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANTIDADActionPerformed(evt);
            }
        });
        CANTIDAD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CANTIDADKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("TRABAJADO POR");

        trabajado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        trabajado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trabajadoActionPerformed(evt);
            }
        });
        trabajado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                trabajadoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Bagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel13)
                        .addComponent(jLabel15)
                        .addComponent(PROCESO, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                        .addComponent(COMENTARIO)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(trabajado, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fechaAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PROCESO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(COMENTARIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trabajado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bagregar)
                    .addComponent(fechaAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(JOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void PNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNActionPerformed
        JOB.requestFocus();
    }//GEN-LAST:event_PNActionPerformed

    private void BagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BagregarActionPerformed

        if (PROCESO.getText().compareTo("") != 0
                && CANTIDAD.getText().compareTo("") != 0
                && FECHA.getDate() != null && trabajado.getText().compareTo("") != 0) {

            Cfecha();
            selectusuario();
            ValidarExistencia();

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese Todos los Datos");
        }

    }//GEN-LAST:event_BagregarActionPerformed

    private void PNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PNKeyReleased
        ListarTrabajos();
    }//GEN-LAST:event_PNKeyReleased

    private void JOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JOBActionPerformed
        ListarTrabajos();
    }//GEN-LAST:event_JOBActionPerformed

    private void JOBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JOBKeyReleased
        ListarTrabajos();
    }//GEN-LAST:event_JOBKeyReleased

    private void trabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trabMouseClicked
        llenacuainformacion();
        FechasJdate();
        Cfecha();
        PROCESO.setEnabled(true);
        FECHA.setEnabled(true);
        CANTIDAD.setEnabled(true);
        COMENTARIO.setEnabled(true);
        Bagregar.setEnabled(true);
        trabajado.setEnabled(true);
    }//GEN-LAST:event_trabMouseClicked

    private void PROCESOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PROCESOActionPerformed
        COMENTARIO.requestFocus();
        //Cfecha();
    }//GEN-LAST:event_PROCESOActionPerformed

    private void CANTIDADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANTIDADActionPerformed
        trabajado.requestFocus();
    }//GEN-LAST:event_CANTIDADActionPerformed

    private void trabAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_trabAncestorMoved
    }//GEN-LAST:event_trabAncestorMoved

    private void COMENTARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMENTARIOActionPerformed
        CANTIDAD.requestFocus();
    }//GEN-LAST:event_COMENTARIOActionPerformed

    private void FECHAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FECHAMouseClicked

        Cfecha();

    }//GEN-LAST:event_FECHAMouseClicked

    private void trabajadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trabajadoActionPerformed

        Bagregar.requestFocus();

    }//GEN-LAST:event_trabajadoActionPerformed

    private void TablaProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProMouseClicked

        int EXISTE = Integer.parseInt(String.valueOf(TablaPro.getModel().getValueAt(TablaPro.getSelectedRow(), 5)));
        if (EXISTE == 0) {
            TablaPro.setToolTipText("No se registro quien lo realizo");
        } else {
            buscar();
            TablaPro.setToolTipText(nombre);
        }

    }//GEN-LAST:event_TablaProMouseClicked

    private void trabajadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trabajadoKeyTyped
       char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && (c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_trabajadoKeyTyped

    private void CANTIDADKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CANTIDADKeyTyped
       char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && (c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CANTIDADKeyTyped

    private void ListarTrabajos() {
        ArrayList<ClassTrabajos> result = InsertarEjemplos.ListarEjemplos(PN.getText(), JOB.getText());
        RecargarTabla(result);
    }

    private void RecargarTabla(ArrayList<ClassTrabajos> list) {

        Object[][] datos = new Object[list.size()][8];
        int i = 0;
        for (ClassTrabajos t : list) {
            datos[i][0] = t.getId();
            datos[i][1] = t.getPN();
            datos[i][2] = t.getJob();
            datos[i][3] = t.getCliente();
            datos[i][4] = t.getEstandar();
            datos[i][5] = t.getRevision();
            datos[i][6] = t.getQtyproduccion();

            i++;
        }
        trab.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "No.", "P/N", "JOB", "CLIENTE", "ESTANDAR", "REVISION", "CANTIDAD"

                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

    }

    private void ListarProcesos() {
        ArrayList<ClassTrabajos> result1 = InsertarEjemplos.ListarProcesoEjemplo(id);
        Listar(result1);
    }

    private void Listar(ArrayList<ClassTrabajos> list1) {

        Object[][] datos = new Object[list1.size()][7];
        int i = 0;
        for (ClassTrabajos t : list1) {
            datos[i][0] = t.getProceso();
            datos[i][1] = t.getFecha();
            datos[i][2] = t.getCantidad();
            datos[i][3] = t.getComentarios();
            datos[i][4] = t.getDepto();
            datos[i][5] = t.getTrabajadopor();
            datos[i][6] = t.getNota();
            i++;
        }
        TablaPro.setModel(new javax.swing.table.DefaultTableModel(
                datos,
                new String[]{
                    "PROCESO ACTUAL", "FECHA", "CANTIDAD", "PROCESO REALIZADO", "DEPARTAMENTO", "REALIZADO POR", "NOTA"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        TableColumn columna1 = TablaPro.getColumn("PROCESO ACTUAL");
        columna1.setPreferredWidth(200);
        TableColumn columna2 = TablaPro.getColumn("FECHA");
        columna2.setPreferredWidth(0);
        TableColumn columna3 = TablaPro.getColumn("CANTIDAD");
        columna3.setPreferredWidth(0);
        TableColumn columna4 = TablaPro.getColumn("PROCESO REALIZADO");
        columna4.setPreferredWidth(200);
        TableColumn columna5 = TablaPro.getColumn("NOTA");
        columna5.setPreferredWidth(120);
        TableColumn columna6 = TablaPro.getColumn("DEPARTAMENTO");
        columna6.setPreferredWidth(50);
        TableColumn columna7 = TablaPro.getColumn("REALIZADO POR");
        columna7.setPreferredWidth(0);
    }

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
            java.util.logging.Logger.getLogger(RecoridosEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecoridosEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecoridosEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecoridosEjemplos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecoridosEjemplos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bagregar;
    private javax.swing.JTextField CANTIDAD;
    private javax.swing.JTextField CANTIDADCLIENTEINFO;
    private javax.swing.JTextField CANTIDADINFO;
    private javax.swing.JTextField CLIENTEINFO;
    private javax.swing.JTextField COMENTARIO;
    private javax.swing.JTextField ESTANDARINFO;
    private com.toedter.calendar.JDateChooser FECHA;
    private javax.swing.JTextField JOB;
    private javax.swing.JTextField JOBINFO;
    private javax.swing.JTextField PN;
    private javax.swing.JTextField PNINFO;
    private javax.swing.JTextField PRIORIDAD;
    private javax.swing.JTextField PROCESO;
    private javax.swing.JTextField REVISIONINFO;
    private javax.swing.JTable TablaPro;
    private javax.swing.JLabel ejemploaprobado;
    private javax.swing.JTextField fechaAuto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea nota;
    private javax.swing.JTable trab;
    private javax.swing.JTextField trabajado;
    // End of variables declaration//GEN-END:variables

    private void llenacuainformacion() {
        try {

            ClassTrabajos c = InsertarEjemplos.buscarEjemplo(Integer.parseInt(String.valueOf(trab.getModel().getValueAt(trab.getSelectedRow(), 0))));
            id = c.getId();
            PNINFO.setText(c.getPN());
            JOBINFO.setText(c.getJob());
            CLIENTEINFO.setText(c.getCliente());
            ESTANDARINFO.setText(c.getEstandar());
            CANTIDADINFO.setText(String.valueOf(c.getQtyproduccion()));
            REVISIONINFO.setText(c.getRevision());
            PRIORIDAD.setText(c.getPrioridadStrin());
            CANTIDADCLIENTEINFO.setText(String.valueOf(c.getQtycliente()));
            PROCESO.requestFocus();
            if (c.getEstadoeje() == 3 || c.getEstadoeje() == 4) {
                ejemploaprobado.setText("EJEMPLO YA APROBADO");
            }
            //System.out.println("ESTADO = " + c.getEstadoeje());
            ListarProcesos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e);
        }
    }
}
