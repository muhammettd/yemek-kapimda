**1. Üye Olma**
**API Metodu:** POST /auth/register
**Açıklama:** Kullanıcıların yeni hesaplar oluşturarak sisteme kayıt olmasını sağlar. Kişisel bilgilerin toplanıp hesap oluşturulmasını sağlar. 
Kullanıcı, ad, soyad, email ve şifre bilgilerini kullanarak hesap oluşturabilir.

**2. Giriş Yapma:** 
**API Metodu:** POST /auth/login
**Açıklama:** Kullanıcı daha öncesinde sisteme kayıt olurken oluşturduğu email ve şifresi bilgilerini kullanarak sisteme giriş yapabilmesini sağlar.

**3. Profilini Görüntüleme:**
**API Metodu:** GET /account/profile/{userId}
**Açıklama:** Kullanıcının profil bilgilerini görüntülemesini sağlar. Kullanıcı kayıt olurken oluşturduğu bilgilerini ve hesabına eklediği kişisel 
bilgileri (telefon no) bilgilerini görüntüleyebilir. Güvenlik için giriş yapmış olması gerekir.

**4. Profilini Güncelleme:**
**API Metodu:** PUT /account/profile/{userId}
**Açıklama:** Kullanıcının profil bilgilerini güncellenmesini sağlar. Kullanıcı kayıt olurken oluşturduğu bilgilerini ve hesabına ait olan kişisel 
bilgileri (telefon no) bilgilerini güncelleyebilir. Güvenlik için giriş yapmış olması gerekir.

**5. Adres Ekleme:**
**API Metodu:** POST /account/address
**Açıklama:** Kullanıcının yemek siparişi oluşturduğunda teslim edilecek ev veya iş yeri adresi/adreslerinin eklenmesini sağlar. Güvenlik için giriş 
yapmış olması gerekir.

**6. Adres Güncelleme:**
**API Metodu:** PUT /account/address/{addressId}
**Açıklama:** Kullanıcının adres bilgilerini güncellmesini sağlar. Kullanıcının daha öncesinde hesabına eklediği ev veya iş adresinin/adreslerinin 
bilgilerini güncelleyebilir. Güvenlik için giriş yapmış olması gerekir.

**7. Adres Silme:**
**API Metodu:** DELETE /account/address/{addressId}
**Açıklama:** Kullanıcının adres bilgilerini hesabından silmesini sağlar. Kullanıcının daha öncesinde hesabına eklediği ev ve iş adresinin/adreslerinin 
bilgilerini silebilir. Güvenlik için giriş yapmış olması gerekir.

**8. Restoranları Yıldız Sayısına Göre Sıralama:**
**API Metodu:** GET /restaurant?sort=stars
**Açıklama:** Kullanıcı websitesindeki bulunan tüm restoranları kullanıcıların verdiği yıldız sayısına göre (1-5 arasında) ortalama bir yıldız sayısı hesaplanır.
Hesaplanan bu yıldız sayısı ile, restoranları çoktan aza doğru sıralar.

**9. Restoranları Mutfağına Göre Filtreleme:**
**API Metodu:** GET /restaurant?cuisine=tür
**Açıklama:** Kullanıcının restoranları seçtiği bir yemek türüne ait olan tüm restorantları gösterir.

**10. Restoranları Arama Çubuğunda İsme Göre Arama:**
**API Metodu:** GET /restaurant?name=isim
**Açıklama**: Kullanıcının arama çubuğuna yazdığı metni alır. Tüm restoranlar içerisinde  bu metne karşılık gelen restoran, yemek isimlerini gösterir.

