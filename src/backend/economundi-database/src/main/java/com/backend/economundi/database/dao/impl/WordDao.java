package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IWordDao;
import com.backend.economundi.database.dao.entity.Word;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordDao implements IWordDao {

    private final Connection conn;

    public WordDao() {
        this.conn = ConnectionFactory.getConnection();
    }

    @Override
    public Boolean create(Word word) {
        String sql = "INSERT INTO " + ENTITY + "(" + ID + "," + NAME
                + "," + DESCRIPTION + ")" + "VALUES (nextval('palavra_id_seq')"
                + ", ?, ?)";
        PreparedStatement stmt = null;
        Boolean success = false;

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, word.getName());
            stmt.setString(2, word.getDescription());
            success = stmt.execute();

            stmt.close();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                
            }
        } finally {
            try {
                if (conn != null & !conn.isClosed()) {
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
        
        return success;
    }

    @Override
    public Word readById(Long id) {
        String sql = "SELECT * FROM " + ENTITY + " w WHERE w.id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Word word = new Word();

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                word.setId(rs.getLong(ID));
                word.setName(rs.getString(NAME));
                word.setDescription(rs.getString(DESCRIPTION));
            }

            rs.close();
            stmt.close();
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
                if (rs != null && !rs.isClosed()) {
                    rs.close();
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

        return word;
    }

    @Override
    public void update(Word word) {
        String sql = "UPDATE " + ENTITY + " SET " + NAME + "= ?,"
                + DESCRIPTION + "= ? WHERE " + ID + "= ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, word.getName());
            stmt.setString(2, word.getDescription());
            stmt.setLong(3, word.getId());
            rs = stmt.executeQuery();

            rs.close();
            stmt.close();
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
                if (rs != null && !rs.isClosed()) {
                    rs.close();
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
    public void delete(Word word) {
        String sql = "DELETE FROM " + ENTITY + " WHERE " + ID + "= ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, word.getId());
            rs = stmt.executeQuery();

            rs.close();
            stmt.close();
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
                if (rs != null && !rs.isClosed()) {
                    rs.close();
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
}
