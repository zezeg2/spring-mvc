package domains.member.dtos;

import java.util.Objects;

public final class MemberInfoDTO {
    private final String id;
    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final String indate;

    public MemberInfoDTO(String id, String name, String email, String phone, String address, String indate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.indate = indate;
    }

    @Override
    public String toString() {
        return "InfoMemberDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", indate='" + indate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MemberInfoDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.phone, that.phone) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.indate, that.indate);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String phone() {
        return phone;
    }

    public String address() {
        return address;
    }

    public String indate() {
        return indate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, address, indate);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getIndate() {
        return indate;
    }
}
