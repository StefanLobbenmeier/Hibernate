package initialtest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestJdbc {
	
	String jsbcUrl = 	"jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
	String url = 		"jdbc:mysql://localhost:3306/mysql";
	String user;
	String pass;
	
	@BeforeEach
	public void initialize() {
		try {
			user = readUser();
			pass = readPass();
		} catch (FileNotFoundException e) {
			fail("File not Found: " + e.getLocalizedMessage());
		}
	}

	private String readUser() throws FileNotFoundException {
		File userfile = new File("private/user.txt");
		return readFirstLine(userfile);
	}

	private String readPass() throws FileNotFoundException {
		File passfile = new File("private/pass.txt");
		return readFirstLine(passfile);
	}

	private String readFirstLine(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		String next = scanner.next();
		scanner.close();
		return next;
	}

	@Test
	void test() {
		try {
			System.out.println("Connecting to database: " + jsbcUrl);
			Connection myConn = DriverManager.getConnection(jsbcUrl, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
