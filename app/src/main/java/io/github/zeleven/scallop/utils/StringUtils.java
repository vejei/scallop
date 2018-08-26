package io.github.zeleven.scallop.utils;

import android.text.format.DateUtils;

import org.jsoup.Jsoup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils {
    public static CharSequence getRelativeTime(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'S'Z'",
                Locale.CHINESE);
        try {
            Date date = dateFormat.parse(dateStr);
            long now = System.currentTimeMillis();
            return DateUtils.getRelativeTimeSpanString(date.getTime(), now, DateUtils.SECOND_IN_MILLIS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "N/A";
    }

    public static String stripHtml(String html) {
        return Jsoup.parse(html).text();
    }

    public static String getImageNameFromUrl(String imageUrl) {
        String[] parts = imageUrl.split("/");
        return parts[parts.length - 1];
    }

    public static boolean isEmpty(String text) {
        return text == null || isWhiteSpaces(text);
    }

    private static boolean isWhiteSpaces(String s) {
        return s != null && s.matches("\\s+");
    }
}
