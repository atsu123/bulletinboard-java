package bulletinboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCommentDAO {

    // DB��id,name,comment�������郁�\�b�h
    public AddCommentDAO(Board bo) {

        if(bo.getName().isEmpty()) {
            bo.setName( "������");
        }
        if(bo.getComment().isEmpty()) {
            bo.setComment( "�R�����g����");
        }


        final String jdbcId = "root";
        final String jdbcPass = "20011230";
        final String jdbcUrl = "jdbc:mysql://localhost:3306/zemiiiia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=JST&useSSL=false";

        Connection con = null;

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

            System.out.println("Connected....");

            try {

                PreparedStatement ps = con.prepareStatement("INSERT INTO zemi (name, comment) VALUES (?, ?)");

                ps.setString(1, bo.getName());
                ps.setString(2, bo.getComment());

                // �ЂȌ`�𑗐M
                int r = ps.executeUpdate();

                if (r != 0) {
                    System.out.println(r + "���̏������݂�ǉ����܂����B");
                } else {
                    System.out.println("�������݂ł��܂���ł����B");
                }

                ps.close();

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

        }

    }

}