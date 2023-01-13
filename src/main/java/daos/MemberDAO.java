package daos;

import domains.member.dtos.*;
import exceptions.IncorrectPasswordException;
import exceptions.MemberNotFoundException;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemberDAO {
    private final int COUNT_PER_PAGE = 3;    private DataSource source = getSource();

    public DataSource getSource() {
        try {
            if (source == null) {
                Context initContext = new InitialContext(); // context.xml 준비
                Context envContext = (Context) initContext.lookup("java:/comp/env"); // context look up(JNDI 방식)
                source = (DataSource) envContext.lookup("jdbc/mydb"); // envContext 에서 jdbc Connection Pool lookup
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return source;
    }

    public boolean isExistId(String id) throws SQLException {
        String sql = "SELECT EXISTS(SELECT 1 FROM member WHERE id = ?)";
        try (Connection con = source.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {
            pt.setString(1, id);
            ResultSet rs = pt.executeQuery();
            rs.next();
            return rs.getInt(1) == 1;
        }

    }

    public boolean isExistEmail(String email) throws SQLException {
        String sql = "SELECT EXISTS(SELECT 1 FROM member WHERE email = ?)";
        try (Connection con = source.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {
            pt.setString(1, email);
            ResultSet rs = pt.executeQuery();
            rs.next();
            return rs.getInt(1) == 1;

        }
    }

    public void createMember(MemberDTO dto) throws SQLException {
        String sql = "INSERT INTO member (id, pw, name, email, phone, address, indate) VALUES (?,?,?,?,?,?,now())";
        try (Connection con = source.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {
            pt.setString(1, dto.id());
            pt.setString(2, dto.pw());
            pt.setString(3, dto.name());
            pt.setString(4, dto.email());
            pt.setString(5, dto.phone());
            pt.setString(6, dto.address());
            pt.execute();
        }
    }

    public int countPage() throws SQLException {
        String sql = "SELECT COUNT(*) FROM member";
        try (Connection con = source.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            int totalCnt = rs.getInt(1);
            return (totalCnt - 1) / COUNT_PER_PAGE + 1;
        }

    }

    public List<MemberInfoDTO> getAllMemberInfo(int page) throws SQLException {
        String sql = "SELECT * FROM member ORDER BY indate LIMIT ? OFFSET ?";
        try (Connection con = source.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {
            List<MemberInfoDTO> memberList = new ArrayList<>();
            int startIndex = COUNT_PER_PAGE * (page - 1);
            pt.setInt(1, COUNT_PER_PAGE);
            pt.setInt(2, startIndex);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String indate = rs.getString("indate");
                memberList.add(new MemberInfoDTO(id, name, email, phone, address, indate));
            }
            return memberList;
        }
    }

    public MemberInfoDTO getMemberInfo(AuthMemberDTO dto) throws SQLException {
        MemberDTO member = getMember(dto);
        return new MemberInfoDTO(member.id(), member.name(), member.email(), member.phone(), member.address(), member.indate());
    }

    public void updateMember(UpdateMemberDTO dto) throws SQLException {
        String sql = "UPDATE member SET pw = ?, email = ?, phone = ?, address = ? WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {

            pt.setString(1, dto.pw());
            pt.setString(2, dto.email());
            pt.setString(3, dto.phone());
            pt.setString(4, dto.address());
            pt.setString(5, dto.id());
            pt.executeQuery();
        }
    }

    public void deleteMember(String id) throws SQLException {
        String sql = "DELETE FROM member WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {
            pt.setString(1, id);
            pt.executeQuery();
        }
    }

    public MemberDTO getMember(AuthMemberDTO dto) throws SQLException {
        String sql = "SELECT id, pw, name, phone, email, address, indate FROM member WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement pt = con.prepareStatement(sql)) {

            pt.setString(1, dto.id());
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                if (rs.getString("pw").equals(dto.pw())) {
                    String id = rs.getString("id");
                    String pw = rs.getString("pw");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String indate = rs.getString("indate");
                    return new MemberDTO(id, pw, name, email, phone, address, indate);
                } else throw new IncorrectPasswordException();
            } else throw new MemberNotFoundException();
        }
    }


}
