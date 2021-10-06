import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MemberHelper {

	public MemberHelper() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 靜態變數，儲存MemberHelper物件
	 */
	private static MemberHelper mh;
	/**
	 * 儲存JDBC資料庫連線
	 */
	private Connection conn = null;
	/**
	 * 儲存JDBC預準備之SQL指令
	 */
	private PreparedStatement pres = null;

	/**
	 * FUNCTIONS
	 */
	/**
	 * 實作Singleton（單例模式）， 僅允許建立一個MemberHelper物件
	 *
	 * @return the helper 回傳MemberHelper物件
	 */
	public static MemberHelper getHelper() {
		/**
		 * Singleton檢查是否已經有MemberHelper物件， 若無則new一個，若有則直接回傳
		 */
		if (mh == null) {
			mh = new MemberHelper();
		}
		return mh;
	}

	/**
	 * 建立該名聯絡人至資料庫
	 *
	 * @param m 一名會員之Member物件
	 * @return 回傳SQL指令執行影響列數
	 */
	public int create(Member m) {
		/**
		 * 記錄實際執行之SQL指令
		 */
		String exexcute_sql = "";
		/**
		 * 紀錄SQL總行數
		 */
		int row = 0;
		try {
			/**
			 * 取得資料庫之連線
			 */
			conn = DBMgr.getConnection();
			/**
			 * QL指令
			 */
			String sql = "INSERT INTO `people`(`name`, `type`, `phone`, `crowd`, `email`) " + "VALUES (?,?,?,?,?)";
			/**
			 * 取得所需之參數
			 */
			String name = m.getName();
			String type = m.getType();
			String phone = m.getPhone();
			String crowd = m.getCrowd();
			String email = m.getEmail();

			/**
			 * 將參數回填至SQL指令當中
			 */
			pres = conn.prepareStatement(sql);
			pres.setString(1, name);
			pres.setString(2, type);
			pres.setString(3, phone);
			pres.setString(4, crowd);
			pres.setString(5, email);

			/**
			 * 執行新增之SQL指令並記錄影響之行數
			 */
			row = pres.executeUpdate();
			/**
			 * 紀錄真實執行的SQL指令，並印出
			 */
			exexcute_sql = pres.toString();
			System.out.println(exexcute_sql);
		} catch (SQLException e) {
			/**
			 * 印出JDBC SQL指令錯誤
			 */
			System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			/**
			 * 若錯誤則印出錯誤訊息
			 */
			e.printStackTrace();
		} finally {
			/**
			 * 關閉連線並釋放所有資料庫相關之資源
			 */
			DBMgr.close(pres, conn);
		}

		return row;

	}

	/**
	 * 獲取所有資料庫
	 *
	 * @return 回傳資料庫中所有聯絡人
	 */
	@SuppressWarnings("null")
	public ArrayList<Member> getAllMember() {
		/**
		 * 記錄實際執行之SQL指令
		 */
		String exexcute_sql = "";

		ArrayList<Member> results = new ArrayList<Member>();

		ResultSet resultSet = null;

		try {
			/**
			 * 取得資料庫之連線
			 */
			conn = DBMgr.getConnection();
			/**
			 * QL指令
			 */
			String sql = "SELECT * FROM `people`";

			/**
			 * 將參數回填至SQL指令當中
			 */
			pres = conn.prepareStatement(sql);

			/**
			 * 執行新增之SQL指令並記錄影響之行數
			 */
			resultSet = pres.executeQuery();

			while (resultSet.next()) {
				results.add(new Member(resultSet.getInt("MemberID"), resultSet.getString("name"),
						resultSet.getString("type"), resultSet.getString("phone"),resultSet.getString("crowd"),resultSet.getString("email")));
			}

			/**
			 * 紀錄真實執行的SQL指令，並印出
			 */
			exexcute_sql = pres.toString();
			System.out.println(exexcute_sql);
		} catch (SQLException e) {
			/**
			 * 印出JDBC SQL指令錯誤
			 */
			System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			/**
			 * 若錯誤則印出錯誤訊息
			 */
			e.printStackTrace();
		} finally {
			/**
			 * 關閉連線並釋放所有資料庫相關之資源
			 */
			DBMgr.close(pres, conn);
		}

		return results;

	}

	/**
	 * 獲取特定聯絡人
	 *
	 * @return 回傳ArrayList(含特定聯絡人所有資料)
	 */
	@SuppressWarnings("null")
	public ArrayList<Member> getMemberByName(String name) {
		/**
		 * 記錄實際執行之SQL指令
		 */
		String exexcute_sql = "";

		ArrayList<Member> results = new ArrayList<Member>();

		ResultSet resultSet = null;

		try {
			/**
			 * 取得資料庫之連線
			 */
			conn = DBMgr.getConnection();
			/**
			 * QL指令
			 */
			String sql = "SELECT * FROM `people` WHERE `name` = ?";

			/**
			 * 將參數回填至SQL指令當中
			 */
			pres = conn.prepareStatement(sql);
			pres.setString(1, name);
			
			/**
			 * 紀錄真實執行的SQL指令，並印出
			 */
			exexcute_sql = pres.toString();
			System.out.println(exexcute_sql);

			/**
			 * 執行新增之SQL指令並記錄回傳的資料
			 */
			resultSet = pres.executeQuery();

			while (resultSet.next()) {
				results.add(new Member(resultSet.getInt("MemberID"), resultSet.getString("name"),
						resultSet.getString("type"), resultSet.getString("phone"),resultSet.getString("crowd"),resultSet.getString("email")));
			}
			
		} catch (SQLException e) {
			/**
			 * 印出JDBC SQL指令錯誤
			 */
			System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			/**
			 * 若錯誤則印出錯誤訊息
			 */
			e.printStackTrace();
		} finally {
			/**
			 * 關閉連線並釋放所有資料庫相關之資源
			 */
			DBMgr.close(pres, conn);
		}

		return results;

	}
	
	/**
	 * 獲取特定聯絡人
	 *
	 * @return 回傳ArrayList(含特定聯絡人所有資料)
	 */
	@SuppressWarnings("null")
	public ArrayList<Member> getMemberByPhone(String phone) {
		/**
		 * 記錄實際執行之SQL指令
		 */
		String exexcute_sql = "";

		ArrayList<Member> results = null;

		ResultSet resultSet = null;

		try {
			/**
			 * 取得資料庫之連線
			 */
			conn = DBMgr.getConnection();
			/**
			 * QL指令
			 */
			String sql = "SELECT * FROM `people` WHERE `phone`=?";

			/**
			 * 將參數回填至SQL指令當中
			 */
			pres = conn.prepareStatement(sql);
			pres.setString(1, phone);

			/**
			 * 執行新增之SQL指令並記錄影響之行數
			 */
			resultSet = pres.executeQuery();

			while (resultSet.next()) {
				results.add(new Member(resultSet.getInt("MemberID"), resultSet.getString("name"),
						resultSet.getString("type"), resultSet.getString("phone"),resultSet.getString("crowd"),resultSet.getString("email")));
			}

			/**
			 * 紀錄真實執行的SQL指令，並印出
			 */
			exexcute_sql = pres.toString();
			System.out.println(exexcute_sql);
		} catch (SQLException e) {
			/**
			 * 印出JDBC SQL指令錯誤
			 */
			System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			/**
			 * 若錯誤則印出錯誤訊息
			 */
			e.printStackTrace();
		} finally {
			/**
			 * 關閉連線並釋放所有資料庫相關之資源
			 */
			DBMgr.close(pres, conn);
		}

		return results;

	}
	
	/**
	 * 更新一名會員之會員資料
	 *
	 * @param m 一名會員之Member物件
	 * @return the JSONObject 回傳SQL指令執行結果與執行之資料
	 */
	public int update(Member m,String oldName) {
		
		/**
		 * 記錄實際執行之SQL指令
		 */
		String exexcute_sql = "";
		
		/**
		 * 紀錄SQL總行數
		 */
		int row = 0;
		try {
			/**
			 * 取得資料庫之連線
			 */
			conn = DBMgr.getConnection();
			/**
			 * SQL指令
			 */
			String sql = " UPDATE `people` SET `name`=?,`type`=?,`phone`=?, `crowd`=?, `email`=? WHERE `name` = ?";
			/**
			 * 取得所需之參數
			 */
			String name = m.getName();
			String type = m.getType();
			String phone = m.getPhone();
			String crowd = m.getCrowd();
			String email = m.getEmail();

			/**
			 * 將參數回填至SQL指令當中
			 */
			pres = conn.prepareStatement(sql);
			pres.setString(1, name);
			pres.setString(2, type);
			pres.setString(3, phone);
			pres.setString(4, crowd);
			pres.setString(5, email);
			pres.setString(6, oldName);
			/**
			 * 執行更新之SQL指令並記錄影響之行數
			 */
			row = pres.executeUpdate();
			/**
			 * 紀錄真實執行的SQL指令，並印出
			 */
			exexcute_sql = pres.toString();
			System.out.println(exexcute_sql);
		} catch (SQLException e) {
			/**
			 * 印出JDBC SQL指令錯誤
			 */
			System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			/**
			 * 若錯誤則印出錯誤訊息
			 */
			e.printStackTrace();
		} finally {
			/**
			 * 關閉連線並釋放所有資料庫相關之資源
			 */
			DBMgr.close(pres, conn);
		}
		
		return row;
		
	}
	
	/**
	 * 透過會員身分證字號（index_id） 刪除會員
	 *
	 * @param id 會員編號
	 * @return the JSONObject 回傳SQL執行結果
	 */
	public int deleteByName(String name) {
		/**
		 * 記錄實際執行之SQL指令
		 */
		String exexcute_sql = "";
		
		/**
		 * 紀錄SQL總行數
		 */
		int row = 0;

		try {
			/**
			 * 取得資料庫之連線
			 */
			conn = DBMgr.getConnection();
			/**
			 * SQL指令
			 */
			String sql = "DELETE FROM `people` WHERE `name`=?";
			/**
			 * 將參數回填至SQL指令當中
			 */
			pres = conn.prepareStatement(sql);
			pres.setString(1, name);
			/**
			 * 執行刪除之SQL指令並記錄影響之行數
			 */
			row = pres.executeUpdate();
			/**
			 * 紀錄真實執行的SQL指令，並印出
			 */
			exexcute_sql = pres.toString();
			System.out.println(exexcute_sql);
		} catch (SQLException e) {
			/**
			 * 印出JDBC SQL指令錯誤
			 */
			System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			/**
			 * 若錯誤則印出錯誤訊息
			 */
			e.printStackTrace();
		} finally {
			/**
			 * 關閉連線並釋放所有資料庫相關之資源
			 */
			DBMgr.close(pres, conn);
		}
		
		/**
		 * 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳
		 */
		return row;
	}
	
	

}
