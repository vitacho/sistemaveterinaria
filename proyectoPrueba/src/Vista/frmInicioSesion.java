/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CuentaDB;
import Controlador.PersonaDB;
import Modelo.Cuenta;
import Controlador.Validaciones;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Personal
 */
public class frmInicioSesion extends javax.swing.JDialog {

    /**
     * Creates new form frmInicioSesion
     */
    CuentaDB cueDB = new CuentaDB();
    PersonaDB persoDB = new PersonaDB();
    Validaciones val = new Validaciones();

    String tipo = "";

    public frmInicioSesion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtcontra = new javax.swing.JPasswordField();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Usuario:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 80, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Contraseña:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 120, 40));

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 190, 30));

        btnIngresar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 90, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("x");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 80, 30));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Constantia", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INICIO DE SESIÓN");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 260, 50));

        txtcontra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcontra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraActionPerformed(evt);
            }
        });
        getContentPane().add(txtcontra, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 190, 30));

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 90, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FONDOP1.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-230, -140, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcontraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraActionPerformed
     
    }//GEN-LAST:event_txtcontraActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        /**
         * Botón Ingresar
         */

       // necesito lo de cuenta y persona
       
//        List<Cuenta> listCuenta = new java.util.ArrayList<>();
//       
//        Cuenta c = new Cuenta();
////        listCuenta = cueDB.traeUsuarios("A", listCuenta);
//      
//        if (listCuenta.size() > 0) {
//            c = cueDB.traeCuentaUsuario(txtUsuario.getText());
            
//            if (txtUsuario.getText().equals("")||txtcontra.getText().equals("")) {
//                JOptionPane.showMessageDialog(this, "ACCESO INCORRECTO", "Mensaje", JOptionPane.ERROR_MESSAGE);
//                txtcontra.setText(null); txtUsuario.setText(null); txtUsuario.requestFocus();
//            }
//
//            if (c.getPersona().getCedula().equals(txtUsuario.getText())
//                && c.getClave().equals(txtcontra.getText())) {
//                tipo = c.getPersona().getRol().getNombre();
//                JOptionPane.showMessageDialog(this, "Acceso Tipo: " + tipo, "ACCESO AL SISTEMA", JOptionPane.INFORMATION_MESSAGE);

                //AQUI ENVIAR EL FORMULARIO

                //frmPrincipal pricipal = new frmPrincipal(c.getPersona().getRol().getId());
                //pricipal.setTitle("Menú Principal");
                //pricipal.setVisible(true);
//           
//                this.dispose();
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "NO EXISTEN USUARIOS REGISTRADOS "
//                + "EN EL SISTEMA ACTUALMENTE", "MENSAJE", JOptionPane.WARNING_MESSAGE);
//        }

    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        txtUsuario.setText(null);
        txtcontra.setText(null);
        txtUsuario.requestFocus();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  System.exit(0);    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
  val.EnterAJtexFiel(txtcontra, evt);    }//GEN-LAST:event_txtUsuarioKeyTyped

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
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmInicioSesion dialog = new frmInicioSesion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JPasswordField txtcontra;
    // End of variables declaration//GEN-END:variables
}
