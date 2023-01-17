package daos;

import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberDTO;
import domains.member.dtos.MemberInfoDTO;
import domains.member.dtos.UpdateMemberDTO;
import exceptions.IncorrectPasswordException;
import exceptions.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SpringMybatisMemberDAOImpl implements MemberDAO {

    private final SqlSession sqlSession;
    
    @Override
    public boolean isExistId(String id) throws SQLException {
        return sqlSession.selectOne("isExistId", id);
    }

    @Override
    public boolean isExistEmail(String email) throws SQLException {
        return sqlSession.selectOne("isExistEmail", email);
    }

    @Override
    public void createMember(MemberDTO dto) throws SQLException {
        sqlSession.insert("createMember", dto);
    }

    @Override
    public int countPage() throws SQLException {
        return (Integer) sqlSession.selectOne("memberCount") / COUNT_PER_PAGE + 1;
    }

    @Override
    public List<MemberInfoDTO> getAllMemberInfo(int page) throws SQLException {
        return sqlSession
                .selectList("getMemberByPage", Map.of("limit", COUNT_PER_PAGE, "offset", COUNT_PER_PAGE * (page - 1)))
                .stream().map(o -> ((MemberDTO) o).toInfoDto()).toList();
    }

    @Override
    public MemberInfoDTO getMemberInfo(AuthMemberDTO dto) throws SQLException {
        return getMember(dto).toInfoDto();
    }

    @Override
    public void updateMember(UpdateMemberDTO dto) throws SQLException {
        sqlSession.update("updateMember", dto);
    }

    @Override
    public void deleteMember(String id) throws SQLException {
        sqlSession.delete("deleteMember", id);
    }

    @Override
    public MemberDTO getMember(AuthMemberDTO dto) throws SQLException {
        SqlSession session = sqlSession;
        MemberDTO match = session.selectOne("getMember", dto.id());
        if (match == null) throw new MemberNotFoundException();
        if (!match.pw().equals(dto.pw())) throw new IncorrectPasswordException();
        return match;
    }


    public List<HashMap<String, String>> getMemberBoardMap(String id){
        return sqlSession.selectList("memberBoard", id);
    }
    @Override
    public boolean isExistBoard(String id) {
        return sqlSession.selectOne("isExistBoard",id);
    }
}
