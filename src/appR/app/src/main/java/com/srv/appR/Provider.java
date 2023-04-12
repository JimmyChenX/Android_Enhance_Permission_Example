package com.srv.appR;

import android.net.Uri;
import android.provider.BaseColumns;

public class Provider{

    public static final String AUTHORITY = "com.srv.sourceprovider";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.android.sourceprovider";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.android.sourceprovider";

    public static final class Person implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY +"/persons");
        public static final String TABLE_NAME = "table_person";
        public static final String DEFAULT_SORT_ORDER = "age desc";

        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String AGE = "age";
    }
}