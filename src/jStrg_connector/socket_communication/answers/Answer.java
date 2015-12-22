package jStrg_connector.socket_communication.answers;

import jStrg_connector.socket_communication.Cryptor;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Answer {

    // CONSTRUCTORS
    public Answer(String _server_answer) throws GeneralSecurityException, IOException {
        String key = "";
        String value = "";
        m_answer_string = Cryptor.decrypt(_server_answer);

        // FILLING ERRORS
        for(String line : m_answer_string.split(";")) {
            key = line.split(":")[0];
            value = line.split(":")[1];
            if (key.equals("status")) {
                if (value.equals(status.ERROR.toString())) {
                    m_status = status.ERROR;
                } else if (value.equals(status.DONE.toString())) {
                    m_status = status.DONE;
                } else if (value.equals(status.READY_FOR_FILEUPLOAD.toString())) {
                    m_status = status.READY_FOR_FILEUPLOAD;
                }
            } else if (key.equals("error_code")) {
                if (value.equals(error_code.UNAUTHORIZED.toString())) {
                    m_error_code = error_code.UNAUTHORIZED;
                } else if (value.equals(error_code.LOCATION_NOT_FOUND.toString())) {
                    m_error_code = error_code.LOCATION_NOT_FOUND;
                } else if (value.equals(error_code.NOT_ENOUGH_SPACE.toString())) {
                    m_error_code = error_code.NOT_ENOUGH_SPACE;
                } else if (value.equals(error_code.INVALID_FILE_FORMAT.toString())) {
                    m_error_code = error_code.INVALID_FILE_FORMAT;
                } else if (value.equals(error_code.FILE_NOT_FOUND.toString())) {
                    m_error_code = error_code.FILE_NOT_FOUND;
                } else if (value.equals(error_code.INVALID_QUERY.toString())) {
                    m_error_code = error_code.INVALID_QUERY;
                } else if (value.equals(error_code.INSUFFITIANT_PERMISSIONS.toString())) {
                    m_error_code = error_code.INSUFFITIANT_PERMISSIONS;
                }
            } else if (key.equals("error_message")) {
                m_error_message = value;
            }
        }
    }

    // CONSTANTS
    public enum status{DONE, READY_FOR_FILEUPLOAD, ERROR}
    public enum error_code{UNAUTHORIZED, FILE_NOT_FOUND, INVALID_QUERY, INVALID_FILE_FORMAT, LOCATION_NOT_FOUND, INSUFFITIANT_PERMISSIONS, NOT_ENOUGH_SPACE, UNKNOWN_REQUEST_TYPE}


    // ATTRIBUTES
    public Answer.status m_status;
    public Answer.error_code m_error_code;
    public String m_error_message;
    public String m_answer_string;

    @Override
    public String toString() {
        return "<Answer::{m_status: " + m_status + ", m_error_code: " + m_error_code + ", m_error_message: " + m_error_message + ", m_answer_string: " + m_answer_string + "}>";
    }
}
