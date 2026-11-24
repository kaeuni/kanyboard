import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("========== 게시판 ==========\n" +
                    "1. 게시글 등록\n" +
                    "2. 게시글 전체 조회\n" +
                    "3. 게시글 상세 조회\n" +
                    "4. 게시글 수정\n" +
                    "5. 게시글 삭제\n" +
                    "0. 프로그램 종료\n" +
                    "===========================");
            System.out.print("메뉴 선택: ");
            int menu = Integer.parseInt(sc.nextLine().trim());
            if (menu == 0) {
                break;
            }
            switch (menu) {
                case 1 -> System.out.println("게시글 등록을 선택했습니다.");
                case 2 -> System.out.println("게시글 전체 조회를 선택했습니다.");
                case 3 -> System.out.println("게시글 상세 조회를 선택했습니다.");
                case 4 -> System.out.println("게시글 수정을 선택했습니다.");
                case 5 -> System.out.println("게시글 삭제를 선택했습니다.");
                default -> System.out.println("잘못된 메뉴입니다.");
            }
        }
    }
}