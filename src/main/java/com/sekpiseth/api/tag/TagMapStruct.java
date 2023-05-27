package com.sekpiseth.api.tag;

import com.github.pagehelper.PageInfo;
import com.sekpiseth.api.tag.web.TagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapStruct { // Tag

    PageInfo<TagDto> toPageInfoTagDto(PageInfo<Tag> tagPageInfo);
    Tag fromTagDto(TagDto tagDto);
    TagDto toTag(Tag tag);

}
