package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IQuoteDao;
import com.backend.economundi.database.dao.entity.coin.CurrencyEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class QuoteDao implements IQuoteDao {

    private static final String ENTITY = "quote";
    private static final String ID = "id";
    private static final String DATA_HOUR = "data_hour";
    private static final String BUY = "buy";
    private static final String SELL = "sell";
    private static final String VARIATION = "variation";
    private static final String CURRENCY_ID = "currency_id";

    private Connection conn;

    @Override
    public void create(CurrencyEntity entity) {
        String sql = "INSERT INTO " + ENTITY + "(" + BUY + ", " + SELL
                + "," + VARIATION + ", " + CURRENCY_ID + ") VALUES "
                + "(?::NUMERIC::MONEY, ?::NUMERIC::MONEY, ?, ?)";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setFloat(1, entity.getBuy());
            stmt.setFloat(2, entity.getSell());
            stmt.setDouble(3, entity.getVariation());
            stmt.setLong(4, entity.getId());
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
    public CurrencyEntity read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CurrencyEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CurrencyEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Map<String, Object>> readQuote() {
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + CURRENCY_ID + "= ?"
                + " ORDER BY " + DATA_HOUR + " DESC LIMIT 1";
        CurrencyDao currencyDao = new CurrencyDao();
        List<CurrencyEntity> currencyList = currencyDao.readAll();
        Map<String, Map<String, Object>> currenciesMap = new HashMap<>();

        currencyList.stream().forEach((currency) -> {
            try {
                PreparedStatement stmt;
                ResultSet rs;
                conn = ConnectionFactory.getConnection();
                stmt = conn.prepareStatement(sql);
                
                stmt.setLong(1, currency.getId());
                rs = stmt.executeQuery();

                if (rs.next()) {
                    Map<String, Object> currencyMap = new HashMap<>();

                    currencyMap.put(BUY, rs.getString(BUY));
                    currencyMap.put(SELL, rs.getString(SELL));
                    currencyMap.put(VARIATION, rs.getDouble(VARIATION));
                    currenciesMap.put(currency.getName(), currencyMap);
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
