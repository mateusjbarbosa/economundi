package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IWordAccessDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WordAccessDao implements IWordAccessDao{
    
    private Connection conn;

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
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                
            }
            
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                
            }
        }
    }
    
}
