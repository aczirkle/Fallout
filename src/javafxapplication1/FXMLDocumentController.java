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

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Andrew and Damon
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
 //   @FXML private TableColumn perkName;
   // @FXML private TableColumn perkDesc;
 //   @FXML private TableColumn itemName;
  //  @FXML private TableColumn Quanity;
    @FXML private TableView itemView;
    @FXML private TableView perkView;
    @FXML private TableView skillView;
    @FXML private TableView fullItemView;
 //   @FXML private TableColumn skillName;
 //   @FXML private TableColumn skillDesc;
	
 //   @FXML private TableColumn itemNameFull;
  //  @FXML private TableColumn itemDescFull;
    
    //TODO: Add in some comments and tell us what these are in the GUI please
    /*Other thongs to do for the rest of us to do what we need to do:
     *add in another tab for skills (could also be a character tab that shows all of a character's stats)
     *add in something that allows us to give characters stuff in their inventory
     *add in something that allows us to remove items from a character's inventory
     *  make sure that it includes quantity of items added
     *add in a item type thing to the inventory tab followed by a place to put in
        the stat for that specific item type.*/
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void Create(ActionEvent event) {
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
            
            //later TODO: actually, take out the skills altogether since Fallout 4 has only perks (keep in for this though)
            //Now add the character's skills to the database. Making them all 10 for now
            
            createString = "INSERT INTO Skills VALUES(10,10,10,10,10," + cha +");";
            st.executeUpdate(createString);
            
            //Skills(Lockpicking int, Hacking int, Sneak int, Speech int, Science int, charID INTEGER NOT NULL PRIMARY KEY references Character(charID)

            //System.out.println("ti's done");
       
       }
       catch(Exception e){
           e.printStackTrace();
           label.setText("Error");
       }
       // System.out.println("You clicked me!");
       // label.setText("Hello World!");
       
    }
    
    
    @FXML
    private void Save(ActionEvent event) {
        //called when the Save button is pushed
        //still called SaveCreate for convenience
        //TODO: implement the stuff that happens when the 
        
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
        
        //cha is the string of the charID and can be used in all the SQL statements
        String cha = rs.getString("charID");
       
        rs = st.executeQuery("Select * from special where charID = '" + cha + "'");
        strInput.setText(Integer.toString(rs.getInt("strength")));
        perInput.setText(Integer.toString(rs.getInt("perception")));
        endInput.setText(Integer.toString(rs.getInt("endurance")));
        chaInput.setText(Integer.toString(rs.getInt("charisma")));
        agiInput.setText(Integer.toString(rs.getInt("agility")));  
        intInput.setText(Integer.toString(rs.getInt("intelligence")));
        lucInput.setText(Integer.toString(rs.getInt("luck")));
     //   System.out.println("Name = " + rs.getString("name"));
     
        //TODO: add in the stuff for getting all of the character's data to be used in other tabs
        
        //getting the requested character's perks
        rs = st.executeQuery("SELECT perkName, perkInfo FROM Perks NATURAL JOIN HasPerks WHERE charID = " +
                cha + " ;");
        ArrayList<String> perkNames = new ArrayList<String>();
        ArrayList<String> perkDescs = new ArrayList<String>();
        
        while(rs.next()) {
            perkNames.add(rs.getString(1));
            perkDescs.add(rs.getString(2));
        }
        
       // displayCharPerks(perkNames, perkDescs);
       for(int i=0;i<perkNames.size();i++){
            addPerk(perkNames.get(i),perkDescs.get(i));  
        }
        
        //getting the requested character's inventory
        ArrayList<String> itemNames = new ArrayList<String>();
        ArrayList<String> itemQuants = new ArrayList<String>();
        ArrayList<String> itemIDs = new ArrayList<String>();
        rs = st.executeQuery("SELECT iID, iName, quantity FROM Items NATURAL JOIN HasItems\n" +
            "WHERE charID = " + cha + " ;");
        
        while(rs.next()) {
            itemIDs.add(rs.getString(1));
            itemNames.add(rs.getString(2));
            itemQuants.add(rs.getString(3));
        }
        
        for(int i=0;i<itemNames.size();i++){
            addItem(itemNames.get(i),itemQuants.get(i));  
        }
        /*now that we have the items a character has, it is time to figure out
         *we are going to find out what kinf of item each item is */
        /*TODO: to do this I am going to make three methods that check to see
         *the iID of each returned item then adds them to an ArrayList that is
         *returned. The returned ArrayList will then only consist of items of whatever
         *type that was being tested.*/
        
        
        ArrayList<String> charArmor = getCharArmor(itemIDs);
        ArrayList<String> charConsumes = getCharConsumes(itemIDs);
        ArrayList<String> charWeps = getCharWeps(itemIDs);
        //TODO: add get level stuff
        
        
        loadSkills(cha);
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
        //TODO: make it so that the randomly generated values always add up to 35
        strInput.setText(Integer.toString((int) Math.ceil(Math.random()*10)));
        perInput.setText(Integer.toString((int) Math.ceil(Math.random()*10)));
        endInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));
        chaInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));
        agiInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));  
        intInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));
        lucInput.setText(Integer.toString((int)Math.ceil(Math.random()*10)));

    }
    
     protected void addItem(String name,String desc) {
        ObservableList<Item> data = itemView.getItems();
        data.add(new Item(name, desc));
    }
     
     protected void addPerk(String name,String desc) {
        ObservableList<Item> data = perkView.getItems();
        data.add(new Item(name, desc));
    }
     
     protected void addSkill(String name,String desc) {
        ObservableList<Item> data = skillView.getItems();
        data.add(new Item(name, desc));
    }
     
     protected void loadItems(){
         createConnection();
         try{
         Statement st = conn.createStatement();
        ResultSet rs;
        st.setQueryTimeout(30);  // set timeout to 30 sec.
        rs = st.executeQuery("Select iID,iName from Items");
        while(rs.next()){
            ObservableList<Item> data = fullItemView.getItems();
            data.add(new Item(rs.getString(1), rs.getString(2)));
         }   
         }
         catch(Exception e){
             e.printStackTrace();
         }
     }
      
     
     
     protected void loadSkills(String charID){
         ArrayList<String> skillNames = new ArrayList<String>();
         skillNames.add("Lockpicking");
         skillNames.add("Hacking");
         skillNames.add("Sneak");
         skillNames.add("Speech");
         skillNames.add("Science");
         createConnection();
         try{
         Statement st = conn.createStatement();
        ResultSet rs;
        st.setQueryTimeout(30);  // set timeout to 30 sec.
        
        //Lockpicking int NOT NULL, Hacking int NOT NULL, Sneak int NOT NULL, Speech int NOT NULL, Science int NOT NULL, charID INTEGER NOT NULL PRIMARY KEY references Character(charID) ON DELETE CASCADE);

        rs = st.executeQuery("Select * from Skills where charID = '"+charID+"'");
      /*  while(rs.next()){
            ObservableList<Skill> data = fullItemView.getItems();
            data.add(new Skill(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
         }   */
      
         for(int i=0;i<skillNames.size();i++){
           ObservableList<Item> data = skillView.getItems();
              data.add(new Item(skillNames.get(i),rs.getString(i+1)));
         }
         }
         catch(Exception e){
             e.printStackTrace();
         }
     }

    Connection conn = null;
    void createConnection(){
        try {
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

    private void displayCharPerks(ArrayList<String> perkNames, ArrayList<String> perkDescs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //TODO: this is where someone is supposed to take the lists I had from the query then put them in the correct tab.
    }
}

private ArrayList<String> getCharWeps(ArrayList<String> itemIDs) {
       for(String item: itemIDs) {
        createConnection();
        ResultSet rs;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT iName, wStat FROM Weapons\n" +
                    "WHERE iID = " + item + " ;");
        
            ArrayList<String> ans = new ArrayList<String>();
            ArrayList<String> wStats = new ArrayList<String>();
            
            while(rs.next()) {
                ans.add(rs.getString(1));
                wStats.add(rs.getString(2));
            }//while loop
        
            displayCharWeps(ans, wStats);
            return ans;
        
        } catch(Exception e) {
            //Yeah, I am expecting this to sometimes catch nothing so an exception would be thrown
            //so do nothing
        }
    }
        return null;
    }

    private ArrayList<String> getCharConsumes(ArrayList<String> itemIDs) {
        for(String item: itemIDs) {
        createConnection();
        ResultSet rs;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT iName, info FROM Consumables\n" +
                    "WHERE iID = " + item + " ;");
        
            ArrayList<String> ans = new ArrayList<String>();
            ArrayList<String> infos = new ArrayList<String>();
            
            while(rs.next()) {
                ans.add(rs.getString(1));
                infos.add(rs.getString(2));
            }//while loop
        
            //other stuff goes here
            displayCharConsumables(ans, infos);
            return ans;
        
        } catch(Exception e) {
            //Yeah, I am expecting this to sometimes catch nothing so an exception would be thrown
            //so do nothing
        }
    }
        return null;
    }

    private ArrayList<String> getCharArmor(ArrayList<String> itemIDs) {
        for(String item: itemIDs) {
        createConnection();
        ResultSet rs;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT iName, armorBonus FROM Armor\n" +
                    "WHERE iID = " + item + " ;");
        
            ArrayList<String> ans = new ArrayList<String>();
            ArrayList<String> bonuses = new ArrayList<String>();
            
            while(rs.next()) {
                ans.add(rs.getString(1));
                bonuses.add(rs.getString(2));
            }//while loop
        
            //other stuff goes here
            displayCharArmor(ans, bonuses);
            return ans;
        
        } catch(Exception e) {
            //Yeah, I am expecting this to sometimes catch nothing so an exception would be thrown
            //so do nothing
        }
    }
        return null;
    }

    private void displayCharArmor(ArrayList<String> itemNames, ArrayList<String> wStats) {
		//TODO: put the character armor in the GUI
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void displayCharConsumables(ArrayList<String> itemNames, ArrayList<String> infos) {
        //TODO: put the character consumables in the GUI
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void displayCharWeps(ArrayList<String> itemNames, ArrayList<String> wStats) {
        //TODO: put the character Weapons in the GUI
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}