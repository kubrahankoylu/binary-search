package likeGoogle;

public class attributeOfFile {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int compareTo1(attributeOfFile x){//if string of this.name equals to the string of x.name this method return 0
											//if string of this.name less then the string of x.name this method return -1
											//if string of this.name greater then the string of x.name this method return 1
		return new String(this.name).compareTo(x.name);
	}
	public int compareTo2(attributeOfFile x){//if integer of this.id equals to the integer of x.id this method return 0
											//if integer of this.id less then the integer of x.id this method return -1
											//if integer of this.id greater then the integer of x.id this method return 1
		return new Integer(this.id).compareTo(x.id);
	}
	
}
