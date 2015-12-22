package jStrg_connector.socket_communication;

import jStrg_connector.socket_communication.answers.Answer;
import jStrg_connector.socket_communication.requests.*;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;

/**
 * Represents a Server in the Local Network Environment of jTest
 * - multiple Local Storage Server used by jTest
 */

public class Server {

    // CONSTRUCTORS
    public Server(){}
    public Server(String _servername, String _ip_address, int _port) throws IOException {
        this.m_ip_address = Inet4Address.getByName(_ip_address);
        if (!this.ping())
            throw new NullPointerException("Not reachable");
        this.m_servername = _servername;
        this.m_port = _port;
        this.m_type = TYPE.STORAGE;
        this.m_path_prefix = "";
    }

    public enum TYPE {DATABASE, STORAGE};

    // ATTRIBUTES
    public InetAddress m_ip_address;
    public String m_servername;
    public int m_port;
    public Server.TYPE m_type;
    public Connection m_current_connection;
    public String m_path_prefix;

    public boolean ping() throws IOException {
        boolean result = false;
        if(this.m_ip_address.isReachable(4000)){
            result = true;
        };
        return result;
    }

    // HANDLE CONNECTION
    public boolean connect(){
        boolean result = false;
        try {
            Socket client_socket = new Socket(m_ip_address.getHostAddress(), m_port);
            this.m_current_connection = new Connection(client_socket, this);
            System.out.println("=====CONNECTED=====");
        }catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean disconnect() throws IOException {
        boolean result = false;
        if(this.m_current_connection != null){
            this.m_current_connection.close();
            this.m_current_connection = null;
            System.out.println("=====DISCONNECTED=====");
            result = true;
        }
        return result;
    }

    // QUERY FOR FILE
    public Answer get_file(String _file_path, String _username, String _password) throws IOException, GeneralSecurityException {
        this.connect();
        System.out.println("ATTEMBING TO GET FILE FROM JSTRG....");
        DownloadFileRequest request = new DownloadFileRequest(_file_path, Request.type.DOWNLOAD_FILE, _username, _password, this);
        Answer answer  = request.process();
        this.disconnect();
        return answer;
    }

    // UPLOAD FILE
    public Answer upload_file(File _file, String _new_file_path, String _file_name, String _username, String _password) throws IOException, GeneralSecurityException {
        boolean result = false;
        System.out.println("ATTEMBING TO UPLOAD FILE TO JSTRG....");
        UploadFileRequest request = new UploadFileRequest(_file, _new_file_path, _file_name, Request.type.UPLOAD_FILE, _username, _password, this);
        Answer answer  = request.process();
        this.disconnect();
        return answer;
    }

    // CREATE USER
    public Answer create_user(String _new_user_name, String _new_user_password, String _username, String _password) throws IOException, GeneralSecurityException {
        boolean result = false;
        System.out.println("ATTEMBING TO CREATE A USER IN JSTRG....");
        CreateUserRequest request = new CreateUserRequest(_new_user_name, _new_user_password, Request.type.CREATE_USER, _username, _password, this);
        Answer answer  = request.process();
        this.disconnect();
        return answer;
    }

    // DELETE FILE
    public Answer delete_file(String _file_path, String _username, String _password) throws IOException, GeneralSecurityException {
        boolean result = false;
        System.out.println("ATTEMBING TO DELETE FILE IN JSTRG....");
        DeleteFileRequest request = new DeleteFileRequest(_file_path, Request.type.DELETE_FILE, _username, _password, this);
        Answer answer  = request.process();
        this.disconnect();
        return answer;
    }

    // DELETE FILE FOLDER
    public Answer delete_file_folder(String _file_path, String _username, String _password) throws IOException, GeneralSecurityException {
        boolean result = false;
        System.out.println("ATTEMBING TO DELETE FILEFOLDER IN JSTRG....");
        DeleteFileFolderRequest request = new DeleteFileFolderRequest(_file_path, Request.type.DELETE_FOLDER, _username, _password, this);
        Answer answer  = request.process();
        this.disconnect();
        return answer;
    }

    // HELPER
    @Override
    public String toString() {
        return "<Server::{m_ip_address: " + m_ip_address + ", m_servername: " + m_servername + ", m_port: " + m_port + ", m_type: " + m_type + ", m_path_prefix: " + m_path_prefix + "}>";
    }
}
