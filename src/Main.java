import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Post> posts = new ArrayList<>();
        int nextId = 1;
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
                case 1 -> {
                    System.out.println("게시글 등록을 선택했습니다.");
                    System.out.print("제목: ");
                    String title = sc.nextLine().trim();
                    while (title.isEmpty()) {
                        System.out.println("제목은 필수 입력 항목입니다.");
                        System.out.print("제목: ");
                        title = sc.nextLine().trim();
                    }
                    System.out.print("내용: ");
                    String content = sc.nextLine().trim();
                    while (content.isEmpty()) {
                        System.out.println("내용은 필수 입력 항목입니다.");
                        System.out.print("내용: ");
                        content = sc.nextLine().trim();
                    }
                    System.out.print("작성자: ");
                    String author = sc.nextLine().trim();
                    while (author.isEmpty()) {
                        System.out.println("작성자는 필수 입력 항목입니다.");
                        System.out.print("작성자: ");
                        author = sc.nextLine().trim();
                    }
                    Post post = new Post(nextId++, title, content, author);
                    posts.add(post);
                    System.out.println("게시글이 등록되었습니다.");
                }
                case 2 -> {
                    System.out.println("게시글 전체 조회를 선택했습니다.");
                    if (posts.isEmpty()) {
                        System.out.println("등록된 게시글이 없습니다.");
                    } else {
                        System.out.println("========= 게시글 목록 ========");
                        for (Post post : posts) {
                            System.out.println(
                                    "번호: " + post.getId() + "|"
                                    + "제목: " + post.getTitle() + "|"
                                    + "작성자: " + post.getAuthor());
                        }
                        System.out.println("===========================\n"
                                + "총 게시글: " + posts.size() + "개");
                    }
                }
                case 3 -> System.out.println("게시글 상세 조회를 선택했습니다.");
                case 4 -> System.out.println("게시글 수정을 선택했습니다.");
                case 5 -> System.out.println("게시글 삭제를 선택했습니다.");
                default -> System.out.println("잘못된 메뉴입니다.");
            }
        }
    }
}