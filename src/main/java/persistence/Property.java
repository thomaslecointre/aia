package persistence;

public class Property {
	int id, activity_id;
	String name;
	Value_type v_type;
	
	public Property(int id, int activity_id, String name, Value_type v_type) {
		super();
		this.id = id;
		this.activity_id = activity_id;
		this.name = name;
		this.v_type = v_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Value_type getV_type() {
		return v_type;
	}
	public void setV_type(Value_type v_type) {
		this.v_type = v_type;
	}
	
	
}
