package domains.member.dtos;

public record UpdateMemberDTO(String id, String pw, String email, String phone, String address) {
}
