package services;

import daos.MemberDAO;
import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberDTO;
import domains.member.dtos.MemberInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MemberService {

    @Autowired
    MemberDAO memberDAO;


    public MemberInfoDTO login(AuthMemberDTO auth) throws SQLException {
        MemberInfoDTO info = memberDAO.getMemberInfo(auth);
        return info;
    }

    public MemberInfoDTO register(MemberDTO info) throws SQLException {
        if (memberDAO.isExistId(info.id()) || memberDAO.isExistEmail(info.email())) return null;
        memberDAO.createMember(info);
        return memberDAO.getMemberInfo(new AuthMemberDTO(info.id(), info.pw()));
    }
}
