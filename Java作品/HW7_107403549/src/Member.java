
public class Member {
	
	private int member_id;
	private String name;
	protected static final String type_arr[] = {"home", "company", "cell"} ;
	private String type;
	private String phone;
	
	public Member(String name, int typeIndex, String phone) {
		this.name = name;
		this.type=type_arr[typeIndex];
		this.phone = phone;
	}

	public Member(int member_id, String name, String type, String phone) {
		this.member_id = member_id;
		this.name = name;
		this.type = type;
		this.phone = phone;
	}

	/**
	 * @return the member_id
	 */
	public int getMember_id() {
		return member_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	
	
}
