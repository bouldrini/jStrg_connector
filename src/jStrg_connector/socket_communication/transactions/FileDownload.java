package jStrg_connector.socket_communication.transactions;

import jStrg_connector.socket_communication.communication.answers.FileDownloadAnswer;
import jStrg_connector.socket_communication.communication.answers.FileUploadAnswer;
import jStrg_connector.socket_communication.core.Download;
import jStrg_connector.socket_communication.core.JstrgServer;

import java.io.*;
import java.security.GeneralSecurityException;

public class FileDownload extends Download{
    public FileDownload(String _username, String _password, JstrgServer _Jstrg_server, int _upload_id) {
        super(_username, _password, _Jstrg_server, _upload_id);
    }

    public FileDownloadAnswer download() throws IOException, GeneralSecurityException {
        FileDownloadAnswer answer = null;
        System.out.println("SENDING FILE");

        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(this.m_file);
        OutputStream out = this.m_Jstrg_server.m_current_connection.m_socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();

        System.out.println("TRANSFER DONE.");

        System.out.println("RECEIVING ANSWER");
        InputStream inFromServer = this.m_Jstrg_server.m_current_connection.m_socket.getInputStream();
        DataInputStream in2 = new DataInputStream(inFromServer);
        String server_answer = in2.readUTF();
        answer = new FileDownloadAnswer(server_answer);
        return answer;
    }
}
