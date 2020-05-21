/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Jogador;

/**
 *
 * @author Samuelson
 */
public class JogadorDAO {

    public void create(Jogador p) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO jogador (nome,estrela,idade,posicao,time_id)VALUES(?,?,?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getEstrela());
            stmt.setInt(3, p.getIdade());
            stmt.setString(4, p.getPosicao());
            stmt.setInt(5, p.getTime_id());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Jogador> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Jogador> jogadores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM jogador");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Jogador jogador = new Jogador();

                jogador.setId(rs.getInt("id"));
                jogador.setNome(rs.getString("nome"));
                jogador.setEstrela(rs.getInt("estrela"));
                jogador.setIdade(rs.getInt("idade"));
                jogador.setPosicao(rs.getString("posicao"));
                jogador.setTime_id(rs.getInt("time_id"));
                jogadores.add(jogador);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return jogadores;

    }
    
    public List<Jogador> readForTeam (int timeID) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Jogador> jogadores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM jogador WHERE time_id = ?");
            stmt.setInt(1, timeID);
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Jogador jogador = new Jogador();

                jogador.setId(rs.getInt("id"));
                jogador.setNome(rs.getString("nome"));
                jogador.setEstrela(rs.getInt("estrela"));
                jogador.setIdade(rs.getInt("idade"));
                jogador.setPosicao(rs.getString("posicao"));
                jogador.setTime_id(rs.getInt("time_id"));
                jogadores.add(jogador);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return jogadores;

    }

    public void update(Jogador p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE jogador SET nome = ? ,estrela = ?,idade = ?,time_id = ?, posicao = ? WHERE id = ?");
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getEstrela());
            stmt.setInt(3, p.getIdade());
            stmt.setString(4, p.getPosicao());
            stmt.setInt(5, p.getTime_id());
            stmt.setInt(6, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Jogador p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM jogador WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
