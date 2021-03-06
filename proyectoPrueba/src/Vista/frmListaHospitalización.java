/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

//import Modelo.Hospitalizacion;
import Controlador.HospitalizacionDB;
import Controlador.PersonaDB;
import Controlador.Validaciones;
import Modelo.Hospitalizacion;
import Modelo.Persona;
//import static Vista.frmHospitalizacion.ch;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Personal
 */
public class frmListaHospitalización extends javax.swing.JDialog {

    /**
     * Creates new form frmListaHospitalización
     */
    DefaultTableModel model;
    Validaciones validar = new Validaciones();
    HospitalizacionDB hosDB = new HospitalizacionDB();
    PersonaDB perDB = new PersonaDB();
    DefaultTableModel modelConsulta;

    public frmListaHospitalización(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTextFieldBuscar.setToolTipText("Ingrese el nombre o la cedula del dueño de la mascota. ");
        iniciar();
    }

    private void iniciar() {
        llenarTabla();
    }

    private void llenarTabla() {
        tableModel();
        List<Hospitalizacion> lista = null;
        lista = hosDB.cargarHospitalizacion(lista);
        for (Hospitalizacion hosp : lista) {
            model.addRow(new Object[]{hosp.getId(), hosp.getMascota().getNombre(),
                hosp.getMascota().getPersona().getCedula(),
                hosp.getMascota().getPersona().getNombre() + " " + hosp.getMascota().getPersona().getApellido(),
                hosp.getVereterinario(), hosp.getEstado(), hosp.getIngreso(), hosp.getSalida()});
        }
    }

    private void tableModel() {

        jTableHospi.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableHospi.getColumnModel().getColumn(0).setMinWidth(0);
        jTableHospi.getColumnModel().getColumn(0).setPreferredWidth(0);

        jTableHospi.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTableHospi.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTableHospi.getColumnModel().getColumn(3).setPreferredWidth(300);
        jTableHospi.getColumnModel().getColumn(4).setPreferredWidth(300);
        jTableHospi.getColumnModel().getColumn(5).setPreferredWidth(300);
        jTableHospi.getColumnModel().getColumn(6).setPreferredWidth(300);
        jTableHospi.getColumnModel().getColumn(7).setPreferredWidth(300);

        model = (DefaultTableModel) jTableHospi.getModel();
        model.setNumRows(0);
    }

    private void buscarHosp(String busqueda) {
        model.setNumRows(0);
        List<Hospitalizacion> busca = new ArrayList<>();
        List<Hospitalizacion> lista = null;
        lista = hosDB.cargarHospitalizacion(lista);
        for (int i = 0; i < lista.size(); i++) {
            String nom = lista.get(i).getMascota().getPersona().getNombre() + " " + lista.get(i).getMascota().getPersona().getApellido();
            if (lista.get(i).getMascota().getPersona().getCedula().equals(busqueda)) {
                busca.add(lista.get(i));
            } else if (lista.get(i).getMascota().getPersona().getNombre().equalsIgnoreCase(busqueda)
                    || lista.get(i).getMascota().getPersona().getApellido().equalsIgnoreCase(busqueda)) {
                busca.add(lista.get(i));
            } else if (nom.equalsIgnoreCase(busqueda)) {
                busca.add(lista.get(i));
            }
        }

        if (busca.size() > 0) {
            for (Hospitalizacion hosp : busca) {
                model.addRow(new Object[]{hosp.getId(), hosp.getMascota().getNombre(),
                    hosp.getMascota().getPersona().getCedula(),
                    hosp.getMascota().getPersona().getNombre() + " " + hosp.getMascota().getPersona().getApellido(),
                    hosp.getVereterinario(), hosp.getEstado(), hosp.getIngreso(), hosp.getSalida()});
            }

        } else {
            JOptionPane.showMessageDialog(null, "CLIENTE NO ENCONTRADO", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            llenarTabla();
        }
    }

    private void finalizarHosp() {
        if (jTableHospi.getSelectedRow() >= 0) {
            java.util.Date fecha = new Date();
            int seleccionar = jTableHospi.getSelectedRow();
            int idHosp = Integer.parseInt(model.getValueAt(seleccionar, 0).toString());
            Hospitalizacion hosp = hosDB.traeHospitalizacion(idHosp);
            hosp.setEstado("De Alta");
            hosp.setSalida(fecha);
            hosDB.nuevaHospitalizacion(hosp);
            llenarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una Hospitalizacion");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHospi = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableHospi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE DE LA MASCOTA", "CEDULA DEL DUEÑO", "DUEÑO", "VETERINARIO", "ESTADO", "FECHA DE INGRESO", "FECHA DE SALIDA"
            }
        ));
        jScrollPane1.setViewportView(jTableHospi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 1070, 370));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Número de Cédula:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, 30));

        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 310, 30));

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, -1, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Finalizar Hospitalización");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 570, 190, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Ver hospitalización ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 570, 180, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Atrás");
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 570, 190, 30));

        jLabel2.setFont(new java.awt.Font("Constantia", 1, 36)); // NOI18N
        jLabel2.setText("LISTA HOSPITALIZACIONES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 660, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FONDOP1.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1290, 630));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:                                                      
        if (jTextFieldBuscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR CAMPO REQUERIDO", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            buscarHosp(jTextFieldBuscar.getText());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int op = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea Finalizar la Hopitalizacion seleccionada?", "Selecciona una opcion", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            finalizarHosp();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (jTableHospi.getSelectedRow() >= 0) {
            int seleccionar = jTableHospi.getSelectedRow();
            int idHosp = Integer.parseInt(model.getValueAt(seleccionar, 0).toString());
            Hospitalizacion hosp = hosDB.traeHospitalizacion(idHosp);
            frmHospitalizacion frmHos = new frmHospitalizacion(new javax.swing.JFrame(), false, true, hosp);
            this.setVisible(false);
            frmHos.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una Hospitalizacion en la tabla");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(frmListaHospitalización.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmListaHospitalización.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmListaHospitalización.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmListaHospitalización.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmListaHospitalización dialog = new frmListaHospitalización(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHospi;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
