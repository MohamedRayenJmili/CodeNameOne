package com.mycompany.myapp.Entities;

public class TypeReclamation {
private int id;
private String typeName;
	public TypeReclamation(int id, String typeName) {
	super();
	this.id = id;
	this.typeName = typeName;
}
	public TypeReclamation(String typeName) {
		super();
		this.typeName = typeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "TypeReclamation [id=" + id + ", typeName=" + typeName + "]";
	}

    public TypeReclamation() {
    }
	

}
