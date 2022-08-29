package bulletinboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindCommentDAO {

    public List<Board> findcomment() {

        // id,name,comment���i�[���郊�X�g
        List<Board> list = new ArrayList<>();

        final String jdbcId = "root";
        final String jdbcPass = "20011230";
        final String jdbcUrl = "jdbc:mysql://localhost:3306/zemiiiia?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=JST&autoReconnect=true&useSSL=false";

        Connection con = null;

        try {

        	Class.forName("com.mysql.cj.jdbc.Driver");
        	
            con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

            System.out.println("Connected....");

            try {
                Statement st = con.createStatement();
                String sql = "select * from zemi";

                try {
                    // sql�𑗐M
                    ResultSet rs = st.executeQuery(sql);

                    while (rs.next()) {
                        // DB������o����id,name,comment��JavaBeans��set
                        Board bo = new Board();
                        bo.setId(rs.getInt("id"));
                        bo.setName(rs.getString("name"));
                        bo.setComment(rs.getString("comment"));
                        bo.setTime(rs.getTimestamp("time"));

                        // ���X�g��1���i�[�B�����ɗv�f���ǉ�����Ă����B
                        list.add(bo);
                    }

                    rs.close();
                    st.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // �f�[�^�x�[�X�ڑ��̐ؒf
                if (con != null) {
                    try {
                        con.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection Failed.");
            return null;
        }
        return list;

    }

}