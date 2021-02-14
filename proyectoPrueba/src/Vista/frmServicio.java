/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

//import Controlador.ServicioDB;
import Controlador.ServicioDB;
import Controlador.Validaciones;
import Modelo.Servicio;
//import Modelo.Servicio;
//import controlador.validaciones;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Personal
 */
public class frmServicio extends javax.swing.JDialog {
   ServicioDB servicioDB  = new ServicioDB();
      DefaultTableModel model = new DefaultTableModel();
    Validaciones val = new Validaciones();
    
    /**
     * Creates new form ListaServicio
     */
    public frmServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicio();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtCosto = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtId = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setText(" SERVICIOS  ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 310, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Buscar");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 90, 30));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nombre: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 32, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Descripción:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 108, -1, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 32, 133, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Costo:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 70, -1, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 108, 420, 151));

        txtCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 70, 133, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, -1, -1));

        btnEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 630, 98, -1));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Salir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 630, 88, -1));

        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, 98, -1));

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE", "COSTO", "ESTADO"
            }
        ));
        tablaServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaServiciosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaServicios);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 470, 130));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, -1, -1));

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 630, 98, -1));

        txtId.setText("txtId");
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FONDOP1.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void txtCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoActionPerformed

    private void tablaServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServiciosMouseClicked
 int selectRow = tablaServicios.getSelectedRow();
//        int idServ = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
//        Servicio serv = servicioDB.traeServicio(idServ);
//        txtNombre.setText(serv.getNombre_servicio());
//        txtDescripcion.setText(serv.getDescripcion());
//        //falta

        Bloquear(true);    }//GEN-LAST:event_tablaServiciosMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
guardar();    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 this.dispose();    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
 Editar();    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
  tablaServicios.setEnabled(true);
        activa_Desac_Panel(false);
        inicio();    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        tablaServicios.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        activa_Desac_Panel(true);
        txtNombre.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed
     private void Editar() {
        activa_Desac_Panel(true);
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnGuardar.setText("Actualizar");
        btnEditar.setEnabled(false);

    }
    
    private void Bloquear(boolean flag) {

        if (flag == true) {
            btnEditar.setEnabled(true);
            btnNuevo.setEnabled(false);
            txtNombre.setEnabled(false);
            txtDescripcion.setEnabled(false);

            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(true);
        }
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
            java.util.logging.Logger.getLogger(frmServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmServicio dialog = new frmServicio(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void inicio() {
        btnNuevo.setEnabled(true);
        btnGuardar.setText("Guardar");
        btnEditar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(true);
        tablaModel();
        llenarTablaServico("A");
        activa_Desac_Panel(false);
        tablaServicios.setEnabled(true);    }
    
     private void tablaModel() {
        tablaServicios.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaServicios.getColumnModel().getColumn(0).setMinWidth(0);
        tablaServicios.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaServicios.getColumnModel().getColumn(1).setMaxWidth(230);
        tablaServicios.getColumnModel().getColumn(2).setMaxWidth(230);
        model = (DefaultTableModel) tablaServicios.getModel();
        model.setNumRows(0);
    }
     private void llenarTablaServico(String estado) {
//        tablaModel();
//        List<Servicio> listaServ = null;
//        listaServ = servicioDB .cargaServicio(estado, listaServ);
//
//        for (Servicio lista : listaServ) {
//            model.addRow(new Object[]{
//                lista.getCodigo_serv(),lista.getNombre_servicio(),lista.getPrecio()
//            });
//        }
    }
      private void activa_Desac_Panel(Boolean flag) {

        txtNombre.setEnabled(flag);
        txtDescripcion.setEnabled(flag);
        txtCosto.setEnabled(flag);

        if (flag == false) {
            txtNombre.setText(null);
            txtNombre.setText(null);
             txtCosto.setText(null);
        }
    }
      
     private void guardar() {
   Servicio serv = null;

        if (btnGuardar.getText().equals("Guardar")) {
            serv = servicioDB.traeNombreServicio(txtNombre.getText());

            if (serv == null) {
                if (ValidarCampos() == true) {
                    serv = new Servicio();
                    serv.setNombre_serv(txtNombre.getText());
                    serv.setDesc_serv(txtDescripcion.getText());
                    serv.setEstado_serv("A");
                    serv.setCantidad_serv(1);
                     serv.setPrecio_serv(Double.parseDouble(txtCosto.getText())  );
                    servicioDB.nuevoServicio(serv);
                     inicio();
                } else {
                    JOptionPane.showMessageDialog(null, "LLENAR CAMPOS REQUERIDOS", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    activa_Desac_Panel(false);
                    inicio();
                }
            } else {
                JOptionPane.showMessageDialog(null, "EL NOMBRE DE LA ESPECIALIDAD YA EXISTE EN EL SISTEMA", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            if (btnGuardar.getText().equals("Actualizar")) {
                if (ValidarCampos() == true) {
                    
                    int idServicio = Integer.parseInt(txtId.getText());
                    serv = servicioDB.traeServicioId(idServicio);
                    int selectRow = tablaServicios.getSelectedRow();
                    int idEspecial = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
                    serv= servicioDB.traeServicio(idEspecial);
                   
                    serv.setNombre_serv(txtNombre.getText());
                    serv.setDesc_serv(txtDescripcion.getText());
                    serv.setEstado_serv("A");
                    serv.setPrecio_serv(Double.parseDouble(txtCosto.getText())  );                    //falta float
                    servicioDB.actualizaServicio(serv);

                    JOptionPane.showMessageDialog(null, "DATOS ACTUALIZADOS", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    activa_Desac_Panel(false);

                } else {
                    JOptionPane.showMessageDialog(null, "LLENAR CAMPOS REQUERIDOS", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    activa_Desac_Panel(false);
                    inicio();
                }
            }
        }
        inicio();

    }
      private boolean ValidarCampos() {
        boolean lleno = true;

        if (txtNombre.getText().equals("") || txtDescripcion.getText().equals("")||txtCosto.getText().equals("")) {
            lleno = false;
        } else {
            lleno = true;
        }
        return lleno;
    }
}

