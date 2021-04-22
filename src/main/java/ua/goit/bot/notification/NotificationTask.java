package ua.goit.bot.notification;

import lombok.extern.slf4j.Slf4j;
import ua.goit.bot.controller.Controller;
import ua.goit.bot.user.service.UserSettingsStorage;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class NotificationTask {
    private static final long START_TIME = System.currentTimeMillis();
    private static final long PERIOD = 3_600_000;
    private final Controller controller;
    private final UserSettingsStorage storage = UserSettingsStorage
            .getStorage();
    private final Timer timer = new Timer(true);

    public NotificationTask(Controller controller) {
        this.controller = controller;
    }

    public void send() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ZonedDateTime time = ZonedDateTime.now()
                        .withZoneSameInstant(ZoneId.of("Europe/Kiev"));
                int hour = time.getHour();
                List<Long> chatIdsByTime = storage.getChatIdsByTime(hour);
                log.info("ChatIds for " + time + "was collected. " + chatIdsByTime.toString());
                controller.sendText(chatIdsByTime);
            }
        }, getDelay(), PERIOD);
    }

    private long getDelay() {
        return PERIOD - START_TIME % PERIOD;
    }
}
