/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgControlador;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Leider
 */
public class LoginController implements Initializable {
    
    ConectaDB conector=new ConectaDB();
    @FXML private Label lblMessage;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    
    @FXML
    private void btnIniciarAction(ActionEvent event){
        String usuario=txtUsername.getText();
        String passwd=txtPassword.getText();
        ResultSet res;
        
        try {
            res=conector.consultar("Select autentificacionUser('"+usuario+"','"+passwd+"' );");
            if (res.next()) {
                String retorno=res.getString("autentificacionUser");
                if (retorno.equals("correcto")) {
                //lblMessage.setText("Bienvenido : "+usuario);
                    System.out.println("***");
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/pkgModelo/principal.fxml"));
                    
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("/pkgEstilo/principal.css").toExternalForm());
	 
                    primaryStage.setScene(scene);
                    primaryStage.show();
                                            
                } else {
                    
                    lblMessage.setText("username or password invalid !!!");
                    
                }
                
            } else {
                System.err.println("Datos Incorrecto");
            }
        } catch (Exception e) {
            System.err.println("Error : "+e);
        }
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
