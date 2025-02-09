package com.xqk.lean.framework.shardingsphere.readwritesplitting;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.algorithm.core.config.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepositoryConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.audit.ShardingAuditStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.single.api.config.SingleRuleConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * 读写分离配置
 *
 * @author qiankun.xiong
 * @version 1.0.0
 * @since 2025/1/24 10:45
 */
@Configuration
public class ReadWriteSplittingConfig {

    @Bean
    public DataSource getDataSource() throws SQLException {
        var props = new Properties();
        props.setProperty("sql-show", Boolean.TRUE.toString());
        return ShardingSphereDataSourceFactory.createDataSource("ds",
                createModeConfiguration(),
                createDataSourceMap(),
                List.of(createReadwriteSplittingRuleConfiguration()
                        // , createShardingRuleConfiguration()
                        , createSingleRuleConfiguration()
                ),
                props);
    }

    /**
     * 创建模式配置
     *
     * @return 模式配置
     */
    private ModeConfiguration createModeConfiguration() {
        return new ModeConfiguration("Standalone", new StandalonePersistRepositoryConfiguration("JDBC", new Properties()));
    }

    /**
     * 创建数据源Map
     *
     * @return 数据源Map
     */
    private Map<String, DataSource> createDataSourceMap() {
        var result = new HashMap<String, DataSource>(3, 1);
        result.put("write_ds", createWriteDataSource());
        result.put("read_ds_0", createReadDataSource());
        return result;
    }

    public DataSource createWriteDataSource() {
        return DataSourceBuilder.create()
                                .url("jdbc:mysql://localhost:3306/shardingsphere?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai" +
                                        "&allowPublicKeyRetrieval=true")
                                .username("root")
                                .password("123456")
                                .type(HikariDataSource.class)
                                .driverClassName("com.mysql.cj.jdbc.Driver")
                                .build();
    }


    public DataSource createReadDataSource() {
        return DataSourceBuilder.create()
                                .url("jdbc:mysql://localhost:3307/shardingsphere?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai" +
                                        "&allowPublicKeyRetrieval=true")
                                .username("root")
                                .password("123456")
                                .type(HikariDataSource.class)
                                .driverClassName("com.mysql.cj.jdbc.Driver")
                                .build();
    }

    /**
     * 读写分离配置
     *
     * @return 读写分离配置write_ds
     */
    public ReadwriteSplittingRuleConfiguration createReadwriteSplittingRuleConfiguration() {
        var dataSourceConfig = new ReadwriteSplittingDataSourceRuleConfiguration("read_write_splitting_ds", "write_ds", List.of("read_ds_0"), "weight_lb");

        var algorithmProps = new Properties();
        algorithmProps.setProperty("read_ds_0", "1");
        var algorithmConfigMap = new HashMap<String, AlgorithmConfiguration>(1);
        algorithmConfigMap.put("weight_lb", new AlgorithmConfiguration("WEIGHT", algorithmProps));

        return new ReadwriteSplittingRuleConfiguration(Collections.singleton(dataSourceConfig), algorithmConfigMap);
    }

    public SingleRuleConfiguration createSingleRuleConfiguration() {
        return new SingleRuleConfiguration(Collections.singleton("read_write_splitting_ds.*"),"read_write_splitting_ds");
    }

    /**
     * 分库分表配置
     *
     * @return 分库分表配置
     */
    private ShardingRuleConfiguration createShardingRuleConfiguration() {
        var result = new ShardingRuleConfiguration();
        result.setTables(getShardingTableRuleConfigurations());
        // result.getTables().add(getOrderTableRuleConfiguration());
        // result.getTables().add(getOrderItemTableRuleConfiguration());
        // //绑定表规则
        // result.getBindingTableGroups().add(new ShardingTableReferenceRuleConfiguration("foo", "t_order, t_order_item"));
        // // 默认分库策略
        // result.setDefaultDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "inline"));
        // // 默认分表策略
        // result.setDefaultTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "standard_test_tbl"));
        // var props = new Properties();
        // props.setProperty("algorithm-expression", "demo_ds_${user_id % 2}");
        // result.getShardingAlgorithms().put("inline", new AlgorithmConfiguration("INLINE", props));
        // result.getShardingAlgorithms().put("standard_test_tbl", new AlgorithmConfiguration("STANDARD_TEST_TBL", new Properties()));
        // result.getKeyGenerators().put("snowflake", new AlgorithmConfiguration("SNOWFLAKE", new Properties()));
        // result.getAuditors().put("sharding_key_required_auditor", new AlgorithmConfiguration("DML_SHARDING_CONDITIONS", new Properties()));
        return result;
    }

    private Collection<ShardingTableRuleConfiguration> getShardingTableRuleConfigurations() {
        var result = new ArrayList<ShardingTableRuleConfiguration>(1);
        var table = new ShardingTableRuleConfiguration("sharding_sphere_table", "read_write_splitting_ds.sharding_sphere_table");
        result.add(table);
        return result;
    }

    private ShardingTableRuleConfiguration getOrderTableRuleConfiguration() {
        var result = new ShardingTableRuleConfiguration("t_order", "demo_ds_${0..1}.t_order_${[0, 1]}");
        result.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_id", "snowflake"));
        result.setAuditStrategy(new ShardingAuditStrategyConfiguration(Collections.singleton("sharding_key_required_auditor"), true));
        return result;
    }

    private ShardingTableRuleConfiguration getOrderItemTableRuleConfiguration() {
        var result = new ShardingTableRuleConfiguration("t_order_item", "demo_ds_${0..1}.t_order_item_${[0, 1]}");
        result.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_item_id", "snowflake"));
        return result;
    }
}
