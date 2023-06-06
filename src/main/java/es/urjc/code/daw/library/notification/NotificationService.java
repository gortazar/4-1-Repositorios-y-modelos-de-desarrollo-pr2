package es.urjc.code.daw.library.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.togglz.core.manager.FeatureManager;

import es.urjc.code.daw.library.toggle.Features;

@Component
public class NotificationService implements ApplicationEventPublisherAware {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private ApplicationEventPublisher applicationEventPublisher;
    private FeatureManager featureManager;

    public NotificationService(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    public void notify(String message) {
        if(featureManager.isActive(Features.EVENT_BASED_COMMUNICATION)) {
            applicationEventPublisher.publishEvent(new NotificationEvent("Async message: " + message));
        } else {
            logger.info("Sync message: " + message);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}

