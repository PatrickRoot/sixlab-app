package cn.sixlab.app.sixlabapp.util

import java.text.SimpleDateFormat

/**
 * Created by patrick on 2017/7/4.
 */
object DateUtil{
    var dateFormat = SimpleDateFormat("yyyy-MM-dd")
    var timeFormat = SimpleDateFormat("HH:mm:ss")
    var dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @JvmStatic
    fun formatDate(dateStr: String?): String {
        //        Date date = new Date(Long.valueOf(dateStr));
        //        return Util.dateFormat.format(date);
        if (null != dateStr && "" != dateStr) {
            return dateStr.substring(0, 10)
        } else {
            return ""
        }
    }
}