package jStrg_connector.socket_communication.answers;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DeleteFileRequestAnswer extends Answer {
    public DeleteFileRequestAnswer(String _server_answer) throws GeneralSecurityException, IOException {
        super(_server_answer);
    }
}
