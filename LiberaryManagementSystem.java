import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagementSystem {
    // Book class to represent book details
    static class Book {
        private String title;
        private String author;
        private String isbn;
        private boolean isAvailable;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.isAvailable = true;
        }

        public String getTitle() {
            return title;
        }

        public String getIsbn() {
            return isbn;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            this.isAvailable = available;
        }

        @Override
        public String toString() {
            return "Book{" +
                   "title='" + title + '\'' +
                   ", author='" + author + '\'' +
                   ", isbn='" + isbn + '\'' +
                   ", isAvailable=" + isAvailable +
                   '}';
        }
    }

    // User class to represent library users
    static class User {
        private String name;
        private String userId;

        public User(String name, String userId) {
            this.name = name;
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }

        @Override
        public String toString() {
            return "User{" +
                   "name='" + name + '\'' +
                   ", userId='" + userId + '\'' +
                   '}';
        }
    }

    // Library class to manage books and users
    static class Library {
        private List<Book> books;
        private List<User> users;
        private Map<String, String> borrowedBooks; // Maps book ISBN to user ID

        public Library() {
            this.books = new ArrayList<>();
            this.users = new ArrayList<>();
            this.borrowedBooks = new HashMap<>();
        }

        public void addBook(Book book) {
            books.add(book);
            System.out.println("Added book: " + book);
        }

        public void addUser(User user) {
            users.add(user);
            System.out.println("Added user: " + user);
        }

        public boolean borrowBook(String isbn, String userId) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                    for (User user : users) {
                        if (user.getUserId().equals(userId)) {
                            book.setAvailable(false);
                            borrowedBooks.put(isbn, userId);
                            System.out.println("Book borrowed: " + book.getTitle() + " by user " + userId);
                            return true;
                        }
                    }
                    System.out.println("User not found: " + userId);
                    return false;
                }
            }
            System.out.println("Book not available or not found: " + isbn);
            return false;
        }

        public boolean returnBook(String isbn) {
            if (borrowedBooks.containsKey(isbn)) {
                for (Book book : books) {
                    if (book.getIsbn().equals(isbn)) {
                        book.setAvailable(true);
                        borrowedBooks.remove(isbn);
                        System.out.println("Book returned: " + book.getTitle());
                        return true;
                    }
                }
            }
            System.out.println("Book not borrowed or not found: " + isbn);
            return false;
        }

        public void displayBooks() {
            System.out.println("Library Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public static void main(String[] args) {
        // Create a new library
        Library library = new Library();

        // Add some books
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "ISBN001");
        Book book2 = new Book("1984", "George Orwell", "ISBN002");
        library.addBook(book1);
        library.addBook(book2);

        // Add a user
        User user1 = new User("John Doe", "USER001");
        library.addUser(user1);

        // Display all books
        library.displayBooks();

        // Borrow and return books
        library.borrowBook("ISBN001", "USER001");
        library.displayBooks();
        library.returnBook("ISBN001");
        library.displayBooks();
    }
}