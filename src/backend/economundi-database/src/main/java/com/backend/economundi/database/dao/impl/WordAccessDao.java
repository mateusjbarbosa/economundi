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
    
    private static final String WORD_ACCESS_ID = "palavra_id";
    private static final String ENTITY_WORD_ACCESS = "palavra_acesso";
    private static final String ENTITY_WORD = "palavra";
    private static final String WORD_NAME = "nome";
    private static final String WORD_ID = "id";

    @Override
    public void create(Long id) {
        String sql = "INSERT INTO " + ENTITY_WORD_ACCESS + "(" + WORD_ACCESS_ID
                + ") VALUES (?)";
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
        String sql = "SELECT W." + WORD_NAME +", W." + WORD_ID + " FROM " +
                ENTITY_WORD  + " W INNER JOIN " + "(SELECT WA." + 
                WORD_ACCESS_ID + ", COUNT(*) AS total FROM " +
                ENTITY_WORD_ACCESS + " WA GROUP BY WA." + WORD_ACCESS_ID + ") "
                + "T1 on T1." +
                WORD_ACCESS_ID + "= W.id ORDER BY T1.total DESC, W." + 
                WORD_NAME + " LIMIT 5";
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
