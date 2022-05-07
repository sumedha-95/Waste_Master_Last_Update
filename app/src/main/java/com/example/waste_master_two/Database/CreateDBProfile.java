package com.example.waste_master_two.Database;

import android.provider.BaseColumns;

public final class CreateDBProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CreateDBProfile() {}

    /* Inner class that defines the table contents */
    public static class CreateUsers implements BaseColumns {
        public static final String TABLE_NAME = "CreateUserInfo";
        public static final String COLUMN_1 = "city";
        public static final String COLUMN_2 = "lordtype";
        public static final String COLUMN_3 = "cleaningperiod";
        public static final String COLUMN_4 = "location";
    }
}
