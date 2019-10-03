package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IWordDao;
import com.backend.economundi.database.dao.entity.WordEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class WordDao implements IWordDao {

    private Connection conn;
    
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String ENTITY = "word";

    @Override
    public void create(WordEntity word) {
        String sql = "INSERT INTO " + ENTITY + "(" + ID + "," + NAME
                + "," + DESCRIPTION + ") VALUES (nextval('word_id_seq')"
                + ", ?, ?) RETURNING " + ID;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, word.getName());
            stmt.setString(2, word.getDescription());
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                word.setId(rs.getLong(ID));
            }

            conn.commit();
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
        }
    }
    
	@Override
	public WordEntity read(Long id) {
        String sql = "SELECT * FROM " + ENTITY + " w WHERE w.id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        WordEntity word = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                word = new WordEntity();
                word.setId(rs.getLong(ID));
                word.setName(rs.getString(NAME));
                word.setDescription(rs.getString(DESCRIPTION));
            }
        } catch (SQLException ex) {

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
    public void update(WordEntity word) {
        String sql = "UPDATE " + ENTITY + " SET " + NAME + "= ?,"
                + DESCRIPTION + "= ? WHERE " + ID + "= ?";
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, word.getName());
            stmt.setString(2, word.getDescription());
            stmt.setLong(3, word.getId());
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
    public void delete(WordEntity word) {
        String sql = "DELETE FROM " + ENTITY + " WHERE " + ID + "= ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, word.getId());
            rs = stmt.executeQuery();

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
    public List<WordEntity> readByName(String name) {
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + NAME + " ilike "
                + "'" + name + "%' order by " + NAME + " asc limit 5";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<WordEntity> wordList = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                WordEntity word = new WordEntity();
                
                word.setId(rs.getLong(ID));
                word.setName(rs.getString(NAME));
                word.setDescription(rs.getString(DESCRIPTION));
                
                wordList.add(word);
            }
        } catch (SQLException ex) {

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

        return wordList;
    }
}
