package ru.monsterdev;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.monsterdev.tools.WorkThread;

@Slf4j
@SpringBootApplication
public class TradePlatformReactorClientApplication implements CommandLineRunner {

  @Value("${app.count}")
  private Integer threadsCount;

  @Autowired
  private ApplicationContext appContext;

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(TradePlatformReactorClientApplication.class);
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }

  @Override
  public void run(String... strings) throws Exception {
    List<Thread> workThreads = new ArrayList<>();

    for (int nI = 0; nI < threadsCount; nI++) {
      Thread thread = appContext.getBean(WorkThread.class);
      thread.start();
      workThreads.add(thread);
    }

    workThreads.forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }
}
