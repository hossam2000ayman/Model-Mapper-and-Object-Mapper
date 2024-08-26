package com.example.testmapper.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UserMixin {

    //when we try to make field of UserMixin not matches with User's field it will cause to get the Jackson Properties from the User class not from the UserMixin
    @JsonProperty(value = "first_name")
    private String name;
    //otherwise when we make fields of the UserMixin matches with User class it will show like on UserMixin Jackson Properties
    @JsonProperty(value = "last_name")
    private String lastName;
    @JsonProperty(value = "email_address")
    private String email;
    @JsonIgnore
    private String password;
}
