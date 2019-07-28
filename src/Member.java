public class Member {
    private String memberName;
    private String memberId;
    private String memberMobile;
    private String memberaddress;

    public Member() {
    }

    public Member(String memberName,String memberId,String memberMobile,String memberaddress) {
        this.memberName=memberName;
        this.memberId=memberId;
        this.memberMobile=memberMobile;
        this.memberaddress=memberaddress;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberAddress() {
        return memberaddress;
    }

    public void setMemberAddress(String memberaddress) {
        this.memberaddress = memberaddress;
    }
}

