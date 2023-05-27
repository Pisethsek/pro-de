package com.sekpiseth.api.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Integer id;
    private String name;
    private Date created;
    private Boolean status;
}
