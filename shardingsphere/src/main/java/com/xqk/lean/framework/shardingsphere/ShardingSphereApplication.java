package com.xqk.lean.framework.shardingsphere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class ShardingSphereApplication {
	@Autowired
	List<DataSource> dataSourceList;

	public static void main(String[] args) {
		SpringApplication.run(ShardingSphereApplication.class, args);
	}

}
