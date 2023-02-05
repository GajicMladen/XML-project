package main.java.com.xws.a1document.dto;

public class MetadataSearch {
	private String first_type;
	private String first_value;
	private String operator;
	private String second_type;
	private String second_value;
	
	public MetadataSearch() {
		
	}
	
	public String getFirst_type() {
		return first_type;
	}

	public void setFirst_type(String first_type) {
		this.first_type = first_type;
	}

	public String getSecond_type() {
		return second_type;
	}

	public void setSecond_type(String second_type) {
		this.second_type = second_type;
	}

	public String getFirst_value() {
		return first_value;
	}

	public void setFirst_value(String first_value) {
		this.first_value = first_value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSecond_value() {
		return second_value;
	}

	public void setSecond_value(String second_value) {
		this.second_value = second_value;
	}

}
