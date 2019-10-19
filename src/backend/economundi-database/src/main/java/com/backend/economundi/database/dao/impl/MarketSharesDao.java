package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IMarketSharesDao;
import com.backend.economundi.database.dao.entity.stocks.MarketSharesEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class MarketSharesDao implements IMarketSharesDao {

    private static final String ENTITY = "market_shares";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOCATION = "location";

    private Connection conn;

    @Override
    public void create(MarketSharesEntity entity) {
        String sql = "INSERT INTO " + ENTITY + "(" + NAME + ", " + LOCATION +
                ") VALUES (?, ?)";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getLocation());
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
    public MarketSharesEntity read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MarketSharesEntity entity) {
        String sql = "UPDATE " + ENTITY + " SET " + NAME + "= ?" + ", "
                + LOCATION + "= ? WHERE " + ID + "= ?";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, entity.getPoints());
            stmt.setDouble(2, entity.getVariation());
            stmt.setLong(3, entity.getId());
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
    public void delete(MarketSharesEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarketSharesEntity readByName(String name) {
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + NAME + " = " + "'"
                + name + "'";
        MarketSharesEntity marketShares = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            if (rs.next()) {
                marketShares = new MarketSharesEntity();

                marketShares.setId(rs.getLong(ID));
                marketShares.setName(rs.getString(NAME));
                marketShares.setLocation(rs.getString(LOCATION));
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

        return marketShares;
    }

    @Override
    public List<MarketSharesEntity> readAll() {
                String sql = "SELECT * FROM " + ENTITY;
        List<MarketSharesEntity> marketSharesList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                MarketSharesEntity marketShares = new MarketSharesEntity();

                marketShares.setId(rs.getLong(ID));
                marketShares.setName(rs.getString(NAME));
                marketSharesList.add(marketShares);
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
        
        return marketSharesList;
    }
}
