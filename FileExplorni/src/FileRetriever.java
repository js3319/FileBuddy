import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FileRetriever {
	//selects files in preparation for modifiers 
	File fileName;
	public static String PS;

	//path separator
	//File fileLoc = new File(fileName);
	public static final String directory = "C:\\Users";


	public FileRetriever(String name) throws IOException {
		checkOS();

		createFolder(name);
		//System.out.println(fileName.getParent()+PS+getFileExtension(name));

		//moveFile(name, (fileName.getParent()+"PS"+getFileExtension(name)));
		massMove(fileName);
		JOptionPane.showMessageDialog(null,"Process Complete!");
	}
	public void checkOS() {
		if(System.getProperty("os.name").equals("Mac OS X")){

			PS = "/";
		}
		else {
			PS="\\";
		}
	}

	public String getFileExtension(String name) {
		if(name.indexOf(".")>0) {


			return(name.substring(1+name.lastIndexOf(".")));}
		return "";
	}

	public void openFolder() {

	}

	public void createFolder(String name) {
		fileName=new File(name);	
		//fileName is the original input
		String parent = fileName.getParent();
		//gets parent directory
		String folderName = parent +PS+ getFileExtension(name);
		//generates foldername based on parent directory and file extension name
		System.out.println (folderName + "1");
		new File(folderName).mkdirs();
		System.out.println(getFileExtension(name + "2"));
	}

	public void moveFile(String original, String newLoc) {
		File select= new File(original);
		System.out.println(newLoc+actualFileName(original)+"3");
		//System.out.println(select.getName()+"4");

		select.renameTo(new File(newLoc+PS+actualFileName(original)));


	}
	public String actualFileName(String sample) {
		int count = 1;
		for(int x=sample.length();x>0;x--) {
			if (sample.substring(x-1,x).equals(PS)){
				count--;
				if(count ==0) {
					return PS+sample.substring(x);
				}

			}
		}
		return null;
	}

	public String actualFileName(File file) {
		return file.getName();
	}

	//	public void massMove(File directory) {
	//		List<String> results = new ArrayList<String>();
	//
	//
	//		File[] files = new File(directory.getParent()).listFiles();
	//		//If this pathname does not denote a directory, then listFiles() returns null. 
	//		
	////		for(int x=0; x<files.length;x++) {
	////			 
	////		    if(getFileExtension(files[x].getName()).toLowerCase().equals(getFileExtension(directory.getName()).toLowerCase())){
	////		    	moveFile(files[x].getPath(),files[x].getParent()+PS+getFileExtension(files[x].getActualName())+PS+files[x].getName());
	////		    	System.out.println(files[x].getParent()+PS+getFileExtension(files[x].getName())+PS+files[x].getName()+"5");
	////
	////		    }
	////		}
	//		
	//
	//		for (File file : files) {
	//		    if (file.isFile()) {
	//		        results.add(file.getName());
	//		        if(getFileExtension(file.getName()).toLowerCase().equals(getFileExtension(directory.getName()).toLowerCase())){
	//			    	moveFile(file.getPath(),file.getParent()+PS+actualFileName(file.getName()));
	//			    	System.out.println(file.getParent()+PS+actualFileName(file.getName()));
	//		        }
	//		    }
	//		}

	//		    for(int x=0; x< results.size();x++) {
	//		    	
	//		    
	//		    if(getFileExtension(files[x].getName()).toLowerCase().equals(getFileExtension(directory.getName()).toLowerCase())){
	//		    	moveFile(files[x].getPath(),files[x].getParent()+PS+files[x].getName());
	//		    	System.out.println(files[x].getParent()+PS+files[x].getName()+"5");
	//
	//		    }
	//		    


	//		for(int x=0;x<results.size();x++) {
	//			if(getFileExtension(directory.getName()).equals(getFileExtension(files[x].getName()))) {

	//			System.out.println(files[x].getPath());
	//			System.out.println(fileName.getParent()+PS+getFileExtension(files[x].getName()));
	//		}
	//		System.out.println(results);}
	public void massMove(File directory) {
		List<String> results = new ArrayList<String>();


		File[] files = new File(directory.getParent()).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
			if(getFileExtension(file.getName()).toLowerCase().equals(getFileExtension(directory.getName()).toLowerCase())){
				moveFile(file.getPath(),file.getParent()+PS+getFileExtension(file.getName()));
				//	System.out.println(file.getParent()+PS+getFileExtension(file.getName()));

			}
		}
	}



}
