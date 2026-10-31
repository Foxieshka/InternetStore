# Исходный код диаграммы классов Plant UML
```
@startuml
enum Category{
PET
FOOD
CLOTHING
ENTERTAINMENT
OTHER
}

class Product{
- {static} counter: int
- article: int
# name: String
# price: double
# quantity: int
# category: Category
+ getArticle(): int
+ getName(): String
+ getPrice(): int
+ getQuantity(): int
+ getCategory(): Category
+ setPrice(price: int): void
+ setQuantity(quantity: int): void
+ decreaseQuantity(delta: int): void
+ {abstract} getInfo(): String
  }

Enum Sizes{
XS
S
M
L
XL
}

class Clothing{
- color: String
- size: Sizes
+ getInfo(): String
  }

class Food{
- expirationDate: LocalDate
- components: String
- weight: double
+ getInfo(): String
  }

Enum Animal{
CAT
DOG
FISH
BIRD
RODENTS
}

class Pet{
- animal: Animal
+ getInfo(): String
  }

class Customer{
- {static} counter: int
- id: int
- name: String
- surname: String
- email: String
- password: String
- balance: double
- cart: Cart
- orders: List<Orders>
+ getId(): int
+ getEmail(): String
+ getCart(): Cart
+ getOrders(): List<Order>
+ getName(): String
+ getSurname(): String
+ getBalance(): double
+ setBalance(balance: double): void
+ setName(name: String): void
+ setSurname(surname: String): void
+ setEmail(email: String): void
+ addToCart(product: Product, quantity: int): void
+ removeFromCart(productName: String): void
+ buyAll(paymentSystem: PaymentSystem): Order
  }

class Cart{
- items: Map<String, CartItem>
+ getItems(): Map<String, CartItem>
+ add(product: Product, quantity: int): void
+ remove(productName: String): void
+ isEmpty(): boolean
+ getTotal(): int
+ clear(): void
  }

class CartItem{
- quantity: int
- product: Product
+ getTotalPrice(): double
+ getQuantity(): int
+ getProduct(): Product
  }

class Store{
- user: Customer
- products: List<Product>
- promenades: Map<String, Promocode>
+ getUser(): Customer
+ getProducts(): List<Product>
+ getPromenades(): Map<String, Promocode>
+ findProduct(productName: String): Product
+ seedData(): void
+ removeFromCart(productName: String): void
+ addToCart(productName: String): void
+ buyAll(paymentSystem: PaymentSystem): Order
+ findPromocode(code: String): Promocode
  }

enum PaymentStatus{
UPON_RECEIVING
PAID
NOT_PAID
}

enum OrderStatus{
ASSEMBLED
IN_PROGRESS
DELIVERED
COMPLETED
DELAYED
}

class Promocode{
- code: String
- percent: int
- used: boolean
+ getCode(): String
+ getPercent(): int
+ getUsed(): boolean
+ setCode(code: String): void
+ setPercent(percent: int): void
+ setUsed(used: boolean): void
+ getInfo(): String
  }

class Order{
- id: int
- {static} counter: int
- paymentStatus: PaymentStatus
- status: OrderStatus
- address: String
- orderItems: ArrayList<OrderItem>
- paymentSystem: PaymentSystem
- total: double
- finalTotal: double
- promocode: Promocode
+ getId(): int
+ getPaymentStatus(): PaymentStatus
+ getStatus(): OrderStatus
+ getAddress(): String
+ getTotal(): double
+ getFinalTotal(): double
+ getStatus(): OrderStatus
+ getPaymentSystem(): PaymentSystem
+ getOrderItems(): List<OrderItem>
+ setPaymentStatus(paymentStatus: PaymentStatus)
+ setStatus(status: OrderStatus)
+ setAddress(address: String)
  }

class OrderItem{
- quantity: int
- product: Product
+ getTotalPrice(): double
+ getQuantity(): int
+ getProduct(): Product
  }

interface PaymentSystem{
+ {abstract} pay(amount: double): boolean
+ {abstract} getName(): String
  }

class CardPayment{
+ pay(amount: double): boolean
+ getName(): String
  }

class CashPayment{
+ pay(amount: double): boolean
+ getName(): String
  }

class BalancePayment{
+ pay(amount: double): boolean
+ getName(): String
  }

class Menu{
- scanner: Scanner
+ readCommand(): String
+ readString(prompt: String): String
+ readInt(prompt: String): int
+ printHelp(): void
+ printError(msg: String): void
+ printCatalog(store: Store): void
+ printCart(cart: Cart): void
+ printOrders(store: Store): void
+ choosePaymentSystem(): int
+ printOrderInfo(order: Order): void
  }

Product <|-- Clothing
Product <|-- Food
Product <|-- Pet
Product --> Category
Clothing --> Sizes
Pet --> Animal

PaymentSystem <|.. CashPayment
PaymentSystem <|..  CardPayment
PaymentSystem <|..  BalancePayment

Order --> PaymentStatus
Order --> OrderStatus
OrderItem "1"--*"1..*" Order
Order "1"-->"0..*" PaymentSystem
Order "0..1"-->"0..*" Promocode

Customer "1"-->"1" Cart
Customer "1"-->"0..*" Order : оформлен
Cart "1"-->"0..*" CartItem : содержит
CartItem "1"-->"0..*" Product
OrderItem "1"-->"0..*" Product

Store "1"-->"1" Customer
Store "1"-->"0..*" Product
Store "1"-->"0..*" Promocode
@enduml
```