package com.example.crosswalkdemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by yinzhiyu on 2018-3-30.
 */

public class TreeBean  implements Parcelable{

    /**
     * code : 200
     * msg : 操作成功
     * data : {"node_list":{"info":[{"tf_id":"1","tf_pid":"0","tf_projectid":"634","tf_catelog":"阿萨德","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[{"tf_id":"4","tf_pid":"1","tf_projectid":"634","tf_catelog":"asdasd","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[{"tf_id":"12","tf_pid":"4","tf_projectid":"634","tf_catelog":"ceshi","tf_href":"www.baidu.com","tf_content":"ada","tf_sort":"1","children":[]},{"tf_id":"13","tf_pid":"4","tf_projectid":"634","tf_catelog":"ces","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[]}]},{"tf_id":"5","tf_pid":"1","tf_projectid":"634","tf_catelog":"asd","tf_href":"www.baid.com","tf_content":"0","tf_sort":"1","children":[]},{"tf_id":"6","tf_pid":"1","tf_projectid":"634","tf_catelog":"asd","tf_href":"www.asds.com","tf_content":"0","tf_sort":"1","children":[]}]},{"tf_id":"2","tf_pid":"0","tf_projectid":"634","tf_catelog":"a","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"2","children":[{"tf_id":"7","tf_pid":"2","tf_projectid":"634","tf_catelog":"asdas","tf_href":"www.abdia.com","tf_content":"0","tf_sort":"1","children":[]}]},{"tf_id":"3","tf_pid":"0","tf_projectid":"634","tf_catelog":"b","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"2","children":[{"tf_id":"8","tf_pid":"3","tf_projectid":"634","tf_catelog":"哎呀","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"2","children":[]}]},{"tf_id":"9","tf_pid":"0","tf_projectid":"634","tf_catelog":"测试节点","tf_href":"www.baidu.com","tf_content":"","tf_sort":"2","children":[{"tf_id":"10","tf_pid":"9","tf_projectid":"634","tf_catelog":"测试节点儿子","tf_href":"www.baidu.com","tf_content":"啊实打实","tf_sort":"1","children":[]}]}]}}
     */

    private String code;
    private String msg;
    private DataBean data;

    protected TreeBean(Parcel in) {
        code = in.readString();
        msg = in.readString();
    }

    public static final Creator<TreeBean> CREATOR = new Creator<TreeBean>() {
        @Override
        public TreeBean createFromParcel(Parcel in) {
            return new TreeBean(in);
        }

        @Override
        public TreeBean[] newArray(int size) {
            return new TreeBean[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(msg);
    }

    public static class DataBean implements Parcelable{
        /**
         * node_list : {"info":[{"tf_id":"1","tf_pid":"0","tf_projectid":"634","tf_catelog":"阿萨德","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[{"tf_id":"4","tf_pid":"1","tf_projectid":"634","tf_catelog":"asdasd","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[{"tf_id":"12","tf_pid":"4","tf_projectid":"634","tf_catelog":"ceshi","tf_href":"www.baidu.com","tf_content":"ada","tf_sort":"1","children":[]},{"tf_id":"13","tf_pid":"4","tf_projectid":"634","tf_catelog":"ces","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[]}]},{"tf_id":"5","tf_pid":"1","tf_projectid":"634","tf_catelog":"asd","tf_href":"www.baid.com","tf_content":"0","tf_sort":"1","children":[]},{"tf_id":"6","tf_pid":"1","tf_projectid":"634","tf_catelog":"asd","tf_href":"www.asds.com","tf_content":"0","tf_sort":"1","children":[]}]},{"tf_id":"2","tf_pid":"0","tf_projectid":"634","tf_catelog":"a","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"2","children":[{"tf_id":"7","tf_pid":"2","tf_projectid":"634","tf_catelog":"asdas","tf_href":"www.abdia.com","tf_content":"0","tf_sort":"1","children":[]}]},{"tf_id":"3","tf_pid":"0","tf_projectid":"634","tf_catelog":"b","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"2","children":[{"tf_id":"8","tf_pid":"3","tf_projectid":"634","tf_catelog":"哎呀","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"2","children":[]}]},{"tf_id":"9","tf_pid":"0","tf_projectid":"634","tf_catelog":"测试节点","tf_href":"www.baidu.com","tf_content":"","tf_sort":"2","children":[{"tf_id":"10","tf_pid":"9","tf_projectid":"634","tf_catelog":"测试节点儿子","tf_href":"www.baidu.com","tf_content":"啊实打实","tf_sort":"1","children":[]}]}]}
         */

        private NodeListBean node_list;

        protected DataBean(Parcel in) {
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public NodeListBean getNode_list() {
            return node_list;
        }

        public void setNode_list(NodeListBean node_list) {
            this.node_list = node_list;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }

        public static class NodeListBean {
            private List<InfoBean> info;

            public List<InfoBean> getInfo() {
                return info;
            }

            public void setInfo(List<InfoBean> info) {
                this.info = info;
            }

            public static class InfoBean implements Parcelable{
                /**
                 * tf_id : 1
                 * tf_pid : 0
                 * tf_projectid : 634
                 * tf_catelog : 阿萨德
                 * tf_href : www.baidu.com
                 * tf_content : 0
                 * tf_sort : 1
                 * children : [{"tf_id":"4","tf_pid":"1","tf_projectid":"634","tf_catelog":"asdasd","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[{"tf_id":"12","tf_pid":"4","tf_projectid":"634","tf_catelog":"ceshi","tf_href":"www.baidu.com","tf_content":"ada","tf_sort":"1","children":[]},{"tf_id":"13","tf_pid":"4","tf_projectid":"634","tf_catelog":"ces","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[]}]},{"tf_id":"5","tf_pid":"1","tf_projectid":"634","tf_catelog":"asd","tf_href":"www.baid.com","tf_content":"0","tf_sort":"1","children":[]},{"tf_id":"6","tf_pid":"1","tf_projectid":"634","tf_catelog":"asd","tf_href":"www.asds.com","tf_content":"0","tf_sort":"1","children":[]}]
                 */

                private String tf_id;
                private String tf_pid;
                private String tf_projectid;
                private String tf_catelog;
                private String tf_href;
                private String tf_content;
                private String tf_sort;
                private List<ChildrenBeanX> children;

                protected InfoBean(Parcel in) {
                    tf_id = in.readString();
                    tf_pid = in.readString();
                    tf_projectid = in.readString();
                    tf_catelog = in.readString();
                    tf_href = in.readString();
                    tf_content = in.readString();
                    tf_sort = in.readString();
                }

                public static final Creator<InfoBean> CREATOR = new Creator<InfoBean>() {
                    @Override
                    public InfoBean createFromParcel(Parcel in) {
                        return new InfoBean(in);
                    }

                    @Override
                    public InfoBean[] newArray(int size) {
                        return new InfoBean[size];
                    }
                };

                public InfoBean() {

                }

                public String getTf_id() {
                    return tf_id;
                }

                public void setTf_id(String tf_id) {
                    this.tf_id = tf_id;
                }

                public String getTf_pid() {
                    return tf_pid;
                }

                public void setTf_pid(String tf_pid) {
                    this.tf_pid = tf_pid;
                }

                public String getTf_projectid() {
                    return tf_projectid;
                }

                public void setTf_projectid(String tf_projectid) {
                    this.tf_projectid = tf_projectid;
                }

                public String getTf_catelog() {
                    return tf_catelog;
                }

                public void setTf_catelog(String tf_catelog) {
                    this.tf_catelog = tf_catelog;
                }

                public String getTf_href() {
                    return tf_href;
                }

                public void setTf_href(String tf_href) {
                    this.tf_href = tf_href;
                }

                public String getTf_content() {
                    return tf_content;
                }

                public void setTf_content(String tf_content) {
                    this.tf_content = tf_content;
                }

                public String getTf_sort() {
                    return tf_sort;
                }

                public void setTf_sort(String tf_sort) {
                    this.tf_sort = tf_sort;
                }

                public List<ChildrenBeanX> getChildren() {
                    return children;
                }

                public void setChildren(List<ChildrenBeanX> children) {
                    this.children = children;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(tf_id);
                    dest.writeString(tf_pid);
                    dest.writeString(tf_projectid);
                    dest.writeString(tf_catelog);
                    dest.writeString(tf_href);
                    dest.writeString(tf_content);
                    dest.writeString(tf_sort);
                }

                public static class ChildrenBeanX implements Parcelable{
                    /**
                     * tf_id : 4
                     * tf_pid : 1
                     * tf_projectid : 634
                     * tf_catelog : asdasd
                     * tf_href : www.baidu.com
                     * tf_content : 0
                     * tf_sort : 1
                     * children : [{"tf_id":"12","tf_pid":"4","tf_projectid":"634","tf_catelog":"ceshi","tf_href":"www.baidu.com","tf_content":"ada","tf_sort":"1","children":[]},{"tf_id":"13","tf_pid":"4","tf_projectid":"634","tf_catelog":"ces","tf_href":"www.baidu.com","tf_content":"0","tf_sort":"1","children":[]}]
                     */

                    private String tf_id;
                    private String tf_pid;
                    private String tf_projectid;
                    private String tf_catelog;
                    private String tf_href;
                    private String tf_content;
                    private String tf_sort;
                    private List<ChildrenBean> children;

                    protected ChildrenBeanX(Parcel in) {
                        tf_id = in.readString();
                        tf_pid = in.readString();
                        tf_projectid = in.readString();
                        tf_catelog = in.readString();
                        tf_href = in.readString();
                        tf_content = in.readString();
                        tf_sort = in.readString();
                    }

                    public static final Creator<ChildrenBeanX> CREATOR = new Creator<ChildrenBeanX>() {
                        @Override
                        public ChildrenBeanX createFromParcel(Parcel in) {
                            return new ChildrenBeanX(in);
                        }

                        @Override
                        public ChildrenBeanX[] newArray(int size) {
                            return new ChildrenBeanX[size];
                        }
                    };

                    public String getTf_id() {
                        return tf_id;
                    }

                    public void setTf_id(String tf_id) {
                        this.tf_id = tf_id;
                    }

                    public String getTf_pid() {
                        return tf_pid;
                    }

                    public void setTf_pid(String tf_pid) {
                        this.tf_pid = tf_pid;
                    }

                    public String getTf_projectid() {
                        return tf_projectid;
                    }

                    public void setTf_projectid(String tf_projectid) {
                        this.tf_projectid = tf_projectid;
                    }

                    public String getTf_catelog() {
                        return tf_catelog;
                    }

                    public void setTf_catelog(String tf_catelog) {
                        this.tf_catelog = tf_catelog;
                    }

                    public String getTf_href() {
                        return tf_href;
                    }

                    public void setTf_href(String tf_href) {
                        this.tf_href = tf_href;
                    }

                    public String getTf_content() {
                        return tf_content;
                    }

                    public void setTf_content(String tf_content) {
                        this.tf_content = tf_content;
                    }

                    public String getTf_sort() {
                        return tf_sort;
                    }

                    public void setTf_sort(String tf_sort) {
                        this.tf_sort = tf_sort;
                    }

                    public List<ChildrenBean> getChildren() {
                        return children;
                    }

                    public void setChildren(List<ChildrenBean> children) {
                        this.children = children;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(tf_id);
                        dest.writeString(tf_pid);
                        dest.writeString(tf_projectid);
                        dest.writeString(tf_catelog);
                        dest.writeString(tf_href);
                        dest.writeString(tf_content);
                        dest.writeString(tf_sort);
                    }

                    public static class ChildrenBean implements Parcelable{
                        /**
                         * tf_id : 12
                         * tf_pid : 4
                         * tf_projectid : 634
                         * tf_catelog : ceshi
                         * tf_href : www.baidu.com
                         * tf_content : ada
                         * tf_sort : 1
                         * children : []
                         */

                        private String tf_id;
                        private String tf_pid;
                        private String tf_projectid;
                        private String tf_catelog;
                        private String tf_href;
                        private String tf_content;
                        private String tf_sort;
                        private List<?> children;

                        protected ChildrenBean(Parcel in) {
                            tf_id = in.readString();
                            tf_pid = in.readString();
                            tf_projectid = in.readString();
                            tf_catelog = in.readString();
                            tf_href = in.readString();
                            tf_content = in.readString();
                            tf_sort = in.readString();
                        }

                        public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
                            @Override
                            public ChildrenBean createFromParcel(Parcel in) {
                                return new ChildrenBean(in);
                            }

                            @Override
                            public ChildrenBean[] newArray(int size) {
                                return new ChildrenBean[size];
                            }
                        };

                        public String getTf_id() {
                            return tf_id;
                        }

                        public void setTf_id(String tf_id) {
                            this.tf_id = tf_id;
                        }

                        public String getTf_pid() {
                            return tf_pid;
                        }

                        public void setTf_pid(String tf_pid) {
                            this.tf_pid = tf_pid;
                        }

                        public String getTf_projectid() {
                            return tf_projectid;
                        }

                        public void setTf_projectid(String tf_projectid) {
                            this.tf_projectid = tf_projectid;
                        }

                        public String getTf_catelog() {
                            return tf_catelog;
                        }

                        public void setTf_catelog(String tf_catelog) {
                            this.tf_catelog = tf_catelog;
                        }

                        public String getTf_href() {
                            return tf_href;
                        }

                        public void setTf_href(String tf_href) {
                            this.tf_href = tf_href;
                        }

                        public String getTf_content() {
                            return tf_content;
                        }

                        public void setTf_content(String tf_content) {
                            this.tf_content = tf_content;
                        }

                        public String getTf_sort() {
                            return tf_sort;
                        }

                        public void setTf_sort(String tf_sort) {
                            this.tf_sort = tf_sort;
                        }

                        public List<?> getChildren() {
                            return children;
                        }

                        public void setChildren(List<?> children) {
                            this.children = children;
                        }

                        @Override
                        public int describeContents() {
                            return 0;
                        }

                        @Override
                        public void writeToParcel(Parcel dest, int flags) {
                            dest.writeString(tf_id);
                            dest.writeString(tf_pid);
                            dest.writeString(tf_projectid);
                            dest.writeString(tf_catelog);
                            dest.writeString(tf_href);
                            dest.writeString(tf_content);
                            dest.writeString(tf_sort);
                        }
                    }
                }
            }
        }
    }
}
