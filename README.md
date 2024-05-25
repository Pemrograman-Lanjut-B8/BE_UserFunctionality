# 📚 Buku.ID

### 📝 Kelas dan Kelompok:
* Pemrograman Lanjut B - B8

### 👤 Developer Team:
* Muhammad Raihan Akbar (2206827674) 🧔🏻‍♂️
* Dimas Herjunodarpito Notoprayitno (2206081282) 🧔🏻‍♂️
* Pradipta Arya Pramudita (2206083685) 🧔🏻‍♂️
* Novrizal Airsyahputra (2206081780) 🧔🏻‍♂️
* Georgina Elena Shinta Dewi Achti (2206810995) 👩🏻

### 💻 Daftar Repositori:

1. BE-Authentication

- Authentication (Register, Login, dan Logout)  (🙋 / 💻) - Raihan

2. BE-Admin

- Dashboard Administrator (💻) - Dimas
- Melihat Pembelian Pelanggan (💻) - Elena
- Mengelola dan Melihat Detail Buku  (🙋 / 💻) - Dimas
- Melihat Keranjang dan Melakukan Checkout  (🙋 / 💻) - Pradipta

3. BE-UserFunctionality

- Landing Page (🙋) - Elena
- Mengelola dan Melihat Detail Buku  (🙋 / 💻) - Dimas
- Melihat Daftar Buku (🙋) - Raihan
- Melihat Keranjang dan Melakukan Checkout  (🙋 / 💻) - Pradipta
- Rating & Review Buku  (🙋) - Novrizal

4. fe-repository

### 🏗️ Software Architecture
Kami menerapkan **Microservice Software Architecture**, yaitu pendekatan _software development_ yang memecah suatu aplikasi besar menjadi serangkaian layanan kecil yang independen satu sama lain. Setiap layanan ini (_microservices_) berjalan dalam proses yang terpisah dan berkomunikasi melalui protokol jaringan.

Berikut ini beberapa alasan kami memilih Microservice Software Architecture:

1. Kemudahan Perawatan dan Pengembangan Kode: Hal tersebut disebabkan setiap _microservice_ adalah unit yang terisolasi. Jadi, perawatan dan pengembangannya akan menjadi lebih mudah. Tim dapat bekerja secara independen pada _service_ yang berbeda.

2. Pecahan Fungsional: Aplikasi besar dibagi menjadi layanan yang lebih kecil, masing-masing bertanggung jawab atas satu fungsi atau tugas tertentu. Ini memungkinkan pengembang untuk fokus pada pengembangan, pengujian, dan perawatan yang lebih kecil, yang lebih mudah dikelola daripada aplikasi monolitik besar.

3. Kemandirian: Setiap _microservice_ berdiri sendiri dan dapat di-deploy secara independen. Hal ini akan memungkinkan pengembang untuk melakukan perubahan pada satu layanan tanpa mempengaruhi layanan lainnya, serta meningkatkan skala dan merilis dengan cepat.

4. Resiliensi: _Microservice architecture_ dapat dirancang untuk menjadi lebih tahan terhadap kegagalan. Misalnya, jika satu layanan gagal, yang lainnya masih dapat beroperasi, dan kemampuan replikasi layanan memungkinkan untuk penanganan kegagalan yang lebih baik.

5. Skalabilitas: _Microservice architecture_ memungkinkan skalabilitas yang lebih baik. Hal tersebut disebabkan pemecahan fungsional yang diterapkan pada _software architecture_ ini.

_Microservice_ : </br>
- `Review & Rating` berguna agar pengguna dapat memberikan ulasan dan memberikan penilaian (dari skala 1-10) terhadap buku yang telah dibaca atau dibeli. 
Keduanya termasuk ke dalam satu _microservice_ karena memiliki keterkaitan erat satu sama lain. 
Sebagai contoh, saat pengguna ingin memberikan ulasan, maka ia harus sekaligus memberikan penilaian terhadap buku tersebut juga.

- `Landing Page` merupakan fitur utama yang berfungsi sebagai titik masuk pertama bagi pengguna ke dalam aplikasi. 
Fitur ini dirancang untuk menyajikan ringkasan informasi dan konten yang relevan, seperti penawaran produk terbaru, artikel terkini, atau fitur utama dari aplikasi tersebut. Dalam konteks mikro-layanan, 
`Landing Page` dapat dipecah menjadi komponen-komponen terpisah yang masing-masing mengelola fungsionalitas tertentu. Misalnya, layanan dapat bertanggung jawab atas pengelolaan konten statis, pembaruan data dinamis, atau interaksi pengguna.
Dengan pendekatan ini, pengembang dapat mencapai skalabilitas, fleksibilitas, dan pemeliharaan yang lebih baik. Ini memungkinkan aplikasi untuk dengan mudah dikelola, dikembangkan, dan diperbarui seiring waktu, sesuai dengan kebutuhan pengguna dan perubahan pasar.

### 🥇 Profiling
https://docs.google.com/document/d/1R4JENwT-I_Zd67zH5d932mGh0AgnFktbEZpjAzxciHM/edit?usp=sharing