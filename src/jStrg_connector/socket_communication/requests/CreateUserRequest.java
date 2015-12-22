package jStrg_connector.socket_communication.requests;

import jStrg_connector.socket_communication.Cryptor;
import jStrg_connector.socket_communication.Server;
import jStrg_connector.socket_communication.answers.Answer;
import jStrg_connector.socket_communication.answers.CreateUserRequestAnswer;

import java.io.*;
import java.security.GeneralSecurityException;

public class CreateUserRequest extends Request{
    public CreateUserRequest(String _new_user_name, String _new_user_password, type _type, String _username, String _password, Server _server) {
        super(_type, _username, _password, _server);
        m_new_user_name = _new_user_name;
        m_new_user_password = _new_user_password;
    }

    public String m_new_user_name;
    public String m_new_user_password;


    public Answer process() throws IOException, GeneralSecurityException {
        boolean result = false;
        this.m_server.connect();
        CreateUserRequestAnswer answer = this.send_create_user_request();
        if(answer.m_status == Answer.status.DONE){
            result = true;
        }
        this.m_server.disconnect();
        return answer;
    }

    private CreateUserRequestAnswer send_create_user_request() throws IOException, GeneralSecurityException {
        System.out.println("SENDING CREATE USER REQUEST....");
        OutputStream outToServer = this.m_server.m_current_connection.m_socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(Cryptor.encrypt(this.for_server_request()));

        System.out.println("RECEIVING ANSWER....");
        InputStream inFromServer = this.m_server.m_current_connection.m_socket.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        String server_answer = in.readUTF();
        CreateUserRequestAnswer answer = new CreateUserRequestAnswer(server_answer);
        return answer;
    }

    // HELPER
    public String for_server_request() {
        String query = "";
        query = "request_type:" + m_type + ";username:" + m_username + ";password:" + m_password + ";new_user_name:" + m_new_user_name + ";new_user_password:" + m_new_user_password + ";";
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
