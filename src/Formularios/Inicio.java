/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import BD.BD;
import Ejemplos.RecoridosEjemplos;
import Consultas.Drawings;
import Envios.EditarEnvio;
import Consultas.EjemplosAprobadosParaTrabajos;
import Consultas.Fotos;
import Consultas.Historial;
import Consultas.HistorialEjemplos;
import Ejemplos.AprobacionEjemplos;
import Ejemplos.IngresoEjemplos;
import Ejemplos.PrioridadProduccionEjemplos;
import Ejemplos.RecoridosEjemplosCombo;
import Envios.AgregarPNquenoestaenprocesosTransformer;
import Envios.CambiarPNenvio;
import Envios.DateCode;
import Envios.EditarCantidad;
import Envios.Historialenvios;
import Envios.ListadoEnvios;
import Envios.NuevoEnvio;
import Envios.SN;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Formularios.EditarCantidadLotes;
import SolicitudMaterialesBodega.CalculoIndicador;
import SolicitudMaterialesBodega.EntregasBodega;
import SolicitudMaterialesBodega.HistorialEntregas;
import SolicitudMaterialesBodega.SolicitudesBodega;
import SolicitudesMateriales.ActualizarStatus;
import SolicitudesMateriales.ConsultadePedidos;
import SolicitudesMateriales.ConsultadeProductos;
import SolicitudesMateriales.EntregaSolicitud;
import SolicitudesMateriales.HistorialStatus;
import SolicitudesMateriales.InicioSolicItudesProductosAtaller;
import SolicitudesMateriales.STATUS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author jluis
 */
public class Inicio extends javax.swing.JFrame {

    int depto;

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        initComponents();
        selectusuario();
        vencer();
        this.setExtendedState(MAXIMIZED_BOTH);

        if (depto == 0) {
            nuevotrabajo.setEnabled(true);
            iniciartrabajo.setEnabled(true);
            procesos.setEnabled(true);
            cerrartrabajo.setEnabled(true);
            EDITARCANTIDAD.setEnabled(true);
            nuevoejemplo.setEnabled(true);
            procesoejemplo.setEnabled(true);
            aprobacionejemplo.setEnabled(true);
            taller.setEnabled(true);
            PRIORIDADEJE.setEnabled(true);
            Prioridad.setEnabled(true);
            trabajosdepto.setEnabled(true);
            ingresoSolicitud.setEnabled(true);
            entregaSolicitud.setEnabled(true);
            listaEnvio.setEnabled(true);
            modificarenvio.setEnabled(true);
            sn.setEnabled(true);
            crearenvio.setEnabled(true);
             datecode.setEnabled(true);
             Menumodificarenvio.setEnabled(true);
            
        } else if (depto == 1)//transformadores
        {
            nuevotrabajo.setEnabled(true);
            iniciartrabajo.setEnabled(true);
            procesos.setEnabled(true);
            cerrartrabajo.setEnabled(true);
            EDITARCANTIDAD.setEnabled(true);
            procesoejemplo.setEnabled(true);
            Prioridad.setEnabled(true);
            PRIORIDADEJE.setEnabled(true);
            trabajosdepto.setEnabled(true);
            ingresoSolicitud.setEnabled(true);
            listaEnvio.setEnabled(true);
            modificarenvio.setEnabled(true);
            sn.setEnabled(true);
            crearenvio.setEnabled(true);
            Menumodificarenvio.setEnabled(true);
        } else if (depto == 2)//INGENIERIA2
        {
            procesos.setEnabled(true);
           // nuevoejemplo.setEnabled(true);
            procesoejemplo.setEnabled(true);
            aprobacionejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
            
        } else if (depto == 3)//potting
        {
            procesos.setEnabled(true);
            procesoejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
        } else if (depto == 4)//inspeccio
        {
            procesos.setEnabled(true);
            procesoejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
        } else if (depto == 5)//testing
        {
            procesos.setEnabled(true);
            procesoejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
        } else if (depto == 6)//calidad    
        {
            procesos.setEnabled(true);
            procesoejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
        } else if (depto == 7)//oscar
        {
            nuevotrabajo.setEnabled(true);
            iniciartrabajo.setEnabled(true);
            procesos.setEnabled(true);
            cerrartrabajo.setEnabled(true);
            EDITARCANTIDAD.setEnabled(true);
            nuevoejemplo.setEnabled(true);
            procesoejemplo.setEnabled(true);
            aprobacionejemplo.setEnabled(true);
            taller.setEnabled(true);
            PRIORIDADEJE.setEnabled(true);
            Prioridad.setEnabled(true);
            trabajosdepto.setEnabled(true);
            ingresoSolicitud.setEnabled(true);
            entregaSolicitud.setEnabled(true);
        }else if (depto == 8)//BODEGA
        {
            procesos.setEnabled(true);
            //nuevoejemplo.setEnabled(true);
            procesoejemplo.setEnabled(true);
           // aprobacionejemplo.setEnabled(true);
           trabajosdepto.setEnabled(true);
           entregaSolicitud.setEnabled(true);
        }else if (depto == 9)//RELACION CON EL CLIENTE
        {
            procesos.setEnabled(true);
            nuevoejemplo.setEnabled(true);
            procesoejemplo.setEnabled(true);
            //aprobacionejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
            datecode.setEnabled(true);
        }else if (depto == 10)//TALLER
        {
            procesos.setEnabled(true);
            procesoejemplo.setEnabled(true);
            //aprobacionejemplo.setEnabled(true);
            taller.setEnabled(true);
            trabajosdepto.setEnabled(true);
        }
        else if (depto == 10)//TALLER
        {
            procesos.setEnabled(true);
            procesoejemplo.setEnabled(true);
            //aprobacionejemplo.setEnabled(true);
            taller.setEnabled(true);
            trabajosdepto.setEnabled(true);
        }
        else if (depto == 11)//GERENCIA
        {
            procesos.setEnabled(true);
           // nuevoejemplo.setEnabled(true);
            procesoejemplo.setEnabled(true);
            aprobacionejemplo.setEnabled(true);
            //aprobacionejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
        }
        else if (depto == 12)//CHIPS
        {
            procesos.setEnabled(true);
            procesoejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
        }
        else if (depto == 13)//MOLDING
        {
            procesos.setEnabled(true);
            procesoejemplo.setEnabled(true);
            trabajosdepto.setEnabled(true);
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

        jToolBar1 = new javax.swing.JToolBar();
        Pane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        me = new javax.swing.JMenu();
        nuevotrabajo = new javax.swing.JMenuItem();
        EDITARCANTIDAD = new javax.swing.JMenuItem();
        iniciartrabajo = new javax.swing.JMenuItem();
        procesos = new javax.swing.JMenuItem();
        trabajosdepto = new javax.swing.JMenuItem();
        cerrartrabajo = new javax.swing.JMenuItem();
        Prioridad = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        nuevoejemplo = new javax.swing.JMenuItem();
        procesoejemplo = new javax.swing.JMenuItem();
        aprobacionejemplo = new javax.swing.JMenuItem();
        PRIORIDADEJE = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        listaEnvio = new javax.swing.JMenuItem();
        crearenvio = new javax.swing.JMenuItem();
        Menumodificarenvio = new javax.swing.JMenu();
        modificarenvio = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        sn = new javax.swing.JMenuItem();
        datecode = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        TALLER = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        taller = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        ingresoSolicitud = new javax.swing.JMenuItem();
        entregaSolicitud = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Historial de Produccion");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Pane1.setBackground(new java.awt.Color(255, 255, 255));
        Pane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Pane1.setInheritsPopupMenu(true);
        Pane1.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 2, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("TRANSFORMADORES");

        jLabel2.setText("jLabel2");

        Pane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Pane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout Pane1Layout = new javax.swing.GroupLayout(Pane1);
        Pane1.setLayout(Pane1Layout);
        Pane1Layout.setHorizontalGroup(
            Pane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pane1Layout.createSequentialGroup()
                .addContainerGap(778, Short.MAX_VALUE)
                .addGroup(Pane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        Pane1Layout.setVerticalGroup(
            Pane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(26, 26, 26))
        );

        jMenuBar1.setPreferredSize(new java.awt.Dimension(56, 35));

        me.setText("TRABAJOS");
        me.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        me.setMargin(new java.awt.Insets(5, 25, 5, 25));

        nuevotrabajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        nuevotrabajo.setText("NUEVO TRABAJO");
        nuevotrabajo.setEnabled(false);
        nuevotrabajo.setMargin(new java.awt.Insets(5, 5, 5, 5));
        nuevotrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevotrabajoActionPerformed(evt);
            }
        });
        me.add(nuevotrabajo);

        EDITARCANTIDAD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit2.png"))); // NOI18N
        EDITARCANTIDAD.setText("EDITAR CANTIDAD DE LOTE");
        EDITARCANTIDAD.setEnabled(false);
        EDITARCANTIDAD.setMargin(new java.awt.Insets(5, 5, 5, 5));
        EDITARCANTIDAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDITARCANTIDADActionPerformed(evt);
            }
        });
        me.add(EDITARCANTIDAD);

        iniciartrabajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1481834876_tick_16.png"))); // NOI18N
        iniciartrabajo.setText("INICIAR TRABAJO");
        iniciartrabajo.setEnabled(false);
        iniciartrabajo.setMargin(new java.awt.Insets(5, 5, 5, 5));
        iniciartrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciartrabajoActionPerformed(evt);
            }
        });
        me.add(iniciartrabajo);

        procesos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ajustes.png"))); // NOI18N
        procesos.setText("PROCESOS");
        procesos.setEnabled(false);
        procesos.setMargin(new java.awt.Insets(5, 5, 5, 5));
        procesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesosActionPerformed(evt);
            }
        });
        me.add(procesos);

        trabajosdepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/List.png"))); // NOI18N
        trabajosdepto.setText("TRABAJOS POR DEPTO");
        trabajosdepto.setEnabled(false);
        trabajosdepto.setMargin(new java.awt.Insets(5, 5, 5, 5));
        trabajosdepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trabajosdeptoActionPerformed(evt);
            }
        });
        me.add(trabajosdepto);

        cerrartrabajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        cerrartrabajo.setText("CERRAR TRABAJO");
        cerrartrabajo.setEnabled(false);
        cerrartrabajo.setMargin(new java.awt.Insets(5, 5, 5, 5));
        cerrartrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrartrabajoActionPerformed(evt);
            }
        });
        me.add(cerrartrabajo);

        Prioridad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Problem.png"))); // NOI18N
        Prioridad.setText("PRIORIDAD DE PRODUCCION");
        Prioridad.setEnabled(false);
        Prioridad.setMargin(new java.awt.Insets(5, 5, 5, 5));
        Prioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrioridadActionPerformed(evt);
            }
        });
        me.add(Prioridad);

        jMenuItem21.setText("VENCIMIENTO DE TRABAJOS");
        jMenuItem21.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        me.add(jMenuItem21);

        jMenuBar1.add(me);

        jMenu6.setText("EJEMPLOS");
        jMenu6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu6.setMargin(new java.awt.Insets(5, 25, 5, 25));

        nuevoejemplo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        nuevoejemplo.setText("NUEVO EJEMPLO");
        nuevoejemplo.setEnabled(false);
        nuevoejemplo.setMargin(new java.awt.Insets(5, 5, 5, 5));
        nuevoejemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoejemploActionPerformed(evt);
            }
        });
        jMenu6.add(nuevoejemplo);

        procesoejemplo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ajustes.png"))); // NOI18N
        procesoejemplo.setText("PROCESO EJEMPLO");
        procesoejemplo.setEnabled(false);
        procesoejemplo.setMargin(new java.awt.Insets(5, 5, 5, 5));
        procesoejemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesoejemploActionPerformed(evt);
            }
        });
        jMenu6.add(procesoejemplo);

        aprobacionejemplo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1481834876_tick_16.png"))); // NOI18N
        aprobacionejemplo.setText("APROBACION DE EJEMPLO");
        aprobacionejemplo.setEnabled(false);
        aprobacionejemplo.setMargin(new java.awt.Insets(5, 5, 5, 5));
        aprobacionejemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprobacionejemploActionPerformed(evt);
            }
        });
        jMenu6.add(aprobacionejemplo);

        PRIORIDADEJE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Problem.png"))); // NOI18N
        PRIORIDADEJE.setText("PRIORIDAD DE PRODUCCION");
        PRIORIDADEJE.setEnabled(false);
        PRIORIDADEJE.setMargin(new java.awt.Insets(5, 5, 5, 5));
        PRIORIDADEJE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRIORIDADEJEActionPerformed(evt);
            }
        });
        jMenu6.add(PRIORIDADEJE);

        jMenuBar1.add(jMenu6);

        jMenu1.setText("ENVIOS");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu1.setMargin(new java.awt.Insets(5, 25, 5, 25));

        listaEnvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/List.png"))); // NOI18N
        listaEnvio.setText("LISTA DE ENVIOS");
        listaEnvio.setMargin(new java.awt.Insets(5, 5, 5, 5));
        listaEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaEnvioActionPerformed(evt);
            }
        });
        jMenu1.add(listaEnvio);

        crearenvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Application.png"))); // NOI18N
        crearenvio.setText("CREAR,EDITAR Y CERRAR ENVIO");
        crearenvio.setEnabled(false);
        crearenvio.setMargin(new java.awt.Insets(5, 5, 5, 5));
        crearenvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearenvioActionPerformed(evt);
            }
        });
        jMenu1.add(crearenvio);

        Menumodificarenvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit2.png"))); // NOI18N
        Menumodificarenvio.setText("MODIFICAR ENVIO");
        Menumodificarenvio.setEnabled(false);
        Menumodificarenvio.setMargin(new java.awt.Insets(5, 5, 5, 5));

        modificarenvio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Modify.png"))); // NOI18N
        modificarenvio.setText("AGREGAR O ELIMINAR P/N");
        modificarenvio.setMargin(new java.awt.Insets(5, 5, 5, 5));
        modificarenvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarenvioActionPerformed(evt);
            }
        });
        Menumodificarenvio.add(modificarenvio);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit2.png"))); // NOI18N
        jMenuItem14.setText("MODIFICAR FECHA DE ENVIO DE P/N");
        jMenuItem14.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        Menumodificarenvio.add(jMenuItem14);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit2.png"))); // NOI18N
        jMenuItem15.setText("MODIFICAR CANTIDADES");
        jMenuItem15.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        Menumodificarenvio.add(jMenuItem15);

        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        jMenuItem16.setText("AGREGAR P/N QUE NO ESTA EN PROCESOS");
        jMenuItem16.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        Menumodificarenvio.add(jMenuItem16);

        jMenu1.add(Menumodificarenvio);

        sn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Info.png"))); // NOI18N
        sn.setText("S/N");
        sn.setEnabled(false);
        sn.setMargin(new java.awt.Insets(5, 5, 5, 5));
        sn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snActionPerformed(evt);
            }
        });
        jMenu1.add(sn);

        datecode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Calendar.png"))); // NOI18N
        datecode.setText("DATE CODE / FECHA PARA DATA");
        datecode.setEnabled(false);
        datecode.setMargin(new java.awt.Insets(5, 5, 5, 5));
        datecode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datecodeActionPerformed(evt);
            }
        });
        jMenu1.add(datecode);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Folder.png"))); // NOI18N
        jMenuItem13.setText("HISTORIAL DE ENVIOS");
        jMenuItem13.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("HISTORIAL");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu4.setHideActionText(true);
        jMenu4.setMargin(new java.awt.Insets(5, 25, 5, 25));

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Report.png"))); // NOI18N
        jMenuItem4.setText("TRABAJOS CERRADOS");
        jMenuItem4.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/OK.png"))); // NOI18N
        jMenuItem1.setText("EJEMPLOS APROBADOS");
        jMenuItem1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("FOTOS");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu2.setMargin(new java.awt.Insets(5, 25, 5, 25));

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camera-icon.png"))); // NOI18N
        jMenuItem6.setText("FOTOS");
        jMenuItem6.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("DRAWINGS");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu3.setMargin(new java.awt.Insets(5, 25, 5, 25));

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        jMenuItem7.setText("BUSCAR");
        jMenuItem7.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        TALLER.setText("SOLICITUDES DE MATERIALES");
        TALLER.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TALLER.setMargin(new java.awt.Insets(5, 25, 5, 25));

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Create.png"))); // NOI18N
        jMenu8.setText("SOLICITUDES A TALLER");
        jMenu8.setMargin(new java.awt.Insets(10, 5, 10, 5));

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        jMenuItem2.setText("INGRESO SOLICITUD");
        jMenuItem2.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Hint.png"))); // NOI18N
        jMenuItem3.setText("CONSULTA ESTADO DE SOLICITUDES");
        jMenuItem3.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Folder.png"))); // NOI18N
        jMenuItem8.setText("HISTORIAL SOLICITUDES");
        jMenuItem8.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem8);

        taller.setText("REPORTES TALLER");
        taller.setEnabled(false);
        taller.setMargin(new java.awt.Insets(5, 5, 5, 5));

        jMenuItem9.setText("LISTADO DE SOLICITUDES ");
        jMenuItem9.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        taller.add(jMenuItem9);

        jMenuItem10.setText("ACTUALIZAR ESTADO DE SOLICITUDES");
        jMenuItem10.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        taller.add(jMenuItem10);

        jMenuItem11.setText("ENTREGA DE PRODUCTOS");
        jMenuItem11.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem11.setName(""); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        taller.add(jMenuItem11);

        jMenuItem12.setText("PRODUCTOS TALLER");
        jMenuItem12.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        taller.add(jMenuItem12);

        jMenu8.add(taller);

        TALLER.add(jMenu8);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Create.png"))); // NOI18N
        jMenu9.setText("SOLICITUDES A BODEGA");
        jMenu9.setMargin(new java.awt.Insets(10, 5, 10, 5));

        ingresoSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        ingresoSolicitud.setText("INGRESO SOLICITUD ");
        ingresoSolicitud.setEnabled(false);
        ingresoSolicitud.setMargin(new java.awt.Insets(5, 5, 5, 5));
        ingresoSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresoSolicitudActionPerformed(evt);
            }
        });
        jMenu9.add(ingresoSolicitud);

        entregaSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Downloads folder.png"))); // NOI18N
        entregaSolicitud.setText("ENTREGA DE MATERIAL");
        entregaSolicitud.setEnabled(false);
        entregaSolicitud.setMargin(new java.awt.Insets(5, 5, 5, 5));
        entregaSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entregaSolicitudActionPerformed(evt);
            }
        });
        jMenu9.add(entregaSolicitud);

        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Folder.png"))); // NOI18N
        jMenuItem22.setText("HISTORIAL ENTREGAS");
        jMenuItem22.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem22);

        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/Diagram.png"))); // NOI18N
        jMenuItem20.setText("INDICADOR BODEGA");
        jMenuItem20.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem20);

        TALLER.add(jMenu9);

        jMenuBar1.add(TALLER);

        jMenu7.setText("CONTROL DE CAMBIOS");
        jMenu7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu7.setMargin(new java.awt.Insets(5, 25, 5, 25));

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New.png"))); // NOI18N
        jMenuItem18.setText("NUEVO CAMBIO");
        jMenuItem18.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem18);

        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ComponenteImagenes/How-to.png"))); // NOI18N
        jMenuItem19.setText("HISTORIAL");
        jMenuItem19.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem19);

        jMenuBar1.add(jMenu7);

        jMenu5.setForeground(new java.awt.Color(255, 0, 0));
        jMenu5.setText("SALIR");
        jMenu5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jMenu5.setMargin(new java.awt.Insets(5, 25, 5, 25));
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        jMenuItem5.setText("SALIR");
        jMenuItem5.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pane1)
        );

        getAccessibleContext().setAccessibleName("PRODUCCION TRANSFORMADORES");

        pack();
    }// </editor-fold>//GEN-END:initComponents
       
    
    
    public void vencer(){
    
        TrabajosVencimiento tra = new TrabajosVencimiento();
              Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
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
        else if(a.equals("taller")){
            depto = 10;
        }//TALLER
       else if(a.equals("Sotano")){
            depto = 10;
        }//TALLER*/
        
       /* else if(a.equals("Sotano")){
            depto = 13;
            a = "Molding";
        }//TALLER*/
        
        else if(a.equals("apacheco")){
            depto = 11;
        }
        else if(a.equals("deptochips")){
            depto = 12;            
        }
        else if(a.equals("molding")){
            depto = 13;
        }
        else if(a.equals("marking")){
        depto = 2;
        }
        jLabel2.setText(a);
    }

    private void nuevotrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevotrabajoActionPerformed
        EjemplosAprobadosParaTrabajos tra = new EjemplosAprobadosParaTrabajos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_nuevotrabajoActionPerformed

    private void procesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesosActionPerformed
        
        if(depto == 9){
        ProcesosTransCombo tra = new ProcesosTransCombo();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        }else
        {
        ProcesosTrans tra = new ProcesosTrans();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        }
    }//GEN-LAST:event_procesosActionPerformed

    private void cerrartrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrartrabajoActionPerformed
        CerrarTrabajo tra = new CerrarTrabajo();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_cerrartrabajoActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Historial tra = new Historial();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed


    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("delete from pedidos_trabajos where  depto = "+depto+" and estado = 0");
    ps.executeUpdate();
    cnn.close();
    ps.close();
      } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }   
        System.exit(1);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Fotos tra = new Fotos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Drawings tra = new Drawings();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void iniciartrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciartrabajoActionPerformed
        IniciarTrabajoTrans tra = new IniciarTrabajoTrans();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_iniciartrabajoActionPerformed

    private void nuevoejemploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoejemploActionPerformed
        IngresoEjemplos tra = new IngresoEjemplos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_nuevoejemploActionPerformed

    private void procesoejemploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesoejemploActionPerformed
        if(depto==9){
            RecoridosEjemplosCombo tra = new RecoridosEjemplosCombo();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        }else{
        RecoridosEjemplos tra = new RecoridosEjemplos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        }
    }//GEN-LAST:event_procesoejemploActionPerformed

    private void aprobacionejemploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprobacionejemploActionPerformed
        AprobacionEjemplos tra = new AprobacionEjemplos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_aprobacionejemploActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        HistorialEjemplos tra = new HistorialEjemplos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void EDITARCANTIDADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDITARCANTIDADActionPerformed
        
        EditarCantidadLotes tra = new EditarCantidadLotes();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_EDITARCANTIDADActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        HistorialStatus tra = new HistorialStatus();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        InicioSolicItudesProductosAtaller tra = new InicioSolicItudesProductosAtaller();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        STATUS tra = new STATUS();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
    Connection cnn = BD.getConnection();
    PreparedStatement ps = null;
    ps= cnn.prepareStatement("delete from pedidos_trabajos where  depto = "+depto+" and estado = 0");
    ps.executeUpdate();
    cnn.close();
    ps.close();
      } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
       // JOptionPane.showConfirmDialog(null, "Se cancelon el pedido que no se confirmo");
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        ActualizarStatus tra = new ActualizarStatus();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        EntregaSolicitud tra = new EntregaSolicitud();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       ConsultadePedidos tra = new ConsultadePedidos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        ConsultadeProductos tra = new ConsultadeProductos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void PrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrioridadActionPerformed
        PrioridadProduccion tra = new PrioridadProduccion();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_PrioridadActionPerformed

    private void PRIORIDADEJEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRIORIDADEJEActionPerformed
        PrioridadProduccionEjemplos tra = new PrioridadProduccionEjemplos();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_PRIORIDADEJEActionPerformed

    private void trabajosdeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trabajosdeptoActionPerformed
       
        TrabajosDepartamento tra = new TrabajosDepartamento();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        
    }//GEN-LAST:event_trabajosdeptoActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
       
        ControlCambios tra = new ControlCambios();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        
        HistorialCambios tra = new HistorialCambios();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void ingresoSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresoSolicitudActionPerformed
        SolicitudesBodega tra = new SolicitudesBodega();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_ingresoSolicitudActionPerformed

    private void entregaSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entregaSolicitudActionPerformed
        EntregasBodega tra = new EntregasBodega();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_entregaSolicitudActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        HistorialEntregas tra = new HistorialEntregas();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
       
        CalculoIndicador tra = new CalculoIndicador();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
        
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
       vencer();
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void listaEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaEnvioActionPerformed
        ListadoEnvios tra = new ListadoEnvios();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_listaEnvioActionPerformed

    private void modificarenvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarenvioActionPerformed
        EditarEnvio tra = new EditarEnvio();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_modificarenvioActionPerformed

    private void crearenvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearenvioActionPerformed
        NuevoEnvio tra = new NuevoEnvio();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_crearenvioActionPerformed

    private void datecodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datecodeActionPerformed
        DateCode tra = new DateCode();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_datecodeActionPerformed

    private void snActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snActionPerformed
       SN tra = new SN();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_snActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
      Historialenvios tra = new Historialenvios();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
         CambiarPNenvio tra = new CambiarPNenvio();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        EditarCantidad tra = new EditarCantidad();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        AgregarPNquenoestaenprocesosTransformer tra = new AgregarPNquenoestaenprocesosTransformer();
        Pane1.add(tra);
        Dimension desktopSize = Pane1.getSize();
        Dimension FrameSize = tra.getSize();
        tra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        tra.show();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem EDITARCANTIDAD;
    private javax.swing.JMenu Menumodificarenvio;
    private javax.swing.JMenuItem PRIORIDADEJE;
    public static javax.swing.JDesktopPane Pane1;
    private javax.swing.JMenuItem Prioridad;
    private javax.swing.JMenu TALLER;
    private javax.swing.JMenuItem aprobacionejemplo;
    private javax.swing.JMenuItem cerrartrabajo;
    private javax.swing.JMenuItem crearenvio;
    private javax.swing.JMenuItem datecode;
    private javax.swing.JMenuItem entregaSolicitud;
    private javax.swing.JMenuItem ingresoSolicitud;
    private javax.swing.JMenuItem iniciartrabajo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem listaEnvio;
    private javax.swing.JMenu me;
    private javax.swing.JMenuItem modificarenvio;
    private javax.swing.JMenuItem nuevoejemplo;
    private javax.swing.JMenuItem nuevotrabajo;
    private javax.swing.JMenuItem procesoejemplo;
    private javax.swing.JMenuItem procesos;
    private javax.swing.JMenuItem sn;
    private javax.swing.JMenu taller;
    private javax.swing.JMenuItem trabajosdepto;
    // End of variables declaration//GEN-END:variables
}
