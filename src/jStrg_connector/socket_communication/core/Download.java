package jStrg_connector.socket_communication.core;


import java.io.File;

public class Download {
    public Download(String _username, String _password, JstrgServer _Jstrg_server, String _transaction_id, long _file_size){
        m_Jstrg_server = _Jstrg_server;
        m_username = _username;
        m_password = _password;
        m_transaction_id = _transaction_id;
        m_file_size = _file_size;
    }

    // ATTRIBUTES
    public JstrgServer m_Jstrg_server;
    public String m_username;
    public String m_password;
    public File m_file;
    public String m_transaction_id;
    public long m_file_size;
}
