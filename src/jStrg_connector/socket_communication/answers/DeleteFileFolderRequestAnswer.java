package jStrg_connector.socket_communication.answers;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DeleteFileFolderRequestAnswer extends Answer {
    public DeleteFileFolderRequestAnswer(String _server_answer) throws GeneralSecurityException, IOException {
        super(_server_answer);
    }
}
