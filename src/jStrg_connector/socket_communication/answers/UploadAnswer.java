package jStrg_connector.socket_communication.answers;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class UploadAnswer extends Answer{
    public UploadAnswer(String _server_answer) throws GeneralSecurityException, IOException {
        super(_server_answer);
    }
}
