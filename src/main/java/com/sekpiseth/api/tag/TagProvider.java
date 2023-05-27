package com.sekpiseth.api.tag;

import org.apache.ibatis.jdbc.SQL;

public class TagProvider {

    public String buildSelectSql(){
        return new SQL(){{
           SELECT("*");
           FROM("tags");
           WHERE("status = FALSE");
        }}.toString();
    }

    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM("tags");
            WHERE("id = #{id}", "status = FALSE");
        }}.toString();
    }

    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO("tags");
            VALUES("name", "#{tag.name}");
            VALUES("created", "#{tag.created}");
            VALUES("status", "#{tag.status}");
        }}.toString();
    }

    public String buildUpdateSql(){
        return new SQL(){{
            UPDATE("tags");
            SET("name = #{tag.name}");
            WHERE("id = #{tag.id}");
        }}.toString();
    }

    public String buildDeleteSql(){
        return new SQL(){{
            DELETE_FROM("tags");
            WHERE("id =#{id}");
        }}.toString();
    }

    public String buildDeleteByStatusSql(){
        return new SQL(){{
            UPDATE("tags");
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

}
