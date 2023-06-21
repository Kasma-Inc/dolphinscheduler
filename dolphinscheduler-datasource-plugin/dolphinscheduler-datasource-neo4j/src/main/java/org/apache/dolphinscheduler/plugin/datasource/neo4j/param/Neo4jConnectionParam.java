package org.apache.dolphinscheduler.plugin.datasource.neo4j.param;

import org.apache.dolphinscheduler.spi.datasource.BaseConnectionParam;

public class Neo4jConnectionParam extends BaseConnectionParam {

    @Override
    public String toString() {
        return "Neo4jConnectionParam{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", database='" + database + '\'' +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", driverLocation='" + driverLocation + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", validationQuery='" + validationQuery + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
