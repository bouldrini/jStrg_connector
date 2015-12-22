package jStrg_connector.socket_communication;

import java.io.*;
import java.net.Socket;

public class Connection {
    public Connection(Socket _socket, Server _server){
        this.m_socket = _socket;
        this.m_server = _server;
    }

    public Socket m_socket;
    private Server m_server;

    public boolean close() throws IOException {
        this.m_socket.close();
        return true;
    }
}