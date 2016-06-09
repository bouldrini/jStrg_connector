package jStrg_connector.socket_communication.communication.requests;

import jStrg_connector.socket_communication.core.Answer;
import jStrg_connector.socket_communication.communication.answers.FileUploadAnswer;
import jStrg_connector.socket_communication.communication.answers.UploadFileRequestAnswer;
import jStrg_connector.socket_communication.core.Cryptor;
import jStrg_connector.socket_communication.core.JstrgServer;
import jStrg_connector.socket_communication.core.Request;

import java.io.*;
import java.security.GeneralSecurityException;

public class UploadFileRequest extends Request {

    public UploadFileRequest(File _file, String _file_path, String _file_name, Request.type _type, String _username, String _password, JstrgServer _Jstrg_server) {
        super(_type, _username, _password, _Jstrg_server);
        m_type = _type;
        m_file = _file;
        m_file_size = m_file.length();
        m_file_name = _file_name;
        m_file_path = _file_path;
    }

    public File m_file;
    public long m_file_size;
    public String m_file_path;
    public String m_file_name;

    public Answer process() throws IOException, GeneralSecurityException {
        UploadFileRequestAnswer request_answer = this.send_file_upload_request();
        return request_answer;
    }

    private UploadFileRequestAnswer send_file_upload_request() throws IOException, GeneralSecurityException {
         System.out.println("SENDING FILE UPLOAD REQUEST....");
         OutputStream outToServer = this.m_Jstrg_server.m_current_connection.m_socket.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         out.writeUTF(Cryptor.encrypt(this.for_server_request()));

         System.out.println("RECEIVING ANSWER....");
         InputStream inFromServer = this.m_Jstrg_server.m_current_connection.m_socket.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         String server_answer = in.readUTF();

         UploadFileRequestAnswer answer = new UploadFileRequestAnswer(server_answer);
         return answer;
    }

    // HELPER
    public String for_server_request() {
        String query = "";
        query = "request_type:" + m_type + ";username:" + m_username + ";password:" + m_password + ";file_path:" + m_file_path + ";file_name:" + m_file_name + ";file_size:" + m_file_size + ";";
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
