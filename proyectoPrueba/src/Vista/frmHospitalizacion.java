/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

//import Controlador.controladorConsulta;
//import Controlador.controladorHospitalizacion;
//import Controlador.MascotaDB;
import Controlador.HospitalizacionDB;
import Controlador.PersonaDB;
import Controlador.Validaciones;
import Modelo.Cuenta;
import Modelo.Hospitalizacion;
import Modelo.Mascota;
//import Modelo.Hospitalizacion;
//import Modelo.Mascota;
import Modelo.Persona;
import Modelo.Rol;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Personal
 */
public class frmHospitalizacion extends javax.swing.JDialog {

    /**
     * Creates new form frmDetalleListaHospitalizacion
     */
    HospitalizacionDB hosDB = new HospitalizacionDB();
    Validaciones validar = new Validaciones();
    PersonaDB perDB = new PersonaDB();
    Mascota mas = null;
    Hospitalizacion veriHosp;
    boolean veri;

    public frmHospitalizacion(java.awt.Frame parent, boolean modal, boolean vrf, Hospitalizacion hosp) {
        super(parent, modal);
        initComponents();
        veri = vrf;
        veriHosp = hosp;
        iniciar();
    }

    private void iniciar() {
        jTextFieldNombreM.setEditable(false);
        jTextFieldCI.setEditable(false);
        jTextFieldSexM.setEditable(false);
        if (veri == true) {
            jLabelTitulo.setText("VER HOSPITALIZACIÓN");
            jButtonGuardar.setVisible(false);
            jLabelDue.setVisible(false);
            jTextFieldCedu.setVisible(false);

            jTextFieldCI.setText(veriHosp.getMascota().getPersona().getCedula());
            jTextFieldNombreM.setText(veriHosp.getMascota().getNombre());
            jTextFieldSexM.setText(veriHosp.getMascota().getSexo());

            jButtonBuscar.setVisible(false);
            jTextFieldVeterinario.setEditable(false);
            jTextAreaMotivo.setEditable(false);
            jComboBoxSangrado.setEnabled(false);
            jComboBoxHincha.setEnabled(false);
            jComboBoxintox.setEnabled(false);
            jComboBoxRespira.setEnabled(false);
            jComboBoxConvulcion.setEnabled(false);
            jTextFieldTem.setEditable(false);
            jTextAreaDiagnos.setEditable(false);
            jTextFieldVeterinario.setText(veriHosp.getVereterinario());
            jTextAreaMotivo.setText(veriHosp.getMotivo());
            jComboBoxSangrado.setSelectedItem(veriHosp.getSangrado());
            jComboBoxHincha.setSelectedItem(veriHosp.getHinchazon());
            jComboBoxintox.setSelectedItem(veriHosp.getIntoxicacion());
            jComboBoxRespira.setSelectedItem(veriHosp.getProbleResp());
            jComboBoxConvulcion.setSelectedItem(veriHosp.getConbulcion());
            jTextFieldTem.setText(Integer.toString(veriHosp.getTemp()));
            jTextAreaDiagnos.setText(veriHosp.getDiagnostico());
            jTextFieldVeterinario.setText(veriHosp.getVereterinario());
        }
    }

    private void buscarCliente(String cedula) {
        if (validar.esNumerico(cedula) == true) {

            mas = validar.buscarMascota(cedula, perDB);

            jTextFieldCI.setText(mas.getPersona().getCedula());
            jTextFieldNombreM.setText(mas.getNombre());
            jTextFieldSexM.setText(mas.getSexo());

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }
    }

    private void registrarHosp() {
        if (!jTextFieldCI.getText().equals("")) {
            if (!jTextFieldVeterinario.getText().equals("")) {
                if (validar.esNumerico(jTextFieldCedu.getText()) == true && validar.esNumerico(jTextFieldTem.getText()) == true) {
                    if (!jTextAreaMotivo.getText().equals("") && !jTextFieldTem.getText().equals("") && !jTextAreaDiagnos.getText().equals("")) {
                        if (!jTextFieldCI.getText().equals("") || !jTextFieldNombreM.getText().equals("")) {
                            java.util.Date fecha = new Date();
                            Hospitalizacion hos = new Hospitalizacion();
                            hos.setMascota(mas);
                            hos.setVereterinario(jTextFieldVeterinario.getText());
                            hos.setIngreso(fecha);
                            hos.setMotivo(jTextAreaMotivo.getText());
                            hos.setTemp(Integer.parseInt(jTextFieldTem.getText()));
                            hos.setDiagnostico(jTextAreaDiagnos.getText());
                            hos.setSangrado(jComboBoxSangrado.getSelectedItem().toString());
                            hos.setHinchazon(jComboBoxHincha.getSelectedItem().toString());
                            hos.setIntoxicacion(jComboBoxintox.getSelectedItem().toString());
                            hos.setProbleResp(jComboBoxRespira.getSelectedItem().toString());
                            hos.setConbulcion(jComboBoxConvulcion.getSelectedItem().toString());
                            hos.setEstado("Hospitalizado");

                            hosDB.nuevaHospitalizacion(hos);

                            JOptionPane.showMessageDialog(null, "Hospitalizacion Guardada");

                        } else {
                            JOptionPane.showMessageDialog(null, "Falta el cliente o mascota");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Llene todos los datos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El nombre el veterinario es obligatorio");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Es nesesario un cliente");
        }
    }

    private void bloquearCampos() {
        jTextFieldCedu.setEditable(false);
        jTextFieldVeterinario.setEditable(false);
        jTextAreaMotivo.setEditable(false);
        jComboBoxSangrado.setEnabled(false);
        jComboBoxHincha.setEnabled(false);
        jComboBoxintox.setEnabled(false);
        jComboBoxRespira.setEnabled(false);
        jComboBoxConvulcion.setEnabled(false);
        jTextFieldTem.setEditable(false);
        jTextAreaDiagnos.setEditable(false);
        jButtonBuscar.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaMotivo = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldTem = new javax.swing.JTextField();
        jComboBoxHincha = new javax.swing.JComboBox<>();
        jComboBoxintox = new javax.swing.JComboBox<>();
        jComboBoxRespira = new javax.swing.JComboBox<>();
        jComboBoxSangrado = new javax.swing.JComboBox<>();
        jComboBoxConvulcion = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDiagnos = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabelDue = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldVeterinario = new javax.swing.JTextField();
        jLabelTitulo = new javax.swing.JLabel();
        jTextFieldNombreM = new javax.swing.JTextField();
        jTextFieldCedu = new javax.swing.JTextField();
        jButtonGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldCI = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldSexM = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setOpaque(false);

        jTextAreaMotivo.setColumns(20);
        jTextAreaMotivo.setRows(5);
        jScrollPane3.setViewportView(jTextAreaMotivo);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Motivo:");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 191, -1, -1));
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 61, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Problemas Respiratorios:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Hinchazón:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Temperatura:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Sangrado:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Intoxicación:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Convulsiones:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        jTextFieldTem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTemActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldTem, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 140, 30));

        jComboBoxHincha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        jPanel3.add(jComboBoxHincha, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 120, 30));

        jComboBoxintox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        jComboBoxintox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxintoxActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBoxintox, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 130, 30));

        jComboBoxRespira.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        jPanel3.add(jComboBoxRespira, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 150, 30));

        jComboBoxSangrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        jPanel3.add(jComboBoxSangrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 120, 30));

        jComboBoxConvulcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        jPanel3.add(jComboBoxConvulcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 120, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 1050, -1));

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Diagnóstico:");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jTextAreaDiagnos.setColumns(20);
        jTextAreaDiagnos.setLineWrap(true);
        jTextAreaDiagnos.setRows(5);
        jTextAreaDiagnos.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaDiagnos);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 940, 110));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 1070, 150));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Nombre de la Mascota:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jLabelDue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDue.setText("Dueño de la Mascota:");
        getContentPane().add(jLabelDue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 117, 140, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Veterinario:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        jTextFieldVeterinario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldVeterinarioActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldVeterinario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 260, 30));

        jLabelTitulo.setFont(new java.awt.Font("Constantia", 1, 36)); // NOI18N
        jLabelTitulo.setText("REGISTRAR HOSPITALIZACIÓN");
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 754, -1));
        getContentPane().add(jTextFieldNombreM, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 260, 30));
        getContentPane().add(jTextFieldCedu, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 260, 30));

        jButtonGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 720, 90, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Atras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 720, 90, 30));

        jButtonBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search.png"))); // NOI18N
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Sexo de la mascota:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, -1, 20));
        getContentPane().add(jTextFieldCI, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 300, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("CI:");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 90, -1));
        getContentPane().add(jTextFieldSexM, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, 300, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FONDOP1.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1970, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (veri == false) {
            frmListaHospitalización frmlis = new frmListaHospitalización(new javax.swing.JFrame(), false);
            this.setVisible(false);
            frmlis.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:

        buscarCliente(jTextFieldCedu.getText());

    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jTextFieldVeterinarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldVeterinarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldVeterinarioActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
        int op = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea guardar?", "Selecciona una opcion", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            registrarHosp();
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jComboBoxintoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxintoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxintoxActionPerformed

    private void jTextFieldTemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTemActionPerformed

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
            java.util.logging.Logger.getLogger(frmHospitalizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHospitalizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHospitalizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHospitalizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmHospitalizacion dialog = new frmHospitalizacion(new javax.swing.JFrame(), true, false, new Hospitalizacion());
                //new Hospitalizacion()
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JComboBox<String> jComboBoxConvulcion;
    private javax.swing.JComboBox<String> jComboBoxHincha;
    private javax.swing.JComboBox<String> jComboBoxRespira;
    private javax.swing.JComboBox<String> jComboBoxSangrado;
    private javax.swing.JComboBox<String> jComboBoxintox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelDue;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaDiagnos;
    private javax.swing.JTextArea jTextAreaMotivo;
    private javax.swing.JTextField jTextFieldCI;
    private javax.swing.JTextField jTextFieldCedu;
    private javax.swing.JTextField jTextFieldNombreM;
    private javax.swing.JTextField jTextFieldSexM;
    private javax.swing.JTextField jTextFieldTem;
    private javax.swing.JTextField jTextFieldVeterinario;
    // End of variables declaration//GEN-END:variables
}
