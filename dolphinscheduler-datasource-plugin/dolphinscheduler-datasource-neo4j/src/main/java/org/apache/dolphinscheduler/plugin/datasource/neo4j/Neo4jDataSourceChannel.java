package org.apache.dolphinscheduler.plugin.datasource.neo4j;

import org.apache.dolphinscheduler.spi.datasource.BaseConnectionParam;
import org.apache.dolphinscheduler.spi.datasource.DataSourceChannel;
import org.apache.dolphinscheduler.spi.datasource.DataSourceClient;
import org.apache.dolphinscheduler.spi.enums.DbType;

public class Neo4jDataSourceChannel implements DataSourceChannel {

    @Override
    public DataSourceClient createDataSourceClient(BaseConnectionParam baseConnectionParam,
                                                   DbType dbType) {
        return new Neo4jDataSourceClient(baseConnectionParam, dbType);
    }
}
