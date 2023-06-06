package es.urjc.code.daw.library.notification;

public class NotificationEvent {

    private String message;

    public NotificationEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
