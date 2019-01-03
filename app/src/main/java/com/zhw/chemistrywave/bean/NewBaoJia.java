package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2017/11/17.
 */

public class NewBaoJia {


    /**
     * msg : success
     * code : 0
     * data : [{"en_id":10,"goods_id":61,"buyer_id":31,"com_name":"华新有限公司","com_addr":"小白楼","com_contacts":"赵家兴","com_number":"18330695790","com_mailbox":"40055251@126.com","postscript":"你走吧","create_time":"2018-05-08 16:42:52","tar_status":"1","zh_pos":null,"goods_name":"Clorofene","user_photo":"upload/31/20180510161433703.jpg"},{"en_id":9,"goods_id":61,"buyer_id":31,"com_name":"小猪佩奇有限公司","com_addr":"滨江道","com_contacts":"熊先生","com_number":"18330695712","com_mailbox":"102755251@126.com","postscript":"社会人","create_time":"2018-05-08 16:21:27","tar_status":"1","zh_pos":null,"goods_name":"Clorofene","user_photo":"upload/31/20180510161433703.jpg"},{"en_id":4,"goods_id":29,"buyer_id":22,"com_name":null,"com_addr":null,"com_contacts":null,"com_number":null,"com_mailbox":null,"postscript":"1231231231232","create_time":"2018-03-31 10:34:22","tar_status":"1","zh_pos":null,"goods_name":" propane-1,2-diol","user_photo":null},{"en_id":1,"goods_id":7,"buyer_id":1,"com_name":"12","com_addr":"123","com_contacts":"123","com_number":"123","com_mailbox":"123","postscript":"123","create_time":"2018-03-01 10:34:22","tar_status":"1","zh_pos":null,"goods_name":"Nandrolone Decanoate","user_photo":"upload/1/20180228115357425.jpg"}]
     * count : 4
     */

    private String msg;
    private int code;
    private int count;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * en_id : 10
         * goods_id : 61
         * buyer_id : 31
         * com_name : 华新有限公司
         * com_addr : 小白楼
         * com_contacts : 赵家兴
         * com_number : 18330695790
         * com_mailbox : 40055251@126.com
         * postscript : 你走吧
         * create_time : 2018-05-08 16:42:52
         * tar_status : 1
         * zh_pos : null
         * goods_name : Clorofene
         * user_photo : upload/31/20180510161433703.jpg
         */

        private int en_id;
        private int goods_id;
        private int buyer_id;
        private String com_name;
        private String com_addr;
        private String com_contacts;
        private String com_number;
        private String com_mailbox;
        private String postscript;
        private String create_time;
        private String tar_status;
        private Object zh_pos;
        private String goods_name;
        private String user_photo;

        public int getEn_id() {
            return en_id;
        }

        public void setEn_id(int en_id) {
            this.en_id = en_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(int buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getCom_name() {
            return com_name;
        }

        public void setCom_name(String com_name) {
            this.com_name = com_name;
        }

        public String getCom_addr() {
            return com_addr;
        }

        public void setCom_addr(String com_addr) {
            this.com_addr = com_addr;
        }

        public String getCom_contacts() {
            return com_contacts;
        }

        public void setCom_contacts(String com_contacts) {
            this.com_contacts = com_contacts;
        }

        public String getCom_number() {
            return com_number;
        }

        public void setCom_number(String com_number) {
            this.com_number = com_number;
        }

        public String getCom_mailbox() {
            return com_mailbox;
        }

        public void setCom_mailbox(String com_mailbox) {
            this.com_mailbox = com_mailbox;
        }

        public String getPostscript() {
            return postscript;
        }

        public void setPostscript(String postscript) {
            this.postscript = postscript;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTar_status() {
            return tar_status;
        }

        public void setTar_status(String tar_status) {
            this.tar_status = tar_status;
        }

        public Object getZh_pos() {
            return zh_pos;
        }

        public void setZh_pos(Object zh_pos) {
            this.zh_pos = zh_pos;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }
    }
}
