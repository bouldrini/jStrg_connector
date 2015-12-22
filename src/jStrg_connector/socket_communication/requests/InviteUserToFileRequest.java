package jStrg_connector.socket_communication.requests;

import jStrg_connector.socket_communication.Server;

public class InviteUserToFileRequest extends Request{
    public InviteUserToFileRequest(type _type, String _username, String _password, Server _server) {
        super(_type, _username, _password, _server);
    }
}
