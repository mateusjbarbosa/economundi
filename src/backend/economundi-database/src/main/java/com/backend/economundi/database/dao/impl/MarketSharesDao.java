package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IStockDao;
import com.backend.economundi.database.dao.entity.stocks.MarketSharesEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class MarketSharesDao implements IStockDao {

    private static final String ENTITY = "market_shares";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String POINTS = "points";
    private static final String VARIATION = "variation";

    private Connection conn;

    @Override
    public void create(MarketSharesEntity entity) {
        String sql = "INSERT INTO " + ENTITY + "(" + POINTS + ", "
                + VARIATION + ", " + NAME + ") VALUES "
                + "(?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, entity.getPoints());
            stmt.setDouble(2, entity.getVariation());
            stmt.setString(3, entity.getName());
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
        String sql = "UPDATE " + ENTITY + " SET " + POINTS + "= ?" + ", "
                + VARIATION + "= ? WHERE " + ID + "= ?";

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
        MarketSharesEntity stock = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            if (rs.next()) {
                stock = new MarketSharesEntity();

                stock.setId(rs.getLong(ID));
                stock.setName(rs.getString(NAME));
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

        return stock;
    }

    @Override
    public Map<String, Map<String, Object>> readStocks() {
        String sql = "SELECT * FROM " + ENTITY;
        Map<String, Map<String, Object>> stockMap = new HashMap<>();
        PreparedStatement stmt;
        ResultSet rs;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> currencyMap = new HashMap<>();

                currencyMap.put(POINTS, rs.getDouble(POINTS));
                currencyMap.put(VARIATION, rs.getDouble(VARIATION));
                stockMap.put(rs.getString(NAME), currencyMap);
            }
        } catch (SQLException ex) {

        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {

            }
        }

        return stockMap;
    }
}
