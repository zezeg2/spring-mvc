package services;

import daos.MemberDAO;
import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberDTO;
import domains.member.dtos.MemberInfoDTO;
import domains.member.dtos.UpdateMemberDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Service
public class MemberService {
    private final MemberDAO dao;
//    public MemberService(@Qualifier("mybatisMemberDAOImpl")MemberDAO dao) {
//        this.dao = dao;
//    }
    public MemberService(@Qualifier("springMybatisMemberDAOImpl")MemberDAO dao) {
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

    public List<MemberInfoDTO> getMemberListByPage(int page) throws SQLException {
        return dao.getAllMemberInfo(page);
    }

    public MemberInfoDTO getMemberInfo(AuthMemberDTO auth) throws SQLException {
        return dao.getMemberInfo(auth);
    }

    public void updateMember(UpdateMemberDTO updateMemberDTO) throws SQLException {
        dao.updateMember(updateMemberDTO);
    }

    public void deleteMember(AuthMemberDTO auth) throws SQLException {
        dao.deleteMember(auth.id());
    }

    public MemberDTO getMember(AuthMemberDTO auth) throws SQLException {
        return dao.getMember(auth);
    }

    public int getTotalPage() throws SQLException {
        return dao.countPage();
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

    public List<MemberInfoDTO> getAllMember(){
        return dao.getMemberList().stream().map(m -> m.toInfoDto()).toList();
    }
}
