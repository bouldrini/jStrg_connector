package jStrg_connector.socket_communication.requests;

import jStrg_connector.socket_communication.Server;

public class Request {

    // CONSTRUCTORS
    public Request(Request.type _type, String _username, String _password, Server _server){
        m_type = _type;
        m_server = _server;
        m_username = _username;
        m_password = _password;
    }

    // CONSTANTS
    public enum type{DOWNLOAD_FILE, UPLOAD_FILE, CREATE_USER, DELETE_FILE, DELETE_FOLDER};

    // ATTRIBUTES
    public Request.type m_type;
    public Server m_server;
    public String m_username;
    public String m_password;
}
