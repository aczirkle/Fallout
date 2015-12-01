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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private TextField Name;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void SaveCreate(ActionEvent event) {
       // System.out.println("You clicked me!");
       // label.setText("Hello World!");
       
    }
    
    @FXML
    private void load(ActionEvent event) {
        //System.out.println("You clicked me!");
       // label.setText("Hello World!");
       try{
       createConnection();
       Statement st = conn.createStatement();
      st.setQueryTimeout(30);  // set timeout to 30 sec.
       String cha="1";
       ResultSet rs;
      rs = st.executeQuery("Select * from special where charID = '"+cha+"'");
      System.out.println("Str = " + rs.getInt("strength"));
       System.out.println("End = " + rs.getInt("endurance"));
       intInput.setText(Integer.toString(rs.getInt("endurance")));
     //   System.out.println("Name = " + rs.getString("name"));
       }
       catch(Exception e){
           e.printStackTrace();
       }
    }
    
    @FXML
    private void randomChar(ActionEvent event) {
        //System.out.println("You clicked me!");
       // double x = Math.random()*10;
        strInput.setText(Double.toString(Math.ceil(Math.random()*10)));
        perInput.setText(Double.toString(Math.ceil(Math.random()*10)));
         endInput.setText(Double.toString(Math.ceil(Math.random()*10)));
        chaInput.setText(Double.toString(Math.ceil(Math.random()*10)));
         agiInput.setText(Double.toString(Math.ceil(Math.random()*10)));  
         intInput.setText(Double.toString(Math.ceil(Math.random()*10)));
        lucInput.setText(Double.toString(Math.ceil(Math.random()*10)));

    }

    Connection conn = null;
    void createConnection(){
        try
    {
      // create a database connection
      conn = DriverManager.getConnection("jdbc:sqlite:Fallout.db");
      Statement st = conn.createStatement();
      st.setQueryTimeout(30);  // set timeout to 30 sec.
    }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
