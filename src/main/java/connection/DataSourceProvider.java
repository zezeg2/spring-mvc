package connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceProvider {
    private static DataSource source;

    public static DataSource getSource() {
        try {
            if (source == null) {
                Context initContext = new InitialContext(); // context.xml 준비
                Context envContext = (Context) initContext.lookup("java:/comp/env"); // context look up(JNDI 방식)
                source = (DataSource) envContext.lookup("jdbc/mydb"); // envContext 에서 jdbc Connection Pool lookup
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        return source;
    }
}