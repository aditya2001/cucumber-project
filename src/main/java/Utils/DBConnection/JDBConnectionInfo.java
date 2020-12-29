package Utils.DBConnection;

public class JDBConnectionInfo {
    private String jdbcDriver;
    private String jdbcUrl;
    private String username;
    private String key;


//
//public JDBCConnectionInfo(String jdbcDriver, String jdbcUrl, String username, String key) {
//    this.jdbcDriver = jdbcDriver;
//    this.jdbcUrl = jdbcUrl;
//    this.username = username;
//    this.key = key;
//
//}

   public String getJdbcDriver() { return this.jdbcDriver; }

    public void setJdbcDriver(String jdbcDriver) { this.jdbcDriver = jdbcDriver; }

    public String getJdbcUrl() { return this.jdbcUrl; }

    public void setJdbcUrl(String jdbcUrl) { this.jdbcUrl = jdbcUrl; }

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.key; }

    public void setPassword(String password) { this.key = password; }

}

