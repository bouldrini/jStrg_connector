package jStrg_connector.socket_communication.core;


import java.io.File;

public class Download {
    public Download(String _username, String _password, JstrgServer _Jstrg_server, int _upload_id){
        m_Jstrg_server = _Jstrg_server;
        m_username = _username;
        m_password = _password;
        m_upload_id = _upload_id;
    }

    // ATTRIBUTES
    public JstrgServer m_Jstrg_server;
    public String m_username;
    public String m_password;
    public File m_file;
    public int m_upload_id;
}
