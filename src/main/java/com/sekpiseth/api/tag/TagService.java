package com.sekpiseth.api.tag;


import com.github.pagehelper.PageInfo;
import com.sekpiseth.api.tag.web.TagDto;

public interface TagService {
    PageInfo<TagDto> findAll(Integer page, Integer limit);
    TagDto findById(Integer id);
    TagDto addNew(TagDto tagDto);
    TagDto editById(Integer id, TagDto tagDto);
    Integer removeById(Integer id);
    Integer removeByStatus(Integer id, Boolean status);
}
