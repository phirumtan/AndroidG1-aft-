package com.gsmarena.firstsample.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "user", primaryKeys = {"first_name", "last_name"})
public class User {

    @NonNull
    @ColumnInfo(name = "first_name")
    public String firstName;


    @NonNull
    @ColumnInfo(name = "last_name")
    public String lastName;

    @Ignore
    public User() {
        super();
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
