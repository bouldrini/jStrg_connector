package jStrg_connector.socket_communication.communication.requests;

import jStrg_connector.socket_communication.core.JstrgServer;
import jStrg_connector.socket_communication.core.Request;

public class InviteUserToFileFolderRequest extends Request {
    public InviteUserToFileFolderRequest(type _type, String _username, String _password, JstrgServer _Jstrg_server) {
        super(_type, _username, _password, _Jstrg_server);
    }
}
