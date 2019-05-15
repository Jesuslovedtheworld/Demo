package com.baidu.fileb.bean;

import java.util.List;

public class SenrceBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2019-01-24","title":"北京牌照车\u201c霸路\u201d  连打两地8个电话都没挪了\u2026\u2026网友：这事归谁管？","description":"每日新报","picUrl":"http://mmbiz.qpic.cn/mmbiz_jpg/qOU8DibVOShcUfr6MH1U0ZUFo3rFls67Qf2VDJsxW0xpXtn2NYyqYgeUuZmYlxMnUzWOLzricVszRUr7Zc7LGRlw/0?wx_fmt=jpeg","url":"https://mp.weixin.qq.com/s?src=11&timestamp=1548331208&ver=1386&signature=dnq9RddHDydaxGar7TiFlUit8zLERmLTwTOM*mPBfKRD4kFJLc*GvUEEwSQ6DAP6dKxLIi1GOmSR*TegxtTU8M4lPan3AYM*lu5SkLafoCBKhbKmeSk7LKyli6xNUf-V&new=1"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2019-01-24
         * title : 北京牌照车“霸路”  连打两地8个电话都没挪了……网友：这事归谁管？
         * description : 每日新报
         * picUrl : http://mmbiz.qpic.cn/mmbiz_jpg/qOU8DibVOShcUfr6MH1U0ZUFo3rFls67Qf2VDJsxW0xpXtn2NYyqYgeUuZmYlxMnUzWOLzricVszRUr7Zc7LGRlw/0?wx_fmt=jpeg
         * url : https://mp.weixin.qq.com/s?src=11&timestamp=1548331208&ver=1386&signature=dnq9RddHDydaxGar7TiFlUit8zLERmLTwTOM*mPBfKRD4kFJLc*GvUEEwSQ6DAP6dKxLIi1GOmSR*TegxtTU8M4lPan3AYM*lu5SkLafoCBKhbKmeSk7LKyli6xNUf-V&new=1
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
