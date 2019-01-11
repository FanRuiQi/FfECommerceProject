package com.tech.wd.ffecommerceproject.bean;

import java.util.List;

public class ShowShopCatBean {


    /**
     * result : [{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":5,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 6
         * commodityName : 轻柔系自然裸妆假睫毛
         * count : 5
         * pic : http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg
         * price : 39
         */

        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;
        private int selected =0;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSelected() {
            return selected;
        }

        public void setSelected(int selected) {
            this.selected = selected;
        }
    }
}
