package com.naikara_talk.dbutil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
//import javax.transaction.UserTransaction;

import java.sql.SQLException;
import java.sql.Connection;

public class DbUtil {

	private static Context ctx;


	static {
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


//	public static UserTransaction getUserTransaction() {
//
//		try {
//			Context c = ctx;
//		    // トランザクションの取得 TODO:外部化する
//		    return (UserTransaction)c.lookup("java:comp/env/UserTransaction");
//
//		} catch (NamingException e) {
//			e.printStackTrace();
//		    throw new RuntimeException();
//		}
//	}

	private static DataSource getDataSource() {

		try {
			Context c = ctx;
			// データソースの取得 TODO:外部化する
		    return (DataSource)c.lookup("java:/comp/env/jdbc/mysql");

		} catch (NamingException e) {
			e.printStackTrace();
		    throw new RuntimeException();
		}
	}

    public static Connection getConnection() throws SQLException {
        DataSource ds = getDataSource();
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        // トランザクション分離レベルを"TRANSACTION_READ_COMMITTED"
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        return conn;

    }
}
