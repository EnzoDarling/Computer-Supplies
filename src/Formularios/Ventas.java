
package Formularios;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.text.*;
public class Ventas extends javax.swing.JFrame {
 DefaultTableModel model;
 String codvent,numventa,codcli,codproduct,detallevent,cantventa,unitventa,totalventa,responventa,pagoventa;
 int preci=0;
 int cant=0;
 int total=0;
 String precio,cantidad,tot;  
     public Ventas() {
        initComponents();
        deshabilitar();
        cargarventas("");
        cargarclientes("");
        cargarproductos("");
    }
    void cargarventas(String valor){
       String [] titulos={"Codigo Ventas","Codigo Clientes","Codigo Productos","Detalles","Cantidad","Prec. Unitario","Prec. Total","Responsable IVA","Tipo Pago","Fecha"};
        String [] registros = new String[20];
        String sql = "SELECT * FROM ventas WHERE vent_cod LIKE '%"+valor+"%' ORDER BY vent_cod ASC";
        model = new DefaultTableModel (null,titulos);
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            registros[0]=rs.getString("vent_cod");
            registros[1]=rs.getString("cli_cod");
            registros[2]=rs.getString("codigo");
            registros[3]=rs.getString("vent_detalle");
            registros[4]=rs.getString("vent_cantidad");
            registros[5]=rs.getString("vent_precunitario");
            registros[6]=rs.getString("vent_prectotal");
            registros[7]=rs.getString("vent_respon");
            registros[8]=rs.getString("vent_tipopago");
            model.addRow(registros);
        }
        tab_ventas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+e);
        } 
    }
    void cargarclientes(String valor){
        String [] titulos={"Codigo","Nombre","Apellido","Telefono","IVA"};
        String [] registros = new String[5];
        String sql = "SELECT * FROM clientes WHERE cli_cod LIKE '%"+valor+"%' ORDER BY cli_cod ASC";
        model = new DefaultTableModel (null,titulos);
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            registros[0]=rs.getString("cli_cod");
            registros[1]=rs.getString("cli_nom");
            registros[2]=rs.getString("cli_ap");
            registros[3]=rs.getString("cli_tel");
            registros[4]=rs.getString("cli_iva");
            model.addRow(registros);
        }
        t_cliente.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+e);
        }        
    }
    void cargarproductos(String valor){
        String [] titulos={"Codigo","Insumos","En Stock","Precio Unit."};
        String [] registros = new String[5];
        String sql = "SELECT * FROM productos WHERE codigo LIKE '%"+valor+"%' ORDER BY codigo ASC";
        model = new DefaultTableModel (null,titulos);
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            registros[0]=rs.getString("codigo");
            registros[1]=rs.getString("insumos");
            registros[2]=rs.getString("cantidad");
            registros[3]=rs.getString("precunitario");
            model.addRow(registros);
        }
        t_datos.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+e);
        }
    }
    void guardar(){
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        String sql="";
        /*String di,me,años;
        int dia=fechaventa.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes=fechaventa.getCalendar().get(Calendar.MONTH)+1;
        int año=fechaventa.getCalendar().get(Calendar.YEAR);
        di=Integer.toString(dia);
        me=Integer.toString(mes);
        años=Integer.toString(año);*/
        //SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
//        String d= sdf.format(fechaventa.getDate());
        numventa=t_codventa.getText();
        codcli=t_codcli.getText();
        codproduct=cod_producto.getText();
        detallevent=t_detalles.getText();
        cantventa=t_cantidad.getText();
        unitventa=t_preunit.getText();
        totalventa=t_prectotal.getText();
        String value1=combo_respons.getSelectedItem().toString();
        String value2=combopago.getSelectedItem().toString();        
        sql="INSERT INTO ventas (vent_cod, vent_detalle, vent_cantidad, vent_precunitario, vent_prectotal, vent_respon, vent_tipopago) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            psd.setString(1,codcli);
            psd.setString(1,codproduct);
            psd.setString(2,detallevent);
            psd.setString(3,cantventa);
            psd.setString(4,unitventa);
            psd.setString(5,totalventa);
            psd.setString(6,value1);
            psd.setString(7,value2);
            //psd.setDate(8,sdf.format(fechaventa.getDate()));
            int n=psd.executeUpdate();
         if(n>0){
            JOptionPane.showMessageDialog(null,"REGISTRO GUARDADO CON EXITO!");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e);
        }
        //codventa=codventa+1;
    }
    void eliminar(){
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        codvent=ventbuscar.getText();
        String sql ="DELETE FROM ventas WHERE vent_cod=?";
        int resp;
        resp = JOptionPane.showConfirmDialog(null,"¿Realmente desea eliminar?","Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION){
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            psd.setString(1,codvent);
            int x= psd.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error :"+e);
        }
        }
        cargarventas("");
    }
    void modificar(){
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        /*codd=t_codigo.getText();
        insu=t_insumos.getText();
        cant=t_cantidad.getText();
        preci=t_precio.getText();
        iva=t_iva.getText();*/
        String sql="UPDATE ventas SET vent_detalle='"+t_detalles.getText()+"', vent_cantidad='"+t_cantidad.getText()+"', vent_precunitario= '"+t_preunit.getText()+"', vent_prectotal='"+t_prectotal.getText()+"', combo_respons='"+combo_respons.getSelectedItem().toString()+"', vent_tipopago='"+combopago.getSelectedItem().toString()+"' WHERE vent_cod='"+ventbuscar.getText()+"'";
        int resp;
        resp = JOptionPane.showConfirmDialog(null,"¿Realmente desea modificar?","Alerta!", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION){
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            int x = psd.executeUpdate();
            if(x==1){
                JOptionPane.showMessageDialog(null,"Registro modificado");
            }else{
                JOptionPane.showMessageDialog(null,"No se existe ningun registro con ese codigo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error :"+e);
        }
        cargarventas("");
        }
    }
    void habilitar(){
        t_codcli.setEnabled(true);
        cod_producto.setEnabled(true);
        t_detalles.setEnabled(true);
        t_cantidad.setEnabled(true);
        t_preunit.setEnabled(true);
        t_prectotal.setEnabled(true);
        combo_respons.setEnabled(true);
        combopago.setEnabled(true);        
    }
    void deshabilitar(){
        t_codcli.setEnabled(false);
        cod_producto.setEnabled(false);
        t_detalles.setEnabled(false);
        t_cantidad.setEnabled(false);
        t_preunit.setEnabled(false);
        t_prectotal.setEnabled(false);
        combo_respons.setEnabled(false);
        combopago.setEnabled(false);
    }
    void limpiar(){
        t_codcli.setText("");
        cod_producto.setText("");
        t_detalles.setText("");
        t_cantidad.setText("");
        t_preunit.setText("");
        t_prectotal.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        t_prectotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        t_detalles = new javax.swing.JTextField();
        t_cantidad = new javax.swing.JTextField();
        t_preunit = new javax.swing.JTextField();
        combo_respons = new javax.swing.JComboBox();
        combopago = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cod_producto = new javax.swing.JTextField();
        btn_modificar = new javax.swing.JButton();
        btn_ventatras = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        t_codcli = new javax.swing.JTextField();
        t_codventa = new javax.swing.JLabel();
        btn_nuevo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ventbuscar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        btn_eliminar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_ventas = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_datos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        t_cliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nº de Venta");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Codigo Cliente");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Detalles Producto");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cantidad");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Precio Unitario");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Precio Total");

        t_detalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_detallesActionPerformed(evt);
            }
        });

        t_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_cantidadKeyPressed(evt);
            }
        });

        combo_respons.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Responsable Inscripto", "Consumidor Final", "Monotributista" }));

        combopago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Tarj. de Credito" }));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Codigo Producto");

        btn_modificar.setBackground(new java.awt.Color(204, 204, 204));
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_ventatras.setBackground(new java.awt.Color(204, 204, 204));
        btn_ventatras.setText("<< Atras");
        btn_ventatras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ventatrasActionPerformed(evt);
            }
        });

        btn_limpiar.setBackground(new java.awt.Color(204, 204, 204));
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        btn_guardar.setBackground(new java.awt.Color(204, 204, 204));
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        t_codventa.setBackground(new java.awt.Color(204, 204, 204));
        t_codventa.setForeground(new java.awt.Color(255, 255, 255));

        btn_nuevo.setBackground(new java.awt.Color(204, 204, 204));
        btn_nuevo.setText("Nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_modificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_respons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combopago, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t_prectotal, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t_preunit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(t_detalles, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cod_producto)
                                    .addComponent(t_codcli, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                    .addComponent(t_codventa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btn_ventatras))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(t_codventa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(t_codcli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cod_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(t_detalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(t_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(t_preunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(t_prectotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(combo_respons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_nuevo)
                    .addComponent(btn_limpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_modificar)
                    .addComponent(btn_guardar))
                .addGap(30, 30, 30)
                .addComponent(btn_ventatras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Ventas"));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));

        jButton3.setText("Mostrar Todo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(204, 204, 204));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, 0, 91, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(ventbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_eliminar)
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ventbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar))
                .addContainerGap())
        );

        tab_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_ventasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_ventas);

        jTabbedPane1.addTab("Ventas", jScrollPane2);

        t_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        t_datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_datosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_datos);

        jTabbedPane1.addTab("Productos", jScrollPane1);

        t_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        t_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_clienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(t_cliente);

        jTabbedPane1.addTab("Clientes", jScrollPane3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 480, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ventatrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ventatrasActionPerformed
        Principal prin= new Principal();
        prin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_ventatrasActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        guardar();
        deshabilitar();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void t_detallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_detallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_detallesActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        habilitar();
        /*String cod1=Integer.toString(codventa);
        t_codventa.setText(cod1);
        codventa=codventa+1;*/
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void t_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_clienteMouseClicked
int fila=t_cliente.getSelectedRow();
        if(fila>=0){
            t_codcli.setText(t_cliente.getValueAt(fila,0).toString());           
        }else{
            JOptionPane.showMessageDialog(null,"No se seleccionó ninguna fila");
            }      }//GEN-LAST:event_t_clienteMouseClicked

    private void t_datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_datosMouseClicked
        habilitar();
        
        int fila=t_datos.getSelectedRow();
        if(fila>=0){
            cod_producto.setText(t_datos.getValueAt(fila,0).toString());
            t_detalles.setText(t_datos.getValueAt(fila,1).toString());
            t_preunit.setText(t_datos.getValueAt(fila,3).toString());
            
        }else{
            JOptionPane.showMessageDialog(null,"No se seleccionó ninguna fila");
            }      }//GEN-LAST:event_t_datosMouseClicked

    private void tab_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_ventasMouseClicked
    int fila=tab_ventas.getSelectedRow();
        if(fila>=0){
            t_detalles.setText(tab_ventas.getValueAt(fila,0).toString());
            t_cantidad.setText(tab_ventas.getValueAt(fila,1).toString());
            t_preunit.setText(tab_ventas.getValueAt(fila,2).toString());
            t_prectotal.setText(tab_ventas.getValueAt(fila,3).toString());            
        }else{
            JOptionPane.showMessageDialog(null,"No se seleccionó ninguna fila");
            }    
    }//GEN-LAST:event_tab_ventasMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cargarventas("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        modificar();// TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        eliminar();// TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void t_cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cantidadKeyPressed
            /*precio=t_preunit.getText();
            cantidad=t_cantidad.getText();
            preci=Integer.parseInt(precio);
            cant=Integer.parseInt(cantidad);
            total=(cant*preci);
            tot=Integer.toString(total);
            t_prectotal.setText(tot);*/
    }//GEN-LAST:event_t_cantidadKeyPressed

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
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_ventatras;
    private javax.swing.JTextField cod_producto;
    private javax.swing.JComboBox combo_respons;
    private javax.swing.JComboBox combopago;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField t_cantidad;
    private javax.swing.JTable t_cliente;
    private javax.swing.JTextField t_codcli;
    private javax.swing.JLabel t_codventa;
    private javax.swing.JTable t_datos;
    private javax.swing.JTextField t_detalles;
    private javax.swing.JTextField t_prectotal;
    private javax.swing.JTextField t_preunit;
    private javax.swing.JTable tab_ventas;
    private javax.swing.JTextField ventbuscar;
    // End of variables declaration//GEN-END:variables
}
