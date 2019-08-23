package com.live;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LiveApplication {

	public static void main(String[] args) { SpringApplication.run(LiveApplication.class, args);

	}
	@Bean
    public boolean createImportantsDir(){
        boolean isCreate=false;
        File file1 = new File(System.getProperty("user.home")+"/alo/img");
        File file2 = new File(System.getProperty("user.home")+"/alo/rapports");
        File file3 = new File(System.getProperty("user.home")+"/alo/partenaires");
        File file4 = new File(System.getProperty("user.home")+"/alo/live");
        File file5 = new File(System.getProperty("user.home")+"/alo/personnels");
        if (!file1.exists()) {
             isCreate = file1.mkdirs();
        }
        if (!file2.exists()) {
            isCreate = file2.mkdirs();
        }
        if (!file3.exists()) {
            isCreate = file3.mkdirs();
        }
        if (!file4.exists()) {
            isCreate = file4.mkdirs();
        }
        if (!file5.exists()) {
            isCreate = file5.mkdirs();
        }
        return isCreate;
    }

}
