package jStrg_connector.socket_communication.transactions;

import jStrg_connector.socket_communication.communication.answers.FileUploadAnswer;
import jStrg_connector.socket_communication.core.Cryptor;
import jStrg_connector.socket_communication.core.JstrgServer;
import jStrg_connector.socket_communication.core.Upload;

import java.io.*;
import java.security.GeneralSecurityException;

public class FileUpload extends Upload {
    public FileUpload(File _file, String _username, String _password, JstrgServer _Jstrg_server, String _transaction_id, String _file_name, String _file_path) {
        super(type.UPLOAD_FILE ,_username, _password, _Jstrg_server, _transaction_id, _file, _file_name, _file_path);
    }

    public FileUploadAnswer process() throws IOException, GeneralSecurityException {
        FileUploadAnswer answer = null;
        System.out.println("Using open transaction_id: " + m_transaction_id);

        System.out.println("SENDING FILE UPLOAD REQUEST....");
        OutputStream outToServer = this.m_Jstrg_server.m_current_connection.m_socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
//        out.writeUTF(Cryptor.encrypt(this.for_server_request()));

        System.out.println("SENDING FILE");
        this.send_file(outToServer);
        System.out.println("TRANSFER DONE.");

        System.out.println("RECEIVING ANSWER");
        InputStream inFromServer = this.m_Jstrg_server.m_current_connection.m_socket.getInputStream();
        DataInputStream in3 = new DataInputStream(inFromServer);
        String server_answer = in3.readUTF();
        answer = new FileUploadAnswer(server_answer);
        return answer;
    }

    private boolean send_file(OutputStream _server_output_stream) throws IOException {
        System.out.println(m_file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(m_file.getAbsolutePath().toString());

        byte[] buffer = new byte[4096];

        while (fis.read(buffer) > 0) {
            _server_output_stream.write(buffer);
        }

        fis.close();
        return true;
    }

    private String for_server_request() {
        String query = "";
        query = "request_type:" + m_type + ";username:" + m_username + ";password:" + m_password + ";file_path:" + m_file_path + ";file_name:" + m_file_name + ";";
        return query;
    }


}
