package factories;

import model.*;

public abstract class AbstractModelFactory {
    public abstract ArticleLeaveModel getArticleLeaveModel();
    public abstract ArticleModel getArticleModel();
    public abstract ArticleTypeModel getArticleTypeModel();
    public abstract CourierModel getCourierModel();
    public abstract EntranceArticleModel getEntranceArticleModel();
    public abstract EntranceModel getEntranceModel();
    public abstract FulfillmentModel getFulfillmentModel();
    public abstract LeaveModel getLeaveModel();
    public abstract OrderModel getOrderModel();
    public abstract OrderTypeArticleModel getOrderTypeArticleModel();
    public abstract PositionModel getPositionModel();
    public abstract SportModel getSportModel();
    public abstract StoreModel getStoreModel();
    public abstract UserModel getUserModel();
}
