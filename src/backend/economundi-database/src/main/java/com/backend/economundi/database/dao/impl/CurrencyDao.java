package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.ICurrencyDao;
import com.backend.economundi.database.dao.entity.coin.CurrencyEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDao implements ICurrencyDao {

    private static final String ENTITY = "currency";
    private static final String ID = "id";
    private static final String NAME = "name";

    private Connection conn;

    @Override
    public void create(CurrencyEntity entity) {
        String sql = "INSERT INTO " + ENTITY + "(" + NAME + ") VALUES "
                + "(?) RETURNING " + ID;

        PreparedStatement stmt = null;
        ResultSet rs;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getName());
            rs = stmt.executeQuery();

            if (rs.next()) {
                entity.setId(rs.getLong(ID));
            }

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
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + ID + "= ?";
        CurrencyEntity currency = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                currency = new CurrencyEntity();

                currency.setName(rs.getString(NAME));
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

        return currency;
    }

    @Override
    public void update(CurrencyEntity entity) {
        String sql = "UPDATE " + ENTITY + " SET " + NAME + "= ?"
                + " WHERE " + ID + "= ?";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getName());
            stmt.setLong(2, entity.getId());
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
    public void delete(CurrencyEntity entity) {

    }

    @Override
    public CurrencyEntity readByName(String name) {
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + NAME + " = " + "'"
                + name + "'";
        CurrencyEntity currency = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            if (rs.next()) {
                currency = new CurrencyEntity();

                currency.setId(rs.getLong(ID));
                currency.setName(rs.getString(NAME));
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

        return currency;
    }

    @Override
    public List<CurrencyEntity> readAll() {
        String sql = "SELECT * FROM " + ENTITY;
        List<CurrencyEntity> currencyList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                CurrencyEntity currency = new CurrencyEntity();

                currency.setId(rs.getLong(ID));
                currency.setName(rs.getString(NAME));
                currencyList.add(currency);
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
        
        return currencyList;
    }

}
