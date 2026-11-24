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
            int menu;
            try {
                menu = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
                continue;
            }
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
                case 3 -> {
                    System.out.println("게시글 상세 조회를 선택했습니다.");
                    System.out.print("조회할 게시글 번호: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요.");
                        continue;
                    }
                    Post foundPost = findByPostId(posts, id);
                    System.out.println("========= 게시글 상세 ========");
                    if (foundPost == null) {
                        System.out.println("존재하지 않는 게시글 번호입니다.");
                    } else {
                        System.out.println(
                            "번호: " + foundPost.getId() + "\n"
                            + "제목: " + foundPost.getTitle() + "\n"
                            + "내용: " + foundPost.getContent() + "\n"
                            + "작성자: " + foundPost.getAuthor());
                        System.out.println("===========================");
                    }
                }
                case 4 -> {
                    System.out.println("게시글 수정을 선택했습니다.");
                    System.out.print("수정할 게시글 번호: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요.");
                        continue;
                    }
                    Post updatePost = findByPostId(posts, id);
                    if (updatePost == null) {
                        System.out.println("존재하지 않는 게시글 번호입니다.");
                    } else {
                        System.out.println();
                        System.out.println("기존 제목: " + updatePost.getTitle());
                        System.out.println("기존 내용: " + updatePost.getContent());
                        System.out.println("기존 작성자: " + updatePost.getAuthor());

                        System.out.print("새로운 제목: ");
                        String title = sc.nextLine().trim();
                        while (title.isEmpty()) {
                            System.out.println("제목은 필수 입력 항목입니다.");
                            System.out.print("새로운 제목: ");
                            title = sc.nextLine().trim();
                        }
                        System.out.print("새로운 내용: ");
                        String content = sc.nextLine().trim();
                        while (content.isEmpty()) {
                            System.out.println("내용은 필수 입력 항목입니다.");
                            System.out.print("새로운 내용: ");
                            content = sc.nextLine().trim();
                        }
                        System.out.print("새로운 작성자: ");
                        String author = sc.nextLine().trim();
                        while (author.isEmpty()) {
                            System.out.println("작성자는 필수 입력 항목입니다.");
                            System.out.print("새로운 작성자: ");
                            author = sc.nextLine().trim();
                        }

                        updatePost.setTitle(title);
                        updatePost.setContent(content);
                        updatePost.setAuthor(author);
                        System.out.println("게시글이 수정되었습니다.");
                    }
                }
                case 5 -> {
                    System.out.println("게시글 삭제를 선택했습니다.");
                    System.out.print("삭제할 게시글 번호: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요.");
                        continue;
                    }
                    Post removePost = findByPostId(posts, id);
                    if (removePost == null) {
                        System.out.println("존재하지 않는 게시글 번호입니다.");
                    } else {
                        System.out.println("제목: " + removePost.getTitle());
                        System.out.println("내용: " + removePost.getContent());
                        System.out.println("작성자: " + removePost.getAuthor());
                        System.out.print("정말 삭제 하시겠습니까? (Y/N): ");
                        String choice = sc.nextLine().trim();
                        while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
                            System.out.println("잘못된 입력입니다. Y 또는 N을 입력해주세요.");
                            choice = sc.nextLine().trim();
                        }
                        if (choice.equalsIgnoreCase("Y")) {
                            posts.remove(removePost);
                            System.out.println("게시글이 삭제되었습니다.");
                        } else {
                            System.out.println("삭제가 취소되었습니다.");
                        }
                    }
                }
                default -> System.out.println("잘못된 메뉴입니다.");
            }
        }
    }

    public static Post findByPostId(ArrayList<Post> posts, int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }
}