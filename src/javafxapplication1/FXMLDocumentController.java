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
    private TableColumn perkName;
    @FXML
    private TableColumn perkDesc;
    @FXML
    private TableColumn itemName;
    @FXML
    private TableColumn Quanity;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void SaveCreate(ActionEvent event) {
       //called when the Create/ Save button is pushed
        
        try{
            createConnection();
            Statement st = conn.createStatement();
            st.setQueryTimeout(30);  // set timeout to 30 sec.
            String createString;
       
            createString = "INSERT INTO Character VALUES(null,\"" + Name.getText() + "\",1,0,0);";
            st.executeUpdate(createString);
       
            ResultSet rs;
            st.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = st.executeQuery("Select charID from Character where cName = '" + Name.getText()+"';");
            st = conn.createStatement();
            String cha = rs.getString("charID");
       
            createString = "INSERT INTO SPECIAL VALUES(" + strInput.getText() + "," +
               perInput.getText() + "," + endInput.getText() + "," + chaInput.getText()
               + "," + intInput.getText() + "," +agiInput.getText() + "," + lucInput.getText()
               + "," + cha + ");";
            st.executeUpdate(createString);
            
            System.out.println("ti's done");
       
       }
       catch(Exception e){
           e.printStackTrace();
           label.setText("Error");
       }
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
       ResultSet rs;
      st.setQueryTimeout(30);  // set timeout to 30 sec.
      rs = st.executeQuery("Select charID from Character where cName = '"+Name.getText()+"'");
      st = conn.createStatement();
       String cha=rs.getString("charID");
       
      rs = st.executeQuery("Select * from special where charID = '"+cha+"'");
      strInput.setText(Integer.toString(rs.getInt("strength")));
        perInput.setText(Integer.toString(rs.getInt("perception")));
         endInput.setText(Integer.toString(rs.getInt("endurance")));
        chaInput.setText(Integer.toString(rs.getInt("charisma")));
         agiInput.setText(Integer.toString(rs.getInt("agility")));  
         intInput.setText(Integer.toString(rs.getInt("intelligence")));
        lucInput.setText(Integer.toString(rs.getInt("luck")));
     //   System.out.println("Name = " + rs.getString("name"));
       }
       catch(Exception e){
           e.printStackTrace();
           label.setText("Error");
       }
    }
    
    @FXML
    private void randomChar(ActionEvent event) {
        //System.out.println("You clicked me!");
       // double x = Math.random()*10;
        strInput.setText(Integer.toString((int) Math.ceil(Math.random()*10)));
        perInput.setText(Integer.toString((int) Math.ceil(Math.random()*10)));
         endInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));
        chaInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));
         agiInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));  
         intInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));
        lucInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));

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
