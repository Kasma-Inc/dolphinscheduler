package org.apache.dolphinscheduler.plugin.datasource.neo4j.param;

import org.apache.dolphinscheduler.common.constants.Constants;
import org.apache.dolphinscheduler.common.constants.DataSourceConstants;
import org.apache.dolphinscheduler.common.utils.JSONUtils;
import org.apache.dolphinscheduler.plugin.datasource.api.datasource.AbstractDataSourceProcessor;
import org.apache.dolphinscheduler.plugin.datasource.api.datasource.BaseDataSourceParamDTO;
import org.apache.dolphinscheduler.plugin.datasource.api.datasource.DataSourceProcessor;
import org.apache.dolphinscheduler.plugin.datasource.api.utils.PasswordUtils;
import org.apache.dolphinscheduler.spi.datasource.ConnectionParam;
import org.apache.dolphinscheduler.spi.enums.DbType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auto.service.AutoService;

@AutoService(DataSourceProcessor.class)
public class Neo4jDataSourceProcessor extends AbstractDataSourceProcessor {

    private final Logger logger = LoggerFactory.getLogger(Neo4jDataSourceProcessor.class);

    @Override
    public BaseDataSourceParamDTO castDatasourceParamDTO(String paramJson) {
        return JSONUtils.parseObject(paramJson, Neo4jDataSourceParamDTO.class);
    }

    @Override
    public BaseDataSourceParamDTO createDatasourceParamDTO(String connectionJson) {
        Neo4jConnectionParam connectionParams = (Neo4jConnectionParam) createConnectionParams(
                connectionJson);
        Neo4jDataSourceParamDTO dataSourceParamDTO = new Neo4jDataSourceParamDTO();
        dataSourceParamDTO.setUserName(connectionParams.getUser());
        dataSourceParamDTO.setDatabase(connectionParams.getDatabase());
        String address = connectionParams.getAddress();
        String[] hostSeparator = address.split(Constants.DOUBLE_SLASH);
        String[] hostPortArray = hostSeparator[hostSeparator.length - 1].split(Constants.COMMA);
        dataSourceParamDTO.setPort(Integer.parseInt(hostPortArray[0].split(Constants.COLON)[1]));
        dataSourceParamDTO.setHost(hostPortArray[0].split(Constants.COLON)[0]);
        return dataSourceParamDTO;
    }

    @Override
    public ConnectionParam createConnectionParams(BaseDataSourceParamDTO datasourceParam) {
        Neo4jDataSourceParamDTO dataSourceParamDTO = (Neo4jDataSourceParamDTO) datasourceParam;
        String address = String.format("%s%s:%s", DataSourceConstants.JDBC_NEO4J,
                dataSourceParamDTO.getHost(), dataSourceParamDTO.getPort());
        String jdbcUrl = String.format("%s/%s", address, dataSourceParamDTO.getDatabase());
        Neo4jConnectionParam neo4jConnectionParam = new Neo4jConnectionParam();
        neo4jConnectionParam.setJdbcUrl(jdbcUrl);
        neo4jConnectionParam.setDatabase(dataSourceParamDTO.getDatabase());
        neo4jConnectionParam.setAddress(address);
        neo4jConnectionParam.setUser(dataSourceParamDTO.getUserName());
        neo4jConnectionParam.setPassword(
                PasswordUtils.encodePassword(dataSourceParamDTO.getPassword()));
        neo4jConnectionParam.setDriverClassName(getDatasourceDriver());
        neo4jConnectionParam.setValidationQuery(getValidationQuery());
        return neo4jConnectionParam;
    }

    @Override
    public ConnectionParam createConnectionParams(String connectionJson) {
        return JSONUtils.parseObject(connectionJson, Neo4jConnectionParam.class);
    }

    @Override
    public String getDatasourceDriver() {
        return DataSourceConstants.ORG_NEO4J_JDBC_DRIVER;
    }

    @Override
    public String getValidationQuery() {
        return DataSourceConstants.NEO4J_VALIDATION_QUERY;
    }

    @Override
    public String getJdbcUrl(ConnectionParam connectionParam) {
        return ((Neo4jConnectionParam) connectionParam).getJdbcUrl();
    }

    @Override
    public Connection getConnection(ConnectionParam connectionParam) throws ClassNotFoundException, SQLException {
        Neo4jConnectionParam neo4jConnectionParam = (Neo4jConnectionParam) connectionParam;
        Class.forName(getDatasourceDriver());
        String user = neo4jConnectionParam.getUser();
        String password = PasswordUtils.decodePassword(neo4jConnectionParam.getPassword());
        return DriverManager.getConnection(getJdbcUrl(connectionParam), user, password);
    }

    @Override
    public DbType getDbType() {
        return DbType.NEO4J;
    }

    @Override
    public DataSourceProcessor create() {
        return new Neo4jDataSourceProcessor();
    }
}
