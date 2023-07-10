package Minimarket;

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