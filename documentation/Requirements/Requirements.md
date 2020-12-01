# Требования к проекту
### Содержание
1. [Введение](#1)
2. [Требования пользователя](#2) <br>
  2.1. [Программные интерфейсы](#2.1) <br>
  2.2. [Интерфейс пользователя](#2.2) <br>
  2.3. [Характеристики пользователей](#2.3) <br>
3. [Системные требования](#3) <br>
  3.1 [Функциональные требования](#3.1) <br>
  3.2 [Нефункциональные требования](#3.2) <br>
    3.2.1 [Атрибуты качества](#3.2.1) <br>
      3.2.1.1 [Требования к удобству использования](#3.2.1.1) <br>
      3.2.1.2 [Требования к безопасности](#3.2.1.2) <br>
	3.2.2 [Ограничения](#3.2.2) <br>
 4. [Аналоги](#4) <br>
 
 ### Глоссарий
 * Продукт (товар) - наручные часы, которые используются в качестве ресурса, предлагаемого пользователям.
 * Корзина - это интерфейс, куда пользователь добавляет товары с целью покупки.
 * Заказ - документ, создаваемый покупателем, на поставку определенного продукта.
 
 ### 1. Введение <a name="1"></a>
В современном интернете существует большое колличество интернет-магазинов, ориентированных на разные группы пользователей, в том числе и интернет-магазины, осуществляющие продажу механических часов. Большинство из существующих сайтов требуют регистрации с указыванием электронной почты и других личных данных, что может быть потенциально опасно, если сайт не будет надёжно защищён. Данное веб-приложение будет предоставлять безопасный способ заказа товаров без регистрации, а также иметь простой и удобный интерфейс. Приложение будет предоставлять возможность увидеть ранее просмотренные товары, возможность поиска, а также сортировок.

### 2. Требования пользователя <a name="2"></a>
#### 2.1. Программные интерфейсы <a name="2.1"></a>
Проект использует язык Java, а также Servlet API, для хранения данных используется база данных MySQL, для реализации пользовательского интерфейса JSP API.
#### 2.2. Интерфейс пользователя <a name="2.2"></a>
- Окно просмотра всех продуктов
  https://github.com/AlexeiZakharchenia/Watch-Store/blob/master/documentation/Mockups/productListPage.png
- Окно просмотра детальной информации о продукте
  https://github.com/AlexeiZakharchenia/Watch-Store/blob/master/documentation/Mockups/productDetailsPage.png
- Окно просмотра корзины
  https://github.com/AlexeiZakharchenia/Watch-Store/blob/master/documentation/Mockups/cartPage.png
- Окно оформления заказа
  https://github.com/AlexeiZakharchenia/Watch-Store/blob/master/documentation/Mockups/orderPage.png
#### 2.3. Характеристики пользователей <a name="2.3"></a>
Целевая аудитория:
* Пользователи, просматривающие или покупающие товары.

### 3. Системные требования <a name="3"></a>
#### 3.1. Функциональные требования <a name="3.1"></a>
Пользователю предоставлены возможности:

| Функция | Требования | 
|:---|:---|
| Просмотр продуктов | Пользователю будет предоставлен список доступных продуктов со всеми возможностями поиска и сортировок. |
| Просмотр детальной информации о продукте | Пользователю будет предоставлена информация о продукте(цена, колличество в наличии, описание), а также возможность добавления продукта в корзину. |
| Просмотр корзины | Пользователь сможет редактировать колличество продуктов в корзине, а также удалять их из корзины. |
| Оформление заказа | Пользователь сможет указать данные, необъходимые для корректного оформления заказа. |
| Раннее просмотренные товары | Пользователю будет предоставлен список из трёх раннее просмотренных им товаров. |

### 3.2 Нефункциональные требования <a name="3.2"></a>
#### 3.2.1 Атрибуты качества <a name="3.2.1"></a>
Приложение будет предоставлять мгновенный доступ к продуктам особо не расходуя ресурсы устройств. Также важным атрибутом является отсутствие необходимости регистрации при совершеннии каких либо действий в приложении.
##### 3.2.1.1 Требования к удобству использования <a name="3.2.1.1"></a>
1. Все функциональные элементы пользовательского интерфейса являются простыми и понятными. 
2. Элементы товаров содержат ссылки на соответствующие товары;
##### 3.2.1.2 Требования к безопасности <a name="3.2.1.2"></a>
1. Все действия в веб-приложении должны осуществляться без требования личных данных пользователя.
2. При оформлении заказа необходимые данные должны храниться в базе данных, доступной только менеджменту приложения.
#### 3.2.2 Ограничения <a name="3.2.2"></a>
* Приложение реализовано на языке Java.
* Приложение доступно для браузера GoogleChrome версии не ниже 3.29, для остальных браузеров ограничений нет.
### 4. Аналоги <a name="4"></a>
Данный проект является упрощенным вариантом [TimesShop.by](https://timeshop.by/) и [tempus](https://www.tempus.by)