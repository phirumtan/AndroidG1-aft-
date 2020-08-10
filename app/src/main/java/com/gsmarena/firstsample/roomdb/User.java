package com.gsmarena.firstsample.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo
    public String gender;

    public User(int uid, String firstName, String lastName, String gender) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    @Ignore
    public User(String firstName, String lastName) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
