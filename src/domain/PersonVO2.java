package domain;

import java.sql.Timestamp;

public class PersonVO2 {
    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String phone1;
    private String phone2;
    private byte age;
    private String address1;
    private String address2;
    private Timestamp regDate;
    private Timestamp modDate;
    
    // 기본 생성자.
    public PersonVO2() {
    }

    // Builder를 사용하는 생성자 구성.


    // 필드 생성자...
    public PersonVO2(Builder builder){
        this.id = builder.id;
        this.userId = builder.userId;
        this.userPw = builder.userPw;
        this.userName = builder.userName;
        this.userEmail = builder.userEmail;
        this.phone1 = builder.phone1;
        this.phone2 = builder.phone2;
        this.age = builder.age;
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.regDate = builder.regDate;
        this.modDate = builder.modDate;
    }

    // Builder는 static 내부 클래스
    // Builder의 역할: 값을 받아서 PersonVO2객체 반환 작업을 위한 객체
    public static class Builder {
        private long id;
        private String userId;
        private String userPw;
        private String userName;
        private String userEmail;
        private String phone1;
        private String phone2;
        private byte age;
        private String address1;
        private String address2;
        private Timestamp regDate;
        private Timestamp modDate;

        // 기본 생성자
        public Builder() {}

        public Builder id(long id) {
            this.id = id;   // 생성된 객체 내 id값을 매개변수 id 대입
            return this;    // 객체 넘김
        }

        public Builder userId(String userId) {
            this.userId = userId;   
            return this;            
        }

        public Builder userPw(String userPw) {
            this.userPw = userPw;   
            return this;            
        }

        public Builder userName(String userName) {
            this.userName = userName;   
            return this;            
        }

        public Builder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder phone1(String phone1) {
            this.phone1 = phone1;
            return this;
        }

        public Builder phone2(String phone2) {
            this.phone2 = phone2;
            return this;
        }

        public Builder age(int age) {
            this.age = (byte) age;
            return this;
        }

        public Builder address1(String address1) {
            this.address1 = address1;
            return this;
        }

        public Builder address2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Builder regDate(Timestamp regDate) {
            this.regDate = regDate;
            return this;
        }

        public Builder modDate(Timestamp modDate) {
            this.modDate = modDate;
            return this;
        }

        public PersonVO2 build() {
            return new PersonVO2(this);
        }
    }

    // builder() - 반환 타입 Builder
    public Builder builder() {
        return new Builder();
    }

    // person 테이블 조건에 따른 Getter, Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Timestamp getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }

    @Override
    public String toString() {
        return "PersonVO [id=" + id + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
                + ", userEmail=" + userEmail + ", phone1=" + phone1 + ", phone2=" + phone2 + ", age=" + age
                + ", address1=" + address1 + ", address2=" + address2 + ", regDate=" + regDate + ", modDate=" + modDate
                + "]";
    }
}

