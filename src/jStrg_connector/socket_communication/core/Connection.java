package jStrg_connector.socket_communication.core;

import java.io.*;
import java.net.Socket;

public class Connection {
    public Connection(Socket _socket, JstrgServer _Jstrg_server){
        this.m_socket = _socket;
        this.m_Jstrg_server = _Jstrg_server;
    }

    public Socket m_socket;
    private JstrgServer m_Jstrg_server;

    public boolean close() throws IOException {
        this.m_socket.close();
        return true;
    }
}