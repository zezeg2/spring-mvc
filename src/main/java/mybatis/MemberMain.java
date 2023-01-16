package mybatis;

import configurations.ApplicationConfig;
import daos.MemberDAO;
import daos.MybatisMemberDAOImpl;
import domains.member.dtos.AuthMemberDTO;
import domains.member.dtos.MemberInfoDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.MemberService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MemberMain {
    public static void main(String[] args) throws IOException, SQLException {
        /**
         *  NoInitialContextException (JDBCMemberDAOImpl) 데이터소스를 로드하는 과정에서 Exception 발생
         *  JNDI는 WAS에 설정 정보가 있고, WAS를 켰을 때 그에 대한 정보를 읽는다고 하는데
         *  AnnotationConfigApplicationContext 는 WAS 로드 없이 테스트하기 위해 사용하기 때문에
         *  JNDI가 초기화되지 않아서 발생하는 것 같다. 실제로 WAS를 켜서 실제 서비스에서 DB 연동이 되는지 확인해보니
         *  제대로 작동하는 걸 확인할 수 있었다.
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        MemberDAO dao = context.getBean("mybatisMemberDAOImpl", MybatisMemberDAOImpl.class);
        MemberService service = context.getBean("memberService", MemberService.class);
        MemberInfoDTO test = dao.getMemberInfo(new AuthMemberDTO("admin", "0000"));
        System.out.println(test);
        List<MemberInfoDTO> allMemberInfo = dao.getAllMemberInfo(2);
        System.out.println(allMemberInfo);
        ((MybatisMemberDAOImpl) dao).getMemberBoardMap("user1").forEach(System.out::println);
        service.deleteMemberWithBoard();
    }
}
