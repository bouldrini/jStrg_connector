package jStrg_connector.socket_communication.requests;

import jStrg_connector.socket_communication.Cryptor;
import jStrg_connector.socket_communication.Server;
import jStrg_connector.socket_communication.answers.*;

import java.io.*;
import java.security.GeneralSecurityException;

public class DeleteFileFolderRequest extends Request{
    public DeleteFileFolderRequest(String _folder_path, type _type, String _username, String _password, Server _server) {
        super(_type, _username, _password, _server);
        m_folder_path = _folder_path;
    }

    public String m_folder_path;

    public Answer process() throws IOException, GeneralSecurityException {
        this.m_server.connect();
        DeleteFileFolderRequestAnswer answer = this.send_delete_file_request();
        this.m_server.disconnect();
        return answer;
    }

    private DeleteFileFolderRequestAnswer send_delete_file_request() throws IOException, GeneralSecurityException {
        System.out.println("SENDING DELETE FILE REQUEST....");
        OutputStream outToServer = this.m_server.m_current_connection.m_socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(Cryptor.encrypt(this.for_server_request()));

        System.out.println("RECEIVING ANSWER....");
        InputStream inFromServer = this.m_server.m_current_connection.m_socket.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        String server_answer = in.readUTF();
        DeleteFileFolderRequestAnswer answer = new DeleteFileFolderRequestAnswer(server_answer);
        return answer;
    }

    // HELPER
    public String for_server_request() {
        String query = "";
        query = "request_type:" + m_type + ";username:" + m_username + ";password:" + m_password + ";folder_path:" + m_folder_path + ";";
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