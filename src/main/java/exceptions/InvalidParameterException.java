package exceptions;

/**
 * Created by jonghyeon on 2022/12/30,
 * Package : template.domain.member.exceptions
 */
public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException() {
        super("id, password 파라미터값이 존재하지 않습니다");
    }
}
