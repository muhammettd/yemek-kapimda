# API Tasarımı - OpenAPI Specification

#### **OpenAPI Spesifikasyon Dosyası:** [API-Tasarimi.yaml](API-Tasarimi.yaml)

Bu doküman, OpenAPI Specification (OAS) 3.0 standardına göre hazırlanmış örnek bir API tasarımını içermektedir.

## OpenAPI Specification

```yaml
openapi: 3.0.3
info:
  title: Yemek Kapımda
  description: |
    Restoranlardan yemek siparişi vermek için bir API.
    
    ## Özellikler
    - Kullanıcı yönetimi
    - Hesap işlemleri
    - Restoran işlemleri
    - Sipariş işlemleri
    - JWT tabanlı kimlik doğrulama
  version: 1.0.0
  contact:
    name: API Destek Ekibi
    email: api-support@yazmuh.com
    url: https://api.yazmuh.com/support
  license:
    name: MIT
    url: https://opensource.org/licenses/MIT

servers:
  - url: https://api.yazmuh.com/v1
    description: Production server
  - url: https://staging-api.yazmuh.com/v1
    description: Staging server
  - url: http://localhost:3000
    description: Development server

tags:
  - name: account
    description: Profil ve adres işlemleri
  - name: restaurant
    description: Restoran listeleme, sipariş verme işlemleri
  - name: auth
    description: Kimlik doğrulama işlemleri

paths:
  /auth/register:
    post:
      tags:
        - auth
      summary: Yeni kullanıcı kaydı
      description: Sisteme yeni bir kullanıcı kaydeder
      operationId: createUser
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CreateUserRequest'
            examples:
              example1:
                summary: Örnek kullanıcı kaydı
                value:
                  email: kullanici@example.com
                  password: Guvenli123!
                  firstName: Ahmet
                  lastName: Yılmaz
      responses:
        '201':
          description: Kullanıcı başarıyla oluşturuldu
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/User'
        '400':
          $ref: '#/components/responses/BadRequest'
        '409':
          description: Email adresi zaten kullanımda
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Error'

  /auth/login:
    post:
      tags:
        - auth
      summary: Kullanıcı girişi
      description: Email ve şifre ile giriş yapar, JWT token döner
      operationId: loginUser
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/LoginUserRequest'
      responses:
        '200':
          description: Giriş başarılı
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/AuthToken'
        '401':
          $ref: '#/components/responses/Unauthorized'

  /restaurant:
    get:
      tags:
        - restaurant
      summary: Restoranları getir
      description: Sistemdeki tüm restoranları getirir
      operationId: getRestaurants
      security:
        - bearerAuth: []
      parameters:
        - $ref: '#/components/parameters/PageParam'
        - $ref: '#/components/parameters/LimitParam'
        - name: stars
          in: query
          description: Restoranları yıldızına göre sırala
          schema:
            type: string
            enum: stars

        - $ref: '#/components/parameters/PageParam'
        - $ref: '#/components/parameters/LimitParam'
        - name: stars
          in: query
          description: Restoranları mutfağına göre filtrele
          schema:
            type: string
            enum: cuisine

        - $ref: '#/components/parameters/PageParam'
        - $ref: '#/components/parameters/LimitParam'
        - name: name
          in: query
          description: Restoranları arama yaptığımız isme göre getir
          schema:
            type: string
            enum: name

      responses:
        '200':
          description: Başarılı
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/RestaurantList'
        

  /account/profile//{userId}:
    get:
      tags:
        - account
      summary: Kullanıcı detayı
      description: Belirli bir kullanıcının detay bilgilerini getirir
      operationId: getUserById
      security:
        - bearerAuth: []
      parameters:
        - $ref: '#/components/parameters/UserIdParam'
      responses:
        '200':
          description: Başarılı
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/User'
        '401':
          $ref: '#/components/responses/Unauthorized'
        '403':
          $ref: '#/components/responses/Forbidden'
        '404':
          $ref: '#/components/responses/NotFound'
    
    put:
      tags:
        - users
      summary: Kullanıcı güncelle
      description: Kullanıcı bilgilerini günceller
      operationId: updateUser
      security:
        - bearerAuth: []
      parameters:
        - $ref: '#/components/parameters/UserIdParam'
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/UpdateUserRequest'
      responses:
        '200':
          description: Kullanıcı başarıyla güncellendi
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/User'
        '400':
          $ref: '#/components/responses/BadRequest'
        '401':
          $ref: '#/components/responses/Unauthorized'
        '403':
          $ref: '#/components/responses/Forbidden'
        '404':
          $ref: '#/components/responses/NotFound'

  /account/address:
    post:
      tags:
        - account
      summary: Yeni adres 
      description: Kullanıcı hesabına yeni bir adres ekler
      operationId: addAddress
      security:
        - bearerAuth: []
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CreateAddressRequest'
      responses:
        '201':
          description: Adres başarıyla eklendi
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Address'
        '400':
          $ref: '#/components/responses/BadRequest'

  /account/address/{addressId}:
    put:
      tags:
        - account
      summary: Adresi güncelle
      description: Kullanıcı hesabındaki seçili adresin bilgilerini günceller
      operationId: getProductById
      parameters:
        - $ref: '#/components/parameters/AddressIdParam'
      responses:
        '200':
          description: Başarılı
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UpdateAddressRequest'
        '404':
          $ref: '#/components/responses/NotFound'
    delete:
      tags:
        - users
      summary: Adres sil
      description: Kullanıcı hesabından seçili adresi siler
      operationId: deleteUser
      security:
        - bearerAuth: []
      parameters:
        - $ref: '#/components/parameters/UserIdParam'
      responses:
        '204':
          description: Kullanıcı başarıyla silindi
        '401':
          $ref: '#/components/responses/Unauthorized'
        '403':
          $ref: '#/components/responses/Forbidden'
        '404':
          $ref: '#/components/responses/NotFound'

components:
  securitySchemes:
    bearerAuth:
      type: http
      scheme: bearer
      bearerFormat: JWT
      description: JWT token ile kimlik doğrulama

  parameters:
    UserIdParam:
      name: userId
      in: path
      required: true
      description: Kullanıcı ID'si
      schema:
        type: number
        format: long
    
    AddressIdParam:
      name: addressId
      in: path
      required: true
      description: Bir adresin ID'si
      schema:
        type: number
        format: long
    
    PageParam:
      name: page
      in: query
      description: Sayfa numarası
      schema:
        type: integer
        minimum: 1
        default: 1
    
    LimitParam:
      name: limit
      in: query
      description: Sayfa başına kayıt sayısı
      schema:
        type: integer
        minimum: 1
        maximum: 100
        default: 20

  schemas:
    User:
      type: object
      required:
        - id
        - email
        - name
        - surname
        - password
      properties:
        id:
          type: number
          format: long
          description: Kullanıcı benzersiz kimliği
          example: 1
        email:
          type: string
          format: email
          description: Kullanıcı email adresi
          example: "kullanici@example.com"
        name:
          type: string
          description: Ad
          example: "Ahmet"
        surname:
          type: string
          description: Soyad
          example: "Yılmaz"
        password:
          type: string
          description: Şifre
          example: "ali123"
        phone:
          type: string
          description: Telefon numarası
          example: "+905551234567"

    CreateUserRequest:
      type: object
      required:
        - email
        - password
        - name
        - surname
      properties:
        email:
          type: string
          format: email
          example: "kullanici@example.com"
        password:
          type: string
          format: password
          minLength: 8
          example: "Guvenli123!"
        name:
          type: string
          minLength: 2
          example: "Ahmet"
        surname:
          type: string
          minLength: 2
          example: "Yılmaz"

    UpdateUserRequest:
      type: object
      properties:
        name:
          type: string
          example: "Ahmet"
        surname:
          type: string
          example: "Yılmaz"
        email:
          type: string
          format: email
          example: "yeniemail@example.com"
        password:
          type: string
          description: Şifre
          example: "ali123"

    LoginUserRequest:
      type: object
      required:
        - email
        - password
      properties:
        email:
          type: string
          format: email
          example: "kullanici@example.com"
        password:
          type: string
          format: password
          example: "Guvenli123!"

    AuthToken:
      type: object
      required:
        - token
        - expiresIn
        - user
      properties:
        token:
          type: string
          description: JWT access token
          example: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        expiresIn:
          type: integer
          description: Token geçerlilik süresi (saniye)
          example: 3600
        user:
          $ref: '#/components/schemas/User'

    Address:
      type: object
      required:
        - id
        - addressTitle
        - city
        - district
        - fullAddress
      properties:
        id:
          type: number
          format: long
          example: 1
        addressTitle:
          type: string
          description: Adresin türü
          example: "Ev adresi"
        city:
          type: string
          description: Adresin bulunduğu şehir
          example: "Isparta"
        district:
          type: string
          description: Adresin bulunduğu ilçe
          example: "Merkez
        fullAddress:
          type: string
          description: Adresin bulunduğu mahalle,sokak,bi ismi, no'su
          example: "Bahçelievler Mahallesi 3000 Sokak"

    CreateAddressRequest:
      type: object
      required:
        - addressTitle
        - city
        - district
        - fullAddress
      properties:
        addressTitle:
          type: string
          description: Adresin türü
          example: "Ev adresi"
        city:
          type: string
          description: Adresin bulunduğu şehir
          example: "Isparta"
        district:
          type: string
          description: Adresin bulunduğu ilçe
          example: "Merkez
        fullAddress:
          type: string
          description: Adresin bulunduğu mahalle,sokak,bi ismi, no'su
          example: "Bahçelievler Mahallesi 3000 Sokak"

    UpdateAddressRequest:
      type: object
      properties:
        addressTitle:
          type: string
          description: Adresin türü
          example: "Ev adresi"
        city:
          type: string
          description: Adresin bulunduğu şehir
          example: "Isparta"
        district:
          type: string
          description: Adresin bulunduğu ilçe
          example: "Merkez
        fullAddress:
          type: string
          description: Adresin bulunduğu mahalle,sokak,bi ismi, no'su
          example: "Bahçelievler Mahallesi 3000 Sokak"

    Restaurant:
      type: object
      required:
        - name
        - cuisine
      properties:
        name:
          type: string
          description: Restoranın ismi
          example: "Pizza Evi"
        stars:
          type: number
          format: float
          description: Restoranın yıldız sayısı
          example: 4.3
        cuisine:
          type: string
          description: Restoranın mutfak türü
          example: "Izgara"

    RestaurantList:
      type: object
      properties:
        data:
          type: array
          items:
            $ref: '#/components/schemas/Restaurant'
        pagination:
          $ref: '#/components/schemas/Pagination'

    Pagination:
      type: object
      properties:
        page:
          type: integer
          description: Mevcut sayfa
          example: 1
        limit:
          type: integer
          description: Sayfa başına kayıt
          example: 20
        totalPages:
          type: integer
          description: Toplam sayfa sayısı
          example: 5
        totalItems:
          type: integer
          description: Toplam kayıt sayısı
          example: 95

    Error:
      type: object
      required:
        - code
        - message
      properties:
        code:
          type: string
          description: Hata kodu
          example: "VALIDATION_ERROR"
        message:
          type: string
          description: Hata mesajı
          example: "Geçersiz email adresi"
        details:
          type: array
          description: Detaylı hata bilgileri
          items:
            type: object
            properties:
              field:
                type: string
                example: "email"
              message:
                type: string
                example: "Email formatı geçersiz"

  responses:
    BadRequest:
      description: Geçersiz istek
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/Error'
          example:
            code: "BAD_REQUEST"
            message: "İstek parametreleri geçersiz"
    
    Unauthorized:
      description: Yetkisiz erişim
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/Error'
          example:
            code: "UNAUTHORIZED"
            message: "Kimlik doğrulama başarısız"
    
    NotFound:
      description: Kaynak bulunamadı
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/Error'
          example:
            code: "NOT_FOUND"
            message: "İstenen kaynak bulunamadı"
    
    Forbidden:
      description: Erişim reddedildi
      content:
        application/json:
          schema:
            $ref: '#/components/schemas/Error'
          example:
            code: "FORBIDDEN"
            message: "Bu işlem için yetkiniz bulunmamaktadır"

