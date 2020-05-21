/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
/**
 *
 * @author ziel_
 */
public class Amistoso implements Serializable{
    
    
    private int placarTimeA;
    private int placarTimeB;
    private Time timeA;
    private Time timeB;
        
    public void setTimes(Time timeA, Time timeB){
        this.timeA = timeA;
        this.timeB = timeB;
    }
    
    public Time getTimeA(){
        return this.timeA;
    }
    
    public Time getTimeB(){
        return this.timeB;
    }
    
    public int getPlacarTimeA(){
        return this.placarTimeA;
    }
    
    public int getPlacarTimeB(){
        return this.placarTimeB;
    }

    
    public void jogar(JList jListEventos){
        int i=0;
        int acrescimo = new Random().nextInt(5) + 1;
            DefaultListModel lista = new DefaultListModel();
            jListEventos.setModel(lista);
        
        
        lista.addElement("Comeca o jogo no "+this.timeA.getEstadio());    
        while(i<=90+acrescimo){
            
            lista.addElement("Minuto "+i);
            int valor = new Random().nextInt(100) + 1;
            if(valor>85){
                fazerGol(jListEventos, lista);
            
            }
            
            if(i==90){
                lista.addElement("Teremos "+acrescimo+" minutos de acrescimo");              
            }
              i+=5;
        }
        
        lista.addElement("Fim de jogo");
               
    }
    
    
    private void fazerGol(JList jListEventos, DefaultListModel lista2){
    
        int valor = new Random().nextInt(100) + 1;
            jListEventos.setModel(lista2);
        
        
        if(timeA.getMediaEstrela() > timeB.getMediaEstrela()){
            valor-=10;
        }else if(timeA.getMediaEstrela() < timeB.getMediaEstrela()){
            valor+=10;
        }
        
        if(timeA.getMediaIdade() < timeB.getMediaIdade()){
            valor-=10;
        }else if(timeA.getMediaIdade() > timeB.getMediaIdade()){
            valor+=10;
        }
        
        if(valor<55){ //55 pelo fator time da casa
            placarTimeA++;
            
            lista2.addElement("Gooool do "+ timeA.getNome());
            lista2.addElement(jogadorGol(timeA)+" "+gritoDeGol());
        }else{
            placarTimeB++;
            
            lista2.addElement("Gooool do "+ timeB.getNome());
            lista2.addElement(jogadorGol(timeB)+" "+gritoDeGol());
        }
    }
    
    private String gritoDeGol(){
        String gritoGol;
        ArrayList<String> gols = new ArrayList<>();
        
        try{
            FileReader fReader = new FileReader("gols.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            while((gritoGol = bReader.readLine()) != null){
                gols.add(gritoGol);
            }
            bReader.close();
            fReader.close();
        }
        catch(Exception e){
            e.toString();
        }
        
        int aux = new Random().nextInt(gols.size());
        
        return gols.get(aux);
    }
    
    private String jogadorGol(Time time){
        
        int aux = new Random().nextInt(time.sizePlayer());
        
        return time.getPlayer(aux).getNome();
    }
}
