package com.office.yancao.untils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 烟叶超时检查调度器
 * 集成到Spring Boot应用中，随应用启动自动执行
 */
@Component
public class TobaccoTimeoutScheduler implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(TobaccoTimeoutScheduler.class);

    @Override
    public void run(ApplicationArguments args) {
        // 初始化定时任务：启动后立即执行一次，之后每天执行一次
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
                TobaccoTimeoutChecker::checkAndUpdateTimeout,
                0, // 初始延迟0秒
                1, // 间隔1天
                TimeUnit.DAYS
        );
        logger.info("烟叶超时检查调度器已启动，将每天自动执行检查...");
    }
}