/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Time;
import model.bean.Jogador;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ejziel
 */
public class TimeDAOTest {

    private Time time;
    private TimeDAO dao;
    private Jogador jogador;
    
    public TimeDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        time = new Time();
        time.setNome("Bahia");
        time.setEstadio("Fonte Nova");
        dao = new TimeDAO();
        jogador = new Jogador();
    }
    
    @After
    public void tearDown() {
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM times");
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }

    /**
     * Test of create method, of class TimeDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        
        boolean expresult = true; //Resultado Esperado
        boolean result = dao.create(time);
        
        assertEquals(expresult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testCreateFalse() {
        System.out.println("create");
        
        boolean expresult = false; //Resultado Esperado
        boolean result = dao.create(time);
        
        assertEquals(expresult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of read method, of class TimeDAO.
     */
    /*
    @Test
    public void testRead() {
        System.out.println("read");
        
        List<Time> expResult = null; //
        
        List<Time> result = dao.read();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    */
}
