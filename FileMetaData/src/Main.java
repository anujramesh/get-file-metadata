import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws IOException, SQLException {
		
		// Choose directory folder you would like to search through
		// Delimiting character to separate directories is '/' on Linux/macOS (forward slash)
		// and '\' on Windows (backslash); you will need an extra '\' in that case
		
		File folder = new File("C:\\Users\\anujr\\Desktop");
		FileMetaData.getFileMetaData(folder);
	}
}
