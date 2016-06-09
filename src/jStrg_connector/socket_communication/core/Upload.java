package jStrg_connector.socket_communication.core;

import java.io.File;

public class Upload extends Request{

    public Upload(type _type, String _username, String _password, JstrgServer _Jstrg_server, String _transaction_id, File _file, String _file_name, String _file_path) {
        super(_type, _username, _password, _Jstrg_server);
        if(_file.exists() && !_file.isDirectory()) {
            m_file = _file;
        }
        m_transaction_id = _transaction_id;
        m_file_name = _file_name;
        m_file_path = _file_path;

    }

    // ATTRIBUTES
    public File m_file;
    public String m_transaction_id;
    public String m_file_path;
    public String m_file_name;

}
