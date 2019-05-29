
public class FileData {
	long size;
	String name;
	public FileData(long s, String n) {
		size=s;
		name=n;
	}
	public long getSize() {
	return size;	
	}
	public String getName() {
		return name;
	}
	public void add(long x) {
		size+=x;
	}
	
	public void merge(FileData data) {
		data.add(size);
	}
	public String fileExtension() {
		if(name.lastIndexOf(".")>0) {
			return name.substring(name.lastIndexOf(".")+1);
		}
		return "";
	}
	public int compareTo(FileData data) {
		if(fileExtension().equals(data.fileExtension())) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
}
