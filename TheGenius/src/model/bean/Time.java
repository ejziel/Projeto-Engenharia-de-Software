/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Samuelson
 */
public class Time {
    
    private int id;
    private String nome;
    private String estadio;
    private final ArrayList<Jogador> players = new ArrayList<>();
    private ImageIcon logo;
    private float mediaEstrela = 0;
    private float mediaIdade = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public ImageIcon getLogo() {
        return logo;
    }

    public void setLogo(ImageIcon logo) {
        this.logo = logo;
    }

    public float getMediaEstrela() {
        return mediaEstrela;
    }

    public void setMediaEstrela(float mediaEstrela) {
        this.mediaEstrela = mediaEstrela;
    }

    public float getMediaIdade() {
        return mediaIdade;
    }

    public void setMediaIdade(float mediaIdade) {
        this.mediaIdade = mediaIdade;
    }

    public void setJogador(Jogador j){
        this.players.add(j);
    }
    
    public String getIdAndName(){
        return this.id +"-"+ this.nome;
    }
    
    @Override
    public String toString() {
        return getIdAndName(); //To change body of generated methods, choose Tools | Templates.
    }
}
