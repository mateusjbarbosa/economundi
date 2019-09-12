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
import org.springframework.stereotype.Repository;

@Repository
public class NewsDao implements INewsDao {

    private Connection conn;
    
    private static final String ID = "id";
    private static final String TITLE = "manchete";
    private static final String DESCRIPTION = "descricao";
    private static final String CONTENT = "conteudo";
    private static final String SOURCE = "fonte";
    private static final String URL = "link";
    private static final String URL_IMAGE = "link_imagem";
    private static final String LOCALITY = "localidade";
    private static final String RELEVANCE = "engajamento";
    private static final String ENTITY = "noticia";
    private static String DATE = "data_hora";

    @Override
    public void create(News news) {
        String sql = "INSERT INTO " + ENTITY + "(" + ID + "," + TITLE
                + "," + DESCRIPTION + "," + CONTENT + "," + SOURCE + ","
                + URL_IMAGE + "," + URL + "," + LOCALITY + "," + RELEVANCE
                + ") VALUES (nextval('noticia_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
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
            
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1.getMessage());
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
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void update(News news) {
        String sql = "UPDATE " + ENTITY + " SET " + RELEVANCE + "= ?"
                + " WHERE " + ID + "= ?";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, news.getRelevance());
            stmt.setLong(2, news.getId());
            stmt.execute();
            
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                
            }
        } finally {
            try {
                if (stmt != null & !stmt.isClosed()) {
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
    public List<News> readNewsWithRelevance() {
        String sql = "SELECT * from " + ENTITY + " WHERE " + RELEVANCE + "> 0";
        List<News> newsList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
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
        
        return newsList;
    }

    @Override
    public List<News> readByPage(Long pageBegin, Integer size, String locality) {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT * from " + ENTITY + " WHERE " + LOCALITY  + " = ?"
                + " ORDER BY " + RELEVANCE + " DESC, " + TITLE + 
                " LIMIT ? OFFSET ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, locality);
            stmt.setInt(2, size);
            stmt.setLong(3, pageBegin);
            
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
        } finally {
            try {
                if (stmt != null & !stmt.isClosed()) {
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
        
        return newsList;
    }
}
