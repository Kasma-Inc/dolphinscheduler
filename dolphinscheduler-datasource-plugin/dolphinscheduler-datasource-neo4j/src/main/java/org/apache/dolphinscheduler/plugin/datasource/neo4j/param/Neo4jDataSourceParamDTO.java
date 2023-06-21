package org.apache.dolphinscheduler.plugin.datasource.neo4j.param;

import org.apache.dolphinscheduler.plugin.datasource.api.datasource.BaseDataSourceParamDTO;
import org.apache.dolphinscheduler.spi.enums.DbType;

public class Neo4jDataSourceParamDTO extends BaseDataSourceParamDTO {

    @Override
    public String toString() {
        return "Neo4jDataSourceParamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", database='" + database + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", other=" + other +
                '}';
    }

    @Override
    public DbType getType() {
        return DbType.NEO4J;
    }
}
