package id.ac.ui.cs.advprog.userfunctionality.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookTest {

    @Test
    public void testBookInitialization() {
        Book book = new Book();
        book.setJudulBuku("Test Judul");
        book.setPenulis("Test Penulis");
        book.setPenerbit("Test Penerbit");
        book.setDeskripsi("Test Deskripsi");
        book.setHarga(100.0);
        book.setStok(10);
        book.setTanggalTerbit(LocalDate.of(2020, 1, 1));
        book.setIsbn("1234567890");
        book.setJumlahHalaman(200);
        book.setFotoCover("test.jpg");
        book.setKategori("Test Kategori");

        assertEquals("Test Judul", book.getJudulBuku());
        assertEquals("Test Penulis", book.getPenulis());
        assertEquals("Test Penerbit", book.getPenerbit());
        assertEquals("Test Deskripsi", book.getDeskripsi());
        assertEquals(100.0, book.getHarga());
        assertEquals(10, book.getStok());
        assertEquals(LocalDate.of(2020, 1, 1), book.getTanggalTerbit());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(200, book.getJumlahHalaman());
        assertEquals("test.jpg", book.getFotoCover());
        assertEquals("Test Kategori", book.getKategori());
    }

    @Test
    public void testBookNoArgsConstructor() {
        Book book = new Book();
        assertNotNull(book);
    }

    @Test
    public void testBookAllArgsConstructor() {
        LocalDate publishDate = LocalDate.of(2020, 1, 1);
        Book book = new Book();
        book.setJudulBuku("Test Judul");
        book.setPenulis("Test Penulis");
        book.setPenerbit("Test Penerbit");
        book.setDeskripsi("Test Deskripsi");
        book.setHarga(100.0);
        book.setStok(10);
        book.setTanggalTerbit(publishDate);
        book.setIsbn("1234567890");
        book.setJumlahHalaman(200);
        book.setFotoCover("test.jpg");
        book.setKategori("Test Kategori");

        assertEquals("Test Judul", book.getJudulBuku());
        assertEquals("Test Penulis", book.getPenulis());
        assertEquals("Test Penerbit", book.getPenerbit());
        assertEquals("Test Deskripsi", book.getDeskripsi());
        assertEquals(100.0, book.getHarga());
        assertEquals(10, book.getStok());
        assertEquals(publishDate, book.getTanggalTerbit());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(200, book.getJumlahHalaman());
        assertEquals("test.jpg", book.getFotoCover());
        assertEquals("Test Kategori", book.getKategori());
    }
}
