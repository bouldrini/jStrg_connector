package jStrg_connector;

// REQUIREMENTS
// EXTERNAL

// INTERNAL
// check the classes Folder...
import jStrg_connector.socket_communication.core.Answer;
import jStrg_connector.socket_communication.core.JstrgServer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static JstrgServer jstrgServer;
    public static List<JstrgServer> SERVERS = new ArrayList<JstrgServer>();
    public static void main(String[] args) throws IOException, GeneralSecurityException {

        JstrgServer jstrg_main_server = new JstrgServer("Main-Server", "127.0.0.1", 3000);
        Main.jstrgServer = jstrg_main_server;
        Main.SERVERS.add(jstrg_main_server);
        Path path = Paths.get("test.jpg");
        File file = new File(path.toUri());


        Answer answer = jstrg_main_server.create_user("Test", "User", "somethinglikedropbox", "somethinglikedropbox");
        if(answer.m_status == Answer.status.DONE) {
            System.out.println("CREATING USER DONE");
            System.out.println("");
        } else if(answer.m_status == Answer.status.ERROR){
            System.out.println(answer.m_error_message);
        }

        answer = jstrg_main_server.upload_file(file, "/ordner_1/ordner_2", "test.jpg", "Test", "User");
        System.out.println(answer);
        if(answer.m_status == Answer.status.DONE) {
            System.out.println("FILE UPLOAD DONE");
        } else if (answer.m_status == Answer.status.ERROR){
            System.out.println("FILE UPLOAD ERROR");
            System.out.println(answer.m_error_message);
        }


    }
//    public File download_file(String _path, String _username, String _password) throws IOException, GeneralSecurityException {
//        File file = null;
//        Answer answer = Main.jstrgServer.get_file(_path, _username, _password);
//        if(answer.m_status == Answer.status.DONE) {
//            file = answer.m_file;
//        } else if (answer.m_status == Answer.status.ERROR){
//            System.out.println(answer.m_error_message);
//        }
//        return file;
//    }
}


