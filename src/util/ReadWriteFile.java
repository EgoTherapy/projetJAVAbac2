package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReadWriteFile {
	public static String read(String fileName) {
		String str = "";
		try(Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
			while(scanner.hasNextLine()) {
				str += scanner.nextLine();
			}
			return str;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void write(String fileName, String content) {
		try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8)) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
