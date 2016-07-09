package jStrg_connector.socket_communication.communication.requests;

import jStrg_connector.socket_communication.communication.answers.DownloadFileRequestAnswer;
import jStrg_connector.socket_communication.core.Answer;
import jStrg_connector.socket_communication.core.Cryptor;
import jStrg_connector.socket_communication.core.JstrgServer;
import jStrg_connector.socket_communication.core.Request;

import java.io.*;
import java.security.GeneralSecurityException;

public class DownloadFileRequest extends Request {

    public DownloadFileRequest(String _file_path, Request.type _type, String _owner_name, String _username, String _password, JstrgServer _Jstrg_server){
        super(_type, _username, _password, _Jstrg_server);
        m_file_path = _file_path;
        m_owner = _owner_name;
    }

    public String m_file_path;
    public String m_owner;

    public Answer process() throws IOException, GeneralSecurityException {
        DownloadFileRequestAnswer request_answer = this.send_file_upload_request();
        return request_answer;
    }

    private DownloadFileRequestAnswer send_file_upload_request() throws IOException, GeneralSecurityException {
        System.out.println("SENDING FILE DOWNLOAD REQUEST....");
        OutputStream outToServer = this.m_Jstrg_server.m_current_connection.m_socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(Cryptor.encrypt(this.for_server_request()));

        System.out.println("RECEIVING ANSWER....");
        InputStream inFromServer = this.m_Jstrg_server.m_current_connection.m_socket.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        String server_answer = in.readUTF();

        DownloadFileRequestAnswer answer = new DownloadFileRequestAnswer(server_answer);
        return answer;
    }

    // HELPER
    public String for_server_request() {
        String query = "";
        query = "request_type:" + m_type + ";ownername:"+ m_owner +";username:" + m_username + ";password:" + m_password + ";file_path:" +  m_file_path + ";";
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