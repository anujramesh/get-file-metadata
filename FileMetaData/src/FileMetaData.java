import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileMetaData {

	public static void getFileMetaData(final File directory) throws IOException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// "root" and "mysql123" will be replaced by the name and password of your database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "mysql123"); 
			Statement stmt = con.createStatement();
			
			for(final File fileEntry : directory.listFiles()) {
				if(fileEntry.isDirectory()) {
					getFileMetaData(fileEntry);
				} else {
					Pattern pattern = Pattern.compile(".pdf"); // Will currently search for pdf files; can be changed to any file extension
					Matcher matcher = pattern.matcher(fileEntry.getName());
					if(matcher.find()) {
						 System.out.println(fileEntry.getName());
						 System.out.println(fileEntry.getPath());
				        Path path = Paths.get(fileEntry.getPath());
				        Map f  = Files.readAttributes(path, "*");
				        System.out.println(f);
				        
						String sql = "insert into pdf_file_metadata " // Change pdf_file_metadata to the name of your SQL table
						           + " (FileName, BasicAttributes)"
								   + " values ('" + fileEntry.getName() + "','" + f +"')";
						
						stmt.executeUpdate(sql);
						System.out.println("Insert complete.");

					}
				}
			}
				
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
