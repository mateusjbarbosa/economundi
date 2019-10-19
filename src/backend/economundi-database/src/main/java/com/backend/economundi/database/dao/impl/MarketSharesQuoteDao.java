package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IMarketSharesQuoteDao;
import com.backend.economundi.database.dao.entity.stocks.MarketSharesEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketSharesQuoteDao implements IMarketSharesQuoteDao {

    private static final String ENTITY = "market_shares_quote";
    private static final String POINTS = "points";
    private static final String VARIATION = "variation";
    private static final String DATA_HOUR = "data_hour";
    private static final String ENTITY_MARKET_SHARES = "market_shares";
    private static final String MARKET_SHARES_ID = "market_shares_id";

    private Connection conn;

    @Override
    public void create(MarketSharesEntity entity) {
        String sql = "INSERT INTO " + ENTITY + "(" + POINTS + ", "
                + VARIATION + ", " + MARKET_SHARES_ID + ") VALUES "
                + "(?, ?, ?)";

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MarketSharesEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Map<String, Object>> readQuote() {
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + MARKET_SHARES_ID +
                "= ? ORDER BY " + DATA_HOUR + " DESC LIMIT 1";
        MarketSharesDao marketSharesDao = new MarketSharesDao();
        List<MarketSharesEntity> marketSharesList = marketSharesDao.readAll();
        Map<String, Map<String, Object>> currenciesMap = new HashMap<>();

        marketSharesList.stream().forEach((marketShares) -> {
            try {
                PreparedStatement stmt;
                ResultSet rs;
                conn = ConnectionFactory.getConnection();
                stmt = conn.prepareStatement(sql);

                stmt.setLong(1, marketShares.getId());
                rs = stmt.executeQuery();

                if (rs.next()) {
                    Map<String, Object> currencyMap = new HashMap<>();

                    currencyMap.put(POINTS, rs.getDouble(POINTS));
                    currencyMap.put(VARIATION, rs.getDouble(VARIATION));
                    currenciesMap.put(marketShares.getName(), currencyMap);
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
        });

        return currenciesMap;
    }
}
