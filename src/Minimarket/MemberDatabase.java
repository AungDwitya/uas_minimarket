package Minimarket;

import java.util.ArrayList;
import java.util.List;

public class MemberDatabase {
    private List<Member> members;

    public MemberDatabase() {
        members = new ArrayList<>();
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public Member getMemberByIndex(int index) {
        if (index >= 0 && index < members.size()) {
            return members.get(index);
        }
        throw new IndexOutOfBoundsException("Member tidak ditemukan");
    }

    public class Member {
        private String nama;
        private int point;

        public Member(String nama) {
            this.nama = nama;
            this.point = 0;
        }

        public String getNama() {
            return nama;
        }

        public int getPoint() {
            return point;
        }

        public void tambahPoint(int point) {
            this.point += point;
        }
    }
}
