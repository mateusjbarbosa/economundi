package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.INewsDao;
import com.backend.economundi.database.dao.entity.News;
import com.backend.economundi.database.dao.entity.Source;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDao implements INewsDao {

    private final Connection conn;

    public NewsDao() {
        conn = ConnectionFactory.getConnection();
    }

    @Override
    public void create(News news) {
        String sql = "INSERT INTO " + ENTITY + "(" + ID + "," + TITLE
                + "," + DESCRIPTION + "," + CONTENT + "," + SOURCE + ","
                + URL_IMAGE + "," + URL + "," + LOCALITY + "," + RELEVANCE
                + ") VALUES (nextval('noticia_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?)";

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
        }
    }

    @Override
    public void update(News news) {
        String sql = "UPDATE " + ENTITY + " SET " + RELEVANCE + "= ?"
                + " WHERE " + ID + "= ?";

        PreparedStatement stmt;
        ResultSet rs;

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, news.getRelevance());
            stmt.setLong(2, news.getId());
            rs = stmt.executeQuery();

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
        }
    }

    @Override
    public List<News> readNewsWithRelevance() {
        String sql = "SELECT * from " + ENTITY + " WHERE " + RELEVANCE + "> 0";
        List<News> newsList = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                News news = new News();
                
                news.setContent(rs.getString(CONTENT));
                news.setDate(rs.getString(DATE));
                news.setDescription(rs.getString(DESCRIPTION));
                news.setId(rs.getLong(ID));
                news.setLocality(rs.getString(LOCALITY));
                news.setRelevance(rs.getLong(RELEVANCE));
                news.setTitle(rs.getString(TITLE));
                news.setUrl(rs.getString(URL));
                news.setUrlToImage(rs.getString(URL_IMAGE));
                
                Source source = new Source();
                
                source.setName(rs.getString(SOURCE));
                news.setSource(source);
                
                newsList.add(news);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return newsList;
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
