/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.INewsLetterDao;
import com.backend.economundi.database.dao.entity.EmailNewsLetterDto;
import com.backend.economundi.database.dao.entity.NewsEntity;
import com.backend.economundi.database.dao.entity.NewsLetterDto;
import com.backend.economundi.database.dao.entity.SourceEntity;
import com.backend.economundi.database.dao.entity.WordEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexandre
 */
@Repository
@Component
public class NewsLetterDao implements INewsLetterDao {

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
    public List<NewsLetterDto> getNewsletterEmail(String locality) {
        List<NewsLetterDto> newsLetterList = new ArrayList<>();

        String sql = "SELECT * FROM " + ENTITY + " where " + LOCALITY + " = ? order by " + RELEVANCE + " desc limit 6 ;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, locality);

            rs = stmt.executeQuery();

            while (rs.next()) {
                NewsLetterDto news = new NewsLetterDto();
                news.setLocality(rs.getString(LOCALITY));
                news.setTitle(rs.getString(TITLE));
                news.setUrl_news(rs.getString(URL));
                news.setUrl_image(rs.getString(URL_IMAGE));
                news.setSource(rs.getString(SOURCE));

                newsLetterList.add(news);
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

        return newsLetterList;
    }

    @Override
    public List<WordEntity> getWordNewsEmail() {

        List<WordEntity> wordList = new ArrayList<>();

        String sql = "SELECT id, name, description\n"
                + "FROM public.word ORDER BY random() limit 4;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                WordEntity word = new WordEntity();

                word.setName(rs.getString("name"));
                word.setDescription(rs.getString("description"));
                wordList.add(word);
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

        return wordList;
    }

    @Override
    public List<EmailNewsLetterDto> getEmailNewsLetter() {

        List<EmailNewsLetterDto> emailList = new ArrayList<>();

        String sql = "select email from _user where news_letter_active is true;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                EmailNewsLetterDto email = new EmailNewsLetterDto();

                email.setEmail(rs.getString("email"));
        
                emailList.add(email);
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

        return emailList;

    }

}
