package com.backend.economundi.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.INewsBlackList;
import com.backend.economundi.database.dao.entity.NewsBlackListEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsBlackListDao implements INewsBlackList {
	private Connection conn;

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String ENTITY = "news_black_list";

	@Override
	public void create(NewsBlackListEntity name) {
		String sql = "INSERT INTO " + ENTITY + "(" + ID + ", " + NAME
				+ ") VALUES (nextval('news_black_list_id_seq'), ?) RETURNING " + ID;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name.getName());

			rs = stmt.executeQuery();

			if (rs.next()) {
				name.setId(rs.getLong(ID));
			}

			conn.commit();
		} catch (SQLException e) {
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
	public NewsBlackListEntity read(Long id) {
        String sql = "SELECT * FROM " + ENTITY + " w WHERE w.id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        NewsBlackListEntity name = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
            	name = new NewsBlackListEntity();
            	name.setId(rs.getLong(ID));
                name.setName(rs.getString(NAME));
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

        return name;
	}

	@Override
	public void update(NewsBlackListEntity entity) {
		String sql = "UPDATE " + ENTITY + " SET " + NAME + "= ? WHERE " + ID + " = ?";
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
	public void delete(NewsBlackListEntity name) {
		String sql = "DELETE FROM " + ENTITY + " WHERE " + ID + "= ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);

			stmt.setLong(1, name.getId());
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
	}

	@Override
	public List<NewsBlackListEntity> readAll() {
		String sql = "SELECT * FROM " + ENTITY;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<NewsBlackListEntity> nameList = new ArrayList<>();

		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				NewsBlackListEntity name = new NewsBlackListEntity();

				name.setId(rs.getLong(ID));
				name.setName(rs.getString(NAME));

				nameList.add(name);
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

		return nameList;
	}
}
