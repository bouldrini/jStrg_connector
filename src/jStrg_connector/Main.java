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

        JstrgServer jstrg_main_server = new JstrgServer("Main-Server", "127.0.0.1", 3000, "/tmp/downloads");
        Main.jstrgServer = jstrg_main_server;
        Main.SERVERS.add(jstrg_main_server);

        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println();
        System.out.println();
        Main.create_users();
        System.out.println();
        System.out.println();
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println();
        System.out.println();
        Main.test_uploads();
        System.out.println();
        System.out.println();
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println();
        System.out.println();
        Main.test_downloads();
        System.out.println();
        System.out.println();
        System.out.println("============================================================================");
        System.out.println("============================================================================");
        System.out.println("============================================================================");
    }

    private static boolean test_downloads() throws IOException, GeneralSecurityException {
        File file;
        file = Main.jstrgServer.download_file("/Ordner_0/", "file_0.css.php", "Dropboxuser 1", "Dropboxuser 1", "jstrg");
        if(file != null) {
            System.out.println("FILE DOWNLOAD DONE");
        } else if (file == null) {
            System.out.println("FILE DOWNLOAD ERROR");
        }
        return true;
    }

    public static boolean create_users() throws IOException, GeneralSecurityException {
        Answer answer;
        answer = Main.jstrgServer.create_user("Remoteuser1", "user", "somethinglikedropbox", "somethinglikedropbox");
        if(answer.m_status == Answer.status.DONE) {
            System.out.println("CREATING USER DONE");
            System.out.println("");
        } else if(answer.m_status == Answer.status.ERROR){
            System.out.println(answer.m_error_message);
        }
        answer = Main.jstrgServer.create_user("Remoteuser2", "user", "somethinglikedropbox", "somethinglikedropbox");
        if(answer.m_status == Answer.status.DONE) {
            System.out.println("CREATING USER DONE");
            System.out.println("");
        } else if(answer.m_status == Answer.status.ERROR){
            System.out.println(answer.m_error_message);
        }
        return true;
    }

    public static boolean test_uploads() throws IOException, GeneralSecurityException {
        Answer answer;
        Path path = Paths.get("phil.zip");
        File file = new File(path.toUri());

        System.out.println("UPLOADING TO OWN ROOT DIRECTORY");
        answer = Main.jstrgServer.upload_file(file, "/", "Remoteuser1", "phil.zip", "Remoteuser1", "user");
        if(answer.m_status == Answer.status.DONE) {
            System.out.println("FILE UPLOAD DONE");
        } else if (answer.m_status == Answer.status.ERROR){
            System.out.println("FILE UPLOAD ERROR");
            System.out.println(answer.m_error_message);
        }

//        System.out.println("OVERWRITE OWN FILE");
//        answer = Main.jstrgServer.upload_file(file, "/", "Remoteuser1", "musicshop.pdf", "Remoteuser1", "user");
//        if(answer.m_status == Answer.status.DONE) {
//            System.out.println("FILE UPLOAD DONE");
//        } else if (answer.m_status == Answer.status.ERROR){
//            System.out.println("FILE UPLOAD ERROR");
//            System.out.println(answer.m_error_message);
//        }
//
//        System.out.println("UPLOADING TO NOT EXISTING DIRECTORY IN OWN ROOT");
//        answer = Main.jstrgServer.upload_file(file, "/testfolder/testfolder/", "Remoteuser1", "musicshop.pdf", "Remoteuser1", "user");
//        if(answer.m_status == Answer.status.DONE) {
//            System.out.println("FILE UPLOAD DONE");
//        } else if (answer.m_status == Answer.status.ERROR){
//            System.out.println("FILE UPLOAD ERROR");
//            System.out.println(answer.m_error_message);
//        }
//
//        path = Paths.get("phil.zip");
//        file = new File(path.toUri());
//
//        System.out.println("WRITE NEW FILE IN FOREIGN EXISTING DIRECTORY");
//        answer = Main.jstrgServer.upload_file(file, "/testfolder/testfolder/", "Remoteuser1", "phil.zip", "Remoteuser2", "user");
//        if(answer.m_status == Answer.status.DONE) {
//            System.out.println("FILE UPLOAD DONE");
//        } else if (answer.m_status == Answer.status.ERROR){
//            System.out.println("FILE UPLOAD ERROR");
//            System.out.println(answer.m_error_message);
//        }
//
//        path = Paths.get("musicshop.pdf");
//        file = new File(path.toUri());
//
//        System.out.println("OVERWRITE AN EXISTING FILE IN A FOREIGN DIRECTORY");
//        answer = Main.jstrgServer.upload_file(file, "/testfolder/testfolder/", "Remoteuser1", "musicshop.pdf", "Remoteuser2", "user");
//        if(answer.m_status == Answer.status.DONE) {
//            System.out.println("FILE UPLOAD DONE");
//        } else if (answer.m_status == Answer.status.ERROR){
//            System.out.println("FILE UPLOAD ERROR");
//            System.out.println(answer.m_error_message);
//        }

        return true;
    }
}


