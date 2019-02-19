package factories;

public class FactoryProducer {
    public enum FactoryType {
        ARTICLE, ARTICLE_LEAVE, ARTICLE_TYPE, COURIER, ENTRANCE_ARTICLE, ENTRANCE, FULFILLMENT, LEAVE, ORDER,
        ORDER_TYPE_ARTICLE, POSITION, SPORT, STORE, USER
    }

    public static AbstractModelFactory getFactory(final FactoryType type) {
        switch (type) {
            case ARTICLE:
                return new ArticleFactory();
            case ARTICLE_LEAVE:
                return new ArticleLeaveFactory();
            case ARTICLE_TYPE:
                return new ArticleTypeFactory();
            case COURIER:
                return new CourierFactory();
            case ENTRANCE_ARTICLE:
                return new EntranceArticleFactory();
            case ENTRANCE:
                return new EntranceFactory();
            case FULFILLMENT:
                return new FulfillmentFactory();
            case LEAVE:
                return new LeaveFactory();
            case ORDER:
                return new OrderFactory();
            case ORDER_TYPE_ARTICLE:
                return new OrderTypeArticleFactory();
            case POSITION:
                return new PositionFactory();
            case SPORT:
                return new SportFactory();
            case STORE:
                return new StoreFactory();
            case USER:
                return new UserFactory();
                default:
                    throw new IllegalArgumentException("Factory not found");
        }
    }
}
