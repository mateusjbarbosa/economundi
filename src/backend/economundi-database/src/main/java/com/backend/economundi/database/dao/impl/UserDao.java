/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.database.dao.impl;


import com.backend.economundi.database.connection.ConnectionFactory;
import com.backend.economundi.database.dao.IUserDao;
import com.backend.economundi.database.dao.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Alexandre
 */
@Repository
public class UserDao implements IUserDao {

    private Connection conn;

    @Override
    public void create(User user) {
        String sql = "INSERT INTO usuario (id, email, nome, sobrenome, senha, data_nasc, permissao, perfil_economico, data_hora_cadastro)"
                + "VALUES (nextval('usuario_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getfirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getPassword());
            stmt.setDate(5, Date.valueOf(user.getBirth()));
            stmt.setString(6, user.getRole());
            stmt.setString(7, user.getEconomicUser());
            stmt.setTimestamp(8, user.getDateTimeSignin());

            stmt.execute();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
        } finally {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Exception e) {
            }

            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(User user) {

        String sql = "UPDATE usuario SET "
                + " nome = ?, sobrenome = ?, senha = ?, data_nasc = ?,"
                + " perfil_economico = ?"
                + " WHERE id = ?";

        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getfirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getPassword());
            stmt.setDate(4, Date.valueOf(user.getBirth()));
            stmt.setString(5, user.getEconomicUser());

            stmt.setLong(6, user.getId());

            stmt.execute();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
        } finally {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Exception e) {
            }

            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            stmt.execute();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
        } finally {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Exception e) {
            }

            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public User readById(Long id) {

        User user = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM usuario WHERE id = ?");
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            conn.commit();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setfirstName(rs.getString("nome"));
                user.setLastName(rs.getString("sobrenome"));
                user.setPassword(rs.getString("senha"));
                user.setBirth(rs.getDate("data_nasc").toString());
                user.setRole(rs.getString("permissao"));
                user.setEconomicUser(rs.getString("perfil_economico"));
                user.setDateTimeSignin(rs.getTimestamp("data_hora_cadastro"));
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
        } finally {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Exception e) {
            }

            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
            }

            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }

        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement("SELECT * FROM usuario");         

            rs = stmt.executeQuery();
            conn.commit();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setfirstName(rs.getString("nome"));
                user.setLastName(rs.getString("sobrenome"));
                user.setPassword(rs.getString("senha"));
                user.setBirth(rs.getDate("data_nasc").toString());
                user.setRole(rs.getString("permissao"));
                user.setEconomicUser(rs.getString("perfil_economico"));
                user.setDateTimeSignin(rs.getTimestamp("data_hora_cadastro"));
                
                userList.add(user);
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
        } finally {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Exception e) {
            }

            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
            }

            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }

        return userList;
    }

}
