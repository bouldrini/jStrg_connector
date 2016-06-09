package jStrg_connector.socket_communication.core;

public class Request {

    // CONSTRUCTORS
    public Request(Request.type _type, String _username, String _password, JstrgServer _Jstrg_server){
        m_type = _type;
        m_Jstrg_server = _Jstrg_server;
        m_username = _username;
        m_password = _password;
    }



    // CONSTANTS
    public enum type{DOWNLOAD_FILE, UPLOAD_FILE, CREATE_USER, DELETE_FILE, DELETE_FOLDER, UPLOAD_FILE_REQUEST};

    // ATTRIBUTES
    public Request.type m_type;
    public JstrgServer m_Jstrg_server;
    public String m_username;
    public String m_password;
}
