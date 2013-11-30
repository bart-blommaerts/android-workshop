package be.hp.workshop.data.ddl_dml;

import android.provider.BaseColumns;

/**
 * Created by bart on 11/20/13.
 */
public class BrownBagContract implements BaseColumns {

    public static final String TABLE_NAME = "brownbag";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_CONTENT = "content";
    public static final String COLUMN_NAME_IMAGE_ID = "image_id";
    public static final String COLUMN_NAME_IMAGE_URL = "image_url";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_CONTENT + " TEXT," +
                    COLUMN_NAME_IMAGE_ID + " TEXT," +
                    COLUMN_NAME_IMAGE_URL + " TEXT" +
                    " )";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String SQL_INSERT_ROWS =
            "INSERT INTO " + TABLE_NAME + " " +
                    "(" +
                    COLUMN_NAME_TITLE + "," +
                    COLUMN_NAME_CONTENT + "," +
                    COLUMN_NAME_IMAGE_ID  +
                    ") VALUES " +
                    "( \"1. Continuous Integration\", \"Continuous integration (CI) is the practice, in software engineering, of merging all developer working copies with a shared mainline several times a day. It was first named and proposed as part of extreme programming (XP). Its main aim is to prevent integration problems, referred to as integration hell in early descriptions of XP.\",0  )" +
                    ",( \"2. ES Service Platform\", \"Accelerate the creation, evolution and operation of high quality application services through a reusable cloud platform.\", 1 )"+
                    ",( \"3. Testing\", \"Software testing is an investigation conducted to provide stakeholders with information about the quality of the product or service under test.\", 2 )"+
                    ",( \"4. Rich Web Clients / HTML5\", \"An RWC is a web-technology based application. It is also called a single page web application. It is built using html, javascript, css, JSON\", 3 )"+
                    ",( \"5. Android Workshop\", \"Android is an operating system based on the Linux kernel, and designed primarily for touchscreen mobile devices such as smartphones and tablet computers.\", 4 )";

    public BrownBagContract() {}

}
