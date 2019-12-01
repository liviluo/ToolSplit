package com.livi.tools.schedule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*spring自带的定时任务，无需添加任何依赖，需要在启动类加上@EnableScheduling*/
@Component
public class ScheduleTask {

	private static final Logger log = LoggerFactory.getLogger("baseLog");

	/* 指定执行时间 */
	/* 秒 分 时 日 月 星期 年 */
	@Scheduled(cron = "0 0 8 * * ?") // 每天早上8点执行
	private void runByTiming() {
		this.task("runByTiming");
	}

	/* 指定时间间隔 */
	/* 单位毫秒 */
	@Scheduled(fixedDelay = 1000 * 10) // 每隔10秒执行
	private void runByInterval() {
		this.task("runByInterval");
	}

	/* 从配置文件获取执行时间 */
	/* 单位毫秒 */
	@Scheduled(cron = "${ScheduleRunTime}") //
	private void runByConfig() {
		this.task("runByConfig");
	}

	private void task(String who) {
		File file = new File("ScheduleTask.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		String content = who + " : " + new Date() + "\r\n";
		System.out.println(content);
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(content);
			fw.flush();
			fw.close();
			log.info(content);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

}
