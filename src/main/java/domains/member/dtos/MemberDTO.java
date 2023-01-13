package domains.member.dtos;

public record MemberDTO(String id, String pw, String name, String email, String phone, String address, String indate) {
    public MemberInfoDTO toInfoDto(){
        return new MemberInfoDTO(id,name,email,phone,address,indate);
    }
}
