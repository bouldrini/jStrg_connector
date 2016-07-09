package jStrg_connector.socket_communication.core;

import jStrg_connector.socket_communication.communication.answers.DownloadFileRequestAnswer;
import jStrg_connector.socket_communication.communication.requests.*;
import jStrg_connector.socket_communication.transactions.FileDownload;
import jStrg_connector.socket_communication.transactions.FileUpload;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;

/**
 * Represents a Server in the Local Network Environment of jTest
 * - multiple Local Storage Server used by jTest
 */

public class JstrgServer {

    public String m_default_file_folder;

    // CONSTRUCTORS
    public JstrgServer(){}
    public JstrgServer(String _servername, String _ip_address, int _port, String _default_file_folder) throws IOException {
        m_ip_address = Inet4Address.getByName(_ip_address);
        if (!this.ping())
            throw new NullPointerException("Not reachable");
        m_servername = _servername;
        m_port = _port;
        m_type = TYPE.STORAGE;
        m_default_file_folder = _default_file_folder;
        m_path_prefix = "";
    }

    public enum TYPE {DATABASE, STORAGE};

    // ATTRIBUTES
    public InetAddress m_ip_address;
    public String m_servername;
    public int m_port;
    public JstrgServer.TYPE m_type;
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
    public File download_file(String _file_path, String _file_name, String _owner_name, String _username, String _password) throws IOException, GeneralSecurityException {
        this.connect();
        System.out.println("ATTEMBING TO GET FILE FROM JSTRG....");
        DownloadFileRequest request = new DownloadFileRequest(_file_path + _file_name, Request.type.DOWNLOAD_FILE, _owner_name, _username, _password, this);
        DownloadFileRequestAnswer answer  = (DownloadFileRequestAnswer) request.process();
        if(answer.m_status == Answer.status.READY_FOR_FILEDOWNLOAD){
            System.out.println("SHOULD RECEIVE FILE NOW");
            FileDownload download = new FileDownload(_username, _password, this, answer.m_transaction_id, _file_name, answer.m_file_size);
            File file = download.process();
            this.disconnect();
            return file;
        }
        this.disconnect();
        return null;
    }

    // UPLOAD FILE
    public Answer upload_file(File _file, String _new_file_path, String _ownername, String _file_name, String _username, String _password) throws IOException, GeneralSecurityException {
        boolean result = false;
        System.out.println("ATTEMBING TO UPLOAD FILE TO JSTRG....");
        this.connect();
        UploadFileRequest request = new UploadFileRequest(_file, _new_file_path, _ownername, _file_name, Request.type.UPLOAD_FILE_REQUEST, _username, _password, this);
        Answer answer  = request.process();

        if(answer.m_status == Answer.status.READY_FOR_FILEUPLOAD){
            System.out.println("SHOULD SEND FILE NOW");
            FileUpload upload = new FileUpload(_file, _username, _password, this, answer.m_transaction_id, request.m_file_path, request.m_file_name);
            answer = upload.process();
        }

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
    public Answer create_user(String _new_user_name, String _new_user_password, String _username, String _password, boolean _use_s3_storage, boolean _use_google_storage) throws IOException, GeneralSecurityException {
        boolean result = false;
        System.out.println("ATTEMBING TO CREATE A USER IN JSTRG....");
        CreateUserRequest request = new CreateUserRequest(_new_user_name, _new_user_password, Request.type.CREATE_USER, _username, _password, this, _use_s3_storage, _use_google_storage);
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
