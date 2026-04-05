## Rest API Görev Dağılımı ##

Rest API Adresi: [https://yemek-kapimda.onrender.com](https://yemek-kapimda.onrender.com)

Video Lİnki: [https://www.youtube.com/watch?v=cATadRAVmkU](https://www.youtube.com/watch?v=cATadRAVmkU)

## 1. Üye Olma
- **Endpoint:** `POST api/auth/register`
- **Request Body:** 
  ```json
  {
    "email": "kullanici@example.com",
    "password": "Guvenli123!",
    "firstName": "Ahmet",
    "lastName": "Yılmaz"
  }
  ```
- **Response:** `201 Created` - Kullanıcı başarıyla oluşturuldu

## 2. Giriş Yapma
- **Endpoint:** `POST api/auth/register`
- **Request Body:** 
  ```json
  {
    "email": "kullanici@example.com",
    "password": "Guvenli123!"
  }
  ```
- **Response:** `201 Created` - Kullanıcı başarıyla giriş yaptı


## 3. Adresleri Getirme
- **Endpoint:** `GET api/addresses`
- Authentication: Bearer Token gerekli
- **Response:** `200 OK` - Kullanıcının adresleri getirildi

## 4. Adres Oluşturma
- **Endpoint:** `POST api/addresses`
- **Request Body:** 
  ```json
  {
    "title": "Ev",
    "city": "Antalya",
    "district": "kepez",
    "fullAddress": "etiler mahallesi"
  }
  ```
- **Response:** `201 Created` - Adres başarıyla oluşturuldu

## 5. Adresi Silme
- **Endpoint:** `GET api/addresses/{addressId}`
- Authentication: Bearer Token gerekli
- Path Parameters:
  addressId (long, required) - Silincek adresin ID'si
- **Response:** `200 OK` - Adres başarıyla silindi.

## 6. Restorandaki Menüyü Getirme
- **Endpoint:** `GET api/restaurants/{restaurantId}/products`
- Authentication: Bearer Token gerekli
- Path Parameters:
  restaurantId (long, required) - Menüsü getirelecek Restoranın ID'si
- **Response:** `200 OK` - Restoran menüsü başarıyla getirildi.

## 7. Restoranları Getirme
- **Endpoint:** `GET api/restaurants`
- Authentication: Bearer Token gerekli
- **Response:** `200 OK` - Restoranlar başarıyla getirildi.

- ## 8. Filtreleme İçin Mutfak Türlerini Getirme
- **Endpoint:** `GET api/restaurants/cuisines`
- Authentication: Bearer Token gerekli
- **Response:** `200 OK` - Mutfak türleri başarıyla getirildi.

## 9. Restoran Veya Yemek Arama
- **Endpoint:** `GET api/restaurants/search?query=`
- Authentication: Bearer Token gerekli
- **Response:** `200 OK` - Restoran başarıyla getirildi.

## 10. Sipariş Oluşturma
- **Endpoint:** `POST api/orders`
- Authentication: Bearer Token gerekli
- **Request Body:** 
  ```json
  {
    {
    "restaurantId": 2,
    "deliveryAddressId": 6,
    "items": [
    {
      "productId": 1,
      "quantity": 1
    },
    {
      "productId": 2,
      "quantity": 1
    }
    ]
    }
  }
  ```
- **Response:** `201 Created` - Sipariş başarıyla oluşturuldu.















