package Utils.DBConnection;

public interface DBConnection {
    boolean connect();
    void disconnect();
    void cleanup();
    boolean isConnected();
}
