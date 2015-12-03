/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.beans.property.SimpleStringProperty;
 
public class Item {
   private final SimpleStringProperty name = new SimpleStringProperty("");
   private final SimpleStringProperty desc = new SimpleStringProperty("");
   private final SimpleStringProperty num = new SimpleStringProperty("");

public Item() {
        this("", "");
    }
 
    public Item(String name, String desc) {
        setName(name);
        setDesc(desc);
        //setEmail(email);
    }
    
    public Item(String name, String num,String desc) {
        setName(name);
        setDesc(desc);
        setNum(num);
        //setEmail(email);
    }

    public String getName() {
        return name.get();
    }
 
    public void setName(String fName) {
        name.set(fName);
    }
    public String getNum() {
        return num.get();
    }
 
    public void setNum(String fName) {
        num.set(fName);
    }
        
    public String getDesc() {
        return desc.get();
    }
    
    public void setDesc(String fName) {
        desc.set(fName);
    }
    
}