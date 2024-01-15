package com.thrivefuse.ums.dto;

import lombok.Data;

@Data
public class EmailInfo {

    private String toAddress;

    private String subject;

    private String textMessage;

    private String attachment;

}
