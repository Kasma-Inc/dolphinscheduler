package org.apache.dolphinscheduler.plugin.datasource.neo4j;

import org.apache.dolphinscheduler.spi.datasource.DataSourceChannel;
import org.apache.dolphinscheduler.spi.datasource.DataSourceChannelFactory;

import com.google.auto.service.AutoService;

@AutoService(DataSourceChannelFactory.class)
public class Neo4jDataSourceChannelFactory implements DataSourceChannelFactory {

    @Override
    public String getName() {
        return "neo4j";
    }

    @Override
    public DataSourceChannel create() {
        return new Neo4jDataSourceChannel();
    }
}
