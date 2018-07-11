/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Modelo.TipoDocumentosDAO;
import Modelo.DepartamentosVO;
import Modelo.DepartamentosDAO;
import Modelo.FiltroProveedores;
import Modelo.MunicipiosDAO;
import Modelo.MunicipiosVO;
import java.awt.Dimension;
import java.util.Date;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jsanchez
 */
public class frmProveedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmProveedores
     */
    PreparedStatement ps;
    ResultSet rs;
    public boolean bandera = true;
    public boolean validacion = true;
    
    
    DepartamentosDAO cc = new DepartamentosDAO();
    TipoDocumentosDAO TipDoc = new TipoDocumentosDAO();

    public int TipoProveedor;
    public int TipoServicio;
    public int TipoDocumentoCompra;
    public int Contrato;
    private boolean BanderaBusqueda = true;
    private boolean BanderaTipoProveedor = false;
    private boolean BanderaTipoServicio = false;
    private boolean BanderaTipoCompra = false;
    private boolean BanderaContrato = false;
    private boolean BanderaDocumento = false;
    private boolean BanderaNombreProveedor = false;
    private boolean BanderaDireccionProveedor = false;
    private boolean BanderaTelefono = false;

    public void txtinactivos() {
        txtDocumento.setEnabled(true);
        cbxTipoDocumento.setEnabled(false);
        txtNombreProveedor.setEnabled(false);
        txtContactoProveedor.setEnabled(false);
        txtDirección.setEnabled(false);
        txtTelefono.setEnabled(false);
        cbxDepartamentos.setEnabled(false);
        cbxMunicipios.setEnabled(false);
        txtCorreoElectronico.setEnabled(false);
        jrbProveedor.setEnabled(false);
        jrbContratista.setEnabled(false);
        jrbAmbosTiposProveedor.setEnabled(false);
        jrbBien.setEnabled(false);
        jrbServicio.setEnabled(false);
        jrbAmbosTiposServicios.setEnabled(false);
        jrbOrdenCompra.setEnabled(false);
        jrbOrdenDespacho.setEnabled(false);
        jrbNoaplica.setEnabled(false);
        jdcFechaIngreso.setEnabled(false);
        jrbcontratosi.setEnabled(false);
        jrbcontratono.setEnabled(false);
        txtDescripcion.setEnabled(false);
        // habilitar botones
        btnNuevo.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
    }

    public void txtactivos() {
        txtDocumento.setEnabled(false);
        cbxTipoDocumento.setEnabled(true);
        txtNombreProveedor.setEnabled(true);
        txtContactoProveedor.setEnabled(true);
        txtDirección.setEnabled(true);
        txtTelefono.setEnabled(true);
        cbxDepartamentos.setEnabled(true);
        cbxMunicipios.setEnabled(true);
        txtCorreoElectronico.setEnabled(true);
        jrbProveedor.setEnabled(true);
        jrbContratista.setEnabled(true);
        jrbAmbosTiposProveedor.setEnabled(true);
        jrbBien.setEnabled(true);
        jrbServicio.setEnabled(true);
        jrbAmbosTiposServicios.setEnabled(true);
        jrbOrdenCompra.setEnabled(true);
        jrbOrdenDespacho.setEnabled(true);
        jrbNoaplica.setEnabled(true);
        jdcFechaIngreso.setEnabled(true);
        jrbcontratosi.setEnabled(true);
        jrbcontratono.setEnabled(true);
        txtDescripcion.setEnabled(true);
        // habilitar botones
        btnNuevo.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }

    public void limpiarcasillas() {
        txtDocumento.setText(null);
        cbxTipoDocumento.setSelectedIndex(0);
        txtNombreProveedor.setText(null);
        txtContactoProveedor.setText(null);
        txtDirección.setText(null);
        txtTelefono.setText(null);
        cbxDepartamentos.setSelectedIndex(0);
        cbxMunicipios.setSelectedIndex(0);
        txtCorreoElectronico.setText(null);
        jrbgrTiposProveedores.clearSelection();
        jbrgrContrato.clearSelection();
        jbrgrDocumentoCompra.clearSelection();
        jbrgrTipoServicio.clearSelection();
        Calendar c2 = new GregorianCalendar();
        jdcFechaIngreso.setCalendar(c2);
        txtDescripcion.setText(null);
    }

    public void llenarcomboboxtipodocumento() {
        TipoDocumentosDAO TipoDoc = new TipoDocumentosDAO();
        TipoDoc.llenartipodocumentos(cbxTipoDocumento);
    }

    public void llenarcomboboxdepartamentos() {
     
     DefaultComboBoxModel modelDepartamento = new DefaultComboBoxModel(cc.mostrarDepartamentos());
     cbxDepartamentos.setModel(modelDepartamento);
    }

    public void validacionopciones() {

        if (BanderaDocumento == false) {
            JOptionPane.showMessageDialog(null, "Debe digitar un numero de documento valido");
            validacion = false;
        }
        if (BanderaNombreProveedor == false) {
            JOptionPane.showMessageDialog(null, "Debe digitar un nombre de proveedor");
            validacion = false;
        }
        if (BanderaDireccionProveedor == false) {
            JOptionPane.showMessageDialog(null, "Debe digitar un dirección del proveedor");
            validacion = false;
        }
        if (BanderaTelefono == false) {
            JOptionPane.showMessageDialog(null, "Debe digitar un numero de telefono del proveedor");
            validacion = false;
        }
        if (BanderaTipoProveedor == false) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una opcion de proveedor");
            validacion = false;
        }
        if (BanderaTipoServicio == false) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una opcion de servicio");
            validacion = false;
        }
        if (BanderaTipoCompra == false) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una opcion de tipo de compra");
            validacion = false;
        }
        if (BanderaContrato == false) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una opcion de Contrato");
            validacion = false;
        }
    }

    public frmProveedores() {
        initComponents();
        llenarcomboboxdepartamentos();
        llenarcomboboxtipodocumento();
        txtinactivos();

        Calendar c2 = new GregorianCalendar();
        jdcFechaIngreso.setCalendar(c2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jrbgrTiposProveedores = new javax.swing.ButtonGroup();
        jbrgrTipoServicio = new javax.swing.ButtonGroup();
        jbrgrDocumentoCompra = new javax.swing.ButtonGroup();
        jbrgrContrato = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbxTipoDocumento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtContactoProveedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDirección = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbxDepartamentos = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbxMunicipios = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jrbProveedor = new javax.swing.JRadioButton();
        jrbContratista = new javax.swing.JRadioButton();
        jrbAmbosTiposProveedor = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jrbBien = new javax.swing.JRadioButton();
        jrbServicio = new javax.swing.JRadioButton();
        jrbAmbosTiposServicios = new javax.swing.JRadioButton();
        txtCorreoElectronico = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jrbOrdenCompra = new javax.swing.JRadioButton();
        jrbOrdenDespacho = new javax.swing.JRadioButton();
        jrbNoaplica = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jdcFechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jrbcontratosi = new javax.swing.JRadioButton();
        jrbcontratono = new javax.swing.JRadioButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDocumento1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cbxTipoDocumento1 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txtNombreProveedor1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtContactoProveedor1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtDirección1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        cbxDepartamentos1 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        cbxMunicipios1 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jrbProveedor1 = new javax.swing.JRadioButton();
        jrbContratista1 = new javax.swing.JRadioButton();
        jrbAmbosTiposProveedor1 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jrbBien1 = new javax.swing.JRadioButton();
        jrbServicio1 = new javax.swing.JRadioButton();
        jrbAmbosTiposServicios1 = new javax.swing.JRadioButton();
        txtCorreoElectronico1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jrbOrdenCompra1 = new javax.swing.JRadioButton();
        jrbOrdenDespacho1 = new javax.swing.JRadioButton();
        jrbNoaplica1 = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        jdcFechaIngreso1 = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion1 = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jrbcontratosi1 = new javax.swing.JRadioButton();
        jrbcontratono1 = new javax.swing.JRadioButton();
        btnNuevo1 = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        btnEliminar1 = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("PROVEEDORES Y CONTRATISTAS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Documento");

        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Tipo de Documento");

        cbxTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoDocumentoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nombre ");

        txtNombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProveedorActionPerformed(evt);
            }
        });
        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nombre Contacto");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Dirección");

        txtDirección.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDirecciónKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Telefono");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Departamento");

        cbxDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentosItemStateChanged(evt);
            }
        });
        cbxDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDepartamentosActionPerformed(evt);
            }
        });
        cbxDepartamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDepartamentosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbxDepartamentosKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Municipios");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Correo Electronico");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("TIPO DE PROVEEDOR");

        jrbgrTiposProveedores.add(jrbProveedor);
        jrbProveedor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbProveedor.setText("Proveedor");
        jrbProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbProveedorItemStateChanged(evt);
            }
        });
        jrbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProveedorActionPerformed(evt);
            }
        });

        jrbgrTiposProveedores.add(jrbContratista);
        jrbContratista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbContratista.setText("Contratista");
        jrbContratista.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbContratistaItemStateChanged(evt);
            }
        });
        jrbContratista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbContratistaActionPerformed(evt);
            }
        });

        jrbgrTiposProveedores.add(jrbAmbosTiposProveedor);
        jrbAmbosTiposProveedor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbAmbosTiposProveedor.setText("Ambos");
        jrbAmbosTiposProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbAmbosTiposProveedorItemStateChanged(evt);
            }
        });
        jrbAmbosTiposProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAmbosTiposProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jrbProveedor)
                .addGap(71, 71, 71)
                .addComponent(jrbContratista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbAmbosTiposProveedor)
                .addGap(41, 41, 41))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbProveedor)
                    .addComponent(jrbContratista)
                    .addComponent(jrbAmbosTiposProveedor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("TIPO DE SERVICIOS");

        jbrgrTipoServicio.add(jrbBien);
        jrbBien.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbBien.setText("Bien");
        jrbBien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbBienItemStateChanged(evt);
            }
        });
        jrbBien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbBienActionPerformed(evt);
            }
        });

        jbrgrTipoServicio.add(jrbServicio);
        jrbServicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbServicio.setText("Sevicio");
        jrbServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbServicioItemStateChanged(evt);
            }
        });
        jrbServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbServicioActionPerformed(evt);
            }
        });

        jbrgrTipoServicio.add(jrbAmbosTiposServicios);
        jrbAmbosTiposServicios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbAmbosTiposServicios.setText("Ambos");
        jrbAmbosTiposServicios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbAmbosTiposServiciosItemStateChanged(evt);
            }
        });
        jrbAmbosTiposServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAmbosTiposServiciosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jrbBien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbServicio)
                .addGap(145, 145, 145)
                .addComponent(jrbAmbosTiposServicios)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(187, 187, 187))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbBien)
                    .addComponent(jrbServicio)
                    .addComponent(jrbAmbosTiposServicios))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("DOCUMENTO DE COMPRA");

        jbrgrDocumentoCompra.add(jrbOrdenCompra);
        jrbOrdenCompra.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbOrdenCompra.setText("Orden de compra");
        jrbOrdenCompra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbOrdenCompraItemStateChanged(evt);
            }
        });
        jrbOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbOrdenCompraActionPerformed(evt);
            }
        });

        jbrgrDocumentoCompra.add(jrbOrdenDespacho);
        jrbOrdenDespacho.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbOrdenDespacho.setText("Orden de despacho");
        jrbOrdenDespacho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbOrdenDespachoItemStateChanged(evt);
            }
        });
        jrbOrdenDespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbOrdenDespachoActionPerformed(evt);
            }
        });

        jbrgrDocumentoCompra.add(jrbNoaplica);
        jrbNoaplica.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbNoaplica.setText("No Aplica");
        jrbNoaplica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbNoaplicaItemStateChanged(evt);
            }
        });
        jrbNoaplica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNoaplicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jrbOrdenCompra)
                        .addGap(43, 43, 43)
                        .addComponent(jrbOrdenDespacho))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jrbNoaplica)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbOrdenCompra)
                    .addComponent(jrbOrdenDespacho)
                    .addComponent(jrbNoaplica))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Fecha de Ingreso");

        jdcFechaIngreso.setDateFormatString("dd/MM/yyyy");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Descripción");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("TIENE CONTRATO");

        jbrgrContrato.add(jrbcontratosi);
        jrbcontratosi.setText("Si");
        jrbcontratosi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbcontratosiItemStateChanged(evt);
            }
        });

        jbrgrContrato.add(jrbcontratono);
        jrbcontratono.setText("No");
        jrbcontratono.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbcontratonoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel16)
                .addContainerGap(186, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jrbcontratosi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbcontratono)
                .addGap(105, 105, 105))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbcontratosi)
                    .addComponent(jrbcontratono))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel10))
                                        .addGap(72, 72, 72)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDirección, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                            .addComponent(cbxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCorreoElectronico)
                                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreProveedor)))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(206, 206, 206)
                                        .addComponent(jdcFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9))
                                        .addGap(78, 78, 78)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxMunicipios, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDirección, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cbxMunicipios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/new-icon.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save-icon.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setMaximumSize(new java.awt.Dimension(109, 71));
        btnGuardar.setMinimumSize(new java.awt.Dimension(109, 71));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pencil-icon.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.setMaximumSize(new java.awt.Dimension(109, 71));
        btnEditar.setMinimumSize(new java.awt.Dimension(109, 71));
        btnEditar.setName(""); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Actions-edit-clear-icon.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.setMaximumSize(new java.awt.Dimension(109, 71));
        btnCancelar.setMinimumSize(new java.awt.Dimension(109, 71));
        btnCancelar.setName(""); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash-icon.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setMaximumSize(new java.awt.Dimension(109, 71));
        btnEliminar.setMinimumSize(new java.awt.Dimension(109, 71));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salir-icon.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.setMaximumSize(new java.awt.Dimension(109, 71));
        btnSalir.setMinimumSize(new java.awt.Dimension(109, 71));
        btnSalir.setName(""); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Search-icon.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscar.setMaximumSize(new java.awt.Dimension(109, 71));
        btnBuscar.setMinimumSize(new java.awt.Dimension(109, 71));
        btnBuscar.setName(""); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Filtro.png"))); // NOI18N
        jButton1.setText("Filtrar");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("PROVEEDORES Y CONTRATISTAS");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Documento");

        txtDocumento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumento1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumento1KeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Tipo de Documento");

        cbxTipoDocumento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoDocumento1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Nombre ");

        txtNombreProveedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProveedor1ActionPerformed(evt);
            }
        });
        txtNombreProveedor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreProveedor1KeyPressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Nombre Contacto");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Dirección");

        txtDirección1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDirección1KeyPressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Telefono");

        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Departamento");

        cbxDepartamentos1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentos1ItemStateChanged(evt);
            }
        });
        cbxDepartamentos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDepartamentos1ActionPerformed(evt);
            }
        });
        cbxDepartamentos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDepartamentos1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbxDepartamentos1KeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Municipios");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Correo Electronico");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("TIPO DE PROVEEDOR");

        jrbgrTiposProveedores.add(jrbProveedor1);
        jrbProveedor1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbProveedor1.setText("Proveedor");
        jrbProveedor1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbProveedor1ItemStateChanged(evt);
            }
        });
        jrbProveedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProveedor1ActionPerformed(evt);
            }
        });

        jrbgrTiposProveedores.add(jrbContratista1);
        jrbContratista1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbContratista1.setText("Contratista");
        jrbContratista1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbContratista1ItemStateChanged(evt);
            }
        });
        jrbContratista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbContratista1ActionPerformed(evt);
            }
        });

        jrbgrTiposProveedores.add(jrbAmbosTiposProveedor1);
        jrbAmbosTiposProveedor1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbAmbosTiposProveedor1.setText("Ambos");
        jrbAmbosTiposProveedor1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbAmbosTiposProveedor1ItemStateChanged(evt);
            }
        });
        jrbAmbosTiposProveedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAmbosTiposProveedor1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jrbProveedor1)
                .addGap(71, 71, 71)
                .addComponent(jrbContratista1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbAmbosTiposProveedor1)
                .addGap(41, 41, 41))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbProveedor1)
                    .addComponent(jrbContratista1)
                    .addComponent(jrbAmbosTiposProveedor1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("TIPO DE SERVICIOS");

        jbrgrTipoServicio.add(jrbBien1);
        jrbBien1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbBien1.setText("Bien");
        jrbBien1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbBien1ItemStateChanged(evt);
            }
        });
        jrbBien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbBien1ActionPerformed(evt);
            }
        });

        jbrgrTipoServicio.add(jrbServicio1);
        jrbServicio1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbServicio1.setText("Sevicio");
        jrbServicio1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbServicio1ItemStateChanged(evt);
            }
        });
        jrbServicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbServicio1ActionPerformed(evt);
            }
        });

        jbrgrTipoServicio.add(jrbAmbosTiposServicios1);
        jrbAmbosTiposServicios1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbAmbosTiposServicios1.setText("Ambos");
        jrbAmbosTiposServicios1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbAmbosTiposServicios1ItemStateChanged(evt);
            }
        });
        jrbAmbosTiposServicios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAmbosTiposServicios1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jrbBien1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbServicio1)
                .addGap(145, 145, 145)
                .addComponent(jrbAmbosTiposServicios1)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addGap(187, 187, 187))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbBien1)
                    .addComponent(jrbServicio1)
                    .addComponent(jrbAmbosTiposServicios1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("DOCUMENTO DE COMPRA");

        jbrgrDocumentoCompra.add(jrbOrdenCompra1);
        jrbOrdenCompra1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbOrdenCompra1.setText("Orden de compra");
        jrbOrdenCompra1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbOrdenCompra1ItemStateChanged(evt);
            }
        });
        jrbOrdenCompra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbOrdenCompra1ActionPerformed(evt);
            }
        });

        jbrgrDocumentoCompra.add(jrbOrdenDespacho1);
        jrbOrdenDespacho1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbOrdenDespacho1.setText("Orden de despacho");
        jrbOrdenDespacho1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbOrdenDespacho1ItemStateChanged(evt);
            }
        });
        jrbOrdenDespacho1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbOrdenDespacho1ActionPerformed(evt);
            }
        });

        jbrgrDocumentoCompra.add(jrbNoaplica1);
        jrbNoaplica1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jrbNoaplica1.setText("No Aplica");
        jrbNoaplica1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbNoaplica1ItemStateChanged(evt);
            }
        });
        jrbNoaplica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNoaplica1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jrbOrdenCompra1)
                        .addGap(43, 43, 43)
                        .addComponent(jrbOrdenDespacho1))
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jrbNoaplica1)
                .addGap(20, 20, 20))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbOrdenCompra1)
                    .addComponent(jrbOrdenDespacho1)
                    .addComponent(jrbNoaplica1))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Fecha de Ingreso");

        jdcFechaIngreso1.setDateFormatString("dd/MM/yyyy");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Descripción");

        txtDescripcion1.setColumns(20);
        txtDescripcion1.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion1);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("TIENE CONTRATO");

        jbrgrContrato.add(jrbcontratosi1);
        jrbcontratosi1.setText("Si");
        jrbcontratosi1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbcontratosi1ItemStateChanged(evt);
            }
        });

        jbrgrContrato.add(jrbcontratono1);
        jrbcontratono1.setText("No");
        jrbcontratono1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbcontratono1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel32)
                .addContainerGap(186, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jrbcontratosi1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbcontratono1)
                .addGap(105, 105, 105))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbcontratosi1)
                    .addComponent(jrbcontratono1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(jLabel17))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel26))
                                        .addGap(72, 72, 72)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDirección1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                            .addComponent(cbxDepartamentos1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCorreoElectronico1)
                                            .addComponent(txtDocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreProveedor1)))
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(206, 206, 206)
                                        .addComponent(jdcFechaIngreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel25))
                                        .addGap(78, 78, 78)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxMunicipios1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxTipoDocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtContactoProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtDocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cbxTipoDocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNombreProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtContactoProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtDirección1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbxDepartamentos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(cbxMunicipios1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtCorreoElectronico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcFechaIngreso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNuevo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/new-icon.png"))); // NOI18N
        btnNuevo1.setText("Nuevo");
        btnNuevo1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        btnGuardar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save-icon.png"))); // NOI18N
        btnGuardar1.setText("Guardar");
        btnGuardar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar1.setMaximumSize(new java.awt.Dimension(109, 71));
        btnGuardar1.setMinimumSize(new java.awt.Dimension(109, 71));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        btnEditar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pencil-icon.png"))); // NOI18N
        btnEditar1.setText("Editar");
        btnEditar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar1.setMaximumSize(new java.awt.Dimension(109, 71));
        btnEditar1.setMinimumSize(new java.awt.Dimension(109, 71));
        btnEditar1.setName(""); // NOI18N
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });

        btnCancelar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Actions-edit-clear-icon.png"))); // NOI18N
        btnCancelar1.setText("Cancelar");
        btnCancelar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar1.setMaximumSize(new java.awt.Dimension(109, 71));
        btnCancelar1.setMinimumSize(new java.awt.Dimension(109, 71));
        btnCancelar1.setName(""); // NOI18N
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        btnEliminar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash-icon.png"))); // NOI18N
        btnEliminar1.setText("Eliminar");
        btnEliminar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar1.setMaximumSize(new java.awt.Dimension(109, 71));
        btnEliminar1.setMinimumSize(new java.awt.Dimension(109, 71));
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        btnSalir1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salir-icon.png"))); // NOI18N
        btnSalir1.setText("Salir");
        btnSalir1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir1.setMaximumSize(new java.awt.Dimension(109, 71));
        btnSalir1.setMinimumSize(new java.awt.Dimension(109, 71));
        btnSalir1.setName(""); // NOI18N
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        btnBuscar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Search-icon.png"))); // NOI18N
        btnBuscar1.setText("Buscar");
        btnBuscar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscar1.setMaximumSize(new java.awt.Dimension(109, 71));
        btnBuscar1.setMinimumSize(new java.awt.Dimension(109, 71));
        btnBuscar1.setName(""); // NOI18N
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Filtro.png"))); // NOI18N
        jButton2.setText("Filtrar");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnEditar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSalir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProveedorActionPerformed

    private void jrbOrdenDespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbOrdenDespachoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jrbOrdenDespachoActionPerformed

    private void jrbOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbOrdenCompraActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jrbOrdenCompraActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        bandera = true;
        txtinactivos();
        llenarcomboboxdepartamentos();
        llenarcomboboxtipodocumento();
        limpiarcasillas();
        BanderaBusqueda = true;

    }//GEN-LAST:event_btnCancelarActionPerformed


    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        BanderaBusqueda = false;
        bandera = true;
        txtactivos();
        txtDocumento.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        BanderaBusqueda = false;
        txtactivos();
        txtDocumento.setEnabled(false);
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void cbxDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentosItemStateChanged
        // TODO add your handling code here:
      if (evt.getStateChange() == ItemEvent.SELECTED) {
            DepartamentosVO Dep = (DepartamentosVO) cbxDepartamentos.getSelectedItem();
            MunicipiosDAO mun = new MunicipiosDAO();
            DefaultComboBoxModel modelMunicipio = new DefaultComboBoxModel(mun.mostrarMunicipios(Dep.getIddepartamento()));
            cbxMunicipios.setModel(modelMunicipio);
            
        }
    }//GEN-LAST:event_cbxDepartamentosItemStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        bandera = false;
        conexionppi conexion = new conexionppi();

        try {
            Connection Rconectar;
            Rconectar = conexion.conectar();
            ps = Rconectar.prepareStatement("SELECT nitproveedor,idtipodocumento,nombreproveedor,contactoproveedor,direccionproveedor,tblproveedores.idmunicipio, municipio,tblmunicipios.iddepartamento,departamento,telefonoproveedor,correo,tipoproveedor,tiposervicio, tipodocumentocompra, fechaingreso, contrato,descripcionbienservicio from tblproveedores, tblmunicipios, tbldepartamentos where tblproveedores.nitproveedor = ? and tblproveedores.idmunicipio = tblmunicipios.idmunicipio and tblmunicipios.iddepartamento = tbldepartamentos.iddepartamento");
            ps.setString(1, txtDocumento.getText());

            rs = ps.executeQuery();
            
            if (rs.next()) {
                
                cbxTipoDocumento.setSelectedIndex(rs.getInt("idtipodocumento"));
                txtNombreProveedor.setText(rs.getString("nombreproveedor"));
                BanderaNombreProveedor = true;
                txtContactoProveedor.setText(rs.getString("contactoproveedor"));
                BanderaContrato = true;
                txtDirección.setText(rs.getString("direccionproveedor"));
                BanderaDireccionProveedor = true;
                txtTelefono.setText(rs.getString("telefonoproveedor"));
                BanderaTelefono = true;            
                Integer Depar = rs.getInt("iddepartamento");
                System.out.println(Depar);
                
                
                cbxDepartamentos.setSelectedItem(((DepartamentosDAO)cbxDepartamentos.getModel()).searchSelectedItem(Depar));
               
                int Mpio = rs.getInt("idmunicipio");
                
                cbxMunicipios.setSelectedItem(((MunicipiosDAO)cbxMunicipios.getModel()).searchSelectedItem(Mpio));
                txtCorreoElectronico.setText(rs.getString("correo"));
                switch (rs.getInt("tipoproveedor")) {
                    case 0:
                        jrbProveedor.setSelected(true);
                        BanderaTipoProveedor = true;
                        break;
                    case 1:
                        jrbContratista.setSelected(true);
                        BanderaTipoProveedor = true;
                        break;
                    case 2:
                        jrbAmbosTiposProveedor.setSelected(true);
                        BanderaTipoProveedor = true;
                        break;
                }
                switch (rs.getInt("tiposervicio")) {
                    case 0:
                        jrbBien.setSelected(true);
                        BanderaTipoServicio = true;
                        break;
                    case 1:
                        jrbServicio.setSelected(true);
                        BanderaTipoServicio = true;
                        break;
                    case 2:
                        jrbAmbosTiposServicios.setSelected(true);
                        BanderaTipoServicio = true;
                        break;
                }
                switch (rs.getInt("contrato")) {
                    case 0:
                        jrbcontratosi.setSelected(true);
                        BanderaContrato = true;
                        break;
                    case 1:
                        jrbcontratono.setSelected(true);
                        BanderaContrato = true;
                        break;
                }
                java.util.Date fechafe = rs.getDate("fechaingreso");
                jdcFechaIngreso.setDate(fechafe);
                switch (rs.getInt("tipodocumentocompra")) {
                    case 0:
                        jrbOrdenCompra.setSelected(true);
                        BanderaTipoCompra = true;
                        break;
                    case 1:
                        jrbOrdenDespacho.setSelected(true);
                        BanderaTipoCompra = true;
                        break;
                }
                txtDescripcion.setText(rs.getString("descripcionbienservicio"));

            } else {
                JOptionPane.showMessageDialog(null, "Documento de Proveedor no Existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        bandera = true;

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jrbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbProveedorActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jrbProveedorActionPerformed

    private void jrbContratistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbContratistaActionPerformed
        // TODO add your handling code here:
        //  TipoProveedor = 1;
        //  JOptionPane.showMessageDialog(null, TipoProveedor);
    }//GEN-LAST:event_jrbContratistaActionPerformed

    private void jrbAmbosTiposProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAmbosTiposProveedorActionPerformed
        // TODO add your handling code here:
        //   TipoProveedor = 2;
        //JOptionPane.showMessageDialog(null, TipoProveedor);
    }//GEN-LAST:event_jrbAmbosTiposProveedorActionPerformed

    private void jrbBienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbBienActionPerformed
        // TODO add your handling code here:
        //   TipoServicio = 0;
        //JOptionPane.showMessageDialog(null, TipoServicio);
    }//GEN-LAST:event_jrbBienActionPerformed

    private void jrbServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbServicioActionPerformed
        // TODO add your handling code here:
        //    TipoServicio = 1;
        // JOptionPane.showMessageDialog(null, TipoServicio);
    }//GEN-LAST:event_jrbServicioActionPerformed

    private void jrbAmbosTiposServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAmbosTiposServiciosActionPerformed
        // TODO add your handling code here:
        //   TipoServicio = 2;
        //JOptionPane.showMessageDialog(null, TipoServicio);
    }//GEN-LAST:event_jrbAmbosTiposServiciosActionPerformed

    private void jrbNoaplicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNoaplicaActionPerformed
        // TODO add your handling code here:
        //    TipoDocumentoCompra = 2;
        //   JOptionPane.showMessageDialog(null, TipoDocumentoCompra);
    }//GEN-LAST:event_jrbNoaplicaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        Date date = jdcFechaIngreso.getDate();
        long d = date.getTime();
        java.sql.Date Fechai = new java.sql.Date(d);
        validacionopciones();

        conexionppi conexion = new conexionppi();
        if (validacion == true) {
            try {
                Connection Rconectar;
                Rconectar = conexion.conectar();

                if (bandera == false) {
                    ps = Rconectar.prepareStatement("UPDATE tblproveedores SET idtipodocumento = ?, nombreproveedor = ?, contactoproveedor = ?, direccionproveedor = ?, idmunicipio = ?, telefonoproveedor = ?, correo = ?,  tipoproveedor = ?, tiposervicio = ?, tipodocumentocompra = ?,  fechaingreso = ?,  contrato = ?,  descripcionbienservicio = ? Where nitproveedor = ?");
                    ps.setInt(1, cbxTipoDocumento.getSelectedIndex());
                    ps.setString(2, txtNombreProveedor.getText());
                    ps.setString(3, txtContactoProveedor.getText());
                    ps.setString(4, txtDirección.getText());
                    String Munic = cbxMunicipios.getSelectedItem().toString();
                    int idMpio = 0;
//                    Mun.buscarcodigomunicipios(Munic, idMpio);
                    ps.setInt(5, idMpio);
                    ps.setString(6, txtTelefono.getText());
                    ps.setString(7, txtCorreoElectronico.getText());
                    ps.setInt(8, TipoProveedor);
                    ps.setInt(9, TipoServicio);
                    ps.setInt(10, TipoDocumentoCompra);
                    ps.setDate(11, Fechai);
                    ps.setInt(12, Contrato);
                    ps.setString(13, txtDescripcion.getText());
                    ps.setString(14, txtDocumento.getText());
                } else {
                    ps = Rconectar.prepareStatement("INSERT INTO tblproveedores(nitproveedor, idtipodocumento, nombreproveedor, contactoproveedor, direccionproveedor, idmunicipio, telefonoproveedor, correo, tipoproveedor, tiposervicio, tipodocumentocompra,fechaingreso, contrato, descripcionbienservicio) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, txtDocumento.getText());
                    ps.setInt(2, (int) cbxTipoDocumento.getSelectedIndex());
                    ps.setString(3, txtNombreProveedor.getText());
                    ps.setString(4, txtContactoProveedor.getText());
                    ps.setString(5, txtDirección.getText());
                    ps.setInt(6, (int) cbxMunicipios.getSelectedIndex());
                    ps.setString(7, txtTelefono.getText());
                    ps.setString(8, txtCorreoElectronico.getText());
                    ps.setInt(9, TipoProveedor);
                    ps.setInt(10, TipoServicio);
                    ps.setInt(11, TipoDocumentoCompra);
                    ps.setDate(12, Fechai);
                    ps.setInt(13, Contrato);
                    ps.setString(14, txtDescripcion.getText());
                }

                int res = ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Proveedor Guardado");
                    limpiarcasillas();
                    txtinactivos();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar el proveedor");
                    limpiarcasillas();
                    txtinactivos();
                }
                Rconectar.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Proveedor no guardado faltan campos por llenar");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jrbProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbProveedorItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbProveedor.isSelected()) {
                TipoProveedor = 0;
                BanderaTipoProveedor = true;
                //   JOptionPane.showMessageDialog(null, TipoProveedor);
            }
        }
    }//GEN-LAST:event_jrbProveedorItemStateChanged

    private void jrbContratistaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbContratistaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbProveedor.isSelected()) {
                TipoProveedor = 1;
                BanderaTipoProveedor = true;
                //  JOptionPane.showMessageDialog(null, TipoProveedor);
            }
        }
    }//GEN-LAST:event_jrbContratistaItemStateChanged

    private void jrbAmbosTiposProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbAmbosTiposProveedorItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbProveedor.isSelected()) {
                TipoProveedor = 2;
                BanderaTipoProveedor = true;
                //   JOptionPane.showMessageDialog(null, TipoProveedor);
            }
        }
    }//GEN-LAST:event_jrbAmbosTiposProveedorItemStateChanged

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jrbBienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbBienItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbBien.isSelected()) {
                TipoServicio = 0;
                BanderaTipoServicio = true;
            }
        }
    }//GEN-LAST:event_jrbBienItemStateChanged

    private void jrbServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbServicioItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbServicio.isSelected()) {
                TipoServicio = 1;
                BanderaTipoServicio = true;
            }
        }
    }//GEN-LAST:event_jrbServicioItemStateChanged

    private void jrbAmbosTiposServiciosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbAmbosTiposServiciosItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbAmbosTiposProveedor.isSelected()) {
                TipoServicio = 2;
                BanderaTipoServicio = true;
            }
        }
    }//GEN-LAST:event_jrbAmbosTiposServiciosItemStateChanged

    private void jrbOrdenCompraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbOrdenCompraItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbOrdenCompra.isSelected()) {
                TipoDocumentoCompra = 0;
            }
        }
    }//GEN-LAST:event_jrbOrdenCompraItemStateChanged

    private void jrbOrdenDespachoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbOrdenDespachoItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbOrdenDespacho.isSelected()) {
                TipoDocumentoCompra = 1;
                BanderaTipoCompra = true;
            }
        }
    }//GEN-LAST:event_jrbOrdenDespachoItemStateChanged

    private void jrbNoaplicaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbNoaplicaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbNoaplica.isSelected()) {
                TipoDocumentoCompra = 2;
                BanderaTipoCompra = true;
            }
        }
    }//GEN-LAST:event_jrbNoaplicaItemStateChanged

    private void jrbcontratosiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbcontratosiItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbcontratosi.isSelected()) {
                Contrato = 1;
                BanderaContrato = true;
            }
        }
    }//GEN-LAST:event_jrbcontratosiItemStateChanged

    private void jrbcontratonoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbcontratonoItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jrbcontratono.isSelected()) {
                Contrato = 0;
                BanderaContrato = true;
            }
        }
    }//GEN-LAST:event_jrbcontratonoItemStateChanged

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDocumentoKeyTyped

    private void txtDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyPressed
        // TODO add your handling code here:
        BanderaDocumento = true;
    }//GEN-LAST:event_txtDocumentoKeyPressed

    private void txtNombreProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyPressed
        // TODO add your handling code here:
        BanderaNombreProveedor = true;
    }//GEN-LAST:event_txtNombreProveedorKeyPressed

    private void txtDirecciónKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDirecciónKeyPressed
        // TODO add your handling code here:
        BanderaDireccionProveedor = true;
    }//GEN-LAST:event_txtDirecciónKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        // TODO add your handling code here:
        BanderaTelefono = true;
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void cbxDepartamentosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDepartamentosKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxDepartamentosKeyPressed

    private void cbxDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDepartamentosActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxDepartamentosActionPerformed

    private void cbxDepartamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDepartamentosKeyReleased
        // TODO add your handling code here:
        llenarcomboboxdepartamentos();
    }//GEN-LAST:event_cbxDepartamentosKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FiltroProveedores.filtroProveedores();
        /*     
            frmFiltroProveedores abrirfrmFiltroProveedores = new frmFiltroProveedores();
      //  try {
            MDIppi.Escritorio.add(abrirfrmFiltroProveedores);
            abrirfrmFiltroProveedores.toFront();
            abrirfrmFiltroProveedores.setVisible(true);
            Dimension desktopSize = MDIppi.Escritorio.getSize();
            Dimension FrameSize = abrirfrmFiltroProveedores.getSize();
            abrirfrmFiltroProveedores.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
      /*      abrirfrmFiltroProveedores.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MDIppi.class.getName()).log(Level.SEVERE, null, ex);
        }  
        */
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDocumento1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumento1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumento1KeyPressed

    private void txtDocumento1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumento1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumento1KeyTyped

    private void cbxTipoDocumento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumento1ActionPerformed

    private void txtNombreProveedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProveedor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProveedor1ActionPerformed

    private void txtNombreProveedor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedor1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProveedor1KeyPressed

    private void txtDirección1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDirección1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirección1KeyPressed

    private void txtTelefono1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono1KeyPressed

    private void txtTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono1KeyTyped

    private void cbxDepartamentos1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentos1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentos1ItemStateChanged

    private void cbxDepartamentos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDepartamentos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentos1ActionPerformed

    private void cbxDepartamentos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDepartamentos1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentos1KeyPressed

    private void cbxDepartamentos1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDepartamentos1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentos1KeyReleased

    private void jrbProveedor1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbProveedor1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbProveedor1ItemStateChanged

    private void jrbProveedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbProveedor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbProveedor1ActionPerformed

    private void jrbContratista1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbContratista1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbContratista1ItemStateChanged

    private void jrbContratista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbContratista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbContratista1ActionPerformed

    private void jrbAmbosTiposProveedor1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbAmbosTiposProveedor1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbAmbosTiposProveedor1ItemStateChanged

    private void jrbAmbosTiposProveedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAmbosTiposProveedor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbAmbosTiposProveedor1ActionPerformed

    private void jrbBien1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbBien1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbBien1ItemStateChanged

    private void jrbBien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbBien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbBien1ActionPerformed

    private void jrbServicio1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbServicio1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbServicio1ItemStateChanged

    private void jrbServicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbServicio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbServicio1ActionPerformed

    private void jrbAmbosTiposServicios1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbAmbosTiposServicios1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbAmbosTiposServicios1ItemStateChanged

    private void jrbAmbosTiposServicios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAmbosTiposServicios1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbAmbosTiposServicios1ActionPerformed

    private void jrbOrdenCompra1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbOrdenCompra1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbOrdenCompra1ItemStateChanged

    private void jrbOrdenCompra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbOrdenCompra1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbOrdenCompra1ActionPerformed

    private void jrbOrdenDespacho1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbOrdenDespacho1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbOrdenDespacho1ItemStateChanged

    private void jrbOrdenDespacho1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbOrdenDespacho1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbOrdenDespacho1ActionPerformed

    private void jrbNoaplica1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbNoaplica1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbNoaplica1ItemStateChanged

    private void jrbNoaplica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNoaplica1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbNoaplica1ActionPerformed

    private void jrbcontratosi1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbcontratosi1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbcontratosi1ItemStateChanged

    private void jrbcontratono1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbcontratono1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbcontratono1ItemStateChanged

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JComboBox<String> cbxDepartamentos;
    private javax.swing.JComboBox<String> cbxDepartamentos1;
    private javax.swing.JComboBox<String> cbxMunicipios;
    private javax.swing.JComboBox<String> cbxMunicipios1;
    private javax.swing.JComboBox<String> cbxTipoDocumento;
    private javax.swing.JComboBox<String> cbxTipoDocumento1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.ButtonGroup jbrgrContrato;
    private javax.swing.ButtonGroup jbrgrDocumentoCompra;
    private javax.swing.ButtonGroup jbrgrTipoServicio;
    private com.toedter.calendar.JDateChooser jdcFechaIngreso;
    private com.toedter.calendar.JDateChooser jdcFechaIngreso1;
    private javax.swing.JRadioButton jrbAmbosTiposProveedor;
    private javax.swing.JRadioButton jrbAmbosTiposProveedor1;
    private javax.swing.JRadioButton jrbAmbosTiposServicios;
    private javax.swing.JRadioButton jrbAmbosTiposServicios1;
    private javax.swing.JRadioButton jrbBien;
    private javax.swing.JRadioButton jrbBien1;
    private javax.swing.JRadioButton jrbContratista;
    private javax.swing.JRadioButton jrbContratista1;
    private javax.swing.JRadioButton jrbNoaplica;
    private javax.swing.JRadioButton jrbNoaplica1;
    private javax.swing.JRadioButton jrbOrdenCompra;
    private javax.swing.JRadioButton jrbOrdenCompra1;
    private javax.swing.JRadioButton jrbOrdenDespacho;
    private javax.swing.JRadioButton jrbOrdenDespacho1;
    private javax.swing.JRadioButton jrbProveedor;
    private javax.swing.JRadioButton jrbProveedor1;
    private javax.swing.JRadioButton jrbServicio;
    private javax.swing.JRadioButton jrbServicio1;
    private javax.swing.JRadioButton jrbcontratono;
    private javax.swing.JRadioButton jrbcontratono1;
    private javax.swing.JRadioButton jrbcontratosi;
    private javax.swing.JRadioButton jrbcontratosi1;
    private javax.swing.ButtonGroup jrbgrTiposProveedores;
    private javax.swing.JTextField txtContactoProveedor;
    private javax.swing.JTextField txtContactoProveedor1;
    private javax.swing.JTextField txtCorreoElectronico;
    private javax.swing.JTextField txtCorreoElectronico1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextArea txtDescripcion1;
    private javax.swing.JTextField txtDirección;
    private javax.swing.JTextField txtDirección1;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtDocumento1;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreProveedor1;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    // End of variables declaration//GEN-END:variables

     

}
