package jStrg_connector.socket_communication.communication.answers;

import jStrg_connector.socket_communication.core.Answer;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class UploadFileRequestAnswer extends Answer {
    public UploadFileRequestAnswer(String _server_answer) throws GeneralSecurityException, IOException {
        super(_server_answer);
    }
}
