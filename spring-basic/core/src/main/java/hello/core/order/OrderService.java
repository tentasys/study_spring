package hello.core.order;

public interface OrderService {
    //회원id, 상품명, 가격을 넣으면 주문 결과를 반환한다.
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
