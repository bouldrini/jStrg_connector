package jStrg_connector.socket_communication.communication.requests;

import jStrg_connector.socket_communication.core.JstrgServer;
import jStrg_connector.socket_communication.core.Request;

public class InviteUserToFileRequest extends Request {
    public InviteUserToFileRequest(type _type, String _username, String _password, JstrgServer _Jstrg_server) {
        super(_type, _username, _password, _Jstrg_server);
    }
}
