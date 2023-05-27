package com.sekpiseth.api.tag;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface TagRepository {

    @SelectProvider(type = TagProvider.class, method = "buildSelectSql")
    List<Tag> select();

    @SelectProvider(type = TagProvider.class, method = "buildSelectByIdSql")
    Optional<Tag> selectById(@Param("id") Integer id);

    @InsertProvider(type = TagProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("tag") Tag tag);

    @UpdateProvider(type = TagProvider.class, method = "buildUpdateSql")
    void update(@Param("tag") Tag tag);

    @DeleteProvider(type = TagProvider.class, method = "buildDeleteSql")
    void delete(@Param("id") Integer id);

    @UpdateProvider(type = TagProvider.class, method = "buildDeleteByStatusSql")
    void deleteByStatus(@Param("id") Integer id, @Param("status") Boolean status);

    @Select("SELECT EXISTS(SELECT * FROM tags WHERE id = #{id})")
    Boolean isExists(@Param("id") Integer id);

}
