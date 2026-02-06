import domain.PersonLom;
public class App {
    public static void main(String[] args) throws Exception {
        // lombok 테스트
        PersonLom lombok1 = new PersonLom();
        PersonLom lombok2 = new PersonLom(0, "1", "1", "1", "1", "221",
                         "221-2222", (byte)0, "1", "1", null, null);
        // @Builder 사용 시
        PersonLom lombokTest = new PersonLom()
                                .builder()
                                .address1("주소1")
                                .build();

        System.out.println("Hello, World!");
    }
}
