//package id.ac.ui.cs.advprog.userfunctionality.repository;
//
//import id.ac.ui.cs.advprog.userfunctionality.builder.CartCheckoutBuilder;
//import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
//import id.ac.ui.cs.advprog.userfunctionality.dto.CartItemsDTO;
//import id.ac.ui.cs.advprog.userfunctionality.model.Book;
//import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
//import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
//import id.ac.ui.cs.advprog.userfunctionality.model.UserEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CartCheckoutRepositoryTest {
//    private CartCheckoutRepository repository;
//
//    @BeforeEach
//    void setUp() {
//        repository = new CartCheckoutRepository();
//    }
//
//    private CartCheckoutDTO createCartCheckoutDTO() {
//        CartCheckoutDTO dto = new CartCheckoutDTO();
//        dto.setId(1L);
//        dto.setUserId(UUID.randomUUID().toString());
//
//        List<CartItemsDTO> items = new ArrayList<>();
//        CartItemsDTO item1 = new CartItemsDTO();
//        item1.setCartId(1L);
//        item1.setBookIsbn("123456789");
//        item1.setBookTitle("Title 1");
//        item1.setPrice(50.0);
//        item1.setQuantity(2);
//        items.add(item1);
//
//        CartItemsDTO item2 = new CartItemsDTO();
//        item2.setCartId(2L);
//        item2.setBookIsbn("987654321");
//        item2.setBookTitle("Title 2");
//        item2.setPrice(1000.0);
//        item2.setQuantity(1);
//        items.add(item2);
//
//        dto.setItems(items);
//        dto.setTotalPrice(50.0 * 2 + 1000.0);
//        dto.setStatus("Pending");
//
//        return dto;
//    }
//
//    private UserEntity createUserEntity(String userId) {
//        UserEntity user = new UserEntity();
//        user.setId(UUID.fromString(userId));
//        return user;
//    }
//
//    private List<CartItems> createCartItems(List<CartItemsDTO> itemsDTO, UserEntity user) {
//        List<CartItems> items = new ArrayList<>();
//        for (CartItemsDTO dto : itemsDTO) {
//            CartItems item = new CartItems();
//            item.setId(dto.getCartId());
//            item.setQuantity(dto.getQuantity());
//
//            Book book = new Book();
//            book.setIsbn(dto.getBookIsbn());
//            book.setJudulBuku(dto.getBookTitle());
//            book.setHarga(dto.getPrice());
//            item.setBook(book);
//
//            item.setUser(user);
//            items.add(item);
//        }
//        return items;
//    }
//
//    @Test
//    void testCreateAndFindById() {
//        CartCheckoutDTO cartCheckoutDTO = createCartCheckoutDTO();
//        UserEntity user = createUserEntity(cartCheckoutDTO.getUserId());
//        List<CartItems> items = createCartItems(cartCheckoutDTO.getItems(), user);
//
//        CartCheckout created = repository.create(cartCheckoutDTO, user, items);
//        Optional<CartCheckoutDTO> found = repository.findById(1L);
//
//        assertTrue(found.isPresent(), "Cart should be found");
//        assertEquals(cartCheckoutDTO.getId(), found.get().getId(), "The found cart should have the same ID as the created one");
//        assertEquals(cartCheckoutDTO.getUserId(), found.get().getUserId(), "The found cart should have the same user ID as the created one");
//        assertEquals(cartCheckoutDTO.getTotalPrice(), found.get().getTotalPrice(), 0.01, "The found cart should have the same total price as the created one");
//    }
//
//    @Test
//    void testFindAllWhenEmpty() {
//        assertTrue(repository.findAll().isEmpty(), "FindAll should return an empty list if no carts are stored");
//    }
//
//    @Test
//    void testDeleteNonexistentCartCheckout() {
//        assertFalse(repository.delete(999L), "Deleting a non-existent cart should return false");
//    }
//
//    @Test
//    void testUpdateNonexistentCartCheckout() {
//        CartCheckoutDTO updatedCartCheckoutDTO = createCartCheckoutDTO();
//        updatedCartCheckoutDTO.setId(999L);
//        UserEntity user = createUserEntity(updatedCartCheckoutDTO.getUserId());
//        List<CartItems> items = createCartItems(updatedCartCheckoutDTO.getItems(), user);
//
//        assertThrows(IllegalArgumentException.class, () -> repository.update(999L, updatedCartCheckoutDTO, user, items),
//                "Should throw an exception when trying to update a non-existent cart");
//    }
//
//    @Test
//    void testDeleteCartCheckout() {
//        CartCheckoutDTO cartCheckoutDTO = createCartCheckoutDTO();
//        UserEntity user = createUserEntity(cartCheckoutDTO.getUserId());
//        List<CartItems> items = createCartItems(cartCheckoutDTO.getItems(), user);
//        repository.create(cartCheckoutDTO, user, items);
//
//        boolean deleted = repository.delete(1L);
//        assertTrue(deleted, "Deleting an existing cart should return true");
//        assertTrue(repository.findAll().isEmpty(), "All carts should be deleted");
//    }
//}