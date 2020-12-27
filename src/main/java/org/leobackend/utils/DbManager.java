package org.leobackend.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created on 2020-12-27.
 * 手动创建数据源，为了在工具类中使用
 *
 * @author Leo
 */
public class DbManager {

    private BasicDataSource dataSource;
    private static DbManager dbManager = new DbManager();

    private DbManager () {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("dbcp.properties");
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return Connection
     */
    private Connection getConnection () {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向连接池归还连接
     * @param connection Connection
     */
    private void close (Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行查询
     * @param sql sql语句：select label1,label2,label3 from tableName where param1=? and param2=?
     * @param labels 如上：label1,label2...
     * @param params ?占位符参数
     * @return 数据集合
     */
    public List<Map<String, Object>> query(String sql, String[] labels, String[] params) {

        Connection connection = null;
        try {
            connection = this.getConnection();
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(sql);

            int paramIndex = 1;
            for (String param : params) {
                statement.setString(paramIndex++, param);
            }

            List<Map<String, Object>> resultList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int labelIndex = 0; labelIndex < labels.length; labelIndex ++) {
                    rowMap.put(labels[labelIndex], resultSet.getObject(labelIndex + 1));
                }
                resultList.add(rowMap);
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(connection);
        }
        return Collections.emptyList();
    }

    public static DbManager getInstance () {
        if (dbManager == null)
            dbManager = new DbManager();
        return dbManager;
    }

}
