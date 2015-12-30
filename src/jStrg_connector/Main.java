package jStrg_connector;

// REQUIREMENTS
// EXTERNAL

// INTERNAL
// check the classes Folder...
import jStrg_connector.socket_communication.Server;
import jStrg_connector.socket_communication.answers.Answer;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Server server;
    public static List<Server> SERVERS = new ArrayList<Server>();
    public static void main(String[] args) throws IOException, GeneralSecurityException {

        Server jstrg_main_server = new Server("Main-Server", "127.0.0.1", 3001);
        Main.server = jstrg_main_server;
        Main.SERVERS.add(jstrg_main_server);

        Path path = Paths.get("filter-mobil.jpg");
        File file = new File(path.toUri());
        Answer answer = jstrg_main_server.create_user("Test", "User", "Dropboxuser 1", "jstrg");
        System.out.println(answer);
        if(answer.m_status == Answer.status.DONE) {
            System.out.println("CREATING USER DONE");
            System.out.println("");
        } else if(answer.m_status == Answer.status.ERROR){
            System.out.println(answer.m_error_message);
        }

        System.out.println("");

//        answer = jstrg_main_server.create_user("testuser", "jstrg", "admin", "jstrg", true, true);
//        System.out.println(answer);
//        if(answer.m_status == Answer.status.DONE) {
//            System.out.println("CREATING USER DONE");
//            answer = jstrg_main_server.upload_file(file, "/ordner_1/ordner_2", "filter-mobil.jpg", "testuser", "jstrg");
//            System.out.println(answer);
//            if(answer.m_status == Answer.status.DONE) {
//                System.out.println("FILE UPLOAD DONE");
//            } else if(answer.m_status == Answer.status.ERROR){
//                System.out.println(answer.m_error_message);
//            }
//
//            System.out.println("");
//         } else if(answer.m_status == Answer.status.ERROR){
//            System.out.println(answer.m_error_message);
//        }
//
//        System.out.println("");


//
        answer = jstrg_main_server.delete_file("/ordner_1/ordner_2/filter-mobil.jpg", "Dropboxuser 5", "jstrg");
        System.out.println(answer);
        if(answer.m_status == Answer.status.DONE) {
            System.out.println("FILE DELETE DONE");
        } else if (answer.m_status == Answer.status.ERROR){
            System.out.println(answer.m_error_message);
        }
//
//        System.out.println("");
//
//        answer = jstrg_main_server.upload_file(file, "/ordner_1/ordner_2", "filter-mobil.jpg", "Dropboxuser 1", "jstrg");
//        System.out.println(answer);
//        if(answer.m_status == Answer.status.DONE) {
//            System.out.println("FILE UPLOAD DONE");
//        } else if (answer.m_status == Answer.status.ERROR){
//            System.out.println(answer.m_error_message);
//        }
//
//        System.out.println("");
//
//        answer = jstrg_main_server.delete_file_folder("/ordner_1/ordner_2", "Dropboxuser 1", "jstrg");
//        System.out.println(answer);
//        if(answer.m_status == Answer.status.DONE){
//            System.out.println("DELETE FILE FOLDER DONE");
//        } else if (answer.m_status == Answer.status.ERROR){
//            System.out.println(answer.m_error_message);
//        }
//
//        System.out.println("");
//        System.out.println("TESTING DOWNLOAD");
//                //        jstrg_main_server.get_file("lieblingsbild.jpg", "Dropbuser 1", "jstrg");
//                //        jstrg_main_server.get_file("/ordner_0/file_0", "Dropboxuser 1", "jstrg");
//                //        jstrg_main_server.get_file("lieblings", "Dropboxuser 1", "jstrg");
//                    };
//                };
//            };
//        };
    }
}
