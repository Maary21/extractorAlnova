package mx.com.baz.pool.config;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @description
 * 
 * @project pool-conexiones
 * @author Mary C
 * @created Agosto 2022
 * @version 1.0
 */

@Configuration
//@EnableScheduling
public class DataSourseBDConfig {

	private static final HikariConfig config;
	private static final HikariDataSource ds;

	static {
		config = new HikariConfig();
		String connectionUrl = "jdbc:oracle:thin:@10.82.56.194:1521/eobddespdb"; //jdbc:oracle:thin:@10.82.56.210:4410/EOBDDES
		config.setJdbcUrl(connectionUrl);
		config.setUsername("USRADMINDES");
		config.setPassword("ADMADNg39jK12Jo");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setConnectionTimeout(300000);
        config.setConnectionTimeout(120000);
        config.setLeakDetectionThreshold(900000); //aumentar

		ds = new HikariDataSource(config);
	}

	public DataSourseBDConfig() {

	}

	public static final Connection getConnectionEO() throws SQLException {
		return ds.getConnection();
	}

}
