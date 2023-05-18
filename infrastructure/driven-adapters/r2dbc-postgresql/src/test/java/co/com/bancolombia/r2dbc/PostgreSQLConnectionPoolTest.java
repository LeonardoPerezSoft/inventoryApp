package co.com.bancolombia.r2dbc;

import co.com.bancolombia.r2dbc.config.PostgreSQLConnectionPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostgreSQLConnectionPoolTest {

    @Test
    void getConnectionConfig() {
        PostgreSQLConnectionPool postgreSQLConnectionPool = new PostgreSQLConnectionPool();
        Assertions.assertNotNull(postgreSQLConnectionPool.getConnectionConfig());
    }
}
