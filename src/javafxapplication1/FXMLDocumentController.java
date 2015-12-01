/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author Andrew
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField strInput;
    @FXML
    private TextField perInput;
    @FXML
    private TextField endInput;
    @FXML
    private TextField chaInput;
    @FXML
    private TextField agiInput;
    @FXML
    private TextField intInput;
    @FXML
    private TextField lucInput;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void SaveCreate(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void load(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void randomChar(ActionEvent event) {
        //System.out.println("You clicked me!");
        int x = (int) Math.random()*10;
        strInput.setText("10");
        perInput.setText(""+x);
         endInput.setText("");
        chaInput.setText("");
         agiInput.setText("");      
         intInput.setText("");
        lucInput.setText("");

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
