/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgControlador;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Leider
 */
public class RegistroPedidoController implements Initializable {
    
    /**
     * Iniciando Conector.
     */
    ConectaDB conector=new ConectaDB();
    
    /**
     * Datos cliente.
     */
    @FXML private TextField txtNomcCliente;
    @FXML private TextField txtApeCliente;
    @FXML private TextField txtNitCi;
    
    /**
     * Datos Producto.
     */
    @FXML private TextField txtNomProducto;
    @FXML private TextField txtCantPoducto;
    @FXML private TextField txtCstProducto;
    
    /**
     * Datos Pedido.
     */
    @FXML private ComboBox comboProveedor=new ComboBox(llenarComboProveedor());
   
   // comboProveedor.getItems().addAll("s","f","l");
    @FXML private DatePicker datePedido;
    @FXML private DatePicker dateLLegada;
    @FXML private TextField txtCstPedido;
    
    @FXML
    private void btnRegistroPedidoAction(ActionEvent event){
        /**
         * Obtenemos datos del cliente.
         */
        String nombreCliente=txtNomcCliente.getText();
        String apellidoCliente=txtApeCliente.getText();
        String nitCi=txtNitCi.getText();
        int ciNit=Integer.parseInt(nitCi);//-->Parseando String to int.
        /**
         * Obtenemos datos del producto.
         */
        String nombreProducto=txtNomProducto.getText();
        String cantidadProducto=txtCantPoducto.getText();
        int productoCantidad=Integer.parseInt(cantidadProducto);//-->Parseando String to int.
        String costoProducto=txtCstProducto.getText();
        int productoCosto=Integer.parseInt(costoProducto);//-->Parseando String to int.
        /**
         * Obtenemos datos de pedido.
         */
        String nombreProveedor=comboProveedor.getValue().toString();
        String fechaPedido=datePedido.getValue().toString();
        String fechaLlegada=dateLLegada.getValue().toString();  
        String costoPedido=txtCstPedido.getText();
        int pedidoCosto=Integer.parseInt(costoPedido);//-->Parseando String to int.
        
        /**
         * System.err.println(nombreCliente);
        System.err.println(apellidoCliente);
        System.err.println(ciNit);
        System.err.println(nombreCliente);
        System.err.println(productoCantidad);
        System.err.println(productoCosto);
        System.err.println(nombreProveedor);
        System.err.println(fechaPedido);
        System.err.println(fechaLlegada);
        System.err.println(pedidoCosto);
         */
        
        /**
         * Resultset
         */
       
        ResultSet rs;
        
        try {
            rs=conector.consultar("select registrarPedido('"+nombreCliente+"','"+apellidoCliente+"',"+ciNit+",'"+nombreProducto+"',"+productoCantidad+","+productoCosto+",'"+nombreProveedor+"','"+fechaPedido+"','"+fechaLlegada+"',"+pedidoCosto+");");
            if (rs.next()) {
                String retorno=rs.getString("registrarPedido");
                if (retorno.equals("registrado")) {
                    System.err.println("Lo que viene desde pues, puede ser un mensaje o una redireccion de interfaz");
                } else {
                    System.err.println("Error al registrar el pedido");
                }
                
            } else {
                System.out.println("datos incorrector, consulte con el encargado de la base de datos");
            }
        } catch (Exception e) {
            System.out.println("Error en el registro de pedidos"+e);
        }
        
    }
    
     private ObservableList<String> llenarComboProveedor() { 

     ObservableList<String> items=FXCollections.observableArrayList();
    try {    
        ResultSet rs=null; 
        rs = conector.consultar("SELECT proveedor.nombre_prov FROM proveedor");
            System.out.println("104");
                while (rs.next()) {
                    items.add(rs.getString(1));
                    System.err.println(rs.getString(1) +"**");
            
        }
        
                } catch(Exception err){err.printStackTrace();} 
     return items;
} 


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboProveedor.setItems(llenarComboProveedor());
    }    
    
}
