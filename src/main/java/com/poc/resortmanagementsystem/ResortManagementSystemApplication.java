package com.poc.resortmanagementsystem;

import com.poc.resortmanagementsystem.controller.ResortController;
import com.poc.resortmanagementsystem.entity.ControllerInfo;
import com.poc.resortmanagementsystem.service.ExcelGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;


@SpringBootApplication
public class ResortManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResortManagementSystemApplication.class, args);
	}

	/*public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(ResortManagementSystemApplication.class, args);

		ResortController resortController = context.getBean(ResortController.class);
		ControllerInfo controllerInfo = resortController.getControllerInfo();

		ExcelGeneratorService excelGeneratorService = context.getBean(ExcelGeneratorService.class);
		excelGeneratorService.generateExcel(controllerInfo, "D:\\Intellij-Workspace\\excel\\controller_info.xlsx");

		context.close();
	}*/

}
