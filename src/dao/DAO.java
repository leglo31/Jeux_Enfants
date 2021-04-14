/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.SQLConnection;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author BsT
 */
public interface DAO<T> {

    Connection connection = SQLConnection.getInstance();

    public List<T> getAll();

    public void update(T object);

    public void create(T object);

    public void delete(Integer id);

}
