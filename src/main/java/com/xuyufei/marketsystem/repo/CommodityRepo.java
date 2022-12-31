package com.xuyufei.marketsystem.repo;

import com.xuyufei.marketsystem.model.Commodity;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommodityRepo extends Repo implements Closeable {
    private static Connection connection;

    public CommodityRepo() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertEntity(Commodity com) {
        try(Statement statement = connection.createStatement();) {
            String sql = "insert into commodity(name, imagePath, text, price, owner)" +
                    " values('" + com.getName() + "','" + com.getImagePath() + "','" +
                    com.getText() + "','" + com.getPrice() + "','" + com.getOwner() + "')";
            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected > 0) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Integer> getIDsByName(String name) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity where name=" + name;
            var resultSet = statement.executeQuery(sql);

            var ret = new ArrayList<Integer>();
            while(resultSet.next()) {
                ret.add(resultSet.getInt("id"));
            }
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIDByName(String name) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity where name='" + name + "'";
            var resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean contains(String name) {
        String sql = "select * from commodity where name='" + name + "'";
        try(Statement statement = connection.createStatement();) {
            var resultSet = statement.executeQuery(sql);

            if(resultSet.next())    return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Integer> getIDsByEntityName(Commodity com) {
        return getIDsByName(com.getName());
    }

    // TODO: 2022/12/4
    // In the function, the codes to import a image may be incorrect

    //2022/12/31
    //the image part has been canceled
    public Commodity getEntityByID(int id) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity where id=" + id;
            var resultSet = statement.executeQuery(sql);
            if(resultSet.next() == false)   return null;

            var id2 = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var imagePath = resultSet.getString("imagePath");
            var text = resultSet.getString("text");
            var price = resultSet.getInt("price");
            var owner = resultSet.getString("owner");
            var status = resultSet.getBoolean("status");
            return new Commodity(id2, name, imagePath, text, price, owner, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Integer> getIDsByOwnerID(int ownerID) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity where owner=" + ownerID;
            var resultSet = statement.executeQuery(sql);

            var ret = new ArrayList<Integer>();
            while(resultSet.next()) {
                ret.add(resultSet.getInt("owner"));
            }
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Commodity> getAllCommodities() {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity";
            var resultSet = statement.executeQuery(sql);

            List<Commodity> ret = new ArrayList<>();
            while(resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var imagePath = resultSet.getString("imagePath");
                var text = resultSet.getString("text");
                var price = resultSet.getInt("price");
                var owner = resultSet.getString("owner");
                var status = resultSet.getBoolean("status");

                ret.add(new Commodity(id, name, imagePath, text, price, owner, status));
            }

            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Commodity> getAllCommoditiesOwnedByUser(String username) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity where owner='" + username + "'";
            var resultSet = statement.executeQuery(sql);

            List<Commodity> ret = new ArrayList<>();
            while(resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var imagePath = resultSet.getString("imagePath");
                var text = resultSet.getString("text");
                var price = resultSet.getInt("price");
                var owner = resultSet.getString("owner");
                var status = resultSet.getBoolean("status");

                ret.add(new Commodity(id, name, imagePath, text, price, owner, status));
            }

            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setStatusByName(String name, boolean status) {
        String sql = "update commodity set status='" + status + "' where name='" + name + "'";
        try(Statement statement = connection.createStatement();) {
            int rowAffected = statement.executeUpdate(sql);

            if(rowAffected > 0)     return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setNameByID(String name, int id) {
        String sql = "update commodity set name='" + name + "' where id = '" + id + "'";
        try(Statement statement = connection.createStatement();) {
            int rowAffected = statement.executeUpdate(sql);

            if(rowAffected > 0)     return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setPriceByID(int price, int id) {
        String sql = "update commodity set price='" + price + "' where id = '" + id + "'";
        try(Statement statement = connection.createStatement();) {
            int rowAffected = statement.executeUpdate(sql);

            if(rowAffected > 0)     return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setOwnerByID(String owner, int id) {
        String sql = "update commodity set owner='" + owner + "' where id = '" + id + "'";
        try(Statement statement = connection.createStatement();) {
            int rowAffected = statement.executeUpdate(sql);

            if(rowAffected > 0)     return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setTextByID(String text, int id) {
        String sql = "update commodity set text='" + text + "' where id = '" + id + "'";
        try(Statement statement = connection.createStatement();) {
            int rowAffected = statement.executeUpdate(sql);

            if(rowAffected > 0)     return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean modifyByID(Commodity commodity, int id) {
        setNameByID(commodity.getName(), id);
        setPriceByID(commodity.getPrice(), id);
        setOwnerByID(commodity.getOwner(), id);
        setTextByID(commodity.getText(), id);
        return true;
    }

    public boolean delByName(String name) {
        String sql = "delete from commodity where name='" + name + "'";
        try(Statement statement = connection.createStatement();) {
            int rowAffected = statement.executeUpdate(sql);

            if(rowAffected > 0) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
