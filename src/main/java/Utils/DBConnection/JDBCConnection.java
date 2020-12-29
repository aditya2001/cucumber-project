//package Utils.DBConnection;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class JDBCConnection implements DBConnection {
//    private Connection conn;
//    private Statement stmt = null;
//
//
//
//    public boolean connect() {
//    try {
//        Class.forName(this.jdbcDriver);
//        JDBConnectionInfo jdbc = new JDBConnectionInfo();
//        jdbc.setUsername("Oracle");
//        this.conn = DriverManager.getConnection(jdbc.getUsername(), this.user, this.key);
//        if (!this.isConnected()) {
//            this.logger.error("Unable to connect");
//        }
//        else {
//            this.logger.debug("JDBC connection established");
//        }
//
//    } catch (ClassNotFoundException | SQLException e) {
//        e.printStackTrace();
//    }
//    return this.isConnected();
//
//    }
//
//
//    public void disconnect() {
//
//    }
//
//
//    public void cleanup() {
//
//    }
//
//
//    public boolean isConnected() {
//        return false;
//    }
//}
