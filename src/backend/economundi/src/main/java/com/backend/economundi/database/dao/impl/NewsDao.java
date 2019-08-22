package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.INewsDao;
import com.backend.economundi.entity.News;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewsDao implements INewsDao {

    private final Connection conn;

    public NewsDao() {
        conn = ConnectionFactory.getConnection();
    }

    @Override
    public void create(News news) {
        String sql = "INSERT INTO " + ENTITY + "(" + ID + "," + TITLE +
                "," + DESCRIPTION + "," + CONTENT + "," + SOURCE + "," +
                URL_IMAGE + "," + URL + "," + LOCALITY + "," + RELEVANCE +
                ") VALUES (nextval('noticia_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?)";
                
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getDescription());
            stmt.setString(3, news.getContent());
            stmt.setString(4, news.getSource().getName());
            stmt.setString(5, news.getUrlToImage());
            stmt.setString(6, news.getUrl());
            stmt.setString(7, news.getLocality());
            stmt.setLong(8, news.getRelevance());
            stmt.execute();

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
