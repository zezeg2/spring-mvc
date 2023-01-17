package domains.member.dtos;

import java.util.Objects;

public record AuthMemberDTO(String id, String pw) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthMemberDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(pw, that.pw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pw);
    }
}

