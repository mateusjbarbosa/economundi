package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IStocksQuoteDao;
import com.backend.economundi.database.dao.entity.StockEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class StocksQuoteDao implements IStocksQuoteDao {

    private static final String ENTITY = "stocks_quote";
    private static final String PRICE = "price";
    private static final String VARIATION = "change_percent";
    private static final String DATA_HOUR = "data_hour";
    private static final String ENTITY_STOCKS = "stocks";
    private static final String STOCKS_ID = "stocks_id";

    private Connection conn;

    @Override
    public void create(StockEntity entity) {
        String sql = "INSERT INTO " + ENTITY + "(" + PRICE + ", "
                + VARIATION + ", " + STOCKS_ID + ") VALUES "
                + "(?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, entity.getPrice());
            stmt.setDouble(2, entity.getChangePercent());
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
    public StockEntity read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(StockEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(StockEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Map<String, Object>> readQuote() {
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + STOCKS_ID
                + "= ? ORDER BY " + DATA_HOUR + " DESC LIMIT 1";
        StocksDao stocksDao = new StocksDao();
        List<StockEntity> marketSharesList = stocksDao.readAll();
        Map<String, Map<String, Object>> stocksMap = new HashMap<>();

        marketSharesList.stream().forEach((marketShares) -> {
            try {
                PreparedStatement stmt;
                ResultSet rs;
                conn = ConnectionFactory.getConnection();
                stmt = conn.prepareStatement(sql);

                stmt.setLong(1, marketShares.getId());
                rs = stmt.executeQuery();

                if (rs.next()) {
                    Map<String, Object> stockMap = new HashMap<>();

                    stockMap.put(PRICE, rs.getDouble(PRICE));
                    stockMap.put(VARIATION, rs.getDouble(VARIATION));
                    stocksMap.put(marketShares.getName(), stockMap);
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

        return stocksMap;
    }
}
