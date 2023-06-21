package org.apache.dolphinscheduler.plugin.datasource.neo4j;

import org.apache.dolphinscheduler.common.constants.DataSourceConstants;
import org.apache.dolphinscheduler.plugin.datasource.api.client.CommonDataSourceClient;
import org.apache.dolphinscheduler.spi.datasource.BaseConnectionParam;
import org.apache.dolphinscheduler.spi.enums.DbType;

public class Neo4jDataSourceClient extends CommonDataSourceClient {

    public Neo4jDataSourceClient(BaseConnectionParam baseConnectionParam, DbType dbType) {
        super(baseConnectionParam, dbType);
    }

    @Override
    protected void setDefaultUsername(BaseConnectionParam baseConnectionParam) {
        baseConnectionParam.setUser("neo4j");
    }

    @Override
    protected void setDefaultValidationQuery(BaseConnectionParam baseConnectionParam) {
        baseConnectionParam.setValidationQuery(DataSourceConstants.NEO4J_VALIDATION_QUERY);
    }
}
