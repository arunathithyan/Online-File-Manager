package homework6;

import java.util.Date;



public class File {
		
		Integer id;
	    String name;
	    String type; 
	    long size;
	    Date date;
	    Integer parentId;
	    boolean isFolder;
	    Integer ownerId;
	    
	    
	    public File(Integer id,String name, String type, long size, Date date, Integer parentId, boolean isFolder,Integer ownerId)
	     {
	     	this.id=id;
	     	this.name=name; 
		 	this.type=type;
		   	this.size=size;
		    this.date=date;
		    this.parentId=parentId;
		    this.isFolder=isFolder;
		    this.ownerId=ownerId;
		 }
	    
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public long getSize() {
			return size;
		}
		public void setSize(long size) {
			this.size = size;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
		public Integer getParentId() {
			return parentId;
		}


		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}


		public Integer getOwnerId() {
			return ownerId;
		}


		public void setOwnerId(Integer ownerId) {
			this.ownerId = ownerId;
		}


		public boolean isFolder() {
			return isFolder;
		}
		public void setFolder(boolean isFolder) {
			this.isFolder = isFolder;
		}
	    
	    
	    
	    
	    
}

