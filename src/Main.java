import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PostRepository postRepository = new JdbcPostRepository();
        PostService postService = new PostService(postRepository);
        while(true){
            printMenu();
            int menu = readInt(sc, "메뉴 선택: ");
            if (menu == 0) {
                break;
            }
            try {
                switch (menu) {
                    case 1 -> createPost(sc, postService);
                    case 2 -> findAllPosts(postService);
                    case 3 -> findPost(sc, postService);
                    case 4 -> updatePost(sc, postService);
                    case 5 -> deletePost(sc, postService);
                    default -> System.out.println("잘못된 메뉴입니다.");
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } sc.close();
    }

    public static String readRequiredString(Scanner sc, String prompt, String errorMessage) {
        System.out.print(prompt);
        String value = sc.nextLine().trim();
        while (value.isEmpty()) {
            System.out.println(errorMessage);
            System.out.print(prompt);
            value = sc.nextLine().trim();
        }
        return value;
    }

    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("========== 게시판 ==========\n" +
            "1. 게시글 등록\n" +
            "2. 게시글 전체 조회\n" +
            "3. 게시글 상세 조회\n" +
            "4. 게시글 수정\n" +
            "5. 게시글 삭제\n" +
            "0. 프로그램 종료\n" +
            "===========================");
    }

    public static void createPost(Scanner sc, PostService postService) {
        System.out.println("게시글 등록을 선택했습니다.");
        String title = readRequiredString(sc, "제목: ", "제목은 필수 입력 항목입니다.");
        String content = readRequiredString(sc, "내용: ", "내용은 필수 입력 항목입니다.");
        String author = readRequiredString(sc, "작성자: ", "작성자는 필수 입력 항목입니다.");
        try {
            int saveId = postService.createPost(title, content, author);
            System.out.println(saveId + "번 게시글이 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findAllPosts(PostService postService) {
        System.out.println("게시글 전체 조회를 선택했습니다.");
        List<Post> posts = postService.findAllPosts();
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

    public static void findPost(Scanner sc, PostService postService) {
        System.out.println("게시글 상세 조회를 선택했습니다.");
        int findId = readInt(sc, "조회할 게시글 번호: ");
        Post foundPost = postService.findPost(findId);
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

    public static void updatePost(Scanner sc, PostService postService) {
        System.out.println("게시글 수정을 선택했습니다.");
        int updateId = readInt(sc, "수정할 게시글 번호: ");
        Post updatePost = postService.findPost(updateId);
        if (updatePost == null) {
            System.out.println("존재하지 않는 게시글 번호입니다.");
        } else {
            System.out.println();
            System.out.println("기존 제목: " + updatePost.getTitle());
            System.out.println("기존 내용: " + updatePost.getContent());
            System.out.println("기존 작성자: " + updatePost.getAuthor());
            System.out.println();

            String newTitle = readRequiredString(sc, "새로운 제목: ", "제목은 필수 입력 항목입니다.");
            String newContent = readRequiredString(sc, "새로운 내용: ", "내용은 필수 입력 항목입니다.");
            String newAuthor = readRequiredString(sc, "새로운 작성자: ", "작성자는 필수 입력 항목입니다.");

            try {
                postService.updatePost(updateId, newTitle, newContent, newAuthor);
                System.out.println(updateId + "번 게시글이 수정되었습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deletePost(Scanner sc, PostService postService) {
        System.out.println("게시글 삭제를 선택했습니다.");
        int deleteId = readInt(sc, "삭제할 게시글 번호: ");
        Post removePost = postService.findPost(deleteId);
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
                try {
                    postService.deletePost(deleteId);
                    System.out.println(deleteId + "번 게시글이 삭제되었습니다.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("삭제가 취소되었습니다.");
            }
        }
    }
}