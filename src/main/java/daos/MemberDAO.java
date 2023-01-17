package daos;

import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberDTO;
import domains.member.dtos.MemberInfoDTO;
import domains.member.dtos.UpdateMemberDTO;
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

 public interface MemberDAO {
    int COUNT_PER_PAGE = 4;
     boolean isExistId(String id) throws SQLException;

     boolean isExistEmail(String email) throws SQLException;

     void createMember(MemberDTO dto) throws SQLException ;

     int countPage() throws SQLException;

     List<MemberInfoDTO> getAllMemberInfo(int page) throws SQLException;

     MemberInfoDTO getMemberInfo(AuthMemberDTO dto) throws SQLException;
     void updateMember(UpdateMemberDTO dto) throws SQLException;

     void deleteMember(String id) throws SQLException ;

     MemberDTO getMember(AuthMemberDTO dto) throws SQLException ;

     boolean isExistBoard(String id);
 }
