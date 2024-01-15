package com.thrivefuse.ums.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailInfo {

    private List<String> toMailAddress;

    private String subject;

    private String message;

    private String attachment;

}
