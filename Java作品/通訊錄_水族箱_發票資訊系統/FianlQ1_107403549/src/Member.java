
public class Member {
	
	private int member_id;
	private String name;
	protected static final String type_arr[] = {"home", "company", "cell"} ;
	private String type;
	private String phone;
	protected static final String crowd_arr[] = {"classmate","family","friend","undefined"} ;
	private String crowd=crowd_arr[3];
	private String email=null;
	
	
	public Member(String name, int typeIndex, String phone, int crowdindex, String email) {
		this.name = name;
		this.type=type_arr[typeIndex];
		this.phone = phone;
		setCrowd(crowdindex);
		this.email = email;
	}

	public Member(int member_id, String name, String type, String phone, String crowd, String email) {
		this.member_id = member_id;
		this.name = name;
		this.type = type;
		this.phone = phone;
		this.crowd = crowd;
		this.email = email;
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
	
	public void setCrowd(int index) {
		crowd = crowd_arr[index];
	}
	
	public void setEmail(String email) {
		this.email=email;
	}

	/**
	 * @return the crowd
	 */
	public String getCrowd()
	{
		return crowd;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	
	
}
