package jStrg_connector.socket_communication.transactions;

import jStrg_connector.socket_communication.communication.answers.FileDownloadAnswer;
import jStrg_connector.socket_communication.communication.answers.FileUploadAnswer;
import jStrg_connector.socket_communication.core.Download;
import jStrg_connector.socket_communication.core.JstrgServer;

import java.io.*;
import java.security.GeneralSecurityException;

public class FileDownload extends Download{
    public FileDownload(String _username, String _password, JstrgServer _Jstrg_server, String _transaction_id, String _file_title, long _file_size) {
        super(_username, _password, _Jstrg_server, _transaction_id, _file_size);
        m_file_size = _file_size;
        m_file_title = _file_title;
    }

    // ATTRIBUTES
    public long m_file_size;
    public String m_file_title;

    public File process() throws IOException, GeneralSecurityException {
        System.out.println("EXPECT THE SERVER TO START SENDING");
        try {
            File floating_file = new File( m_Jstrg_server.m_default_file_folder + "" + m_file_title);

            floating_file.getParentFile().mkdirs();
            floating_file.createNewFile();

            DataInputStream dis = new DataInputStream(m_Jstrg_server.m_current_connection.m_socket.getInputStream());
            try {
                FileOutputStream fos = new FileOutputStream(floating_file.getAbsolutePath());
                byte[] buffer = new byte[4096];

                int read = 0;
                int totalRead = 0;
                long remaining = m_file_size;

                while((read = dis.read(buffer, 0, Math.min(buffer.length, (int)remaining))) > 0) {
                    totalRead += read;
                    remaining -= read;
                    System.out.println(remaining);
                    fos.write(buffer);
                }

                fos.close();

                return floating_file;
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. ");
                return null;
            }
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
            return null;
        }
    }
}
