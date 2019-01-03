package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by zhw on 2018/5/28.
 */

public class MyService {


    /**
     * msg : success
     * code : 0
     * data : {"totalCount":1,"list":[{"five_id":22,"user_id":53,"five_type":"ser3","com_name":"1","com_addr":"1","contact":"1","contact_phone":"1","mailbox":"1","create_time":"2018-05-28 20:48:50","appl_status":"-1","postscript":"攻击力"}],"pageSize":1000,"currPage":1,"totalPage":1}
     */

    private String msg;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalCount : 1
         * list : [{"five_id":22,"user_id":53,"five_type":"ser3","com_name":"1","com_addr":"1","contact":"1","contact_phone":"1","mailbox":"1","create_time":"2018-05-28 20:48:50","appl_status":"-1","postscript":"攻击力"}]
         * pageSize : 1000
         * currPage : 1
         * totalPage : 1
         */

        private int totalCount;
        private int pageSize;
        private int currPage;
        private int totalPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * five_id : 22
             * user_id : 53
             * five_type : ser3
             * com_name : 1
             * com_addr : 1
             * contact : 1
             * contact_phone : 1
             * mailbox : 1
             * create_time : 2018-05-28 20:48:50
             * appl_status : -1
             * postscript : 攻击力
             */

            private int five_id;
            private int user_id;
            private String five_type;
            private String com_name;
            private String com_addr;
            private String contact;
            private String contact_phone;
            private String mailbox;
            private String create_time;
            private String appl_status;
            private String postscript;

            public int getFive_id() {
                return five_id;
            }

            public void setFive_id(int five_id) {
                this.five_id = five_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getFive_type() {
                return five_type;
            }

            public void setFive_type(String five_type) {
                this.five_type = five_type;
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

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getContact_phone() {
                return contact_phone;
            }

            public void setContact_phone(String contact_phone) {
                this.contact_phone = contact_phone;
            }

            public String getMailbox() {
                return mailbox;
            }

            public void setMailbox(String mailbox) {
                this.mailbox = mailbox;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getAppl_status() {
                return appl_status;
            }

            public void setAppl_status(String appl_status) {
                this.appl_status = appl_status;
            }

            public String getPostscript() {
                return postscript;
            }

            public void setPostscript(String postscript) {
                this.postscript = postscript;
            }
        }
    }
}
