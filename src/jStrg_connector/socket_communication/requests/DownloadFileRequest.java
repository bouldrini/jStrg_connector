package jStrg_connector.socket_communication.requests;

import jStrg_connector.socket_communication.Cryptor;
import jStrg_connector.socket_communication.answers.Answer;
import jStrg_connector.socket_communication.Server;
import java.io.*;
import java.security.GeneralSecurityException;

public class DownloadFileRequest extends Request {

    public DownloadFileRequest(String _file_path, Request.type _type, String _username, String _password, Server _server){
        super(_type, _username, _password, _server);
        m_file_path = _file_path;
    }

    public String m_file_path;

    public Answer process() throws IOException, GeneralSecurityException {
        return null;
    }

    // HELPER
    public String for_server_request() {
        String query = "";
        query = "request_type:" + m_type + ";username:" + m_username + ";password:" + m_password + ";file_path:" +  m_file_path + ";";
        return query;
    }

    private String encrypt() throws GeneralSecurityException, UnsupportedEncodingException {
        return Cryptor.encrypt(this.for_server_request());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}