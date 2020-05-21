package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Time;


/**
 *
 * @author Samuelson
 */
public class TimeDAO {

   
    
   public boolean create(Time p) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO times (nome,estadio)VALUES(?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEstadio());
                    
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
   
   public List<Time> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Time> times = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM times");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Time time = new Time();

                time.setId(rs.getInt("id"));
                time.setNome(rs.getString("nome"));
                time.setEstadio(rs.getString("estadio")); 
                times.add(time);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TimeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return times;

    }

   public void delete(Time p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM times WHERE id = ?");
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
