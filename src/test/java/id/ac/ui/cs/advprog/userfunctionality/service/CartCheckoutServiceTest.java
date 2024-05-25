//package id.ac.ui.cs.advprog.userfunctionality.service;
//
//import id.ac.ui.cs.advprog.userfunctionality.dto.CartCheckoutDTO;
//import id.ac.ui.cs.advprog.userfunctionality.dto.CartItemsDTO;
//import id.ac.ui.cs.advprog.userfunctionality.model.Book;
//import id.ac.ui.cs.advprog.userfunctionality.model.CartCheckout;
//import id.ac.ui.cs.advprog.userfunctionality.model.CartItems;
//import id.ac.ui.cs.advprog.userfunctionality.model.UserEntity;
//import id.ac.ui.cs.advprog.userfunctionality.repository.CartCheckoutRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//public class CartCheckoutServiceTest {
//
//    @Mock
//    private CartCheckoutRepository cartCheckoutRepository;
//
//    @InjectMocks
//    private CartCheckoutServiceImpl cartCheckoutService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
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
//    public void testCreateCartCheckout() throws ExecutionException, InterruptedException {
//        CartCheckoutDTO cartCheckoutDTO = createCartCheckoutDTO();
//        UserEntity user = createUserEntity(cartCheckoutDTO.getUserId());
//        List<CartItems> items = createCartItems(cartCheckoutDTO.getItems(), user);
//
//        CartCheckout cartCheckout = new CartCheckout();
//        cartCheckout.setId(cartCheckoutDTO.getId());
//        cartCheckout.setUser(user);
//        cartCheckout.setItems(items);
//        cartCheckout.setTotalPrice(cartCheckoutDTO.getTotalPrice());
//        cartCheckout.setStatus(cartCheckoutDTO.getStatus());
//
//        when(cartCheckoutRepository.create(any(CartCheckoutDTO.class), any(UserEntity.class), any(List.class)))
//                .thenReturn(cartCheckout);
//
//        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.createCartCheckout(cartCheckoutDTO);
//        CartCheckoutDTO createdCartCheckoutDTO = future.get();
//
//        assertEquals(cartCheckoutDTO.getUserId(), createdCartCheckoutDTO.getUserId());
//        assertEquals(cartCheckoutDTO.getTotalPrice(), createdCartCheckoutDTO.getTotalPrice(), 0.01);
//    }
//
//    @Test
//    public void testFindCartCheckoutById() throws ExecutionException, InterruptedException {
//        Long cartId = 1L;
//        CartCheckoutDTO cartCheckoutDTO = createCartCheckoutDTO();
//        UserEntity user = createUserEntity(cartCheckoutDTO.getUserId());
//        List<CartItems> items = createCartItems(cartCheckoutDTO.getItems(), user);
//
//        CartCheckout cartCheckout = new CartCheckout();
//        cartCheckout.setId(cartId);
//        cartCheckout.setUser(user);
//        cartCheckout.setItems(items);
//        cartCheckout.setTotalPrice(cartCheckoutDTO.getTotalPrice());
//        cartCheckout.setStatus(cartCheckoutDTO.getStatus());
//
//        when(cartCheckoutRepository.findById(cartId)).thenReturn(Optional.of(cartCheckoutDTO));
//
//        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.findCartCheckoutById(cartId);
//        CartCheckoutDTO foundCartCheckoutDTO = future.get();
//
//        assertNotNull(foundCartCheckoutDTO);
//        assertEquals(cartCheckoutDTO.getUserId(), foundCartCheckoutDTO.getUserId());
//    }
//
//    @Test
//    public void testUpdateCartCheckout() throws ExecutionException, InterruptedException {
//        Long cartId = 1L;
//        CartCheckoutDTO existingCartCheckoutDTO = createCartCheckoutDTO();
//        UserEntity user = createUserEntity(existingCartCheckoutDTO.getUserId());
//        List<CartItems> items = createCartItems(existingCartCheckoutDTO.getItems(), user);
//
//        CartCheckout existingCartCheckout = new CartCheckout();
//        existingCartCheckout.setId(cartId);
//        existingCartCheckout.setUser(user);
//        existingCartCheckout.setItems(items);
//        existingCartCheckout.setTotalPrice(existingCartCheckoutDTO.getTotalPrice());
//        existingCartCheckout.setStatus(existingCartCheckoutDTO.getStatus());
//
//        CartCheckoutDTO updateDTO = createCartCheckoutDTO();
//        updateDTO.setUserId(UUID.randomUUID().toString());
//        updateDTO.setTotalPrice(150.0);
//
//        when(cartCheckoutRepository.findById(cartId)).thenReturn(Optional.of(existingCartCheckoutDTO));
//        when(cartCheckoutRepository.update(eq(cartId), any(CartCheckoutDTO.class), any(UserEntity.class), any(List.class)))
//                .thenReturn(existingCartCheckout);
//
//        existingCartCheckout.setUser(user);
//        existingCartCheckout.setTotalPrice(updateDTO.getTotalPrice());
//
//        CompletableFuture<CartCheckoutDTO> future = cartCheckoutService.updateCartCheckout(cartId, updateDTO);
//        CartCheckoutDTO updatedCartCheckout = future.get();
//
//        assertNotNull(updatedCartCheckout);
//        assertEquals(updateDTO.getUserId(), updatedCartCheckout.getUserId());
//        assertEquals(updateDTO.getTotalPrice(), updatedCartCheckout.getTotalPrice(), 0.01);
//    }
//
//    @Test
//    public void testDeleteCartCheckout() throws ExecutionException, InterruptedException {
//        Long cartId = 1L;
//        when(cartCheckoutRepository.delete(cartId)).thenReturn(true);
//
//        CompletableFuture<Boolean> future = cartCheckoutService.deleteCartCheckout(cartId);
//        boolean result = future.get();
//
//        assertTrue(result);
//        verify(cartCheckoutRepository).delete(cartId);
//    }
//
//    @Test
//    public void testDeleteCartCheckoutNotFound() throws ExecutionException, InterruptedException {
//        Long cartId = 99L;
//        when(cartCheckoutRepository.delete(cartId)).thenReturn(false);
//
//        CompletableFuture<Boolean> future = cartCheckoutService.deleteCartCheckout(cartId);
//        boolean result = future.get();
//
//        assertFalse(result);
//    }
//}