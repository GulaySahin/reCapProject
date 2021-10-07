<img align="right" alt="GIF" src="https://github.com/abhisheknaiidu/abhisheknaiidu/blob/master/code.gif?raw=true" width="500" height="320" />

## ETIYACAMP RENT A CAR PROJECT 👨‍🎓, BACKEND 🚀 ✍!



Bu proje bir online araç kiralama projesidir.
Kurumsal, Katmanlı Mimari yapısı kullanılarak SOLID prensiplerine uyularak clean code yazılması amaçlanmış , Java dili ile yazılmıştır.

Katmanlar <br>
🧷WebAPI  <br>
🧷Business  <br>
🧷Core  <br>
🧷DataAccess  <br>
🧷Entities  <br>


Bu Projede Kullanılan Teknolojiler

✔PostgreSql <br>
✔Spring Data JPA <br>
✔Validation <br>
✔Lombok <br>
✔Cloudinary <br>
✔Swagger UI <br>
✔Error Handling <br>
✔Result Türleri <br>

### 📩 Connect with me:
[linkedin]: https://www.linkedin.com/in/g%C3%BClay-%C5%9Fahin/
<img align="left" alt="linkedin | LinkedIn" width="24px" src="https://raw.githubusercontent.com/peterthehan/peterthehan/master/assets/linkedin.svg" />[LinkedIn]
<br />
gulay_431@hotmail.com
<img align="left"  height="24" width="24" src="https://cdn.jsdelivr.net/npm/simple-icons@v4/icons/gmail.svg" />

## GEREKSİNİMLER

1-
Yeni bir proje oluşturunuz. Adı ReCapProject olacak. 
Entities, DataAccess, Business oluşturunuz.
Bir araba nesnesi oluşturunuz. "Car"
Brand ve Color nesnelerini oluşturunuz.(BrandId,BrandName…
Özellik olarak : Id, BrandId, ColorId, ModelYear, DailyPrice, Description alanlarını ekleyiniz. (Brand = Marka)
<br> <br>
2-
Car, Brand, Color sınıflarınız için tüm CRUD operasyonlarını hazır hale getiriniz.
Console'da Tüm CRUD operasyonlarınızı Car, Brand, Model nesneleriniz için test ediniz. GetAll, GetById, Insert, Update, Delete.
Arabaları şu bilgiler olacak şekilde listeleyiniz. CarName, BrandName, ColorName, DailyPrice. (İpucu : Dto oluşturup 3 tabloya join yazınız)
<br> <br>
3-
Core katmanında Results yapılandırması yapınız.
<br><br>
4-
Kullanıcılar tablosu oluşturunuz. Users-->Id,FirstName,LastName,Email,Password
Müşteriler tablosu oluşturunuz. Customers-->UserId,CompanyName
*****Kullanıcılar ve müşteriler ilişkilidir.
Arabanın kiralanma bilgisini tutan tablo oluşturunuz. Rentals-->Id, CarId, CustomerId, RentDate(Kiralama Tarihi), ReturnDate(Teslim Tarihi). Araba teslim edilmemişse ReturnDate null'dır.
Projenizde bu entity'leri oluşturunuz.
CRUD operasyonlarını yazınız.
Yeni müşteriler ekleyiniz.
Arabayı kiralama imkanını kodlayınız. Rental-->Add
Arabanın kiralanabilmesi için arabanın teslim edilmesi gerekmektedir.
<br><br>
5-
WebAPI katmanını kurunuz.
Business katmanındaki tüm servislerin Api karşılığını yazınız.
Swagger’da test ediniz.
<br><br>
6-
Validation desteği ekleyiniz.
<br><br>
7-
CarImages (Araba Resimleri) tablosu oluşturunuz. (Id,CarId,ImagePath,Date) Bir arabanın birden fazla resmi olabilir.
Api üzerinden arabaya resim ekleyecek sistemi yazınız.
Resimler projeniz içerisinde bir klasörde tutulacaktır. Resimler yüklendiği isimle değil, kendi vereceğiniz GUID ile dosyalanacaktır.
Resim silme, güncelleme yetenekleri ekleyiniz.
Bir arabanın en fazla 5 resmi olabilir.
Resmin eklendiği tarih sistem tarafından atanacaktır.
Bir arabaya ait resimleri listeleme imkanı oluşturunuz. (Liste)
Eğer bir arabaya ait resim yoksa, default bir resim gösteriniz. Bu resim şirket logonuz olabilir. (Tek elemanlı liste)
<br><br>
8-
Brand listesinde herhangi bir marka seçildiğinde, o markaya ait arabaları listeleyiniz.
Color listesinde herhangi bir renk seçildiğinde, o renge ait arabaları listeleyiniz.
Car listesinde bir arabaya tıklandığında o arabaya ait detay sayfası oluşturunuz. Bu sayfada bu araca ait resimleri de gösteriniz.
<br><br>
9-
Login/Register yetenekleri getiriniz.
Kiralama esnasında müşterinin findeks puanını sorgulayacak sahte servis ekleyiniz.
Findeks puan aralığı 0-1900 arasındadır.
Araçların kiralanabilmesi için her aracın ayrı ayrı minimum findeks puanı olmalıdır. Bu puanı olmayan müşteriler araç kiralayamaz.
Eğer giriş yapılmamışsa nav'da bu butonlar olsun. Giriş yapılmışsa bu butonlar yerine müşteri adı ex: "Engin Demiroğ" yazsın.
Kullanıcı adını yazdığınız kısım açılır kutu olmalı.
Kullanıcı bilgilerini görüp güncelleyebilmelidir.
Kredi kartıyla ödeme alındığında kullanıcıya kredi kartını kaydedelim mi? Sorusu yöneltiniz. Kaydetmek isteyen müşteriye sonraki ödemede kayıtlı kredi kartını gösteriniz.
<br><br>

10- Markalar tekrar edemez. (Brand-add)
<br><br>
11- (Düzeltme) Kredi kartı formatı control edilmeli. Regex
<br><br>
12- Arabalar bakıma gönderilebilmelidir.
Kirada olan bir araba bakıma gönderilemez.
Bakımda olan bir araba kiralanamaz.
Bakımda olan araba tüm listelerde listelenmemelidir.
<br><br>
13- Şirketimiz büyüdü. Kurumsal müşteriler araba kiralayabilmelidir. (Kurumsal Müşteri – vergiNo, Şirket ismi,email….
	Kiralama kuralları bireysel müşteri ile aynıdır.
  <br><br>
14- Tüm kiralamalar sonucunda fatura kesilmelidir. (Fatura No, Oluşturma Tarihi, Kiralama tarihleri, Toplam kiralama gün sayısı, kiralama tutarı)
	Belirli tarih aralığında tüm faturalar listelenebilmelidir.
           Müşteriye ait faturalar listelenebilmelidir.
           <br><br>
15- Farklı illerde şubeler açmaya karar verdik. Arabalara şehir bilgisi eklenmelidir.
Şehir bilgisine göre arabalar listelenebilmelidir.
Kiralamada alış şehri – dönüş şehri bilgisi eklenmelidir.
Araba teslim edildiğinde, dönüş şehri farklıysa, araba dönüş ili portföyüne girmelidir.
<br><br>
16- Arabalara güncel kilometre bilgisi eklenmelidir.
Kiralamalarda başlıngıç kilometresi girilmelidir.
Kiralama Dönüşlerinde dönüş kilometresi bilgisi girilmelidir. Bu bilgi arabada da güncellenmelidir.
<br><br>
17- Arabaya ait hasarlar girilebilmelidir. (Id,CarId,HasarBilgisi)
<br><br>
18-Fake pos servisi ekleyiniz.Servis olumsuz döndüğünde kiralama gerçekleştirilmemelidir.
<br><br>
19-Araba farklı ilde teslim edildiğinde kiralama bedeline 500 tl ek hizmet bedeli eklenmelidir.
<br><br>
20-Hizmet kalitesini arttırmaya karar verdik.Araba kiralanırken ek hizmetler satın 
alınabilir.Bebek koltuğu,scooter.
<br><br>
21-Ek hizmetler eklenebilmeli,güncellenebilmeli,listelenebilmelidir.Ek hizmetler isim
olarak tekrar edemez.
