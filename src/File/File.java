package File;

public class File {
	
	public String name;
	public String contents;
	
	public File() {
	}
	
	public File(String name) {
		this.name = name;
	}
	
	public File(String name, String contents) {
		this.name = name;
		this.contents = contents;
	}
	
	public String displayContents() {
		return this.contents;
	}
	
	public String toString() {
		return this.name;
	}
	

}
