package directory;

import java.util.ArrayList;
import File.File;

public class Directory extends File{
	
	public ArrayList<File> DirectoryContents = new ArrayList<File>();
	public String name;

	public Directory(String name) {
		this.name = name;
	}
	
	public void add(File file) {
		DirectoryContents.add(file);
	}
	
	public boolean contains(String name) {
		for (File file : DirectoryContents) {
			if (file.name == name) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return this.name;
	}
}
