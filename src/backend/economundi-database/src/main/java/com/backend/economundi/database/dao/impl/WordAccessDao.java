package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IWordAccessDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordAccessDao implements IWordAccessDao{
    
    private Connection conn;
    
    public static String WORD_ID = "palavra_id";
    public static String ENTITY = "palavra_acesso";

    @Override
    public void create(Long id) {
        String sql = "INSERT INTO " + ENTITY + "(" + WORD_ID + ") VALUES (?)";
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, id);
            stmt.execute();
            
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                
            }
        } finally {
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                
            }
            
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                
            }
        }
    }

    @Override
    public Map<Long, String> getMostSearched() {
        String sql = "SELECT P.id, P.nome FROM palavra P INNER JOIN ("
                + "SELECT PA." + WORD_ID + ", COUNT(*) AS total FROM " +
                ENTITY + " PA GROUP BY PA." + WORD_ID + ") T1 on T1." +
                WORD_ID + "= P.id ORDER BY T1.total DESC, P.nome LIMIT 5";
        Map<Long, String> wordMap = new HashMap<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                wordMap.put(rs.getLong("id"), rs.getString("nome"));
            }
        } catch (SQLException ex) {
            
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException ex) {
                
            }
            
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                
            }
            
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(WordAccessDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return wordMap;
    }
    
    
    
}
