package services;

import daos.MemberDAO;
import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberDTO;
import domains.member.dtos.MemberInfoDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Scanner;

@Service
public class MemberService {
    private final MemberDAO dao;
    public MemberService(@Qualifier("mybatisMemberDAOImpl")MemberDAO dao) {
        this.dao = dao;
    }

    public MemberInfoDTO login(AuthMemberDTO auth) throws SQLException {
        MemberInfoDTO info = dao.getMemberInfo(auth);
        return info;
    }

    public MemberInfoDTO register(MemberDTO info) throws SQLException {
        if (dao.isExistId(info.id()) || dao.isExistEmail(info.email())) return null;
        dao.createMember(info);
        return dao.getMemberInfo(new AuthMemberDTO(info.id(), info.pw()));
    }

    public void deleteMemberWithBoard() throws SQLException {
        try(Scanner sc = new Scanner(System.in)){
            System.out.print("아이디 입력 : ");
            String id = sc.next();
            System.out.print("패스워드 입력 : ");
            String pw = sc.next();
            AuthMemberDTO auth = new AuthMemberDTO(id, pw);
            dao.getMemberInfo(auth);
            if (dao.isExistBoard(id)){
                System.out.println("글 다지우고 나가리? y/n");
                String key = sc.next();
                if (key.equals("y")) {
                    dao.deleteMember(id);
                } else {
                    System.out.println("삭제안함 ㅎㅎ");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
