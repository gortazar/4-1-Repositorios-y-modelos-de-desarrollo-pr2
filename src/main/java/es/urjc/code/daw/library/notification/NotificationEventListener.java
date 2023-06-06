package es.urjc.code.daw.library.notification;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {
    
    @EventListener
    public void logInfo(NotificationEvent event) {
        System.out.println(event.getMessage());
    }
}
