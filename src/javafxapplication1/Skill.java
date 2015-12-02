/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.beans.property.SimpleStringProperty;
 
public class Skill {
   private final SimpleStringProperty lock = new SimpleStringProperty("");
   private final SimpleStringProperty hack = new SimpleStringProperty("");
   private final SimpleStringProperty sneak = new SimpleStringProperty("");
   private final SimpleStringProperty speech = new SimpleStringProperty("");
   private final SimpleStringProperty science = new SimpleStringProperty("");



public Skill() {
        this("", "","","","");
    }
 
    public Skill(String lock, String hack,String sneak,String speech,String science) {
        setlock(lock);
        setHack(hack);
        setSneak(sneak);
        setSpeech(speech);
        setScience(science);
        
        //setEmail(email);
    }

    public String getlock() {
        return lock.get();
    }
    public String getHack() {
        return hack.get();
    }
    public String getSneak() {
        return sneak.get();
    }
    public String getSpeech() {
        return speech.get();
    }
    public String getScience() {
        return science.get();
    }
    public void setlock(String fName) {
        lock.set(fName);
    }

    public void setHack(String hack) {
            this.hack.set(hack);
        }

    public void setSneak(String sneak) {
        this.sneak.set(sneak);
    }

    public void setSpeech(String speech) {
        this.speech.set(speech);
    }

    public void setScience(String science) {
        this.science.set(science);
    }
        
    
}