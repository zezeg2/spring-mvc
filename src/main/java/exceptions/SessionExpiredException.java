package exceptions;

public class SessionExpiredException extends RuntimeException {
    public SessionExpiredException() {
        super("로그인 정보가 없습니다.");
    }
}
