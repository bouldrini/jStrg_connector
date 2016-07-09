package jStrg_connector.socket_communication.communication.answers;

import jStrg_connector.socket_communication.core.Answer;
import jStrg_connector.socket_communication.core.Cryptor;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DownloadFileRequestAnswer extends Answer {

    public DownloadFileRequestAnswer(String _server_answer) throws GeneralSecurityException, IOException {
        super(_server_answer);
        String key = "";
        String value = "";

        for(String line : m_answer_string.split(";")) {
            key = line.split(":")[0];
            value = line.split(":")[1];
            if (key.equals("file_size")) {
                if(key != null){
                    m_file_size = Long.parseLong(value);
                }
            }
        }

    }

    // ATTRIBUTES
    public long m_file_size;
}
