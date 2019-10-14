package com.backend.economundi.database.dao.impl;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IStockDao;
import com.backend.economundi.database.dao.entity.stocks.StockEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

@Component
public class SotckDao implements IStockDao {

    private static final String ENTITY = "stock";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String POINTS = "points";
    private static final String VARIATION = "variation";

    private Connection conn;

    @Override
    public void create(StockEntity entity) {
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
    public StockEntity read(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(StockEntity entity) {
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
    public void delete(StockEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StockEntity readByName(String name) {
        String sql = "SELECT * FROM " + ENTITY + " WHERE " + NAME + " = " + "'"
                + name + "'";
        StockEntity stock = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            if (rs.next()) {
                stock = new StockEntity();

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

    
}
