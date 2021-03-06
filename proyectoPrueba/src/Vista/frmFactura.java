/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.DetallefacturaDB;
import Controlador.FacturaDB;
import Controlador.PersonaDB;
import Controlador.ServicioDB;
import Controlador.Validaciones;
import Modelo.Detallefactura;
import Modelo.Factura;
import Modelo.Persona;
import Modelo.Servicio;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;

/**
 *
 * @author Personal
 */
public class frmFactura extends javax.swing.JDialog implements Printable {

    DefaultTableModel model = new DefaultTableModel();
    Validaciones vali = new Validaciones();
    PersonaDB perDB = new PersonaDB();
    FacturaDB facturaDB = new FacturaDB();
    ServicioDB servicioDB = new ServicioDB();
    DetallefacturaDB detalledb = new DetallefacturaDB();
    int cont = 0;
    double total_fact;
    double desctcuento_total = 0;
    double subtot = 0;

    /**
     * parte para la integracion de ls servicios solo
     */
    public frmFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fecha.setText(fechaactual());
        txtdecuento.setText("0");
        txtidpersona.setVisible(false);
        buttoneliminar.setEnabled(false);
        tablemodeldetalle();
        txtiva.setText("12");
        txtNro.setText(vali.ObtenerCodString(obtenerCodigoReceta()));

    }

    private void tablaModelServicio() {
        // metodo sirve para llamar a los campos que se hayan guardado en la base de datos
        tablaServicios.getColumnModel().getColumn(0).setMaxWidth(100);
        tablaServicios.getColumnModel().getColumn(1).setMaxWidth(350);
        tablaServicios.getColumnModel().getColumn(2).setMaxWidth(1250);
        tablaServicios.getColumnModel().getColumn(3).setMaxWidth(300);
        model = (DefaultTableModel) tablaServicios.getModel();
        model.setNumRows(0);
    }

    private void tablemodeldetalle() {
        tabladetalle.getColumnModel().getColumn(0).setMaxWidth(75);
        tabladetalle.getColumnModel().getColumn(1).setMaxWidth(75);
        tabladetalle.getColumnModel().getColumn(2).setMaxWidth(300);
        tabladetalle.getColumnModel().getColumn(3).setMaxWidth(1100);
        tabladetalle.getColumnModel().getColumn(4).setMaxWidth(250);
        tabladetalle.getColumnModel().getColumn(5).setMaxWidth(100);
        model = (DefaultTableModel) tabladetalle.getModel();
        model.setNumRows(0);

    }

    private void tablaModelCliente() {
        // metodo sirve para llamar a los campos que se hayan guardado en la tabla clienetes

        Tablaclientes.getColumnModel().getColumn(0).setMaxWidth(0);
        Tablaclientes.getColumnModel().getColumn(0).setMinWidth(0);
        Tablaclientes.getColumnModel().getColumn(0).setPreferredWidth(0);

        Tablaclientes.getColumnModel().getColumn(1).setPreferredWidth(300);
        Tablaclientes.getColumnModel().getColumn(2).setPreferredWidth(300);
        Tablaclientes.getColumnModel().getColumn(3).setPreferredWidth(300);
        Tablaclientes.getColumnModel().getColumn(4).setPreferredWidth(300);
        model = (DefaultTableModel) Tablaclientes.getModel();
        model.setNumRows(0);

    }

    private void llenarTablaServico(String estado) {
        tablaModelServicio();
        List<Servicio> listaServ = null;
        listaServ = servicioDB.cargaServicio(estado, listaServ);

        for (Servicio lista : listaServ) {
            model.addRow(new Object[]{
                lista.getId_serv(), lista.getNombre_serv(), lista.getDesc_serv(), lista.getPrecio_serv()
            });
        }
    }

    private void llenaTablaClientes(String estado) {
        tablaModelCliente();
        List<Persona> lista = null;

        lista = perDB.listarPersonas();

        for (Persona perLis : lista) {
            model.addRow(new Object[]{
                perLis.getId_persona(), perLis.getCedula(), perLis.getNombre(), perLis.getApellido(), perLis.getTelefono()
            });
        }

    }

    private static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatofecha.format(fecha);

    }

    private void buscarClienteCedula(String cedula) {
        model.setNumRows(0);
        List<Persona> listaP = null;

        cedula = txtBuscar.getText();

        listaP = perDB.buscarPersonaCed(cedula);

        if (listaP.size() > 0) {
            for (Persona perLis : listaP) {
                model.addRow(new Object[]{
                    perLis.getId_persona(), perLis.getCedula(), perLis.getNombre(), perLis.getApellido(), perLis.getTelefono()
                });
            }

        } else {
            JOptionPane.showMessageDialog(null, "CLIENTE NO ENCONTRADO", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            llenaTablaClientes("A");
        }

    }

    private void escoderAparecer(boolean bl) {

        jButtonbuscarcliente.setVisible(bl);
        jButtonbuscarservicio.setVisible(bl);
        buttoneliminar.setVisible(bl);
        jButtonimprimir.setVisible(bl);
//        jButtoncancelar.setVisible(bl);
        jButtonatras.setVisible(bl);

    }

    private void guardar() {
        int contar = tabladetalle.getRowCount();//filas actules de tabla detalle
        Date fecha = new Date();
        Persona pr = new Persona();
        Servicio ser = new Servicio();
        pr = perDB.traerPorCedula(txtcedula.getText());
        Factura ft = new Factura();
        //generamos los datos de factura
        ft.setFecha(fecha);
        ft.setIva(Double.parseDouble(txtiva.getText()));
        ft.setNro_factura(txtNro.getText());
        ft.setPersona(pr);
        ft.setDescuento(desctcuento_total);
        ft.setTotal(total_fact);
        ft.setSubtotal(subtot);
        pr.getFactura().add(ft);
        facturaDB.nuevaFactura(ft);
        //Ya se esta creada la factura 
        // vamos a crea un bucle para que se para recorer todo la tabla para ir gerenado los dettles factura
        for (int i = 0; i < contar; i++) {
            ft = facturaDB.traenumfact(txtNro.getText());
            ser = servicioDB.traeServicio(Integer.parseInt((tabladetalle.getValueAt(i, 0).toString())));
            int id = ser.getId_serv();
            Detallefactura det = new Detallefactura();
            det.setCantidad(Integer.parseInt((tabladetalle.getValueAt(i, 1).toString())));//cantidad
            det.setPrecio_total(Double.parseDouble((tabladetalle.getValueAt(i, 5).toString())));//cantidad
            det.setPrecio_unitario(Double.parseDouble((tabladetalle.getValueAt(i, 4).toString())));//cantidad
            det.setFactura(ft);
            det.setServicio(ser);
            ser.getListPerso().add(det);
            ft.getDetallefactura().add(det);
            detalledb.nuevodetallefactura(det);
            // detallefactura.set
//            suma += Double.parseDouble(tabladetalle.getValueAt(i, 5).toString());
//            System.out.println(suma);
        }

    }
    private int num_resp = 0;

    public int obtenerCodigoReceta() {
        num_resp = 0;
        List<Factura> lista = null;
        lista = facturaDB.caragarFacturas(lista);
        for (Iterator<Factura> it = lista.iterator(); it.hasNext();) {
            Factura cit = it.next();
            num_resp = Integer.parseInt(cit.getNro_factura()) + 1;
        }
        if (num_resp == 0) {
            num_resp = 1;
        }
        return num_resp;
    }

    private void imprimir() {
        try {
            PrinterJob g = PrinterJob.getPrinterJob();
            g.setPrintable(this);
            boolean top = g.printDialog();//para ve el como la enta na de imprimir 
            // si se pone acpatr se vuelve 1
            if (top) {
                g.print();
                escoderAparecer(false);
            }
        } catch (Exception e) {
            escoderAparecer(false);
            JOptionPane.showMessageDialog(null, "ERRROR DE¨PROGRAMA ", "ERROR" + e, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void controldescuento() {
        if (Integer.parseInt(txtdecuento.getText()) <= 100) {
            return;
        } else {

            txtdecuento.setText("100");
        }
    }

    //obtenemos la suma y tambien el calculo del iva ingresado
    private void suma() {
        //obtenemos las fila actuales 
        int contar = tabladetalle.getRowCount();
        controldescuento();
        double suma = 0.0;
        if (txtiva.getText().equalsIgnoreCase("")) {
            // JOptionPane.showMessageDialog(null,"EL CAMPO DE IVA NO TIENE QUE ESTAR VACIO");
            txtiva.setText("0");
        }

        for (int i = 0; i < contar; i++) {
            suma += Double.parseDouble(tabladetalle.getValueAt(i, 5).toString());
            System.out.println(suma);
        }

        //calculamos el iva 
        double total = suma;
        double iva = Double.parseDouble(txtiva.getText());
        double subtototal = 0.0;
        double total_a_pagar;
        double desct;
        double totalaux;
        double desct_total;

        //total menos el decuento
        if (!txtdecuento.getText().equals("0")) {
            desctcuento_total = Double.parseDouble(txtdecuento.getText());
            desct = Double.parseDouble(txtdecuento.getText());
            System.out.println(desct);
            desct_total = total - ((total * desct) / 100);
            total = desct_total;
            subtototal = total / ((100 + iva) / 100);
            total = subtototal * (iva / 100);
            total_fact = total + subtototal;
            txtsubtotal.setText(String.format("%.2f", subtototal));
            subtot = subtototal;
            txttotal.setText(String.format("%.2f", total + subtototal));
        } else {
            desctcuento_total = 0.0;
            desct = 0;
            subtototal = total / ((100 + iva) / 100);
            total = subtototal * (iva / 100);
            txtsubtotal.setText(String.format("%.2f", subtototal));
            subtot = subtototal;
            txttotal.setText(String.format("%.2f", total + subtototal));
            total_fact = total + subtototal;
        }

    }

    private void eliminar() {
        txtNro.setText(vali.ObtenerCodString(obtenerCodigoReceta()));
        txtcedula.setText("");
        txtnombre.setText("");
        txtcorreo.setText("");
        txtdirecion.setText("");
        txttelefono.setText("");
        txtidpersona.setText("");
        txtsubtotal.setText("");
        txttotal.setText("");
        txtdecuento.setText("");
        model = (DefaultTableModel) tabladetalle.getModel();
        model.setNumRows(0);
        cont = 0;
    }

    private void buscaServicio(String nombre) {
        model.setNumRows(0);

        List<Servicio> lis = null;
        lis = servicioDB.buscarServicio(nombre, lis);

        if (lis.size() > 0) {
            for (Servicio lista : lis) {
                model.addRow(new Object[]{
                    lista.getId_serv(), lista.getNombre_serv(),lista.getDesc_serv(), lista.getPrecio_serv()
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "SERVICIO NO ENCONTRADO", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            txtBuscar.requestFocus();
            llenarTablaServico("A");
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

        buscarcliente = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tablaclientes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        Agregarcliente = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        buscarservicio = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        Agregar_servicio = new javax.swing.JButton();
        Cancelarservicio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtbuscarser = new javax.swing.JTextField();
        buscar_ser = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cantidaagregar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabladetalle = new javax.swing.JTable();
        jButtonimprimir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLnombre = new javax.swing.JLabel();
        jlabeldireccion = new javax.swing.JLabel();
        jLabelcorreo = new javax.swing.JLabel();
        jLabelcedula = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jButtonbuscarcliente = new javax.swing.JButton();
        jLabeltelefono = new javax.swing.JLabel();
        txttelefono = new javax.swing.JLabel();
        txtcedula = new javax.swing.JLabel();
        txtdirecion = new javax.swing.JLabel();
        txtnombre = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JLabel();
        jButtonbuscarservicio = new javax.swing.JButton();
        buttoneliminar = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jButtonatras = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtdecuento = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtiva = new javax.swing.JTextField();
        txttotal = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JLabel();
        txtidpersona = new javax.swing.JLabel();
        txtNro = new javax.swing.JLabel();

        buscarcliente.setResizable(false);
        buscarcliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        Tablaclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cédula", "Nombre", "Apelido", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tablaclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaclientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tablaclientes);

        jLabel5.setText("Nro. Cédula");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        Agregarcliente.setText("Agregar");
        Agregarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarclienteActionPerformed(evt);
            }
        });

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(44, 44, 44)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnBuscar)
                        .addGap(159, 159, 159))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Agregarcliente)
                        .addGap(70, 70, 70)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(271, 271, 271))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(btnBuscar)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Agregarcliente)
                    .addComponent(jButton1))
                .addGap(69, 69, 69))
        );

        buscarcliente.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 260));

        buscarservicio.setPreferredSize(new java.awt.Dimension(710, 260));
        buscarservicio.setResizable(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(710, 300));

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Descripción", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaServiciosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaServicios);

        Agregar_servicio.setText("Agregar ");
        Agregar_servicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Agregar_servicioActionPerformed(evt);
            }
        });

        Cancelarservicio.setText("Cancelar");
        Cancelarservicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarservicioActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar");

        txtbuscarser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarserActionPerformed(evt);
            }
        });
        txtbuscarser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarserKeyTyped(evt);
            }
        });

        buscar_ser.setText("Buscar");
        buscar_ser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_serActionPerformed(evt);
            }
        });

        jLabel2.setText("Cantidad");

        cantidaagregar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantidaagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidaagregarActionPerformed(evt);
            }
        });
        cantidaagregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidaagregarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cantidaagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(Agregar_servicio)
                .addGap(111, 111, 111)
                .addComponent(Cancelarservicio)
                .addGap(221, 221, 221))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(txtbuscarser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(buscar_ser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscarser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar_ser))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Agregar_servicio)
                            .addComponent(Cancelarservicio)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cantidaagregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout buscarservicioLayout = new javax.swing.GroupLayout(buscarservicio.getContentPane());
        buscarservicio.getContentPane().setLayout(buscarservicioLayout);
        buscarservicioLayout.setHorizontalGroup(
            buscarservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        buscarservicioLayout.setVerticalGroup(
            buscarservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tabladetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Cantidad", "Nombre", "Decripción", "Precio Unitario", "Total"
            }
        ));
        tabladetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabladetalleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabladetalle);

        jButtonimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/print.png"))); // NOI18N
        jButtonimprimir.setText("Imprimir");
        jButtonimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonimprimirActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLnombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLnombre.setText("Nombre y apellidos  ");

        jlabeldireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlabeldireccion.setText("Dirección");

        jLabelcorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelcorreo.setText("Correo");

        jLabelcedula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelcedula.setText("Cedula");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Fecha ");

        fecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fecha.setText("mm-dd-aa");
        fecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonbuscarcliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonbuscarcliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/search.png"))); // NOI18N
        jButtonbuscarcliente.setText(" Cliente");
        jButtonbuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbuscarclienteActionPerformed(evt);
            }
        });

        jLabeltelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabeltelefono.setText("Teléfono");

        txttelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtcedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtdirecion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtnombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtcorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonbuscarservicio.setText("Agregar servicio");
        jButtonbuscarservicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbuscarservicioActionPerformed(evt);
            }
        });

        buttoneliminar.setText("Eliminar");
        buttoneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLnombre)
                            .addComponent(jlabeldireccion)
                            .addComponent(jLabeltelefono)
                            .addComponent(jLabelcedula))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdirecion, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonbuscarcliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonbuscarservicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttoneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)))
                        .addGap(30, 30, 30)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelcorreo))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(33, 33, 33)
                        .addComponent(jLabelcorreo))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLnombre)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlabeldireccion)
                                    .addComponent(txtdirecion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButtonbuscarcliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelcedula)
                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonbuscarservicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabeltelefono)
                    .addComponent(buttoneliminar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Factura N°:");

        jButtonatras.setText("Atrás");
        jButtonatras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonatrasActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setText("Subtotal $");

        jLabel17.setText("Descuento %");

        jLabel18.setText("Total $");

        txtdecuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdecuentoActionPerformed(evt);
            }
        });
        txtdecuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdecuentoKeyTyped(evt);
            }
        });

        jLabel29.setText("Iva");

        txtiva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtivaKeyTyped(evt);
            }
        });

        txttotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsubtotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtiva, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(txtsubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdecuento)
                            .addComponent(txttotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtdecuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(txttotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        txtidpersona.setText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtNro, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(132, 132, 132)
                            .addComponent(txtidpersona, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButtonimprimir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonatras, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(txtidpersona)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonimprimir)
                    .addComponent(jButtonatras, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 51, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 930, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonbuscarservicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbuscarservicioActionPerformed
        cantidaagregar.setText("1");
        llenarTablaServico("A");
        buscarservicio.setSize(730, 400);
        buscarservicio.setLocationRelativeTo(null);
        buscarservicio.setModal(true);
        buscarservicio.setVisible(true);
    }//GEN-LAST:event_jButtonbuscarservicioActionPerformed

    private void jButtonimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonimprimirActionPerformed
        //verificamos que la cedula este corecta o si no se hace nada

        if (txtcedula.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "NO HAY CLIENTE INGRESADO", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        } else {
            System.out.println(cont);
            if (cont == 0) {
                JOptionPane.showMessageDialog(null, "NO HAY SERVICIOS INGRESADOS", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            } else {
                controldescuento();
                suma();
                guardar();
                //ocultamos los paneles y botones para que no aparecan al imprimir 
                escoderAparecer(false);
                //metodo para la impresion de panel 
                imprimir();
                //mostramos otra vez los que se escondio
                escoderAparecer(true);
                eliminar();
                txtsubtotal.setText("0");
                txtdecuento.setText("0");
                //neviamos los datos de la factura a jpanel para imprimirlo
            }
        }


    }//GEN-LAST:event_jButtonimprimirActionPerformed

    private void txtdecuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdecuentoKeyTyped
        vali.valNum(evt, txtdecuento, 3);

//        if (txtdecuento.getText().length() >= 5) {
//            evt.consume();
//        }
//        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
//            evt.consume();
//        }
//        if (evt.getKeyChar() == '.' && txtdecuento.getText().contains(".")) {
//            evt.consume();
//        }

    }//GEN-LAST:event_txtdecuentoKeyTyped

    private void txtdecuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdecuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdecuentoActionPerformed

    private void TablaclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaclientesMouseClicked


    }//GEN-LAST:event_TablaclientesMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        if (txtBuscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR CAMPO DEL NUMERO DE CEDULA", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (vali.validarCedula(txtBuscar.getText())) {
                buscarClienteCedula(txtBuscar.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Cedula no valida", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void AgregarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarclienteActionPerformed
        int seleccionar = Tablaclientes.getSelectedRow();
        try {
            if (seleccionar == -1) {

                JOptionPane.showMessageDialog(null, "Debe selecionar un cliente", "Advetecia", JOptionPane.WARNING_MESSAGE);
            }
            int idCliente = Integer.parseInt(model.getValueAt(seleccionar, 0).toString()); // esto esta tomando el id de la tabla
            //tramos las persona que esta selecionada con el id
            Persona per = perDB.traeClientesId(idCliente);
            //agregamos la cedula traida en los campos de factura 
            txtcedula.setText(per.getCedula());
            txtnombre.setText(per.getNombre() + " " + per.getApellido());
            txtcorreo.setText(per.getCorreo());
            txtdirecion.setText(per.getDireccion());
            txttelefono.setText(per.getTelefono());
            txtidpersona.setText(String.valueOf(per.getId_persona()));
            buscarcliente.setModal(false);
            buscarcliente.setVisible(false);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_AgregarclienteActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        vali.valNum(evt, txtBuscar, 10);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void jButtonbuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbuscarclienteActionPerformed
        // abrir la ventana de busqueda
        llenaTablaClientes("A");
        buscarcliente.setSize(730, 300);
        buscarcliente.setLocationRelativeTo(null);
        buscarcliente.setModal(true);
        buscarcliente.setVisible(true);

    }//GEN-LAST:event_jButtonbuscarclienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarcliente.setModal(false);
        buscarcliente.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServiciosMouseClicked
        int selectRow = tablaServicios.getSelectedRow();
        int idServ = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
        Servicio serv = servicioDB.traeServicio(idServ);
    }//GEN-LAST:event_tablaServiciosMouseClicked

    private void txtbuscarserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarserActionPerformed

    private void CancelarservicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarservicioActionPerformed
        buscarservicio.setModal(false);
        buscarservicio.setVisible(false);
    }//GEN-LAST:event_CancelarservicioActionPerformed

    private void cantidaagregarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidaagregarKeyTyped
        vali.valNum(evt, cantidaagregar, 4);
    }//GEN-LAST:event_cantidaagregarKeyTyped

    private void Agregar_servicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Agregar_servicioActionPerformed
        Servicio ser;
        int slecionar = tablaServicios.getSelectedRow();
        try {
            String codigo, nombre, cantidad, descripcion, precio, total;
            double cantidatotal = 0.0;
            if (slecionar == -1) {

                JOptionPane.showMessageDialog(null, "Debe selecionar un cliente", "Advetecia", JOptionPane.WARNING_MESSAGE);
            } else {
                int idServico = Integer.parseInt(model.getValueAt(slecionar, 0).toString());
                ser = servicioDB.traeServicio(idServico);
                codigo = String.valueOf(ser.getId_serv());

                cantidad = cantidaagregar.getText();
                nombre = ser.getNombre_serv();
                descripcion = ser.getDesc_serv();
                precio = String.valueOf(ser.getPrecio_serv());
                cantidatotal = ser.getPrecio_serv() * Integer.parseInt(cantidaagregar.getText());
                total = String.valueOf(cantidatotal);
                model = (DefaultTableModel) tabladetalle.getModel();
                String fila[] = {codigo, cantidad, nombre, descripcion, precio, total};
                model.addRow(fila);

                cont++;
                System.out.println(cont);
                buscarservicio.setVisible(false);
                suma();
                buscarservicio.setModal(false);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_Agregar_servicioActionPerformed

    private void txtbuscarserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarserKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarserKeyTyped

    private void cantidaagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidaagregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidaagregarActionPerformed

    private void tabladetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabladetalleMouseClicked
        buttoneliminar.setEnabled(true);

    }//GEN-LAST:event_tabladetalleMouseClicked

    private void buttoneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoneliminarActionPerformed
        if (tabladetalle.getSelectedRow() == -1) {

        } else {
            model = (DefaultTableModel) tabladetalle.getModel();
            //eliminamos la fila
            model.removeRow(tabladetalle.getSelectedRow());
            txttotal.setText("45645");
            buttoneliminar.setEnabled(false);
            cont--;
            suma();

        }
    }//GEN-LAST:event_buttoneliminarActionPerformed

    private void txtivaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtivaKeyTyped
        vali.valNum(evt, txtiva, 2);

        /*
        if (txtiva.getText().length() >= 4) {
            evt.consume();
        }
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtiva.getText().contains(".")) {
            evt.consume();
        }
         */
    }//GEN-LAST:event_txtivaKeyTyped

    private void jButtonatrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonatrasActionPerformed
        this.setModal(false);
        this.dispose();

    }//GEN-LAST:event_jButtonatrasActionPerformed

    private void buscar_serActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_serActionPerformed
        if (txtbuscarser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR EL NOMBRE ", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            buscaServicio(txtbuscarser.getText());
//            chkDesactivados.setSelected(false);
        }
    }//GEN-LAST:event_buscar_serActionPerformed

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
            java.util.logging.Logger.getLogger(frmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmFactura dialog = new frmFactura(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Agregar_servicio;
    private javax.swing.JButton Agregarcliente;
    private javax.swing.JButton Cancelarservicio;
    private javax.swing.JTable Tablaclientes;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton buscar_ser;
    private javax.swing.JDialog buscarcliente;
    private javax.swing.JDialog buscarservicio;
    private javax.swing.JButton buttoneliminar;
    private javax.swing.JTextField cantidaagregar;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonatras;
    private javax.swing.JButton jButtonbuscarcliente;
    private javax.swing.JButton jButtonbuscarservicio;
    private javax.swing.JButton jButtonimprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelcedula;
    private javax.swing.JLabel jLabelcorreo;
    private javax.swing.JLabel jLabeltelefono;
    private javax.swing.JLabel jLnombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jlabeldireccion;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JTable tabladetalle;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtNro;
    private javax.swing.JTextField txtbuscarser;
    private javax.swing.JLabel txtcedula;
    private javax.swing.JLabel txtcorreo;
    private javax.swing.JTextField txtdecuento;
    private javax.swing.JLabel txtdirecion;
    private javax.swing.JLabel txtidpersona;
    private javax.swing.JTextField txtiva;
    private javax.swing.JLabel txtnombre;
    private javax.swing.JLabel txtsubtotal;
    private javax.swing.JLabel txttelefono;
    private javax.swing.JLabel txttotal;
    // End of variables declaration//GEN-END:variables
 //@Override
    public int print(Graphics graf, PageFormat pagfor, int index) throws PrinterException {

        if (index > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D hub = (Graphics2D) graf;
        hub.translate(pagfor.getImageableX(), pagfor.getImageableY());
        hub.scale(0.5, 0.5);
        // lo que se va  a imprimir 
        jPanel1.printAll(graf);

        return PAGE_EXISTS;

    }
}
