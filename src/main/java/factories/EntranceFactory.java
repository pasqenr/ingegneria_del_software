package factories;

import model.*;

public class EntranceFactory extends AbstractModelFactory {
    private static final EntranceModel entranceModel = new EntranceModel(0, null);

    @Override
    public ArticleLeaveModel getArticleLeaveModel() {
        return null;
    }

    @Override
    public ArticleModel getArticleModel() {
        return null;
    }

    @Override
    public ArticleTypeModel getArticleTypeModel() {
        return null;
    }

    @Override
    public CourierModel getCourierModel() {
        return null;
    }

    @Override
    public EntranceArticleModel getEntranceArticleModel() {
        return null;
    }

    @Override
    public EntranceModel getEntranceModel() {
        return entranceModel;
    }

    @Override
    public FulfillmentModel getFulfillmentModel() {
        return null;
    }

    @Override
    public LeaveModel getLeaveModel() {
        return null;
    }

    @Override
    public OrderModel getOrderModel() {
        return null;
    }

    @Override
    public OrderTypeArticleModel getOrderTypeArticleModel() {
        return null;
    }

    @Override
    public PositionModel getPositionModel() {
        return null;
    }

    @Override
    public SportModel getSportModel() {
        return null;
    }

    @Override
    public StoreModel getStoreModel() {
        return null;
    }

    @Override
    public UserModel getUserModel() {
        return null;
    }
}
