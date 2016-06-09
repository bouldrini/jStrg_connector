package jStrg_connector.socket_communication.communication.answers;

import jStrg_connector.socket_communication.core.Answer;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DownloadFileRequestAnswer extends Answer {

    public DownloadFileRequestAnswer(String _server_answer) throws GeneralSecurityException, IOException {
        super(_server_answer);
    }
}
