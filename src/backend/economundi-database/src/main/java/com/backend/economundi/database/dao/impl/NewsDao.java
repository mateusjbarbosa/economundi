package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.INewsDao;
import com.backend.economundi.database.dao.entity.NewsEntity;
import com.backend.economundi.database.dao.entity.SourceEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class NewsDao implements INewsDao {

    private Connection conn;

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String CONTENT = "content";
    private static final String SOURCE = "source";
    private static final String URL = "link";
    private static final String URL_IMAGE = "link_image";
    private static final String LOCALITY = "locality";
    private static final String RELEVANCE = "engagement";
    private static final String DATE = "date_hour";
    private static final String ENTITY = "news";

    @Override
    public void create(NewsEntity news) {
        String sql = "INSERT INTO " + ENTITY + "(" + ID + "," + TITLE + "," + DESCRIPTION + "," + CONTENT + "," + SOURCE
                + "," + URL_IMAGE + "," + URL + "," + LOCALITY + "," + RELEVANCE
                + ") VALUES (nextval('news_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?)";

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
    public NewsEntity read(Long id) {
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + ID + "= ?";
        NewsEntity news = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                news = new NewsEntity();
                news.setContent(rs.getString(CONTENT));
                news.setDate(rs.getString(DATE));
                news.setDescription(rs.getString(DESCRIPTION));
                news.setId(rs.getLong(ID));
                news.setLocality(rs.getString(LOCALITY));
                news.setRelevance(rs.getLong(RELEVANCE));
                news.setTitle(rs.getString(TITLE));
                news.setUrl(rs.getString(URL));
                news.setUrlToImage(rs.getString(URL_IMAGE));

                SourceEntity source = new SourceEntity();

                source.setName(rs.getString(SOURCE));
                news.setSource(source);
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

        return news;
    }

    @Override
    public void update(NewsEntity news) {
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
    public void delete(NewsEntity entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<NewsEntity> readNewsWithRelevance() {
        String sql = "SELECT * from " + ENTITY + " WHERE " + RELEVANCE + "> 0";
        List<NewsEntity> newsList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                NewsEntity news = new NewsEntity();

                news.setContent(rs.getString(CONTENT));
                news.setDate(rs.getString(DATE));
                news.setDescription(rs.getString(DESCRIPTION));
                news.setId(rs.getLong(ID));
                news.setLocality(rs.getString(LOCALITY));
                news.setRelevance(rs.getLong(RELEVANCE));
                news.setTitle(rs.getString(TITLE));
                news.setUrl(rs.getString(URL));
                news.setUrlToImage(rs.getString(URL_IMAGE));

                SourceEntity source = new SourceEntity();

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
    public List<NewsEntity> readByPage(Long pageBegin, Integer size, String locality) {
        List<NewsEntity> newsList = new ArrayList<>();    
        
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + LOCALITY + " = ?"
                + " ORDER BY " + RELEVANCE + " DESC, " + TITLE
                + " LIMIT ? OFFSET ?";
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
                NewsEntity news = new NewsEntity();

                news.setContent(rs.getString(CONTENT));
                news.setDate(rs.getString(DATE));
                news.setDescription(rs.getString(DESCRIPTION));
                news.setId(rs.getLong(ID));
                news.setLocality(rs.getString(LOCALITY));
                news.setRelevance(rs.getLong(RELEVANCE));
                news.setTitle(rs.getString(TITLE));
                news.setUrl(rs.getString(URL));
                news.setUrlToImage(rs.getString(URL_IMAGE));

                SourceEntity source = new SourceEntity();

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

    @Override
    public Long getAmountNewsByLocality(String locality) {
        Long amount = 0L;
        String sql = "SELECT COUNT(*) AS amount FROM " + ENTITY + " WHERE "
                + LOCALITY + " = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, locality);

            rs = stmt.executeQuery();

            if (rs.next()){
                amount = rs.getLong("amount");
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

        return amount;
    }
}
