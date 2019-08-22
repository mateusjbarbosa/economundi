package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IWordDao;
import com.backend.economundi.entity.Word;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordDao implements IWordDao {

    private final Connection conn;

    public WordDao() {
        this.conn = ConnectionFactory.getConnection();
    }

    @Override
    public void create(Word word) {
        String sql = "INSERT INTO palavra (" + ID + "," + NAME
                + "," + DESCRIPTION + ")" + "VALUES (nextval('palavra_id_seq')"
                + ", ?, ?)";
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, word.getName());
            stmt.setString(2, word.getDescription());
            stmt.execute();

            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Word readById(Long id) {
        String sql = "SELECT * FROM " + ENTITY + " w WHERE w.id = ?";
        PreparedStatement stmt;
        ResultSet rs;
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
            System.out.println(ex);
        }

        return word;
    }

    @Override
    public void update(Word word) {
        String sql = "UPDATE " + ENTITY + " set " + NAME + "= ?,"
                + DESCRIPTION + "= ? WHERE " + ID + "= ?";

        PreparedStatement stmt;
        ResultSet rs;

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, word.getName());
            stmt.setString(2, word.getDescription());
            stmt.setLong(3, word.getId());
            rs = stmt.executeQuery();

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void delete(Word word) {
        String sql = "DELETE FROM " + ENTITY + " WHERE " + ID + "= ?";

        PreparedStatement stmt;
        ResultSet rs;

        try {
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, word.getId());
            rs = stmt.executeQuery();

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
