/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Enzo
 */
public class Clientes extends javax.swing.JFrame {
    DefaultTableModel model;
    String cod,ape,nomb,tel,iiva;
    public Clientes() {
        initComponents();
        cargar("");
        //deshabilitar();
    }
    void limpiar(){
        cli_num.setText("");
        cli_ap.setText("");
        cli_nom.setText("");
        cli_tel.setText("");
    }
    void cargar(String valor){
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
        t_tabclientes.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+e);
        }
    }
    void guardar(){
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        
        String sql="";
        cod=cli_num.getText();
        ape=cli_ap.getText();
        nomb=cli_nom.getText();
        tel=cli_tel.getText();
        String ivarespon=combocli_iva.getSelectedItem().toString();
        sql="INSERT INTO clientes (cli_cod, cli_nom, cli_ap, cli_tel, cli_iva) VALUES (?,?,?,?,?)";
        try {
        PreparedStatement psd= cn.prepareStatement(sql);
        psd.setString(1,cod);
        psd.setString(2,ape);
        psd.setString(3,nomb);
        psd.setString(4,tel);
        psd.setString(5,ivarespon);
        int n=psd.executeUpdate();
        if(n>0){
            JOptionPane.showMessageDialog(null,"REGISTRO GUARDADO CON EXITO!");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e);
        }
        cargar("");
        limpiar();
    }
    void eliminar(){
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        cod=cli_num.getText();
        String sql ="DELETE FROM clientes WHERE cli_cod=?";
        int resp;
        resp = JOptionPane.showConfirmDialog(null,"¿Realmente desea eliminar?","Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION){
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            psd.setString(1,cod);
            int x= psd.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error :"+e);
        }
        }
        cargar("");
    }
    void modificar(){
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        /*codd=t_codigo.getText();
        insu=t_insumos.getText();
        cant=t_cantidad.getText();
        preci=t_precio.getText();
        iva=t_iva.getText();*/
        String sql="UPDATE clientes SET cli_ap='"+cli_ap.getText()+"', cli_nom= '"+cli_nom.getText()+"', cli_tel='"+cli_tel.getText()+"', cli_iva='"+combocli_iva.getSelectedItem().toString()+"' WHERE cli_nom='"+cli_num.getText()+"'";
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
        cargar("");
        }
    }
    void habilitar(){
        cli_num.setEnabled(true);
        cli_ap.setEnabled(true);
        cli_nom.setEnabled(true);
        cli_tel.setEnabled(true);
        combocli_iva.setEnabled(true);
    }
    /*void deshabilitar(){
        cli_num.setEnabled(false);
        cli_ap.setEnabled(false);
        cli_nom.setEnabled(false);
        cli_tel.setEnabled(false);
        combocli_iva.setEnabled(false);
        
    }*/

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cli_tel = new javax.swing.JTextField();
        cli_num = new javax.swing.JTextField();
        cli_nom = new javax.swing.JTextField();
        cli_ap = new javax.swing.JTextField();
        btn_cguardar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_celiminar = new javax.swing.JButton();
        btn_cmodificar = new javax.swing.JButton();
        btn_climpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        combocli_iva = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        cli_buscar = new javax.swing.JTextField();
        btn_catras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_tabclientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nº Cliente");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Apellido");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tel/Cel");

        btn_cguardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/Save.png"))); // NOI18N
        btn_cguardar.setText("Guardar");
        btn_cguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cguardarActionPerformed(evt);
            }
        });

        btn_nuevo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/newl.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_celiminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_celiminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/Delete.png"))); // NOI18N
        btn_celiminar.setText("Eliminar");
        btn_celiminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_celiminarActionPerformed(evt);
            }
        });

        btn_cmodificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/editl_16.png"))); // NOI18N
        btn_cmodificar.setText("Modificar");
        btn_cmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cmodificarActionPerformed(evt);
            }
        });

        btn_climpiar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_climpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/clear.png"))); // NOI18N
        btn_climpiar.setText("Limpiar");
        btn_climpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_climpiarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        combocli_iva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combocli_iva.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Responsable Inscripto", "Consumidor Final", "Monotributista" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combocli_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combocli_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cguardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_cmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_celiminar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_climpiar))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cli_tel, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(cli_nom)
                    .addComponent(cli_ap)
                    .addComponent(cli_num))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cli_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2))
                    .addComponent(cli_ap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cli_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cli_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_celiminar)
                    .addComponent(btn_nuevo))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cguardar)
                    .addComponent(btn_cmodificar)
                    .addComponent(btn_climpiar))
                .addGap(22, 22, 22))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cli_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cli_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        btn_catras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_catras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/go-back.png"))); // NOI18N
        btn_catras.setText("<< Atras");
        btn_catras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_catrasActionPerformed(evt);
            }
        });

        t_tabclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        t_tabclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_tabclientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_tabclientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_catras))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_catras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_catrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_catrasActionPerformed
        Principal prin = new Principal();
        prin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_catrasActionPerformed

    private void btn_climpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_climpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_climpiarActionPerformed

    private void btn_cguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cguardarActionPerformed
        guardar();
        limpiar();
        //deshabilitar();
    }//GEN-LAST:event_btn_cguardarActionPerformed

    private void btn_cmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cmodificarActionPerformed
        modificar();
        limpiar();
        //deshabilitar();
    }//GEN-LAST:event_btn_cmodificarActionPerformed

    private void btn_celiminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_celiminarActionPerformed
        eliminar();
        limpiar();
        //deshabilitar();
    }//GEN-LAST:event_btn_celiminarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        habilitar();
        limpiar();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void t_tabclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_tabclientesMouseClicked
        int fila=t_tabclientes.getSelectedRow();
        if(fila>=0){
                cli_num.setText(t_tabclientes.getValueAt(fila,0).toString());
                cli_ap.setText(t_tabclientes.getValueAt(fila,1).toString());
                cli_nom.setText(t_tabclientes.getValueAt(fila,2).toString());
                cli_tel.setText(t_tabclientes.getValueAt(fila,3).toString());
                combocli_iva.setSelectedItem(t_tabclientes.getValueAt(fila,4).toString());
        }
    }//GEN-LAST:event_t_tabclientesMouseClicked

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
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_catras;
    private javax.swing.JButton btn_celiminar;
    private javax.swing.JButton btn_cguardar;
    private javax.swing.JButton btn_climpiar;
    private javax.swing.JButton btn_cmodificar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JTextField cli_ap;
    private javax.swing.JTextField cli_buscar;
    private javax.swing.JTextField cli_nom;
    private javax.swing.JTextField cli_num;
    private javax.swing.JTextField cli_tel;
    private javax.swing.JComboBox combocli_iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_tabclientes;
    // End of variables declaration//GEN-END:variables
}
