import java.util.List;
import java.util.Scanner;

import controller.OrderProgramController;
import dto.OrderDTO;
import dto.UserDTO;

public class App {
    // 사용자 입력을 위한 도구
    private static Scanner scanner = new Scanner(System.in, "cp949");
    // Controller 레이어
    private static OrderProgramController controller = new OrderProgramController();
    
    // 로그인 정보를 저장하는 변수
    private static UserDTO userInfo = null;    // 로그인 하지 않았으므로 기본값 = null, 로그인 하면 추가, 로그아웃하면 제거.0
    public static void main(String[] args) throws Exception {
        System.out.println("[고객 주문 관리 프로그램]");
        menu();
    }

    public static void menu() {     // 메인 메뉴(View)
        while(true) {
            System.out.println("1. 회원 관리");
            System.out.println("2. 주문 관리");
            System.out.println("0. 종료");

            System.out.print("메뉴 선택: ");
            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                // 회원 가입, 로그인 정보를 출력하는 메뉴 메소드 호출
                case '1' -> {
                    System.out.println("회원 관리 하위 메뉴");
                    userManageMenu();
                }
                // 주문 처리(회원), 주문 처리(비회원)
                case '2' -> {
                    System.out.println("주문 관리 하위 메뉴");
                    userOrder();
                }
                // 종료
                case '0' -> { 
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return; // 프로세스 종료
                }
                default -> System.out.println("잘못된 메뉴 입력.");
            }
        }
        
    }

    public static void userManageMenu() {
        while (true) {
            System.out.println("1) 회원 가입");
            System.out.println("2) 로그인");
            System.out.println("0) 메인으로 이동");

            System.out.print("메뉴 선택: ");
            char choice = scanner.nextLine().charAt(0);
            switch (choice) {
                // 회원 가입 정보 입력 메소드 호출
                case '1' -> {
                    System.out.println("회원 가입 정보 처리 메소드");
                    joinUser();
                }
                // 로그인 처리 메소드 호출
                case '2' -> {
                    System.out.println("로그인 처리 메소드");
                    logIn();
                }
                // 종료
                case '0' -> { 
                    System.out.println("메인으로 이동합니다.");
                    return; // 프로세스 종료
                }
                default -> System.out.println("잘못된 메뉴 입력.");
            }
        }

    }

    public static void joinUser() {
        while(true) {
            System.out.println("[회원 가입 정보 처리]");
            System.out.print("사용자 ID를 입력하세요: ");
            String userId = scanner.next();
            System.out.print("사용자 PW를 입력하세요: ");
            String userPw = scanner.next();
            scanner.nextLine();
            System.out.print("사용자 이름을 입력하세요: ");
            String userName = scanner.nextLine();
            System.out.print("사용자 이메일을 입력하세요: ");
            String userEmail = scanner.next();
            scanner.nextLine();
            System.out.print("사용자 전화번호를 입력하세요: ");
            String userPhone = scanner.nextLine();
            System.out.print("사용자 나이를 입력하세요: ");
            int userAge = scanner.nextInt();
            scanner.nextLine();
            System.out.print("사용자 주소를 입력하세요: ");
            String userAddr1 = scanner.nextLine();
            System.out.print("사용자 세부 주소를 입력하세요: ");
            String userAddr2 = scanner.nextLine();
            
            System.out.println("[입력한 정보를 확인]");
            System.out.println("사용자 ID: " + userId);
            System.out.println("사용자 PW: " + userPw);
            System.out.println("사용자 이름: " + userName);
            System.out.println("사용자 이메일: " + userEmail);
            System.out.println("사용자 전화번호: " + userPhone);
            System.out.println("사용자 나이: " + userAge);
            System.out.println("사용자 주소: " + userAddr1);
            System.out.println("사용자 상세주소: " + userAddr2);

            System.out.print("입력한 정보를 회원가입 하시겠습니까? (Y/N): ");
            char done = scanner.nextLine().toLowerCase().charAt(0);
            if(done == 'y') {
                // 회원 가입 처리(controller)
                boolean status =
                    controller.join(userId, userPw, userName, userEmail, userPhone, userAge, userAddr1, userAddr2);
                if(status) return;  // 회원 가입 메뉴에서 나감, 실패 시 while문 복귀.
                else System.out.println("회원 가입 실패.");
                
            }
        }

    }

    public static void logIn() {
        while (true) {
            System.out.println("[회원 로그인 처리]");
            System.out.print("사용자 ID를 입력하세요: ");
            String userId = scanner.nextLine();
            System.out.print("사용자 PW를 입력하세요: ");
            String userPw = scanner.nextLine();
            System.out.println("로그인 하시겠습니까? y/n ");
            char done = scanner.nextLine().toLowerCase().charAt(0);
            if(done == 'y') {
                // controller을 통한 logIn() 작업
                // 로그인 성공 시: 정보 확인, 수정, 탈퇴 메뉴를 연결 - 메소드 연결
                // 로그인 실패 시: ID, 또는 PW가 다릅니다.
                // 다시 입력 반복(계속 여부 확인)
                userInfo = controller.logIn(userId, userPw);
                // userInfo에 로그인 정보를 저장하고, userInfo의 내용이 있는지 검증
                // userInfo는 로그인 상태 정보를 저장.
                if(!userInfo.getUserId().isEmpty()) {
                    userManage();
                    return;
                } else {
                    System.out.println("아이디 또는 패스워드가 다릅니다.");
                }
            } else if(done == 'n') {
                return;
            } else {
                System.out.println("메뉴 잘못 입력함.");
            }
        }

    }
    
    public static void userManage() {
        while(true) {
            System.out.println("1) 로그인 정보 확인");
            System.out.println("2) 로그인 정보 수정");
            System.out.println("3) 회원 탈퇴");
            System.out.println("0) 이전 메뉴로 이동");
            System.out.print("메뉴 선택: ");
            char choice = scanner.nextLine().charAt(0);
            switch(choice) {
                case '1' -> {
                    // 회원 정보 출력
                    System.out.println("[회원 정보를 확인]");
                    System.out.println("사용자 ID: " + userInfo.getUserId());
                    System.out.println("사용자 PW: " + userInfo.getUserPw());
                    System.out.println("사용자 이름: " + userInfo.getUserName());
                    System.out.println("사용자 이메일: " + userInfo.getUserEmail());
                    System.out.println("사용자 전화번호: " + userInfo.getPhone1() + "-" + userInfo.getPhone2());
                    System.out.println("사용자 나이: " + userInfo.getAge());
                    System.out.println("사용자 주소: " + userInfo.getAddress1());
                    System.out.println("사용자 상세주소: " + userInfo.getAddress2());
                }
                case '2' -> {
                    // 회원 정보 출력 후 수정
                    System.out.println("[회원 정보를 확인]");
                    System.out.println("사용자 ID(): " + userInfo.getUserId());
                    // 원래 패스워드 수정은 별도의 로직으로 구성해야 함***
                    System.out.printf("사용자 PW(%s): ", userInfo.getUserPw());
                    String userPw = scanner.nextLine();
                    System.out.printf("사용자 이름(%s): ", userInfo.getUserName());
                    String userName = scanner.nextLine();
                    System.out.printf("사용자 이메일(%s): ", userInfo.getUserEmail());
                    String userEmail = scanner.nextLine();
                    System.out.printf("사용자 전화번호(%s-%s): ", userInfo.getPhone1(), userInfo.getPhone2());
                    String userPhone = scanner.nextLine();
                    System.out.printf("사용자 나이(%d): ", userInfo.getAge());
                    int userAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("사용자 주소(%s): ", userInfo.getAddress1());
                    String userAddr1 = scanner.nextLine();
                    System.out.printf("사용자 상세주소(%s): ", userInfo.getAddress2());
                    String userAddr2 = scanner.nextLine();
                    boolean status = controller.userModify(userInfo.getId(), userInfo.getUserId(), userPw, userName,
                                        userEmail, userPhone, userAge, userAddr1, userAddr2);
                    if(status) {
                        System.out.println("회원 정보 수정");
                        // 회원 정보 갱신 처리
                        userInfo = controller.userInfo(userEmail);
                    } else
                        System.out.println("회원 정보 수정 실패.");
                }
                case '3' -> {
                    // 회원 정보 출력 후 삭제
                    System.out.println("[회원 정보 확인]");
                    System.out.println("사용자 ID : " + userInfo.getUserId());
                    System.out.print("회원 탈퇴하시겠습니까?(y/n) ");
                    char done = scanner.nextLine().toLowerCase().charAt(0);
                    if (done == 'y') {
                        System.out.print("사용자 PW(): ");
                        String pw = scanner.next();
                        scanner.nextLine();
                        // 회원 정보 넣어서 보낼 Pw
                        // 회원 정보 삭제를 위한 확인 처리할 패스워드.
                        if(pw.equals(userInfo.getUserPw())) {
                            // 회원 탈퇴
                            // 1. userInfo 정리(null) 후, 2. 이전 메뉴로 이동
                            controller.revokeUser(userInfo);
                            System.out.println("회원 탈퇴 성공");
                            userInfo = null;
                            break;
                        } else {
                            // 회원 탈퇴 실패
                            System.out.println("회원 탈퇴 실패");
                        }
                    } 
                }
                case '0' -> {
                    // 이전 메뉴로 이동
                    System.out.println("이전 메뉴로 이동.");
                    return;
                }
                default -> System.out.println("오류");
            }
        }
    }

    public static void userOrder() {
        while (true) {
            System.out.println("1) 주문처리(회원)");
            System.out.println("2) 주문처리(비회원-x)");
            System.out.println("0) 이전메뉴");
            System.out.print("메뉴 선택: ");
            char choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '1' -> {
                    // 주문 생성, 조회, 수정, 삭제
                    System.out.println("[회원 주문 작업]");
                    orderManage();
                }
                case '2' -> {
                    System.out.println("미구현");
                }
                case '0' -> {
                    System.out.println("이전 메뉴로 이동");
                    return;
                }
                default -> System.out.println("메뉴 선택이 잘못됨. 다시 선택.");
            }

        }
    }

    public static void orderManage() {
        if(userInfo == null) {
            System.out.println("로그인 한 이용자만 사용 가능.");
            return;
        }
        boolean status = false;
        List<OrderDTO> list = null;
        // 주문 생성, 조회, 수정, 삭제 (회원인 경우 작업)
        while(true) {
            System.out.println("1) 주문 생성");
            System.out.println("2) 주문 조회");
            System.out.println("3) 주문 수정/삭제");
            System.out.println("0) 이전 메뉴로");
            System.out.print("메뉴 선택: ");
            char choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '1' -> {
                    System.out.println("[주문 생성]");
                    System.out.print("메뉴 입력(list): ");
                    String orderList = scanner.nextLine();
                    System.out.print("가격: ");
                    int price = scanner.nextInt();
                    scanner.nextLine();
                    // controller에서 메뉴 처리...
                    status = controller.createOrder(userInfo, orderList, price);
                    if(status) System.out.println("주문 생성 성공");
                    else System.out.println("주문 생성 실패");
                }
                case '2' -> {
                    // 주문 정보 읽어오기
                    // 반복문으로 처리. stream 또는 for문
                    list = controller.getOrders(userInfo);
                    System.out.println("주문 내역 읽어오기");
                    list.stream().forEach(System.out::println);
                }
                case '3' -> {
                    // 주문 리스트 확인 인덱스 번호 입력
                    System.out.println("주문 삭제하기");
                    list = controller.getOrders(userInfo);
                    for(int i=0; i<list.size(); i++) {
                        System.out.println((i+1) + ":" + list.get(i));
                    }
                    // 주문 삭제 처리
                    System.out.print("삭제할 주문(index) 번호 입력: ");
                    int idx = scanner.nextInt() -1;
                    scanner.nextLine();
                    controller.removeOrder(userInfo, list.get(idx));
                    if(status) {
                        System.out.println("삭제 성공");
                    } else {
                        System.out.println("삭제 실패.");
                    }
                }
                case '0' -> {
                    System.out.println("이전 메뉴로 이동합니다.");
                    return;
                }
                default -> System.out.println("메뉴 선택이 잘못됨. 다시 선택.");
            }
        }
    }

 }
